package com.team2.shopperhelper;

public class StoreListPojo {
	
	/**
	 * 
	 */
	
	private Integer id;
	private String address;
	private String secondAddress;
	private String city;
	private String state;
	private String zip;
	
	
	public StoreListPojo(Integer id,String address, String secondAddress, String city, String state,String zip)
	{
		this.id=id;
		this.address=address;
		this.city=city;
		this.state=state;
		this.zip=zip;
	}
	
	/**
	 * @return the id
	 */
	
	
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the zip
	 */
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address=address;
	}
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
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

}
