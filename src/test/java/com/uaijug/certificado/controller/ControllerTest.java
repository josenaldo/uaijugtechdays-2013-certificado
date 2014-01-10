package com.uaijug.certificado.controller;

import static org.mockito.Mockito.*;

import java.util.Map;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(JukitoRunner.class)
public class ControllerTest {

	@Mock
	private Command command;

	@Mock
	private Map<String, Command> commands;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testExecute() throws Exception {
		when(this.commands.get("teste")).thenReturn(this.command);

		Controller controller = new Controller(this.commands);

		controller.execute("teste", null);

		verify(this.command).execute(null);
	}
}
