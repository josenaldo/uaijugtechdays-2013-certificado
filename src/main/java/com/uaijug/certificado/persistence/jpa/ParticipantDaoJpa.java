package com.uaijug.certificado.persistence.jpa;

import javax.inject.Singleton;

import com.uaijug.certificado.model.Participant;
import com.uaijug.certificado.persistence.ParticipantDao;

@Singleton
public class ParticipantDaoJpa extends GenericDaoJpa<Participant, Long>
		implements ParticipantDao {

}
