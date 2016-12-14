package com.example.hasee.collage.activity.auxiliary;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by hasee on 2016/11/28.
 */

public class CodeTimerTask extends AsyncTask<Void,Void,Void>{
    private int time = 90;
    private TextView textView;
    private static CodeTimerTask task;
    private static boolean isNew;
    private boolean isRun;
    private CodeTimerTask(){};
    public  static CodeTimerTask getInstence(){
        if(task == null){
            task = new CodeTimerTask();
            isNew = true;
        }
        return task;
    }
    public void startTimer(TextView textview){
        this.textView = textview;
        if(isNew){
            execute();
        }

    }

    @Override
    protected void onPreExecute() {
        time = 90;
        isNew = false;
        isRun = true;
    }

    @Override
    protected Void doInBackground(Void... params) {

            try {
                for(;time>=0;time--) {
                    publishProgress();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        if(textView != null){
            textView.setText(String.format("%ds",time));
            if(textView.isEnabled()){
                textView.setEnabled(false);
            }
        }

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        end();
    }
    private void end(){
        if(textView != null){
            textView.setEnabled(true);
            textView.setText("获取验证码");
        }

        cancle();
    }

    public void cancle(){
        if(task != null){
            //super.cancle();
            super.onCancelled();
            isRun = false;
            task = null;
            isNew = true;
        }

    }
    public boolean isRun(){
        return isRun;
    }
}
