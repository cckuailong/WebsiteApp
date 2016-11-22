package com.example.hasee.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hasee.news.Bean.BannerBean;
import com.example.hasee.news.Bean.Data;
import com.example.hasee.news.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

/**
 * Created by hasee on 2016/10/21.
 */

public class FirstPageAdapter extends RecyclerView.Adapter{


    private final int TYPE_HEAD = 0;//表示首个位置直接显示轮播图片
    private final int TYPE_NORMAL = 1;//表示正常的item布局
    private final int TYPE_FOOT = 2;//刷新布局
    private Context mcontext;
    private  BannerBean bean;
    private List<Data> item_data;
    public FirstPageAdapter(Context context, List<Data> item_data, BannerBean bean){
        this.mcontext = context;
        this.item_data = item_data;
        this.bean = bean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if(viewType == TYPE_HEAD){
            //在此处创建顶部banner的viewholder
            holder = new BannerViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.itemheader_banner
            ,parent,false));
        }else if(viewType == TYPE_FOOT){
            holder = new FootViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.itemfooter
                    ,parent,false));
        }
        else{
            holder = new ItemViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_firstfragment
            ,parent,false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof  BannerViewHolder){
            BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;
            bannerViewHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            bannerViewHolder.banner.setBannerTitle(bean.getTitle());
            bannerViewHolder.banner.setImages(bean.getImg_url());

        }else if(holder instanceof  ItemViewHolder){
            //处理每个item的布局
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.simpleview.setImageURI(item_data.get(position-1).getThumbnail_pic_s());
            itemViewHolder.textView.setText(item_data.get(position-1).getTitle());
            if(listener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(v,position);
                    }
                });
            }

        }
    }

    @Override
    /*
    创建什么类型的viewholder
     */
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEAD;
        }else if(position+1 == getItemCount()){
            return TYPE_FOOT;

        }
        else{
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return item_data.size()+1;
    }

    //正常的item的布局管理
    class ItemViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView simpleview;
        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            simpleview = (SimpleDraweeView) itemView.findViewById(R.id.simpleView);
            textView = (TextView) itemView.findViewById(R.id.news_text);
        }
    }

    //首位banner的viewholder

    class BannerViewHolder extends  RecyclerView.ViewHolder{
        Banner banner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);

        }
    }
    class FootViewHolder extends  RecyclerView.ViewHolder{

        public FootViewHolder(View itemView) {
            super(itemView);

        }
    }
    //item的单击事件回调借口
    public interface MyitemClickListener{
        void onClick(View itemView,int position);
    }
    private MyitemClickListener listener;
    public void setMyItemClickListener(MyitemClickListener listener){
        this.listener = listener;
    }
}
