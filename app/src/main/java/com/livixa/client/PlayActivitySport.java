package com.livixa.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.appconfig.AppKeys.LANGUAGES;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.other.AllVideoCheckActivity;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.client.R;
import com.livixa.client.BridgeService.DateTimeInterface;
import com.livixa.client.BridgeService.PlayInterface;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.AudioPlayer;
import object.p2pipcam.utils.CustomAudioRecorder;
import object.p2pipcam.utils.CustomBuffer;
import object.p2pipcam.utils.CustomBufferData;
import object.p2pipcam.utils.CustomBufferHead;
import object.p2pipcam.utils.DataBaseHelper;
import object.p2pipcam.utils.TakeVideoThread;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PlayActivitySport extends BaseActivity implements OnTouchListener,
		OnGestureListener, CustomAudioRecorder.AudioRecordResult,
		OnClickListener, OnDoubleTapListener, PlayInterface, DateTimeInterface {

	private static final String LOG_TAG = "PlayActivity";

	private static final int AUDIO_BUFFER_START_CODE = 0xff00ff;

	private SurfaceView playSurface = null;
	private SurfaceHolder playHolder = null;
	private byte[] videodata = null;
	private int videoDataLen = 0;
	private int nVideoWidth = 0;
	private int nVideoHeight = 0;

	private View progressView = null;
	private boolean bProgress = true;

	@SuppressWarnings("deprecation")
	private GestureDetector gt = new GestureDetector(this);
	@SuppressWarnings("unused")
	private int nSurfaceHeight = 0;
	@SuppressWarnings("unused")
	private int nSurfaceWidth = 0;
	private int nResolution = 0;
	private int nBrightness = 0;
	private int nContrast = 0;
	@SuppressWarnings("unused")
	private int nMode = 0;
	@SuppressWarnings("unused")
	private int nFlip = 0;
	@SuppressWarnings("unused")
	private int nFramerate = 0;
	private boolean bInitCameraParam = false;
	private boolean bManualExit = false;

	private TextView textosd = null;
	private TextView textTimeStamp = null;
	private String strName = null;
	private String strDID = null;
	private String strUser = null;
	private String strPwd = null;
	private int kmode = 0;
	private int streamType = ContentCommon.MJPEG_SUB_STREAM;

	private View osdView = null;

	private boolean bDisplayFinished = true;
	private surfaceCallback videoCallback = new surfaceCallback();

	private int nPlayCount = 0;

	private CustomBuffer AudioBuffer = null;
	private AudioPlayer audioPlayer = null;
	private boolean bAudioStart = false;
	private CustomAudioRecorder customAudioRecorder = null;
	private boolean bAudioRecordStart = false;
	@SuppressWarnings("unused")
	private boolean bGetStreamCodecType = false;
	private int nStreamCodecType;
	private int nP2PMode = ContentCommon.PPPP_MODE_P2P_NORMAL;
	// private TextView textTimeoutTextView = null;
	@SuppressWarnings("unused")
	private boolean bTimeoutStarted = false;
	@SuppressWarnings("unused")
	private int nTimeoutRemain = 180;
	// add
	private boolean isTakeVideo = false;
	@SuppressWarnings("unused")
	private long videotime = 0;
	private boolean isLeftRight = false;
	private boolean isUpDown = false;
	private PopupWindow mPopupWindowProgress;
	private final int BRIGHT = 1;
	private final int CONTRAST = 2;
	@SuppressWarnings("unused")
	private boolean isHorizontalMirror = false;
	@SuppressWarnings("unused")
	private boolean isVerticalMirror = false;
	private boolean isUpDownPressed = false;
	private boolean isShowtoping = false;
	private ImageView vidoeView;
	private DataBaseHelper helper;
	private ImageView videoViewStandard;
	// private View ptzOtherSetView;
	private ImageButton ptzAudio;
	private ImageButton ptzMicrophone;
	@SuppressWarnings("unused")
	private ImageButton ptzPlayMode;
	private Button ptzResolutoin;
	private Animation showAnim;
	private boolean isTalking = false;
	private boolean isTakepic = false;
	private boolean isMcriophone = false;
	@SuppressWarnings("unused")
	private boolean isExit = false;

	private PopupWindow resolutionPopWindow;
	private Animation dismissAnim;
	private View ptzOtherSetAnimView;
	private boolean isH264 = false;
	// video
	@SuppressWarnings("unused")
	private TextView textViewVideoing = null;
	// private final int TAKEVIDEO = 10000;
	private boolean isPictSave = false;
	private ImageButton checkBoxHS = null;
	private String status = null;
	private StatFs stat;
	private long blockSize;
	private long totalBlocks;
	private long availableBlocks;
	private String sdSize = null;
	@SuppressWarnings("unused")
	private String sdAvail = null;
	private TextView textView_sdsize = null;
	private File path = null;
	private LinearLayout layout_videoing = null;
	private ImageButton button_back = null;
	@SuppressWarnings("unused")
	private int widthV = 0;
	@SuppressWarnings("unused")
	private int heightV = 0;
	@SuppressWarnings("unused")
	private int talkAudio = 0;
	@SuppressWarnings("unused")
	private int tagone = 0;
	private boolean m_bUpDownMirror;
	private boolean m_bLeftRightMirror;
	private boolean m_bGpioOn=false;
	private boolean m_bFrequencyOn=false;


	private ImageButton led_open;
	private PopupWindow popupWindow_about, popupWindow_frame, popupWindow_re,
			popupWindow_in, popupWindow_bit,popupWindow_frequency,popupWindow_gpio;
	private Button buttonPreset;
	private View popv, popv_frame, popv_re, popv_infrared, popv_bit,popv_frequency,popv_gpio;
	private ViewPager mPager;
	private List<View> listViews;
	private ImageView cursorxx;
	private TextView t1, t2;
	private int offset = 0;
	private int currIndex = 0;
	private int bmpW;
	private Button buttonpre1, buttonpre2, buttonpre3, buttonpre4, buttonpre5,
			buttonpre6, buttonpre7, buttonpre8, buttonpre9, buttonpre10,
			buttonpre11, buttonpre12, buttonpre13, buttonpre14, buttonpre15,
			buttonpre16;
	private Button buttonprec1, buttonprec2, buttonprec3, buttonprec4,
			buttonprec5, buttonprec6, buttonprec7, buttonprec8, buttonprec9,
			buttonprec10, buttonprec11, buttonprec12, buttonprec13,
			buttonprec14, buttonprec15, buttonprec16;
	private ImageButton imageButton_frequency;
	private Button button_frame1, button_frame2, button_frame3, button_frame4,
			button_frame5, button_frame6, Button_quality,ptz_frame;
	private int frame;
	private Button button_re1, button_re2, button_re3;
	private Button button_i1, button_i2,button_i3;
	private Button button_bit1, button_bit2;
	private Button button_frq50, button_frq60;
	private Button button_gpio_open, button_gpio_close;
	private PopupWindow popupWindow_show_you = null;
	private TextView textView_show;
	private boolean lefRitUpDowTag = false;
	private ImageButton button_say;
	private Animation animationButtonSay;
	@SuppressWarnings("unused")
	private TextView textViewKPS, textViewWH;
	@SuppressWarnings("unused")
	private int sizeKbps;
	@SuppressWarnings("unused")
	private boolean isKBPS = false;
	@SuppressWarnings("unused")
	private LinearLayout linea_show_kbps;
	@SuppressWarnings("unused")
	private int Kbps = 5;
	// ==============test=================
	Bitmap bitmap11;
	Bitmap bitmap22;
	int nVideoWidth11 = 0;
	int nVideoHeight22 = 0;
	byte[] sufVideoBytes;
	int p1 = 0;
	int p2 = 0;
	int xxxxxxxxxxx = 0;
	boolean mbLoop = false;
	boolean ifDrawOver = false;
	int one = 0;
	int two = 0;
	private String tzStr = "GMT+08:00";
	@SuppressWarnings("unused")
	private TextView connecting_show;
	@SuppressWarnings("unused")
	private String timeshow = " ";
	@SuppressWarnings("unused")
	private long time, time1;
	private ProgressBar ptz_takevideo_progress;
	private boolean isOneShow = true;
	private int i1 = 0;
	private int i2 = 0;
	boolean exit = false;
	private TakeVideoThread takeVideoThread = null;
	private LinearLayout layout_vctscape;
	private RelativeLayout layout_landscape;

	private ImageButton imgUp = null;
	private ImageButton imgDown = null;
	private ImageButton imgRight = null;
	private ImageButton imgLeft = null;
	private ImageButton imgUp1 = null;
	private ImageButton imgDown1 = null;
	private ImageButton imgRight1 = null;
	private ImageButton imgLeft1 = null;
	private ImageButton play_more = null;
	private boolean isDateComeOn = false;

	class MyThread extends Thread {
		@Override
		public void run() {
			while (exit == true) {
				i2 = i1;
				Log.d("test", "shix-sleep");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				streamType = 10;
				if (exit == true) {
					NativeCaller.StartPPPPLivestream(strDID, streamType);
				} else {
					break;
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (i2 == i1) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// Toast.makeText(getApplicationContext(),
							// R.string.pppp_status_disconnect,
							// Toast.LENGTH_SHORT).show();
							Log.d("test", "disconnect--finish");
							//PlayActivitySport.this.finish();
							
							playSurface.postDelayed(new Runnable() {
								
								@Override
								public void run() {
									
									Intent intent = new Intent(PlayActivitySport.this,
											MainActivity.class);
									startActivity(intent);
									finish();
								}
							}, 500);
							
							
							exit=false;
							
							
							//KisafaApplication.perFormActivityBackTransition(PlayActivitySport.this);
							/*overridePendingTransition(R.anim.out_to_right,
									R.anim.in_from_left);// �˳�����
*/						}
					});
				}
			}
			super.run();
		}
	}

	private String formatSize(long size) {
		return Formatter.formatFileSize(this, size);
	}

	private Handler MyTakeVideoHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// case TAKEVIDEO:
			// if ((msg.arg1 * 50) % 1000 == 0) {
			// textViewVideoing.setText((msg.arg1 * 50) / 1000 + "");
			// }
			// break;
			case 1005:
				textView_sdsize.setText(msg.getData().getString("sd_size")
						+ " / " + sdSize);
				Log.d("tagg", msg.getData().getString("sd_size"));
				break;
			default:
				break;
			}
		};
	};

	private class surfaceCallback implements SurfaceHolder.Callback {
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			if (holder == playHolder) {
				streamType = 10;
				NativeCaller.StartPPPPLivestream(strDID, streamType);
			}
		}

		public void surfaceCreated(SurfaceHolder holder) {
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			// finish();
		}
	}

	private void returnLastBmp2Home() {
		new Thread() {
			public void run() {
				if (isH264) {
					byte[] rgb = new byte[nVideoWidth * nVideoHeight * 2];
					NativeCaller.YUV4202RGB565(videodata, rgb, nVideoWidth,
							nVideoHeight);
					ByteBuffer buffer = ByteBuffer.wrap(rgb);
					rgb = null;
					mBmp = Bitmap.createBitmap(nVideoWidth, nVideoHeight,
							Bitmap.Config.RGB_565);
					mBmp.copyPixelsFromBuffer(buffer);
				}
				if (mBmp != null) {
					int btmWidth = mBmp.getWidth();
					int btmHeight = mBmp.getHeight();
					float scaleW = ((float) 70) / btmWidth;
					float scaleH = ((float) 50) / btmHeight;
					Matrix matrix = new Matrix();
					matrix.postScale(scaleW, scaleH);
					Bitmap bmp = Bitmap.createBitmap(mBmp, 0, 0, btmWidth,
							btmHeight, matrix, true);
					File div1 = new File(
							Environment.getExternalStorageDirectory(),
							ContentCommon.SDCARD_PATH + "/picid");
					File file = new File(div1, strDID + ".jpg");
					if (file.exists()) {
						Log.d("first_pic", file.delete() + "");
					}
					saveBmpToSDcard(strDID, bmp);
				}
			}
		}.start();
	}

	private void saveBmpToSDcard(String did, Bitmap bitmap) {
		FileOutputStream fos = null;
		File div = new File(Environment.getExternalStorageDirectory(),
				ContentCommon.SDCARD_PATH + "/picid");
		if (!div.exists()) {
			div.mkdirs();
		}
		try {
			File file = new File(div, did + ".jpg");
			fos = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)) {
				fos.flush();
				fos.close();
				Cursor cursor = helper.queryFirstpic(did);
				if (cursor.getCount() <= 0) {
					String path = file.getAbsolutePath();
					helper.addFirstpic(did, path);
				}
				if (cursor != null) {
					cursor.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showSureDialogPlay(Context context) {
		
		
		
		
final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(PlayActivitySport.this,getString(R.string.exit_play_show));
		
		myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
			
			@Override
			public void onCustomDialoguePositiveClick() {
				
				myASlertDialog.dismiss();
				
				new AsyncTask<Void, Void, Void>() {
					protected void onPreExecute() {
						if (mPopupWindowProgress != null
								&& mPopupWindowProgress.isShowing()) {
							mPopupWindowProgress.dismiss();
						}
						if (popupWindow_show_you != null
								&& popupWindow_show_you.isShowing()) {
							popupWindow_show_you.dismiss();
						}
					};

					@Override
					protected Void doInBackground(Void... params) {

						if (myRender != null) {
							myRender.destroyShaders();
						}
						mbLoop = false;
						isKBPS = false;
						talkAudio = 0;
						tagone = 0;
						isTakeVideo = false;
						one = 0;
						bitmap22 = null;
						p1 = 0;
						StopAudio();
						StopTalk();
						releaseTalk();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}

					protected void onPostExecute(Void result) {
						
						
						Intent intent = new Intent(PlayActivitySport.this,
								MainActivity.class);
						startActivity(intent);
						/*overridePendingTransition(R.anim.out_to_right,
								R.anim.in_from_left);*/
						//123

					};
				}.execute();
			}
			
			@Override
			public void onCustomDialogueNegativeClick() {
				
				myASlertDialog.dismiss();
				
			}
		});
		
		myASlertDialog.show();
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setIcon(R.drawable.app);
		// builder.setTitle(getResources().getString(R.string.exit_show));
		builder.setMessage(R.string.exit_play_show);
		builder.setPositiveButton(R.string.str_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
					}
				});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.show();*/
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (mPopupWindowProgress != null && mPopupWindowProgress.isShowing()) {
			mPopupWindowProgress.dismiss();

		}
		if (resolutionPopWindow != null && resolutionPopWindow.isShowing()) {
			resolutionPopWindow.dismiss();
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			bManualExit = true;
			if (!bProgress) {
				if (isTakeVideo == true) {
					showToast(R.string.ptz_takevideo_show);
				} else {
					returnLastBmp2Home();
					showSureDialogPlay(this);
				}
				return true;
			}
		}
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (!bProgress) {
				showTop();
				showBottom();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private void showTop() {
		if (isShowtoping) {
			isShowtoping = false;
			topbg.setVisibility(View.GONE);
			topbg.startAnimation(dismissTopAnim);
		} else {
			isShowtoping = true;
			topbg.setVisibility(View.VISIBLE);
			topbg.startAnimation(showTopAnim);
		}
	}

	private void defaultVideoParams() {
		nBrightness = 1;
		nContrast = 128;
		NativeCaller.PPPPCameraControl(strDID, 7, 0);
		// NativeCaller.PPPPCameraControl(strDID, 2, 128);

		showToast(R.string.ptz_default_vedio_params);
		NativeCaller.PPPPGetSystemParams(strDID,
				ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
	}

	// private void updateTimeout() {
	// textTimeoutTextView
	// .setText(getString(R.string.p2p_relay_mode_time_out));
	// }

	private void InitTextView() {
		t1 = (TextView) popv.findViewById(R.id.text1);
		t2 = (TextView) popv.findViewById(R.id.text2);
		// t1.setTextColor(0xff000000);
		// t2.setTextColor(0x80000000);
		// t1.setBackgroundColor(0xffffffff);
		// t2.setBackgroundColor(0xffdddddd);
		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));
	}

	private void InitViewPager() {
		mPager = (ViewPager) popv.findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		View view1 = mInflater.inflate(R.layout.popuppre, null);
		View view2 = mInflater.inflate(R.layout.popuppre, null);
		buttonpre1 = (Button) view1.findViewById(R.id.pre1);
		buttonprec1 = (Button) view2.findViewById(R.id.pre1);
		buttonpre2 = (Button) view1.findViewById(R.id.pre2);
		buttonprec2 = (Button) view2.findViewById(R.id.pre2);
		buttonpre3 = (Button) view1.findViewById(R.id.pre3);
		buttonprec3 = (Button) view2.findViewById(R.id.pre3);
		buttonpre4 = (Button) view1.findViewById(R.id.pre4);
		buttonprec4 = (Button) view2.findViewById(R.id.pre4);
		buttonpre5 = (Button) view1.findViewById(R.id.pre5);
		buttonprec5 = (Button) view2.findViewById(R.id.pre5);
		buttonpre6 = (Button) view1.findViewById(R.id.pre6);
		buttonprec6 = (Button) view2.findViewById(R.id.pre6);
		buttonpre7 = (Button) view1.findViewById(R.id.pre7);
		buttonprec7 = (Button) view2.findViewById(R.id.pre7);
		buttonpre8 = (Button) view1.findViewById(R.id.pre8);
		buttonprec8 = (Button) view2.findViewById(R.id.pre8);
		buttonpre9 = (Button) view1.findViewById(R.id.pre9);
		buttonprec9 = (Button) view2.findViewById(R.id.pre9);
		buttonpre10 = (Button) view1.findViewById(R.id.pre10);
		buttonprec10 = (Button) view2.findViewById(R.id.pre10);
		buttonpre11 = (Button) view1.findViewById(R.id.pre11);
		buttonprec11 = (Button) view2.findViewById(R.id.pre11);
		buttonpre12 = (Button) view1.findViewById(R.id.pre12);
		buttonprec12 = (Button) view2.findViewById(R.id.pre12);
		buttonpre13 = (Button) view1.findViewById(R.id.pre13);
		buttonprec13 = (Button) view2.findViewById(R.id.pre13);
		buttonpre14 = (Button) view1.findViewById(R.id.pre14);
		buttonprec14 = (Button) view2.findViewById(R.id.pre14);
		buttonpre15 = (Button) view1.findViewById(R.id.pre15);
		buttonprec15 = (Button) view2.findViewById(R.id.pre15);
		buttonpre16 = (Button) view1.findViewById(R.id.pre16);
		buttonprec16 = (Button) view2.findViewById(R.id.pre16);
		listViews.add(view1);
		listViews.add(view2);
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		buttonpre1.setOnClickListener(this);
		buttonprec1.setOnClickListener(this);
		buttonpre2.setOnClickListener(this);
		buttonprec2.setOnClickListener(this);
		buttonpre3.setOnClickListener(this);
		buttonprec3.setOnClickListener(this);
		buttonpre4.setOnClickListener(this);
		buttonprec4.setOnClickListener(this);
		buttonpre5.setOnClickListener(this);
		buttonprec5.setOnClickListener(this);
		buttonpre6.setOnClickListener(this);
		buttonprec6.setOnClickListener(this);
		buttonpre7.setOnClickListener(this);
		buttonprec7.setOnClickListener(this);
		buttonpre8.setOnClickListener(this);
		buttonprec8.setOnClickListener(this);
		buttonpre9.setOnClickListener(this);
		buttonprec9.setOnClickListener(this);
		buttonpre10.setOnClickListener(this);
		buttonprec10.setOnClickListener(this);
		buttonpre11.setOnClickListener(this);
		buttonprec11.setOnClickListener(this);
		buttonpre12.setOnClickListener(this);
		buttonprec12.setOnClickListener(this);
		buttonpre13.setOnClickListener(this);
		buttonprec13.setOnClickListener(this);
		buttonpre14.setOnClickListener(this);
		buttonprec14.setOnClickListener(this);
		buttonpre15.setOnClickListener(this);
		buttonprec15.setOnClickListener(this);
		buttonpre16.setOnClickListener(this);
		buttonprec16.setOnClickListener(this);

	}

	private void InitImageView() {
		cursorxx = (ImageView) popv.findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
				.getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / 2 - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursorxx.setImageMatrix(matrix);
	}

	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	public class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;

		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

		int one = offset * 2 + bmpW;
		int two = one * 2;

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				break;
			}
			currIndex = arg0;
			if (currIndex == 0) {
				t1.setTextColor(0xffffff00);
				t2.setTextColor(0xffffffff);
				// t1.setBackgroundColor(0xff000000);
				// t2.setBackgroundColor(0xffdddddd);
			} else {
				t2.setTextColor(0xffffff00);
				t1.setTextColor(0xffffffff);
				// t2.setBackgroundColor(0xff000000);
				// t1.setBackgroundColor(0xffdddddd);
			}
			animation.setFillAfter(true);
			animation.setDuration(300);
			cursorxx.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	public void initExitPopupWindow2() {
		LayoutInflater li = LayoutInflater.from(this);
		popv = li.inflate(R.layout.ip_video_activity, null);
		InitImageView();
		InitTextView();
		InitViewPager();
		popupWindow_about = new PopupWindow(popv,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_about.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_about.setFocusable(true);
		popupWindow_about.setOutsideTouchable(true);
		popupWindow_about.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_about
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_about.dismiss();
					}
				});
		popupWindow_about.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_about.dismiss();
				}
				return false;
			}
		});
	}

	private View popvPlayMore;
	private PopupWindow popupWindow_playMore;
	private ImageButton ptzBrightness, ptzContrast, ptzDefaultSet;

	public void initExitPopupWindow_playMore() {
		LayoutInflater li = LayoutInflater.from(this);
		popvPlayMore = li.inflate(R.layout.popup_playmore, null);
//		ptzHoriMirror2 = (ImageButton) popvPlayMore
//				.findViewById(R.id.ptz_hori_mirror);
//		ptzVertMirror2 = (ImageButton) popvPlayMore
//				.findViewById(R.id.ptz_vert_mirror);
//		imageButton_frequency=(ImageButton) popvPlayMore.findViewById(R.id.frequence);
//		imageButton_frequency.setOnClickListener(this);
		Button_quality = (Button) popvPlayMore.findViewById(R.id.quality);
		Button_quality.setOnClickListener(this);
		ptzDefaultSet = (ImageButton) popvPlayMore.findViewById(R.id.ptz_default_set);
		ptzDefaultSet.setOnClickListener(this);
//		led_open = (ImageButton) popvPlayMore.findViewById(R.id.led_open);
//		led_open.setOnClickListener(this);
		buttonPreset = (Button) popvPlayMore.findViewById(R.id.preset);
		buttonPreset.setOnClickListener(this);
		ptz_io_open = (ImageButton) popvPlayMore.findViewById(R.id.ptz_io_open);
		ptz_io_open.setOnClickListener(this);
//		ptz_io_close = (ImageButton) popvPlayMore
//				.findViewById(R.id.ptz_io_close);
//		ptz_io_close.setOnClickListener(this);
		ptzBrightness = (ImageButton) popvPlayMore
				.findViewById(R.id.ptz_brightness);
		ptzContrast = (ImageButton) popvPlayMore
				.findViewById(R.id.ptz_contrast);
		ptzBrightness.setOnClickListener(this);
		ptzContrast.setOnClickListener(this);
//		ptzHoriMirror2.setOnClickListener(this);
//		ptzVertMirror2.setOnClickListener(this);
		popupWindow_playMore = new PopupWindow(popvPlayMore,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_playMore
				.setAnimationStyle(R.style.AnimationPreviewPlayMore);
		popupWindow_playMore.setFocusable(true);
		popupWindow_playMore.setOutsideTouchable(true);
		popupWindow_playMore.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_playMore
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_playMore.dismiss();
					}
				});
		popupWindow_playMore.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_playMore.dismiss();
				}
				return false;
			}
		});
	}

	View popv_alerm;
	PopupWindow popupWindow_alerm;
	Button button_alerm_on, button_alerm_off;

	public void initExitPopupWindow_Alerm() {
		LayoutInflater li = LayoutInflater.from(this);
		popv_alerm = li.inflate(R.layout.popup_alerm, null);
		button_alerm_on = (Button) popv_alerm
				.findViewById(R.id.button_alerm_on);
		button_alerm_off = (Button) popv_alerm
				.findViewById(R.id.button_alerm_off);
		button_alerm_on.setOnClickListener(this);
		button_alerm_off.setOnClickListener(this);
		popupWindow_alerm = new PopupWindow(popv_alerm,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_alerm.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_alerm.setFocusable(true);
		popupWindow_alerm.setOutsideTouchable(true);
		popupWindow_alerm.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_alerm
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_alerm.dismiss();
					}
				});
		popupWindow_alerm.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_alerm.dismiss();
				}
				return false;
			}
		});
	}

	public void initExitPopupWindow_frame() {
		LayoutInflater li = LayoutInflater.from(this);
		popv_frame = li.inflate(R.layout.popup_s, null);
		button_frame1 = (Button) popv_frame.findViewById(R.id.button_frame_1);
		button_frame2 = (Button) popv_frame.findViewById(R.id.button_frame_2);
		button_frame3 = (Button) popv_frame.findViewById(R.id.button_frame_3);
		button_frame4 = (Button) popv_frame.findViewById(R.id.button_frame_4);
		button_frame5 = (Button) popv_frame.findViewById(R.id.button_frame_5);
		button_frame6 = (Button) popv_frame.findViewById(R.id.button_frame_6);
		button_frame1.setOnClickListener(this);
		button_frame2.setOnClickListener(this);
		button_frame3.setOnClickListener(this);
		button_frame4.setOnClickListener(this);
		button_frame5.setOnClickListener(this);
		button_frame6.setOnClickListener(this);
		popupWindow_frame = new PopupWindow(popv_frame,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_frame.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_frame.setFocusable(true);
		popupWindow_frame.setOutsideTouchable(true);
		popupWindow_frame.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_frame
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_frame.dismiss();
					}
				});
		popupWindow_frame.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_frame.dismiss();
				}
				return false;
			}
		});
	}

	public void initExitPopupWindow_re() {
		LayoutInflater li = LayoutInflater.from(this);
		popv_re = li.inflate(R.layout.popup_r, null);
		button_re1 = (Button) popv_re.findViewById(R.id.button_resolution_1);
		button_re2 = (Button) popv_re.findViewById(R.id.button_resolution_2);
		button_re3 = (Button) popv_re.findViewById(R.id.button_resolution_3);
		button_re1.setOnClickListener(this);
		button_re2.setOnClickListener(this);
		button_re3.setOnClickListener(this);
		popupWindow_re = new PopupWindow(popv_re,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_re.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_re.setFocusable(true);
		popupWindow_re.setOutsideTouchable(true);
		popupWindow_re.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_re
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_re.dismiss();
					}
				});
		popupWindow_re.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_re.dismiss();
				}
				return false;
			}
		});
	}
	public void initExitPopupWindow_gpio() {
		LayoutInflater li = LayoutInflater.from(this);
		popv_gpio = li.inflate(R.layout.popup_gpio, null);
		button_gpio_open = (Button) popv_gpio.findViewById(R.id.button_gpio_open);
		button_gpio_close = (Button) popv_gpio.findViewById(R.id.button_gpio_close);
		button_gpio_open.setOnClickListener(this);
		button_gpio_close.setOnClickListener(this);
		popupWindow_gpio = new PopupWindow(popv_gpio,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_gpio.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_gpio.setFocusable(true);
		popupWindow_gpio.setOutsideTouchable(true);
		popupWindow_gpio.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_gpio
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_gpio.dismiss();
					}
				});
		popupWindow_gpio.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_gpio.dismiss();
				}
				return false;
			}
		});
	}
	
	public void initExitPopupWindow_frequency() {
		LayoutInflater li = LayoutInflater.from(this);
		popv_frequency = li.inflate(R.layout.popup_frequency, null);
		button_frq50 = (Button) popv_frequency.findViewById(R.id.button_fq_50);
		button_frq60 = (Button) popv_frequency.findViewById(R.id.button_fq_60);

		button_frq50.setOnClickListener(this);
		button_frq60.setOnClickListener(this);

		popupWindow_frequency = new PopupWindow(popv_frequency,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_frequency.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_frequency.setFocusable(true);
		popupWindow_frequency.setOutsideTouchable(true);
		popupWindow_frequency.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_frequency
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_frequency.dismiss();
					}
				});
		popupWindow_frequency.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_frequency.dismiss();
				}
				return false;
			}
		});
	}
	
	public void initExitPopupWindow_Infrared() {
		LayoutInflater li = LayoutInflater.from(this);
		popv_infrared = li.inflate(R.layout.popup_i, null);
		button_i1 = (Button) popv_infrared.findViewById(R.id.button_in_1);
		button_i2 = (Button) popv_infrared.findViewById(R.id.button_in_2);
		button_i3 = (Button) popv_infrared.findViewById(R.id.button_in_3);
		button_i1.setOnClickListener(this);
		button_i2.setOnClickListener(this);
		button_i3.setOnClickListener(this);
		popupWindow_in = new PopupWindow(popv_infrared,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_in.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_in.setFocusable(true);
		popupWindow_in.setOutsideTouchable(true);
		popupWindow_in.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_in
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_in.dismiss();
					}
				});
		popupWindow_in.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_in.dismiss();
				}
				return false;
			}
		});
	}

	private Button button_bit_4;

	public void initExitPopupWindow_bit() {
		LayoutInflater li = LayoutInflater.from(this);
		popv_bit = li.inflate(R.layout.popup_b, null);
		button_bit1 = (Button) popv_bit.findViewById(R.id.button_bit_1);
		button_bit2 = (Button) popv_bit.findViewById(R.id.button_bit_2);
		button_bit_4 = (Button) popv_bit.findViewById(R.id.button_bit_4);
		button_bit1.setOnClickListener(this);
		button_bit2.setOnClickListener(this);
		button_bit_4.setOnClickListener(this);
		popupWindow_bit = new PopupWindow(popv_bit,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_bit.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_bit.setFocusable(true);
		popupWindow_bit.setOutsideTouchable(true);
		popupWindow_bit.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_bit
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_bit.dismiss();
					}
				});
		popupWindow_bit.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_bit.dismiss();
				}
				return false;
			}
		});
	}

	private void setViewVisible() {
		if (bProgress) {
			bProgress = false;
			progressView.setVisibility(View.INVISIBLE);
			osdView.setVisibility(View.VISIBLE);
			// if (nP2PMode == ContentCommon.PPPP_MODE_P2P_RELAY) {
			// updateTimeout();
			// textTimeoutTextView.setVisibility(View.VISIBLE);
			// // startTimeout();
			// }
			getCameraParams();
		}
	}

	// private Handler timeoutHandle = new Handler() {
	// public void handleMessage(Message msg) {
	//
	// if (nTimeoutRemain > 0) {
	// nTimeoutRemain = nTimeoutRemain - 1;
	// updateTimeout();
	// Message msgMessage = new Message();
	// timeoutHandle.sendMessageDelayed(msgMessage, 1000);
	// } else {
	// if (!isExit) {
	// Toast.makeText(getApplicationContext(),
	// R.string.p2p_view_time_out, Toast.LENGTH_SHORT)
	// .show();
	// }
	// finish();
	// }
	// }
	// };

	// private void startTimeout() {
	// if (!bTimeoutStarted) {
	// Message msgMessage = new Message();
	// timeoutHandle.sendMessageDelayed(msgMessage, 1000);
	// bTimeoutStarted = true;
	// }
	// }
	
	private Bitmap mBmp;
	private Handler mHandler = new Handler() {

		@SuppressLint("NewApi") public void handleMessage(Message msg) {
			if (msg.what == 1 || msg.what == 2) {
				if (isOneShow) {
					setViewVisible();
				}
			}
			if (!isPTZPrompt) {
				isPTZPrompt = true;
				showToast(R.string.ptz_control);
			}

			switch (msg.what) {
			case 1: // h264
			{

				if (isOneShow) {
					isDateComeOn = true;
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					button_re3.setVisibility(View.VISIBLE);
					button_frame6.setVisibility(View.GONE);
					vidoeView.setVisibility(View.GONE);
					videoViewStandard.setVisibility(View.GONE);
					isOneShow = false;
					layout_vctscape.setVisibility(View.GONE);
					layout_landscape.setVisibility(View.VISIBLE);
				}

				int width = getWindowManager().getDefaultDisplay().getWidth();
				int height = getWindowManager().getDefaultDisplay().getHeight();
				if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
					// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
							width, width * 3 / 4);
					lp.gravity = Gravity.CENTER;
					myGlSurfaceView.setLayoutParams(lp);
				} else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
					// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
							width, height);
					lp.gravity = Gravity.CENTER;
					myGlSurfaceView.setLayoutParams(lp);
				}
				//Log.i("", "Call VideoData...h264Data:" + nVideoWidth + " ,nVideoHeight + " + nVideoHeight);
				myRender.writeSample(videodata, nVideoWidth, nVideoHeight);
				// videoViewStandard.setVisibility(View.GONE);

			}
				break;
			case 2: // JPEG
			{
				if (isOneShow) {
					isDateComeOn = true;
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					button_re3.setVisibility(View.GONE);
					button_frame6.setVisibility(View.VISIBLE);
					// ptzTakepic.setVisibility(View.VISIBLE);
					// ptzTakeVideo.setVisibility(View.VISIBLE);
					myGlSurfaceView.setVisibility(View.GONE);
					layout_vctscape.setVisibility(View.GONE);
					layout_landscape.setVisibility(View.VISIBLE);
					isOneShow = false;
				}
				mBmp = BitmapFactory
						.decodeByteArray(videodata, 0, videoDataLen);
				if (mBmp == null) {
					Log.d(LOG_TAG, "bmp can't be decode...");
					bDisplayFinished = true;
					return;
				}
				//Log.d("mybitmap","orign--width="+mBmp.g);
				int width = getWindowManager().getDefaultDisplay().getWidth();
				int height = getWindowManager().getDefaultDisplay().getHeight();

				nVideoWidth = mBmp.getWidth();
				nVideoHeight = mBmp.getHeight();
				
				if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
					Bitmap bitmap = Bitmap.createScaledBitmap(mBmp, width,
							width * 3 / 4, true);
					videoViewStandard.setVisibility(View.GONE);
					vidoeView.setVisibility(View.VISIBLE);
					vidoeView.setImageBitmap(bitmap);

				} else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
					Bitmap bitmap;
					
					bitmap= Bitmap.createScaledBitmap(mBmp, width,
							height, true);

					videoViewStandard.setImageBitmap(bitmap);
					videoViewStandard.setVisibility(View.VISIBLE);
					vidoeView.setVisibility(View.GONE);
				}
				if (isTakepic) {
					isTakepic = false;
					takePicture(mBmp);
				}

			}
				break;
			case 3: //
			{
				displayResolution();
			}
				break;
			}

			if (msg.what == 1 || msg.what == 2) {

				showTimeStamp();
				bDisplayFinished = true;

				nPlayCount++;
				if (nPlayCount >= 100) {
					nPlayCount = 0;
				}
			}
		}

	};

	private void takePicture(final Bitmap bmp) {
		if (isPictSave == false) {
			isPictSave = true;
			new Thread() {
				public void run() {
					savePicToSDcard(bmp);
				}
			}.start();
		} else {
			return;
		}

	}

	private synchronized void savePicToSDcard(Bitmap bmp) {
		String strDate = getStrDate();
		String date = strDate.substring(0, 10);
		int i = 0;
		Cursor cursorpic = helper.queryAllPicture(strDID);
		while (cursorpic.moveToNext()) {
			@SuppressLint("Range")
			String filePath = cursorpic.getString(cursorpic.getColumnIndex(DataBaseHelper.KEY_FILEPATH));
			String s1 = filePath.substring(filePath.lastIndexOf("/") + 1);
			String date1 = s1.substring(0, 10);
			Log.d("tag", "cursorpic.getCount():" + cursorpic.getCount() + " i:"
					+ i + "  date:" + date + "  date1:" + date1);
			if (date1.toString().equals(date)) {
				i++;
			}
		}

		if (i >= 500) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					 
					isPictSave = false;
					showToast(R.string.play_take_pic_show);
				}
			});
			return;
		}
		if (cursorpic != null) {
			cursorpic.close();
		}
		Cursor cursor = helper.queryVideoOrPictureByDate(strDID, strDate,
				DataBaseHelper.TYPE_PICTURE);

		int seri = cursor.getCount() + 1;
		FileOutputStream fos = null;
		try {
			File div = new File(Environment.getExternalStorageDirectory(),
					ContentCommon.SDCARD_PATH + "/pic");
			if (!div.exists()) {
				div.mkdirs();
			}
			File file = new File(div, strDate + "_" + "=" + strDID + "!" + seri
					+ "_" + ".jpg");
			fos = new FileOutputStream(file);
			if (bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos)) {
				fos.flush();
				helper.createVideoOrPic(strDID, file.getAbsolutePath(),
						DataBaseHelper.TYPE_PICTURE, strDate);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(
								PlayActivitySport.this,
								getResources().getString(
										R.string.ptz_takepic_ok), Toast.LENGTH_SHORT).show();
					}
				});
			}

		} catch (Exception e) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(
							PlayActivitySport.this,
							getResources().getString(R.string.ptz_takepic_fail),
							Toast.LENGTH_SHORT).show();
				}
			});

			Log.d("tag", "exception:" + e.getMessage());
			e.printStackTrace();
		} finally {
			isPictSave = false;
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fos = null;
			}
		}
		if (cursor != null) {
			cursor.close();
		}
		if (bmp != null) {
			bmp.recycle();
		}
	}

	private String getStrDate() {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
		String strDate = f.format(d);
		return strDate;
	}

	protected void displayResolution() {
		// ---------------------
		/*
		 * 0->640x480 1->320x240 2->160x120; 3->1280x720 4->640x360 5->1280x960
		 */

		String strCurrResolution = null;

		switch (nResolution) {
		case 0:// vga
			strCurrResolution = "640x480";
			break;
		case 1:// qvga
			strCurrResolution = "320x240";
			break;
		case 2:
			strCurrResolution = "160x120";
			break;
		case 3:// 720p
			strCurrResolution = "1280x720";
			break;
		case 4:
			strCurrResolution = "640x360";
			break;
		case 5:
			strCurrResolution = "1280x960";
			break;
		default:
			return;
		}
	}

	protected void showTimeStamp() {
		 Calendar calendar = Calendar.getInstance();
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH) + 1;
		 int day = calendar.get(Calendar.DAY_OF_MONTH);
		 int hour = calendar.get(Calendar.HOUR_OF_DAY);
		 int minute = calendar.get(Calendar.MINUTE);
		 int second = calendar.get(Calendar.SECOND);
		
		 String strMonth = (month >= 10) ? String.valueOf(month) : "0" +
		 month;
		 String strDay = (day >= 10) ? String.valueOf(day) : "0" + day;
		 String strHour = (hour >= 10) ? String.valueOf(hour) : "0" + hour;
		 String strMinite = (minute >= 10) ? String.valueOf(minute) : "0"
		 + minute;
		 String strSecond = (second >= 10) ? String.valueOf(second) : "0"
		 + second;
		
		 String strTimeStamp = year + "-" + strMonth + "-" + strDay + " "
		 + strHour + ":" + strMinite + ":" + strSecond;
		//textTimeStamp.setText(timeshow);
		
		//PHP设备显示时间
				if(strDID.substring(0, 3).equals("PTP")){
					textTimeStamp.setVisibility(View.VISIBLE);
				}else{
					textTimeStamp.setVisibility(View.GONE);
				}
				textTimeStamp.setText(strTimeStamp);
			}

	private void getCameraParams() {
		NativeCaller.PPPPGetSystemParams(strDID,
				ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
	}

	private Handler msgHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				if (exit == false) {
					exit = true;
					
					
						//BridgeService.setPlayInterface(null);
					    new MyThread().start();
				}
			}
		}
	};

	private Handler msgStreamCodecHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (nStreamCodecType == ContentCommon.PPPP_STREAM_TYPE_JPEG) {
				// textCodec.setText("JPEG");
			} else {
				// textCodec.setText("H.264");
			}
		}
	};
	private ImageButton ptz_alerm;
	private ImageButton ptz_io_open, ptz_io_close;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getDataFromOther();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.playsport);
		initExitPopupWindow2();
		initExitPopupWindow_frame();
		initExitPopupWindow_re();
		initExitPopupWindow_Infrared();
		initExitPopupWindow_bit();
		initExitPopupWindow_showyou();
		initExitPopupWindow_Alerm();
		initExitPopupWindow_playMore();
		initExitPopupWindow_frequency();
		initExitPopupWindow_gpio();
		findView();
		InitParams();
		BridgeService.setDateTimeInterface(this);
		BridgeService.setPlayInterface(this);
		NativeCaller.PPPPGetSystemParams(strDID,
				ContentCommon.MSG_TYPE_GET_PARAMS);
		helper = DataBaseHelper.getInstance(this);
		AudioBuffer = new CustomBuffer();
		Log.i("", "kmode = " + kmode);
		audioPlayer = new AudioPlayer(AudioBuffer, kmode);
		customAudioRecorder = new CustomAudioRecorder(this, kmode);

		playHolder = playSurface.getHolder();
		playHolder.setFormat(PixelFormat.RGB_565);
		playHolder.addCallback(videoCallback);

		playSurface.setOnTouchListener(this);
		playSurface.setLongClickable(true);

		getCameraParams();
		dismissTopAnim = AnimationUtils.loadAnimation(this,
				R.anim.ptz_top_anim_dismiss);
		showTopAnim = AnimationUtils.loadAnimation(this,
				R.anim.ptz_top_anim_show);
		showAnim = AnimationUtils.loadAnimation(this,
				R.anim.ptz_otherset_anim_show);
		dismissAnim = AnimationUtils.loadAnimation(this,
				R.anim.ptz_otherset_anim_dismiss);

		// prompt user how to control ptz when first enter play
		SharedPreferences sharePreferences = getSharedPreferences("ptzcontrol",
				MODE_PRIVATE);
		isPTZPrompt = sharePreferences.getBoolean("ptzcontrol", false);
		if (!isPTZPrompt) {
			Editor edit = sharePreferences.edit();
			edit.putBoolean("ptzcontrol", true);
			edit.commit();
		}
		status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			try {
				path = Environment.getExternalStorageDirectory();
				stat = new StatFs(path.getPath());
				blockSize = stat.getBlockSize();
				totalBlocks = stat.getBlockCount();
				availableBlocks = stat.getAvailableBlocks();
				sdSize = formatSize(totalBlocks * blockSize);
				sdAvail = formatSize(availableBlocks * blockSize);
			} catch (IllegalArgumentException e) {
				status = Environment.MEDIA_REMOVED;
			}
		} else {
		}
		myRender = new MyRender(myGlSurfaceView);
		myGlSurfaceView.setRenderer(myRender);
		isTakeVideo = false;

		layout_vctscape.setVisibility(View.GONE);
		layout_landscape.setVisibility(View.GONE);
	}

	private void getDataFromOther() {
		isDateComeOn = false;
		Intent in = getIntent();
		if (in != null) {
			streamType = in.getIntExtra(ContentCommon.STR_STREAM_TYPE,
					ContentCommon.MJPEG_SUB_STREAM);
			strName = in.getStringExtra(ContentCommon.STR_CAMERA_NAME);
			strDID = in.getStringExtra(ContentCommon.STR_CAMERA_ID);
			strUser = in.getStringExtra(ContentCommon.STR_CAMERA_USER);
			strPwd = in.getStringExtra(ContentCommon.STR_CAMERA_PWD);
			kmode = in.getIntExtra(ContentCommon.STR_CAMERA_MODE, 0);
			Log.i("", "kmode = " + kmode);
			nP2PMode = in.getIntExtra("modep",
					ContentCommon.PPPP_MODE_P2P_NORMAL);
			Log.d("shix", "mode:" + nP2PMode);
		}
	}

	@SuppressLint("DefaultLocale")
	private void InitParams() {
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		nSurfaceWidth = dm.widthPixels;
		nSurfaceHeight = dm.heightPixels;
		//textosd.setText(strName);
		//cameraName.setText(strName);
		
		//PHP设备显示时间
				if("ptp".equalsIgnoreCase(strDID.substring(0, 3).toString())){
					textosd.setVisibility(View.VISIBLE);
				}else{
					textosd.setVisibility(View.GONE);
				}
				//textosd.setText(strName);
			
			}
	

	private void StartAudio() {
		synchronized (this) {
			AudioBuffer.ClearAll();
			audioPlayer.AudioPlayStart();
			NativeCaller.PPPPStartAudio(strDID);
		}
	}

	private void StopAudio() {
		synchronized (this) {
			audioPlayer.AudioPlayStop();
			AudioBuffer.ClearAll();
			NativeCaller.PPPPStopAudio(strDID);
		}
	}

	private void StartTalk() {
		if (customAudioRecorder != null) {
			customAudioRecorder.StartRecord();
			NativeCaller.PPPPStartTalk(strDID, kmode);
		}
	}

	private void releaseTalk() {
		if (customAudioRecorder != null) {
			customAudioRecorder.releaseRecord();
		}

	}

	private void StopTalk() {
		if (customAudioRecorder != null) {
			customAudioRecorder.StopRecord();
			NativeCaller.PPPPStopTalk(strDID);
		}
	}

	protected void setResolution(int Resolution) {
		Log.d("tag", "setResolution resolution:" + Resolution);
		NativeCaller.PPPPCameraControl(strDID, 0, Resolution);
		NativeCaller.PPPPGetSystemParams(strDID,
				ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
	}

	@Override
	protected void onStart() {
		 
		super.onStart();
	}

	private void findView() {
		layout_vctscape = (LinearLayout) findViewById(R.id.layout_verticalscreen);
		layout_landscape = (RelativeLayout) findViewById(R.id.layout_landscape);

		textView_sdsize = (TextView) findViewById(R.id.sdcard_size);
		layout_videoing = (LinearLayout) findViewById(R.id.video_lu_linear);
		checkBoxHS = (ImageButton) findViewById(R.id.ptz_check_pi);
		// if (play_four_tag == 1) {
		// checkBoxHS.setVisibility(View.GONE);
		// }
		connecting_show = (TextView) findViewById(R.id.connecting_show);
		playSurface = (SurfaceView) findViewById(R.id.playSurface);
		playSurface.setBackgroundColor(0xff000000);
		button_back = (ImageButton) findViewById(R.id.login_top_back);
		myGlSurfaceView = (GLSurfaceView) findViewById(R.id.myhsurfaceview);
		//buttonPreset = (Button) findViewById(R.id.preset);

		textViewVideoing = (TextView) findViewById(R.id.textTimevideoing);
		//led_open = (ImageButton) findViewById(R.id.led_open);
		//imageButton_quality = (ImageButton) findViewById(R.id.quality);
		//ptz_frame = (Button) findViewById(R.id.ptz_frame);
		button_say = (ImageButton) findViewById(R.id.button_say);
		textViewKPS = (TextView) findViewById(R.id.kps);
		textViewWH = (TextView) findViewById(R.id.wh);
		linea_show_kbps = (LinearLayout) findViewById(R.id.linea_show_kbps);
		ptz_takevideo_progress = (ProgressBar) findViewById(R.id.ptz_takevideo_progress);
//		ptz_alerm = (ImageButton) findViewById(R.id.ptz_alerm);
//		ptz_alerm.setOnClickListener(this);
		play_more = (ImageButton) findViewById(R.id.play_more);
		play_more.setOnClickListener(this);

		ptzResolutoin = (Button) findViewById(R.id.ptz_resolution);
		ptzResolutoin.setOnClickListener(this);
//		ptzDefaultSet = (ImageButton) findViewById(R.id.ptz_default_set);
//		ptzDefaultSet.setOnClickListener(this);
		
		ptzHoriMirror2 = (ImageButton)findViewById(R.id.ptz_hori_mirror);
		ptzVertMirror2 = (ImageButton)findViewById(R.id.ptz_vert_mirror);		
		ptzHoriMirror2.setOnClickListener(this);
		ptzVertMirror2.setOnClickListener(this);
		
		button_say.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					StartTalk();
					StopAudio();
					ptzMicrophone
							.setImageResource(R.drawable.ptz_microphone_on1);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					StopTalk();
					ptzMicrophone
							.setImageResource(R.drawable.ptz_microphone_on);
					new AsyncTask<Void, Void, Void>() {
						protected void onPreExecute() {
							button_say.setVisibility(View.GONE);
						};

						@Override
						protected Void doInBackground(Void... params) {
							 
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						protected void onPostExecute(Void result) {
							button_say.setVisibility(View.VISIBLE);
							StartAudio();
						};
					}.execute();
				}
				return false;
			}
		});

		imgUp = (ImageButton) findViewById(R.id.imgup);
		imgDown = (ImageButton) findViewById(R.id.imgdown);
		imgRight = (ImageButton) findViewById(R.id.imgright);
		imgLeft = (ImageButton) findViewById(R.id.imgleft);
		imgUp1 = (ImageButton) findViewById(R.id.imgup1);
		imgDown1 = (ImageButton) findViewById(R.id.imgdown1);
		imgRight1 = (ImageButton) findViewById(R.id.imgright1);
		imgLeft1 = (ImageButton) findViewById(R.id.imgleft1);
		imgRight1.setOnClickListener(this);
		imgDown1.setOnClickListener(this);
		imgLeft1.setOnClickListener(this);
		imgUp1.setOnClickListener(this);
		imgUp.setOnClickListener(this);
		imgDown.setOnClickListener(this);
		imgRight.setOnClickListener(this);
		imgLeft.setOnClickListener(this);
		imgUp.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 0);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 2);
					}
					//
					
					Log.d("test", "zhaogenghuai ptz up start");
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					//NativeCaller.PPPPPTZControl(strDID, 1111);
					
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID,1);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 3);
					}
					Log.d("test", "zhaogenghuai ptz up stop");
				}
				return false;
			}
		});
		imgDown.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					NativeCaller.PPPPPTZControl(strDID, 1112);
					//NativeCaller.PPPPPTZControl(strDID, 2);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 2);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 0);
					}
				Log.d("test", "zhaogenghuai ptz down start");
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					//NativeCaller.PPPPPTZControl(strDID, 1113);
					//NativeCaller.PPPPPTZControl(strDID, 3);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 3);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 1);
					}
					Log.d("test", "zhaogenghuai ptz down stop");
				}
				return false;
			}
		});
		imgLeft.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//NativeCaller.PPPPPTZControl(strDID, 1116);
					//NativeCaller.PPPPPTZControl(strDID, 4);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 4);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 6);
					}
					
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					//NativeCaller.PPPPPTZControl(strDID, 1117);
					//NativeCaller.PPPPPTZControl(strDID, 5);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 5);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 7);
					}
					
				}
				return false;
			}
		});
		imgRight.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//NativeCaller.PPPPPTZControl(strDID, 6);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 6);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 4);
					}
					//NativeCaller.PPPPPTZControl(strDID, 1114);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					//NativeCaller.PPPPPTZControl(strDID, 1115);
					//NativeCaller.PPPPPTZControl(strDID, 7);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 7);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 5);
					}
				}
				return false;
			}
		});
		imgUp1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//NativeCaller.PPPPPTZControl(strDID, 1110);
					//NativeCaller.PPPPPTZControl(strDID, 0);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID,1110);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 2);
					}
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					//NativeCaller.PPPPPTZControl(strDID, 1111);
					//NativeCaller.PPPPPTZControl(strDID, 1);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 1);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 3);
					}
				}
				return false;
			}
		});
		imgDown1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//NativeCaller.PPPPPTZControl(strDID, 1112);
					//NativeCaller.PPPPPTZControl(strDID, 2);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 1112);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 0);
					}
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					//NativeCaller.PPPPPTZControl(strDID, 1113);
					//NativeCaller.PPPPPTZControl(strDID, 3);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 3);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 1);
					}
				}
				return false;
			}
		});
		imgLeft1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//NativeCaller.PPPPPTZControl(strDID, 1116);
					//NativeCaller.PPPPPTZControl(strDID, 4);
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 1114);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 6);
					}
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					//NativeCaller.PPPPPTZControl(strDID, 1117);
					
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 5);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 7);
					}
				}
				return false;
			}
		});
		imgRight1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				 
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//NativeCaller.PPPPPTZControl(strDID, 1114);
				
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						//NativeCaller.PPPPPTZControl(strDID, 6);
						NativeCaller.PPPPPTZControl(strDID, 1116);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 4);
					}
				
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					//NativeCaller.PPPPPTZControl(strDID, 1115);
					
					if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
						NativeCaller.PPPPPTZControl(strDID, 7);
					}else{
						NativeCaller.PPPPPTZControl(strDID, 5);
					}
					
				}
				return false;
			}
		});
		//ptz_frame.setOnClickListener(this);
