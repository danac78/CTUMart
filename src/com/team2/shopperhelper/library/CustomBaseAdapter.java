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
 * @author Dana Haywood
 * @7/20/2012
 * @version 0.1.5
 * @Karl Lloyd
 * @IT482
 * @Source Cite: http://geekswithblogs.net/bosuch/archive/2011/01/31/android---
 *         create-a-custom-multi-line-listview-bound-to-an.aspx
 * 
 * 
 */
public class CustomBaseAdapter extends BaseAdapter {
	private static ArrayList<SearchResults> searchArrayList;
	/*
	 * Typically in a List, it will show one value at a time. To make the list a
	 * little more functional, we going to contain a list of the Store ID,
	 * address, and second address if available. When it pulls it up in the
	 * listing, it will display perfectly (although this may be modified prior
	 * to the final version.)
	 */
	private LayoutInflater mInflater;

	public CustomBaseAdapter(Context context, ArrayList<SearchResults> results) {
		searchArrayList = results;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return searchArrayList.size();
	}

	public Object getItem(int position) {
		return searchArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		/*
		 * pushing the values from the ArrayList into the TextViews we created
		 * in the custom XML layout.
		 */

		ViewHolder holder;
		if (convertView == null) {
			/*
			 * This is setting it up so custom_row_product will be pushed into
			 * the readweb.xml UI.
			 */
			convertView = mInflater.inflate(R.layout.custom_row_product, null);
			/*
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
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtName.setText(searchArrayList.get(position).getName());
		holder.txtPrice.setText(searchArrayList.get(position).getPrice());
		holder.txtInventoryCount.setText(searchArrayList.get(position)
				.getInventoryCount());
		holder.txtSection.setText(searchArrayList.get(position).getSections());
		holder.txtAisle.setText(searchArrayList.get(position).getAisle());

		return convertView;
	}
/*
 * Creating the type ViewHolder.
 */
	static class ViewHolder {
		TextView txtName;
		TextView txtPrice;
		TextView txtInventoryCount;
		TextView txtSection;
		TextView txtAisle;
	}
}
