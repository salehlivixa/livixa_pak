package com.livixa.apacam.client.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import com.livixa.apacam.client.utility.DebugHelper;
import com.livixa.client.R;

public class KisafaActivity extends Activity  {

	protected Context mContext;
	protected String TAG = getClass().getName();
	private TextView mTvAbsTittle;
	private ImageView mIvAbsBack;
	private ActionBar actionBar;
	private Activity mCallingActivity;

	// private Menu mOptionsMenu;

	protected void onCreate(Bundle savedInstanceState,
			final Activity currentActivity) {
		
		
		super.onCreate(savedInstanceState);
		mCallingActivity = currentActivity;
		//initGUIComponents();
	}

	protected void onCreate(Bundle savedInstanceState,
			final Activity currentActivity, final String activityTitle) {
		super.onCreate(savedInstanceState);
		mCallingActivity = currentActivity;
		// initGUIComponents();
		// updateHeaderText(activityTitle);
	}

	public void initUIComponents(final String activityTitle) {
		mContext = this;
		/*try {
			actionBar = getSupportActionBar();
			// final Drawable d = this.getResources().getDrawable(
			// R.drawable.abs_widgets_color_state);
			// actionBar.setBackgroundDrawable(d);
			actionBar.setDisplayUseLogoEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowCustomEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setCustomView(R.layout.action_bar_default);
			mTvAbsTittle = (TextView) actionBar.getCustomView().findViewById(
					R.id.ui_header_txt_title);
			updateHeaderText(activityTitle);
		} catch (Exception exception) {
			DebugHelper.printException(exception);
		}*/
	}

	protected void endScreen() {
		finish();
	}

	public void onLeftBtnPressed() {
		endScreen();
	}

	public void updateHeaderText(String text) {
		if (mTvAbsTittle != null) {
			mTvAbsTittle.setText("" + text);
			mTvAbsTittle.setVisibility(View.VISIBLE);
			mTvAbsTittle.setSingleLine(true);
			mTvAbsTittle.setEms(18);
			mTvAbsTittle.setEllipsize(TruncateAt.END);
		}
	}

	public void showHideHeader(boolean shouldShowHeader) {
		if (actionBar != null) {
			if (shouldShowHeader) {
				actionBar.show();
			} else {
				actionBar.hide();
			}
		}
	}
}
