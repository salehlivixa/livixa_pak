package object.p2pipcam.adapter;

import java.util.ArrayList;

import com.livixa.client.R;

import object.p2pipcam.bean.CameraParamsBean;
import object.p2pipcam.content.ContentCommon;
import object.p2pipcam.system.SystemValue;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VideoActivityAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ViewHolder holder;
	public int mode = 1;
	public final int PHONE = 1;
	public final int REMOTE = 2;
	private boolean isOver = false;

	public VideoActivityAdapter(Context context,
			ArrayList<CameraParamsBean> list) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return SystemValue.arrayList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.video_only_listitem, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.id = (TextView) convertView.findViewById(R.id.tv_did);
			holder.status = (TextView) convertView.findViewById(R.id.tv_status);
			holder.number = (TextView) convertView
					.findViewById(R.id.tv_pic_num);
			holder.pic = (ImageView) convertView.findViewById(R.id.pic);
			holder.videoFlag = (ImageView) convertView
					.findViewById(R.id.video_flag);
			holder.pBar = (ProgressBar) convertView
					.findViewById(R.id.progressBar1);
			holder.frame = convertView.findViewById(R.id.frame);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.videoFlag.setVisibility(View.VISIBLE);
		CameraParamsBean bean = SystemValue.arrayList.get(arg0);
		Bitmap bmp = bean.getBmp();
		int sum = bean.getSum();
		if (bmp != null) {
			holder.pBar.setVisibility(View.GONE);
			Drawable d = new BitmapDrawable(bmp);
			holder.pic.setImageBitmap(null);
			holder.pic.setBackgroundDrawable(d);
			holder.videoFlag.setVisibility(View.VISIBLE);
		} else {
			holder.videoFlag.setVisibility(View.GONE);
			holder.pic.setImageResource(R.drawable.video_default);
			holder.pic.setBackgroundColor(0xff000000);
		}
		if (isOver) {
			holder.pBar.setVisibility(View.GONE);
		}
		holder.number.setText("(" + sum + ")");
		String did = bean.getDid();
		String name = bean.getName();
		int status = bean.getStatus();
		int resid;
		switch (status) {
		case ContentCommon.PPPP_STATUS_CONNECTING:
			resid = R.string.pppp_status_connecting;
			break;
		case ContentCommon.PPPP_STATUS_CONNECT_FAILED:
			resid = R.string.pppp_status_connect_failed;
			break;
		case ContentCommon.PPPP_STATUS_DISCONNECT:
			resid = R.string.pppp_status_disconnect;
			break;
		case ContentCommon.PPPP_STATUS_INITIALING:
			resid = R.string.pppp_status_initialing;
			break;
		case ContentCommon.PPPP_STATUS_INVALID_ID:
			resid = R.string.pppp_status_invalid_id;
			break;
		case ContentCommon.PPPP_STATUS_ON_LINE:
			resid = R.string.pppp_status_online;
			break;
		case ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE:
			resid = R.string.device_not_on_line;
			break;
		case ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT:
			resid = R.string.pppp_status_connect_timeout;
			break;
		case ContentCommon.PPPP_STATUS_CONNECT_ERRER:
			resid = R.string.pppp_status_connect_log_errer;
			break;
		case ContentCommon.PPPP_STATUS_INVALID_TIME:
			resid = R.string.set_user_time_pppp_statu;
			break;
		default:
			resid = R.string.pppp_status_unknown;
		}
		holder.status.setText(context.getResources().getString(resid));
		holder.id.setText(did);
		holder.name.setText(name);
		switch (mode) {
		case PHONE:
			holder.number.setVisibility(View.VISIBLE);
			holder.frame.setVisibility(View.GONE);
			holder.status.setVisibility(View.GONE);
			break;
		case REMOTE:
			holder.frame.setVisibility(View.GONE);
			holder.number.setVisibility(View.GONE);
			holder.status.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
		return convertView;
	}

	public void setOver(boolean over) {
		isOver = over;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getMode() {
		return mode;
	}

	private class ViewHolder {
		TextView name;
		TextView id;
		TextView status;
		TextView number;
		ImageView pic;
		ImageView videoFlag;
		ProgressBar pBar;
		View frame;
	}
}
