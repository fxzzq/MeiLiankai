package com.minlength.platform.core.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类路径资源文件扫描工具类
 * @author shugang
 * @version 1.1 20170628
 *
 */
public class ClassPathResourceScanner {
	/**
	 * 查找类路径下的某些特定后缀的资源文件
	 * @param basePackage
	 * @param suffix
	 * @return
	 */
	public static Set<String> find(String packageName, String suffix) {
		Set<String> result = null;
		if (packageName == null || "".equals(packageName)) {
			return null;
		}
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = ClassPathResourceScanner.class.getClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					Set<String> temp = findAndAddClassesInPackageByFile(packageName, filePath, true, suffix);
					if (temp != null) {
						if (result == null) {
							result = new HashSet<String>();
						}
						result.addAll(temp);
					}
				} else if ("jar".equals(protocol)) {
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						Enumeration<JarEntry> entries = jar.entries();
						while (entries.hasMoreElements()) {
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							if (name.charAt(0) == '/') {
								name = name.substring(1);
							}
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								if (idx != -1) {
									packageName = name.substring(0, idx).replace('/', '.');
								}
								if (name.endsWith(suffix) && !entry.isDirectory()) {									
									result.add(name.substring(0, name.length()).replace(".", "/") + suffix);
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static Set<String> findAndAddClassesInPackageByFile(
			String packageName, String packagePath, final boolean recursive,
			final String suffix) {
		Set<String> result = null;
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return null;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(suffix));
			}
		});

		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				Set<String> temp = findAndAddClassesInPackageByFile(packageName + "." + file.getName(),file.getAbsolutePath(), recursive, suffix);
				if(temp != null){
					if(result == null){result = new HashSet<String>();}
					result.addAll(temp);
				}
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName();
				if (result == null) {
					result = new HashSet<String>();
				}
				result.add(packageName.replace(".", "/") + '/' + className);
			}
		}
		return result;
	}
}

