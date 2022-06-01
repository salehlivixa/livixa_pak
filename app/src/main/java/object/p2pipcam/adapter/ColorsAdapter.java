package object.p2pipcam.adapter;

import java.util.List;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.livixa.client.R;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mDataSet;

    public ColorsAdapter(Context context, List<String> list){
        mContext = context;
        mDataSet = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
       
    	public ImageView icon;
    	public TextView  moodNmae;
    	public TextView  repeatMood;
    	public TextView time;
 	    
 	    LinearLayout moodItemBGLY;

        public ViewHolder(View convertView){
            super(convertView);
            
            icon = (ImageView) convertView.findViewById(R.id.img_icon_mood);
            moodNmae = (TextView) convertView.findViewById(R.id.moodText);
            repeatMood = (TextView) convertView.findViewById(R.id.repeatTV);
            time = (TextView) convertView.findViewById(R.id.timeTV);
            moodItemBGLY = (LinearLayout) convertView.findViewById(R.id.moodItemBGLY);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.moods_horizontal_listitem_test,parent,false);
        ViewHolder vh = new ViewHolder(v);

        
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        
    	
    	

        
    }

    @Override
    public int getItemCount(){
        // Count the items
        return mDataSet.size();
    }

	
}