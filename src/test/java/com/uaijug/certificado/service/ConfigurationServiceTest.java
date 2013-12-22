package com.uaijug.certificado.service;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uaijug.certificado.model.Configuration;
import com.uaijug.certificado.repository.ConfigurationRepository;

@RunWith(JukitoRunner.class)
public class ConfigurationServiceTest {

	@Mock
	private ConfigurationRepository configurationRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll() {

		List<Configuration> configurations = Arrays.asList(new Configuration(
				"config1", "value1"), new Configuration("config2", "value2"),
				new Configuration("config3", "value3"));

		when(this.configurationRepository.findAll()).thenReturn(configurations);

		ConfigurationService configurationService = new ConfigurationService();
		configurationService
				.setConfigurationRepository(this.configurationRepository);

		List<Configuration> list = configurationService.findAll();
		assertThat("Lista deveria ter 3 elementos", list, hasSize(3));

	}

}