//		imageButton_quality.setOnClickListener(this);
		//led_open.setOnClickListener(this);
		//buttonPreset.setOnClickListener(this);
		checkBoxHS.setOnClickListener(this);
		vidoeView = (ImageView) findViewById(R.id.vedioview);
		videoViewStandard = (ImageView) findViewById(R.id.vedioview_standard);
		progressView = (View) findViewById(R.id.progressLayout);
		textosd = (TextView) findViewById(R.id.textosd);
		textTimeStamp = (TextView) findViewById(R.id.textTimeStamp);
		// textTimeoutTextView = (TextView) findViewById(R.id.textTimeout);
		cameraName = (TextView) findViewById(R.id.cameraName);

		// textResolution = (TextView) findViewById(R.id.textResolution);
		osdView = (View) findViewById(R.id.osdlayout);

		ptzHoriTour2 = (ImageButton) findViewById(R.id.ptz_hori_tour);
		ptzVertTour2 = (ImageButton) findViewById(R.id.ptz_vert_tour);

		ptzAudio = (ImageButton) findViewById(R.id.ptz_audio);
		ptzMicrophone = (ImageButton) findViewById(R.id.ptz_microphone);
		ptzTakepic = (ImageButton) findViewById(R.id.ptz_takepic);
		ptzTakeVideo = (ImageButton) findViewById(R.id.ptz_takevideo);
		// ptzOtherSetView = findViewById(R.id.ptz_othersetview);
		ptzOtherSetAnimView = findViewById(R.id.ptz_othersetview_anim);

		ptzHoriTour2.setOnClickListener(this);
		ptzVertTour2.setOnClickListener(this);

		ptzAudio.setOnClickListener(this);
		ptzMicrophone.setOnClickListener(this);
		ptzTakepic.setOnClickListener(this);
		ptzTakeVideo.setOnClickListener(this);
		button_back.setOnClickListener(this);
		topbg = (RelativeLayout) findViewById(R.id.top_bg);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.top_bg);
		BitmapDrawable drawable = new BitmapDrawable(bitmap);
		drawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
		drawable.setDither(true);
		topbg.setBackgroundDrawable(drawable);
		ptzOtherSetAnimView.setBackgroundDrawable(drawable);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		
		
		
		
		super.onConfigurationChanged(newConfig);
		mBaseMatrix = new Matrix();
		mSuppMatrix = new Matrix();
		mDisplayMatrix = new Matrix();
		
		
		
		videoViewStandard.setImageMatrix(mDisplayMatrix);
		
		
		upDateLanguageonConfigurationChange();
		
	}
	
	
	
	private void upDateLanguageonConfigurationChange()
	{
		
		String  tempLanguage="en";
	    if(KisafaApplication.currentAppLanguage.equals(LANGUAGES.ENGLISH))
		{
	    	tempLanguage="en";
	    	
	    	
		}
	    else
	    {
	    	tempLanguage="ar";
	    	
	    	
	    }
	 
	 try
		{
			Locale locale = new Locale(tempLanguage);
			Locale.setDefault(locale);
			Configuration config = getApplicationContext().getResources().getConfiguration();
			config.locale = locale;
			getBaseContext().getResources().updateConfiguration(config,
			      getBaseContext().getResources().getDisplayMetrics());
			
		
			
		}catch(Exception ex)
		{
			ex.toString();
		}
	 
		
	}
	
	
	
	

	private boolean existSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isDown = false;
	private boolean isSecondDown = false;
	private float x1 = 0;
	private float x2 = 0;
	private float y1 = 0;
	private float y2 = 0;

	// private float ZOOMMAX=2f;
	// private float ZOOMMIN=0.5f;
	// private float ZOOMMultiple=1.0f;
	// private float currentWidth=480.0f;
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		if (!isDown) {
			x1 = event.getX();
			y1 = event.getY();
			isDown = true;
		}
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			mode = DRAG;
			originalScale = getScale();
			break;
		case MotionEvent.ACTION_POINTER_UP:

			break;
		case MotionEvent.ACTION_UP:
			if (Math.abs((x1 - x2)) < 25 && Math.abs((y1 - y2)) < 25) {
				if (resolutionPopWindow != null
						&& resolutionPopWindow.isShowing()) {
					resolutionPopWindow.dismiss();
				}
				if (mPopupWindowProgress != null
						&& mPopupWindowProgress.isShowing()) {
					mPopupWindowProgress.dismiss();
				}
				if (!isSecondDown) {
					if (!bProgress) {
						showTop();
						showBottom();
					}
				}
				isSecondDown = false;
			} else {
			}
			x1 = 0;
			x2 = 0;
			y1 = 0;
			y2 = 0;
			isDown = false;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			isSecondDown = true;
			oldDist = spacing(event);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode = ZOOM;
			}
			break;

		case MotionEvent.ACTION_MOVE:
			x2 = event.getX();
			y2 = event.getY();

			int midx = getWindowManager().getDefaultDisplay().getWidth() / 2;
			int midy = getWindowManager().getDefaultDisplay().getHeight() / 2;
			if (mode == ZOOM) {
				float newDist = spacing(event);
				if (newDist > 0f) {
					float scale = newDist / oldDist;
					Log.d("scale", "scale:" + scale);
					if (scale <= 2.0f && scale >= 0.2f) {
						zoomTo(originalScale * scale, midx, midy);
					}
				}
			}
		}

		return gt.onTouchEvent(event);
	}

	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;

	private int mode = NONE;
	private float oldDist;
	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();
	private PointF start = new PointF();
	private PointF mid = new PointF();
	float mMaxZoom = 2.0f;
	float mMinZoom = 0.3125f;
	float originalScale;
	float baseValue;
	protected Matrix mBaseMatrix = new Matrix();
	protected Matrix mSuppMatrix = new Matrix();
	private Matrix mDisplayMatrix = new Matrix();
	private final float[] mMatrixValues = new float[9];

	protected void zoomTo(float scale, float centerX, float centerY) {
		Log.d("zoomTo", "zoomTo scale:" + scale);
		if (scale > mMaxZoom) {
			scale = mMaxZoom;
		} else if (scale < mMinZoom) {
			scale = mMinZoom;
		}

		float oldScale = getScale();
		float deltaScale = scale / oldScale;
		Log.d("deltaScale", "deltaScale:" + deltaScale);
		mSuppMatrix.postScale(deltaScale, deltaScale, centerX, centerY);
		videoViewStandard.setScaleType(ImageView.ScaleType.MATRIX);
		videoViewStandard.setImageMatrix(getImageViewMatrix());
	}

	protected Matrix getImageViewMatrix() {
		mDisplayMatrix.set(mBaseMatrix);
		mDisplayMatrix.postConcat(mSuppMatrix);
		return mDisplayMatrix;
	}

	protected float getScale(Matrix matrix) {
		return getValue(matrix, Matrix.MSCALE_X);
	}

	protected float getScale() {
		return getScale(mSuppMatrix);
	}

	protected float getValue(Matrix matrix, int whichValue) {
		matrix.getValues(mMatrixValues);
		return mMatrixValues[whichValue];
	}

	private float spacing(MotionEvent event) {
		try {
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return (float)Math.sqrt(x * x + y * y);
		} catch (Exception e) {
		}
		return 0;
	}

	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	// private boolean isArrowShowing=false;
	// end===================================================================
	@Override
	public boolean onDown(MotionEvent e) {
		Log.d("tag", "onDown");
		return false;
	}

	@Override
	protected void onPause() {
		 
		Log.d("test", "zhaogenghuai onstop");
		bManualExit = true;
		if (!bProgress) {
			if (!isTakeVideo) {
				button_back.setEnabled(false);
				new AsyncTask<Void, Void, Void>() {
					protected void onPreExecute() {
						button_back.setFocusable(false);
						if (mPopupWindowProgress != null
								&& mPopupWindowProgress.isShowing()) {
							mPopupWindowProgress.dismiss();
						}
						if (popupWindow_show_you != null
								&& popupWindow_show_you.isShowing()) {
							popupWindow_show_you.dismiss();
						}
					};

					@Override
					protected Void doInBackground(Void... params) {
						NativeCaller.StopPPPPLivestream(strDID);
						if (myRender != null) {
							myRender.destroyShaders();
						}
						mbLoop = false;
						isKBPS = false;

						talkAudio = 0;
						tagone = 0;
						isTakeVideo = false;
						one = 0;
						bitmap22 = null;
						p1 = 0;
						StopAudio();
						StopTalk();
						releaseTalk();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}

					protected void onPostExecute(Void result) {
						//finish();
					};
				}.execute();
			}
		}
		super.onStop();
	}

	public void initExitPopupWindow_showyou() {
		LayoutInflater li = LayoutInflater.from(this);
		View popv = li.inflate(R.layout.popupplay, null);
		textView_show = (TextView) popv.findViewById(R.id.textView1_play);
		popupWindow_show_you = new PopupWindow(popv,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		// popupWindow_about.setAnimationStyle(R.style.AnimationPreview);
		// popupWindow_show_you.setFocusable(true);
		// popupWindow_show_you.setOutsideTouchable(true);
		popupWindow_show_you.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		popupWindow_show_you
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						 
						popupWindow_show_you.dismiss();
					}
				});
		popupWindow_show_you.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow_show_you.dismiss();
				}
				return false;
			}
		});
	}

	private final int MINLEN = 80;
	private TextView cameraName;
	private RelativeLayout topbg;
	private Animation showTopAnim;
	private Animation dismissTopAnim;
	private ImageButton ptzHoriMirror2;
	private ImageButton ptzVertMirror2;
	private ImageButton ptzHoriTour2;
	private ImageButton ptzVertTour2;
	private boolean isPTZPrompt;
	private ImageButton ptzTakeVideo;

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		// if(true){
		// return false;
		// }

		float x1 = e1.getX();
		float x2 = e2.getX();
		float y1 = e1.getY();
		float y2 = e2.getY();

		float xx = x1 > x2 ? x1 - x2 : x2 - x1;
		float yy = y1 > y2 ? y1 - y2 : y2 - y1;

		if (xx > yy) {
			if ((x1 > x2) && (xx > MINLEN)) {// rigth
				if (lefRitUpDowTag == false) {
					new AsyncTask<Void, Void, Void>() {
						protected void onPreExecute() {
							lefRitUpDowTag = true;
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
							if (popupWindow_show_you != null
									&& !popupWindow_show_you.isShowing()) {
								textView_show.setText(R.string.rigth);
								popupWindow_show_you.showAtLocation(
										button_back, Gravity.CENTER, 0, 0);
							}
						};

						@Override
						protected Void doInBackground(Void... params) {
							 
							//NativeCaller.PPPPPTZControl(strDID,ContentCommon.CMD_PTZ_RIGHT);
							if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
								NativeCaller.PPPPPTZControl(strDID, 6);
							}else{
								NativeCaller.PPPPPTZControl(strDID, 4);
							}							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						protected void onPostExecute(Void result) {
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
							lefRitUpDowTag = false;
						};
					}.execute();
				}
			} else if ((x1 < x2) && (xx > MINLEN)) {// left
				if (lefRitUpDowTag == false) {
					new AsyncTask<Void, Void, Void>() {
						protected void onPreExecute() {
							lefRitUpDowTag = true;
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
							if (popupWindow_show_you != null
									&& !popupWindow_show_you.isShowing()) {
								textView_show.setText(R.string.left);
								popupWindow_show_you.showAtLocation(
										button_back, Gravity.CENTER, 0, 0);
							}
						};

						@Override
						protected Void doInBackground(Void... params) {
							 
							//NativeCaller.PPPPPTZControl(strDID,ContentCommon.CMD_PTZ_LEFT);
							if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
								NativeCaller.PPPPPTZControl(strDID, 4);
							}else{
								NativeCaller.PPPPPTZControl(strDID, 6);
							}							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						protected void onPostExecute(Void result) {
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
							lefRitUpDowTag = false;
						};
					}.execute();
				}

			}

		} else {
			if ((y1 > y2) && (yy > MINLEN)) {// down
				if (lefRitUpDowTag == false) {
					new AsyncTask<Void, Void, Void>() {
						protected void onPreExecute() {
							lefRitUpDowTag = true;
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
							if (popupWindow_show_you != null
									&& !popupWindow_show_you.isShowing()) {
								textView_show.setText(R.string.down);
								popupWindow_show_you.showAtLocation(
										button_back, Gravity.CENTER, 0, 0);
							}
						};

						@Override
						protected Void doInBackground(Void... params) {
							 
							//NativeCaller.PPPPPTZControl(strDID,ContentCommon.CMD_PTZ_DOWN);
							if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
								NativeCaller.PPPPPTZControl(strDID, 2);
							}else{
								NativeCaller.PPPPPTZControl(strDID, 0);
							}							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						protected void onPostExecute(Void result) {
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
							lefRitUpDowTag = false;
						};
					}.execute();
				}

			} else if ((y1 < y2) && (yy > MINLEN)) {// up
				if (lefRitUpDowTag == false) {
					new AsyncTask<Void, Void, Void>() {
						protected void onPreExecute() {
							lefRitUpDowTag = true;
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
							if (popupWindow_show_you != null
									&& !popupWindow_show_you.isShowing()) {
								textView_show.setText(R.string.up);
								popupWindow_show_you.showAtLocation(
										button_back, Gravity.CENTER, 0, 0);
							}
						};

						@Override
						protected Void doInBackground(Void... params) {
							 
							//NativeCaller.PPPPPTZControl(strDID,ContentCommon.CMD_PTZ_UP);
							if ("ptp".equalsIgnoreCase(strDID.subSequence(0, 3).toString())){
								NativeCaller.PPPPPTZControl(strDID, 0);
							}else{
								NativeCaller.PPPPPTZControl(strDID, 2);
							}							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						protected void onPostExecute(Void result) {
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
							lefRitUpDowTag = false;
						};
					}.execute();
				}
			}

		}

		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public void AudioRecordData(byte[] data, int len) {
		if (bAudioRecordStart && len > 0) {
			NativeCaller.PPPPTalkAudioData(strDID, data, len);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play_more:
			popupWindow_playMore.showAsDropDown(play_more, 0, 15);
			break;	
//		case R.id.frequence:
//			popupWindow_frequency
//			.showAtLocation(cameraName, Gravity.CENTER, 0, 0);			 
//			break;			
		case R.id.ptz_io_open:
			popupWindow_gpio
			.showAtLocation(cameraName, Gravity.CENTER, 0, 0);			
//			String aaa="";
//			int gpio_tag=0;
//			if (m_bGpioOn){
//				aaa="close gpio alarm?";
//				gpio_tag=1;
//			}else{
//				aaa="open gpio alarm?";
//				gpio_tag=0;
//			}
//			 new AlertDialog.Builder(PlayActivitySport.this).setTitle("tips")
//			 .setMessage(aaa)
//			 .setPositiveButton("ok", new DialogInterface.OnClickListener(){
//				
//				@Override
//				public void onClick(DialogInterface dialog, int arg0) {
//					 
//					int gpio_value=94;
//					if (m_bGpioOn){
//						ptz_io_open.setImageResource(R.drawable.gpio_close);
//						gpio_value=95;
//					}
//					else
//						ptz_io_open.setImageResource(R.drawable.gpio_open);
//					m_bGpioOn=!m_bGpioOn;
//					NativeCaller.PPPPPTZControl(strDID, gpio_value);
//				}
//				 
//			 })
//			 .setNeutralButton("cancel", new DialogInterface.OnClickListener(){
//
//				@Override
//				public void onClick(DialogInterface dialog, int arg1) {
//					 
//					dialog.cancel();
//				}
//				 
//			 })
//			 .show();
			break;
//		case R.id.ptz_io_close:
//			NativeCaller.PPPPPTZControl(strDID, 95);
//			break;
//		case R.id.ptz_alerm:
//			popupWindow_alerm.showAtLocation(cameraName, Gravity.CENTER, 0, 0);
//			break;
		case R.id.button_gpio_close:
			NativeCaller.PPPPPTZControl(strDID, 95);
			popupWindow_gpio.dismiss();
			break;
		case R.id.button_gpio_open:
			NativeCaller.PPPPPTZControl(strDID, 94);
			popupWindow_gpio.dismiss();
			break;
		case R.id.button_fq_50:
			NativeCaller.PPPPCameraControl(strDID, 3, 0);
			popupWindow_frequency.dismiss();
			break;
		case R.id.button_fq_60:
			NativeCaller.PPPPCameraControl(strDID, 3, 1);
			popupWindow_frequency.dismiss();
			break;
			
		case R.id.button_alerm_on:
			NativeCaller.PPPPAlarmOpenCloseSetting(strDID, 1, 1);
			popupWindow_alerm.dismiss();
			break;
		case R.id.button_alerm_off:
			NativeCaller.PPPPAlarmOpenCloseSetting(strDID, 0, 0);
			popupWindow_alerm.dismiss();
			break;
//		case R.id.ptz_frame:
//			popupWindow_frame.showAtLocation(cameraName, Gravity.CENTER, 0, 0);
//			break;
		case R.id.quality:
			if (isTakeVideo == true) {
				showToast(R.string.ptz_takevideoing_cut);
			} else {
				popupWindow_bit
						.showAtLocation(cameraName, Gravity.CENTER, 0, 0);
			}

			break;
//		case R.id.led_open:
//			popupWindow_in.showAtLocation(cameraName, Gravity.CENTER, 0, 0);
//			break;
		case R.id.preset:
			popupWindow_about.showAtLocation(cameraName, Gravity.CENTER, 0, 0);
			break;
		case R.id.login_top_back:
			bManualExit = true;
			if (!bProgress) {
				if (isTakeVideo == true) {
					showToast(R.string.ptz_takevideo_show);
				} else {
					button_back.setEnabled(false);
					new AsyncTask<Void, Void, Void>() {
						protected void onPreExecute() {
							button_back.setFocusable(false);
							if (mPopupWindowProgress != null
									&& mPopupWindowProgress.isShowing()) {
								mPopupWindowProgress.dismiss();
							}
							if (popupWindow_show_you != null
									&& popupWindow_show_you.isShowing()) {
								popupWindow_show_you.dismiss();
							}
						};

						@Override
						protected Void doInBackground(Void... params) {

							NativeCaller.StopPPPPLivestream(strDID);
							if (myRender != null) {
								myRender.destroyShaders();
							}
							mbLoop = false;
							isKBPS = false;
							talkAudio = 0;
							tagone = 0;
							isTakeVideo = false;
							one = 0;
							bitmap22 = null;
							p1 = 0;
							StopAudio();
							StopTalk();
							releaseTalk();
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						protected void onPostExecute(Void result) {
							//finish();
							//overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);

							Intent intent = new Intent(PlayActivitySport.this,
									MainActivity.class);
							startActivity(intent);
						};
					}.execute();
				}
			}

			break;
		case R.id.ptz_check_pi:
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				
				
				
				layout_vctscape.setVisibility(View.GONE);
				layout_landscape.setVisibility(View.VISIBLE);
			} else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				layout_vctscape.setVisibility(View.VISIBLE);
				layout_landscape.setVisibility(View.GONE);
				
				
			}
			break;

		case R.id.ptz_hori_mirror:
			int value1;

			if (m_bLeftRightMirror) {
				ptzHoriMirror2.setImageResource(R.drawable.ptz_hori_mirror);
				if (m_bUpDownMirror) {
					value1 = 1;
				} else {
					value1 = 0;
				}
			} else {
				ptzHoriMirror2
						.setImageResource(R.drawable.ptz_hori_mirror_press);
				if (m_bUpDownMirror) {
					value1 = 3;
				} else {
					value1 = 2;
				}
			}

			NativeCaller.PPPPCameraControl(strDID, 5, value1);
			m_bLeftRightMirror = !m_bLeftRightMirror;
			break;
		case R.id.ptz_vert_mirror:
			int value;
			if (m_bUpDownMirror) {
				ptzVertMirror2.setImageResource(R.drawable.ptz_vert_mirror);
				if (m_bLeftRightMirror) {
					value = 2;
				} else {
					value = 0;
				}
			} else {
				ptzVertMirror2
						.setImageResource(R.drawable.ptz_vert_mirror_press);
				if (m_bLeftRightMirror) {
					value = 3;
				} else {
					value = 1;
				}
			}
			Log.d("tttt", value + "");
			NativeCaller.PPPPCameraControl(strDID, 5, value);
			m_bUpDownMirror = !m_bUpDownMirror;
			break;

		case R.id.ptz_hori_tour:
			if (isLeftRight) {
				ptzHoriTour2.setImageResource(R.drawable.ptz_hori_tour);
				isLeftRight = false;
				NativeCaller.PPPPPTZControl(strDID,ContentCommon.CMD_PTZ_LEFT_RIGHT_STOP);
			} else {
				ptzHoriTour2.setImageResource(R.drawable.ptz_hori_tour_press);
				isLeftRight = true;
				NativeCaller.PPPPPTZControl(strDID,ContentCommon.CMD_PTZ_LEFT_RIGHT);
			}
			break;
		case R.id.ptz_vert_tour:
			if (isUpDown) {
				ptzVertTour2.setImageResource(R.drawable.ptz_vert_tour);
				isUpDown = false;
				NativeCaller.PPPPPTZControl(strDID,ContentCommon.CMD_PTZ_UP_DOWN_STOP);
			} else {
				ptzVertTour2.setImageResource(R.drawable.ptz_vert_tour_press);
				isUpDown = true;
				NativeCaller.PPPPPTZControl(strDID,ContentCommon.CMD_PTZ_UP_DOWN);
			}
			break;
		case R.id.ptz_audio:
			dismissBrightAndContrastProgress();
			if (!isMcriophone) {
				if (bAudioStart) {
					// isTalking = false;
					bAudioStart = false;
					ptzAudio.setImageResource(R.drawable.ptz_audio_off);
					StopAudio();

				} else {
					// isTalking = true;
					bAudioStart = true;
					ptzAudio.setImageResource(R.drawable.ptz_audio_on);
					StartAudio();

				}
			}
			break;
		case R.id.ptz_microphone:
			dismissBrightAndContrastProgress();
			if (!isTalking) {
				if (bAudioRecordStart) {
					// isMcriophone = false;
					bAudioRecordStart = false;
					ptzMicrophone
							.setImageResource(R.drawable.ptz_microphone_off1);
					animationButtonSay = AnimationUtils.loadAnimation(this,
							R.anim.my_scale_action2);
					button_say.setAnimation(animationButtonSay);
					button_say.setVisibility(View.GONE);
					// StopTalk();

					StopAudio();
				} else {

					// isMcriophone = true;
					bAudioRecordStart = true;
					ptzMicrophone
							.setImageResource(R.drawable.ptz_microphone_on);
					// StartTalk();
					animationButtonSay = AnimationUtils.loadAnimation(this,
							R.anim.my_scale_action);
					button_say.setAnimation(animationButtonSay);
					button_say.setVisibility(View.VISIBLE);
					showToast(R.string.play_talk_show);
					StartAudio();
				}
			}
			break;
		case R.id.ptz_takepic:
			if (hasSdcard()) {
				dismissBrightAndContrastProgress();
				if (existSdcard()) {
					isTakepic = true;
				} else {
					showToast(R.string.ptz_takepic_save_fail);
				}
			} else {
				showToast(R.string.local_picture_show_sd);
			}
			break;
		case R.id.ptz_takevideo:
			if (hasSdcard()) {
				if (isTakeVideo) {
					isTakeVideo = false;
					//ptzTakeVideo.setVisibility(View.GONE);
					ptz_takevideo_progress.setVisibility(View.VISIBLE);
					new AsyncTask<Void, Void, Void>() {
						@Override
						protected void onPreExecute() {
							 
							takeVideoThread.stopThread();
							super.onPreExecute();
						}

						@Override
						protected Void doInBackground(Void... params) {
							 

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						@Override
						protected void onPostExecute(Void result) {
							 
							ptzTakeVideo
									.setImageResource(R.drawable.ptz_takevideo);
							layout_videoing.setVisibility(View.GONE);
							ptz_takevideo_progress.setVisibility(View.GONE);
							ptzTakeVideo.setVisibility(View.VISIBLE);
							super.onPostExecute(result);
						}
					}.execute();
				} else {
					stat = new StatFs(path.getPath());
					availableBlocks = stat.getAvailableBlocks();
					sdAvail = formatSize(availableBlocks * blockSize);
					if ((availableBlocks * blockSize) / (1024 * 1024) < 50) {
						showToastLong(R.string.sd_card_size_show);
						return;
					}
					isTakeVideo = true;
					//ptzTakeVideo.setVisibility(View.GONE);
					ptz_takevideo_progress.setVisibility(View.VISIBLE);
					layout_videoing.setVisibility(View.VISIBLE);
					SystemValue.checkSDStatu = 1;
					if (resolutionPopWindow != null
							&& resolutionPopWindow.isShowing()) {
						resolutionPopWindow.dismiss();
					}
					new AsyncTask<Void, Void, Void>() {

						@Override
						protected void onPreExecute() {
							 
							takeVideoThread = new TakeVideoThread(isH264Data,
									strDID, frame, nVideoWidth, nVideoHeight);
							takeVideoThread.start();
							super.onPreExecute();
						}

						@Override
						protected Void doInBackground(Void... params) {
							 
							try {
								Thread.sleep(6000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}

						@Override
						protected void onPostExecute(Void result) {
							 
							ptz_takevideo_progress.setVisibility(View.GONE);
							ptzTakeVideo.setVisibility(View.VISIBLE);
							ptzTakeVideo
									.setImageResource(R.drawable.ptz_takevideo_pressed);
							super.onPostExecute(result);
						}
					}.execute();
				}
			}

			break;
		case R.id.ptz_brightness:
			if (mPopupWindowProgress != null
					&& mPopupWindowProgress.isShowing()) {
				mPopupWindowProgress.dismiss();
				mPopupWindowProgress = null;
			}
			setBrightOrContrast(BRIGHT);
			break;
		case R.id.ptz_contrast:
			if (mPopupWindowProgress != null
					&& mPopupWindowProgress.isShowing()) {
				mPopupWindowProgress.dismiss();
				mPopupWindowProgress = null;
			}
			setBrightOrContrast(CONTRAST);
			break;
		case R.id.ptz_resolution:
			if (isTakeVideo == true) {
				showToast(R.string.ptz_takevideoing_cut);
			} else {
				popupWindow_re.showAtLocation(cameraName, Gravity.CENTER, 0, 0);
			}
			break;
		// case R.id.ptz_resolution_jpeg_qvga:
		// dismissBrightAndContrastProgress();
		// resolutionPopWindow.dismiss();
		// nResolution = 1;
		// setResolution(nResolution);
		// Log.d("tag", "jpeg resolution:" + nResolution + " qvga");
		// ptzResolutoin.setImageResource(R.drawable.ptz_resolution_qvga);
		// break;
		// case R.id.ptz_resolution_jpeg_vga:
		// dismissBrightAndContrastProgress();
		// resolutionPopWindow.dismiss();
		// nResolution = 0;
		// setResolution(nResolution);
		// Log.d("tag", "jpeg resolution:" + nResolution + " vga");
		// ptzResolutoin.setImageResource(R.drawable.ptz_resolution_vga);
		// break;
		// case R.id.ptz_resolution_h264_qvga:
		// dismissBrightAndContrastProgress();
		// resolutionPopWindow.dismiss();
		// nResolution = 1;
		// setResolution(nResolution);
		// Log.d("tag", "h264 resolution:" + nResolution + " qvga");
		// ptzResolutoin.setImageResource(R.drawable.ptz_resolution_qvga);
		// break;
		// case R.id.ptz_resolution_h264_vga:
		// dismissBrightAndContrastProgress();
		// resolutionPopWindow.dismiss();
		// nResolution = 0;
		// setResolution(nResolution);
		// Log.d("tag", "h264 resolution:" + nResolution + " vga");
		// ptzResolutoin.setImageResource(R.drawable.ptz_resolution_vga);
		// break;
		// case R.id.ptz_resolution_h264_720p:
		// dismissBrightAndContrastProgress();
		// resolutionPopWindow.dismiss();
		// nResolution = 3;
		// setResolution(nResolution);
		// Log.d("tag", "h264 resolution:" + nResolution + " 720p");
		// ptzResolutoin.setImageResource(R.drawable.ptz_resolution_720p);
		// break;
		case R.id.ptz_default_set:
			dismissBrightAndContrastProgress();
			
			
			 new Builder(PlayActivitySport.this).setTitle(R.string.setting_restore_factory)
			 //.setMessage(R.string.str_ok+"?")
			 .setPositiveButton(R.string.str_ok, new DialogInterface.OnClickListener(){
				
				@Override
				public void onClick(DialogInterface dialog, int arg0) {
					 
					defaultVideoParams();
					dialog.cancel();
				}
				 
			 })
			 .setNeutralButton(R.string.str_cancel, new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					 
					dialog.cancel();
				}
				 
			 })
			 .show();
			
			break;
		case R.id.pre1:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 31);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 30);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre2:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 33);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 32);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre3:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 35);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 34);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre4:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 37);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 36);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre5:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 39);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 38);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre6:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 41);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 40);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre7:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 43);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 42);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre8:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 45);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 44);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre9:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 47);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 46);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre10:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 49);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 48);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre11:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 51);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 50);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre12:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 53);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 52);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre13:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 55);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 54);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre14:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 57);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 56);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre15:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 59);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 58);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.pre16:
			if (currIndex == 0) {
				NativeCaller.PPPPPTZControl(strDID, 61);
			} else {
				NativeCaller.PPPPPTZControl(strDID, 60);
			}
			popupWindow_about.dismiss();
			break;
		case R.id.button_frame_1:
			// ptz_frame.setText("5FPS");
			NativeCaller.PPPPCameraControl(strDID, 6, 5);
			NativeCaller.PPPPGetSystemParams(strDID,
					ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
			popupWindow_frame.dismiss();
			break;
		case R.id.button_frame_2:
			// ptz_frame.setText("10FPS");
			NativeCaller.PPPPCameraControl(strDID, 6, 10);
			NativeCaller.PPPPGetSystemParams(strDID,
					ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
			popupWindow_frame.dismiss();
			break;
		case R.id.button_frame_3:
			// ptz_frame.setText("15FPS");
			NativeCaller.PPPPCameraControl(strDID, 6, 15);
			NativeCaller.PPPPGetSystemParams(strDID,
					ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
			popupWindow_frame.dismiss();
			break;
		case R.id.button_frame_4:
			// ptz_frame.setText("20FPS");
			NativeCaller.PPPPCameraControl(strDID, 6, 20);
			NativeCaller.PPPPGetSystemParams(strDID,
					ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
			popupWindow_frame.dismiss();
			break;
		case R.id.button_frame_5:
			// ptz_frame.setText("25FPS");
			NativeCaller.PPPPCameraControl(strDID, 6, 25);
			NativeCaller.PPPPGetSystemParams(strDID,
					ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
			popupWindow_frame.dismiss();
			break;
		case R.id.button_frame_6:
			// ptz_frame.setText("30FPS");
			NativeCaller.PPPPCameraControl(strDID, 6, 30);
			NativeCaller.PPPPGetSystemParams(strDID,
					ContentCommon.MSG_TYPE_GET_CAMERA_PARAMS);
			popupWindow_frame.dismiss();
			break;
		case R.id.button_resolution_1:
			setResolution(1);
			ContentCommon.viewAlpha=true;
			popupWindow_re.dismiss();
			break;
		case R.id.button_resolution_2:
			setResolution(0);
			ContentCommon.viewAlpha=false;
			popupWindow_re.dismiss();
			break;
		case R.id.button_resolution_3:
			setResolution(3);
			ContentCommon.viewAlpha=false;
			popupWindow_re.dismiss();
			break;
		case R.id.button_in_1:
			button_i1.setTextColor(0xff0044aa);
			button_i2.setTextColor(0xff000000);
			button_i3.setTextColor(0xff000000);
			NativeCaller.PPPPCameraControl(strDID, 14, 1);
			popupWindow_in.dismiss();
			break;
		case R.id.button_in_2:
			button_i2.setTextColor(0xff0044aa);
			button_i1.setTextColor(0xff000000);
			button_i3.setTextColor(0xff000000);
			NativeCaller.PPPPCameraControl(strDID, 14, 0);
			popupWindow_in.dismiss();
			break;
			
		case R.id.button_in_3:
			button_i3.setTextColor(0xff0044aa);
			button_i2.setTextColor(0xff000000);
			button_i1.setTextColor(0xff000000);
			NativeCaller.PPPPCameraControl(strDID, 14, 2);
			popupWindow_in.dismiss();
			break;
		case R.id.button_bit_1:
			button_bit1.setTextColor(0xff0044aa);
			button_bit2.setTextColor(0xff000000);
			button_bit_4.setTextColor(0xff000000);
			//ContentCommon.viewAlpha=true;
			new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... arg0) {
					//setResolution(1);
					setResolution(1);

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					NativeCaller.PPPPCameraControl(strDID, 13, 150);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.PPPPCameraControl(strDID, 6, 15);
					return null;
				}
			}.execute();
			popupWindow_bit.dismiss();
			break;
		case R.id.button_bit_2:
			//ContentCommon.viewAlpha=false;
			button_bit1.setTextColor(0xff000000);
			button_bit2.setTextColor(0xff0044aa);
			button_bit_4.setTextColor(0xff000000);
			new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... arg0) {
					setResolution(1);
		
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.PPPPCameraControl(strDID, 13, 600);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.PPPPCameraControl(strDID, 6, 15);

					return null;
				}
			}.execute();
			popupWindow_bit.dismiss();
			break;

		case R.id.button_bit_4:
			//ContentCommon.viewAlpha=false;
			button_bit1.setTextColor(0xff000000);
			button_bit2.setTextColor(0xff000000);
			button_bit_4.setTextColor(0xff0044aa);
			new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... arg0) {
					//setResolution(3);
					setResolution(0);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.PPPPCameraControl(strDID, 13, 1000);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.PPPPCameraControl(strDID, 6, 15);

					return null;
				}
			}.execute();
			popupWindow_bit.dismiss();
			break;
		case R.id.imgup:
			// ptz_frame.setText("30FPS");
			//NativeCaller.PPPPPTZControl(strDID, 0);
			//NativeCaller.PPPPPTZControl(strDID, 1);
