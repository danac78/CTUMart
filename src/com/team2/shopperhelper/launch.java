package com.team2.shopperhelper;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Dana Haywood
 * @date 7/10/2012
 * @IT482
 * @Karl Lloyd
 * @Version 1.0
 * 
 *          This class is the initial activity that is launched when the icon is
 *          tapped on. It will direct itself into Search for Store, but it needs
 *          to check to ensure if that the Android OS is compatible (in case of
 *          Android OS corruption) and that it is connected to the Internet. If
 *          it loads with these two conditions being false, it will display a
 *          message alerting the user. Once they click on the ok button, the
 *          application will close. If both items are true, the user will just
 *          see Search Store appear. In the simulations ran, this actually only
 *          takes one second and the customer will not notice these tests being
 *          conducted at all.
 * 
 */
public class launch extends Activity {
	/*
	 * Declaring the variables required for this activity. These will be
	 * explained when we bring them to build objects.
	 */
	ConnectivityManager connectivity;
	NetworkInfo wifiInfo, mobileInfo;
	private String version;

	private int versionCheck;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		/*
		 * Building the objects
		 * 
		 * connectivity will be the primary object that associated with network
		 * connectivity for the Android
		 * 
		 * wifiInfo and mobileInfo are associated with the networkinfo api and
		 * connectivity to provide information about those devices.
		 * 
		 * Both TextView and Button items are creating Java containers so they
		 * can be access. For instance, the error message for Internet and
		 * Compatible are invisible in the layout, but these two will be able to
		 * set the appropriate message visible.
		 * 
		 * The okButton.setOnClickListener is listening for the OK button to be clicked and will
		 * perform the action. In this case, it will tell the application to close.
		 */
		
		connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		mobileInfo = connectivity
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		TextView compatibleDisplay = (TextView) findViewById(R.id.compatiableError);
		TextView internetDisplay = (TextView) findViewById(R.id.internetError);
		Button okButton = (Button) findViewById(R.id.OkButton);
		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.exit(0);
			}
		});

		compatibilityTest(compatibleDisplay, okButton); // checking for
														// compatibility to
														// ensure it is API 8
														// and above.
		internetTest(internetDisplay, okButton); // checking to make sure there
													// is Internet connectivity.

	}

	@SuppressWarnings("unused")
	private void internetTest(TextView internetDisplay, Button okButton) {
		/*
		 * These two setters will configure wifiInfo and mobileInfo with the
		 * information needed to complete the task. In this case, it will gain
		 * if the wifi and mobile are connected.
		 */

		boolean wifiConnect = getWiFiConnect();
		boolean mobileConnect = getMobileConnect();

		/*
		 * if either isWifiConnect or isMobileConnect come back false (not
		 * connected), the system will report it is not connected. It will
		 * report this to the user via dialog box and exit the application.
		 */
		if ((mobileConnect = false) || (wifiConnect = false)) {
			internetDisplay.setVisibility(View.VISIBLE);
			okButton.setVisibility(View.VISIBLE);
			
		}

	}

	/*
	 * This setter is returning the information to let the the App know if it
	 * has mobile connectivity
	 */
	private boolean getMobileConnect() {
		return mobileInfo.isConnected();

	}

	/*
	 * This getter is returning information letting it know if it has wifi
	 * connectivity.
	 */
	private boolean getWiFiConnect() {
		return wifiInfo.isConnected();

	}

	private void compatibilityTest(TextView compatibleDisplay, Button okButton) {
		// capturing the version of the build.
		version = Build.VERSION.SDK;
		// version = "7"; // hardcoded variable to test.

		/*
		 * After obtaining it, we are converting version into an integer for
		 * numerical comparison. The NumberFormatException The
		 * NumberFormatException will throw an error if the information we
		 * provide to versionCheck is not a number inside the string.
		 */

		try {
			versionCheck = Integer.parseInt(version);
		} catch (NumberFormatException numFormat) {
			System.out.print(numFormat);
		}

		// If the versionCheck is below API 8, it will report wrong version and
		// exit the App.
		if (versionCheck < 8) {
			compatibleDisplay.setVisibility(View.VISIBLE);
			okButton.setVisibility(View.VISIBLE);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_launch, menu);
		return true;
	}

}
