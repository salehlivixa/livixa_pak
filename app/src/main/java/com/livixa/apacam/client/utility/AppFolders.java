package com.livixa.apacam.client.utility;

import java.io.File;

import com.livixa.apacam.client.base.KisafaApplication;
import android.os.Environment;

public class AppFolders {

	public static final String FILE_PATH_BASE = "/Android/data/com.idevnerds.kisafa/.files/";
	public static final String IMAGE_FILE_PATH = FILE_PATH_BASE + ".images/";
	private static final String TEMP_IMAGE_FOLDER = "/.temp_image/";

	public static String getRootPath() {
		File path = KisafaApplication.getInstance().getApplicationContext()
				.getExternalFilesDir(null);
		if (path == null) {
			path = KisafaApplication.getInstance().getApplicationContext()
					.getFilesDir();
		}
		return path.toString();
	}

	public static File getImageFilePath() {
		return new File(Environment.getExternalStorageDirectory().toString()
				+ AppFolders.IMAGE_FILE_PATH);
	}

	public static String getRandomImageFilePath() {
		return Environment.getExternalStorageDirectory().toString()
				+ AppFolders.IMAGE_FILE_PATH
				+ (System.currentTimeMillis() / 1000) + ".jpg";
	}
}