package com.example.hasee.collage.activity.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2016/12/10.
 */

public class ListTab {
    private List<Tab> list;
    String [] tabs = {"头条","财经","娱乐","体育","军事","科技","国际","社会"};
    public ListTab(){
        Tab tab;
        list = new ArrayList<>();
        for(int i = 0;i<8;i++){
            tab = new Tab();
            tab.setTab(tabs[i]);
            tab.setIsmy(0);
            tab.setTid(i);
            list.add(tab);
        }
    }
    public List<Tab> getList(){
        return this.list;
    }

}
