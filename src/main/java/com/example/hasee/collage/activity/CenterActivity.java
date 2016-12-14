package com.example.hasee.collage.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.SharedPrefUtil;
import com.example.hasee.collage.activity.https.XUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import view.TitleView;


public class CenterActivity extends BaseActivity {
    @ViewInject(R.id.center_title)
    private TitleView title;
    @ViewInject(R.id.center_exit)
    private Button btExit;
    @ViewInject(R.id.center_items)
    private ListView items;
    @ViewInject(R.id.center_nick)
    private TextView tvNick;
    @ViewInject(R.id.center_account)
    private TextView tvAccount;
    @ViewInject(R.id.center_school)
    private TextView tvSchool;
    @ViewInject(R.id.center_phone)
    private ImageView imgPhoto;
    @ViewInject(R.id.center_authentication)
    private CheckBox cbAuthentication;

    private int[] items_icons = new int[]{R.drawable.icon_lock, R.drawable.icon_set,R.drawable.save};
    @ResInject(id = R.array.center_items_text, type = ResType.StringArray)
    private String[] items_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        registerReceiver(receiver,new IntentFilter("com.guxu_MODE_CHANGED"));
        registerReceiver(receiver1,new IntentFilter("com.guxu.head_changed"));
    }

    private void init(){
        boolean isNight = SharedPrefUtil.isNight(this);
        if(isNight){
            setTheme(R.style.Night);
        }else{
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.center);
        ViewUtils.inject(this);
        setUserInfo();
        items.setAdapter(new ItemAdapter());
        title.setBackClickListener(clickLis);
        btExit.setOnClickListener(clickLis);
    }

    private View.OnClickListener clickLis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.title_back:
                    finish();
                    break;
                case R.id.center_exit:
                    ((MyApp)getApplication()).setRegister(false);
                    finish();

            }
        }
    };

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CenterActivity.class));
    }
    @OnClick(R.id.center_private)
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.center_private:
                UserinfoActivity.startActivity(this);
                break;
        }
    }
    private void setUserInfo() {
//        Log.i("Tag", "setUserInfo: "+((MyApp)getApplication()).getUser().getPhone());
        tvAccount.setText(((MyApp)getApplication()).getUser().getPhone() == null ? "未设置" : ((MyApp)getApplication()).getUser().getPhone());
        tvNick.setText(((MyApp)getApplication()).getUser().getNickname() == null ? "未设置" : ((MyApp)getApplication()).getUser().getNickname());
        XUtils.display(imgPhoto, ((MyApp)getApplication()).getUser().getGender());
        cbAuthentication.setChecked(true);
        cbAuthentication.setText("已认证");
    }
    @OnItemClick(R.id.center_items)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                validate.startActivity(CenterActivity.this,false);
                break;
            case 1:
                Setting_activity.startActivity(this);
                break;
            case 2:
                ColleteNewsActivity.startActivity(this);
                break;

        }
    }

    class ItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items_text.length;
        }

        @Override
        public String getItem(int position) {
            return items_text[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            ViewHolder holder = null;
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.layout_simple_list_item_2, null);
                holder = new ViewHolder();
                holder.icon = (ImageView) v.findViewById(R.id.list_item_2_icon);
                holder.tv = (TextView) v.findViewById(R.id.list_item_2_item);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            holder.icon.setImageResource(items_icons[position]);
            holder.tv.setText(items_text[position]);
            return v;
        }
    }

    class ViewHolder {
        ImageView icon;
        TextView tv;
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            init();
        }

    };
    private BroadcastReceiver receiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            init();
        }

    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(receiver1);
    }
}
