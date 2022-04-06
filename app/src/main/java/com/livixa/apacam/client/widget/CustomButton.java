package com.livixa.apacam.client.widget;

import com.livixa.client.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {
	private int mDefaultFontValue = 1;

	public CustomButton(Context context) {
		super(context);
		setTypeFace(mDefaultFontValue, context);

	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray attriutes = context.obtainStyledAttributes(attrs,
				R.styleable.CustomTextView);
		final int fontValue = attriutes.getInt(
				R.styleable.CustomTextView_CustomFontFamily, mDefaultFontValue);
		setTypeFace(fontValue, context);
		attriutes.recycle();
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CustomTextView);
		final int fontValue = a.getInt(
				R.styleable.CustomTextView_CustomFontFamily, mDefaultFontValue);
		setTypeFace(fontValue, context);
		a.recycle();
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
