package com.minlength.platform.core.utils.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于描述xml与java对象映射的注解
 * @author shug
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ElementMapping {
	
	/**
	 * 集合对应的节点
	 * @return
	 */
	public String element();
	
	/**
	 * 集合对应的类
	 * @return
	 */
	public Class<?> type();

}