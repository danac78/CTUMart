package com.team2.shopperhelper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowSection extends Activity {

	private static final String PREF_NAME = "shopPref";
	int section;
	private int drawable;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showsection);

		SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
		ImageView image = (ImageView) findViewById(R.id.sectionView);
		section = settings.getInt("section", 0);

		image.setImageResource(getDrawable(section));

	}

	public int getDrawable(Integer section) {

		switch (section) {
		case 1:
			drawable = R.drawable.ctumart_section_books;
			break;
		case 2:
			drawable = R.drawable.ctumart_section_hb;
			break;
		case 3:
			drawable = R.drawable.ctumart_section_grocery;
			break;
		case 4:
			drawable = R.drawable.ctumart_section_cards;
		case 5:
			drawable = R.drawable.ctumart_section_office;
			break;
		case 6:
			drawable = R.drawable.ctumart_section_toys;
			break;
		case 7:
			drawable = R.drawable.ctumart_section_electronics;
			break;
		case 8:
			drawable = R.drawable.ctumart_section_hardware;
			break;
		case 9:
			drawable = R.drawable.ctumart_section_cleaning;
			break;
		case 10:
			drawable = R.drawable.ctumart_section_sports;
			break;
		case 11:
			drawable = R.drawable.ctumart_section_shoes;
			break;
		case 12:
			drawable = R.drawable.ctumart_section_girls;
			break;
		case 13:
			drawable = R.drawable.ctumart_section_boys;
			break;
		case 14:
			drawable = R.drawable.ctumart_section_men;
			break;
		case 15:
			drawable = R.drawable.ctumart_section_women;
			break;
		}
		return drawable;
	}

	public void setDrawable(int drawable) {
		this.drawable = drawable;
	}

}
