package com.uaijug.certificado.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.jukito.UseModules;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.uaijug.certificado.model.Configuration;
import com.uaijug.certificado.module.BasicConfigurationTestModule;
import com.uaijug.certificado.module.RepositoryModule;
import com.uaijug.certificado.test.AbstractIntegrationTest;
import com.uaijug.certificado.test.annotation.type.IntegrationTest;

@Category(IntegrationTest.class)
@UseModules(value = { BasicConfigurationTestModule.class,
		RepositoryModule.class })
public class ConfigurationServiceIntegrationTest extends
		AbstractIntegrationTest {

	@Inject
	private ConfigurationService configurationService;

	@Test
	public void testFindAll() {
		List<Configuration> list = this.configurationService.findAll();
		assertThat("Lista deveria ter 8 elementos", list, hasSize(8));
	}

	@Test
	public void testFindByName_found() {
		Configuration config = this.configurationService
				.findByKey("email.username");

		assertThat("A configuração deveria ter sido encontrada", config,
				is(notNullValue()));
		assertThat("A configuração deveria ser igual a test@localhost.com",
				config.getValue(), is(equalTo("test@localhost.com")));
	}

	@Test
	public void testFindByName_notFound() {
		Configuration config = this.configurationService
				.findByKey("nao_existe");

		assertThat("Uma configuração não deveria ter sido encontrada", config,
				is(nullValue()));
	}
}
