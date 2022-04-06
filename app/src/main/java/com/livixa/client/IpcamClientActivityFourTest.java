package com.livixa.client;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import object.p2pipcam.bean.CameraParamsBean;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.nativecaller.NativeCaller;
import object.p2pipcam.system.SystemValue;
import object.p2pipcam.utils.DataBaseHelper;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.client.BridgeService.IPPlayInterface;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class IpcamClientActivityFourTest extends BaseActivity implements
		OnClickListener, OnItemClickListener, IPPlayInterface,
		OnLongClickListener {
	private static final String TAG = "IpcamClientActivity";
	private final int SNAPSHOT = 200;

	private static final String STR_DID = "did";
	private static final String STR_MSG_PARAM = "msgparam";
	private CameraInfoReceiver receiver = null;
	private DataBaseHelper helper = null;
	private static int cameraStatus;
	private ListView cameraListView = null;
	private int timeTag = 0;
	private int timeOne = 0;
	private int timeTwo = 0;
	private int timeTag2 = 0;
	private int timeOne2 = 0;
	private int timeTwo2 = 0;
	private int timeTag3 = 0;
	private int timeOne3 = 0;
	private int timeTwo3 = 0;
	private int timeTag4 = 0;
	private int timeOne4 = 0;
	private int timeTwo4 = 0;
	private int camerSum = 0;
	private ImageView imageView1, imageView2, imageView3, imageView4;
	Bitmap bitmap11;
	Bitmap bitmap22;
	Bitmap bitmap33;
	Bitmap bitmap44;
	int nVideoWidth11 = 0;
	int nVideoHeight22 = 0;
	byte[] sufVideoBytes1, sufVideoBytes2, sufVideoBytes3, sufVideoBytes4;
	boolean mbLoop = false;
	boolean ifDrawOver = false;
	int one = 0;
	int two = 0;
	private final int PLAYTAG1 = 1213;
	private final int PLAYTAG2 = 1214;
	private final int PLAYTAG3 = 1215;
	private final int PLAYTAG4 = 1216;
	private final int SLEEPTIME = 200;
	private ArrayList<String> strDidList;
	int screenHeight;
	int screenWidth;
	private Bitmap bitmap_defult;
	private PopupWindow popupWindow_about;
	private TextView textView_did;
	private int TAGPlay = 0;
	private int imageTag1 = 0;
	private int imageTag2 = 0;
	private int imageTag3 = 0;
	private int imageTag4 = 0;
	private Map<String, String> maps;
	private Intent in;
	
	private TextView textView1, textView2, textView3, textView4;
	private int bitMapWidth = 0;
	private int bitMapHight = 0;
	private ImageButton imageButton_hind = null;
	private boolean ifHind = true;
	private boolean ssidTag1 = false;
	private boolean ssidTag2 = false;
	private boolean ssidTag3 = false;
	private boolean ssidTag4 = false;
	private int image1Width, image2Width, image3Width, image4Width,
			image1Higth, image2Higth, image3Higth, image4Higth;
	private int image1IsH264, image2IsH264, image3IsH264, image4IsH264;
	private int ifImageTag1, ifImageTag2, ifImageTag3, ifImageTag4;
	private boolean ifOver = true;
	private String name1, name2, name3, name4;
	private CameraParamsBean bean;
	// private Animation animation;
	private LinearLayout linear;
	private boolean PlayTag;
	private LinearLayout connection_show1, connection_show2, connection_show3,
			connection_show4;
	private int connectionTag1 = 3;
	private int connectionTag2 = 3;
	private int connectionTag3 = 3;
	private int connectionTag4 = 3;
	// private FrameLayout frameLayout1, frameLayout2, frameLayout3,
	// frameLayout4;
	// ����������
	private Matrix matrix;
	private boolean copyTag1 = true;
	private boolean copyTag2 = true;
	private boolean copyTag3 = true;
	private boolean copyTag4 = true;

	public static void changerCameraStatus(int status) {
		cameraStatus = status;
	}

	private Handler noLineHandler = new Handler() {
		public void handleMessage(Message msg) {
			String did = msg.getData().getString("no_line_did");
			switch (msg.what) {
			case 110:

				if (imageView1.getTag().toString().equals(did)
						&& strDidList.size() != 0) {
					camerSum--;
					ssidTag1 = false;
					strDidList.remove(strDidList.indexOf(imageView1.getTag()
							.toString()));
					connection_show1.setVisibility(View.GONE);
					imageView1.setTag(1);
					textView1.setVisibility(View.GONE);
					imageTag1 = 0;
					new AsyncTask<Void, Void, Void>() {

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

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							// pppp_status_disconnect
							imageView1.setImageBitmap(bitmap_defult);
							connectionTag1 = 1;
							showToast(name1
									+ getResources().getString(
											R.string.pppp_status_disconnect));
							if (popupWindow_about != null
									&& popupWindow_about.isShowing()) {
								popupWindow_about.dismiss();
							}
						}
					}.execute();
				} else if (imageView2.getTag().toString().equals(did)
						&& strDidList.size() != 0) {
					camerSum--;
					ssidTag2 = false;
					strDidList.remove(strDidList.indexOf(imageView2.getTag()
							.toString()));
					connection_show2.setVisibility(View.GONE);
					imageView2.setTag(1);
					textView2.setVisibility(View.GONE);
					imageTag2 = 0;
					new AsyncTask<Void, Void, Void>() {

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

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							imageView2.setImageBitmap(bitmap_defult);
							connectionTag2 = 1;
							showToast(name2
									+ getResources().getString(
											R.string.pppp_status_disconnect));
							if (popupWindow_about != null
									&& popupWindow_about.isShowing()) {
								popupWindow_about.dismiss();
							}
						}
					}.execute();

				} else if (imageView3.getTag().toString().equals(did)
						&& strDidList.size() != 0) {
					camerSum--;
					ssidTag3 = false;
					strDidList.remove(strDidList.indexOf(imageView3.getTag()
							.toString()));
					imageView3.setTag(1);
					textView3.setVisibility(View.GONE);
					connection_show3.setVisibility(View.GONE);
					imageTag3 = 0;
					new AsyncTask<Void, Void, Void>() {

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

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							imageView3.setImageBitmap(bitmap_defult);
							connectionTag3 = 1;
							showToast(name3
									+ getResources().getString(
											R.string.pppp_status_disconnect));
							if (popupWindow_about != null
									&& popupWindow_about.isShowing()) {
								popupWindow_about.dismiss();
							}
						}
					}.execute();
				} else if (imageView4.getTag().toString().equals(did)
						&& strDidList.size() != 0) {
					camerSum--;
					ssidTag4 = false;
					strDidList.remove(strDidList.indexOf(imageView4.getTag()
							.toString()));
					imageView4.setTag(1);
					textView4.setVisibility(View.GONE);
					connection_show4.setVisibility(View.GONE);
					imageTag4 = 0;
					new AsyncTask<Void, Void, Void>() {

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

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							imageView4.setImageBitmap(bitmap_defult);
							connectionTag4 = 1;
							showToast(name4
									+ getResources().getString(
											R.string.pppp_status_disconnect));
							if (popupWindow_about != null
									&& popupWindow_about.isShowing()) {
								popupWindow_about.dismiss();
							}
						}
					}.execute();

				}
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		upDateLanguageonConfigurationChange();
		
		
		setContentView(R.layout.ipcamclientftestone);
		findView();
		setControlListener();
		SystemValue.TAG_CAMERLIST = 1;
		screenHeight = getWindowManager().getDefaultDisplay().getHeight();
		screenWidth = getWindowManager().getDefaultDisplay().getWidth();
		bitMapWidth = screenWidth / 2;
		bitMapHight = screenHeight / 2;
		helper = DataBaseHelper.getInstance(this);
		strDidList = new ArrayList<String>();
		// initCameraList();
		BridgeService.setIpPlayInterface(this);
		

		maps = new HashMap<String, String>();
		// animation = AnimationUtils.loadAnimation(this,
		// R.anim.my_alpha_action);
		// imageView1.setAnimation(animation);
		// imageView2.setAnimation(animation);
		// imageView3.setAnimation(animation);
		// imageView4.setAnimation(animation);
		matrix = new Matrix();
		// ����ԭͼ
		matrix.postScale(0.5f, 0.3f);
		initExitPopupWindow2();
	}

	private Bitmap returnBitmap(Bitmap bitmap) {
		if (bitmap.getWidth() < 600 || bitmap.getHeight() < 300) {
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
		} else {
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, 600, 300);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
		}
		return bitmap;
	}

	private void findView() {
		cameraListView = (ListView) findViewById(R.id.listviewCamera);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		textView1 = (TextView) findViewById(R.id.textView1_ssid);
		textView2 = (TextView) findViewById(R.id.textView2_ssid);
		textView3 = (TextView) findViewById(R.id.textView3_ssid);
		textView4 = (TextView) findViewById(R.id.textView4_ssid);
		linear = (LinearLayout) findViewById(R.id.linear);
		connection_show1 = (LinearLayout) findViewById(R.id.connection_show1);
		connection_show2 = (LinearLayout) findViewById(R.id.connection_show2);
		connection_show3 = (LinearLayout) findViewById(R.id.connection_show3);
		connection_show4 = (LinearLayout) findViewById(R.id.connection_show4);
		imageButton_hind = (ImageButton) findViewById(R.id.imagebutton_hind);
		imageView1.setTag(1);
		imageView2.setTag(1);
		imageView3.setTag(1);
		imageView4.setTag(1);
		// frameLayout1 = (FrameLayout) findViewById(R.id.frame1);
		// frameLayout2 = (FrameLayout) findViewById(R.id.frame2);
		// frameLayout3 = (FrameLayout) findViewById(R.id.frame3);
		// frameLayout4 = (FrameLayout) findViewById(R.id.frame4);
	}

	private void setControlListener() {
		cameraListView.setOnItemClickListener(this);
		imageView1.setOnClickListener(this);
		imageView2.setOnClickListener(this);
		imageView3.setOnClickListener(this);
		imageView4.setOnClickListener(this);
		imageView1.setOnLongClickListener(this);
		imageView2.setOnLongClickListener(this);
		imageView3.setOnLongClickListener(this);
		imageView4.setOnLongClickListener(this);
		imageButton_hind.setOnClickListener(this);
	}

	public void initExitPopupWindow2() {
		LayoutInflater li = LayoutInflater.from(this);
		View popv = li.inflate(R.layout.popup, null);
		Button button_load = (Button) popv.findViewById(R.id.add_check_load);
		Button button_phone = (Button) popv.findViewById(R.id.add_check_phone);
		textView_did = (TextView) popv.findViewById(R.id.textView1_did);
		popupWindow_about = new PopupWindow(popv,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popupWindow_about.setAnimationStyle(R.style.AnimationPreview);
		popupWindow_about.setFocusable(true);
		popupWindow_about.setOutsideTouchable(true);
		popupWindow_about.setBackgroundDrawable(new ColorDrawable(0));
		// popupWindow.update();
		button_load.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				in = new Intent(IpcamClientActivityFourTest.this,
						PlayActivitySport.class);//xq
				in.putExtra("play_four_tag", 1);
				in.putExtra(ContentCommon.STR_CAMERA_TYPE,
						ContentCommon.CAMERA_TYPE_MJPEG);
				in.putExtra(ContentCommon.STR_STREAM_TYPE,
						ContentCommon.MJPEG_SUB_STREAM);
				switch (TAGPlay) {
				case 1:
					in.putExtra(ContentCommon.STR_CAMERA_NAME,
							returnString(R.string.play_four_show1) + "---"
									+ name1);
					in.putExtra(ContentCommon.STR_CAMERA_ID, imageView1
							.getTag().toString());
					break;
				case 2:
					in.putExtra(ContentCommon.STR_CAMERA_NAME,
							returnString(R.string.play_four_show2) + "---"
									+ name2);
					in.putExtra(ContentCommon.STR_CAMERA_ID, imageView2
							.getTag().toString());
					break;
				case 3:
					in.putExtra(ContentCommon.STR_CAMERA_NAME,
							returnString(R.string.play_four_show3) + "---"
									+ name3);
					in.putExtra(ContentCommon.STR_CAMERA_ID, imageView3
							.getTag().toString());
					break;
				case 4:
					in.putExtra(ContentCommon.STR_CAMERA_NAME,
							returnString(R.string.play_four_show4) + "---"
									+ name4);
					in.putExtra(ContentCommon.STR_CAMERA_ID, imageView4
							.getTag().toString());
					break;
				default:
					break;
				}
				new AsyncTask<Void, Void, Void>() {
					protected void onPreExecute() {
						
						
						WaitingStaticProgress.showProgressDialog("", IpcamClientActivityFourTest.this);
					};

					@Override
					protected Void doInBackground(Void... params) {
						for (int i = 0; i < strDidList.size(); i++) {
							NativeCaller.StopPPPPLivestream(strDidList.get(i));
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
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
						WaitingStaticProgress.hideProgressDialog();
						startActivity(in);
						overridePendingTransition(R.anim.in_from_right,
								R.anim.out_to_left);

					};
				}.execute();

				popupWindow_about.dismiss();
			}
		});
		button_phone.setOnClickListener(new OnClickListener() {
			// �Ͽ�
			@Override
			public void onClick(View v) {
				switch (TAGPlay) {
				case 1:
					NativeCaller.StopPPPPLivestream(imageView1.getTag()
							.toString());
					ssidTag1 = false;
					strDidList.remove(strDidList.indexOf(imageView1.getTag()
							.toString()));
					imageView1.setTag(1);
					textView1.setVisibility(View.GONE);
					connection_show1.setVisibility(View.GONE);
					imageTag1 = 0;
					new AsyncTask<Void, Void, Void>() {

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

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							imageView1.setImageBitmap(bitmap_defult);
							connectionTag1 = 0;
						}
					}.execute();

					break;
				case 2:
					NativeCaller.StopPPPPLivestream(imageView2.getTag()
							.toString());
					ssidTag2 = false;
					strDidList.remove(strDidList.indexOf(imageView2.getTag()
							.toString()));

					imageView2.setTag(1);
					textView2.setVisibility(View.GONE);
					connection_show2.setVisibility(View.GONE);
					imageTag2 = 0;
					new AsyncTask<Void, Void, Void>() {

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

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							imageView2.setImageBitmap(bitmap_defult);
							connectionTag2 = 0;
						}
					}.execute();

					break;
				case 3:
					NativeCaller.StopPPPPLivestream(imageView3.getTag()
							.toString());
					ssidTag3 = false;
					strDidList.remove(strDidList.indexOf(imageView3.getTag()
							.toString()));
					imageView3.setTag(1);
					textView3.setVisibility(View.GONE);
					connection_show3.setVisibility(View.GONE);
					imageTag3 = 0;
					new AsyncTask<Void, Void, Void>() {

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

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							imageView3.setImageBitmap(bitmap_defult);
							connectionTag3 = 0;
						}
					}.execute();

					break;
				case 4:
					NativeCaller.StopPPPPLivestream(imageView4.getTag()
							.toString());
					ssidTag4 = false;
					strDidList.remove(strDidList.indexOf(imageView4.getTag()
							.toString()));
					imageView4.setTag(1);
					textView4.setVisibility(View.GONE);
					connection_show4.setVisibility(View.GONE);
					imageTag4 = 0;
					new AsyncTask<Void, Void, Void>() {

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

						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							imageView4.setImageBitmap(bitmap_defult);
							connectionTag4 = 0;
						}
					}.execute();

					break;
				default:
					break;
				}
				camerSum--;
				popupWindow_about.dismiss();
			}
		});
		popupWindow_about
				.setOnDismissListener(new PopupWindow.OnDismissListener() {

					@Override
					public void onDismiss() {
						// TODO Auto-generated method stub
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imagebutton_hind:
			if (ifHind) {
				cameraListView.setVisibility(View.GONE);
				imageButton_hind.setImageResource(R.drawable.hind_buttong_bg1);
				ifHind = false;
			} else {
				cameraListView.setVisibility(View.VISIBLE);
				imageButton_hind.setImageResource(R.drawable.hind_buttong_bg);
				ifHind = true;
			}
			break;
		case R.id.imageView1:
			if (imageTag1 == 1) {
				Date date = new Date();
				if (timeTag == 0) {
					timeOne = date.getSeconds();
					timeTag = 1;
				} else if (timeTag == 1) {
					timeTwo = date.getSeconds();
					if (timeTwo - timeOne <= 3) {
						// new AsyncTask<Void, Void, Void>() {
						// protected void onPreExecute() {
						// in = new Intent(
						// IpcamClientActivityFourTest.this,
						// PlayActivity.class);
						// in.putExtra("play_four_tag", 1);
						// in.putExtra(ContentCommon.STR_CAMERA_TYPE,
						// ContentCommon.CAMERA_TYPE_MJPEG);
						// in.putExtra(ContentCommon.STR_STREAM_TYPE,
						// ContentCommon.MJPEG_SUB_STREAM);
						// in.putExtra(ContentCommon.STR_CAMERA_NAME,
						// returnString(R.string.play_four_show1)
						// + "---" + name1);
						// in.putExtra(ContentCommon.STR_CAMERA_ID,
						// imageView1.getTag().toString());
						//
						// };
						//
						// @Override
						// protected Void doInBackground(Void... params) {
						// for (int i = 0; i < strDidList.size(); i++) {
						// if (strDidList.get(i).endsWith(
						// imageView1.getTag().toString())) {
						// continue;
						// }
						// NativeCaller.StopPPPPLivestream(strDidList
						// .get(i));
						// try {
						// Thread.sleep(500);
						// } catch (InterruptedException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						// }
						// return null;
						// }
						//
						// protected void onPostExecute(Void result) {
						// PlayTag = false;
						// bitmap11 = null;
						// bitmap22 = null;
						// bitmap33 = null;
						// bitmap44 = null;
						// startActivity(in);
						// overridePendingTransition(R.anim.in_from_right,
						// R.anim.out_to_left);
						// };
						// }.execute();
						new AsyncTask<Void, Void, Void>() {
							protected void onPreExecute() {
								WaitingStaticProgress.showProgressDialog("", IpcamClientActivityFourTest.this);
								in = new Intent(
										IpcamClientActivityFourTest.this,
										PlayActivitySport.class);//xq
								in.putExtra("play_four_tag", 1);
								in.putExtra(ContentCommon.STR_CAMERA_TYPE,
										ContentCommon.CAMERA_TYPE_MJPEG);
								in.putExtra(ContentCommon.STR_STREAM_TYPE,
										ContentCommon.MJPEG_SUB_STREAM);
								in.putExtra(ContentCommon.STR_CAMERA_NAME,
										returnString(R.string.play_four_show1)
												+ "---" + name1);
								in.putExtra(ContentCommon.STR_CAMERA_ID,
										imageView1.getTag().toString());
							};

							@Override
							protected Void doInBackground(Void... params) {
								for (int i = 0; i < strDidList.size(); i++) {
									NativeCaller.StopPPPPLivestream(strDidList
											.get(i));
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
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
								WaitingStaticProgress.hideProgressDialog();
								PlayTag = false;
								bitmap_defult = null;
								bitmap11 = null;
								bitmap22 = null;
								bitmap33 = null;
								bitmap44 = null;
								startActivity(in);
								overridePendingTransition(R.anim.in_from_right,
										R.anim.out_to_left);

							};
						}.execute();
						timeTag = 0;
					} else {
						timeTag = 1;
					}
				}
			}
			break;
		case R.id.imageView2:
			if (imageTag2 == 1) {
				Date date1 = new Date();
				if (timeTag2 == 0) {
					timeOne2 = date1.getSeconds();
					timeTag2 = 1;
				} else if (timeTag2 == 1) {
					timeTwo2 = date1.getSeconds();
					if (timeTwo2 - timeOne2 <= 3) {
						// new AsyncTask<Void, Void, Void>() {
						// protected void onPreExecute() {
						// in = new Intent(
						// IpcamClientActivityFourTest.this,
						// PlayActivity.class);
						// in.putExtra("play_four_tag", 1);
						// in.putExtra(ContentCommon.STR_CAMERA_TYPE,
						// ContentCommon.CAMERA_TYPE_MJPEG);
						// in.putExtra(ContentCommon.STR_STREAM_TYPE,
						// ContentCommon.MJPEG_SUB_STREAM);
						// in.putExtra(ContentCommon.STR_CAMERA_NAME,
						// returnString(R.string.play_four_show1)
						// + "---" + name2);
						// in.putExtra(ContentCommon.STR_CAMERA_ID,
						// imageView2.getTag().toString());
						//
						// };
						//
						// @Override
						// protected Void doInBackground(Void... params) {
						// for (int i = 0; i < strDidList.size(); i++) {
						// if (strDidList.get(i).endsWith(
						// imageView2.getTag().toString())) {
						// continue;
						// }
						// NativeCaller.StopPPPPLivestream(strDidList
						// .get(i));
						// try {
						// Thread.sleep(500);
						// } catch (InterruptedException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						// }
						// return null;
						// }
						//
						// protected void onPostExecute(Void result) {
						// PlayTag = false;
						// bitmap11 = null;
						// bitmap33 = null;
						// bitmap22 = null;
						// bitmap44 = null;
						// startActivity(in);
						// overridePendingTransition(R.anim.in_from_right,
						// R.anim.out_to_left);
						// };
						// }.execute();
						new AsyncTask<Void, Void, Void>() {
							protected void onPreExecute() {
								WaitingStaticProgress.showProgressDialog("", IpcamClientActivityFourTest.this);
								in = new Intent(
										IpcamClientActivityFourTest.this,
										PlayActivitySport.class);//xq
								in.putExtra("play_four_tag", 1);
								in.putExtra(ContentCommon.STR_CAMERA_TYPE,
										ContentCommon.CAMERA_TYPE_MJPEG);
								in.putExtra(ContentCommon.STR_STREAM_TYPE,
										ContentCommon.MJPEG_SUB_STREAM);
								in.putExtra(ContentCommon.STR_CAMERA_NAME,
										returnString(R.string.play_four_show1)
												+ "---" + name2);
								in.putExtra(ContentCommon.STR_CAMERA_ID,
										imageView2.getTag().toString());
							};

							@Override
							protected Void doInBackground(Void... params) {
								for (int i = 0; i < strDidList.size(); i++) {
									NativeCaller.StopPPPPLivestream(strDidList
											.get(i));
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
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
								WaitingStaticProgress.hideProgressDialog();
								PlayTag = false;
								bitmap_defult = null;
								bitmap11 = null;
								bitmap22 = null;
								bitmap33 = null;
								bitmap44 = null;
								startActivity(in);
								overridePendingTransition(R.anim.in_from_right,
										R.anim.out_to_left);

							};
						}.execute();
						timeTag2 = 0;
					} else {
						timeTag2 = 1;
					}
				}
			}
			break;
		case R.id.imageView3:
			if (imageTag3 == 1) {
				Date date3 = new Date();
				if (timeTag3 == 0) {
					timeOne3 = date3.getSeconds();
					timeTag3 = 1;
				} else if (timeTag3 == 1) {
					timeTwo3 = date3.getSeconds();
					if (timeTwo3 - timeOne3 <= 3) {
						// new AsyncTask<Void, Void, Void>() {
						// protected void onPreExecute() {
						// in = new Intent(
						// IpcamClientActivityFourTest.this,
						// PlayActivity.class);
						// in.putExtra("play_four_tag", 1);
						// in.putExtra(ContentCommon.STR_CAMERA_TYPE,
						// ContentCommon.CAMERA_TYPE_MJPEG);
						// in.putExtra(ContentCommon.STR_STREAM_TYPE,
						// ContentCommon.MJPEG_SUB_STREAM);
						// in.putExtra(ContentCommon.STR_CAMERA_NAME,
						// returnString(R.string.play_four_show1)
						// + "---" + name3);
						// in.putExtra(ContentCommon.STR_CAMERA_ID,
						// imageView3.getTag().toString());
						//
						// };
						//
						// @Override
						// protected Void doInBackground(Void... params) {
						// for (int i = 0; i < strDidList.size(); i++) {
						// if (strDidList.get(i).endsWith(
						// imageView3.getTag().toString())) {
						// continue;
						// }
						// NativeCaller.StopPPPPLivestream(strDidList
						// .get(i));
						// try {
						// Thread.sleep(500);
						// } catch (InterruptedException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						// }
						// return null;
						// }
						//
						// protected void onPostExecute(Void result) {
						// PlayTag = false;
						// bitmap22 = null;
						// bitmap11 = null;
						// bitmap44 = null;
						// bitmap33 = null;
						// startActivity(in);
						// overridePendingTransition(R.anim.in_from_right,
						// R.anim.out_to_left);
						// };
						// }.execute();
						new AsyncTask<Void, Void, Void>() {
							protected void onPreExecute() {
								WaitingStaticProgress.showProgressDialog("", IpcamClientActivityFourTest.this);
								in = new Intent(
										IpcamClientActivityFourTest.this,
										PlayActivitySport.class);//xq
								in.putExtra("play_four_tag", 1);
								in.putExtra(ContentCommon.STR_CAMERA_TYPE,
										ContentCommon.CAMERA_TYPE_MJPEG);
								in.putExtra(ContentCommon.STR_STREAM_TYPE,
										ContentCommon.MJPEG_SUB_STREAM);
								in.putExtra(ContentCommon.STR_CAMERA_NAME,
										returnString(R.string.play_four_show1)
												+ "---" + name3);
								in.putExtra(ContentCommon.STR_CAMERA_ID,
										imageView3.getTag().toString());
							};

							@Override
							protected Void doInBackground(Void... params) {
								for (int i = 0; i < strDidList.size(); i++) {
									NativeCaller.StopPPPPLivestream(strDidList
											.get(i));
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
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
								WaitingStaticProgress.hideProgressDialog();
								PlayTag = false;
								bitmap_defult = null;
								bitmap11 = null;
								bitmap22 = null;
								bitmap33 = null;
								bitmap44 = null;
								startActivity(in);
								overridePendingTransition(R.anim.in_from_right,
										R.anim.out_to_left);

							};
						}.execute();
						timeTag3 = 0;
					} else {
						timeTag3 = 1;
					}
				}
			}
			break;
		case R.id.imageView4:
			if (imageTag4 == 1) {
				Date date4 = new Date();
				if (timeTag4 == 0) {
					timeOne4 = date4.getSeconds();
					timeTag4 = 1;
				} else if (timeTag4 == 1) {
					timeTwo4 = date4.getSeconds();
					if (timeTwo4 - timeOne4 <= 3) {
						// new AsyncTask<Void, Void, Void>() {
						// protected void onPreExecute() {
						// in = new Intent(
						// IpcamClientActivityFourTest.this,
						// PlayActivity.class);
						// in.putExtra("play_four_tag", 1);
						// in.putExtra(ContentCommon.STR_CAMERA_TYPE,
						// ContentCommon.CAMERA_TYPE_MJPEG);
						// in.putExtra(ContentCommon.STR_STREAM_TYPE,
						// ContentCommon.MJPEG_SUB_STREAM);
						// in.putExtra(ContentCommon.STR_CAMERA_NAME,
						// returnString(R.string.play_four_show1)
						// + "---" + name4);
						// in.putExtra(ContentCommon.STR_CAMERA_ID,
						// imageView4.getTag().toString());
						//
						// };
						//
						// @Override
						// protected Void doInBackground(Void... params) {
						// for (int i = 0; i < strDidList.size(); i++) {
						// if (strDidList.get(i).endsWith(
						// imageView4.getTag().toString())) {
						// continue;
						// }
						// NativeCaller.StopPPPPLivestream(strDidList
						// .get(i));
						// try {
						// Thread.sleep(500);
						// } catch (InterruptedException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						// }
						// return null;
						// }
						//
						// protected void onPostExecute(Void result) {
						// PlayTag = false;
						// bitmap22 = null;
						// bitmap11 = null;
						// bitmap33 = null;
						// bitmap44 = null;
						// startActivity(in);
						// overridePendingTransition(R.anim.in_from_right,
						// R.anim.out_to_left);
						// };
						// }.execute();
						new AsyncTask<Void, Void, Void>() {
							protected void onPreExecute() {
								WaitingStaticProgress.showProgressDialog("", IpcamClientActivityFourTest.this);
								in = new Intent(
										IpcamClientActivityFourTest.this,
										PlayActivitySport.class);//xq
								in.putExtra("play_four_tag", 1);
								in.putExtra(ContentCommon.STR_CAMERA_TYPE,
										ContentCommon.CAMERA_TYPE_MJPEG);
								in.putExtra(ContentCommon.STR_STREAM_TYPE,
										ContentCommon.MJPEG_SUB_STREAM);
								in.putExtra(ContentCommon.STR_CAMERA_NAME,
										returnString(R.string.play_four_show1)
												+ "---" + name4);
								in.putExtra(ContentCommon.STR_CAMERA_ID,
										imageView4.getTag().toString());
							};

							@Override
							protected Void doInBackground(Void... params) {
								for (int i = 0; i < strDidList.size(); i++) {
									NativeCaller.StopPPPPLivestream(strDidList
											.get(i));
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
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
								WaitingStaticProgress.hideProgressDialog();
								PlayTag = false;
								bitmap_defult = null;
								bitmap11 = null;
								bitmap22 = null;
								bitmap33 = null;
								bitmap44 = null;
								startActivity(in);
								overridePendingTransition(R.anim.in_from_right,
										R.anim.out_to_left);

							};
						}.execute();
						timeTag4 = 0;
					} else {
						timeTag4 = 1;
					}
				}
			}
			break;
		}
	}

	private void showPlayLastBmp(final String did) {
		new Thread() {
			public void run() {
				File div = new File(Environment.getExternalStorageDirectory(),
						"ipcam/pic");
				File file = new File(div, did + ".jpg");
				String filepath = file.getAbsolutePath();
				Bitmap bitmap = BitmapFactory.decodeFile(filepath);
				if (IpcamClientActivity.listAdapter.UpdateCameraImage(did,
						bitmap)) {
					PPPPMsgHandler.sendEmptyMessage(SNAPSHOT);
				}
			}
		}.start();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			// Intent intent = new Intent("finish");
			// sendBroadcast(intent);
			// /showSureDialog(IpcamClientActivityFour.this);
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Date date = new Date();
			// if (timeTag == 0) {
			// timeOne = date.getSeconds();
			// timeTag = 1;
			// Toast.makeText(IpcamClientActivityFour.this,
			// R.string.four_show_back, 0).show();
			// } else if (timeTag == 1) {
			// timeTwo = date.getSeconds();
			// if (timeTwo - timeOne <= 3) {
			// // Intent intent = new Intent("initfour");
			// // sendBroadcast(intent);
			// // Intent intent = new Intent(IpcamClientActivityFour.this,
			// // MainActivity.class);
			// // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// // startActivity(intent);
			//
			// timeTag = 0;
			// } else {
			// timeTag = 1;
			// Toast.makeText(IpcamClientActivityFour.this,
			// R.string.four_show_back, 0).show();
			// }
			// }
			showSureDialogF();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void showSureDialogF() {
		/*AlertDialog.Builder builder = new AlertDialog.Builder(
				IpcamClientActivityFourTest.this);
		builder.setIcon(R.drawable.app);
		builder.setTitle(returnString(R.string.play_four_exit1));
		builder.setMessage(R.string.play_four_exit2);
		builder.setPositiveButton(R.string.str_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Process.killProcess(Process.myPid());
						SystemValue.TAG_CAMERLIST = 0;
						finish();
					}
				});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.show();*/
		
        final CustomAlertDialogueTwoButtons myASlertDialog=new CustomAlertDialogueTwoButtons(IpcamClientActivityFourTest.this,getString(R.string.exit_play_show));
		
		myASlertDialog.setListner(new CustomDialogueTwoButtonsClickListner() {
			
			@Override
			public void onCustomDialoguePositiveClick() {
				
				myASlertDialog.dismiss();
				SystemValue.TAG_CAMERLIST = 0;
				finish();
				
			}
			
			@Override
			public void onCustomDialogueNegativeClick() {
				
				myASlertDialog.dismiss();
				
			}
		});
		
		myASlertDialog.show();
	}

	/**
	 * ��UI�߳���ˢ�½���״̬
	 * **/
	private Handler PPPPMsgHandler = new Handler() {
		public void handleMessage(Message msg) {
			Bundle bd = msg.getData();
			int msgParam = bd.getInt(STR_MSG_PARAM);
			int msgType = msg.what;
			String did = bd.getString(STR_DID);
			switch (msgType) {
			case ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS:
				// ���㲥��鿴����
				Intent intent = new Intent("camera_status_change");
				intent.putExtra(ContentCommon.STR_CAMERA_ID, did);
				intent.putExtra(ContentCommon.STR_PPPP_STATUS, msgParam);
				sendBroadcast(intent);

				if (IpcamClientActivity.listAdapter.UpdataCameraStatus(did,
						msgParam)) {
					IpcamClientActivity.listAdapter.notifyDataSetChanged();
					if (msgParam == ContentCommon.PPPP_STATUS_ON_LINE) {
						NativeCaller.PPPPGetSystemParams(did,
								ContentCommon.MSG_TYPE_GET_PARAMS);
					}
					if (msgParam == ContentCommon.PPPP_STATUS_INVALID_ID
							|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_FAILED
							|| msgParam == ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE
							|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT
							|| msgParam == ContentCommon.PPPP_STATUS_CONNECT_ERRER) {
						NativeCaller.StopPPPP(did);
					}
				}
				break;
			case ContentCommon.PPPP_MSG_TYPE_PPPP_MODE:
				if (IpcamClientActivity.listAdapter.UpdataCameraType(did,
						msgParam)) {
					// listAdapter.notifyDataSetChanged();
				}
				break;
			case SNAPSHOT:
				IpcamClientActivity.listAdapter.notifyDataSetChanged();
				break;

			case PLAYTAG1:
				Log.d("taggg", "test---hangler");
				imageView1.setImageBitmap(bitmap11);
				connection_show1.setVisibility(View.GONE);
				// bitmap11 = null;
				break;
			case PLAYTAG2:
				imageView2.setImageBitmap(bitmap22);
				connection_show2.setVisibility(View.GONE);
				// bitmap22 = null;
				break;
			case PLAYTAG3:
				imageView3.setImageBitmap(bitmap33);
				connection_show3.setVisibility(View.GONE);
				// bitmap33 = null;
				break;
			case PLAYTAG4:
				imageView4.setImageBitmap(bitmap44);
				connection_show4.setVisibility(View.GONE);
				// bitmap44 = null;
				break;
			}
		}
	};

	// private synchronized boolean delCameraFromdb(String did) {
	// boolean bRes = false;
	// if (helper.deleteCamera(did)) {
	// bRes = true;
	// }
	// return bRes;
	// }
	//
	private synchronized boolean UpdataCamera2db(String oldDID, String name,
			String did, String user, String pwd) {
		boolean bRes = false;
		if (helper.updateCamera(oldDID, name, did, user, pwd)) {
			bRes = true;
		}
		return bRes;
	}

	private synchronized void addCamera2db(String name, String did,
			String user, String pwd) {
		helper.createCamera(name, did, user, pwd);
	}

	//
	// /**
	// * ����ݿ��л�ȡ������б����
	// * **/
	// private void initCameraList() {
	// Cursor cursor = helper.fetchAllCameras();
	// if (cursor != null) {
	// while (cursor.moveToNext()) {
	// String name = cursor.getString(1);
	// String did = cursor.getString(2);
	// String user = cursor.getString(3);
	// String pwd = cursor.getString(4);
	// listAdapter.AddCamera(name, did, user, pwd);
	// }
	// listAdapter.notifyDataSetChanged();
	// }
	// if (cursor != null) {
	// cursor.close();
	// }
	// }

	@Override
	protected void onResume() {
		Log.d("IpcamClientActivity", "onResume()");
		super.onResume();
	}

	/**
	 * ���/�޸�/�û����õȷ��ص���Ϣ
	 * **/
	class CameraInfoReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if ("other".equals(action)) {
				IpcamClientActivity.listAdapter.sendCameraStatus();
			} else {
				int option = intent.getIntExtra(ContentCommon.CAMERA_OPTION,
						ContentCommon.INVALID_OPTION);
				if (option == ContentCommon.INVALID_OPTION)
					return;
				String strOldDID = "";
				final String name = intent
						.getStringExtra(ContentCommon.STR_CAMERA_NAME);
				final String did = intent
						.getStringExtra(ContentCommon.STR_CAMERA_ID);
				final String user = intent
						.getStringExtra(ContentCommon.STR_CAMERA_USER);
				final String pwd = intent
						.getStringExtra(ContentCommon.STR_CAMERA_PWD);

				if (option == ContentCommon.EDIT_CAMERA) { // �޸������
					strOldDID = intent
							.getStringExtra(ContentCommon.STR_CAMERA_OLD_ID);
					if (UpdataCamera2db(strOldDID, name, did, user, pwd)) {
						if (IpcamClientActivity.listAdapter.UpdateCamera(
								strOldDID, name, did, user, pwd)) {
							NativeCaller.PPPPGetSystemParams(did,
									ContentCommon.MSG_TYPE_GET_PARAMS);
							IpcamClientActivity.listAdapter
									.notifyDataSetChanged();
							NativeCaller.StopPPPP(did);
							StartPPPP(did, user, pwd);
						}
						Intent intentChange = new Intent(
								"del_add_modify_camera");
						intentChange
								.putExtra("type", ContentCommon.EDIT_CAMERA);
						intentChange.putExtra(ContentCommon.STR_CAMERA_ID, did);
						intentChange.putExtra("olddid", strOldDID);
						intentChange.putExtra("name", name);
						sendBroadcast(intentChange);
					}
				} else if (option == ContentCommon.CHANGE_CAMERA_USER) {
					strOldDID = intent
							.getStringExtra(ContentCommon.STR_CAMERA_OLD_ID);
					if (IpcamClientActivity.listAdapter.UpdateCamera(strOldDID,
							name, did, user, pwd)) {
						Log.d(TAG, "did:" + did + " user:" + user + " pwd:"
								+ pwd);
						IpcamClientActivity.listAdapter.notifyDataSetChanged();
						NativeCaller.StopPPPP(did);
						StartPPPP(did, user, pwd);
					}
				} else {
					if (IpcamClientActivity.listAdapter.getCount() < 20) {
						if (IpcamClientActivity.listAdapter.AddCamera(name,
								did, user, pwd)) {
							IpcamClientActivity.listAdapter
									.notifyDataSetChanged();
							StartPPPP(did, user, pwd);
							new Thread() {
								public void run() {

									addCamera2db(name, did, user, pwd);
									Intent intentAdd = new Intent(
											"del_add_modify_camera");
									intentAdd.putExtra("type",
											ContentCommon.ADD_CAMERA);
									intentAdd.putExtra(
											ContentCommon.STR_CAMERA_ID, did);
									intentAdd.putExtra("name", name);
									sendBroadcast(intentAdd);
								}
							}.start();
						}
					} else {
						showToast(R.string.add_camer_no_add);
					}
				}
			}
		}
	}

	@Override
	protected void onStart() {
		Log.d("IpcamClientActivity", "onStart()");
		super.onStart();
		SystemValue.TAG_CAMERLIST = 1;
		// bitmap_defult =
		// Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
		// getResources(), R.drawable.camer_ico), 360, 360, true);
		// bitmap_defult = returnBitmap(bitmap_defult);
		imageView1.setImageBitmap(bitmap_defult);
		imageView2.setImageBitmap(bitmap_defult);
		imageView3.setImageBitmap(bitmap_defult);
		imageView4.setImageBitmap(bitmap_defult);
		cameraListView.setAdapter(IpcamClientActivity.listAdapter);
		PlayTag = true;
		timeTag = 0;
		timeOne = 0;
		timeTwo = 0;
		timeTag2 = 0;
		timeOne2 = 0;
		timeTwo2 = 0;
		timeTag3 = 0;
		timeOne3 = 0;
		timeTwo3 = 0;
		timeTag4 = 0;
		timeOne4 = 0;
		timeTwo4 = 0;
		if (strDidList != null || strDidList.size() != 0) {
			new AsyncTask<Void, Void, Void>() {
				protected void onPreExecute() {
					WaitingStaticProgress.showProgressDialog("", IpcamClientActivityFourTest.this);
				};

				protected void onPostExecute(Void result) {
					WaitingStaticProgress.hideProgressDialog();
				};

				@Override
				protected Void doInBackground(Void... params) {

					for (int i = 0; i < strDidList.size(); i++) {
						NativeCaller.PPPPCameraControl(strDidList.get(i), 0, 1);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						NativeCaller.StartPPPPLivestream(strDidList.get(i), 10);
						Log.d("IpcamClientActivity", "StartPPPPLivestream()");
					}

					return null;
				}
			}.execute();
		}
		if (receiver == null) {
			receiver = new CameraInfoReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction(ContentCommon.STR_CAMERA_INFO_RECEIVER);
			filter.addAction("back");
			filter.addAction("other");
			registerReceiver(receiver, filter);
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		connectionTag1 = 0;
		connectionTag2 = 0;
		connectionTag3 = 0;
		connectionTag4 = 0;
		Log.d("shix", "shix play_four_onrestart");
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		SystemValue.TAG_CAMERLIST = 0;
		ssidTag1 = false;
		ssidTag2 = false;
		ssidTag3 = false;
		ssidTag4 = false;
		ifImageTag1 = 0;
		ifImageTag2 = 0;
		ifImageTag3 = 0;
		ifImageTag4 = 0;
		imageTag1 = 0;
		imageTag2 = 0;
		imageTag3 = 0;
		imageTag4 = 0;
		
		
		new Thread(new Runnable() {
			public void run() {
				
				for (int i = 0; i < strDidList.size(); i++) {
					NativeCaller.StopPPPPLivestream(strDidList.get(i));
				}
				
			}
		}).start();
		
		
		strDidList.clear();
		if (helper != null) {
			helper = null;
		}
		bitmap11 = null;
		bitmap22 = null;
		bitmap33 = null;
		bitmap44 = null;
		bitmap_defult = null;
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	public void showSetting(int position) {
		// CameraParamsBean bean = IpcamClientActivity.listAdapter
		// .getItemCamera(position);
		// int status = bean.getStatus();
		// if (status == ContentCommon.PPPP_STATUS_ON_LINE) {
		// boolean authority = bean.isAuthority();
		// if (authority) {
		// Intent intent = new Intent(IpcamClientActivityFourTest.this,
		// SettingActivity.class);
		// intent.putExtra(ContentCommon.STR_CAMERA_ID, bean.getDid());
		// intent.putExtra(ContentCommon.STR_CAMERA_NAME, bean.getName());
		// startActivity(intent);
		// overridePendingTransition(R.anim.in_from_right,
		// R.anim.out_to_left);
		// } else {
		// showToast(R.string.main_not_administrator);
		// }
		// } else {
		// showToast(R.string.main_setting_prompt);
		// }
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		bean = IpcamClientActivity.listAdapter.getOnItem(position);
		if (bean == null) {
			return;
		}
		int status = bean.getStatus();
		if (status == ContentCommon.PPPP_STATUS_INVALID_ID) {
			return;
		}
		if (status != ContentCommon.PPPP_STATUS_ON_LINE) {
			String did = bean.getDid();
			String user = bean.getUser();
			String pwd = bean.getPwd();
			StartPPPP(did, user, pwd);
			return;
		}
		if (strDidList.contains(bean.getDid())) {
			showToast(R.string.play_four_connected);
			return;
		}
		if (camerSum > 3) {
			showToast(R.string.play_four_del);
			return;
		}
		strDidList.add(bean.getDid());
		camerSum++;
		imageSetTag(imageView1, imageView2, imageView3, imageView4,
				bean.getDid(), bean.getName());

	}

	class MyImage1Threak extends Thread {
		@Override
		public void run() {
			while (ssidTag1) {
				if (ifImageTag1 == 1) {
					Log.d("shix", "shix connectionTag1:" + connectionTag1);
					if (connectionTag1 == 1) {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								connection_show1.setVisibility(View.GONE);
							}
						});
						connectionTag1 = 0;
					}
					if (connectionTag1 != 0) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						continue;
					}

					try {
						Thread.sleep(SLEEPTIME);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (copyTag1) {
						copyTag1 = false;
						try {
							if (image1IsH264 == 0) {
								bitmap11 = BitmapFactory.decodeByteArray(
										sufVideoBytes1, 0,
										sufVideoBytes1.length);
							} else if (image1IsH264 == 1) {
								byte[] rgb = new byte[image1Width * image1Higth
										* 2];
								NativeCaller.YUV4202RGB565(sufVideoBytes1, rgb,
										image1Width, image1Higth);
								ByteBuffer buffer = ByteBuffer.wrap(rgb);
								bitmap11 = Bitmap.createBitmap(image1Width,
										image1Higth, Bitmap.Config.RGB_565);
								try {
									buffer.rewind();
									bitmap11.copyPixelsFromBuffer(buffer);
									buffer.position(0);
								} catch (Exception e) {
									// TODO: handle exceptions
									Message message = new Message();
									Bundle bundle = new Bundle();
									bundle.putString("no_line_did", imageView1
											.getTag().toString());
									message.what = 110;
									message.setData(bundle);
									noLineHandler.sendMessage(message);
								}
								// bitmap11 = BitmapFactory.decodeByteArray(
								// rgb, 0, rgb.length);
							}
						} catch (OutOfMemoryError e) {
							Log.e("tag", "�ڴ����");
						}
					} else {
						continue;
					}

					if (bitmap11 == null) {
						Log.d("play", "play_four_1");
						continue;
					}
					// bitmap11 = Bitmap.createScaledBitmap(bitmap11,
					// bitMapWidth / 4, bitMapHight / 4, true);
					// bitmap11 = Bitmap.createBitmap(bitmap11, 0, 0,
					// bitmap11.getWidth(), bitmap11.getHeight(), matrix,
					// true);
					bitmap11 = returnBitmap(bitmap11);
					copyTag1 = true;
					PPPPMsgHandler.sendEmptyMessage(PLAYTAG1);
				}
			}
			super.run();
		}
	}

	class MyImage2Threak extends Thread {
		@Override
		public void run() {
			while (ssidTag2) {

				if (connectionTag2 == 1) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							connection_show2.setVisibility(View.GONE);
						}
					});
					connectionTag2 = 0;
				}
				if (connectionTag2 != 0) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				if (ifImageTag2 == 1) {
					try {
						Thread.sleep(SLEEPTIME);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (copyTag2) {
						copyTag2 = false;
						try {
							if (image2IsH264 == 0) {
								bitmap22 = BitmapFactory.decodeByteArray(
										sufVideoBytes2, 0,
										sufVideoBytes2.length);
							} else if (image2IsH264 == 1) {
								byte[] rgb = new byte[image2Width * image2Higth
										* 2];
								NativeCaller.YUV4202RGB565(sufVideoBytes2, rgb,
										image2Width, image2Higth);
								ByteBuffer buffer = ByteBuffer.wrap(rgb);
								bitmap22 = Bitmap.createBitmap(image2Width,
										image2Higth, Bitmap.Config.RGB_565);
								try {
									buffer.rewind();
									bitmap22.copyPixelsFromBuffer(buffer);
									buffer.position(0);
								} catch (Exception e) {
									// TODO: handle exceptions
									Message message = new Message();
									Bundle bundle = new Bundle();
									bundle.putString("no_line_did", imageView2
											.getTag().toString());
									message.what = 110;
									message.setData(bundle);
									noLineHandler.sendMessage(message);
								}

							}
						} catch (OutOfMemoryError e) {
							Log.e("tag", "�ڴ����");
						}
					} else {
						continue;
					}
					if (bitmap22 == null) {
						Log.d("play", "play_four_2");
						continue;
					}
					// bitmap22 = Bitmap.createScaledBitmap(bitmap22,
					// bitMapWidth / 4, bitMapHight / 4, true);
					// bitmap22 = Bitmap.createBitmap(bitmap22, 0, 0,
					// bitmap22.getWidth(), bitmap22.getHeight(), matrix,
					// true);
					bitmap22 = returnBitmap(bitmap22);
					copyTag2 = true;
					PPPPMsgHandler.sendEmptyMessage(PLAYTAG2);
				}
			}
			super.run();
		}
	}

	class MyImage3Threak extends Thread {
		@Override
		public void run() {
			while (ssidTag3) {

				if (connectionTag3 == 1) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							connection_show3.setVisibility(View.GONE);
						}
					});
					connectionTag3 = 0;
				}
				if (connectionTag3 != 0) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				if (ifImageTag3 == 1) {
					try {
						Thread.sleep(SLEEPTIME);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (copyTag3) {
						copyTag3 = false;
						try {
							if (image3IsH264 == 0) {
								bitmap33 = BitmapFactory.decodeByteArray(
										sufVideoBytes3, 0,
										sufVideoBytes3.length);
							} else if (image3IsH264 == 1) {
								byte[] rgb = new byte[image3Width * image3Higth
										* 2];
								NativeCaller.YUV4202RGB565(sufVideoBytes3, rgb,
										image3Width, image3Higth);
								ByteBuffer buffer = ByteBuffer.wrap(rgb);
								bitmap33 = Bitmap.createBitmap(image3Width,
										image3Higth, Bitmap.Config.RGB_565);
								try {
									buffer.rewind();
									bitmap33.copyPixelsFromBuffer(buffer);
									buffer.position(0);
								} catch (Exception e) {
									// TODO: handle exceptions
									Message message = new Message();
									Bundle bundle = new Bundle();
									bundle.putString("no_line_did", imageView3
											.getTag().toString());
									message.what = 110;
									message.setData(bundle);
									noLineHandler.sendMessage(message);
								}

							}
						} catch (OutOfMemoryError e) {
							Log.e("tag", "�ڴ����");
						}
					} else {
						continue;
					}
					if (bitmap33 == null) {
						Log.d("play", "play_four_3");
						continue;
					}
					// bitmap33 = Bitmap.createScaledBitmap(bitmap33,
					// bitMapWidth / 4, bitMapHight / 4, true);
					// bitmap33 = Bitmap.createBitmap(bitmap33, 0, 0,
					// bitmap33.getWidth(), bitmap33.getHeight(), matrix,
					// true);
					bitmap33 = returnBitmap(bitmap33);
					copyTag3 = true;
					PPPPMsgHandler.sendEmptyMessage(PLAYTAG3);
				}
			}
			super.run();
		}
	}

	class MyImage4Threak extends Thread {
		@Override
		public void run() {
			while (ssidTag4) {
				if (connectionTag4 == 1) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							connection_show4.setVisibility(View.GONE);
						}
					});
					connectionTag4 = 0;
				}
				if (connectionTag4 != 0) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				if (ifImageTag4 == 1) {
					try {
						Thread.sleep(SLEEPTIME);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (copyTag4) {
						copyTag4 = false;
						try {
							if (image4IsH264 == 0) {
								bitmap44 = BitmapFactory.decodeByteArray(
										sufVideoBytes4, 0,
										sufVideoBytes4.length);
							} else if (image4IsH264 == 1) {
								byte[] rgb = new byte[image4Width * image4Higth
										* 2];
								NativeCaller.YUV4202RGB565(sufVideoBytes4, rgb,
										image4Width, image4Higth);
								ByteBuffer buffer = ByteBuffer.wrap(rgb);
								bitmap44 = Bitmap.createBitmap(image4Width,
										image4Higth, Bitmap.Config.RGB_565);
								try {
									buffer.rewind();
									bitmap44.copyPixelsFromBuffer(buffer);
									buffer.position(0);
								} catch (Exception e) {
									// TODO: handle exceptions
									Message message = new Message();
									Bundle bundle = new Bundle();
									bundle.putString("no_line_did", imageView4
											.getTag().toString());
									message.what = 110;
									message.setData(bundle);
									noLineHandler.sendMessage(message);
								}

							}
						} catch (OutOfMemoryError e) {
							Log.e("tag", "�ڴ����");
						}
					} else {
						continue;
					}
					if (bitmap44 == null) {
						Log.d("play", "play_four_4");
						continue;
					}
					// bitmap44 = Bitmap.createScaledBitmap(bitmap44,
					// bitMapWidth / 4, bitMapHight / 4, true);
					// bitmap44 = Bitmap.createBitmap(bitmap44, 0, 0,
					// bitmap44.getWidth(), bitmap44.getHeight(), matrix,
					// true);
					bitmap44 = returnBitmap(bitmap44);
					copyTag4 = true;
					PPPPMsgHandler.sendEmptyMessage(PLAYTAG4);
				}
			}
			super.run();
		}
	}

	private void imageSetTag(ImageView imageView11, ImageView imageView22,
			ImageView imageView33, ImageView imageView44, String tag,
			String name) {
		if (imageView1.getTag().toString().equalsIgnoreCase("1")) {
			connectionTag1 = 2;
			imageView1.setTag(tag);
			textView1.setVisibility(View.VISIBLE);
			connection_show1.setVisibility(View.VISIBLE);
			textView1.setText(name);
			name1 = name;
			imageTag1 = 1;
			ssidTag1 = true;
			new Thread() {
				public void run() {

					NativeCaller.PPPPCameraControl(imageView1.getTag()
							.toString(), 6, 5);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.StartPPPPLivestream(imageView1.getTag()
							.toString(), 10);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new MyImage1Threak().start();
				};
			}.start();

			return;
		} else if (imageView2.getTag().toString().equalsIgnoreCase("1")) {
			connectionTag2 = 2;
			imageView2.setTag(tag);
			textView2.setVisibility(View.VISIBLE);
			textView2.setText(name);
			connection_show2.setVisibility(View.VISIBLE);
			name2 = name;
			imageTag2 = 1;
			ssidTag2 = true;
			new Thread() {
				public void run() {

					NativeCaller.PPPPCameraControl(imageView2.getTag()
							.toString(), 6, 5);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.StartPPPPLivestream(imageView2.getTag()
							.toString(), 10);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new MyImage2Threak().start();
				};
			}.start();
			return;
		} else if (imageView3.getTag().toString().equalsIgnoreCase("1")) {
			connectionTag3 = 2;
			imageView3.setTag(tag);
			textView3.setVisibility(View.VISIBLE);
			textView3.setText(name);
			connection_show3.setVisibility(View.VISIBLE);
			name3 = name;
			imageTag3 = 1;
			ssidTag3 = true;
			new Thread() {
				public void run() {

					NativeCaller.PPPPCameraControl(imageView3.getTag()
							.toString(), 6, 5);

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.StartPPPPLivestream(imageView3.getTag()
							.toString(), 10);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new MyImage3Threak().start();
				};
			}.start();

			return;
		} else if (imageView4.getTag().toString().equalsIgnoreCase("1")) {
			connectionTag4 = 2;
			imageView4.setTag(tag);
			textView4.setVisibility(View.VISIBLE);
			textView4.setText(name);
			connection_show4.setVisibility(View.VISIBLE);
			name4 = name;
			imageTag4 = 1;
			ssidTag4 = true;

			new Thread() {
				public void run() {
					NativeCaller.PPPPCameraControl(imageView4.getTag()
							.toString(), 0, 1);

					NativeCaller.PPPPCameraControl(imageView4.getTag()
							.toString(), 6, 5);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					NativeCaller.StartPPPPLivestream(imageView4.getTag()
							.toString(), 10);

					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new MyImage4Threak().start();
				};
			}.start();
			return;
		}

	}

	@Override
	public void callBackCameraParamNotify(String did, int resolution,
			int brightness, int contrast, int hue, int saturation, int flip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callBaceVideoData(String did, byte[] videobuf, int h264Data,
			int len, int width, int height) {
		// TODO Auto-generated method stub
		// Log.d("tagg", "did==" + did + "Call VideoData...h264Data: " +
		// h264Data
		// + " len: " + len + " videobuf len: " + videobuf.length
		// + "width==" + width + "height==" + height);
		if (PlayTag) {
			if (did.equalsIgnoreCase(imageView1.getTag().toString())) {
				if (copyTag1 == false) {
					return;
				}
				if (connectionTag1 == 2) {
					connectionTag1 = 1;
				}
				sufVideoBytes1 = videobuf;
				image1IsH264 = h264Data;
				image1Width = width;
				image1Higth = height;
				ifImageTag1 = 1;
			} else if (did.equalsIgnoreCase(imageView2.getTag().toString())) {
				if (copyTag2 == false) {
					return;
				}
				if (connectionTag2 == 2) {
					connectionTag2 = 1;
				}
				sufVideoBytes2 = videobuf;
				image2IsH264 = h264Data;
				image2Width = width;
				image2Higth = height;
				ifImageTag2 = 1;
			} else if (did.equalsIgnoreCase(imageView3.getTag().toString())) {
				if (copyTag3 == false) {
					return;
				}
				if (connectionTag3 == 2) {
					connectionTag3 = 1;
				}
				sufVideoBytes3 = videobuf;
				image3IsH264 = h264Data;
				image3Width = width;
				image3Higth = height;
				ifImageTag3 = 1;
			} else if (did.equalsIgnoreCase(imageView4.getTag().toString())) {
				if (copyTag4 == false) {
					return;
				}
				if (connectionTag4 == 2) {
					connectionTag4 = 1;
				}
				sufVideoBytes4 = videobuf;
				image4IsH264 = h264Data;
				image4Width = width;
				image4Higth = height;
				ifImageTag4 = 1;
			} else {
				return;
			}
		}

	}

	@Override
	public void callBackMessageNotify(String did, int msgType, int param) {
		// TODO Auto-generated method stub
		Log.d("test_four", "MessageNotify did: " + did + " msgType: " + msgType
				+ " param: " + param);
		if (msgType == 0 && param != 2) {
			Message message = new Message();
			Bundle bundle = new Bundle();
			bundle.putString("no_line_did", did);
			message.what = 110;
			message.setData(bundle);
			noLineHandler.sendMessage(message);
		}
	}

	@Override
	public void callBackAudioData(byte[] pcm, int len) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callBackH264Data(byte[] h264, int type, int size) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageView1:
			// Date date = new Date();
			// if (timeTag == 0) {
			// timeOne = date.getSeconds();
			// timeTag = 1;
			// } else if (timeTag == 1) {
			// timeTwo = date.getSeconds();
			// if (timeTwo - timeOne <= 3) {
			// frameLayout2.setVisibility(View.GONE);
			// frameLayout3.setVisibility(View.GONE);
			// frameLayout4.setVisibility(View.GONE);
			// timeTag = 0;
			// } else {
			// timeTag = 1;
			// }
			// }
			if (imageTag1 == 1) {
				TAGPlay = 1;
				if (!ifHind) {
					popupWindow_about.showAtLocation(imageView1, 0,
							screenWidth / 4 - 40,
							(screenHeight / 2 - 40) / 2 - 40);
				} else {
					popupWindow_about.showAtLocation(imageView1, 0,
							screenWidth * 8 / 44 - 50,
							(screenHeight / 2 - 40) / 2 - 40);
				}

			}

			break;
		case R.id.imageView2:
			if (imageTag2 == 1) {
				TAGPlay = 2;
				if (!ifHind) {
					popupWindow_about.showAtLocation(imageView2, 0,
							screenWidth * 3 / 4 - 40, screenHeight / 2
									+ ((screenHeight / 2 - 40) / 2 - 40));
				} else {
					popupWindow_about.showAtLocation(imageView2, 0, screenWidth
							* 8 / 22 + (screenWidth * 8 / 44 - 50),
							screenHeight / 2
									+ ((screenHeight / 2 - 40) / 2 - 40));
				}
			}

			break;
		case R.id.imageView3:
			if (imageTag3 == 1) {
				TAGPlay = 3;
				if (!ifHind) {
					popupWindow_about.showAtLocation(imageView1, 0,
							screenWidth / 4 - 40, screenHeight / 2
									+ (screenHeight / 2 - 40) / 2 - 40);
				} else {
					popupWindow_about.showAtLocation(imageView1, 0,
							screenWidth * 8 / 44 - 50, screenHeight / 2
									+ (screenHeight / 2 - 40) / 2 - 40);
				}
			}

			break;
		case R.id.imageView4:
			if (imageTag4 == 1) {
				TAGPlay = 4;
				if (!ifHind) {
					popupWindow_about.showAtLocation(imageView2, 0,
							screenWidth * 3 / 4 - 40,
							((screenHeight / 2 - 40) / 2 - 40));
				} else {
					popupWindow_about.showAtLocation(imageView2, 0, screenWidth
							* 8 / 22 + (screenWidth * 8 / 44 - 50),
							((screenHeight / 2 - 40) / 2 - 40));
				}
			}

			break;

		}
		return false;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		
		
		//Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
		
		super.onConfigurationChanged(newConfig);
		
		upDateLanguageonConfigurationChange();
		
	}
	
	private void upDateLanguageonConfigurationChange()
	{
		
		String  tempLanguage="en";
	    if(KisafaApplication.currentAppLanguage.equals(AppKeys.LANGUAGES.ENGLISH))
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
	

}