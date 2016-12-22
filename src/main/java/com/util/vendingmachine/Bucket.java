package com.util.vendingmachine;

public class Bucket<A1, A2> {
	private A1 first;
	private A2 second;

	public Bucket(A1 first, A2 second) {
		this.first = first;
		this.second = second;
	}

	public A1 getFirst() {
		return first;
	}

	public A2 getSecond() {
		return second;
	}

}
