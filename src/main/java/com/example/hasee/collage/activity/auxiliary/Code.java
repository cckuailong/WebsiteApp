package com.example.hasee.collage.activity.auxiliary;

import android.os.Environment;

/**
 * Created by hasee on 2016/11/27.
 */

public class Code {
    public static  String Phone_match = "^1(3|4|5|7|8)\\d{9}$";
    public static String email_match = "^\\w+@\\w+\\.(com|cn)(.cn)?$";
    public  static String PWD_MATCH = "^\\w{6,20}$";
    public static final int REQ_SELECT_TAB = 26;
    public static final int RESP_SELECT_TAB = 27;
    public static final int REQ_SELECT_PHOTO = 20;
    public static final int REDP_CUT_PHOTO = 14;
    public static final String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
}
