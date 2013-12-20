package com.uaijug.certificado;

import org.apache.log4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.uaijug.certificado.module.BasicConfigurationModule;
import com.uaijug.certificado.module.RepositoryModule;

public class Generator {

	private static final Logger log = Logger.getLogger(Generator.class);

	public static void main(String[] args) {

		try {
			Generator generator = new Generator();

			generator.init();

		} catch (Exception e) {
			log.error("Um erro derrubou o servidor de impress�o: ", e);
			System.exit(1);
		}
	}

	private void init() throws Exception {
		this.init(new BasicConfigurationModule(), new RepositoryModule());
	}

	private void init(AbstractModule... abstractModules) throws Exception {
		Injector injector = Guice.createInjector(abstractModules);

	}

}
