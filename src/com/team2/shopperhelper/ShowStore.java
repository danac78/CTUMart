
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

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Dana Haywood
 *
 */
public class ShowStore extends ListActivity {

	
	private String city;
	private String state;
	private String zip;
	private ArrayList<String> idList= new ArrayList<String>();
	private ArrayList<String> storeList=new ArrayList<String>();
	
	/**
	 * 
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		
		/*
		 * Getting the information passed from SearchForStore activity.
		 */
		Bundle bundle = getIntent().getExtras();
		String XMLcity = bundle.getString("city");
		String XMLstate= bundle.getString("state");
		String XMLzip = bundle.getString("zip");
		bundle.clear();
		this.city = XMLcity;
		this.state= XMLstate;
		this.zip = XMLzip;
		/*
		 * setting up the InputStream with the xml stored in resources.
		 */
		InputStream raw = getResources().openRawResource(R.raw.store);
		try {
			QueryXML(raw);
			raw.close();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	protected void onListItemClick(ListView lv, View v, int position, long id)
	{
		super.onListItemClick(lv, v, position, id);
				
		String storeID = idList.get(position);
		Intent intent = new Intent(this,SearchProduct.class);
		Bundle bundle = new Bundle();
		
		bundle.putString("storeID", storeID);
		intent.putExtras(bundle);
		
		
	}
	/*
	 * XPath is not really a Java, but an XML querying method that was designed by W3C.
	 * This method is pulling up the means to do this, and adding only the items to the
	 * array that matches the city, state, and zip.
	 */
				private void QueryXML(InputStream raw) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
					// Calling the items required for XPath reading.
					DocumentBuilder builder;
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					factory.setNamespaceAware(true);
					
					Document doc = null;
					XPathExpression expr=null;
					builder = factory.newDocumentBuilder();
					
					doc = builder.parse(raw);
					
					// create a XPathFactory
					
					XPathFactory xFactory = XPathFactory.newInstance();
					
					//creat an XPath object
					
					XPath xpath = xFactory.newXPath();
					
					// creating an expression
					
					expr=xpath.compile("//store");
					
					// running query
					
					NodeList nodes=(NodeList)expr.evaluate(doc,XPathConstants.NODESET);
					
					/*
					 * Pulling the values from the XML query and placing them into an
					 * array list.
					 */
					for (int i=0; i< nodes.getLength();i++)
					{
						NodeList items = nodes.item(i).getChildNodes();
						String id= items.item(1).getTextContent();
						
						String address = items.item(3).getTextContent();
						String XMLcity = items.item(7).getTextContent();
						String XMLstate = items.item(9).getTextContent();
						String XMLzip = items.item(11).getTextContent();
						
						if ((city.length()>0) && (XMLcity==city))
						{
							storeList.add(address);
							idList.add(id);
						} else if ((state.length()>0) && (XMLstate==state)) 
						{
							storeList.add(address);
							idList.add(id);
						} else if ((zip.length()>0) && (XMLzip==zip))
						{
							storeList.add(address);
							idList.add(id);
						}
					}
					
	
			
				}


}
