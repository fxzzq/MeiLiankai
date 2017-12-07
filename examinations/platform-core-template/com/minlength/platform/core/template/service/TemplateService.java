package com.minlength.platform.core.template.service;

import java.util.Map;

/**
 * freemaker模板工具的处理接口
 * @author shugang
 *
 */
public interface TemplateService {
	
	/**
	 * 已此字符串开头表示为模板替换
	 */
	public static final String START_WITH_PREFIX = "template:";
	
	public static final String IS_TEMPLATE_PATTERN_START = "$_TMP_{";
	public static final String IS_TEMPLATE_PATTERN_END = "}";
	/**
	 * 已此正则表达是的为模板替换
	 */
	public static final String IS_TEMPLATE_PATTERN = "^\\$_TMP_\\{(.+)\\}$";
	
	/**
	 * 对字符串进行模板替换
	 * @param content		字符串模板内容
	 * @param data			模板的匹配数据
	 * @return
	 */
	public String stringTemplate(String content, Map<String, Object> data, boolean cache);
	
	/**
	 * 向字符串模板中加入一个字符串模板
	 * @param content	字符串模板
	 * @return
	 */
	public String addStringTemplate(String content);
	
	/**
	 * 根据类路径中的模板进行模板替换
	 * @param tmppath	模板的路径
	 * @param data		模板的数据
	 * @param local		模板国际化
	 * @return
	 */
	public String classPathTemplate(String tmppath, Map<String, Object> data);

}
