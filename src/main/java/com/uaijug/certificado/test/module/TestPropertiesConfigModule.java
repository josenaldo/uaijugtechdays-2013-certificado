package com.uaijug.certificado.test.module;

import javax.inject.Named;

import com.google.inject.Provides;
import com.uaijug.certificado.module.PropertiesConfigModule;
import com.uaijug.certificado.module.loader.PropertiesLoader;
import com.uaijug.certificado.module.loader.ResourcesPropertiesLoader;
import com.uaijug.certificado.test.annotation.config.ConfigTestDataset;

public class TestPropertiesConfigModule extends PropertiesConfigModule {

	@Override
	public String getConfigFile() {
		return "config.properties";
	}

	@Override
	protected PropertiesLoader getPropertiesLoader() {
		return new ResourcesPropertiesLoader();
	}

	@Provides
	@ConfigTestDataset
	public String getConfigTestDataset(@Named("test.dataset") String property) {
		return property;
	}
}
