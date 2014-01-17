package com.uaijug.certificado.test.module;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MysqlTestPropertiesConfigModule extends TestPropertiesConfigModule {

	@Override
	public String getConfigFile() {
		return "d:\\config_real.properties";
	}

	@Override
	protected Properties getProperties(String configFile) throws IOException {
		Properties properties = new Properties();

		InputStream inputStream = new FileInputStream(configFile);

		properties.load(inputStream);

		return properties;
	}
}
