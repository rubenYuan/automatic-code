package com.dy.bom.code.util;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class FreemarkerUtil {

	private static Configuration configuration = null;

	private static Configuration getConfiguration() {
		if (configuration == null) {
			configuration = new Configuration();
		}
		return configuration;
	}

	/**
	 * 根据模板渲染数据
	 * @param templateFile freemarker模板文件路径
	 * @param out	目标对象
	 * @param rootMap	值栈对象
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void flushData(String templateFile, Writer out, Object rootMap) throws IOException, TemplateException {
		flushData(templateFile, out, rootMap, "UTF-8", "#");
	}

	/**
	 * 根据模板渲染数据
	 * @param templateFile freemarker模板文件路径
	 * @param out	目标对象
	 * @param rootMap	值栈对象
	 * @param encoding	   编码，默认UTF-8
	 * @param numberFormat 数字格式化，默认#000.00
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void flushData(String templateFile, Writer out, Object rootMap, String encoding, String numberFormat) throws IOException, TemplateException {
		File template = new File(templateFile);
		if (!template.exists()) {
			throw new IOException("templateFile is NOT exist");
		}
		if (!template.isFile()) {
			throw new IOException("templateFile is NOT a File");
		}

		Configuration cfg = getConfiguration();
		cfg.setDefaultEncoding(StringUtils.defaultIfEmpty(encoding, "UTF-8"));
		cfg.setNumberFormat(StringUtils.defaultIfEmpty(numberFormat, "#"));

		String fullPath = FilenameUtils.getFullPathNoEndSeparator(templateFile);
		String templateName = FilenameUtils.getName(templateFile);
		File templateDir = new File(fullPath);
		cfg.setDirectoryForTemplateLoading(templateDir);
		Template t = cfg.getTemplate(templateName);
		t.process(rootMap, out);
	}
}
