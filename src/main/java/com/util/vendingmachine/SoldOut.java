package com.util.vendingmachine;

@SuppressWarnings("serial")
public class SoldOut extends RuntimeException {
	private String message;

	public SoldOut(String string) {
		this.message = string;
	}

	@Override
	public String getMessage() {
		return message;

	}
}
