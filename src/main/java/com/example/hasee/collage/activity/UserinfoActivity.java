package com.example.hasee.collage.activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.Bean.UserId;
import com.example.hasee.collage.activity.auxiliary.Code;
import com.example.hasee.collage.activity.auxiliary.MyApp;
import com.example.hasee.collage.activity.https.XUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import view.TitleView;
import view.UserInfoView;

public class UserinfoActivity extends BaseActivity {
    @ViewInject(R.id.userinfo_photo)
    private UserInfoView mePhoto;
    @ViewInject(R.id.userinfo_nick)
    private UserInfoView meNick;
    @ViewInject(R.id.userinfo_title)
    private TitleView title;
    @ViewInject(R.id.userinfo_save)
    private Button btSav;
    private int Size;
    private InputStream inputStream;
    private UserId u;
    private String url;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ViewUtils.inject(this);
        initData();
        title.setBackClickListener(clicks);
        btSav.setOnClickListener(clicks);
        mePhoto.setOnClickListener(clicks);

    }
    private void initData(){
        u = ((MyApp)getApplication()).getUser();
        if(u == null){
            XUtils.show("没有登录");
            finish();
            return;
        }
        //mePhoto.setRightImgUrl(u.getGender());
        meNick.setText(u.getNickname()==null?"":u.getNickname());
    }
    private View.OnClickListener clicks = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.title_back:
                    finish();
                    break;
                case R.id.userinfo_save:
                    try {
                        save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.userinfo_photo:
                    selectorPhoto();
                    break;
            }

        }
    };
    private void save() throws IOException {
        if(url != null){
            u.setGender(url);

        }
        if(meNick.getText() !=null){
            u.setNickname(meNick.getText());
        }
        XUtils.saveUserId(u);
        sendBroadcast(new Intent("com.guxu.head_changed"));
        finish();
    }
    private void selectorPhoto() {
        Intent in = new Intent(Intent.ACTION_PICK);
        in.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(in, Code.REQ_SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Code.REQ_SELECT_PHOTO && resultCode == RESULT_OK  && data != null){
            Uri uri = data.getData();
            String[] projection = new String[]{MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(uri,projection,null,null,null);
            if(c != null && c.moveToFirst()){
                String Path = c.getString(c.getColumnIndex(projection[0]));
                toCutPhoto(Path);
            }
        }else if(requestCode == Code.REDP_CUT_PHOTO && resultCode == RESULT_OK && data!= null){
            Bitmap bmp = data.getParcelableExtra("data");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG,70,bos);
            Size = bos.size();

            inputStream = new ByteArrayInputStream(bos.toByteArray());
            mePhoto.setRightImgBitmap(bmp);
            saveBitmap(bmp);

        }
    }
    private void toCutPhoto(String Path){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(new File(Path)), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent,Code.REDP_CUT_PHOTO);
    }
/**
    private void showGender(){
        Log.i("======", "showGender: dknvkdnvkdnvknvkd");
        new AlertDialog.Builder(this).setSingleChoiceItems(new String[]{"男","女"},1,dialogClickd).show();
    }
    private DialogInterface.OnClickListener dialogClickd = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch(which){
                case 1:
                    meGender.setText("女");
                    break;
                case 0:
                    meGender.setText("男");
                    break;
            }
        }
    };**/
    private void saveBitmap(Bitmap mBitmap){
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        Calendar now = new GregorianCalendar();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String filename = simpleDate.format(now.getTime());
       // Log.i("file", "saveBitmap: "+Code.dir);
        try {
            File file = new File(getSDCardPath() +"/"+ filename + ".png");
            if(file.isFile()){
                file.delete();
            }
                File file1 = new File(getSDCardPath() +"/"+ filename + ".png");
                url = getSDCardPath() +"/"+ filename + ".png";
                FileOutputStream out = new FileOutputStream(file1);
                mBitmap.compress(Bitmap.CompressFormat.PNG, 70, out);
                out.flush();
                out.close();

            file.exists();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void startActivity(Context context){
        Intent intent = new Intent(context,UserinfoActivity.class);
        context.startActivity(intent);
    }
    private String getSDCardPath() {
        File sdcardDir = null;
        // 判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }
}
