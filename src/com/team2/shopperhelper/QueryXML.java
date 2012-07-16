package com.team2.shopperhelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class QueryXML {
	
	private String city;
	private String state;
	private String zip;

	public ArrayList<XMLPojo> query(InputStream is) throws ParserConfigurationException,SAXException,IOException,XPathExpressionException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		ArrayList<XMLPojo> storeList = new ArrayList<XMLPojo>();
		
		DocumentBuilder builder;
		Document doc = null;
		XPathExpression expr = null;
		builder = factory.newDocumentBuilder();
		doc = builder.parse(is);
		
		// create a XPathFactory
		XPathFactory xFactory = XPathFactory.newInstance();
		
		// Create an XPath object
		
		XPath xpath = xFactory.newXPath();
		
		// create an expression
		
		expr = xpath.compile("//store");
		
		// Running the query and getting the results
		
		NodeList nodes = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
		
		for (int i=0; i< nodes.getLength();i++)
		{
			NodeList items = nodes.item(i).getChildNodes();
			String id = items.item(1).getTextContent();
			String address = items.item(3).getTextContent();
			String secondAddress = items.item(5).getTextContent();
			String XMLcity = items.item(7).getTextContent();
			String XMLstate = items.item(9).getTextContent();
			String XMLzip = items.item(11).getTextContent();
			XMLPojo xml = new XMLPojo(id, address, secondAddress, city, state, zip);
			if ((city!=null) && (XMLcity==city))
			{
				storeList.add(xml);
			} else if ((state!=null) && (XMLstate==state)) 
			{
				storeList.add(xml);
			} else if ((zip!=null) && (XMLzip==zip))
			{
				storeList.add(xml);
			}
		}
		
		
		return storeList;
	}

	public QueryXML(String city, String state, String zip) {
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
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
