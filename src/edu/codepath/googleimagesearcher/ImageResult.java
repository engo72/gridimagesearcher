package edu.codepath.googleimagesearcher;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ImageResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5109230479819204708L;

	private static String TAG = SearchActivity.TAG;

	private String fullUrl;
	private String thumbUrl;
	
	
	public ImageResult(JSONObject json)
	{
		try {
			this.fullUrl = json.getString("url");
			this.thumbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
			Log.w(TAG, "warning getting image url");
		}
		
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public static ArrayList<ImageResult> constructImageArray(JSONArray json)
	{
		ArrayList<ImageResult> ar = new ArrayList<ImageResult>();
		for (int i=0; i < json.length(); i++)
		{
			try {
				ar.add(new ImageResult(json.getJSONObject(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.w(TAG, e.getMessage());
			}
		}	// for(..)
		return ar;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.thumbUrl;
	}
}	// ImageResult
