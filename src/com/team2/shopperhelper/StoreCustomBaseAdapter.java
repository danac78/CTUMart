package com.team2.shopperhelper;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



/**
 * @author Dana Haywood
 * @version 0.1.0
 * @IT482
 * @Karl Lloyd
 * @Commented by
 * 
 *            This is creating a custom adapter for the Show Product in order to
 *            provide it with the tools needed to read the information and place
 *            it into an arraylist. An adapter converts an ArrayList into
 *            something the ListView will understand. OTHERWISE, it will not
 *            post right..
 * 
 * @Source cite<DO NOT
 *         REMOVE>:http://geekswithblogs.net/bosuch/archive/2011/01/31
 *         /android---create-a-custom-multi-line-listview-bound-to-an.aspx.
 */

public class StoreCustomBaseAdapter extends BaseAdapter {
/*
 * http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx
 */
	static class ViewHolder {

		TextView storeTXT;
		TextView addressTXT;
		TextView secondAddressTXT;
		

	}




	/*
	 * This method begins the construction of the ArrayList<SearchResults>
	 */
	private static ArrayList<StoreResults> searchArrayList;

	private LayoutInflater mInflater;

	

	public StoreCustomBaseAdapter(Context context,
			ArrayList<StoreResults> results) {
		searchArrayList = results;
		/*
		 * The Views are constructed via XML files in the Layout folder. What
		 * LayoutInflater does instantiate an XML layout file into a specific
		 * view. We are using this to push the Custom Rows XML into the
		 * readDB.xml
		 */

		mInflater = LayoutInflater.from(context);
	}
	/**
	 * @return the searchArrayList
	 */
	public static ArrayList<StoreResults> getSearchArrayList() {
		return searchArrayList;
	}

	/**
	 * @param searchArrayList the searchArrayList to set
	 */

	

	public static void setSearchArrayList(
			ArrayList<StoreResults> searchArrayList) {

		StoreCustomBaseAdapter.searchArrayList = searchArrayList;
		
	}


	public int getCount() {
		// TODO Auto-generated method stub
		return searchArrayList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return searchArrayList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView==null)
		{
			convertView=mInflater.inflate(R.layout.custom_row_view, null);
			holder = new ViewHolder();
			holder.storeTXT = (TextView) convertView.findViewById(R.id.Store);
			holder.addressTXT=(TextView) convertView.findViewById(R.id.address);
			holder.secondAddressTXT = (TextView) convertView.findViewById(R.id.secondAddress);
			
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.storeTXT.setText(searchArrayList.get(position).getStore());
		holder.addressTXT.setText(searchArrayList.get(position).getAddress());
		holder.secondAddressTXT.setText(searchArrayList.get(position).getSecondAddress());
		
		return convertView;		
		
	}
}
