package edu.codepath.googleimagesearcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class OptionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	public void save(View v) {
		Toast.makeText(this, "You ckick me", Toast.LENGTH_LONG).show();
		EditText etSite = (EditText) this.findViewById(R.id.etSite);
		// Spinner spnSize, spnColor, spnType;
		Spinner spnSize = (Spinner) this.findViewById(R.id.spnSize);
		Spinner spnColor = (Spinner) this.findViewById(R.id.spnColor);
		Spinner spnType = (Spinner) this.findViewById(R.id.spnType);

		String size = spnSize.getSelectedItem().toString();
		String color = spnColor.getSelectedItem().toString();
		String type = spnType.getSelectedItem().toString();
		String site = etSite.getText().toString();

		AdvanceFilter af = new AdvanceFilter(size, color, type, site);
		Intent intent = new Intent();
		intent.putExtra(AdvanceFilter.BUNDLE_KEY, af);
		setResult(RESULT_OK, intent);
		this.finish();

	}
} // Activity
