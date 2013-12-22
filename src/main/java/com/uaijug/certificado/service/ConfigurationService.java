package com.uaijug.certificado.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;

import com.uaijug.certificado.model.Configuration;
import com.uaijug.certificado.repository.ConfigurationRepository;

/**
 * Esse é o serviço de configurações, que pesquisa configurações externas.
 * 
 * Idealmente, haveria uma interface que possibilitasse vários tipos de
 * configurações, as configurações seriam carregadas apenas uma vez, poderíamos
 * ter configurações extraídas de properties, cache... Mas, considerando a
 * urgência atual, o tamanho minúsculo da aplicação e minha sensaquisse aguda
 * momentânea, solicito que abra uma Issue para que sua mãe implemente isso
 * agora.
 * 
 * @author Josenaldo
 * 
 */
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
