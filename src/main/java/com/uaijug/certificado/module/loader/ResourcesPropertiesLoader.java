package com.uaijug.certificado.module.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.uaijug.certificado.module.PropertiesConfigModule;

public class ResourcesPropertiesLoader implements PropertiesLoader {

	@Override
	public Properties loadProperties(String path) throws IOException {
		Properties properties = new Properties();

		ClassLoader classLoader = PropertiesConfigModule.class.getClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream(path);
		properties.load(resourceAsStream);

		return properties;
	}

}
