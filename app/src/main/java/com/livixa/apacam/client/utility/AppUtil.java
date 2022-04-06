package com.livixa.apacam.client.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.livixa.client.R;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The Class AppUtil.
 */

@SuppressWarnings("resource")
public class AppUtil {

	final static String TAG = "AppUtil";

	/**
	 * The Constant SECOND.
	 */
	public static final long SECOND = 1000;

	/**
	 * The Constant MINT.
	 */
	public static final long MINT = 60 * AppUtil.SECOND;

	/**
	 * The Constant HOUR.
	 */
	public static final long HOUR = 60 * AppUtil.MINT;

	/**
	 * The Constant DAY.
	 */
	public static final long DAY = 24 * AppUtil.HOUR;

	public static final long WEEK = 7 * AppUtil.DAY;

	public static final long MONTH = 30 * AppUtil.DAY;

	public static final long YEAR = 12 * AppUtil.MONTH;

	// url + append token

	// Printing HashKey
	public static void KeyHash(Context context, String packageName) {

		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					packageName, PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("Followit",
						Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (NameNotFoundException e) {
		} catch (NoSuchAlgorithmException e) {
		}
	}

	@SuppressWarnings("deprecation")
	public static void showAlertDialog(Context context, String title, String msg) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(msg);

		// Setting Icon to Dialog
		// alertDialog.setIcon(R.drawable.tick);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		alertDialog.show();
	}

	public static Map<String, String> getHeaders(Context context) {
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("X-API-AUTHID",
				AppPreference.getValue(context, AppKeys.KEY_X_API_AUTHID));
		temp.put("X-API-AUTHKEY",
				AppPreference.getValue(context, AppKeys.KEY_X_API_AUTHKEY));
		return temp;
	}

	public static Map<String, String> getTokenHeader(Context context) {
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("x-access-token",
				AppPreference.getValue(context, AppKeys.KEY_TOKEN));
		return temp;
	}

