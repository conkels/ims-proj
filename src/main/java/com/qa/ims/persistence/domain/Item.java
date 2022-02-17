package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private long item_id;
	private String name;
	private double price;
	
	public Item(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Item(long item_id, String name, double price) {
		super();
		this.item_id = item_id;
		this.name = name;
		this.price = price;
	}

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", name=" + name + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(item_id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return item_id == other.item_id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}
	
}
