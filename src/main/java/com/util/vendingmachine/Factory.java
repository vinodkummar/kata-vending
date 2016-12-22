package com.util.vendingmachine;

public class Factory

{
	public static VendingMachine createVendingMachine()

	{
		return new Implementation();

	}

}
