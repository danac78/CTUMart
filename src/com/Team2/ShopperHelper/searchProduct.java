package com.Team2.ShopperHelper;

import android.widget.TextView;


public class searchProduct {

	String UPC;
	String ProductName;
	String ProductType;
	String StoreID;
	TextView tv = new TextView(null);
	public searchProduct(String StoreID)
	{
		this.StoreID = StoreID;
		
	}
	
}
