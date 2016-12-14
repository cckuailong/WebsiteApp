package com.example.hasee.collage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.DataId;
import com.example.hasee.collage.activity.Bean.saveNews;
import com.example.hasee.collage.activity.PageFragment.NewsAdapter;
import com.example.hasee.collage.activity.PageFragment.PageFragment;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.XUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.hasee.collage.R.drawable.save;

public class ColleteNewsActivity extends BaseActivity {
    private ViewPager view;
    private NewsAdapter adapter;
    private ListView listView;
    private int indext;
    private  List<saveNews> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collete_news);
        listView = (ListView) findViewById(R.id.lv_main);
        list = new ArrayList<>();
        list = XUtils.getnew();
        adapter = new NewsAdapter(ColleteNewsActivity.this,list);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(clickListener);
        listView.setOnItemClickListener(clicks);

    }
    private AdapterView.OnItemClickListener clicks = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            NewsInfoActivity.startActivity(ColleteNewsActivity.this,list.get(position).getUrl(),list.get(position).getTitle(),list.get(position).getPicUrl());
        }
    };
    private AdapterView.OnItemLongClickListener clickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            indext = position;
            list.remove(position);
            XUtils.save(list);
            adapter.notifyDataSetChanged();
            XUtils.show("删除成功");
            return true;
        }
    };
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ColleteNewsActivity.class));
    }

}
