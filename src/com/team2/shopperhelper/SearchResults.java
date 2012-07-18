package com.team2.shopperhelper;

public class SearchResults {
	/*
	 * This is basically a pojo.
	 * 
	 * Source Cite:
	 * http://geekswithblogs.net/bosuch/archive/2011/01/31/android--
	 * -create-a-custom-multi-line-listview-bound-to-an.aspx
	 */
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
