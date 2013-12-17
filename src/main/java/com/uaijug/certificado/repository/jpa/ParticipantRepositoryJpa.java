package com.uaijug.certificado.repository.jpa;

import javax.inject.Singleton;

import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.repository.ParticipantRepository;

@Singleton
public class ParticipantRepositoryJpa extends GenericRepositoryJpa<Participant> implements ParticipantRepository{

	public ParticipantRepositoryJpa(String persistenceUnit) {
		super(persistenceUnit);
	}
}
