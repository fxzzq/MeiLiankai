package com.minlength.proj.examinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minlength.platform.core.function.processor.FunctionOperationProcessor;
import com.minlength.platform.core.function.service.FunctionManagerService;
import com.minlength.platform.core.function.vo.Function;
import com.minlength.platform.core.function.vo.Operation;
import com.minlength.platform.core.processor.Processor;
import com.minlength.platform.core.processor.RunnableProcessor;
import com.minlength.platform.core.processor.sys.JdbcProcessor;
import com.minlength.platform.core.processor.sys.vo.QueryResult;
import com.minlength.platform.core.security.context.SecurityContext;
import com.minlength.platform.core.security.manager.UserDetailsService;
import com.minlength.platform.core.template.service.TemplateService;
import com.minlength.platform.core.utils.JSONUtil;
import com.minlength.platform.core.web.context.WebSystemContext;
import com.minlength.platform.core.web.processor.WebSystemProcessor;

/**
 * 用于生成页面的控制器
 * @author shugang
 *
 */

@Controller
@RequestMapping("/view")
public class ViewController {
	
	@Resource
	private Processor processor;
	
	@Resource
	private TemplateService templateService;
	
	@Resource
	private FunctionManagerService functionService;
	
//	@Resource
//	private UserDetailsService userDetailsService;
	
