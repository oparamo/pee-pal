package com.parse.starter;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class NearbyActivity extends Activity {
	// private variables
	private LatLng user;
	private GPSTracker gps;
	private GoogleMap mMap;
	private GoogleMapOptions options = new GoogleMapOptions();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby);
		// makes a gps object, this makes geo functionality easier
		gps = new GPSTracker(this);
		// gets the user's location
		user = new LatLng(gps.getLatitude(), gps.getLongitude());
		
		// gets a handle on the map by id
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		// adds a marker at the user's location
		mMap.addMarker(new MarkerOptions().position(user).title("You"));
		/* sets the options for the map in order of cameraposition, zoomlevel, tilt, bearing
		options.mapType(GoogleMap.MAP_TYPE_NORMAL).camera(new CameraPosition(user, 19, 0, 0))
												  .zoomControlsEnabled(false).zoomGesturesEnabled(true).scrollGesturesEnabled(true);
		// initializes the map with the options above
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// Replace the container with the new fragment
		ft.add(R.id.map, MapFragment.newInstance(options));
		// Execute the changes specified
		ft.commit();*/
		
		// checks if the map is available
		setUpMapIfNeeded();
		findNearby(new ParseGeoPoint(gps.getLatitude(), gps.getLongitude()), 2);
		System.out.println(gps.getLatitude() + "   " + gps.getLongitude());
	}
	
	@Override
	public void onStart() {
		super.onStart();
		// clears the map
		mMap.clear();
		// checks if the map is available
		setUpMapIfNeeded();
		// adds a marker
		mMap.addMarker(new MarkerOptions().position(user).title("You"));
		// Get user location and adds entry when done
        findNearby(new ParseGeoPoint(gps.getLatitude(), gps.getLongitude()), 2);
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
	            //if (e == null) {
	            	// updates the objects list to be sorted by proximity to user
	            	//objects = sortPoints(objects, currentLocation);
	            	// iterates through the objects and puts markers on the map
	            	for(int i = 0; i < objects.size(); i++) {
	            		ParseGeoPoint point = (ParseGeoPoint)(objects.get(i).get("Location"));
	            		LatLng location = new LatLng(point.getLatitude(), point.getLongitude());
	            		mMap.addMarker(new MarkerOptions().position(location).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
	            	}
	            /*} else {
	                //throw exception
	            }*/
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