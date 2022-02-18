package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Order {

	private Long orderId;
	private Long custId;
	private Long itemId;
	private Long qty;
	private Double cost;
	
	public Order(Long custId, Long itemId, Long qty) {
		super();
		this.custId = custId;
		this.itemId = itemId;
		this.qty = qty;
	}
	
	public Order(Long orderId, Long custId, Long itemId, Long qty, Double cost) {
		super();
		this.orderId = orderId;
		this.custId = custId;
		this.itemId = itemId;
		this.qty = qty;
		this.cost = cost;
	}
	
	public Order(Long orderId, Long custId, Long itemId, Long qty) {
		this.orderId = orderId;
		this.custId = custId;
		this.itemId = itemId;
		this.qty = qty;
	}

// create a new constructor to implement addItem method
	public Order(Long orderId, Long itemId) {
		this.orderId = orderId;
		this.itemId = itemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", custId=" + custId + ", itemId=" + itemId + ", qty=" + qty + ", cost="
				+ cost + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, custId, itemId, orderId, qty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(cost, other.cost) && Objects.equals(custId, other.custId)
				&& Objects.equals(itemId, other.itemId) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(qty, other.qty);
	}
	
	
	
}
