package com.uaijug.certificado.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.repository.ParticipantRepository;

@Singleton
public class ParticipantService {

	@Inject
	private ParticipantRepository participantRepository;

	public void setParticipantRepository(
			ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	public List<Participant> findAll() {
		return this.participantRepository.findAll();
	}

}
