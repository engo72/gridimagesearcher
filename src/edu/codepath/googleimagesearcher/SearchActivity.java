package edu.codepath.googleimagesearcher;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity {
	public static String TAG = "SearchActivity";

	private EditText etQuery;
	private GridView gvResult;
	private ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	private ImageResultArrayAdapter imageResultAdapter;
	private AdvanceFilter advanceFilter = new AdvanceFilter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	// ================= private/event methods ==================
	private void init() {
		etQuery = (EditText) this.findViewById(R.id.etQuery);
		this.gvResult = (GridView) this.findViewById(R.id.gvResult);
		// Add Listeners
		this.imageResultAdapter = new ImageResultArrayAdapter(
				this.getApplicationContext(), imageResults);
		this.gvResult.setAdapter(this.imageResultAdapter);
		this.gvResult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent,
					int position, long arg3) {
				displayImage(position);
			}
		});

		// add Action Item for Advance Search
		// modify menu for search.xml only
		// init scroll
		this.gvResult.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				Toast.makeText(getApplicationContext(),"########## new page ="+page, Toast.LENGTH_LONG).show();
				loadPage(page,false);
			}
		}); 
	} // init()

	/**
	 * Handle "search" onClick button event.
	 * 
	 * @param v
	 */
	@SuppressLint("DefaultLocale")
	public void onClick(View view) {
		this.loadPage(0,  true);	// new search
	} // onClick

	/**
	 * View the image result at the position specified. If
	 * 
	 * @param position
	 *            the position of the image to display.
	 */
	private void displayImage(int position) {
		ImageResult ir = this.imageResults.get(position);
		Intent intent = new Intent(this.getApplicationContext(),
				DisplayImageActivity.class);
		intent.putExtra(DisplayImageActivity.IMAGE_URL, ir);
		this.startActivity(intent);
	} // viewImage(..)

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.itmAdvanceFilter) {
			Toast.makeText(this, "########## Options ", Toast.LENGTH_LONG)
					.show();
			Intent intent = new Intent(this.getApplicationContext(),
					OptionsActivity.class);
			this.startActivityForResult(intent, 1);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 1 && resultCode == RESULT_OK) {
			this.advanceFilter = (AdvanceFilter) data
					.getSerializableExtra(AdvanceFilter.BUNDLE_KEY);
			Log.d(TAG, this.advanceFilter.toString());
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void loadPage(int page, final boolean reset) {
		String query = this.etQuery.getText().toString();

		// search for image =====
		String url = this.advanceFilter.constructRestUrl(query, page);

		Log.d(TAG, "RESTful's#### url=" + url);

		AsyncHttpClient ahc = new AsyncHttpClient();

		ahc.get(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				try {
					JSONArray results = response.getJSONObject("responseData")
							.getJSONArray("results");
					Log.v(TAG, "Jason imageResults = " + results.toString());
					if (reset) // reset, new search
						imageResults.clear();
					imageResultAdapter.addAll(ImageResult
							.constructImageArray(results));

					Log.v(TAG, "imageResults = " + imageResults.toString());
				} catch (JSONException e) {
					Log.w(TAG, e.getMessage());
				}
			}
		});

	} // load page
} // activity

