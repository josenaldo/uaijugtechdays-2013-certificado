package com.uaijug.certificado.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.uaijug.certificado.repository.ParticipantRepository;

@Singleton
public class ParticipantService {

	@Inject
	private ParticipantRepository participantDao;
}
