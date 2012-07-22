package com.team2.shopperhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Dana Haywood
 * @date 7/10/2012
 * @version 0.1.5
 * @IT482
 * @Karl Lloyd
 * @Source Cite:http://developer.android.com/guide/components/index.html
 * @Commented by:
 * 
 * 
 *            This class is the initial activity that is launched when the icon
 *            is tapped on. It will direct itself into Search for Store, but it
 *            needs to check to ensure if that the Android OS is compatible (in
 *            case of Android OS corruption) and that it is connected to the
 *            Internet. If it loads with these two conditions being false, it
 *            will display a message alerting the user. Once they click on the
 *            ok button, the application will close. If both items are true, the
 *            user will just see Search Store appear. In the simulations ran,
 *            this actually only takes one second and the customer will not

 *            notice these tests being conducted at all.
 * 

 *            notice these tests being conducted at all. .
 * @Commenters .

 */

public class Launch extends Activity {
	/*
	 * Declaring the variables required for this activity. These will be
	 * explained when we bring them to build objects.
	 */
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		/*
		 * Setting up the objects:
		 * 
		 * 1. internetDisplay ties into the textview displaying that message
		 * 2. compatibleDisplay ties into the textview displaying that message.
		 * 3. okButton is the button that will be clicked to close the program
		 * 4. connectivity is tying into Android ConnectivityManager API, which is
		 *    part of the internet for Android.
		 * 5. wiFiInfo: Getting the information about the Android's wifi connectivity.
		 * 6. mobileInfo: Getting the information about the Android's mobile connectivty
		 * 7. mobileIsConnect: Containing the value if it is connected or not.
		 * 8. wifiIsConnect: Containing the value if wifi is connected or not.
		 * 9. version: Obtaining the version build.
		 * 10. checkVersion: Changing it into an Integer. There is an integer version
		 *     of this API, but does not work with version 1 phones. It would technically
		 *     bypass this feature.
		 */
		
		final TextView internetDisplay = (TextView) findViewById(R.id.internetError);
		final TextView compatibleDisplay = (TextView) findViewById(R.id.compatiableError);
		final Button okButton = (Button) findViewById(R.id.OkButton);
		ConnectivityManager connectivity = (ConnectivityManager) 
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wiFiInfo = connectivity.
				getNetworkInfo(ConnectivityManager.TYPE_WIFI);		
		NetworkInfo mobileInfo = connectivity.
				getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//		
		/*
		 * For SOME reason, Android is hating booleans, so using String so I can
		 * check the contents which has been shown to work.
		 */
		String mobileIsConnect = Boolean.toString(mobileInfo.isConnected());
		String wifiIsConnect = Boolean.toString(wiFiInfo.isConnected());
//		mobileIsConnect = "false";//hard code for testing
//		wifiIsConnect = "false"; //hard code for testing
		String version = Build.VERSION.SDK;
//		String version = "7"; //hardcode for testing.
		int checkVersion = Integer.parseInt(version);
		Intent intent = new Intent(this,SearchForStore.class);
		
		/*
		 * When the become visible, it will follow this listener instructions
		 * to close the program. It will set the two text views back to invisible
		 * in the XML file.
		 */
		okButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				okButton.setVisibility(View.INVISIBLE);
				compatibleDisplay.setVisibility(View.INVISIBLE);
				internetDisplay.setVisibility(View.INVISIBLE);
				System.exit(0);
				
			}
		});
		
		/*
		 * Running through a system validation. Any one of these methods will
		 * stop the program from running. If both mobile and wifi are not connected,
		 * this will cause an internet issue to be displayed. If the compatible see 
		 * it is less than 8 (2.2), it will raise that issue. If it makes it through
		 * there, it will start the next activity. So far, there has been no consensus
		 * on whether or not someone should see the launch screen. Keeping it this 
		 * way so the only time they see the launch screen is if there is a problem.
		 */
		if ((mobileIsConnect.contentEquals("false")) && 
				(wifiIsConnect.contentEquals("false"))) 
		{
			internetDisplay.setVisibility(View.VISIBLE);
			okButton.setVisibility(View.VISIBLE);
		} else if (checkVersion < 8) 
		{
			compatibleDisplay.setVisibility(View.VISIBLE);
			okButton.setVisibility(View.VISIBLE);
		} else 
		{
			startActivity(intent);
		}
		

		
	}

}
