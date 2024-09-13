package com.jdc.app.support;

public class JdbcException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public JdbcException(String message) {
		super(message);
	}

}
