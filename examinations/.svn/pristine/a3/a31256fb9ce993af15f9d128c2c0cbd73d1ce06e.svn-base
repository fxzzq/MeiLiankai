package com.minlength.proj.examinations.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.minlength.platform.core.security.context.SecurityContext;
import com.minlength.platform.core.template.service.TemplateService;
import com.minlength.platform.core.utils.JSONUtil;
import com.minlength.platform.core.utils.file.ExcelUtil;
import com.minlength.platform.core.web.context.WebSystemContext;
import com.minlength.proj.examinations.service.ImportService;
import com.minlength.proj.examinations.vo.Configuration;
import com.minlength.proj.examinations.vo.Result;

@Controller
@RequestMapping("/proj/examinations")
public class ImportController {
	
	@Resource
	private TemplateService templateService;
	
	@Resource
	ImportService importService;
	
	@Resource
	private Configuration configuration;
	
	@ResponseBody
	@RequestMapping(value = "/import.do",produces="text/html;charset=UTF-8")
	public String fileImport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
    	String bathPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    	Map<String, Object> mp = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();
		String account = request.getParameter("account");
		if (account == null || "".equals(account)) {
			return "<html><body>获取帐号失败!</body></html>";
		}
    	mp.put("account", account);
    	mp.put("basePath", bathPath);
    	mp.putAll(WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get());
    	String content = templateService.classPathTemplate("fileimport.html", mp);
    	response.setCharacterEncoding("UTF-8");
		return content;
	}
	
	@ResponseBody
	@RequestMapping(value = "/import.action",produces="application/json;charset=UTF-8")
	public String importAction(@RequestParam MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String account = request.getParameter("account");
		if (account == null || "".equals(account)) {
			Result result = new Result();
			result.setStatus(500);
			result.setEmsg("参数传递失败，请重新打开页面!");
			return JSONUtil.toJson(result);
		}
		SecurityContext.CURRENT_LOGIN_USER_ACCOUNT.set(account);
		String fileName = file.getOriginalFilename();
		String type = fileName.substring(fileName.lastIndexOf(".")+1);
		if (type.equalsIgnoreCase("zip")) {
			return importService.importZipFile(file);
		}
		else {
			return importService.importDocFile(file);
		}
	}
	@RequestMapping(value = "/downloadzip.action",produces="application/zip")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = configuration.getZipTemplate();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		if (is == null) {
			response.getWriter().println("<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> </header><body>服务器文件不存在!</body></html>");
			return;
		}
		String fileName = path.substring(path.lastIndexOf("/")+1);
		byte[]data = new byte[is.available()];
		is.read(data);
		fileName = URLEncoder.encode(fileName, "UTF-8");  
		response.reset();  
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.addHeader("Content-Length", "" + data.length);  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(data);  
	    outputStream.flush();  
	    outputStream.close();
	}
	@RequestMapping(value = "/downloadexcel.action",produces="application/x-xls")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = configuration.getExcelTemplate();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		if (is == null) {
			response.getWriter().println("<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> </header><body>服务器文件不存在!</body></html>");
			return;
		}
		String fileName = path.substring(path.lastIndexOf("/")+1);
		byte[]data = new byte[is.available()];
		is.read(data);
		fileName = URLEncoder.encode(fileName, "UTF-8");  
		response.reset();  
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.addHeader("Content-Length", "" + data.length);  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(data);  
	    outputStream.flush();  
	    outputStream.close();
	}

}