//			NativeCaller.PPPPPTZControl(strDID, 1110);
//			NativeCaller.PPPPPTZControl(strDID, 1111);
			break;
		case R.id.imgdown:
			// ptz_frame.setText("30FPS");
			//NativeCaller.PPPPPTZControl(strDID, 2);
			//NativeCaller.PPPPPTZControl(strDID, 3);
//			NativeCaller.PPPPPTZControl(strDID, 1112);
//			NativeCaller.PPPPPTZControl(strDID, 1113);
			break;
		case R.id.imgleft:
			// ptz_frame.setText("30FPS");
			//NativeCaller.PPPPPTZControl(strDID, 4);
			//NativeCaller.PPPPPTZControl(strDID, 5);
//			NativeCaller.PPPPPTZControl(strDID, 1116);
//			NativeCaller.PPPPPTZControl(strDID, 1117);
			break;
		case R.id.imgright:
			// ptz_frame.setText("30FPS");
			//NativeCaller.PPPPPTZControl(strDID, 6);
			//NativeCaller.PPPPPTZControl(strDID, 7);
//			NativeCaller.PPPPPTZControl(strDID, 1114);
//			NativeCaller.PPPPPTZControl(strDID, 1115);
			break;
		case R.id.imgup1:
			// ptz_frame.setText("30FPS");
			//NativeCaller.PPPPPTZControl(strDID, 0);
			//NativeCaller.PPPPPTZControl(strDID, 1);
