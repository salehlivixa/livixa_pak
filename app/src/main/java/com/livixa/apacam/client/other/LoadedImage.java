/**
 * 
 */
package com.livixa.apacam.client.other;

import android.graphics.Bitmap;

/**
 * @author : �Թ���
 * @version ��2012-12-28 ����2:11:35
 */
public class LoadedImage {
	Bitmap mBitmap;

	LoadedImage(Bitmap bitmap) {
		mBitmap = bitmap;
	}

	public Bitmap getBitmap() {
		return mBitmap;
	}
}
