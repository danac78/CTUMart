/**
 * 
 */
package com.team2.shopperhelper;

/**
 * @author Dana Haywood
 * @version 1.0
 * @category Helper Class
 * @date 6/29/2012
 * @IT482-1203C
 * @Instructor Karl Lloyd
 * 
 *  This is creating an object that will be used by the XML parser to file specific 
 *  information pertaining to the store.xml. As each of these has to be put into an
 *  array, this class helps compile specific information as an "entry".
 */

public class Entry {
	public final String storeID;
	public final String address;
	public final String secondAddress;
	public final String city;
	public final String state;
	public final String zip;
	
	Entry(String storeID, String address, String secondAddress,
			String city, String state, String zip) {
		this.storeID = storeID;
		this.address = address;
		this.secondAddress = secondAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	
}
