package com.livixa.apacam.client.widget;

import com.livixa.client.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {
	private int mDefaultFontValue = 1;

	public CustomTextView(Context context) {
		super(context);
		setTypeFace(mDefaultFontValue, context);

	}

	public CustomTextView(Context context, AttributeSet attrs) {

		super(context, attrs);
		TypedArray attriutes = context.obtainStyledAttributes(attrs,
				R.styleable.CustomTextView);
		final int fontValue = attriutes.getInt(
				R.styleable.CustomTextView_CustomFontFamily, mDefaultFontValue);
		setTypeFace(fontValue, context);
		attriutes.recycle();
	}

	public void setTypeFace(final int fontValue, Context context) {
		if (!isInEditMode()) {

			Typeface myTypeFace = FontCache.get(fontValue, context);
			if (myTypeFace != null) {
				this.setTypeface(myTypeFace);
			}

		}
	}
}