package com.minlength.proj.examinations.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Resource;

import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zefer.html.doc.r;

import com.minlength.platform.core.processor.Processor;
import com.minlength.platform.core.processor.sys.JdbcProcessor;
import com.minlength.platform.core.processor.sys.vo.JdbcUpdateResult;
import com.minlength.platform.core.security.context.SecurityContext;
import com.minlength.platform.core.utils.JSONUtil;
import com.minlength.platform.core.utils.RegUtil;
import com.minlength.platform.core.utils.file.ExcelUtil;
import com.minlength.proj.examinations.vo.Result;


/**
 * @author zhangz
 * 导入服务
 */
@Service
@Scope(value="singleton")
public class ImportService {
	
	@Resource
	private Processor processor;
	
	private boolean debugMode = false;

	public String importDocFile(MultipartFile file) throws IOException {
		String account = "admin";
		if (!debugMode) {
    		account = SecurityContext.CURRENT_LOGIN_USER_ACCOUNT.get();
		}
		Result result = new Result();
		result.setSuccess(false);
		String filename = file.getOriginalFilename();
		String suff = filename.substring(filename.lastIndexOf(".")+1);
		InputStream input = file.getInputStream();
		List<Map<String, String>> data =ExcelUtil.readExcel(input, suff);
		List<Object[]> params = new ArrayList<Object[]>();
		for(int i = 0; i < data.size(); i++){
			Map<String, String> oneData = data.get(i);
			List<Map<String, Object>> contentList = new ArrayList<Map<String,Object>>();
			TreeSet<String>keys = new TreeSet<String>(oneData.keySet());
			Iterator<String>iterator = keys.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> option = new HashMap<String, Object>();
				String key =  iterator.next();
				String value = oneData.get(key);
				if (key.matches("[a-zA-Z]")) {
					option.put("name", key.toUpperCase());
					option.put("value", value);
					option.put("type","charactor");
					contentList.add(option);
				}
				
			}
			
			result = validateQuertion(null,account,filename,oneData,contentList,i+1);
			if (!result.isSuccess()) {
				return JSONUtil.toJson(result);
			}
			params.add((Object[]) result.getCustom());
		}
		return JSONUtil.toJson(saveDatabase(params));
	}
	
	public String importZipFile(MultipartFile file) throws IOException {
		Result result = new Result();
		boolean hasFile = false;
		String account = "admin";
		if (!debugMode) {
    		account = SecurityContext.CURRENT_LOGIN_USER_ACCOUNT.get();
		}
		ZipFile zipFile = null;
		File f = null;
		List<Object[]> params = new ArrayList<Object[]>();
		try{
			f = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +   
	                UUID.randomUUID().toString());
			file.transferTo(f);
			//Windows系统压缩的文件默认编码为GBK
			zipFile = new ZipFile(f,Charset.forName("GBK"));
			Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
			if (zipEntries.hasMoreElements()) {
				try {
					ZipEntry entry = zipEntries.nextElement();
					zipEntries = zipFile.entries();
				} catch (IllegalArgumentException e) {
					//Linux系统压缩的文件编码为UTF-8
					zipFile = new ZipFile(f);
					zipEntries = zipFile.entries();
				}
			}
			while(zipEntries.hasMoreElements()) {
				ZipEntry entry = zipEntries.nextElement();
				if (entry.isDirectory()) {
					continue;
				}
				String fileName = entry.getName();
				if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
					hasFile = true;
					String suff = fileName.substring(fileName.lastIndexOf(".")+1);
					InputStream input = zipFile.getInputStream(entry);
					List<Map<String, String>> data =ExcelUtil.readExcel(input, suff);
					
					for(int i = 0; i < data.size(); i++){
						result = new Result();
						result.setStatus(500);
						Map<String, String> oneData = data.get(i);
						List<Map<String, Object>> contentList = new ArrayList<Map<String,Object>>();
						TreeSet<String>keys = new TreeSet<String>(oneData.keySet());
						Iterator<String>iterator = keys.iterator();
						while (iterator.hasNext()) {
							Map<String, Object> option = new HashMap<String, Object>();
							String key =  iterator.next();
							String value = oneData.get(key);
							if(value == "") {result.setEmsg("文件："+fileName+"\n第"+(i+1)+"行，选项"+key+"错误，选项不能为空!");return JSONUtil.toJson(result);}
							//替换题干
							
							if (key.matches("[a-zA-Z]")) {
								option.put("name", key.toUpperCase());
								if(value.matches(".*\\$\\{.*}.*")) {//$替换符，表示该选项为图片
									List<String> strs = RegUtil.find("\\$\\{([\\w\\u4e00-\\u9fa5\\\\.\\/]+)\\}", value);
									String src  = value;
									for (String string : strs) {
										//value = value.replace("${", "").replace("}", "");
										ZipEntry imgEntry =zipFile.getEntry(string);
										if(string == "" || imgEntry == null) {result.setEmsg("文件："+fileName+"\n第"+(i+1)+"行，选项"+key+"错误，选项对应图片不存在!");return JSONUtil.toJson(result);}
										String imgSuff = imgEntry.getName().substring(imgEntry.getName().lastIndexOf(".")+1);
										InputStream img = zipFile.getInputStream(imgEntry);
										Base64 base64 = new Base64();
										byte bt[] = new byte[img.available()];
										img.read(bt);
										String code = base64.encodeToString(bt);
										src = src.replace("${"+string+"}", "<img src='data:image/"+imgSuff+";base64,"+code+"'>") ;
									}
									option.put("value", src);
									option.put("type","image");
								}
								else {
									option.put("value", value);
									option.put("type","charactor");
								}
								contentList.add(option);
							}
							
						}
						
						result = validateQuertion(zipFile,account,fileName,oneData,contentList,i+1);
						if (!result.isSuccess()) {
							result.setCustom(null);
							return JSONUtil.toJson(result);
						}
						params.add((Object[]) result.getCustom());
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zipFile != null) {
				zipFile.close();
			}
			f.delete();
		}
		
		
		result.setSuccess(hasFile);
		if (hasFile) {
			JdbcUpdateResult updateResult = saveDatabase(params);
			if (updateResult.isSuccess()) {
				result.setStatus(200);
				result.setSuccess(true);
				result.setEmsg("导入成功!");
			}
			else {
				result.setEmsg(updateResult.getEmsg());
			}
			
			return JSONUtil.toJson(result);
		}
		else {
			result.setEmsg("文件中未包含Excel文件，导入失败!");
			return JSONUtil.toJson(result);
		}
	}
	/**
	 * 将数据保存到数据库
	 * @param params
	 * @return
	 */
	private JdbcUpdateResult saveDatabase(List<Object[]> params) {
		String sql = "insert into tb_proj_examinations_examqusetions(`uuid`,`creator`,`type`,`stem`,`content`,`analysis`,`answer`,`multi`) values(UUID(),?,?,?,?,?,?,?)";
		return processor.start(null).routeTo(JdbcProcessor.class).beginJdbcTransaction()
		.jdbcUpdate(sql, params).commitJdbcTransaction().preValue(JdbcUpdateResult.class);
	}
	
	private Result validateQuertion(ZipFile zipFile,String account,String fileName,Map<String, String> oneData,List<Map<String, Object>> contentList, int i) throws IOException {
		Result result = new Result();
		result.setSuccess(true);
		String stem = oneData.get("题干");
		result.setStatus(500);
		if(stem == "") {result.setEmsg("文件："+fileName+"\n第"+i+"行错误，题干不能为空!");return result;}
		List<String> strs = RegUtil.find("\\$\\{([\\w\\u4e00-\\u9fa5\\\\.\\/]+)\\}", stem);
		if (!strs.isEmpty()) {
			String src  = stem;
			for (String string : strs) {
				if (zipFile == null) {
					break;
				}
				//value = value.replace("${", "").replace("}", "");
				ZipEntry imgEntry =zipFile.getEntry(string);
				if(string == "" || imgEntry == null) {result.setSuccess(false);result.setEmsg("文件："+fileName+"\n第"+(i)+"行错误，题干对应图片"+"${"+string+"}不存在!");return result;}
				String imgSuff = imgEntry.getName().substring(imgEntry.getName().lastIndexOf(".")+1);
				InputStream img = zipFile.getInputStream(imgEntry);
				Base64 base64 = new Base64();
				byte bt[] = new byte[img.available()];
				img.read(bt);
				String code = base64.encodeToString(bt);
				src = src.replace("${"+string+"}", "<img src='data:image/"+imgSuff+";base64,"+code+"'>") ;
			}
			stem = src;
		}
		
		
		
		if(contentList.size() == 0) {result.setSuccess(false);result.setEmsg("第"+i+"行错误，选项不能为空!");return result;}
		String type = oneData.get("类型");
		if(type == "") {result.setSuccess(false);result.setEmsg("文件："+fileName+"\n第"+i+"行错误，类型不能为空!");return result;}
		String ani = oneData.get("解析");
		String answer = oneData.get("答案");
		if(answer == "") {result.setSuccess(false);result.setEmsg("文件："+fileName+"\n第"+i+"行错误，答案不能为空!");return result;}
		if(!answer.matches("[a-zA-z]+")) {result.setSuccess(false);result.setEmsg("文件："+fileName+"\n第"+i+"行错误，答案格式错误!");return result;}
		int multi = answer.length() > 1?1 : 0;
		if (multi == 1) {
			stem = "(多选)"+stem;
		}
		else {
			stem = "(单选)"+stem;
		}
		Object[]param = new Object[] {account,type,stem,JSONUtil.toJson(contentList),ani,answer,multi};
		result.setCustom(param);
		result.setStatus(200);
		return result;
	}
	private String getOptionType(String value) {
		return null;
	}
}
