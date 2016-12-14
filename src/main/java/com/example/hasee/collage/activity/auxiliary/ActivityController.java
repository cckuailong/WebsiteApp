package com.example.hasee.collage.activity.auxiliary;

import android.app.Activity;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2016/11/22.
 */

public class ActivityController {
    private static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void closeAllActivity(){
        for(Activity activity:activities){
            activity.finish();
        }
        Process.killProcess(Process.myPid());
    }
}