	@RequestMapping(value = "/one2many.html",produces="text/html;charset=UTF-8")
    public void one2many(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{			
		Map<String, Object> data = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();		
		String fid = request.getParameter("fid");
		String opt = request.getParameter("opt");
		Function _function = functionService.getFunctionById(fid);
		Operation _operation = _function.getOperationById(opt);
		data.put("forwardParams", _operation.getForwardParams());
		
		Function _forward_function = functionService.getFunctionById(_operation.getForwardParams());
		if(_forward_function != null){
			data.put("forwardFunction", _forward_function);
		}		
		for(Operation _subopt : _operation.getOperations()){
			data.put(_subopt.getId(), _subopt);
		}
		data.put("_operation", _operation);
		data.put("_function", _function);
		String id = request.getParameter("id");
		data.put("rowid", id);		
		String content = templateService.classPathTemplate("one2many.html", data);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
		
	@RequestMapping(value = "/org.html",produces="text/html;charset=UTF-8")
    public void org(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{			
		Map<String, Object> data = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();		
		String content = templateService.classPathTemplate("org.html", data);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	
	@RequestMapping(value = "/One2ManyOnOnePage.html",produces="text/html;charset=UTF-8")
	public void one2ManyOnOnePage(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{
		Map<String, Object> data = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();		
		String fid = request.getParameter("fid");
		Function _one_function = functionService.getFunctionById(fid);
		if(_one_function == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}
		String opt = "manytable";
		Operation _operation = _one_function.getOperationById(opt);
		
		Function _many_function = functionService.getFunctionById(_operation.getForwardParams());
		if(_many_function != null){
			data.put("_many_function", _many_function);
		}		

		data.put("_one_function", _one_function);
	
		String content = templateService.classPathTemplate("One2ManyOnOnePage.html", data);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
	}
	
	@RequestMapping(value = "/timetable.html",produces="text/html;charset=UTF-8")
	public void timetable(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{
		Map<String, Object> data = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();		
		String fid = request.getParameter("fid");
		Function function = functionService.getFunctionById(fid);
		if(function == null){
			response.getWriter().print("<html>页面不存在!</html>");
			return;
		}
		
		data.put("function", function);
		
		String content = templateService.classPathTemplate("timetable.html", data);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
	}
	
	@RequestMapping(value = "/filelistview.html",produces="text/html;charset=UTF-8")
    public void filelistview(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{	    
    	response.setCharacterEncoding("UTF-8");
    	String fid = request.getParameter("fid");
    	String singlefid = request.getParameter("singlefid");
    	String finstid = request.getParameter("finstid");
    	if(fid == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}
    	Function _function = functionService.getFunctionById(fid);
    	if(_function == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}    	    	
    	String json = JSONUtil.toJson(_function);
        Map<String, Object> mp = JSONUtil.parseJson2Map(json);
        if(mp.get("operations") != null){
        	List<Map<String, Object>> operations = (List<Map<String, Object>>)mp.get("operations");
        	for(Map<String, Object> _mp_ : operations){
        		if(_mp_.get("value") != null && _mp_.get("value").toString().startsWith("template:")){
        			_mp_.put("value", templateService.classPathTemplate(_mp_.get("value").toString().substring(_mp_.get("value").toString().indexOf(':')+1), WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get()));
        		}
        	}
        }
        //如果加载调整模板为单独的文件,则需要先替换
    	if(_function.getOnlistpageready() != null && _function.getOnlistpageready().startsWith("template:")){
    		mp.put("onlistpageready", templateService.classPathTemplate(_function.getOnlistpageready().substring(_function.getOnlistpageready().indexOf(':')+1), WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get()));
    	}
    	
		mp.put("finstid", finstid!=null?finstid:"-1");
		mp.put("singlefid", singlefid);
    	mp.putAll(WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get());
    	String content = templateService.classPathTemplate("filelistview.html", mp);
    	response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	@RequestMapping(value = "/fileupload.html",produces="text/html;charset=UTF-8")
    public void fileupload(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{	    
    	response.setCharacterEncoding("UTF-8");
    	String singlefid = request.getParameter("singlefid");
    	String finstid = request.getParameter("finstid");
    	Map<String, Object> mp = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();
    	
		mp.put("finstid", finstid!=null?finstid:"-1");
		mp.put("singlefid", singlefid);
    	mp.putAll(WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get());
    	String content = templateService.classPathTemplate("fileupload.html", mp);
    	response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	@RequestMapping(value = "/import.html",produces="text/html;charset=UTF-8")
    public void _import(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{	    
    	response.setCharacterEncoding("UTF-8");
    	String fid = request.getParameter("fid");
    	String opt = request.getParameter("opt");
    	Map<String, Object> mp = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();
    	
		mp.put("fid", fid);
		mp.put("opt", opt);
    	mp.putAll(WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get());
    	String content = templateService.classPathTemplate("import.html", mp);
    	response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	@RequestMapping(value = "/one2manytree.html",produces="text/html;charset=UTF-8")
    public void one2manytree(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{	    
    	response.setCharacterEncoding("UTF-8");
    	String many = request.getParameter("many");
    	Map<String, Object> mp = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();
    	if(mp == null){mp = new HashMap<String, Object>();}
    	if(many != null && !"".equals(many)){
    		String[] manys = many.split(":");
    		if(manys.length > 2){
    			mp.put("fid", manys[0]);
    			mp.put("optquery", manys[1]);
    			mp.put("optupdate", manys[2]);
    			
    			Map<String, Object> params = new HashMap<String, Object>();
    			params.put("id", request.getParameter("id"));
    			params.put("magicboy", "yes");
    			//获取菜单权限树形结构
    			Map<String, Map<String, Object>> treeMap = new HashMap<String, Map<String, Object>>();
    			Function function = functionService.getFunctionById(manys[0]);
    			Operation opt = function.getOperationById(manys[1]);
    			QueryResult menus = processor.start().routeTo(FunctionOperationProcessor.class).doFunctionOperation("final", opt).routeTo(RunnableProcessor.class).end().preValue(QueryResult.class);
    			if(menus != null && !menus.isEmpty()){
    				List<Map<String, Object>> menulist = menus.getData();
    				for(Map<String, Object> _mp : menulist){				
    					treeMap.put(_mp.get("id").toString(), _mp);    					  				
    				}
    				for(Map<String, Object> _mp : menulist){
    					String pid = _mp.get("pid")!=null?_mp.get("pid").toString():null;
    					if(pid == null){continue;}
    					Map<String, Object> pmenu = treeMap.get(pid);				
    					if(pmenu == null){continue;}
    					if(_mp.get("flag") == null || !"1".equals(_mp.get("flag").toString())){
    						pmenu.put("flag", "0");
    					}
    					Object childrenObj = pmenu.get("children");
    					List<Map<String, Object>> children = null;
    					if(childrenObj == null){
    						children = new ArrayList<Map<String, Object>>();
    						pmenu.put("children", children);
    					}else{
    						children = (List<Map<String, Object>>)childrenObj;
    					}
    					children.add(_mp);
    				}
    			}
    			if(treeMap.get("-1") != null){
    				mp.put("menus", treeMap.get("-1"));
    			}
    		}
    	}
		mp.put("id", request.getParameter("id"));		
    	String content = templateService.classPathTemplate("one2manytree.html", mp);
    	response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	@RequestMapping(value = "/home.html",produces="text/html;charset=UTF-8")
    public void home(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{			
		Map<String, Object> data = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();
		String content = templateService.classPathTemplate("home.html", data);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	@RequestMapping(value = "/index.html",produces="text/html;charset=UTF-8")
    public void index(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{			
		Map<String, Object> data = WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+path+"/"; 
		List<Map<String, Object>> topMenus = getMenuTree("-1",basePath, processor.start().routeTo(WebSystemProcessor.class).acceptParameter(request).end().preValue(Map.class));		
		data.put("topmenus", topMenus);
		//当前用户
		//data.put("loginuser", userDetailsService.loadUserByUsername(SecurityContext.CURRENT_LOGIN_USER_ACCOUNT.get()));
		String content = templateService.classPathTemplate("index.html", data);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	@RequestMapping(value = "/listview.html",produces="text/html;charset=UTF-8")
    public void listview(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{	    
    	response.setCharacterEncoding("UTF-8");
    	String fid = request.getParameter("fid");
    	if(fid == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}
    	Function _function = functionService.getFunctionById(fid);
    	if(_function == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}    	    	
    	String json = JSONUtil.toJson(_function);
        Map<String, Object> mp = JSONUtil.parseJson2Map(json);
        if(mp.get("operations") != null){
        	List<Map<String, Object>> operations = (List<Map<String, Object>>)mp.get("operations");
        	for(Map<String, Object> _mp_ : operations){
        		if(_mp_.get("value") != null && _mp_.get("value").toString().startsWith("template:")){
        			_mp_.put("value", templateService.classPathTemplate(_mp_.get("value").toString().substring(_mp_.get("value").toString().indexOf(':')+1), WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get()));        			    		
        		}        		
        	}
        }
        //如果加载调整模板为单独的文件,则需要先替换
    	if(_function.getOnlistpageready() != null && _function.getOnlistpageready().startsWith("template:")){
    		mp.put("onlistpageready", templateService.classPathTemplate(_function.getOnlistpageready().substring(_function.getOnlistpageready().indexOf(':')+1), WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get()));
    	}
    	
    	String finstid = request.getParameter("finstid");
		mp.put("finstid", finstid!=null?finstid:"-1");
    	mp.putAll(WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get());
    	String content = templateService.classPathTemplate("list.html", mp);
    	response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	@RequestMapping(value = "/editor.html",produces="text/html;charset=UTF-8")
    public void editor(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{	
		response.setCharacterEncoding("UTF-8");
    	String fid = request.getParameter("fid");
    	String id = request.getParameter("id");
    	if(fid == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}
    	Function _function = functionService.getFunctionById(fid);
    	if(_function == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}
    	String json = JSONUtil.toJson(_function);
        Map<String, Object> mp = JSONUtil.parseJson2Map(json);
        mp.put("rowid", id);        
      //如果加载调整模板为单独的文件,则需要先替换
    	if(_function.getOnedtpageready() != null && _function.getOnedtpageready().startsWith("template:")){
    		mp.put("onedtpageready", templateService.classPathTemplate(_function.getOnedtpageready().substring(_function.getOnedtpageready().indexOf(':')+1), WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get()));
    	}
    	mp.putAll(WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get());
    	String content = templateService.classPathTemplate("editor.html", mp);
    	response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	@RequestMapping(value = "/newview.html",produces="text/html;charset=UTF-8")
    public void newview(HttpServletRequest request, HttpServletResponse response,String contentType) throws IOException{	
		response.setCharacterEncoding("UTF-8");
    	String fid = request.getParameter("fid");
    	if(fid == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}
    	Function _function = functionService.getFunctionById(fid);
    	if(_function == null){
    		response.getWriter().print("<html>页面不存在!</html>");
    		return;
    	}    	
    	String json = JSONUtil.toJson(_function);
    	System.out.println(json);
    	Map<String, Object> mp = JSONUtil.parseJson2Map(json);
        //如果加载调整模板为单独的文件,则需要先替换
    	if(_function.getOnnewpageready() != null && _function.getOnnewpageready().startsWith("template:")){
    		mp.put("onnewpageready", templateService.classPathTemplate(_function.getOnnewpageready().substring(_function.getOnnewpageready().indexOf(':')+1), WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get()));
    	}
    	mp.putAll(WebSystemContext.TEMPLATE.TEMPLATE_DEFAULT_PARAMS.get());
    	String content = templateService.classPathTemplate("new.html", mp);
    	response.setCharacterEncoding("UTF-8");
		response.getWriter().print(content);
    }
	
	private void setParentHashRight(String pid, Map<String, Map<String, Object>> treeMap){
		if(pid == null || "".equals(pid.trim()) || treeMap == null){return;}
		Map<String, Object> pmenu = treeMap.get(pid);
		if(pmenu == null){return;}
		pmenu.put("flag",1);
		setParentHashRight(pmenu.get("pid")!=null?pmenu.get("pid").toString():null, treeMap);
	}
	
	private List<Map<String, Object>> getMenuTree(String _pid, String basePath, Map<String, Object> _params){
		Map<String, Object> userDetails = SecurityContext.CURRENT_LOGIN_USER_DETAILS.get();
		long userId = Long.parseLong(userDetails.get("id").toString());
		//请求左侧菜单树
		QueryResult queryResult = processor.start().routeTo(JdbcProcessor.class)
				 .jdbcQuery("SELECT T2.id, T2.`value`,T2.`name`,T2.pid, IF(?=-1 OR T1.MID IS NOT NULL,true, false) AS flag, T2.ord FROM " +
				 		"(SELECT MID FROM ref_proj_user_menu WHERE UID=? " +
				 		"UNION " +
				 		"SELECT MID FROM ref_proj_usergroup_menu WHERE UGID IN (SELECT `GROUP` FROM tb_proj_system_user WHERE ID=?)) T1 " +
				 		"RIGHT JOIN tb_proj_system_menu T2 ON T1.MID=T2.ID ORDER BY T2.ord", new Object[]{userId,userId,userId}).routeTo(RunnableProcessor.class).end().preValue(QueryResult.class);
//		Map<String, Object> menus = functionService.operate("meloclan.platform.Menu", "query", null, _params);		
		List<Map<String, Object>> menulist = null;
		if(queryResult != null && !queryResult.isEmpty()){
			menulist = queryResult.getData();
		}		
		//进行数据封装
		Map<String, Map<String, Object>> treeMap = new HashMap<String, Map<String, Object>>();
		if(menulist != null){
			for(Map<String, Object> mp : menulist){				
				treeMap.put(mp.get("id").toString(), mp);
				if(mp.get("value")!=null && mp.get("value").toString().contains("{$basePath}")){
					mp.put("value", mp.get("value").toString().replace("{$basePath}", basePath));					
				}
				if(mp.get("value")!=null){
					String value = mp.get("value").toString();
        			if(!value.startsWith("${basePath}") && !value.startsWith("http://")){
        				//判断要跳转的组件是什么类型
        				Function forwardFunction = functionService.getFunctionById(value);
        				if(forwardFunction == null){continue;}
        				if("SIMPLECRUD".equals(forwardFunction.getType())){
        					value = basePath + "view/listview.html?magicboy=yes&fid="+value;
        				}else if("SELFASSTREE".equals(forwardFunction.getType())){
        					value = basePath + "view/tree.html?magicboy=yes&fid="+value;
        				}else if("platform.view.org.OrgView".equals(forwardFunction.getView())){
        					value = basePath + "view/org.html?magicboy=yes&fid="+value;
        				}else if("platform.view.common.One2ManySinglePanel".equals(forwardFunction.getView())) {
        					value = basePath + "view/One2ManyOnOnePage.html?magicboy=yes&fid="+value;
	        			}else if("platform.view.common.Timetable".equals(forwardFunction.getView())) {
	        				value = basePath + "view/timetable.html?magicboy=yes&fid="+value;
	        			}else if("dzzg.jwgl.paikegl.courseparam".equals(value)){
        					value = "editor.html?fid=dzzg.jwgl.paikegl.courseparam&id=1&magicboy=yes";
        				}
        				mp.put("value", value);
        			}    
				}
			}
		}		
		List<Map<String, Object>> topMenus = null;
		if(menulist != null){
			for(Map<String, Object> mp : menulist){
				String pid = mp.get("pid")!=null?mp.get("pid").toString():null;
				if(pid == null){continue;}
				Map<String, Object> pmenu = treeMap.get(pid);
				if(pmenu == null){treeMap.get(Long.parseLong(pid));}
				if(pmenu == null){continue;}
				if(mp.get("flag") != null && "1".equals(mp.get("flag").toString())){
					pmenu.put("flag", 1);
					setParentHashRight(pid, treeMap);
				}
				Object childrenObj = pmenu.get("children");
				List<Map<String, Object>> children = null;
				if(childrenObj == null){
					children = new ArrayList<Map<String, Object>>();
					pmenu.put("children", children);
				}else{
					children = (List<Map<String, Object>>)childrenObj;
				}
				children.add(mp);
			}
			//去掉没有权限的项
			for(Map<String, Object> mp : menulist){
				String pid = mp.get("pid")!=null?mp.get("pid").toString():null;
				if(pid == null){continue;}
				Map<String, Object> pmenu = treeMap.get(pid);				
				if(pmenu == null){continue;}
				Object childrenObj = pmenu.get("children");
				List<Map<String, Object>> children = null;
				if(childrenObj == null){
					children = new ArrayList<Map<String, Object>>();
					pmenu.put("children", children);
				}else{
					children = (List<Map<String, Object>>)childrenObj;
				}
				if(mp.get("flag") == null || !"1".equals(mp.get("flag").toString())){
					children.remove(mp);
				}				
			}
			
			Map<String, Object> _t1 = treeMap.get(_pid);
			if(_t1 != null && _t1.get("children")!=null){
				topMenus = (List<Map<String, Object>>)_t1.get("children");
			}
		}		
		
		if(topMenus == null){topMenus = new ArrayList<Map<String, Object>>();}
		
		return topMenus;
	}

}
