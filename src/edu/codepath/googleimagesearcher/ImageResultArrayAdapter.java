package edu.codepath.googleimagesearcher;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {
	private static String TAG = SearchActivity.TAG;
	public ImageResultArrayAdapter(Context context, List<ImageResult> objects) {
	//	super(context, android.R.layout.simple_list_item_1, objects);
		super(context, R.layout.item_image_result, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageResult ir = this.getItem(position);
		SmartImageView siv;
		if (convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			siv = (SmartImageView) inflater.inflate(R.layout.item_image_result, parent, false);
		}
		else 
		{ 
			siv = (SmartImageView)convertView;
			siv.setImageResource(android.R.color.transparent);	// clear image
		}
		siv.setImageUrl(ir.getThumbUrl());
		Log.d(TAG, "############ SmartImageView =" + siv);
		return siv;
	}	// getView

} // ImageResulstArrayAdapter
