package com.example.hasee.collage.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.Tab;
import com.example.hasee.collage.activity.auxiliary.Code;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.XUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import view.TitleView;

public class SelectTabActivity extends BaseActivity {
    @ViewInject(R.id.tabs_title)
    private TitleView titleView;
    @ViewInject(R.id.tabs_my)
    private GridView gvMy;
    @ViewInject(R.id.tabs_other)
    private GridView gvOther;

    private TabAdapter myTabAdapter;
    private TabAdapter otherTabAdapter;
    private View moveView;
    private int[] locStar;
    private int[] locEnd = new int[2];
    private boolean toMy;
    private FrameLayout flDecor;
    private int position;
    private boolean isAnimitioning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tab);
        ViewUtils.inject(this);
        titleView.setRightClickListener(clicks);
        titleView.setBackClickListener(clicks);
        myTabAdapter = new TabAdapter(((MyApp)getApplication()).getMyTabs());
        otherTabAdapter = new TabAdapter(((MyApp)getApplication()).getOtherTabs());
        gvMy.setAdapter(myTabAdapter);
        gvOther.setAdapter(otherTabAdapter);
        gvMy.setOnItemClickListener(itemClickListener);
        gvOther.setOnItemClickListener(itemClickListener);
        initDecor();

    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(!isAnimitioning){
                isAnimitioning = true;

                moveView = getMoveView(view);
                locStar = new int[2];
                SelectTabActivity.this.position = position;
                view.getLocationInWindow(locStar);
                Tab t = ((TabAdapter) parent.getAdapter()).getItem(position);
                otherTabAdapter.setShowText(false);
                myTabAdapter.setShowText(false);
                switch (parent.getId()){
                    case R.id.tabs_my:
                        toMy = false;
                        t.setIsmy(1);
                        otherTabAdapter.add(t);
                        gvOther.getViewTreeObserver().addOnGlobalLayoutListener(global);
                        break;
                    case R.id.tabs_other:
                        toMy = true;
                        t.setIsmy(0);
                        myTabAdapter.add(t);
                        gvMy.getViewTreeObserver().addOnGlobalLayoutListener(global);

                        break;
                }
                otherTabAdapter.notifyDataSetChanged();
                myTabAdapter.notifyDataSetChanged();

            }
        }
    };
    private ViewTreeObserver.OnGlobalLayoutListener global = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            if(toMy){
                gvMy.getChildAt(gvMy.getLastVisiblePosition()).getLocationInWindow(locEnd);
                gvMy.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            else{
                gvOther.getChildAt(gvOther.getLastVisiblePosition()).getLocationInWindow(locEnd);
                gvOther.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }

            startAnimation(moveView);
        }
    };

    private ImageView getMoveView(View v){
        v.setDrawingCacheEnabled(true);
        Bitmap map = v.getDrawingCache();
        ImageView img = new ImageView(this);
        img.setImageBitmap(map);
        img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        return img;
    }

    private void startAnimation(View moveView){
        flDecor.addView(moveView);
        TranslateAnimation animation = new TranslateAnimation(locStar[0],locEnd[0],locStar[1],locEnd[1]);
        animation.setDuration(100);
        animation.setFillAfter(true);
        animation.setAnimationListener(listener);
        moveView.startAnimation(animation);

    }
    private Animation.AnimationListener listener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            flDecor.removeAllViews();
            otherTabAdapter.setShowText(true);
            myTabAdapter.setShowText(true);
            if(toMy){
                otherTabAdapter.remove(position);
            }else{
                myTabAdapter.remove(position);
            }
            otherTabAdapter.notifyDataSetChanged();
            myTabAdapter.notifyDataSetChanged();
            isAnimitioning = false;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    private void initDecor(){
        ViewGroup root = (ViewGroup)getWindow().getDecorView();
        flDecor = new FrameLayout(this);
        flDecor.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT));
        root.addView(flDecor);

    }

    private View.OnClickListener clicks = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.title_back:
                    finish();
                    break;
                case R.id.title_tv_right:
                    Log.i("guxu", "onClick: "+otherTabAdapter.getTabs().size()+"==="+myTabAdapter.getTabs().size());
                    XUtils.updateTabs(otherTabAdapter.getTabs());
                    XUtils.updateTabs(myTabAdapter.getTabs());
                    setResult(Code.RESP_SELECT_TAB);
                    finish();
                    break;
            }
        }
    };
    public static void startActivityForResult(Activity activity){
        Intent intent = new Intent(activity,SelectTabActivity.class);
        activity.startActivityForResult(intent, Code.REQ_SELECT_TAB);
    }

    class TabAdapter extends BaseAdapter{
        private List<Tab> tabs;
        private boolean showText = true;
        public TabAdapter(List<Tab>tabs){
            this.tabs = tabs;
        }
        @Override
        public int getCount() {
            return tabs!=null? tabs.size():0;
        }

        @Override
        public Tab getItem(int position) {
            return tabs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = null;
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.layout_item_tab,null);
                tv = (TextView) convertView.findViewById(R.id.tab_item_tv);
                convertView.setTag(tv);
            }else{
                tv = (TextView) convertView.getTag();
            }
            tv.setText(getItem(position).getTab());
            if(position == tabs.size()-1 && !showText){
                tv.setTextColor(Color.TRANSPARENT);
            }else{
                tv.setTextColor(getResources().getColor(R.color.title_text_color));
            }
            return convertView;
        }
        public void add(Tab T){
            this.tabs.add(T);

        }
        public void remove(int position){
            this.tabs.remove(position);
        }
        public List<Tab> getTabs(){
            return tabs;
        }
        public void setShowText(boolean showText) {
            this.showText = showText;
        }

    }
}
