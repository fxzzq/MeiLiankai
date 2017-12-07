package com.minlength.platform.core.utils.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 用于实现xml到java对象映射的通用类
 * @author shug
 *
 */
public class ElementMappingManager {
	
	private static Logger logger = Logger.getLogger(ElementMappingManager.class);
		
	private static final SAXReader reader = new SAXReader();
	
	/**
	 * 安装文件
	 * @param stream
	 * @param clazz
	 * @param elementName
	 * @param classMapping
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> install(InputStream stream, Class<T> clazz, String elementName, Map<String, Class<?>> classMapping){		
		Document doc = null;
		try {
			doc = reader.read(stream);
		} catch (DocumentException e) {				
			logger.error("install resource error:", e);
			return null;
		}finally{
			if(stream != null){
				try {
					stream.close();
				} catch (IOException e) {}
			}
		}
		if(doc == null){
			return null;
		}
		Element root = doc.getRootElement();
		if(root == null){
			return null;
		}
		List<Element> elementList = root.elements(elementName);
		if(elementList == null){
			return null;
		}
		List<T> temp = new ArrayList<T>();
		for(int i = 0; i < elementList.size(); i++){
			Element element = elementList.get(i);
			T instance = null;
			try {
				instance = clazz.newInstance();
			} catch (Exception e) {
				logger.error("部分资源文件安装成功!");
				continue;
			} 
			instance = ElementMappingManager.elementToObject(null, element, instance, classMapping);
			if(instance != null){
				temp.add(instance);
			}
		}
		return temp;
	}
	
	/**
	 * 安装流程定义
	 * @param input 流程定义的输入流
	 * @throws FileNotFoundException
	 */
	public static <T> List<T> install(File config, Class<T> clazz, String elementName, Map<String, Class<?>> classMapping) throws FileNotFoundException{		
		if(config == null || !config.exists() || !config.isFile()){
			logger.error("install resource file error!");
			return null;
		}
		
		//判断文件类型，如果是以.r结尾的需要解密
		InputStream stream = null;
		stream = new FileInputStream(config);
		return install(stream, clazz, elementName, classMapping);		
	}
	
	/**
	 * 将xml节点映射成java对象
	 * @param element	xml节点
	 * @param instance	java对象实例
	 * @return
	 */
	public static <T> T elementToObject(Object father, Element element, T instance, Map<String, Class<?>> classMapping){
		if(element == null || instance == null){
			return null;
		}
		instance = fillElementAttributeToObjectField(father, element, instance, classMapping);
		return instance;
	}
	
	/**
	 * 将xml节点的
	 * @param element	xml节点
	 * @param instance	对象实例
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T fillElementAttributeToObjectField(Object father, Element element, T instance, Map<String, Class<?>> classMapping){
		if(element == null || instance == null){
			return null;
		}
		
		//判断节点名称和对象类名称是否对应
		String className = instance.getClass().getName();		
		String elementName = element.getName();
		if(classMapping.get(elementName) == null){
			logger.error("element '"+elementName+"' mapping class undefined!");
			return null;
		}
		if(!className.equals(classMapping.get(elementName).getName())){
			return null;
		}
		//获取java类的所有字段
		Field[] fields = instance.getClass().getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			Field fd = fields[i];
			if(fd.getName().equals("father")){
				fd.setAccessible(true);
				try {
					fd.set(instance, father);
				} catch (Exception e) {
					continue;
				}
				fd.setAccessible(false);
				continue;
			}
			if(fd.getType().equals(String.class)){
				//获取属性值
				Attribute attribute = element.attribute(fd.getName());
				if(attribute != null){
					String value = attribute.getValue();
					fd.setAccessible(true);
					try {
						fd.set(instance, value);
					} catch (Exception e) {
						continue;
					}
					fd.setAccessible(false);					
				}
			}else if(fd.getType().equals(List.class)){//如果为数组，则进行递归
				List listValue = null;
				//首先检查字段上是否有标签
				ElementMapping elementMapping = fd.getAnnotation(ElementMapping.class);
				if(elementMapping != null){//如果不为空，则查找下一个节点
					String elementMappingElement = elementMapping.element();
					Class<?> elementMappingType = elementMapping.type();
					//查找节点
					List<Element> subList = element.elements(elementMappingElement);
					if(subList == null){
						continue;
					}
					for(int j = 0; j < subList.size(); j++){
						Element subElement = subList.get(j);
						//在获取下级子节点集合
						List<Element> subsubElementList = subElement.elements();
						if(subsubElementList == null){
							continue;
						}
						for(int n = 0; n < subsubElementList.size(); n++){
							Element subsubElement = subsubElementList.get(n);
							Object subInstance = null;
							try {
								subInstance = elementMappingType.newInstance();
							} catch (Exception e) {								
								continue;
							}
							subInstance = fillElementAttributeToObjectField(instance, subsubElement, subInstance, classMapping);
							if(subInstance != null){
								if(listValue == null){
									listValue = new ArrayList();
								}
								listValue.add(subInstance);
							}
						}
					}
				}else {
					List<Element> subList = element.elements();
					if(subList == null){
						continue;
					}
					for(int j = 0; j < subList.size(); j++){
						Element subElement = subList.get(j);
						Class<?> clazz = classMapping.get(subElement.getName());
						Object subInstance = null;
						try {
							subInstance = clazz.newInstance();
						} catch (Exception e) {								
							continue;
						}
						subInstance = fillElementAttributeToObjectField(instance, subElement, subInstance, classMapping);
						if(subInstance != null){
							if(listValue == null){
								listValue = new ArrayList();
							}
							listValue.add(subInstance);
						}
					}
				}
				
				fd.setAccessible(true);
				try {
					fd.set(instance, listValue);
				} catch (Exception e) {
					continue;
				}
				fd.setAccessible(false);
			}else if(fd.getType().equals(boolean.class) || fd.getType().equals(Boolean.class)){
				//获取属性值
				Attribute attribute = element.attribute(fd.getName());
				if(attribute != null){
					String value = attribute.getValue();
					fd.setAccessible(true);
					try {
						fd.set(instance, Boolean.parseBoolean(value));
					} catch (Exception e) {
						continue;
					}
					fd.setAccessible(false);					
				}
			}else{//如果是对象类型
				
				Class<?> clazz = classMapping.get(fd.getName());
				if(clazz != null){//如果不为空，则表示配置了映射关系的					
					//查找子节点，由于是对象，因此子节点应该只有一个
					Element ele = element.element(fd.getName());
					if(ele != null){//如果不为空，则进行映射
						try {
							Object subInstance = clazz.newInstance();
							subInstance = fillElementAttributeToObjectField(instance, ele, subInstance, classMapping);
							fd.setAccessible(true);
							try {
								fd.set(instance, subInstance);
							} catch (Exception e) {
								continue;
							}
							fd.setAccessible(false);											
						} catch (Exception e) {
							e.printStackTrace();
						}
					}					
				}
				
			}
		}
		
		return instance;
	}

}
