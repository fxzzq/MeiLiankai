package com.minlength.platform.core.utils;

import java.io.StringWriter;
import java.math.BigDecimal;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 格式化工具类
 * @author shugang
 *
 */
public class FormatUtil {
	
	/**
	 * 将double保留小数
	 * @param value		需要转换的double值
	 * @param scale	保留的小数位数
	 * @return
	 */
	public static double decimal(double value, int scale){
		BigDecimal bg = new BigDecimal(value);
		return bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static String formatXml(String str) {
        try{
        	Document document = null;
            document = DocumentHelper.parseText(str);
            // 格式化输出格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            StringWriter writer = new StringWriter();
            // 格式化输出流
            XMLWriter xmlWriter = new XMLWriter(writer, format);
            // 将document写入到输出流
            xmlWriter.write(document);
            xmlWriter.close();
            return writer.toString();
        }catch(Exception e){
        	return str;
        }
    }

}
