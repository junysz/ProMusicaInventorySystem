package com.group8.model;

public class CheckoutItem {
	private String  brand, model;
	private double price;
	private int  quantity;
	private double totalprice;

	public CheckoutItem(String brand, String model, double price, int quantity){
		this.brand=brand;
		this.model=model;
		this.price=price;
		this.quantity=quantity;
		totalprice = price*((double) quantity);

	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public int getQuantity() {
		return (quantity);
	}
	public double getTotalPrice() {
		return (totalprice);
	}
	public double getPrice() {
		return (price);
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setTotalPrice(double totalPrice)
	{
		this.totalprice=totalPrice;
	}
	
}
