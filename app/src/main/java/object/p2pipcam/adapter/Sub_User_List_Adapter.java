package object.p2pipcam.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.activeandroid.query.Delete;
import com.afollestad.materialdialogs.MaterialDialog;

import com.github.mrengineer13.snackbar.SnackBar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.livixa.client.R;
import com.livixa.apacam.client.activity.Add_OR_Edit_UserActivity;
import com.livixa.apacam.client.activity.AssignedRoomtoSubuserActivity;
import com.livixa.apacam.client.activity.AssignedCamerasToSubuserActivity;
import com.livixa.apacam.client.activity.RegisterActivityForSubUser;
import com.livixa.apacam.client.appconfig.AppKeys;
import com.livixa.apacam.client.base.KisafaApplication;
import com.livixa.apacam.client.network.ApiService;
import com.livixa.apacam.client.network.RestCallback;
import com.livixa.apacam.client.network.ServerConnectListener;
import com.livixa.apacam.client.response.ServerResponse;
import com.livixa.apacam.client.response.deletion.DeletionResponse;
import com.livixa.apacam.client.response.login.LoginResponse;
import com.livixa.apacam.client.response.login.SUB_USER_DB;
import com.livixa.apacam.client.response.register.RegisterResponse;
import com.livixa.apacam.client.swipelistview.SwipeRevealLayout;
import com.livixa.apacam.client.utility.ACache;
import com.livixa.apacam.client.utility.AppConstants;
import com.livixa.apacam.client.utility.AppPreference;
import com.livixa.apacam.client.utility.ServerCodes;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons;
import com.livixa.apacam.customprogressbar.CustomAlertDialogueTwoButtons.CustomDialogueTwoButtonsClickListner;
import com.livixa.apacam.customprogressbar.WaitingStaticProgress;
import com.livixa.apacam.dbmodel.Room_Model;
import object.p2pipcam.gridview.PullToRefreshAdapterViewBase;
import retrofit2.Call;

public class Sub_User_List_Adapter extends BaseAdapter implements ServerConnectListener
{
	
	Context context;
	private LayoutInflater inflater = null;
	ArrayList<SUB_USER_DB> sub_UserDBList=new ArrayList<SUB_USER_DB>();
	
	private Map<String, String> map;
	
	private ProgressDialog mProgressDialog;
	
	int currentSelectedSubUserPosition=0;
	
	View listView, emptyImg;
	
	boolean isDeleteDialogeShowing=false;
	
