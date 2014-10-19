package com.parse.starter;

import java.io.ByteArrayOutputStream;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableLayout.LayoutParams;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class UploadActivity extends Activity
{
	private String [] text = {"Neutral","Male","Female","Baby","Handicap","Family"};
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private ParseGeoPoint mCurrentLocation;
	private GPSTracker gps;
	private TextView tv_location;
	private Bitmap photo; 
	private byte[]imagebytes;
	private Context context;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload);
		tv_location = (TextView)findViewById(R.id.Location);
		populateTable();
		context = this;
		
		Button submit = (Button)findViewById(R.id.submitbutton);
		submit.setTypeface(MainActivity.font_pacifico);
		submit.setTextColor(getResources().getColor(R.color.Purple));
		submit.setTextSize(36);
		submit.setText("Submit");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		gps = new GPSTracker(this);
		// Get user location and adds entry when done.
		if (gps.canGetLocation) {
			double lat = gps.getLatitude();
			double lon = gps.getLongitude();
			mCurrentLocation = new ParseGeoPoint(lat, lon);
		}
	}
	
	@Override
	public void onStop() {
		super.onStart();
		gps.stopUsingGPS();
	}
	
	private void populateTable() {
		final int ROWS = 2;
		final int COLS = 3;
		int cnt = 0;
		
		TableLayout table = (TableLayout) findViewById(R.id.PropTable);

		for (int r = 0; r < ROWS; r++) {
			TableRow row = new TableRow(this);
			table.addView(row);			
			for (int c = 0; c < COLS; c++) {
				Button btn = new Button(this);
				//btn.setText(text[cnt]);
				row.addView(btn);
			}
			cnt++;
		}
		
	}

	/** Creates and intent to take a picture and return control to calling 
	  * application. Then starts the intent.
	  * @param v - view, used by layout */
	public void onClk_TakePicture(View v) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	@Override
	/** Gets the resulting image after the picture has been taking. Called
	  * after the image is taken. Creates an entry ParseObject and adds it to 
	  * the table. */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) { 	
				// Updating text view
				String loc = "Lat: " + (int)Math.round(mCurrentLocation.getLatitude()) 
							+"\tLon: " + (int)Math.round(mCurrentLocation.getLongitude());
				tv_location.setText(loc);
				
				// Updating image view
				ImageView imgvw = (ImageView)findViewById(R.id.Image);
				photo = (Bitmap) data.getExtras().get("data"); 
				imgvw.setImageBitmap(photo);
				
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
				imagebytes = stream.toByteArray();
	        }
		}
	}
	
	/** Adds to Parse database. */
	public void onClk_SubmitPicture(View v) {
		ParseFile image = new ParseFile(imagebytes);
		
		ParseObject entry = new ParseObject("Entry");
		entry.put("Image", image);
		entry.put("Location", mCurrentLocation);
		entry.saveInBackground(new SaveCallback(){
			@Override
			public void done(ParseException e) {
				Log.v("Upload", "Success");	
				tv_location.setText("succcess");
			}
		});
	
	}
	
	
}
