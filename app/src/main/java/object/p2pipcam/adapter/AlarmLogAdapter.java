package object.p2pipcam.adapter;

import java.util.ArrayList;

import com.livixa.client.R;

import object.p2pipcam.bean.AlarmLogBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmLogAdapter extends BaseAdapter {
	private ArrayList<AlarmLogBean> arrayList;
	private Context context;
	private LayoutInflater inflater;

	public AlarmLogAdapter(Context context) {
		arrayList = new ArrayList<AlarmLogBean>();
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {

		return arrayList.size();
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.alarmlog_listitem, null);
			holder = new ViewHolder();
			holder.camName = (TextView) convertView
					.findViewById(R.id.alarm_log_cam_name);
			holder.content = (TextView) convertView
					.findViewById(R.id.alarm_log_content);
			holder.createTime = (TextView) convertView
					.findViewById(R.id.alarm_log_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AlarmLogBean alarmLogBean = arrayList.get(position);
		holder.content.setText(alarmLogBean.getContent());
		holder.createTime.setText(alarmLogBean.getCreatetime());
		holder.camName.setText(alarmLogBean.getCamName());
		MyOnLongListener onLongListener = new MyOnLongListener(position);
		holder.content.setOnLongClickListener(onLongListener);
		return convertView;
	}

	public void addAlarmLog(AlarmLogBean alarmLogBean) {
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getCreatetime()
					.equals(alarmLogBean.getCreatetime())) {
				return;
			}
		}
		arrayList.add(alarmLogBean);
	}

	public void clearAllAlarmLog() {
		arrayList.clear();
	}

	private class MyOnLongListener implements OnLongClickListener {
		private int position;

		public MyOnLongListener(int position) {
			this.position = position;
		}

		@Override
		public boolean onLongClick(View v) {
			// Toast.makeText(context, "position:" + position, 0).show();
			return false;
		}

	}

	// private View.OnCreateContextMenuListener contextMenuListener = new
	// OnCreateContextMenuListener() {
	//
	// @Override
	// public void onCreateContextMenu(ContextMenu menu, View v,
	// ContextMenuInfo menuInfo) {
	// AdapterView.AdapterContextMenuInfo info;
	// try {
	// info = (AdapterView.AdapterContextMenuInfo) menuInfo;
	// if (info == null) {
	// return;
	// }
	// } catch (Exception e) {
	// }
	// menu.add(0, 1, 0, "ɾ��");
	// }
	// };

	private class ViewHolder {
		TextView camName;
		TextView content;
		TextView createTime;
	}
}
