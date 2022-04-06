package com.livixa.client;

import object.p2pipcam.content.ContentCommon;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

/**
 * �������
 * **/
public class AboutActivity extends BaseActivity {
	private static final String LOG_TAG = "AboutActivity";
	private TextView tv_softversion, tv_softversion_self;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG, "AboutActivity onCreate");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
		tv_softversion = (TextView) findViewById(R.id.softversion);
		tv_softversion_self = (TextView) findViewById(R.id.softversion_self);
		tv_softversion.setText(ContentCommon.APP_SOFTVERSION);
		//tv_softversion_self.setText("");
	}
}