package com.uaijug.certificado.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.uaijug.certificado.annotation.Commands;

@Singleton
public class Controller {

	private Map<String, Command> commands;

	@Inject
	public Controller(@Commands Map<String, Command> commands) {
		this.commands = commands;
	}

	public void execute(String commandName, Map<String, Object> params)
			throws Exception {

		Command command = this.commands.get(commandName);

		command.execute(params);

	}
}
