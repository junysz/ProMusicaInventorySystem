package com.group8.view;

/*
 * Interface listens for events from MaintainPanel (category View)
 * If category button is clicked informs Controller class and
 * take and pass details of category as an object back to Controller
*/
public interface CategoryListener {

	public void categoryAddedPerformed(CategoryFormEvent catFormEvent);

}
