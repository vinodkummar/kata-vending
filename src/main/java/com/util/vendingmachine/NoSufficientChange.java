package com.util.vendingmachine;

@SuppressWarnings("serial")
public class NoSufficientChange extends RuntimeException {

	private String message;

	public NoSufficientChange(String string) {
		this.message = string;
	}

	@Override
	public String getMessage() {
		return message;

	}
}