//			NativeCaller.PPPPPTZControl(strDID, 1110);
//			NativeCaller.PPPPPTZControl(strDID, 1111);
			break;
		case R.id.imgdown1:
			// ptz_frame.setText("30FPS");
			//NativeCaller.PPPPPTZControl(strDID, 2);
			//NativeCaller.PPPPPTZControl(strDID, 3);
//			NativeCaller.PPPPPTZControl(strDID, 1112);
//			NativeCaller.PPPPPTZControl(strDID, 1113);
			break;
		case R.id.imgleft1:
			// ptz_frame.setText("30FPS");
			//NativeCaller.PPPPPTZControl(strDID, 4);
			//NativeCaller.PPPPPTZControl(strDID, 5);
//			NativeCaller.PPPPPTZControl(strDID, 1116);
//			NativeCaller.PPPPPTZControl(strDID, 1117);
			break;
		case R.id.imgright1:
			// ptz_frame.setText("30FPS");
			//NativeCaller.PPPPPTZControl(strDID, 4);
			//NativeCaller.PPPPPTZControl(strDID, 7);
			//NativeCaller.PPPPPTZControl(strDID, 1114);
//			NativeCaller.PPPPPTZControl(strDID, 1115);
			break;
		default:
			break;
		}
	}

	private void dismissBrightAndContrastProgress() {
		if (mPopupWindowProgress != null && mPopupWindowProgress.isShowing()) {
			mPopupWindowProgress.dismiss();
			mPopupWindowProgress = null;
		}
	}

	private void showBottom() {
		if (isUpDownPressed) {
			isUpDownPressed = false;
			ptzOtherSetAnimView.startAnimation(dismissAnim);
			ptzOtherSetAnimView.setVisibility(View.GONE);
		} else {
			// showResolution();
			isUpDownPressed = true;
			ptzOtherSetAnimView.startAnimation(showAnim);
			ptzOtherSetAnimView.setVisibility(View.VISIBLE);
		}
	}

	private String getDateTime() {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String strDate = f.format(d);
		Log.d("tag", "record strDate:" + strDate);
		return strDID + "!" + "LOD_" + strDate;
	}

	private void setBrightOrContrast(final int type) {
		Log.i(LOG_TAG, "type:" + type + "  bInitCameraParam:"
				+ bInitCameraParam);
		if (!bInitCameraParam) {
			return;
		}
		int width = getWindowManager().getDefaultDisplay().getWidth();
		LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.brightprogress, null);
		SeekBar seekBar = (SeekBar) layout.findViewById(R.id.brightseekBar1);
		seekBar.setMax(255);
		switch (type) {
		case BRIGHT:
			seekBar.setProgress(nBrightness);
			break;
		case CONTRAST:
			seekBar.setProgress(nContrast);
			break;
		default:
			break;
		}
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				int progress = seekBar.getProgress();
				switch (type) {
				case BRIGHT:
					nBrightness = progress;
					NativeCaller.PPPPCameraControl(strDID, BRIGHT, nBrightness);
					break;
				case CONTRAST:
					nContrast = progress;
					NativeCaller.PPPPCameraControl(strDID, CONTRAST, nContrast);
					break;
				default:
					break;
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {

			}

			@Override
			public void onProgressChanged(SeekBar arg0, int progress,
					boolean arg2) {

			}
		});

		mPopupWindowProgress = new PopupWindow(layout, width / 2, 60);
		mPopupWindowProgress.showAtLocation(findViewById(R.id.play),
				Gravity.TOP, 0, 60);
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		return false;
	}

	private MyRender myRender = null;
	private GLSurfaceView myGlSurfaceView = null;
	private ImageButton ptzTakepic = null;

	// private Bitmap resultBmp;

	@Override
	protected void onDestroy() {
		exit = false;
		if (takeVideoThread != null) {
			takeVideoThread.stopThread();
		}
		isTakeVideo = false;
		
		
		Thread tempThread=null;
		try
		{
			tempThread=new Thread( new Runnable() {
			public void run() {
				
				
				NativeCaller.StopPPPPLivestream(strDID);
				if (!isDateComeOn) {
					//NativeCaller.StopPPPP(strDID);
					//NativeCaller.Free();
					StartPPPP(strDID, strUser, strPwd);
				}
				
				
			}
		});
			tempThread.start();
		}catch(Exception ex)
		{
			if(tempThread!=null)
			tempThread.stop();
		}
		
		BridgeService.setDateTimeInterface(null);
		BridgeService.setPlayInterface(null);
		if (popupWindow_show_you != null && popupWindow_show_you.isShowing()) {
			popupWindow_show_you.dismiss();
		}

		isOneShow = true;
		
		upDateLanguageonConfigurationChange();
		
		super.onDestroy();
	}

	/***
	 * BridgeService callback
	 * 
	 * **/
	int ircut1=0;
	@Override
	public void callBackCameraParamNotify(String did, int resolution,
			int brightness, int contrast, int hue, int saturation, int flip,
			int fram,int ircut) {
		// Log.d(LOG_TAG, "CameraParamNotify...did:" + did + " brightness: "
		// + brightness + " resolution: " + resolution + " contrast: "
		// + contrast + " hue: " + hue + " saturation: " + saturation
		// + " flip: " + flip + "fram:" + fram);
		// Log.d("tag", "contrast:" + contrast + " brightness:" + brightness);
		ircut1 = ircut;
		frame = fram;
		nBrightness = brightness;
		nContrast = contrast;
		nResolution = resolution;

		Message msg = new Message();
		msg.what = 3;
		mHandler.sendMessage(msg);

		bInitCameraParam = true;
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				 
				//ptz_frame.setText(frame + "FPS");
				// ptzResolutoin.sett
				Log.d("xqtest", "ircut====="+ircut1);
				switch(ircut1){
				case 0:
				{
					button_i1.setTextColor(0xff0044aa);
					button_i2.setTextColor(0xff000000);					
					button_i3.setTextColor(0xff000000);	
				} 
				break;
				case 1:
				{
					button_i2.setTextColor(0xff0044aa);
					button_i1.setTextColor(0xff000000);
					button_i3.setTextColor(0xff000000);	
				}
				break;
				case 2:
					button_i1.setTextColor(0xff000000);	
					button_i3.setTextColor(0xff0044aa);
					button_i2.setTextColor(0xff000000);	
					break;
				}				
				switch (nResolution) {
				case 1:
					// qvga
					ptzResolutoin.setText("QVGA");
					textViewWH.setText("W:320/H:240");
					button_bit1.setTextColor(0xff0044aa);
					button_bit2.setTextColor(0xff000000);
					button_bit_4.setTextColor(0xff000000);
					break;
				case 0:
					// vga
					ptzResolutoin.setText("VGA");
					textViewWH.setText("W:640/H:480");
					button_bit_4.setTextColor(0xff000000);
					button_bit1.setTextColor(0xff000000);
					button_bit2.setTextColor(0xff000000);
					if (ContentCommon.viewAlpha){
						button_bit1.setTextColor(0xff0044aa);
					}else{
						button_bit_4.setTextColor(0xff0044aa);
					}
						
					break;
				case 3:
					// 720
					ptzResolutoin.setText("720P");
					textViewWH.setText("W:1280/H:720");
					button_bit_4.setTextColor(0xff0044aa);
					button_bit2.setTextColor(0xff000000);
					button_bit1.setTextColor(0xff000000);
					break;
				default:
					break;
				}
			}
		});

		switch (flip) {
		case 0: // normal
			m_bUpDownMirror = false;
			m_bLeftRightMirror = false;
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					 
					ptzVertMirror2.setImageResource(R.drawable.ptz_vert_mirror);
					ptzHoriMirror2.setImageResource(R.drawable.ptz_hori_mirror);
				}
			});

			break;
		case 1: // up down mirror
			m_bUpDownMirror = true;
			m_bLeftRightMirror = false;
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					 
					ptzHoriMirror2.setImageResource(R.drawable.ptz_hori_mirror);
					ptzVertMirror2
							.setImageResource(R.drawable.ptz_vert_mirror_press);
				}
			});
			break;
		case 2: // left right mirror
			m_bUpDownMirror = false;
			m_bLeftRightMirror = true;
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					 
					ptzHoriMirror2
							.setImageResource(R.drawable.ptz_hori_mirror_press);
					ptzVertMirror2.setImageResource(R.drawable.ptz_vert_mirror);
				}
			});
			break;
		case 3: // all mirror
			m_bUpDownMirror = true;
			m_bLeftRightMirror = true;
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					 
					ptzVertMirror2
							.setImageResource(R.drawable.ptz_vert_mirror_press);
					ptzHoriMirror2
							.setImageResource(R.drawable.ptz_hori_mirror_press);
				}
			});
			break;
		default:
			break;
		}

	}

	private int isH264Data = 0;

	/***
	 * BridgeService callback
	 * 
	 * **/
	@Override
	public void callBaceVideoData(String did, byte[] videobuf, int h264Data,
			int len,int bufsize, int width, int height, int tim) {
		Log.i("xqtest", "Call VideoData...h264Data: " + h264Data + " len: "
		+ len + " videobuf len: " + videobuf.length + " ,width：" + width + " ,height:" + height);
		/*
		if (!did.endsWith(strDID)) {
			return;
		}
		*/
		if (!did.equalsIgnoreCase(strDID)) {
			return;
		}		// i1++;
		
		if (!bDisplayFinished) {
			Log.d(LOG_TAG, "return bDisplayFinished");
			return;
		}
		videodata=videobuf;
		videoDataLen=len;
		isH264Data = h264Data;
		bDisplayFinished = false;
		Message msg=new Message();
		
		if (isH264Data == 1) { // H264
			nVideoWidth = width;
			nVideoHeight = height;
			if (isTakepic) {
				isTakepic = false;
				byte[] rgb = new byte[width * height * 2];
				NativeCaller.YUV4202RGB565(videobuf, rgb, width, height);
				ByteBuffer buffer = ByteBuffer.wrap(rgb);
				mBmp = Bitmap
						.createBitmap(width, height, Bitmap.Config.RGB_565);
				mBmp.copyPixelsFromBuffer(buffer);
				takePicture(mBmp);
			}
			isH264 = true;
			msg.what=1;
		} else { // MJPEG
			timeshow = "["+videoDataLen + "B]  ["+nVideoWidth+"x"+nVideoHeight+"]";
			if (isTakeVideo && takeVideoThread != null) {
				takeVideoThread
						.addVideo(videobuf, 0, nVideoWidth, nVideoHeight);
			}
			msg.what = 2;
		}	
		mHandler.sendMessage(msg);
	}

	/***
	 * BridgeService callback
	 * 
	 * **/
	@Override
	public void callBackMessageNotify(String did, int msgType, int param) {
		Log.d("tag", "MessageNotify did: " + did + " msgType: " + msgType
				+ " param: " + param);
		if (bManualExit)
			return;

		if (msgType == ContentCommon.PPPP_MSG_TYPE_STREAM) {
			nStreamCodecType = param;
			bGetStreamCodecType = true;
			Message msgMessage = new Message();
			msgStreamCodecHandler.sendMessage(msgMessage);
			return;
		}

		if (msgType != ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS) {
			return;
		}

		if (!did.equals(strDID)) {
			return;
		}
		Log.d("test", "disconnection");
		Message msg = new Message();
		msg.what = 1;

		msgHandler.sendMessage(msg);
	}

	/***
	 * BridgeService callback
	 * 
	 * **/

	@Override
	public void callBackAudioData(byte[] pcm, int len) {
		// Log.d(LOG_TAG, "AudioData: len :+ " + len);
		if (isTakeVideo && takeVideoThread != null) {
			takeVideoThread.addAudio(pcm);
		}
		if (!audioPlayer.isAudioPlaying()) {
			return;
		}
		CustomBufferHead head = new CustomBufferHead();
		CustomBufferData data = new CustomBufferData();
		head.length = len;
		head.startcode = AUDIO_BUFFER_START_CODE;
		data.head = head;
		data.data = pcm;
		AudioBuffer.addData(data);
	}

	/***
	 * BridgeService callback
	 * 
	 * **/

	@Override
	public void callBackH264Data(String did, byte[] h264, int type, int size) {

		if (!did.endsWith(strDID)) {
			Log.d("testTakeVideo", "!did.endsWith(strDID)" + "   did:" + did
					+ "  strDID:" + strDID);
			return;
		}
		if (isTakeVideo && takeVideoThread != null) {
			takeVideoThread.addVideo(h264, type, nVideoWidth, nVideoHeight);
		}
	}

	@Override
	public void callBackDatetimeParams(String did, int now, int tz,
			int ntp_enable, String ntp_svr, int xialingshi) {
		 
		setTimeZone(tz);
		Log.d("tag", "timestr:" + tzStr + "  tz:" + tz);
	}

	@Override
	public void callBackSetSystemParamsResult(String did, int paramType,
			int result) {
		 

	}

	private String setDeviceTime(long millisutc, String tz) {

		TimeZone timeZone = TimeZone.getTimeZone(tz);
		Calendar calendar = Calendar.getInstance(timeZone);
		calendar.setTimeInMillis(millisutc);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String months = "";
		if (month < 10) {
			months = "0" + month;
		} else {
			months = String.valueOf(month);
		}
		String strDay = "";
		if (day < 10) {
			strDay = "0" + day;
		} else {
			strDay = String.valueOf(day);
		}
		String strHour = "";
		if (hour < 10) {
			strHour = "0" + hour;
		} else {
			strHour = String.valueOf(hour);
		}
		String strMinute = "";
		if (minute < 10) {
			strMinute = "0" + minute;
		} else {
			strMinute = String.valueOf(minute);
		}
		String strSecond = "";
		if (second < 10) {
			strSecond = "0" + second;
		} else {
			strSecond = String.valueOf(second);
		}
		// return strWeek + "," + day + " " + strMonth + year + " " + strHour
		// + ":" + strMinute + ":" + strSecond + "    UTC";
		return year + "-" + months + "-" + strDay + "   " + strHour + ":"
				+ strMinute + ":" + strSecond;
	}

	private void setTimeZone(int tz) {
		switch (tz) {
		case 39600:
			tzStr = "GMT-11:00";
			break;
		case 36000:
			tzStr = "GMT-10:00";
			break;
		case 32400:
			tzStr = "GMT-09:00";
			break;
		case 28800:
			tzStr = "GMT-08:00";
			break;
		case 25200:
			tzStr = "GMT-07:00";
			break;
		case 21600:
			tzStr = "GMT-06:00";
			break;
		case 18000:
			tzStr = "GMT-05:00";
			break;
		case 14400:
			tzStr = "GMT-04:00";
			break;
		case 12600:
			tzStr = "GMT-03:30";
			break;
		case 10800:
			tzStr = "GMT-03:00";
			break;
		case 7200:
			tzStr = "GMT-02:00";
			break;
		case 3600:
			tzStr = "GMT-01:00";
			break;
		case 0:
			tzStr = "GMT";
			break;
		case -3600:
			tzStr = "GMT+01:00";
			break;
		case -7200:
			tzStr = "GMT+02:00";
			break;
		case -10800:
			tzStr = "GMT+03:00";
			break;
		case -12600:
			tzStr = "GMT+03:30";
			break;
		case -14400:
			tzStr = "GMT+04:00";
			break;
		case -16200:
			tzStr = "GMT+04:30";
			break;
		case -18000:
			tzStr = "GMT+05:00";
			break;
		case -19800:
			tzStr = "GMT+05:30";
			break;

		case -21600:
			tzStr = "GMT+06:00";
			break;
		case -25200:
			tzStr = "GMT+07:00";
			break;
		case -28800:
			tzStr = "GMT+08:00";
			break;
		case -32400:
			tzStr = "GMT+09:00";
			break;
		case -34200:
			tzStr = "GMT+09:30";
			break;
		case -36000:
			tzStr = "GMT+10:00";
			break;
		case -39600:
			tzStr = "GMT+11:00";
			break;
		case -43200:
			tzStr = "GMT+12:00";
			break;
		default:
			break;
		}
	};
}