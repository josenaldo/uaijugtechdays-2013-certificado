package com.uaijug.certificado.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationTest {

	private String key = "key";
	private String value = "value";

	@Test(expected = NullPointerException.class)
	public void testConstructorNullKey() {
		new Configuration(null, this.value);
		fail("Uma NullPointerException deveria ter sido lançada, pois o construtor não pode permitir uma key null");
	}

	@Test(expected = NullPointerException.class)
	public void testConstructorNullValue() {
		new Configuration(this.key, null);
		fail("Uma NullPointerException deveria ter sido lançada, pois o construtor não pode permitir um value null");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmptyKey() {
		new Configuration("", this.value);
		fail("Uma IllegalArgumentException  deveria ter sido lançada, pois o construtor não pode permitir uma key vazia");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorEmptyValue() {
		new Configuration(this.key, "");
		fail("Uma IllegalArgumentException deveria ter sido lançada, pois o construtor não pode permitir um value vazia");
	}

	@Test
	public void testConstructor() {
		Configuration configuration = new Configuration(this.key, this.value);
		assertThat("A chave deveria ser key", configuration.getKey(),
				is(this.key));
		assertThat("O valor deveria ser key", configuration.getValue(),
				is(this.value));
	}
}
