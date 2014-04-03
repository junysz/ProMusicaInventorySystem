package com.group8.model;
import java.util.ArrayList;

import java.util.List;

public class TemporaryDataBaseClass {
	
	
	
	
	   
	public List<Item> getMeSomeItems(){
		Item i1= new Item(1, 900.00, "Fender", "MetalXW", 10, 10,true);
		Item i2= new Item(2, 1200.00, "Fender", "Pinki", 1, 1,true);
		Item i3= new Item(3, 400.00, "Fender", "Grany", 3, 3,true);
		Item i4= new Item(4, 200.00, "Fender", "YUPI", 55, 55,true);
		Item i5= new Item(5, 1200.00, "Fender", "XHa", 6, 6,true);
		Item i6= new Item(6, 3300.00, "Fender", "JJJJ", 10, 10,true);
		Item i7= new Item(7, 1.00, "F", "J", 10, 10,true);

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
	
	
	
	public List<String> getMeSomeCategories(){
		String cat1= "Electric guitars"; String cat2= "Concert guitars"; 
		String cat3= "Acoustic Guitars"; String cat4= "Guitar synths"; String cat5= "E"; String cat6= "F";
		
		List<String> cat= new ArrayList<>();
		cat.add(cat6);
		cat.add(cat5);
		cat.add(cat4);
		cat.add(cat3);
		cat.add(cat2);
		cat.add(cat1);
		return cat;
	}
	
	
	public List<String> getMeSomeSubCategories(){
		String cat1= "Electric1"; String cat2= "Electric3"; 
		String cat3= "Electric2"; String cat4= "Electric4"; String cat5= "Electric2"; String cat6= "Electric2";
		
		List<String> cat= new ArrayList<>();
		cat.add(cat6);
		cat.add(cat5);
		cat.add(cat4);
		cat.add(cat3);
		cat.add(cat2);
		cat.add(cat1);
		return cat;
	}

	
}
