package com.util.vendingmachine;

@SuppressWarnings("serial")
public class NotPaid extends RuntimeException {
	private String message;
	private long remaining;

	public NotPaid(String message, long remaining) {
		this.message = message;
		this.remaining = remaining;
	}

	public long getRemaining() {
		return remaining;
	}

	@Override
	public String getMessage() {
		return message + remaining;
	}

}
