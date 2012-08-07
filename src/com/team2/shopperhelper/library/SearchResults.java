package com.team2.shopperhelper.library;


/**
 * @author Dana Haywood
 * @date 8/4/2012
 * @version 0.5.0
 * @source cite: Deital How to Program in Java.
 * @Karl Lloyd
 * @IT482
 * 
 * This is the creation of a type that will be involved in creating the ArrayList. When
 * ArrayList<SearchResults> is used, it will use this type as a format to retain data.
 *
 */
public class SearchResults {
	private String name = "";
	private String price = "";
	private String inventoryCount = "";
	private String sections="";
	private String aisle="";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getInventoryCount() {
		return inventoryCount;
	}
	public void setInventoryCount(String inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	public String getSections() {
		return sections;
	}
	public void setSections(String sections) {
		this.sections = sections;
	}
	public String getAisle() {
		return aisle;
	}
	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

}