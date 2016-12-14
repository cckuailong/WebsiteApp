package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hasee.collage.R;
import com.example.hasee.collage.activity.https.XUtils;

/**
 * Created by hasee on 2016/12/1.
 */

public class UserInfoView extends RelativeLayout implements View.OnClickListener,View.OnFocusChangeListener{
    private TextView tvLab;
    private ImageView img;
    private EditText etInput;
    private ImageView arr;
    private OnClickListener l;
    private boolean enable;
    public UserInfoView(Context context) {
        super(context);
        init(null);
    }

    public UserInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public UserInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private void init(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_userinfo,this);
        img = (ImageView) findViewById(R.id.userinfo_img);
        tvLab = (TextView)findViewById(R.id.userinfo_label);
        etInput = (EditText)findViewById(R.id.userinfo_input);
        arr = (ImageView)findViewById(R.id.userinfo_next);
        this.setOnClickListener(this);
        etInput.setOnFocusChangeListener(this);
        if(attrs == null){
            return;
        }
        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.UserInfoView);
        int N = a.getIndexCount();
        for(int i=0;i<N;i++){
            int index = a.getIndex(i);
            switch (index){
                case R.styleable.UserInfoView_uv_arr_visibility:
                    setVisibility(arr,a.getInt(index,0));
                    break;
                case R.styleable.UserInfoView_uv_img_visibility:
                    setVisibility(img,a.getInt(index,0));
                    break;
                case R.styleable.UserInfoView_uv_input_enable:
                    enable = a.getBoolean(index,false);
                    break;
                case R.styleable.UserInfoView_uv_input_visibility:
                    setVisibility(etInput,a.getInt(index,0));
                    break;
                case R.styleable.UserInfoView_uv_input_hint:
                    etInput.setHint(a.getString(index));
                    break;
                case R.styleable.UserInfoView_uv_label_text:
                    tvLab.setText(a.getString(index));
                    break;
                case R.styleable.UserInfoView_uv_input_text:
                    etInput.setText(a.getString(index));
                    break;

            }
        }
    }
    private void setVisibility(View v,int visable){
        switch (visable){
            case 0:
                v.setVisibility(View.VISIBLE);
                break;
            case 1:
                v.setVisibility(View.GONE);
                break;
        }
    }



    @Override
    public void onClick(View v) {
        if(! enable && l!= null){
            l.onClick(this);
        }else{
            setEnabled(true);
            etInput.requestFocus();
        }
    }
    public void setText(String text){
        etInput.setText(text);
    }
    public void setEnabled(boolean enable){
        etInput.setEnabled(enable);
    }
    public String getText(){
        return etInput.getText().toString().trim();
    }
    public void setRightImgUrl(String url){

        XUtils.display(img,url);
    }
    public void setRightImgBitmap(Bitmap bitmap){
        img.setImageBitmap(bitmap);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            setEnabled(false);
        }
    }
}
