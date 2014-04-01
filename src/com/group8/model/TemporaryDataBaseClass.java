package com.group8.model;
import java.util.ArrayList;

import java.util.List;

public class TemporaryDataBaseClass {
	
	
	
	
	
	public List<Item> getMeSomeItems(){
		Item i1= new Item(1, 900.00, "Fender", "MetalXW", 10, 10);
		Item i2= new Item(2, 1200.00, "Fender", "Pinki", 1, 1);
		Item i3= new Item(3, 400.00, "Fender", "Grany", 3, 3);
		Item i4= new Item(4, 200.00, "Fender", "YUPI", 55, 55);
		Item i5= new Item(5, 1200.00, "Fender", "XHa", 6, 6);
		Item i6= new Item(6, 3300.00, "Fender", "JJJJ", 10, 10);
		Item i7= new Item(7, 1.00, "F", "J", 10, 10);

		List<Item> temItemList= new ArrayList<Item>();
		 temItemList.add(i1);
		 temItemList.add(i2);
		 temItemList.add(i3);
		 temItemList.add(i4);
		 temItemList.add(i5);
		 temItemList.add(i6);
		 temItemList.add(i7);
	
		 return temItemList;	
	}

	
}
