package com.uaijug.certificado.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import com.uaijug.certificado.config.ConfigDatabaseDriver;
import com.uaijug.certificado.config.ConfigDatabasePassword;
import com.uaijug.certificado.config.ConfigDatabaseUrl;
import com.uaijug.certificado.config.ConfigDatabaseUser;
import com.uaijug.certificado.test.annotation.config.ConfigTestDataset;

@Singleton
public class DatabaseLoader {

	private IDatabaseTester databaseTester;

	@Inject
	@ConfigDatabaseDriver
	private String driver;

	@Inject
	@ConfigDatabaseUrl
	private String url;

	@Inject
	@ConfigDatabaseUser
	private String user;

	@Inject
	@ConfigDatabasePassword
	private String password;

	@Inject
	@ConfigTestDataset
	private String dataset;

	public void setup() throws Exception {
		this.setup(dataset);
	}

	public void setup(String dataset) throws Exception {

		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, driver);

		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				user);

		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				password);

		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, url);

		databaseTester = new JdbcDatabaseTester(driver, url, user, password);

		// initialize your dataset here
		File file = new File(dataset);
		assertThat(
				"Arquivo do banco não foi encontrado: "
						+ file.getAbsolutePath(), file.exists(), is(true));

		IDataSet dataSet = new FlatXmlDataSet(file);

		databaseTester.setDataSet(dataSet);

		// will call default setUpOperation
		databaseTester.onSetup();

	}

	public void tearDown() throws Exception {
		// will call default tearDownOperation
		databaseTester.onTearDown();
	}
}
