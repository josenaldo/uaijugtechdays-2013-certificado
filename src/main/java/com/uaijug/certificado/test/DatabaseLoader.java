package com.uaijug.certificado.test;

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
import com.uaijug.certificado.test.config.ConfigTestDataset;

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
		this.setup(this.dataset);
	}

	public void setup(String dataset) throws Exception {

		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				this.driver);

		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				this.user);

		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				this.password);

		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				this.url);

		this.databaseTester = new JdbcDatabaseTester(this.driver, this.url,
				this.user, this.password);

		// initialize your dataset here
		IDataSet dataSet = new FlatXmlDataSet(new File(dataset));

		this.databaseTester.setDataSet(dataSet);

		// will call default setUpOperation
		this.databaseTester.onSetup();

	}

	public void tearDown() throws Exception {
		// will call default tearDownOperation
		this.databaseTester.onTearDown();
	}
}
