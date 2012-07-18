package com.team2.shopperhelper;

/**
 * @author Dana Haywood
 * @version 0.1.0
 * @IT482
 * @Karl Lloyd
 * @Commented by
 * 
 *            This is a Plain Old Java Object (POJO). It is creating the values
 *            required for search results as well as getter and setters.. Source
 *            Cite: <DO NOT
 *            REMOVE>http://geekswithblogs.net/bosuch/archive/2011/
 *            01/31/android--
 *            -create-a-custom-multi-line-listview-bound-to-an.aspx.
 */
public class SearchResults {

	private String store = "";
	private String address = "";
	private String secondAddress = "";

	/**
	 * @return the store
	 */
	public String getStore() {
		return store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(String store) {
		this.store = store;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the secondAddress
	 */
	public String getSecondAddress() {
		return secondAddress;
	}

	/**
	 * @param secondAddress
	 *            the secondAddress to set
	 */
	public void setSecondAddress(String secondAddress) {
		this.secondAddress = secondAddress;
	}

}
