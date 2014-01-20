package com.uaijug.certificado.test.module;

import com.uaijug.certificado.module.loader.FilePropertiesLoader;
import com.uaijug.certificado.module.loader.PropertiesLoader;

public class MysqlTestPropertiesConfigModule extends TestPropertiesConfigModule {

	@Override
	public String getConfigFile() {
		return "d:\\config_real.properties";
	}

	@Override
	protected PropertiesLoader getPropertiesLoader() {
		return new FilePropertiesLoader();
	}
}
