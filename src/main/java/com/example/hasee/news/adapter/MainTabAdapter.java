package com.example.hasee.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hasee.news.Fragment.FirstPageFragment;

import java.util.List;

/**
 * Created by hasee on 2016/10/19.
 */

public class MainTabAdapter extends FragmentPagerAdapter{

    private List<FirstPageFragment> mList_fragment;
    private String[] mList_title;

    public MainTabAdapter(FragmentManager fm,List<FirstPageFragment> list_fragment,String []mList_title) {
        super(fm);

        mList_fragment= list_fragment;
        this.mList_title = mList_title;
    }

    @Override
    public Fragment getItem(int position) {
        return mList_fragment.get(position);
    }

    @Override
    public int getCount() {
        return mList_fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList_title[position];
    }
}
