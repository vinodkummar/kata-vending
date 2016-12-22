package com.util.vendingmachine;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("hiding")
public class Inventory<Item> {
	private Map<Item, Integer> inventory = new HashMap<Item, Integer>();

	public int getQuantity(Item item) {
		Integer value = inventory.get(item);
		return value == null ? 0 : value;
	}

	public void add(Item item) {
		int count = inventory.get(item);
		inventory.put(item, count + 1);
	}

	public void deduct(Item item) {
		if (hasItem(item)) {
			int count = inventory.get(item);
			inventory.put(item, count - 1);
		}
	}

	public boolean hasItem(Item item) {
		return getQuantity(item) > 0;
	}

	public void clear() {
		inventory.clear();
	}

	public void put(Item item, int quantity) {
		inventory.put(item, quantity);
	}
}
