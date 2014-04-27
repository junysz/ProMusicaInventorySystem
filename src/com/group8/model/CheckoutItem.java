package com.group8.model;

public class CheckoutItem {
	private String  brand, model;
	private double price;
	private double totalprice;
	private int id, quantity;

	public CheckoutItem(String brand, String model, double price, int quantity){
		this.brand=brand;
		this.model=model;
		this.price=price;
		this.quantity=quantity;
		totalprice = price*((double) quantity);

	}
	public String getItemID() {
		return Integer.toString(id);
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public String getQuantity() {
		return Integer.toString(quantity);
	}
	public String getTotalPrice() {
		return Double.toString(totalprice);
	}
	public String getPrice() {
		return Double.toString(price);
	}

}
