package com.parse.starter;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.os.Bundle;

public class NearbyActivity extends Activity {
	// private variables
	private ParseGeoPoint currentLocation;
	private GPSTracker gps;
	private GoogleMap mMap;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby);
		// gets a handle on the gmap
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		// checks if the map is available
		setUpMapIfNeeded();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		// checks if the map is available
		setUpMapIfNeeded();
		gps = new GPSTracker(this);
		// Get user location and adds entry when done.
		if (gps.canGetLocation) {
            currentLocation = new ParseGeoPoint(gps.getLatitude(), gps.getLongitude());
            findNearby(currentLocation, 2);
		} else {
			//throw exception
		}
	}
	
	@Override
	public void onStop() {
		super.onStart();
	}
	
	// finds nearby locations
	public void findNearby(final ParseGeoPoint currentLocation, int radius) {
		// creates a query and gives it a distance condition
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Entry");
		query.whereWithinMiles("Location", currentLocation, radius);
		query.findInBackground(new FindCallback<ParseObject>() {
	        public void done(List<ParseObject> objects, ParseException e) {
	            if (e == null) {
	            	// updates the objects list to be sorted by proximity to user
	            	objects = sortPoints(objects, currentLocation);
	            	
	            	
	            } else {
	                //throw exception
	            }
	        }
		});
	}
	
	// orders the list of ParseGeoPoints
	public List<ParseObject> sortPoints(List<ParseObject> objects, ParseGeoPoint currentLocation) {
		List<ParseObject> sortedObjects= new ArrayList<ParseObject>();
		/*double a = 0;
		double b = 0;
		
		// iterates through the given objects
		for (int i = 1; i < objects.size(); i++) {
			int index = objects.get(i);
			int j = i;
			while (j > 0 && objects[j-1] > index) {
				
			}
		}
		
		// returns a sorted list*/
		return sortedObjects;
	}
	
	// calculates distance between two ParseGeoPoints
	public double calcDistance(ParseGeoPoint a, ParseGeoPoint b) {
		// gets the two points and returns the distance between them
		return Math.abs(Math.sqrt(Math.pow((b.getLatitude() - a.getLatitude()), 2) + Math.pow(b.getLongitude() - a.getLongitude(), 2)));
	}

	// verifies that the map is available
	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {
	        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.
	        }
	    }
	}
}