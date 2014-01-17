package com.uaijug.certificado.test.module;

import javax.inject.Named;

import com.google.inject.Provides;
import com.uaijug.certificado.module.PropertiesConfigModule;
import com.uaijug.certificado.test.annotation.config.ConfigTestDataset;

public class TestPropertiesConfigModule extends PropertiesConfigModule {

	@Provides
	@ConfigTestDataset
	public String getConfigTestDataset(@Named("test.dataset") String property) {
		return property;
	}
}
