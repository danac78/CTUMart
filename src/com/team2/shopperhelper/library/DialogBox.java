package com.team2.shopperhelper.library;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team2.shopperhelper.R;

/**
 * Making the Dialog Box option reusuable without repeating code.
 * 
 * @author Dana Haywood
 * @since 8/8/2012
 * @version 0.5.0 <br>
 *          Instructor: Karl Lloyd<br>
 *          Class: IT482<br>
 *          University: Colorado Technical University<br>
 *          Source Cite: http://developer.android.com
 * 
 *          As the dialog box is repeated in numerous activities, it was
 *          separated into a library to allow re-useability amongst the different
 *          activities and classes.
 * 
 */
public class DialogBox extends Activity {
	/**
	 * This will be used to create a dialog box to display information for the
	 * customer.
	 */
	private Dialog dialog;

	/**
	 * A constructor to instantiate it in other classes.
	 */
	public DialogBox() {

	}

	/**
	 * This will create the dialog box and pass on the values from other class. It will
	 * display a message with a button avoid to close it. If the msg passed is "Error",
	 * the closure of the box will result in closing the application. Otherwise, it will
	 * just close the dialog box.
	 * 
	 * @param class1
	 *            This is the context for the class in question (for instance,
	 *            ShowProduct has a different context than SearchProduct)
	 * @param msg
	 *            This message will be come the title. In the case it has error,
	 *            it will also close the application.
	 * @param stringXmlMsg
	 *            The reference to strings.xml messages.
	 *            
	 *            
	 */
	public void postDialog(Context class1, final String msg, int stringXmlMsg) {
		
		dialog = new Dialog(class1);
		dialog.setContentView(R.layout.dialog);
		dialog.setTitle(msg);
		dialog.setCancelable(true);

		/*
		 * Creates a text view and passes the string references for the message
		 * needed to be given.
		 */
		TextView text = (TextView) dialog.findViewById(R.id.dialogTXT);

		text.setText(stringXmlMsg);
		Button button = (Button) dialog.findViewById(R.id.dialogCloseBTN);

		button.setOnClickListener(new View.OnClickListener() {
			/*
 */
			public void onClick(View v) {
				/*
				 * If the msg contains error because it is from the Launch page,
				 * it will close the application. Otherwise it will just close
				 * the dialog box.
				 */
				if (msg.contentEquals("Error")) {
					System.exit(0);
				} else {
					dialog.cancel();
				}

			}
		});

		dialog.show();

	}
}
