package me.duanyu.mylauncher;

import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<ResolveInfo> mApps;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        getApps();

        AppsAdapter appsAdapter =new AppsAdapter(mApps,this,getPackageManager());
        listView.setAdapter(appsAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ResolveInfo resolveInfo =mApps.get(position);
        String packageName =resolveInfo.activityInfo.packageName;
        String className =resolveInfo.activityInfo.name;
        ComponentName componentName = new ComponentName(packageName,className);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }

    private void getApps()
    {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    private void initViews()
    {
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);
    }



}
