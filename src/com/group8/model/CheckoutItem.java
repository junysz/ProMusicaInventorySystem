package com.group8.model;

public class CheckoutItem {
	String  brand, model;
	double price;
	double totalprice;
	int id, quantity;
	public CheckoutItem(int id, String brand, String model, double price, int quantity){
			this.id=id;
			this.brand=brand;
			this.model=model;
			this.price=price;
			this.quantity=quantity;
			totalprice = price*((double) quantity);
		
	}
	public String getItemID() {
		// TODO Auto-generated method stub
		return Integer.toString(id);
	}
	public String getBrand() {
		// TODO Auto-generated method stub
		return brand;
	}
	public String getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	public String getQuantity() {
		// TODO Auto-generated method stub
		return Integer.toString(quantity);
	}
	public String getTotalPrice() {
		// TODO Auto-generated method stub
		return Double.toString(totalprice);
	}
	public String getPrice() {
		// TODO Auto-generated method stub
		return Double.toString(price);
	}

}
