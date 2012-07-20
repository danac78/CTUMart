package com.team2.shopperhelper;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProductCustomBaseAdapter extends BaseAdapter {
	/*
	 * Typically in a List, it will show one value at a time. To make the list a
	 * little more functional, we going to contain a list of the Store ID,
	 * address, and second address if available. When it pulls it up in the
	 * listing, it will display perfectly (although this may be modified prior
	 * to the final version.)
	 */
	private static ArrayList<ProductResults> searchArrayList;
	private LayoutInflater mInflater;

	static class ViewHolder {
/*
 * This is a type that will be used later on to declare where the items are going.
 */
		TextView productNameTXT;
		TextView priceTXT;
		TextView inventoryCountTXT;
		TextView sectionTXT;
		TextView aisleTXT;
	}

	public ProductCustomBaseAdapter(Context context,
			ArrayList<ProductResults> results) {
		setSearchArrayList(results);
		setmInflater(mInflater);

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
/*
 */
	public View getView(int position, View convertView, ViewGroup parent) {
		/*
		 * pushing the values from the ArrayList into the TextViews we created in
		 * the custom XML layout.
		 */
		ViewHolder holder = new ViewHolder();

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_row_product, null);
			holder.productNameTXT = (TextView) convertView
					.findViewById(R.id.productname);
			holder.priceTXT = (TextView) convertView
					.findViewById(R.id.priceTXT);
			holder.inventoryCountTXT = (TextView) convertView
					.findViewById(R.id.inventoryCountTXT);
			holder.sectionTXT = (TextView) convertView
					.findViewById(R.id.sectionTXT);
			holder.aisleTXT = (TextView) convertView
					.findViewById(R.id.aisleTXT);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.productNameTXT.setText(searchArrayList.get(position)
				.getProductName());
		holder.priceTXT.setText(searchArrayList.get(position).getPrice());
		holder.inventoryCountTXT.setText(searchArrayList.get(position)
				.getInventoryCount());
		holder.sectionTXT.setText(searchArrayList.get(position).getSection());
		holder.aisleTXT.setText(searchArrayList.get(position).getAisle());

		return convertView;
	}

	/**
	 * @return the searchArrayList
	 */
	public static ArrayList<ProductResults> getSearchArrayList() {
		return searchArrayList;
	}

	/**
	 * @param searchArrayList
	 *            the searchArrayList to set
	 */
	public static void setSearchArrayList(
			ArrayList<ProductResults> searchArrayList) {
		ProductCustomBaseAdapter.searchArrayList = searchArrayList;
	}

	/**
	 * @return the mInflater
	 */
	public LayoutInflater getmInflater() {
		return mInflater;
	}

	/**
	 * @param mInflater
	 *            the mInflater to set
	 */
	public void setmInflater(LayoutInflater mInflater) {
		this.mInflater = mInflater;
	}
}