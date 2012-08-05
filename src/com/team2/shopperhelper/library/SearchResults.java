package com.team2.shopperhelper.library;

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