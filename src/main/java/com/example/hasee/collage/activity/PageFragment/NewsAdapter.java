package com.example.hasee.collage.activity.PageFragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.Data1;
import com.example.hasee.collage.activity.Bean.DataId;
import com.example.hasee.collage.activity.Bean.saveNews;
import com.example.hasee.collage.activity.https.XUtils;

public class NewsAdapter extends BaseAdapter{
	private List<saveNews> mList = new ArrayList<saveNews>();
	private LayoutInflater mInflater;

	public  NewsAdapter(Context context,List<saveNews> data) {
		mList = data;
		mInflater = LayoutInflater.from(context);
	}
	
	

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.itemlayout, null);
			
			viewHolder.tv_Icon   = (ImageView) convertView.findViewById(R.id.icon);
			viewHolder.tv_Content = (TextView) convertView.findViewById(R.id.content);
			viewHolder.tv_Title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(viewHolder);
			
			
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
			
		}
		viewHolder.tv_Content.setText(mList.get(position).getTitle());
		XUtils.display(viewHolder.tv_Icon,mList.get(position).getPicUrl());
		viewHolder.tv_Title.setText(mList.get(position).getTitle());
		
		return convertView;
	}

	class ViewHolder{
		public TextView tv_Title,tv_Content;
		public ImageView tv_Icon;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
