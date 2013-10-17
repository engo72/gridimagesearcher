package edu.codepath.googleimagesearcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.loopj.android.image.SmartImageView;

public class DisplayImageActivity extends Activity {
	public static String IMAGE_URL = "url";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_image);
		Intent intent = this.getIntent();
		ImageResult ir = (ImageResult) intent.getSerializableExtra(IMAGE_URL);
		SmartImageView siv = (SmartImageView)this.findViewById(R.id.ivFullImage);
		siv.setImageUrl(ir.getFullUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_image, menu);
		return true;
	}

}
