package edu.codepath.googleimagesearcher;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class AdvanceFilter implements Serializable {

	static String BUNDLE_KEY = "AdvanceFilter";
	// since only v=1.0 exist right hardcored, hardcode number of page rsz=4
	private static int pageSize = 8;
	private static String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz="
			+ pageSize;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4078626669711855105L;
	/**************
	 * er can configure advanced search options such as: Size (small, medium,
	 * large, extra-large) Color filter (black, blue, brown, gray, green,
	 * etc...) Type (faces, photo, clip art, line art) Site (espn.com)
	 */
	private String size;;
	private String color;
	private String type;
	private String site;

	public AdvanceFilter() {
		this("", "", "", "");
	}

	public AdvanceFilter(String size, String color, String type, String site) {
		this.setSize(size);
		setColor(color);
		setType(type);
		setSite(site);

	}

	public void setSize(String size) {
		this.size = size; // do have it
	}

	public void setColor(String color) { // tag="imgcolor="
		this.color = color; // ok keep it
	}

	public void setType(String type) { // tag="imgtype"
		this.type = type;
	}

	public void setSite(String site) { // dont have tag
		this.site = site;
	}

	// default construct
	public String getSize() {
		return size;
	}

	public String getColor() {
		return color;
	}

	public String getType() {
		return type;
	}

	public String getSite() { // tag = "as_sitesearch=" not sure
		return site;
	}

	public String toString() {
		return String.format("{%s, %s, %s, %s}", size, color, type, site);
	}

	public String constructRestUrl(String query, int page) {
		String params = "";
		if (this.color.length() > 0)
			params += "&imgcolor=" + color;
		if (this.type.length() > 0)
			params += "&imgtype=" + type;
		if (this.site.length() > 0)
			params += "&as_sitesearch=" + site;

		params = params
				+ String.format("&q=%s&page=%d", query, page * pageSize);
		return url + params;
	}

	
} // Advance Filter
