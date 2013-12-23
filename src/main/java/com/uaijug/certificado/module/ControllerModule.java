package com.uaijug.certificado.module;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.uaijug.certificado.annotation.Commands;
import com.uaijug.certificado.controller.Command;
import com.uaijug.certificado.controller.command.ParticipantReportCommand;

public class ControllerModule extends AbstractModule {

	@Override
	protected void configure() {

	}

	@Provides
	@Commands
	public Map<String, Command> createCommands(
			ParticipantReportCommand participantReportCommand) {

		Map<String, Command> commands = new HashMap<String, Command>();
		commands.put(ParticipantReportCommand.COMMAND_NAME,
				participantReportCommand);

		return commands;
	}
}
