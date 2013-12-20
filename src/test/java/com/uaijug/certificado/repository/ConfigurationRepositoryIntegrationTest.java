package com.uaijug.certificado.repository;

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
public class ConfigurationRepositoryIntegrationTest extends
		AbstractIntegrationTest {

	private String key = "TestKey";
	private String value = "TestValue";

	@Inject
	private ConfigurationRepository configurationRepository;

	@Test
	public void testSave() {
		Configuration configuration = new Configuration(this.key, this.value);

		this.configurationRepository.create(configuration);

		List<Configuration> configurations = this.configurationRepository
				.findAll();

		assertThat("Deveria haver 9 configurações", configurations, hasSize(9));

		Configuration savedConfiguration = this.configurationRepository
				.find(9L);
		assertThat("A chave deveria ser igual a " + this.key,
				savedConfiguration.getKey(), is(equalTo(this.key)));

		assertThat("O valor deveria ser igual a " + this.value,
				savedConfiguration.getValue(), is(equalTo(this.value)));

	}

	@Test
	public void testFindAll() {
		List<Configuration> configurations = this.configurationRepository
				.findAll();
		assertThat("Deveria haver 2 configurações", configurations, hasSize(8));
	}

	@Test
	public void testDelete() {

		this.configurationRepository.delete(1l);

		List<Configuration> configurations = this.configurationRepository
				.findAll();

		assertThat("Deve haver 1 configuratione", configurations, hasSize(7));

		Configuration configuration = this.configurationRepository.find(1L);
		assertThat("O configuratione 1 não deveria mais existir",
				configuration, is(nullValue()));
	}

	@Test
	public void testFindByKey() {
		Configuration configuration = this.configurationRepository
				.findByKey("email.username");
		assertThat("Uma configuração deveria ter sido encontrada",
				configuration, is(notNullValue()));

		assertThat("A chave deveria ser email.username",
				configuration.getKey(), is(equalTo("email.username")));

		assertThat("O valor deveria ser test@localhost.com",
				configuration.getValue(), is(equalTo("test@localhost.com")));
	}

}
