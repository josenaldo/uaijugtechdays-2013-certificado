package com.uaijug.certificado.module;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.uaijug.certificado.config.ConfigDatabaseDriver;
import com.uaijug.certificado.config.ConfigDatabasePassword;
import com.uaijug.certificado.config.ConfigDatabaseUrl;
import com.uaijug.certificado.config.ConfigDatabaseUser;
import com.uaijug.certificado.config.ConfigEmailCharset;
import com.uaijug.certificado.config.ConfigEmailMessageParticipant;
import com.uaijug.certificado.config.ConfigEmailPassword;
import com.uaijug.certificado.config.ConfigEmailSmtpAuth;
import com.uaijug.certificado.config.ConfigEmailSmtpHost;
import com.uaijug.certificado.config.ConfigEmailSmtpPort;
import com.uaijug.certificado.config.ConfigEmailSmtpTransportType;
import com.uaijug.certificado.config.ConfigEmailStartTlsEnabled;
import com.uaijug.certificado.config.ConfigEmailTextType;
import com.uaijug.certificado.config.ConfigEmailUsername;
import com.uaijug.certificado.config.ConfigPersistenceUnit;
import com.uaijug.certificado.config.ConfigReportBackgroundPage1;
import com.uaijug.certificado.config.ConfigReportBackgroundPage2;
import com.uaijug.certificado.config.ConfigReportGeneratedDir;
import com.uaijug.certificado.config.ConfigReportParticipantTemplate;
import com.uaijug.certificado.config.ConfigReportTemplateDir;
import com.uaijug.certificado.test.annotation.type.IntegrationTest;
import com.uaijug.certificado.test.module.TestPropertiesConfigModule;

@RunWith(JukitoRunner.class)
@Category(IntegrationTest.class)
@UseModules(value = { TestPropertiesConfigModule.class })
public class PropertiesConfigModuleTest {

	public void testConfig(String property, String propertyName) {
		assertThat("A configuração " + propertyName + " não deveria ser nula",
				property, is(notNullValue()));
		assertThat("A configuração " + propertyName + " não deveria ser vazia",
				property.isEmpty(), is(false));
	}

	public void testConfigInteger(Integer property, String propertyName,
			Integer expected) {
		assertThat("A configuração " + propertyName + " não deveria ser nula",
				property, is(notNullValue()));

		assertThat("A configuração " + propertyName + " deveria ser "
				+ expected, property, is(equalTo(expected)));
	}

	@Test
	public void testConfigDatabaseDriver(@ConfigDatabaseDriver String property) {
		this.testConfig(property, "@ConfigDatabaseDriver");
	}

	@Test
	public void testConfigDatabasePassword(
			@ConfigDatabasePassword String property) {

		assertThat(
				"A configuração @ConfigDatabasePassword não deveria ser nula",
				property, is(notNullValue()));
		assertThat("A configuração @ConfigDatabasePassword deveria ser vazia",
				property.isEmpty(), is(true));
	}

	@Test
	public void testConfigDatabaseUrl(@ConfigDatabaseUrl String property) {
		this.testConfig(property, "@ConfigDatabaseUrl");
	}

	@Test
	public void testConfigDatabaseUser(@ConfigDatabaseUser String property) {
		this.testConfig(property, "@ConfigDatabaseUser");
	}

	@Test
	public void testConfigEmailCharset(@ConfigEmailCharset String property) {
		this.testConfig(property, "@ConfigEmailCharset");
	}

	@Test
	public void testConfigEmailMessageParticipant(
			@ConfigEmailMessageParticipant String property) {
		this.testConfig(property, "@ConfigEmailMessageParticipant");
	}

	@Test
	public void testConfigEmailPassword(@ConfigEmailPassword String property) {
		this.testConfig(property, "@ConfigEmailPassword");
	}

	@Test
	public void testConfigEmailSmtpAuth(@ConfigEmailSmtpAuth String property) {
		this.testConfig(property, "@ConfigEmailSmtpAuth");
	}

	@Test
	public void testConfigEmailSmtpHost(@ConfigEmailSmtpHost String property) {
		this.testConfig(property, "@ConfigEmailSmtpHost");
	}

	@Test
	public void testConfigEmailSmtpPort(@ConfigEmailSmtpPort Integer property) {
		this.testConfigInteger(property, "@ConfigEmailSmtpPort", 10058);
	}

	@Test
	public void testConfigEmailSmtpTransportType(
			@ConfigEmailSmtpTransportType String property) {
		this.testConfig(property, "@ConfigEmailSmtpTransportType");
	}

	@Test
	public void testConfigEmailStartTlsEnabled(
			@ConfigEmailStartTlsEnabled String property) {
		this.testConfig(property, "@ConfigEmailStartTlsEnabled");
	}

	@Test
	public void testConfigEmailTextType(@ConfigEmailTextType String property) {
		this.testConfig(property, "@ConfigEmailTextType");
	}

	@Test
	public void testConfigEmailUsername(@ConfigEmailUsername String property) {
		this.testConfig(property, "@ConfigEmailUsername");
	}

	@Test
	public void testConfigPersistenceUnit(@ConfigPersistenceUnit String property) {
		this.testConfig(property, "@ConfigPersistenceUnit");
	}

	@Test
	public void testConfigReportBackgroundPage1(
			@ConfigReportBackgroundPage1 String property) {
		this.testConfig(property, "@ConfigReportBackgroundPage1");
	}

	@Test
	public void testConfigReportBackgroundPage2(
			@ConfigReportBackgroundPage2 String property) {
		this.testConfig(property, "@ConfigReportBackgroundPage2");
	}

	@Test
	public void testConfigReportGeneratedDir(
			@ConfigReportGeneratedDir String property) {
		this.testConfig(property, "@ConfigReportGeneratedDir");
	}

	@Test
	public void testConfigReportParticipantTemplate(
			@ConfigReportParticipantTemplate String property) {
		this.testConfig(property, "@ConfigReportParticipantTemplate");
	}

	@Test
	public void testConfigReportTemplateDir(
			@ConfigReportTemplateDir String property) {
		this.testConfig(property, "@ConfigReportTemplateDir");
	}

}
