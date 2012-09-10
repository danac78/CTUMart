package com.team2.shopperhelper.library;

import java.util.ArrayList;

import com.team2.shopperhelper.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Creating a Custom List with multiple values.
 * 
 * @author Dana Haywood
 * @version 0.9.5
 * @since 9/1/2012 <br>
 *        Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite::
 *        http://geekswithblogs.net/bosuch/archive/2011/01/31/android---
 *        create-a-custom-multi-line-listview-bound-to-an.aspx<br>
 *        Note:<br>
 *        <p>
 *        Typically the ListView for Android will only show one value at a time.
 *        For our purposes, we need it to show the productName, price,
 *        inventoryCount, section, and aisle. In order to accommodate this, we
 *        are building a custom adapter to do so. What will happen is that the
 *        values for the POJO SearchResults will be passed into this class and
 *        it will list the items correctly.
 *        </p>
 * 
 * 
 */

public class CustomBaseAdapter extends BaseAdapter {

	/**
	 * Contains the array passed on from ShowProduct and uses it to format it
	 * correctly.
	 */
	private static ArrayList<SearchResults> searchArrayList;
	/**
	 * Setting up the LayOutinflater in order to insert a TextView layout into a
	 * ListView layout.
	 */
	private LayoutInflater mInflater;

	/**
	 * <p>
	 * Constructs the CustomBaseAdapter for ShowProduct to use. searchArrayList
	 * will be assigned results in order to be manipulated into the List that we
	 * need to occur. mInflater takes the context provided by the procedure call
	 * and places it in. This will allow the mInflater to insert the TextView
	 * layout to be inserted into ListView rows.
	 * </p>
	 * 
	 * @param context
	 *            This is a representation of the application environment. In
	 *            this case, it is taking the environment that ShowProduct has
	 *            displayed and using it to transform the data into that.
	 * @param results
	 *            Once ShowProduct has collected the information, it stores it
	 *            into an ArrayList called results. The results will be
	 *            manipulated into the adapter and will present the list.
	 */
	public CustomBaseAdapter(Context context, ArrayList<SearchResults> results) {
		searchArrayList = results;
		mInflater = LayoutInflater.from(context);
	}

	/**
	 * @return getCount is asking for the size of the searchArrayList. it will
	 *         count the number of entries that is inside of it and return that
	 *         number.
	 */
	public int getCount() {
		return searchArrayList.size();
	}

	/**
	 * @param position
	 *            This is the numerical value that is used to find the position
	 *            of an item. It starts at 0 and continues. This is derived from
	 *            the size of the searchArrayList and it is returning the value
	 *            from a particular position. For example,
	 *            searchArrayList.get(0) might have all the details for Pepsi.
	 * @return When you retrieve items from an ArrayList, you use the index
	 *         number that item is located in. What this is doing is getting the
	 *         value that is in position and returning it to the requester.
	 */
	public Object getItem(int position) {
		return searchArrayList.get(position);
	}

	/**
	 * @param position
	 *            This is the numerical value that is used to find the position
	 *            of an item. It starts at 0 and continues. This is derived from
	 *            the size of the searchArrayList and it is returning the value
	 *            from a particular position. For example,
	 *            searchArrayList.get(0) might have all the details for Pepsi.
	 * @return This is asking for something different then getItem. This is
	 *         retrieving the unique identifier for that particular item. This
	 *         can be used to in the getItem.
	 */

	public long getItemId(int position) {
		return position;
	}

	/**
	 * <p>
	 * getView is responsible for placing the items into TextView layout, and
	 * then inserting that into ListView
	 * </p>
	 * 
	 * @param position
	 *            This value shall be used to get the position of the item
	 *            within searchArrayList
	 * @param convertView
	 *            This is creating the link to the custom_row_product layout,
	 *            and shall be responsible for inserting the appropriate data
	 *            inside of it.
	 * @param parent
	 *            The Parent is the ViewGroup that we shall be inserting
	 *            convertView into (i.e. ListView)
	 * @return This is returning the convertView to the List so it can place
	 *         into the ListView.
	 */

	public View getView(int position, View convertView, ViewGroup parent) {
		/**
		 * pushing the values from the ArrayList into the TextViews we created
		 * in the custom XML layout.
		 */

		ViewHolder holder;
		if (convertView == null) {
			/**
			 * This is setting it up so custom_row_product will be pushed into
			 * the readweb.xml UI.
			 */
			convertView = mInflater.inflate(R.layout.custom_row_product, null);
			/**
			 * Instantiate holder to 1. associate the appropriate field with the
			 * UI as well as getting information into that field.
			 */
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.name);
			holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
			holder.txtInventoryCount = (TextView) convertView
					.findViewById(R.id.inventory);
			holder.txtSection = (TextView) convertView
					.findViewById(R.id.sections);
			holder.txtAisle = (TextView) convertView.findViewById(R.id.aisle);

			convertView.setTag(holder);
			
			/*
			 * This is utilized to associate the name in the user interface (UI) with the 
			 * name in the array list.@CSJ
			 */
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtName.setText(searchArrayList.get(position).getName());
		holder.txtPrice.setText(searchArrayList.get(position).getPrice());
		holder.txtInventoryCount.setText(searchArrayList.get(position)
				.getInventoryCount());
		holder.txtSection.setText(searchArrayList.get(position).getSections());
		holder.txtAisle.setText(searchArrayList.get(position).getAisle());
		
		/*
		 * This is utilized to get and return the values stored in the array list for the 
		 * Name, Price, InventoryCount, Section,and the Aisle so that the user 
		 * can see it in the GUI. @CSJ 
		 */

		return convertView;
	}

	
	/**
	 * 
	 * This class is primarily for creating types for CustomBaseAdapter. The
	 * types included are the ones we need to be listed.
	 * 
	 */
	static class ViewHolder {
		TextView txtName;
		TextView txtPrice;
		TextView txtInventoryCount;
		TextView txtSection;
		TextView txtAisle;
	}
}
