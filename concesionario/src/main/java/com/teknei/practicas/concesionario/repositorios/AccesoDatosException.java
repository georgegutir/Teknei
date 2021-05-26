package com.teknei.practicas.concesionario.repositorios;

public class AccesoDatosException extends RuntimeException {
	private static final long serialVersionUID = 3613095638345334077L;
	
	public AccesoDatosException() {
		super();
	}
	
	public AccesoDatosException(Long id) {
		super("Could not find marca " + id);
	}

	public AccesoDatosException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccesoDatosException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccesoDatosException(String message) {
		super(message);
	}

	public AccesoDatosException(Throwable cause) {
		super(cause);
	}
}