	public Sub_User_List_Adapter(Context context,ArrayList<SUB_USER_DB> sub_UserDBList,View listView,View emptyImg)
	{
		this.sub_UserDBList=sub_UserDBList;
		this.context=context;
		this.inflater = LayoutInflater.from(context);
		this.listView=listView;
		this.emptyImg=emptyImg;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sub_UserDBList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sub_UserDBList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		convertView= inflater.inflate(R.layout.sub_user_list_item, null);
		
		TextView tv_subUsername=(TextView) convertView.findViewById(R.id.tv_subUserName);
		TextView tv_delete_User=(TextView) convertView.findViewById(R.id.tv_sub_UserDelete);
		LinearLayout lL_itemContainer=(LinearLayout) convertView.findViewById(R.id.itemContainerLY);
		final SwipeRevealLayout  swipeRevealLayout=(SwipeRevealLayout) convertView.findViewById(R.id.subUsersListRoot);
		
		
		tv_subUsername.setText(sub_UserDBList.get(position).username);
		
		tv_delete_User.setTag(position);
		
		lL_itemContainer.setTag(position);
		
		tv_delete_User.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				
				
				if(!isDeleteDialogeShowing)
				{
				
								isDeleteDialogeShowing=true;
								
								
								TextView b=(TextView)v;
								 
								final int itemPos= (Integer) b.getTag();
								
								currentSelectedSubUserPosition=itemPos;
								
								
								
								final CustomAlertDialogueTwoButtons cusTwo=new CustomAlertDialogueTwoButtons(context, context.getString(R.string.deleteuser)+sub_UserDBList.get(itemPos).username);
								
								cusTwo.setListner(new CustomDialogueTwoButtonsClickListner() {
									
									@Override
									public void onCustomDialoguePositiveClick() {
										
										
										
										try
										{
										//AppPreference.saveValue(this, loginResponse.getShResult()
										String sessionId=AppPreference.getValue(context,AppKeys.KEY_SESSION);
										deleteSub_User(sessionId,sub_UserDBList.get(itemPos).sh_user_id);
										}catch(Exception ex){}
										
										
										cusTwo.dismiss();
										
										isDeleteDialogeShowing=false;
										
									}
									
									@Override
									public void onCustomDialogueNegativeClick() {
										
										
										cusTwo.dismiss();
										
										swipeRevealLayout.close(false);
										isDeleteDialogeShowing=false;
										
									}
								});
								
								
								
								cusTwo.show();
								
								
								//
								
								/*MaterialDialog dialog = new MaterialDialog.Builder(context)
										.content("Do do want to delete subuser with username="+sub_UserDBList.get(itemPos).username)
										.positiveText(android.R.string.ok)
										.negativeText(android.R.string.cancel)
										.negativeColor(
												KisafaApplication.getAppResources().getColor(
														R.color.app_header_bg))
										.positiveColor(
												KisafaApplication.getAppResources().getColor(
														R.color.app_header_bg))
										.callback(new MaterialDialog.ButtonCallback() {
											@Override
											public void onPositive(MaterialDialog dialog) {
												
												try
												{
												//AppPreference.saveValue(this, loginResponse.getShResult()
												String sessionId=AppPreference.getValue(context,AppKeys.KEY_SESSION);
												deleteSub_User(sessionId,sub_UserDBList.get(itemPos).sh_user_id);
												}catch(Exception ex){}
											}
										}).build();
								dialog.show();
								
								dialog.setOnDismissListener(new OnDismissListener() {
									
									@Override
									public void onDismiss(DialogInterface dialog)
									{
										swipeRevealLayout.close(false);
										isDeleteDialogeShowing=false;
									}
								});
								*/
				
				}
				
				
			}
		});
		
		lL_itemContainer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				
				LinearLayout  l=(LinearLayout)v;
				
				final int itemPos= (Integer) l.getTag();
				
				callPopup(itemPos);
				
				
			}
		});
		
		
		
		return convertView;
	}

	@Override
	public void onSuccess(ServerResponse response) {
		
		WaitingStaticProgress.hideProgressDialog();
		
		if (response.getRequestCode() == ServerCodes.ServerRequestCodes.MSG_DELETE_REQUEST_CODE) {
			
			
			DeletionResponse deletionResponse = null;
			try {
				deletionResponse = (DeletionResponse) response;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (deletionResponse.getShMeta().getShErrorCode().equalsIgnoreCase("0")) {
				
				SUB_USER_DB user=sub_UserDBList.remove(currentSelectedSubUserPosition);
				
				
				new Delete().from(SUB_USER_DB.class).where("sh_user_id = ?", user.sh_user_id).execute();
				
				notifyDataSetChanged();
				
				if(sub_UserDBList!=null && sub_UserDBList.size()==0 )
				{
					listView.setVisibility(View.GONE);
					emptyImg.setVisibility(View.VISIBLE);
				}
				else
				{
					if(emptyImg.getVisibility()==View.VISIBLE)
					{
						listView.setVisibility(View.VISIBLE);
						emptyImg.setVisibility(View.INVISIBLE);
					}
				}
				
				//Toast.makeText(context, "Subuser Deleted Successfully", Toast.LENGTH_SHORT).show();
				
			} else {
				
					onFailure(deletionResponse.getShMeta().getShMessage());
				
			}
		}
	}

	@Override
	public void onFailure(ServerResponse response) {
		// TODO Auto-generated method stub
		try
		{
		WaitingStaticProgress.hideProgressDialog();
		onFailure(response.getMessage());
		}catch(Exception ex){}
	}
	
	
	public void onFailure(String retrofitError) {
		WaitingStaticProgress.hideProgressDialog();
		try
		{
		new SnackBar.Builder((Activity) context).withMessage(retrofitError)
				.withDuration(SnackBar.SHORT_SNACK).show();
		}catch(Exception ex){}
	}
	
	public void deleteSub_User(String session, String user_id )
	{
		
		
	
		map = new HashMap<String, String>();
		map.put("session", session);
		map.put("user_id", user_id);
		
		
		showProgressDialog("Deleteing User...", 100);
		ApiService service = KisafaApplication.getRestClient().getApiService();
		Call<DeletionResponse> call = service.delete(map);
		call.enqueue(new RestCallback<DeletionResponse>(this,
				ServerCodes.ServerRequestCodes.MSG_DELETE_REQUEST_CODE, context));
		
		
	}
	
	
	private void showProgressDialog(String text, int progress) {
		/*mProgressDialog = new ProgressDialog(context,R.style.progressDialogTheme);
		mProgressDialog.setMessage(text);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setMax(progress);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();*/
		
		WaitingStaticProgress.showProgressDialog("", context);
	}

	
	private void callPopup(final int position) {

		 LayoutInflater layoutInflater = (LayoutInflater)context
		 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		 View popupView = layoutInflater.inflate(R.layout.sub_user_list_popup, null);
		
		TextView tv_cancel=(TextView) popupView.findViewById(R.id.cancelButton);
		TextView tv_Camera=(TextView) popupView.findViewById(R.id.tv_Camera);
		TextView tv_Room=(TextView) popupView.findViewById(R.id.tv_Room);
		
		
		
		
		

		final PopupWindow popupWindow=new PopupWindow(popupView,
		         LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,        
		 true);

		 popupWindow.setTouchable(true);
		 popupWindow.setFocusable(true);
		 
		 popupWindow.setAnimationStyle(R.style.BottonUpAnimation);
		 
		

		 popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
		 
		 tv_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
				}
			});
		 
		 tv_Room.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					
					((Activity) context).finish();
					SUB_USER_DB _sUB_USER_DB=sub_UserDBList.get(position);
					Intent intent = new Intent(context, AssignedRoomtoSubuserActivity.class);
					intent.putExtra(AppKeys.KEY_SUB_USER_TAG, _sUB_USER_DB.sh_user_id+"");
					context.startActivity(intent);
					KisafaApplication.perFormActivityNextTransition(context);
					
					
				}
			});
		 
		 
		 tv_Camera.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					popupWindow.dismiss();
					
					((Activity) context).finish();
					SUB_USER_DB _sUB_USER_DB=sub_UserDBList.get(position);
					Intent intent = new Intent(context, AssignedCamerasToSubuserActivity.class);
					intent.putExtra(AppKeys.KEY_SUB_USER_TAG, _sUB_USER_DB.sh_user_id+"");
					context.startActivity(intent);
					KisafaApplication.perFormActivityNextTransition(context);
					//context.overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
				}
			});
		
	}
	
}
