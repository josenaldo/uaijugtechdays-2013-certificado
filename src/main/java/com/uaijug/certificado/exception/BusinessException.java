package com.uaijug.certificado.exception;

/**
 * A classe BusinessException representa uma exceção de negócio do sistema.
 */
public class BusinessException extends Exception {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 9202643849994458269L;

	/**
	 * Cria uma nova instância de BusinessException.
	 */
	public BusinessException() {
		super("Um erro ocorreu nessa operação");
	}

	/**
	 * Cria uma nova instância de BusinessException.
	 * 
	 * @param message
	 *            a messagem de erro
	 * @param cause
	 *            a causa do erro
	 */
	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Cria uma nova instância de BusinessException.
	 * 
	 * @param message
	 *            a messagem de erro
	 */
	public BusinessException(final String message) {
		super(message);
	}

	/**
	 * Cria uma nova instância de BusinessException.
	 * 
	 * @param cause
	 *            a causa do erro
	 */
	public BusinessException(final Throwable cause) {
		super(cause);
	}

}
