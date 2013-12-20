package com.uaijug.certificado.exception;

/**
 * A classe CannotSendEmailException representa um erro ao enviar o email .
 */
public class CannotSendEmailException extends BusinessException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2401874844603065288L;

	/**
	 * Cria uma nova instância de CannotSendEmailException.
	 */
	public CannotSendEmailException() {
		super("Impossível enviar email.");
	}

	/**
	 * Cria uma nova instância de CannotSendEmailException.
	 * 
	 * @param message
	 *            a messagem de erro
	 * @param cause
	 *            a causa do erro
	 */
	public CannotSendEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Cria uma nova instância de CannotSendEmailException.
	 * 
	 * @param message
	 *            a messagem de erro
	 */
	public CannotSendEmailException(String message) {
		super(message);
	}

	/**
	 * Cria uma nova instância de CannotSendEmailException.
	 * 
	 * @param cause
	 *            a causa do erro
	 */
	public CannotSendEmailException(Throwable cause) {
		super(cause);
	}

}
