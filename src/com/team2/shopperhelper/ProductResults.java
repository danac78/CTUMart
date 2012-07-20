package com.team2.shopperhelper;

public class ProductResults {
/*
 * Creating a type for the Products for an ArrayList.
 */
	private String productName = "";
	private String price ="";
	private String inventoryCount="";
	private String section="";
	private String aisle="";
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the inventoryCount
	 */
	public String getInventoryCount() {
		return inventoryCount;
	}
	/**
	 * @param inventoryCount the inventoryCount to set
	 */
	public void setInventoryCount(String inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/**
	 * @return the aisle
	 */
	public String getAisle() {
		return aisle;
	}
	/**
	 * @param aisle the aisle to set
	 */
	public void setAisle(String aisle) {
		this.aisle = aisle;
	}
}
