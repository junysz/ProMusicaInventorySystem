package com.group8.model;

import java.util.List;

public class MainModel {

		TemporaryDataBaseClass someItems;
		
		
		public MainModel(){
			
			someItems=new TemporaryDataBaseClass();
			
			
		}
		
		
		
		
		
		
		public List<Item> getMeSomeItems(){
			return someItems.getMeSomeItems();
		}
		public List<String>getMySomeCategories(){
			return someItems.getMeSomeCategories();
		}
}
