package com.team2.shopperhelper.library;

/**
 * @author Dana Haywood
 * @version 0.1.5
 * @date 7/16/2012
 * @IT482
 * @Karl Lloyd
 * @Commented By:
 * @source cite: Deitel, P., & Deitel, H. (2010). Java: how to program (8th
 *         ed.). Upper Saddle River: Pearson-Prentice Hall.
 *         
 * Dana Comments:
 * 
 * This is a POJO creating the type ProductResults, which will be used to create the
 * format for the String Array.
 */
public class ProductResults {

	/*
	 * Creating a type for the Products for an ArrayList.
	 */
	private String Name = "";
	private String price = "";
	private String inventoryCount = "";
	private String section = "";
	private String aisle = "";

	/**
	 * @return the productName
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setName(String productName) {
		this.Name = productName;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
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
	 * @param inventoryCount
	 *            the inventoryCount to set
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
	 * @param section
	 *            the section to set
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
	 * @param aisle
	 *            the aisle to set
	 */
	public void setAisle(String aisle) {
		this.aisle = aisle;
	}
}
