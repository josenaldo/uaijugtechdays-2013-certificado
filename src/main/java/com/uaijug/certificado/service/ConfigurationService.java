package com.uaijug.certificado.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;

import com.uaijug.certificado.model.Configuration;
import com.uaijug.certificado.repository.ConfigurationRepository;

@Singleton
public class ConfigurationService {

	@Inject
	private ConfigurationRepository configurationRepository;

	public void setConfigurationRepository(
			ConfigurationRepository configurationRepository) {
		this.configurationRepository = configurationRepository;
	}

	public List<Configuration> findAll() {

		return this.configurationRepository.findAll();
	}

	public Configuration findByKey(String key) {
		try {
			return this.configurationRepository.findByKey(key);
		} catch (NoResultException e) {
			return null;
		}
	}
}
