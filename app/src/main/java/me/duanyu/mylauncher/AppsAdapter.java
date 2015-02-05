package me.duanyu.mylauncher;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;




/**
 * Created by Steve on 2015/2/5.
 */
public class
        AppsAdapter extends BaseAdapter {
    private List<ResolveInfo> mApps;
    private LayoutInflater layoutInflater;
    private ImageView icon;
    private TextView appName;
    private PackageManager packageManager;

    public AppsAdapter(List<ResolveInfo> mApps,Context context,PackageManager packageManager){
        this.mApps=mApps;
        this.packageManager =packageManager;
        layoutInflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mApps.size();
    }

    @Override
    public Object getItem(int position) {
        return mApps.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder=new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_cell,null);
            holder.appName=(TextView)convertView.findViewById(R.id.appName);
            holder.icon=(ImageView)convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.icon.setImageDrawable(mApps.get(position).activityInfo.loadIcon(packageManager));
        holder.appName.setText(mApps.get(position).activityInfo.loadLabel(packageManager));
        return convertView;
    }



     class ViewHolder{
        ImageView icon;
        TextView appName;

    }
}
