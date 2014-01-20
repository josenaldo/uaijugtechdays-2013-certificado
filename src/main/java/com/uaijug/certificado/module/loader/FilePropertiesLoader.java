package com.uaijug.certificado.module.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class FilePropertiesLoader implements PropertiesLoader {

	@Override
	public Properties loadProperties(String path) throws IOException {
		Properties properties = new Properties();

		File file = new File(path);
		FileInputStream openInputStream = FileUtils.openInputStream(file);

		properties.load(openInputStream);

		return properties;
	}

}
