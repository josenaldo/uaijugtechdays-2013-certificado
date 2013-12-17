package com.uaijug.certificado.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class BasicConfigurationModule extends AbstractModule {

	@Override
	protected void configure() {
		this.bind(String.class).annotatedWith(Names.named("persistenceUnit"))
				.toInstance("CertificadosPU");
	}

}
