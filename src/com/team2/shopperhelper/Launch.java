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
 * @author Dana Haywood
 * @version 0.5.2
 * @since 7/10/2012<br>
 * 
 *        Instructor: Karl Lloyd<br>
 *        Class: IT482<br>
 *        University: Colorado Technical University<br>
 *        Source Cite: http://developer.android.com/guide/components/index.html<br>
 *        Commented by:<br>
 * 
 * 
 *        <p>
 *        This class is the initial activity that is launched when the icon is
 *        tapped on. It will direct itself into Search for Store, but it needs
 *        to check to ensure if that the Android OS is compatible (in case of
 *        Android OS corruption) and that it is connected to the Internet. If it
 *        loads with these two conditions being false, it will display a message
 *        alerting the user. Once they click on the ok button, the application
 *        will close. If both items are true, the user will just see Search
 *        Store appear. In the simulations ran, this actually only takes one
 *        second and the customer will not * notice these tests being conducted
 *        at all.
 *        </p>
 */

public class Launch extends Activity {

	/**
	 * <p>
	 * The onCreate method is basically running to launch the application. The
	 * sole purpose is to ensure that the application is compatible and has
	 * Internet. The following processes occur:
	 * <ol>
	 * <li><b>connectivity</b>: This is invoking the Connectivity Manager, which
	 * is part of the Android API. it is how we are going learn if the internet
	 * is on or off.</li>
	 * <li><b>wiFiInfo</b>: This will hold the information pertaining to Wifi
	 * connection.</li>
	 * <li><b>mobileInfo</b>This will hold information pertaining to Mobile
	 * connections.</li>
	 * <li><b>mobileIsConnect</b>: Checks to see if the Mobile connection is
	 * connected. It is saved to a string because had a tough time getting
	 * Android to like booleans.</li>
	 * <li><b>wifiIsConnect</b>: Similar to mobileIsConnect, but for the Wifi.</li>
	 * <li><b>checkVersion</b>: This is gathering the API level the device is
	 * using. For example, Android 2.2. would be API 8 The purpose for parsing
	 * it is that below Android 2.x, they did not have the Integer version.
	 * Technically, they could bypass this check if I did it that way.</li>
	 * <li><b>intent:</b> This is declaring the next Activity that will be
	 * opened if the two checks passes.</li>
	 * 
	 * </ol>
	 * 
	 * <p>
	 * With these attributes created, it is going to run through an if/else
	 * statement for validation. If either the Internet is not operating or the
	 * API level is below 8, it will trigger a dialog box with the respective
	 * message.
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wiFiInfo = connectivity
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mobileInfo = connectivity
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		String mobileIsConnect = Boolean.toString(mobileInfo.isConnected());
		String wifiIsConnect = Boolean.toString(wiFiInfo.isConnected());
		int checkVersion = Integer.parseInt(Build.VERSION.SDK);
		Intent intent = new Intent(this, SearchForStore.class);

		if ((mobileIsConnect.contentEquals("false"))
				&& (wifiIsConnect.contentEquals("false"))) {

			dialogCreate(R.string.internetError);

		} else if (checkVersion < 8) {

			dialogCreate(R.string.compatibleError);
		} else {
			startActivity(intent);
			finish();
		}

	}

	/**
	 * This method is meant to create a dialog box with the respective message.
	 * 
	 * @param errorMsg
	 *            Each check has R.string assigned to it, and it is passing the
	 *            value onto a single method. From here, it will call DialogBox
	 *            and have the Dialog Box pop up.
	 */
	public void dialogCreate(int errorMsg) {

		DialogBox dialog = new DialogBox();

		dialog.postDialog(Launch.this, "Error", errorMsg);

	}

}
