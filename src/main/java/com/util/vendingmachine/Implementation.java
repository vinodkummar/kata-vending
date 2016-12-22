package com.util.vendingmachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Implementation implements VendingMachine {
	private Inventory<Coin> cashInventory = new Inventory<Coin>();
	private Inventory<Item> itemInventory = new Inventory<Item>();
	private long totalSales;
	private Item currentItem;
	private long currentBalance;

	public Implementation() {
		initialize();
	}

	private void initialize() {

		for (Coin c : Coin.values()) {
			cashInventory.put(c, 5);
		}

		for (Item i : Item.values()) {
			itemInventory.put(i, 5);
		}

	}

	public long selectItemAndGetPrice(Item item) {
		if (itemInventory.hasItem(item)) {
			currentItem = item;
			return currentItem.getPrice();
		}
		throw new SoldOut("Sold Out, Please select another item");
	}

	public void insertCoin(Coin coin) {
		currentBalance = currentBalance + coin.getDenomination();
		cashInventory.add(coin);
	}

	public Bucket<Item, List<Coin>> collectItemAndChange() {
		Item item = collectItem();
		totalSales = totalSales + currentItem.getPrice();

		List<Coin> change = collectChange();

		return new Bucket<Item, List<Coin>>(item, change);
	}

	private Item collectItem() throws NoSufficientChange, NotPaid {
		if (isFullPaid()) {
			if (hasSufficientChange()) {
				itemInventory.deduct(currentItem);
				return currentItem;
			}
			throw new NoSufficientChange("Not Sufficient change");

		}
		long remainingBalance = currentItem.getPrice() - currentBalance;
		throw new NotPaid("Price not full paid, remaining : ", remainingBalance);
	}

	private List<Coin> collectChange() {
		long changeAmount = currentBalance - currentItem.getPrice();
		List<Coin> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentItem = null;
		return change;
	}

	public List<Coin> refund() {
		List<Coin> refund = getChange(currentBalance);
		updateCashInventory(refund);
		currentBalance = 0;
		currentItem = null;
		return refund;
	}

	private boolean isFullPaid() {
		if (currentBalance >= currentItem.getPrice()) {
			return true;
		}
		return false;
	}

	private List<Coin> getChange(long amount) throws NoSufficientChange {
		List<Coin> changes = Collections.EMPTY_LIST;

		if (amount > 0) {
			changes = new ArrayList<Coin>();
			long balance = amount;
			while (balance > 0) {
				if (balance >= Coin.QUARTER.getDenomination()
						&& cashInventory.hasItem(Coin.QUARTER)) {
					changes.add(Coin.QUARTER);
					balance = balance - Coin.QUARTER.getDenomination();
					continue;

				} else if (balance >= Coin.DIME.getDenomination()
						&& cashInventory.hasItem(Coin.DIME)) {
					changes.add(Coin.DIME);
					balance = balance - Coin.DIME.getDenomination();
					continue;

				} else if (balance >= Coin.NICKLE.getDenomination()
						&& cashInventory.hasItem(Coin.NICKLE)) {
					changes.add(Coin.NICKLE);
					balance = balance - Coin.NICKLE.getDenomination();
					continue;

				} else if (balance >= Coin.PENNY.getDenomination()
						&& cashInventory.hasItem(Coin.PENNY)) {
					changes.add(Coin.PENNY);
					balance = balance - Coin.PENNY.getDenomination();
					continue;

				} else {
					throw new NoSufficientChange(
							"NoSufficientChange,Please select another product");
				}
			}
		}

		return changes;
	}

	public void reset() {
		cashInventory.clear();
		itemInventory.clear();
		totalSales = 0;
		currentItem = null;
		currentBalance = 0;
	}

	public void printStats() {
		System.out.println("Total Sales : " + totalSales);
		System.out.println("Current Item Inventory : " + itemInventory);
		System.out.println("Current Cash Inventory : " + cashInventory);
	}

	private boolean hasSufficientChange() {
		return hasSufficientChangeForAmount(currentBalance
				- currentItem.getPrice());
	}

	private boolean hasSufficientChangeForAmount(long amount) {
		boolean hasChange = true;
		try {
			getChange(amount);
		} catch (NoSufficientChange nsce) {
			return hasChange = false;
		}

		return hasChange;
	}

	private void updateCashInventory(List change) {
		for (Coin c : change) {
			cashInventory.deduct(c);
		}
	}

	public long getTotalSales() {
		return totalSales;
	}

}
