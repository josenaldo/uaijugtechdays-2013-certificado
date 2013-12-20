package com.uaijug.certificado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Preconditions;

@Entity
public class Configuration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String key;

	@NotBlank
	private String value;

	public Configuration(String key, String value) {
		Preconditions.checkNotNull(key,
				"A chave da configuração não pode ser nula");
		Preconditions.checkArgument(!key.isEmpty(),
				"A chave da configuração não pode ser nula");

		Preconditions.checkNotNull(value,
				"O valor da configuração não pode ser nulo");
		Preconditions.checkArgument(!value.isEmpty(),
				"A o valor da configuração não pode ser nulo");

		this.key = key;
		this.value = value;
	}

	@SuppressWarnings("unused")
	private Configuration() {

	}

	public Long getId() {
		return this.id;
	}

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.key == null) ? 0 : this.key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		if (this.key == null) {
			if (other.key != null)
				return false;
		} else if (!this.key.equals(other.key))
			return false;
		return true;
	}

}
