package com.team2.shopperhelper.library;

/**
 * @author Dana Haywood
 * @since 8/4/2012
 * @version 0.5.0
 * <br> Instructor: Karl Lloyd<br>
 *       Class: IT482<br>
 *       University: Colorado Technical University<br>
 *       Source Cite:: Deital How to Program in Java. * This is the creation of
 *       a type that will be involved in creating the ArrayList. When
 *       ArrayList<SearchResults> is used, it will use this type as a format to
 *       retain data.
 * 
 */
public class SearchResults {
	/**
	 * Will hold the product name
	 */
	private String name = "";
	/**
	 * Will hold the price.
	 */
	private String price = "";
	/**
	 * Will hold the inventory count
	 */
	private String inventoryCount = "";
	
	/**
	 * Will hold the Sections
	 */
	private String sections = "";
	/**
	 * Will hold the aisles.
	 */
	private String aisle = "";

	/**
	 * This will help the arraylist add the productname.
	 * 
	 * @return the Product Name so the ArrayList can add it.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setting the Product Name
	 * 
	 * @param name
	 *            This will set the product name for the ArrayList to gather
	 *            later.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getting the Price
	 * 
	 * @return the Price so the ArrayList can add it.
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Setting the price.
	 * 
	 * @param price
	 *            This will set the product name for the ArrayList to gather
	 *            later.
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * Getting Inventory Count.
	 * 
	 * @return the Inventory Count so the ArrayList can add it.
	 */
	public String getInventoryCount() {
		return inventoryCount;
	}

	/**
	 * Setting the Inventory Count
	 * 
	 * @param inventoryCount
	 *            This will set the Inventory Count for the ArrayList to gather
	 *            later.
	 */
	public void setInventoryCount(String inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	/**
	 * Getting the Section
	 * 
	 * @return the Section so the ArrayList can add it.
	 */
	public String getSections() {
		return sections;
	}

	/**
	 * Setting the section
	 * 
	 * @param sections
	 *            This will set the section for the ArrayList to gather later.
	 */
	public void setSections(String sections) {
		this.sections = sections;
	}

	/**
	 * Getting the Aisle.
	 * 
	 * @return the Aisle so the ArrayList can add it.
	 */
	public String getAisle() {
		return aisle;
	}

	/**
	 * Setting the Aisle.
	 * 
	 * @param aisle
	 *            This will set the Aisle for the ArrayList to gather later.
	 */
	public void setAisle(String aisle) {
		this.aisle = aisle;
	}

}