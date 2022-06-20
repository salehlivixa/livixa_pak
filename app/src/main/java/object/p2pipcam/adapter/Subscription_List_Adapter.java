package object.p2pipcam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Features;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Subscription;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Sh_Subscription_1;
import com.livixa.apacam.client.response.isdatasyncedwithserver.Subscription;
import com.livixa.apacam.client.swipelistview.SwipeRevealLayout;
import com.livixa.apacam.dbmodel.Tariff_Model;
import com.livixa.client.R;

import java.util.List;

public class Subscription_List_Adapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Sh_Subscription> sh_subscriptions;
    private List<Sh_Features> sh_features;

    public Subscription_List_Adapter(Context mContext,List<Sh_Subscription> sh_subscriptions,List<Sh_Features> sh_features){
        this.mContext = mContext;
        this.sh_subscriptions = sh_subscriptions;
        this.sh_features = sh_features;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return this.sh_subscriptions.size();
    }

    @Override
    public Object getItem(int position) {

        return sh_subscriptions.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.subscription_listview_item, null);
        TextView Sub_id = convertView.findViewById(R.id.Sub_id);
        TextView sub_package = convertView.findViewById(R.id.sub_package);
        TextView sub_exp_date = convertView.findViewById(R.id.sub_exp_date);
        Sh_Subscription object = this.sh_subscriptions.get(position);
        String value = object.getSh_subscription_eng() + "\n" + ConcatEnglish(object.getSh_feature_id());
        Sub_id.setText(object.getSh_id());
        sub_package.setText(value);
        sub_exp_date.setText(object.getSh_subscriptions_period());

        return convertView;
    }

    public String ConcatEnglish(String ids) {
        StringBuilder sb = new StringBuilder();
        if (!ids.contains(",")) {
            for (Sh_Features feature : sh_features) {
                if (ids.equals(feature.getSh_id())) {
                    return feature.getSh_feature_eng();
                }
            }
        } else {
            String[] _ids = ids.split(",");
            for (String id : _ids) {
                for (Sh_Features feature : sh_features) {
                    if (id.equals(feature.getSh_id())) {/**/
                        sb = sb.append(feature.getSh_feature_eng()).append("\n");
                    }
                }
            }
            return sb.toString();
        }
        return "";
    }
}
