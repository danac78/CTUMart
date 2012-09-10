package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import com.team2.shopperhelper.library.DialogBox;

/**
 * Activity to check Internet and Compatibility issues prior to proceeding.
 * 
 * @author Dana Haywood
 * @version 1.0.0
 * @since 9/10/2012<br>
 * 
 *        Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite: http://developer.android.com/guide/components/index.html<br>
 *        Commented by:<br>
 * 
 *        <p>
 *        This class is the initial activity that is launched when the icon is
 *        tapped on. It will direct itself into Search for Store, but it needs
 *        to check to ensure if that the Android OS is compatible (in case of
 *        Android OS corruption) and that it is connected to the Internet. If it
 *        loads with these two conditions being false, it will display a message
 *        alerting the user. Once they click on the close button, the
 *        application will close. If both items are true, the user will just see
 *        Search Store appear. In the simulations ran, this actually only takes
 *        one second and the customer will not * notice these tests being
 *        conducted at all.
 *        </p>
 */

public class Launch extends Activity {

	/**
	 * This is gathering the API level the device is using. For example, Android
	 * 2.2. would be API 8. The minimum will be 4. The purpose for parsing it is
	 * that below Android 2.x, they did not have the Integer version.
	 * Technically, they could bypass this check if I did it that way. Although
	 * the support library does add this for 1.6, it will allow 1.5 and lesser
	 * to bypass.
	 */
	private ConnectivityManager connectivity;
	/**
	 * This is invoking the Connectivity Manager, which is part of the Android
	 * API. it is how we are going learn if the Internet is on or off. It will use
	 * the boolean to determine if connection is available.
	 */
	private NetworkInfo mobileInfo;
	/**
	 * This will hold the information pertaining to Wifi connection. It will use
	 * the boolean to determine if connection is available.
	 */
	private NetworkInfo wiFiInfo;
	/**
	 * This is declaring the next Activity that will be opened if the two checks
	 * passes. 
	 */
	private Intent intent;

	/**
	 * Creating a dialog box for this activity.
	 */
	private DialogBox dialog;
	/**
	 * <p>
	 * The onCreate method is basically running to launch the application. The
	 * sole purpose is to ensure that the application is compatible and has
	 * Internet. *
	 * 
	 * It is going to run through an if/else statement for validation. If either
	 * the Internet is not operating or the API level is below 4, it will
	 * trigger a dialog box with the respective message.
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch);
		dialog = new DialogBox();
		connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		wiFiInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		mobileInfo = connectivity
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		intent = new Intent(this, SearchForStore.class);

		/**
		 * The editor.clear() is merely for testing purposes. At this point, we
		 * intend to keep previous searches stored in memory. Adding mapReturn
		 * from for Show Product. It will keep the setting stored for future
		 * use, but this is to prevent a crash as a result of this setting not
		 * being there.
		 */

		if ((!mobileInfo.isConnected()) && (!wiFiInfo.isConnected())) {

			dialog.postDialog(Launch.this, "Error", R.string.internetError);
			
		/*
		 * This statement is used to verify that an Internet connection is available 
		 * by invoking the connectivity manager that is part of the API. This will be 
		 * a boolean value, either a connection is available or it isn't. If a connection
		 * is not available an dialogue box will be displayed that states "Error". @CSJ
		 */

		} else if (Integer.parseInt(Build.VERSION.SDK) < 4) {

			dialog.postDialog(Launch.this, "Error", R.string.compatibleError);
			
		/*
		 * If an Internet connection is available then this else if statement confirms that
		 * the build version is less than 4. If this is not a true statement then once again the 
		 * dialogue box will be displayed that states "Error". @CSJ
		 */
			
		} else {
			startActivity(intent);
			finish();
			
		/*
		 * If both of the preceding test are true, the Internet connection is available and
		 * the build version is less than 4 then the SearchForStore.class will be initiated @CSJ
		 */
					
		}

	}

}
