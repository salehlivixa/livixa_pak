package com.livixa.apacam.client.widget;

import java.util.Hashtable;

import android.content.Context;
import android.graphics.Typeface;

public class FontCache {

	private static Hashtable<Integer, Typeface> fontCache = new Hashtable<Integer, Typeface>();

	public static Typeface get(int fontValue, Context context) {
		Typeface tf = fontCache.get(fontValue);
		if (tf == null) {
			try {
				tf = Typeface.createFromAsset(context.getAssets(),
						FontUtility.getApplicationFont(fontValue));
			} catch (Exception e) {
				return null;
			}
			fontCache.put(fontValue, tf);
		}
		return tf;
	}
}