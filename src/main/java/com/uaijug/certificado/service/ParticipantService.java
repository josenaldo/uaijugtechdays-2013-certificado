package com.uaijug.certificado.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.uaijug.certificado.persistence.ParticipantDao;

@Singleton
public class ParticipantService {

	@Inject
	private ParticipantDao participantDao;
}