	public static String ordinal(int i) {
		String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th",
				"th", "th", "th", "th" };
		switch (i % 100) {
		case 11:
		case 12:
		case 13:
			return i + "th";
		default:
			return i + sufixes[i % 10];

		}
	}

	public static void showConfirmationDialog(Context context, int titleId,
			int msgId, int negId, int posId) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(KisafaApplication.getAppContext().getString(titleId));
		builder.setTitle(KisafaApplication.getAppContext().getString(msgId));
		builder.setNegativeButton(
				KisafaApplication.getAppContext().getString(negId),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.setPositiveButton(
				KisafaApplication.getAppContext().getString(posId),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		builder.create().show();
	}

	/**
	 * Gets the app build date.
	 * 
	 * @param context
	 *            the context
	 * @return the app build date
	 */

	public static String getAppBuildDate(Context context) {

		String date = null;
		try {
			final ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(), 0);
			final ZipFile zf = new ZipFile(ai.sourceDir);
			final ZipEntry ze = zf.getEntry("classes.dex");
			final long time = ze.getTime();
			date = AppUtil.convertDateToString(new Date(time));

		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return date;
	}

	/**
	 * Convert date to string.
	 * 
	 * @param sDate
	 *            the s date
	 * @return the string
	 */
	public static String convertDateToString(Date sDate) {

		final SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
		final String strDate = sdf.format(sDate);
		return strDate;
	}

	/**
	 * Convert to m d5.
	 * 
	 * @param stringToConvert
	 *            the string to convert
	 * @return the string
	 */
	public static String convertToMD5(String stringToConvert) {

		String result = null;
		try {
			byte[] hash;

			try {
				hash = MessageDigest.getInstance("MD5").digest(
						stringToConvert.getBytes("UTF-8"));
			} catch (final NoSuchAlgorithmException exception) {
				throw new RuntimeException("Huh, MD5 should be supported?",
						exception);
			} catch (final UnsupportedEncodingException exception) {
				throw new RuntimeException("Huh, UTF-8 should be supported?",
						exception);
			}

			final StringBuilder hex = new StringBuilder(hash.length * 2);
			for (final byte b : hash) {
				if ((b & 0xFF) < 0x10) {
					hex.append("0");
				}
				hex.append(Integer.toHexString(b & 0xFF));
			}
			result = hex.toString();
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return result;
	}

	/**
	 * Gets the current time.
	 * 
	 * @return the current time
	 */
	public static String getCurrentTime() {

		return "" + System.currentTimeMillis();
	}

	public static Bitmap decodeAdjustedFileHD(String fileName) {

		if (fileName != null) {
			File imageFile = new File(fileName);
			return adjustOrientation(decodeFile(imageFile, 400),
					imageFile.toURI());
		}

		return null;
	}

	public static Bitmap adjustOrientation(Bitmap bmp, URI uri) {
		try {
			ExifInterface exif = new ExifInterface(uri.getPath());
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			Matrix matrix = new Matrix();
			if (orientation == 6) {
				matrix.postRotate(90);
			}
			return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
					bmp.getHeight(), matrix, true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap decodeFile(File f, int simpleSize) {

		try {
			// decode image size
			final Options o = new Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = simpleSize;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE
						|| height_tmp / 2 < REQUIRED_SIZE) {
					break;
				}
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			final Options o2 = new Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (final FileNotFoundException exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		} catch (final OutOfMemoryError error) {
			DebugHelper.trackError(error);
		}
		return null;
	}

	public static Bitmap decodeFileHD(String fileName) {

		if (fileName != null) {
			File imageFile = new File(fileName);
			return decodeFile(imageFile, 200);
		}

		return null;
	}

	public Bitmap getBitmapFromURI(Context mContext, Uri uri) {
		InputStream inputStream;
		Bitmap bitmap = null;
		try {
			inputStream = mContext.getContentResolver().openInputStream(uri);

			Options imageOpts = new Options();
			imageOpts.inSampleSize = 3;
			bitmap = Bitmap.createScaledBitmap(
					BitmapFactory.decodeStream(inputStream, null, imageOpts),
					400, 265, false);
			if (bitmap.getWidth() > 800) {
				bitmap = Bitmap.createScaledBitmap(bitmap, 800,
						bitmap.getHeight(), false);
			}
			if (bitmap.getHeight() > 600) {
				bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(),
						600, false);
			}
			inputStream.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

		return bitmap;

	}

	/**
	 * Verify edit text data.
	 * 
	 * @param context
	 *            the context
	 * @param editTextView
	 *            the edit text view
	 * @param needTrimSpaces
	 *            the need trim spaces
	 * @param showMessage
	 *            the show message
	 * @return the string
	 */
	public static String verifyEditTextData(Context context,
			EditText editTextView, boolean needTrimSpaces, boolean showMessage) {

		String textOfEditext = null;
		if (editTextView != null) {
			textOfEditext = editTextView.getText().toString();
			if (textOfEditext != null) {
				if (needTrimSpaces) {
					textOfEditext = textOfEditext.trim();
				}
				if (textOfEditext.length() <= 0) {
					textOfEditext = null;
					// editTextView.setBackgroundResource(R.drawable.edit_text_backgound_error);
				}
				if (textOfEditext == null && showMessage) {
					String message = context
							.getString(R.string.msg_please_enter_some_text_in);
					if (needTrimSpaces) {
						message = context
								.getString(R.string.error_msg_must_contains_characters_in);
					}

					message = message + " " + editTextView.getTag();
					AppUtil.showDialogMessage(context, message);
				} else {
					// editTextView.setBackgroundResource(R.drawable.edit_text_backgound);
				}
			}
		}
		return textOfEditext;
	}

	/**
	 * Show dialog message.
	 * 
	 * @param context
	 *            the context
	 * @param message
	 *            the message
	 */
	public static void showDialogMessage(Context context, String message) {

		try {
			if (context != null) {
				final AlertDialog alert = new AlertDialog.Builder(context)
						.setMessage(message)
						.setPositiveButton(context.getString(R.string.btn_ok),
								null).create();
				alert.show();

			}
		} catch (final Exception exception) {
			DebugHelper.trackException(exception);
		}
	}

	/**
	 * Show dialog message.
	 * 
	 * @param context
	 *            the context
	 * @param message
	 *            the message
	 * @param title
	 *            the title
	 */
	public static void showDialogMessage(Context context, String message,
			String title) {

		try {
			if (context != null) {
				final AlertDialog alert = new AlertDialog.Builder(context)
						.setTitle(title)
						.setMessage(message)
						.setPositiveButton(context.getString(R.string.btn_ok),
								null).create();
				alert.show();

			}
		} catch (final Exception exception) {
			DebugHelper.trackException(exception);
		}
	}

	public static Boolean isNetworkAvailable() {
		ConnectivityManager manager = (ConnectivityManager) KisafaApplication
				.getAppContext()
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		// For 3G check
		boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.isConnectedOrConnecting();
		// For WiFi Check
		boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.isConnectedOrConnecting();
		// System.out.println(is3g + " net " + isWifi);
		if (!is3g && !isWifi) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Change time from 24 hour format to 12 hour format
	 * 
	 * @param string
	 *            time
	 */
	public static String convertTimeTo12hourFormat(String time) {
		String s = time;
		DateFormat f1 = new SimpleDateFormat("HH:mm:ss"); // HH for hour of the
		// day (0 - 23)
		Date d = null;
		try {
			d = f1.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateFormat f2 = new SimpleDateFormat("h:mma");
		return f2.format(d).toLowerCase();
	}

	public static String convertDateTimeTo12hourFormat(String dateTime) {

		String s = "00:00:00";
		try {
			if (dateTime.contains("T")) {
				String[] result = dateTime.split("T");
				s = result[1];
			} else {
				s = dateTime;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DateFormat f1 = new SimpleDateFormat("HH:mm:ss"); // HH for hour of the
		// day (0 - 23)
		Date d = null;
		try {
			d = f1.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateFormat f2 = new SimpleDateFormat("h:mma");
		return f2.format(d).toLowerCase();
	}

	public static String convertDateTimeToDateWithSlashes(String dateTime) {

		String s = "00:00:00";
		try {
			if (dateTime.contains("T")) {
				String[] result = dateTime.split("T");
				s = result[0];
			} else {
				s = dateTime;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DateFormat f1 = new SimpleDateFormat("yyyy-MM-dd"); // HH for hour of
		// the
		// day (0 - 23)
		Date d = null;
		try {
			d = f1.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");
		return f2.format(d).toLowerCase();
	}

	public static String splitFullDateTimeFormat(String dateTime) {
		String s = "";
		try {
			if (dateTime.contains("T")) {
				String[] result = dateTime.split("T");

				s = result[0];
				return s;
			} else {
				s = dateTime;
				return dateTime;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * Show dialog message.
	 * 
	 * @param context
	 *            the context
	 * @param message
	 *            the message
	 * @param title
	 *            the title
	 */
	public static void showDialogMessage(Context context,
			DialogInterface.OnClickListener clickHandler, String message,
			String title) {

		try {
			if (context != null) {
				final AlertDialog alert = new AlertDialog.Builder(context)
						.setTitle(title)
						.setMessage(message)
						.setPositiveButton(context.getString(R.string.btn_ok),
								clickHandler).create();
				alert.show();

			}
		} catch (final Exception exception) {
			DebugHelper.trackException(exception);
		}
	}

	/**
	 * Show insufficient space dialog.
	 * 
	 * @param context
	 *            the context
	 * @param message
	 *            the message
	 * @param title
	 *            the title
	 * @param fileSize
	 *            the file size
	 */
	// public static void showInsufficientSpaceDailog(final Context context,
	// String message, String title, long fileSize) {
	//
	// try {
	// final Context caller = context;
	//
	// final DecimalFormat df = new DecimalFormat("0.0");
	// final double SizeOfFile = (double) (fileSize /
	// ExternalStorageHandler.SIZE_KB)
	// / (double) ExternalStorageHandler.SIZE_KB;
	// final double AvailableSpace = (double) (ExternalStorageHandler
	// .getExternalStorageAvailableSpace() / ExternalStorageHandler.SIZE_KB)
	// / (double) ExternalStorageHandler.SIZE_KB;
	// final double RequiredSpace = (double) ((fileSize - ExternalStorageHandler
	// .getExternalStorageAvailableSpace()) / ExternalStorageHandler.SIZE_KB)
	// / (double) ExternalStorageHandler.SIZE_KB;
	//
	// final Dialog dialog = new Dialog(caller);
	// dialog.getWindow();
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// dialog.setContentView(R.layout.dialog_insufficient_space);
	//
	// final Button btnOk = (Button) dialog
	// .findViewById(R.id.insufficient_space_ok_btn);
	// final TextView downloadSize = (TextView) dialog
	// .findViewById(R.id.download_size);
	// final TextView availableSpace = (TextView) dialog
	// .findViewById(R.id.available_size);
	// final TextView requiredSpace = (TextView) dialog
	// .findViewById(R.id.required_Size);
	// final TextView dlgTitle = (TextView) dialog
	// .findViewById(R.id.insufficient_space_title);
	// final TextView dlgMessage = (TextView) dialog
	// .findViewById(R.id.insufficient_space_message);
	//
	// dlgTitle.setText(title);
	// dlgMessage.setText(message);
	// downloadSize.setText(" " + df.format(SizeOfFile) + " MB ");
	// availableSpace.setText(" " + df.format(AvailableSpace) + " MB ");
	// requiredSpace.setText(" " + df.format(RequiredSpace) + " MB");
	//
	// btnOk.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View view) {
	//
	// dialog.dismiss();
	// dialog.cancel();
	// }
	// });
	// dialog.show();
	//
	// } catch (final Exception exception) {
	//
	// DebugHelper.trackException(AppUtil.TAG, exception);
	// }
	//
	// }
	public Bitmap combineImages(Bitmap c, Bitmap s) { // can add a 3rd parameter
		// 'String loc' if you
		// want to save the new
		// image - left some
		// code to do that at
		// the bottom
		Bitmap cs = null;

		int width, height = 0;

		if (c.getWidth() > s.getWidth()) {
			width = c.getWidth();
			height = c.getHeight() + s.getHeight();
		} else {
			width = s.getWidth();
			height = c.getHeight() + s.getHeight();
		}

		cs = Bitmap.createBitmap(width, height, Config.ARGB_8888);

		Canvas comboImage = new Canvas(cs);

		comboImage.drawBitmap(c, 0f, 0f, null);
		comboImage.drawBitmap(s, 0f, c.getHeight(), null);

		return cs;

	}

	public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
		Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(),
				bmp1.getHeight(), bmp1.getConfig());
		Canvas canvas = new Canvas(bmOverlay);
		canvas.drawBitmap(bmp1, new Matrix(), null);
		canvas.drawBitmap(bmp2, 0, 0, null);
		return bmOverlay;
	}

	/**
	 * Save image.
	 * 
	 * @param bitmap
	 *            the bitmap
	 * @param path
	 *            the path
	 * @param fileName
	 *            the file name
	 * @param context
	 *            the context
	 * @return the string
	 */
	public static String saveImage(Bitmap bitmap, String path, String fileName,
			Context context) {

		return AppUtil.saveImage(bitmap, path, fileName, context, false);
	}

	/**
	 * Save image.
	 * 
	 * @param bitmap
	 *            : its the bitmap data to be written to the file
	 * @param path
	 *            : should start and end with a slash e.g. /path/
	 * @param fileName
	 *            : should be a string with extension without any slashes e.g.
	 *            fileName.ext
	 * @param context
	 *            : context is cascading from the action-starting-activity to
	 *            this function
	 * @param replace
	 *            the recycle
	 * @return the string
	 * @returns fileName: file on which the file was written
	 */
	public static String saveImage(Bitmap bitmap, String path, String fileName,
			Context context, boolean replace) {

		OutputStream outStream = null;

		File file = null;
		try {
			file = new File(path, "");
			file.mkdirs();
			File imgFile = new File(path + fileName);
			if (new File(path + fileName).exists() == true) {
				imgFile.delete();
			}
			if (bitmap == null) {
				return null;
			}

			file = new File(path, fileName);
			outStream = new FileOutputStream(file);

			if (fileName.endsWith(".png") || fileName.endsWith(".PNG")) {
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			} else if (fileName.endsWith(".jpg") || fileName.endsWith(".JPG")) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outStream);
			} else {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outStream);
			}

			outStream.flush();
			outStream.close();
		} catch (final FileNotFoundException exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return file.toString();
	}

	/**
	 * Does file exist.
	 * 
	 * @param filePath
	 *            the file path
	 * @return true, if successful
	 */
	public static boolean doesFileExist(String filePath) {

		String rootDir = null;
		InputStream input = null;
		try {
			rootDir = Environment.getExternalStorageDirectory().toString();
			input = new FileInputStream(rootDir + filePath);

			final int data = input.read();
			while (data != -1) {
				input.close();
				return true;
			}
			input.close();
		} catch (final FileNotFoundException exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
			return false;
		} catch (final IOException exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
			return false;
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
			return false;
		}
		input = null;
		return false;
	}

	public static String formatPrice(double price) {

		try {
			DecimalFormat df = new DecimalFormat("$###0.00");
			return df.format(price);
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return "" + price;
	}

	/**
	 * Gets the rounded corner bitmap.
	 * 
	 * @param bitmap
	 *            the bitmap
	 * @param pixels
	 *            the pixels
	 * @param context
	 *            the context
	 * @return the rounded corner bitmap
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels,
			Context context) {

		Bitmap output = null;
		try {
			if (bitmap == null || bitmap.isRecycled()) {
				return null;
			}
			output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
					Config.ARGB_8888);
			final Canvas canvas = new Canvas(output);

			final int color = 0xff424242;
			final Paint paint = new Paint();

			final Rect rect = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			final float roundPx = pixels;

			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			// paint.setAlpha(0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			// paint.setAlpha(255);
			canvas.drawBitmap(bitmap, rect, rect, paint);
			if (output == null || output.isRecycled()) {
				return bitmap;
			}
			if (bitmap != null && bitmap.isRecycled() == false) {
				bitmap.recycle();
			}
			return output;
		} catch (final OutOfMemoryError e) {
			DebugHelper.trackError(AppUtil.TAG, e);
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		if (output != null && output.isRecycled() == false) {
			output.recycle();
		}
		return bitmap;
	}

	// decodes image and scales it to reduce memory consumption

	/**
	 * Decode file.
	 * 
	 * @param f
	 *            the f
	 * @return the bitmap
	 */
	public static Bitmap decodeFile(File f) {

		try {
			// decode image size
			final Options o = new Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 100;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < REQUIRED_SIZE
						|| height_tmp / 2 < REQUIRED_SIZE) {
					break;
				}
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// decode with inSampleSize
			final Options o2 = new Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (final FileNotFoundException exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return null;
	}

	/**
	 * Checks if is saved.
	 * 
	 * @param filePath
	 *            the file path
	 * @return true, if is saved
	 */
	public static boolean isSaved(String filePath) {

		boolean isFileSaved = false;
		try {
			final File file = new File(filePath);
			isFileSaved = file.exists();
		} catch (final Exception exception) {

			DebugHelper.trackException(AppUtil.TAG, exception);
		}

		return isFileSaved;
	}

	/**
	 * Delete hidden folder.
	 * 
	 * @return true, if successful
	 */
	public static boolean deleteHiddenFolder() {
		boolean isDeleted = false;
		try {
			// FileUtils
			// .deleteDirectory(new File(AppFolders.getHiddenFolderPath()));
			isDeleted = true;
		} catch (Exception exception) {
			DebugHelper.printException(TAG, exception);
		}
		return isDeleted;
	}

	/**
	 * Removes the directory.
	 * 
	 * @param directory
	 *            the directory
	 * @return true, if successful
	 */
	public static boolean removeDirectory(String directory) {

		if (directory != null && directory.length() > 0) {
			return AppUtil.removeDirectory(new File(directory));
		}
		return false;
	}

	/**
	 * Removes the directory.
	 * 
	 * @param directory
	 *            the directory
	 * @return true, if successful
	 */
	public static boolean removeDirectory(File directory) {

		if (directory == null) {
			return false;
		}
		if (!directory.exists()) {
			return true;
		}
		if (directory.isDirectory()) {
			final String[] list = directory.list();
			// Some JVMs return null for File.list() when the
			// directory is empty.
			if (list != null) {
				for (final String element : list) {
					final File entry = new File(directory, element);
					if (entry.isDirectory()) {
						if (!AppUtil.removeDirectory(entry)) {
							return false;
						}
					} else {
						if (!entry.delete()) {
							return false;
						}
					}
				}
			}
		}
		return directory.delete();
	}

	/**
	 * Gets the directory files list.
	 * 
	 * @param directory
	 *            the directory
	 * @return the directory files list
	 */
	public static HashMap<String, File> getDirectoryFilesList(String directory) {

		return AppUtil.getDirectoryFilesList(new File(directory));
	}

	/**
	 * Gets the directory files list.
	 * 
	 * @param directory
	 *            the directory
	 * @return the directory files list
	 */
	public static HashMap<String, File> getDirectoryFilesList(File directory) {

		final HashMap<String, File> directoryList = new HashMap<String, File>();

		if (directory == null) {
			return directoryList;
		}
		if (!directory.exists()) {
			return directoryList;
		}
		if (directory.isDirectory()) {
			final String[] list = directory.list();
			// Some JVMs return null for File.list() when the
			// directory is empty.
			if (list != null) {
				for (final String element : list) {
					final File entry = new File(directory, element);
					if (!entry.isDirectory()) {
						String name = entry.getName();
						if (name.length() > 0 && name.contains(".")) {
							name = (String) name.subSequence(0,
									name.lastIndexOf('.'));
							directoryList.put(name, entry);
						}
					}
				}
			}
		}
		return directoryList;
	}

	/**
	 * Show toast.
	 * 
	 * @param appContext
	 *            the app context
	 * @param message
	 *            the message
	 */
	public static void showToast(Context appContext, String message) {

		Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
	}

	public static void showToastLong(Context appContext, String message) {

		Toast.makeText(appContext, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * Gets the com.gsoft.pathfindervs.network type.
	 * 
	 * @param ctx
	 *            the ctx
	 * @return the com.gsoft.pathfindervs.network type
	 */
	public String getNetworkType(Context ctx) {

		final ConnectivityManager cm = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// int networkType = cm.getActiveNetworkInfo().getType();
		if (cm != null) {
			final String networkTypeName = cm.getActiveNetworkInfo()
					.getTypeName();

			final TelephonyManager tm = (TelephonyManager) ctx
					.getSystemService(Context.TELEPHONY_SERVICE);
			final int telNetworkType = tm.getNetworkType();

			return networkTypeName + " - " + telNetworkType;
		}
		return "";
	}

	// public static void playAudioVideo(final LCVideo LCVideo,
	// final boolean isWatchInstant, final Context activity) {
	// if (LCVideo.getFileFolderName() == LtdFolders.AUDIO) {
	// LTDPlayerFactory.startLTDAudioPlayer(LCVideo, activity);
	// return;
	// }
	// String localPath = null;
	// File file = null;
	// try {
	//
	// if (isWatchInstant == false) {
	//
	// if (LCVideo.isCanEncrypt()) {
	// localPath = LCVideo.getHiddenFilePath();
	// } else {
	// localPath = LCVideo.getFilePath();
	// }
	//
	// file = new File(localPath);
	//
	// } else {
	//
	// localPath = SMUtility.createFileUrl(
	// LCVideo.getFileNameServer(), activity,
	// LCVideo).toString();
	// localPath = localPath.replace("https", "http");
	//
	// }
	//
	// final Intent intent = new Intent();
	// intent.setAction(android.content.Intent.ACTION_VIEW);
	//
	// if (isWatchInstant == true) {
	//
	// final String tempLocalPath = new String(localPath);
	//
	// BGWorkerDefiner workToDone = new BGWorkerDefiner() {
	//
	// @Override
	// public Object performInBackground() {
	// return tempLocalPath;
	// // return getShortURL(tempLocalPath);
	// }
	//
	// @Override
	// public void finalResult() {
	// }
	//
	// @Override
	// public void callback(Object result) {
	//
	// String shortPath = (String) result;
	// String finalPath = new String(tempLocalPath);
	// if (shortPath != null && shortPath.length() > 0) {
	// finalPath = shortPath;
	// }
	// intent.setDataAndType(Uri.parse(finalPath), "video/*");
	// LTDMediaApplication.G_TRACKER
	// .trackLiveStreamingEvent(LCVideo);
	// executeIntentForplayVideo(intent, finalPath,
	// LCVideo, activity);
	// }
	// };
	//
	// new BGWorkerExecuter(workToDone, activity, "Processing...",
	// true).execute();
	//
	// } else {
	// intent.setDataAndType(Uri.fromFile(file), "video/*");
	// executeIntentForplayVideo(intent, localPath, LCVideo,
	// activity);
	// }
	// } catch (Exception exception) {
	// DQDebugHelper.printAndTrackException(TAG , exception);
	// }
	// }
	//
	// // We are using this method to exectue only video intents. Audio intent
	// will
	// // be handled in LTDPlayerFactory
	// private static void executeIntentForplayVideo(Intent intent,
	// String localPath, LCVideo LCVideo, final Context context) {
	// try {
	// List<ResolveInfo> intents = context.getPackageManager()
	// .queryIntentActivities(intent,
	// PackageManager.MATCH_DEFAULT_ONLY);
	//
	// if (intents != null && intents.size() > 0) {
	// context.startActivity(intent);
	// } else {
	//
	// Intent videoIntent = new Intent(context,
	// CommonVideoPlayerActivity.class);
	// videoIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY
	// | Intent.FLAG_ACTIVITY_NO_USER_ACTION);
	// videoIntent.putExtra("localPath", localPath);
	// videoIntent.putExtra("title", LCVideo.getTitle());
	// CommonVideoPlayerActivity.LCVideo = LCVideo;
	// context.startActivity(videoIntent);
	// }
	// } catch (Exception exception) {
	// DQDebugHelper.printAndTrackException(TAG , exception);
	// }
	// }
	//
	// public static String[] getLTDIds(Context context) {
	// String[] ltdIdList = null;
	// SMUtility smUtility = SMUtility.getInstance(context);
	// SharedPreferences pref = smUtility.getSharedPreferences();
	// String ltdIds = pref.getString(SMConstants.LIST_OF_USER_NAMES, null);
	// if (ltdIds != null) {
	// ltdIdList = ltdIds.split(",");
	// }
	// return ltdIdList;
	//
	// }
	//
	// public static void saveLTDIdToIDList(Context context, String userName) {
	// String[] ltdIdList = null;
	// SMUtility smUtility = SMUtility.getInstance(context);
	// SharedPreferences pref = smUtility.getSharedPreferences();
	// String ltdIds = pref.getString(SMConstants.LIST_OF_USER_NAMES, "");
	// if (ltdIds != null) {
	// ltdIdList = ltdIds.split(",");
	// boolean flag = false;
	// for (String string : ltdIdList) {
	// if (string.equalsIgnoreCase(userName) == true) {
	// flag = true;
	// break;
	// }
	// }
	// if (flag == false || ltdIds == null) {
	// if (ltdIds == null || ltdIds.length() <= 0) {
	// smUtility.savePerefference(SMConstants.LIST_OF_USER_NAMES,
	// userName);
	// } else {
	// smUtility.savePerefference(SMConstants.LIST_OF_USER_NAMES,
	// ltdIds + "," + userName);
	// }
	// }
	// }
	//
	// }

	/**
	 * Format size.
	 * 
	 * @param expectedBytes
	 *            the expected bytes
	 * @return the string
	 */
	public static String formateSize(long expectedBytes) {

		String result = null;
		if (expectedBytes < 1024) {
			result = String.format("%d B", expectedBytes);
		} else if (expectedBytes < 1024 * 1024) {
			final float value = (float) expectedBytes / 1024;
			result = String.format("%1.2f KB", value);
		} else if (expectedBytes < 1024 * 1024 * 1024) {
			final float value = (float) expectedBytes / (1024 * 1024);
			result = String.format("%1.2f MB", value);
		} else {
			final float value = (float) expectedBytes / (1024 * 1024 * 1024);
			result = String.format("%1.2f GB", value);
		}
		return result;
	}

	/**
	 * Formate size.
	 * 
	 * @param expectedBytes
	 *            the expected bytes
	 * @return the string
	 */
	public static String formateSizeInMB(long expectedBytes) {

		String result = null;
		if (expectedBytes >= 1024 * 1024 * 1024) {
			final float value = (float) expectedBytes / (1024 * 1024 * 1024);
			result = String.format("%1.2f GB", value);

		} else {
			final float value = (float) expectedBytes / (1024 * 1024);
			result = String.format("%1.2f MB", value);
		}
		return result;
	}

	/**
	 * Sets the list view height based on children.
	 * 
	 * @param listView
	 *            the new list view height based on children
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {

		final ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			final View listItem = listAdapter.getView(i, null, listView);
			if (listItem instanceof ViewGroup) {
				listItem.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			}
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		final LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + listView.getDividerHeight()
				* (listAdapter.getCount() - 1);
		listView.setLayoutParams(params);
	}

	/**
	 * Check for updates message.
	 * 
	 * @param context
	 *            the context
	 */
	public static void checkForUpdatesMessage(final Context context) {

		// try {
		//
		// long updateType =
		// sharedPreferences.getLong(VersionUpgrade.UPDATE_AVAILABLE, -1);
		// final String urlLTDMedia =
		// sharedPreferences.getString(VersionUpgrade.UPDATE_URL, "");
		//
		// if (updateType == VersionUpgradeActivity.FORCE_UPDATE) {
		// Intent intent = new Intent(context, VersionUpgradeActivity.class);
		// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// context.startActivity(intent);
		// } else if (updateType == VersionUpgradeActivity.NORMAL_UPDATE) {
		// if (context != null) {
		//
		// AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setMessage(context.getString(R.string.msg_normal_updates_are_available))
		// .setCancelable(false)
		// .setPositiveButton("Update", new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int id) {
		// dialog.cancel();
		// dialog.dismiss();
		// Intent intent = new Intent(Intent.ACTION_VIEW);
		// intent.setData(Uri.parse(urlLTDMedia));
		// context.startActivity(intent);
		//
		// }
		// }).setNegativeButton("Ignore", new DialogInterface.OnClickListener()
		// {
		// public void onClick(DialogInterface dialog, int id) {
		// /*
		// * Ignore normal updates for three days.
		// */
		// long threeDaysTime = 3 * 24 * 60 * 60 * 1000;
		// long currentTime = System.currentTimeMillis();
		// long updateIgnoreTime = currentTime + threeDaysTime;
		// savePerefference(VersionUpgrade.UPDATE_IGNORE_TIME,
		// updateIgnoreTime);
		// dialog.cancel();
		// }
		// });
		//
		// AlertDialog alert = builder.create();
		// alert.show();
		//
		// }
		// } else if (updateType ==
		// VersionUpgradeActivity.NETWORK_NOT_AVAILABLE) {
		// if (context != null) {
		//
		// AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setMessage(context.getString(R.string.error_msg_network_not_reachable))
		// .setCancelable(false)
		// .setPositiveButton(context.getString(R.string.btn_try_again),
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int id) {
		// dialog.cancel();
		// dialog.dismiss();
		// AsyncVersionUpgradeTask asyncVersionUpgradeTask = new
		// AsyncVersionUpgradeTask(
		// context);
		// asyncVersionUpgradeTask.execute();
		//
		// }
		// })
		// .setNegativeButton(context.getString(R.string.btn_nevermind),
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int id) {
		// dialog.cancel();
		// dialog.dismiss();
		// }
		// });
		//
		// AlertDialog alert = builder.create();
		// alert.show();
		//
		// }
		// } else {
		// if (context != null) {
		//
		// AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setMessage(context.getString(R.string.msg_no_updates_available))
		// .setCancelable(false)
		// .setPositiveButton(context.getString(R.string.btn_ok),
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int id) {
		// dialog.cancel();
		// dialog.dismiss();
		// }
		// });
		//
		// AlertDialog alert = builder.create();
		// alert.show();
		//
		// }
		// }
		// } catch (Exception exception) {
		// DQDebugHelper.printAndTrackException(TAG , exception);
		// }

	}

	/**
	 * Conver string to date.
	 * 
	 * @param stringDate
	 *            the string date
	 * @return the date
	 */
	public static Date converStringToDate(String stringDate) {
		if (stringDate == null) {
			return null;
		}

		try {
			final SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");// ("yyyy-mm-ddTHH:mm:ss zzzz"");
			final Date date = dateFormat.parse(stringDate.substring(0, 18));
			return date;
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return null;
	}

	public static String getMonthNameAndDay(String mDateString) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");// ("yyyy-mm-ddTHH:mm:ss zzzz"");
		Date date = null;
		try {
			date = dateFormat.parse(mDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "" + new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date)
				+ ", " + date.getDate() + "/" + (date.getMonth() + 1);
	}

	/**
	 * Convert date to simple string.
	 * 
	 * @param sDate
	 *            the s date
	 * @return the string
	 */
	public static String convertDateToSimpleString(Date sDate) {

		try {
			final SimpleDateFormat dateFormat = new SimpleDateFormat(
					"MMM dd, yyyy");
			final String strDate = dateFormat.format(sDate);
			return strDate;
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return null;
	}

	/**
	 * Gets the full time estimations.
	 * 
	 * @param statTime
	 *            the stat time
	 * @return the full time estimations
	 */
	public static String getFullTimeEstimations(long statTime) {

		String timeRemainingStr = "";
		try {

			final long timeRemaining = System.currentTimeMillis() - statTime;
			if (timeRemaining < AppUtil.DAY) {
				long time = timeRemaining;
				final int hours = (int) (timeRemaining / AppUtil.HOUR);
				time = timeRemaining - hours * AppUtil.HOUR;
				final int minutes = (int) (time / AppUtil.MINT);
				timeRemainingStr = String.format("%d hours ago", timeRemaining
						/ AppUtil.HOUR, minutes);
			} else {
				long time = timeRemaining;
				final int days = (int) (timeRemaining / AppUtil.DAY);
				time = timeRemaining - days * AppUtil.DAY;
				final int hours = (int) (time / AppUtil.HOUR);
				time = time - hours * AppUtil.HOUR;
				final int mints = (int) (time / AppUtil.MINT);
				timeRemainingStr = String.format("%d Days, %d hours ago", days,
						hours, mints);
			}
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return timeRemainingStr;
	}

	public static String getHoursEstimations(long mPostTime) {
		SimpleDateFormat format = new SimpleDateFormat("h");
		String strhours = "";
		try {
			final long timeRemaining = System.currentTimeMillis() - mPostTime;
			strhours = format.format(timeRemaining);
			long fhours = Long.parseLong(strhours);
			if (fhours > 1) {
				strhours = strhours + " hours";
			} else {
				strhours = strhours + " hour";
			}
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return strhours;
	}

	/**
	 * Gets the theater time estimations.
	 * 
	 * @param date
	 *            the date
	 * @return the theater time estimations
	 */
	public static String getTheaterTimeEstimations(Date date) {

		final long statTime = date.getTime();
		String timeRemainingStr = "";
		try {

			final long timeRemaining = System.currentTimeMillis() - statTime;

			if (timeRemaining <= AppUtil.MINT * 10) {
				timeRemainingStr = "Just now";
			} else if (timeRemaining <= AppUtil.HOUR) {
				final int mints = (int) (timeRemaining / AppUtil.MINT);
				timeRemainingStr = String.format("%d minutes ago", mints);
			} else if (timeRemaining <= AppUtil.HOUR * 2) {
				timeRemainingStr = String.format("%d hour ago", 1);
			} else if (timeRemaining <= AppUtil.HOUR * 24) {
				final int hours = (int) (timeRemaining / AppUtil.HOUR);
				timeRemainingStr = String.format("%d hours ago", hours);
			} else if (timeRemaining <= AppUtil.DAY * 2) {
				timeRemainingStr = String.format("%d day ago", 1);
			} else if (timeRemaining <= AppUtil.DAY * 30) {
				final int days = (int) (timeRemaining / AppUtil.DAY);
				timeRemainingStr = String.format("%d days ago", days);
			} else {
				timeRemainingStr = AppUtil.convertDateToSimpleString(date);
			}
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return timeRemainingStr;
	}

	/**
	 * Format time.
	 * 
	 * @param mills
	 *            the mills
	 * @return the string
	 */
	public static String formatTime(long mills) {

		try {

			final int seconds = (int) (mills / 1000) % 60;
			final int minutes = (int) (mills / (1000 * 60) % 60);
			final int hours = (int) (mills / (1000 * 60 * 60) % 24);

			return String.format("%02d:%02d:%02d", hours, minutes, seconds);
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return "00:00:00";
	}

	/**
	 * Creates the user fb imag url.
	 * 
	 * @param userId
	 *            the user id
	 * @return the string
	 */
	public static String createUserFBImagUrl(String userId) {

		return "http://graph.facebook.com/" + userId
				+ "/picture?width=210&height=240";
	}

	/**
	 * Update user fonts.
	 * 
	 * @param context
	 *            the context
	 * @param root
	 *            the root
	 */
	public static void updateUserFonts(Context context, ViewGroup root) {

		try {
			final Typeface mFont = Typeface.createFromAsset(
					context.getAssets(), "fonts/18836_HELR45W.ttf");
			AppUtil.setFont(context, root, mFont);
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
	}

	/**
	 * Update user fonts.
	 * 
	 * @param context
	 *            the context
	 * @param root
	 *            the root
	 */
	public static void updateUserFonts(Context context, View root) {

		try {
			Typeface mFont = Typeface.createFromAsset(context.getAssets(),
					"fonts/18836_HELR45W.ttf");
			if (root instanceof ViewGroup) {
				AppUtil.setFont(context, (ViewGroup) root, mFont);
			} else if (root instanceof TextView) {
				final TextView curView = (TextView) root;
				if (curView.getTypeface() != null
						&& curView.getTypeface().isBold()) {
					mFont = Typeface.createFromAsset(context.getAssets(),
							"fonts/18923_HelveticaNeueHv.ttf");
					curView.setTypeface(mFont);
				} else {
					mFont = Typeface.createFromAsset(context.getAssets(),
							"fonts/18836_HELR45W.ttf");
					curView.setTypeface(mFont);
				}
			} else if (root instanceof Button) {
				final Button curView = (Button) root;
				if (curView.getTypeface() != null
						&& curView.getTypeface().isBold()) {
					mFont = Typeface.createFromAsset(context.getAssets(),
							"fonts/18923_HelveticaNeueHv.ttf");
					curView.setTypeface(mFont);
				} else {
					mFont = Typeface.createFromAsset(context.getAssets(),
							"fonts/18836_HELR45W.ttf");
					curView.setTypeface(mFont);
				}
			} else if (root instanceof ViewGroup) {
				AppUtil.setFont(context, (ViewGroup) root, mFont);
			}
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
	}

	/**
	 * Sets the font.
	 * 
	 * @param context
	 *            the context
	 * @param group
	 *            the group
	 * @param font
	 *            the font
	 */
	public static void setFont(Context context, ViewGroup group, Typeface font) {

		try {
			final int count = group.getChildCount();
			View v;
			for (int i = 0; i < count; i++) {
				v = group.getChildAt(i);
				if (v instanceof TextView) {
					final TextView curView = (TextView) v;
					if (curView.getTypeface() != null
							&& curView.getTypeface().isBold()) {
						font = Typeface.createFromAsset(context.getAssets(),
								"fonts/18923_HelveticaNeueHv.ttf");
						curView.setTypeface(font);
					} else {
						font = Typeface.createFromAsset(context.getAssets(),
								"fonts/18836_HELR45W.ttf");
						curView.setTypeface(font);
					}
				} else if (v instanceof Button) {
					final Button curView = (Button) v;
					if (curView.getTypeface() != null
							&& curView.getTypeface().isBold()) {
						font = Typeface.createFromAsset(context.getAssets(),
								"fonts/18923_HelveticaNeueHv.ttf");
						curView.setTypeface(font);
					} else {
						font = Typeface.createFromAsset(context.getAssets(),
								"fonts/18836_HELR45W.ttf");
						curView.setTypeface(font);
					}
				} else if (v instanceof ViewGroup) {
					AppUtil.setFont(context, (ViewGroup) v, font);
				}
			}
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
	}

	/**
	 * Creates the user fb id.
	 * 
	 * @param uid
	 *            the uid
	 * @return the string
	 */
	public static String createUserFBId(String uid) {

		return "" + uid + ".jpg";
	}

	public static String verifyTextFieldData(Context context, EditText field,
			boolean trimSpace, boolean canEmpty, boolean showError, int lenght) {

		String fieldName = null;
		String userText = null;
		String errorString = null;
		Resources resource = null;

		if (field != null) {
			if (context != null) {
				resource = context.getResources();
			}
			fieldName = (String) field.getTag();
			userText = field.getText().toString();
			if (userText == null || userText.length() <= 0) {
				userText = null;
				if (canEmpty) {
					errorString = resource
							.getString(R.string.lab_field_required);
				}
			} else {
				if (trimSpace) {
					userText = userText.trim();
					if (userText == null || userText.length() <= 0) {
						userText = null;
						if (canEmpty) {
							errorString = resource
									.getString(R.string.lab_field_invalid);
						}

					} else {
						if (lenght > -1 && userText.length() < lenght) {

							errorString = resource
									.getString(R.string.lab_filed_short_start)
									+ fieldName
									+ resource
											.getString(R.string.lab_filed_short_end);
						}
					}
				}
			}
			if (showError) {
				field.setError(errorString);
			}
		}
		return userText;
	}

	public static String verifyTextFieldData(Context context, EditText field) {

		return verifyTextFieldData(context, field, true, false, true, -1);
	}

	public static void updateButtonSatate(View view, boolean enable) {
		if (view != null) {
			view.setClickable(enable);
		}
	}

	public static String toInitCap(String param) {

		if (param != null && param.length() > 0) {
			char[] charArray = param.toCharArray(); // convert into char array
			charArray[0] = Character.toUpperCase(charArray[0]); // set capital
			// letter to
			// first
			// postion
			return new String(charArray); // return desired output
		} else {
			return "";
		}
	}

	/**
	 * Format milli seconds to time.
	 * 
	 * @param milliseconds
	 *            the milliseconds
	 * @return the string
	 */
	public static String formatMilliSecondsToTime(String milliseconds) {

		try {
			final Long mills = Long.parseLong(milliseconds);

			final int seconds = (int) (mills / 1000) % 60;
			final int minutes = (int) (mills / (1000 * 60) % 60);
			final int hours = (int) (mills / (1000 * 60 * 60) % 24);

			final String format = String.format("%02d", hours) + ":"
					+ String.format("%02d", minutes) + ":"
					+ String.format("%02d", seconds);

			return format;
		} catch (final Exception exception) {
			DebugHelper.trackException(TAG, exception);
		}
		return "00:00:00";
	}

	public static String formatLongToTime(long mills) {

		try {

			final double seconds = (mills / 1000);
			final int minutes = (int) (mills / (1000 * 60) % 60);
			final int hours = (int) (mills / (1000 * 60 * 60) % 24);
			StringBuilder builder = new StringBuilder();
			if (hours > 0) {
				builder.append(String.format("%.2d Hour ", hours));
			}
			if (minutes > 0) {
				builder.append(String.format("%.2d Mints ", minutes));
			}

			if (seconds > 0) {
				builder.append(String.format("%.2f Seconds ", seconds));
			}

			return builder.toString();
		} catch (final Exception exception) {
			DebugHelper.trackException(TAG, exception);
		}
		return "";
	}

	/**
	 * Format milli seconds to date.
	 * 
	 * @param milliseconds
	 *            the milliseconds
	 * @param dateFormat
	 *            the date format
	 * @return the string
	 */
	public static String formatMilliSecondsToDate(final String milliseconds,
			final String dateFormat) {

		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			final Long mills = Long.parseLong(milliseconds);
			final Date d = new Date(mills * 1000);
			df = new SimpleDateFormat(dateFormat);
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			return df.format(d);
		} catch (final Exception exception) {
			DebugHelper.trackException(TAG, exception);
		}
		final Date d = new Date();
		return df.format(d);
	}

	/**
	 * Join.
	 * 
	 * @param array
	 *            the array
	 * @param separator
	 *            the separator
	 * @param startIndex
	 *            the start index
	 * @param endIndex
	 *            the end index
	 * @return the string
	 */
	public static String join(final Object[] array, String separator,
			final int startIndex, final int endIndex) {

		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = "";
		}
		int bufSize = endIndex - startIndex;
		if (bufSize <= 0) {
			return "";
		}

		bufSize *= (array[startIndex] == null ? 16 : array[startIndex]
				.toString().length()) + separator.length();

		final StringBuffer buf = new StringBuffer(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * Sets the font.
	 * 
	 * @param view
	 *            the view
	 * @param fontName
	 *            the font name
	 */
	public static void setFont(final View view, final String fontName) {

		if (view != null) {
			if (view instanceof TextView) {
				final Typeface myTypeFace = Typeface
						.createFromAsset(KisafaApplication.getAppContext()
								.getAssets(), fontName);
				((TextView) view).setTypeface(myTypeFace);
			} else if (view instanceof Button) {
				final Typeface myTypeFace = Typeface
						.createFromAsset(KisafaApplication.getAppContext()
								.getAssets(), fontName);
				((Button) view).setTypeface(myTypeFace);
			}

		}
	}

	/**
	 * Make first word capital.
	 * 
	 * @param sentence
	 *            the sentence
	 * @return the string
	 */
	public static String makeFirstWordCapital(final String sentence) {

		final StringBuilder result = new StringBuilder(sentence.length());
		final String[] words = sentence.split("\\s");
		for (int i = 0, l = words.length; i < l; ++i) {
			if (i > 0) {
				result.append(" ");
			}
			result.append(Character.toUpperCase(words[i].charAt(0))).append(
					words[i].substring(1));

		}
		return result.toString();
	}

	/**
	 * Human readable byte count.
	 * 
	 * @param bytes
	 *            the bytes
	 * @param si
	 *            the si
	 * @return the string
	 */
	public static String humanReadableByteCount(final long bytes,
			final boolean si) {

		final int unit = si ? 1000 : 1024;
		if (bytes < unit) {
			return bytes + " B";
		}
		final int exp = (int) (Math.log(bytes) / Math.log(unit));
		final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1)
				+ (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	/**
	 * Format upload percentage.
	 * 
	 * @param url
	 *            the url
	 * @param uploadedBytes
	 *            the uploaded bytes
	 * @return the string
	 */
	public static String formatUploadPercentage(final String url,
			final long uploadedBytes) {

		try {
			final File f = new File(url);
			final long contentLength = f.length();
			final double progressPercentage = (double) uploadedBytes
					/ contentLength * 100;
			int p = (int) Math.floor(progressPercentage);
			if (p > 100) {
				p = 100;
			}
			return String.valueOf(p);
		} catch (final Exception exception) {
			DebugHelper.trackException(TAG, exception);
		}
		return null;
	}

	/**
	 * Format string.
	 * 
	 * @param value
	 *            the value
	 * @param literalToReplace
	 *            the literal to replace
	 * @param arguments
	 *            the arguments
	 * @return the string
	 */
	public static String formatString(String value, String literalToReplace,
			String arguments) {

		return value.replaceAll(literalToReplace, arguments);
	}

	public static String getCurrentDateTime() {

		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String getMonthYearFromDateTime(String date, boolean isYoutube) {
		String desireDate = "";
		SimpleDateFormat sdf = null;
		try {

			if (isYoutube) {
				sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT_ZONE);
			} else {
				sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT_VIMEO);
			}

			sdf.setTimeZone(TimeZone.getDefault());
			Date formatDate = sdf.parse(date);
			sdf = new SimpleDateFormat("MMMM yyyy");
			desireDate = sdf.format(formatDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desireDate;
	}

	public static String getCurrentDateTimeForRefresh() {

		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_FOR_PULL);
		sdf.setTimeZone(TimeZone.getDefault());
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String getCurrentTimeForChat() {

		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.TIME_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String convertInSimpleDateTime(Date sDate) {

		if (sDate == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(sDate);
		return strDate;
	}

	public static String convertToCompletedDateTime(Date sDate) {

		if (sDate == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd:mm:yyyy");
		String strDate = sdf.format(sDate);
		return strDate;
	}

	public static Date convertStringToDate(String stringDate) {

		if (stringDate == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(stringDate);
		} catch (Exception e) {
			DebugHelper.printException(e);
		}
		return date;
	}

	public static Date convertStringToDateDB(String stringDate) {

		SimpleDateFormat sdf = new SimpleDateFormat(AppConstants.DATE_DB_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(stringDate);
		} catch (Exception e) {
			DebugHelper.printException(e);
		}
		return date;
	}

	public static Bitmap getBitmapFromView(View view) {

		Bitmap returnedBitmap = null;
		try {

			returnedBitmap = Bitmap.createBitmap(view.getWidth(),
					view.getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(returnedBitmap);
			Drawable bgDrawable = view.getBackground();
			if (bgDrawable != null)
				bgDrawable.draw(canvas);
			else
				canvas.drawColor(Color.WHITE);
			view.draw(canvas);
		} catch (Exception exception) {
			DebugHelper.printException(TAG, exception);
		}

		return returnedBitmap;
	}

	public static boolean isEmailValid(String email) {

		boolean isValid = false;
		try {
			// String expression =
			// "^[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$";
			String expression = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			CharSequence inputStr = email;

			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (matcher.matches()) {
				isValid = true;
			}
		} catch (Exception e) {
			Log.w(TAG, "isEmailValid Message = " + e.toString());
			DebugHelper.printException(e);
		}

		return isValid;
	}

	// public static String getTimeEstimations(long statTime) {
	//
	// String timeRemainingStr = "";
	// try {
	// long currentTime = System.currentTimeMillis() / 1000;
	// long timeRemaining = currentTime - statTime;
	// //timeRemaining = statTime;
	// if (timeRemaining < 0) {
	// timeRemainingStr = "Just Now";
	// return timeRemainingStr;
	// }
	// if (timeRemaining < AppUtil.MINT) {
	// long sec = timeRemaining / AppUtil.SECOND;
	// timeRemainingStr = String.format("%d "
	// + (sec > 1 ? "seconds" : "second"), sec);
	// } else if (timeRemaining < AppUtil.HOUR) {
	// long mint = timeRemaining / AppUtil.MINT;
	// timeRemainingStr = String.format("%d "
	// + (mint > 1 ? "minutes" : "minute"), mint);
	//
	// } else if (timeRemaining < AppUtil.DAY) {
	// long hours = timeRemaining / AppUtil.HOUR;
	// timeRemainingStr = String.format("%d "
	// + (hours > 1 ? "hours" : "hour"), hours);
	// } else if (timeRemaining < AppUtil.WEEK) {
	// long days = timeRemaining / AppUtil.DAY;
	// if (days == 1) {
	// timeRemainingStr = "Yesterday";
	// } else {
	// timeRemainingStr = String.format("%d "
	// + (days > 1 ? "days" : "day"), days);
	// }
	// } else if (timeRemaining < AppUtil.MONTH) {
	// long week = timeRemaining / AppUtil.WEEK;
	// timeRemainingStr = String.format("%d "
	// + (week > 1 ? "weeks" : "week"), week);
	// } else if (timeRemaining < AppUtil.YEAR) {
	// long months = timeRemaining / AppUtil.MONTH;
	// timeRemainingStr = String.format("%d "
	// + (months > 1 ? "months" : "month"), months);
	// } else {
	// long year = timeRemaining / AppUtil.YEAR;
	// timeRemainingStr = String.format("%d "
	// + (year > 1 ? "years" : "year"), year);
	// }
	// timeRemainingStr = timeRemainingStr + " ago";
	// } catch (final Exception exception) {
	// DebugHelper.trackException(AppUtil.TAG, exception);
	// }
	// return timeRemainingStr;
	// }

	public static String getTimeEstimations(long statTime) {

		String timeRemainingStr = "";
		try {
			long currentTime = System.currentTimeMillis() / 1000;
			long timeRemaining = currentTime - statTime;
			timeRemaining = timeRemaining * AppUtil.SECOND;
			if (timeRemaining < 0) {
				// timeRemainingStr = "Just Now";
				timeRemainingStr = "1 mintue ago";
				return timeRemainingStr;
			}
			if (timeRemaining < AppUtil.MINT) {
				long sec = timeRemaining / AppUtil.SECOND;
				timeRemainingStr = String.format("%d "
						+ (sec > 1 ? "seconds" : "second"), sec);
			} else if (timeRemaining < AppUtil.HOUR) {
				long mint = timeRemaining / AppUtil.MINT;
				timeRemainingStr = String.format("%d "
						+ (mint > 1 ? "minutes" : "minute"), mint);

			} else if (timeRemaining < AppUtil.DAY) {
				long hours = timeRemaining / AppUtil.HOUR;
				timeRemainingStr = String.format("%d "
						+ (hours > 1 ? "hours" : "hour"), hours);
			} else if (timeRemaining < AppUtil.WEEK) {
				long days = timeRemaining / AppUtil.DAY;
				if (days == 1) {
					timeRemainingStr = "Yesterday";
				} else {
					timeRemainingStr = String.format("%d "
							+ (days > 1 ? "days" : "day"), days);
				}
			} else if (timeRemaining < AppUtil.MONTH) {
				long week = timeRemaining / AppUtil.WEEK;
				timeRemainingStr = String.format("%d "
						+ (week > 1 ? "weeks" : "week"), week);
			} else if (timeRemaining < AppUtil.YEAR) {
				long months = timeRemaining / AppUtil.MONTH;
				timeRemainingStr = String.format("%d "
						+ (months > 1 ? "months" : "month"), months);
			} else {
				long year = timeRemaining / AppUtil.YEAR;
				timeRemainingStr = String.format("%d "
						+ (year > 1 ? "years" : "year"), year);
			}
			if (!timeRemainingStr.equals("Yesterday")) {
				timeRemainingStr = timeRemainingStr + " ago";
			}
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return timeRemainingStr;
	}

	public static String getValueAppendByComma(long value) {
		// TODO Auto-generated method stub
		int count = 0;
		String number = "" + value;
		StringBuilder numberFormat = new StringBuilder();
		if (number.length() > 3) {
			for (int i = number.length() - 1; i >= 0; i--) {
				count++;
				numberFormat.append(number.charAt(i));
				if (count % 3 == 0 && i > 0)
					numberFormat.append(",");
			}
			numberFormat.reverse();
			number = numberFormat.toString();
		}
		return number;
	}

	public static String getValueAppendByComma(double value) {
		// TODO Auto-generated method stub
		int count = 0;
		String number = "" + (long) value;
		StringBuilder numberFormat = new StringBuilder();
		if (number.length() > 3) {
			for (int i = number.length() - 1; i >= 0; i--) {
				count++;
				numberFormat.append(number.charAt(i));
				if (count % 3 == 0 && i > 0)
					numberFormat.append(",");
			}
			numberFormat.reverse();
			number = numberFormat.toString();
		}
		return number;
	}

	public static String formatCounts(String number) {
		return formatCounts(Double.parseDouble(number));
	}

	public static String formatCounts(double value) {
		int power;
		String suffix = " kmbt";
		String formattedNumber = "";

		NumberFormat formatter = new DecimalFormat("#,###.#");
		power = (int) StrictMath.log10(value);
		value = value / (Math.pow(10, (power / 3) * 3));
		formattedNumber = formatter.format(value);
		formattedNumber = formattedNumber + suffix.charAt(power / 3);
		return formattedNumber.length() > 4 ? formattedNumber.replaceAll(
				"\\.[0-9]+", "") : formattedNumber;
	}

	public static String getCurrentDateOrTime(long milliseconds) {
		Date msgDate = new Date(milliseconds * 1000L);
		DateFormat msgDF = new SimpleDateFormat("MMM dd");

		Date currentDate = new Date(System.currentTimeMillis());
		if (msgDF.format(msgDate).equals(msgDF.format(currentDate))) {
			DateFormat hours = new SimpleDateFormat("KK:mm aa");
			return hours.format(msgDate);
		}
		return msgDF.format(msgDate);
	}

	@SuppressWarnings("unused")
	public static String getTimeStampInDate(String timestamp) {
		String[] dateTime = timestamp.split(" ");
		String date = dateTime[0]; // 004
		String time = dateTime[1]; // 034556

		String[] yyyyMMdd = date.split("-");
		String yyyy = yyyyMMdd[0]; // 004
		String mm = yyyyMMdd[1]; // 034556
		String dd = yyyyMMdd[2]; // 034556

		String[] hhMMss = time.split(":");
		String hh = hhMMss[0]; // 004
		String min = hhMMss[1]; // 034556
		String ss = hhMMss[2]; // 034556

		// Date timestampInDate = new Date(Integer.parseInt(yyyy),
		// Integer.parseInt(mm), Integer.parseInt(dd), Integer.parseInt(hh),
		// Integer.parseInt(min), Integer.parseInt(ss));
		// return getTime(timestampInDate.getTime()/10000);
		return getMonth(mm) + " " + dd;
	}

	@SuppressWarnings("unused")
	public static String getTimeStampInYear(String timestamp) {
		String[] dateTime = timestamp.split(" ");
		String date = dateTime[0]; // 004
		String time = dateTime[1]; // 034556

		String[] yyyyMMdd = date.split("-");
		String yyyy = yyyyMMdd[0]; // 004
		String mm = yyyyMMdd[1]; // 034556
		String dd = yyyyMMdd[2]; // 034556

		String[] hhMMss = time.split(":");
		String hh = hhMMss[0]; // 004
		String min = hhMMss[1]; // 034556
		String ss = hhMMss[2]; // 034556

		// Date timestampInDate = new Date(Integer.parseInt(yyyy),
		// Integer.parseInt(mm), Integer.parseInt(dd), Integer.parseInt(hh),
		// Integer.parseInt(min), Integer.parseInt(ss));
		// return getTime(timestampInDate.getTime()/10000);
		return yyyy;
	}

	public static String getMonth(String monthNo) {
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		return months[Integer.parseInt(monthNo) - 1];
	}

	public static String getTime(long statTime) {

		String timeRemainingStr = "";
		try {
			long currentTime = System.currentTimeMillis() / 1000;
			long timeRemaining = currentTime - statTime;
			timeRemaining = timeRemaining * AppUtil.SECOND;
			if (timeRemaining < 0) {
				timeRemainingStr = "Just Now";
				return timeRemainingStr;
			}
			if (timeRemaining < AppUtil.MINT) {
				long sec = timeRemaining / AppUtil.SECOND;
				timeRemainingStr = String.format("%d "
						+ (sec > 1 ? "seconds" : "second"), sec);
			} else if (timeRemaining < AppUtil.HOUR) {
				long mint = timeRemaining / AppUtil.MINT;
				timeRemainingStr = String.format("%d "
						+ (mint > 1 ? "minutes" : "minute"), mint);

			} else if (timeRemaining < AppUtil.DAY) {
				long hours = timeRemaining / AppUtil.HOUR;
				timeRemainingStr = String.format("%d "
						+ (hours > 1 ? "hours" : "hour"), hours);
			} else if (timeRemaining < AppUtil.WEEK) {
				long days = timeRemaining / AppUtil.DAY;
				if (days == 1) {
					timeRemainingStr = "Yesterday";
				} else {
					timeRemainingStr = String.format("%d "
							+ (days > 1 ? "days" : "day"), days);
				}
			} else if (timeRemaining < AppUtil.MONTH) {
				long week = timeRemaining / AppUtil.WEEK;
				timeRemainingStr = String.format("%d "
						+ (week > 1 ? "weeks" : "week"), week);
			} else if (timeRemaining < AppUtil.YEAR) {
				long months = timeRemaining / AppUtil.MONTH;
				timeRemainingStr = String.format("%d "
						+ (months > 1 ? "months" : "month"), months);
			} else {
				long year = timeRemaining / AppUtil.YEAR;
				timeRemainingStr = String.format("%d "
						+ (year > 1 ? "years" : "year"), year);
			}
			timeRemainingStr = timeRemainingStr + " ago";
		} catch (final Exception exception) {
			DebugHelper.trackException(AppUtil.TAG, exception);
		}
		return timeRemainingStr;
	}

	public static void hideKeyBoard(Context context, View editText) {

		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

	}

	/**
	 * Hide soft keyboard.
	 * 
	 * @param callingActivity
	 *            the calling activity
	 */
	public static void hideSoftKeyboard(Activity callingActivity) {
		if (callingActivity.getCurrentFocus() != null) {
			InputMethodManager inputManager = (InputMethodManager) callingActivity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(callingActivity
					.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * Return date in specified format.
	 * 
	 * @param milliSeconds
	 *            Date in milliseconds
	 * @param dateFormat
	 *            Date format
	 * @return String representing date in specified format
	 */
	public static String getDateFromMiliSeconds(double milliSeconds,
			String dateFormat) {
		// Create a DateFormatter object for displaying date in specified
		// format.
		long lng = Double.valueOf("" + milliSeconds).longValue();
		// lng=(System.currentTimeMillis()*1000)-lng;
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in
		// milliseconds to date.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(lng * 1000);
		return formatter.format(calendar.getTime());
	}

	/**
	 * Show soft keyboard.
	 * 
	 * @param callActivity
	 *            the call activity
	 * @param view
	 *            the view
	 */
	public static void showSoftKeyboard(Activity callActivity, View view) {
		InputMethodManager imm = (InputMethodManager) callActivity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
	}

	/**
	 * Mathod to get date in message center required format. Like Today,
	 * Yesterday and then in the format like Mon, 12 Mar 2015.
	 * 
	 * @param dateTime
	 * @param addTimeForTodayOrYest
	 * @return
	 */
	public static String getDateTagWithTime(String dateTime,
			boolean addTimeForTodayOrYest) {
		String dateOld = "";
		SimpleDateFormat formatCurr = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
		if (dateTime.length() > 18)
			dateTime = dateTime.substring(0, 18);
		else if (dateTime.length() < 13)
			formatCurr = new SimpleDateFormat("MMM dd, yyyy");
		try {

			String RFC1123_DATE_PATTERN = "EEE, dd MMM yyyy";
			SimpleDateFormat formatOldDate = new SimpleDateFormat(
					RFC1123_DATE_PATTERN);

			String dateStarter = "";
			Date date = formatCurr.parse(dateTime);
			Calendar calender = Calendar.getInstance();
			int currDate = calender.get(Calendar.DAY_OF_MONTH);
			if (date.getDate() == currDate
					&& date.getMonth() == calender.get(Calendar.MONTH)
					&& date.getYear() == calender.get(Calendar.YEAR) - 1900) {
				dateStarter = "Today ";

				if (addTimeForTodayOrYest) {
					String time = convertDateTimeTo12hourFormat(dateTime);
					return dateStarter + time.toString();
				}
				return dateStarter;
			}

			calender.add(Calendar.DAY_OF_MONTH, -1);
			int yesterDay = calender.get(Calendar.DAY_OF_MONTH);

			if (date.getDate() == yesterDay
					&& date.getMonth() == calender.get(Calendar.MONTH)
					&& date.getYear() == calender.get(Calendar.YEAR) - 1900) {
				dateStarter = "Yesterday ";

				if (addTimeForTodayOrYest) {
					String time = convertDateTimeTo12hourFormat(dateTime);
					return dateStarter + time.toString();
				}
				return dateStarter;
			}

			dateOld = formatOldDate.format(date);

		} catch (Exception e) {
			Log.e(TAG, "Exception", e);
		}
		return dateOld;
	}

	/**
	 * Mathod to find out weather two given dates fall into same calendar day or
	 * not.
	 * 
	 * @param prev
	 *            date
	 * @param curr
	 *            date
	 * @return
	 */
	public static boolean isDateFallInSameDay(Date prev, Date curr) {
		if (prev == null)
			return false;
		if (prev != null && curr != null) {
			Calendar calCur = Calendar.getInstance();
			calCur.setTime(curr);
			Calendar calPrev = Calendar.getInstance();
			calPrev.setTime(prev);
			if (calCur.get(Calendar.YEAR) == calPrev.get(Calendar.YEAR)
					&& calCur.get(Calendar.MONTH) == calPrev
							.get(Calendar.MONTH)
					&& calCur.get(Calendar.DAY_OF_MONTH) == calPrev
							.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		}
		return false;
	}

	public static String getCurrentDateTimeString() {
		SimpleDateFormat formatCurr = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
		return formatCurr.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * This method converts dp unit to equivalent pixels, depending on device
	 * density.
	 * 
	 * @param dp
	 *            A value in dp (density independent pixels) unit. Which we need
	 *            to convert into pixels
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on
	 *         device density
	 */
	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	/**
	 * This method converts device specific pixels to density independent
	 * pixels.
	 * 
	 * @param px
	 *            A value in px (pixels) unit. Which we need to convert into db
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}
}
