package com.team2.shopperhelper;

/**
 * @author Dana Haywood
 *
 */
public class XMLPojo {
	private String id;
	private String address;
	private String secondAddress;
	private String city;
	private String state;
	private String zip;
	
	public XMLPojo(String id, String address)
	{
		this.setId(id);
		this.setAddress(address);
		this.setSecondAddress(secondAddress);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
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
	 * @param secondAddress the secondAddress to set
	 */
	public void setSecondAddress(String secondAddress) {
		this.secondAddress = secondAddress;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

}
