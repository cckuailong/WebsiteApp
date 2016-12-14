package view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.hasee.collage.R;

/**
 * Created by hasee on 2016/11/15.
 */

public class MEditText extends RelativeLayout implements View.OnFocusChangeListener,View.OnClickListener,View.OnTouchListener,TextWatcher{


    private RelativeLayout rRoot;
    private EditText etInput;
    private ImageView imgLable;
    private ImageView imgDel;
    private ImageView imgEye;
    private boolean delEnable;
    private boolean eyeEnable;
    public final static int input_type_password = 1;
    public final static int input_type_normal = 0;



    public MEditText(Context context) {
        super(context);
        init(null);
    }

    public MEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_medit,this);
        rRoot = (RelativeLayout) findViewById(R.id.medit_root);
        imgDel = (ImageView) findViewById(R.id.medit_del);
        imgLable = (ImageView) findViewById(R.id.medit_lable);
        imgEye = (ImageView) findViewById(R.id.medit_eye);
        etInput = (EditText) findViewById(R.id.medit_input);
        etInput.setOnFocusChangeListener(this);
        etInput.addTextChangedListener(this);
        imgDel.setOnClickListener(this);
        imgEye.setOnTouchListener(this);
        if(attrs == null){
            return;
        }
        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.MEditText);

        int X = a.getIndexCount();
        for(int i = 0;i<X;i++){
            int index = a.getIndex(i);
            switch (index){
                case R.styleable.MEditText_me_del_enable:
                    delEnable = a.getBoolean(index,false);
                    break;
                case R.styleable.MEditText_me_eye_enable:
                    eyeEnable = a.getBoolean(index,false);
                    break;
                case R.styleable.MEditText_me_hint:
                    setHint(a.getString(index));
                    break;
                case R.styleable.MEditText_me_lable_src:
                    imgLable.setImageDrawable(a.getDrawable(index));
                    break;
                case R.styleable.MEditText_me_text:
                    setText(a.getString(index));
                    break;
                case R.styleable.MEditText_input_type:
                    setInputType(a.getInt(index,0));
                    break;
            }
        }


    }
    public String getText(){
        return etInput.getText().toString().trim();
    }
    public void setInputType(int type){
        if(type == input_type_password){
            etInput.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            etInput.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
        }
    }
    public void setText(String text){
        etInput.setText(text);
    }
    public void setHint(String text){
        etInput.setText(text);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        rRoot.setSelected(hasFocus);
        if(delEnable && hasFocus && etInput.getText().length()>0){
            imgDel.setVisibility(View.VISIBLE);

        }else if(delEnable && !hasFocus){
            imgDel.setVisibility(View.GONE);
        }
        if(eyeEnable &&hasFocus){
            imgEye.setVisibility(View.VISIBLE);
        }else if(eyeEnable){
            imgEye.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        etInput.getText().clear();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            etInput.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
            return  true;
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            etInput.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            return true;
        }
        return false;
    }




    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(delEnable&&s.length() >0){
            imgDel.setVisibility(View.VISIBLE);
        }else if(delEnable){
            imgDel.setVisibility(View.GONE);
        }
    }
    public  void addTextChangedListener(TextWatcher watcher){
        etInput.addTextChangedListener(watcher);
    }
}
