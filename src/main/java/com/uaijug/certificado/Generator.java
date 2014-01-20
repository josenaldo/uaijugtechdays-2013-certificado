package com.uaijug.certificado;

import org.apache.log4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.uaijug.certificado.controller.Controller;
import com.uaijug.certificado.controller.command.ParticipantReportCommand;
import com.uaijug.certificado.module.ControllerModule;
import com.uaijug.certificado.module.PropertiesConfigModule;
import com.uaijug.certificado.module.RepositoryModule;

public class Generator {

	private static final Logger log = Logger.getLogger(Generator.class);
	private Controller controller;

	public static void main(String[] args) {

		try {
			Generator generator = new Generator();

			generator.init();
			generator.execute();

		} catch (Exception e) {
			log.error("Um erro derrubou o gerador de relatório: ", e);
			System.exit(1);
		}
	}

	void init() throws Exception {
		this.init(new PropertiesConfigModule(), new RepositoryModule(),
				new ControllerModule());
	}

	void init(AbstractModule... abstractModules) throws Exception {
		Injector injector = Guice.createInjector(abstractModules);

		controller = injector.getInstance(Controller.class);
	}

	void execute() throws Exception {
		controller.execute(ParticipantReportCommand.COMMAND_NAME, null);
	}
}
