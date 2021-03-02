package com.example.imoocmusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.imoocmusic.R;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.views
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/20 17:31
 * @change
 * @chang time
 * @class describe
 ******/


public class InputView  extends FrameLayout {

    private int inputIcon;
    private  String inputHint;
    private  boolean isPassword;

    private View mView;
    private ImageView mIvIcon;
    private EditText mEtInput;

    public InputView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);

    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }


    private void init(Context context,AttributeSet attrs){
        if(attrs==null){
            return ;
        }
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.inputView);
        inputIcon=typedArray.getResourceId(R.styleable.inputView_input_icon,R.mipmap.logo);
        inputHint=typedArray.getString(R.styleable.inputView_input_hint);
        isPassword=typedArray.getBoolean(R.styleable.inputView_is_password,false);
        typedArray.recycle();

//        绑定layout布局
        mView=LayoutInflater.from(context).inflate(R.layout.input_view,this,false);
        mIvIcon=mView.findViewById(R.id.iv_icon);
        mEtInput=mView.findViewById(R.id.et_input);

//        布局关联属性
        mIvIcon.setImageResource(inputIcon);
        mEtInput.setHint(inputHint);
        mEtInput.setInputType(isPassword? InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD:InputType.TYPE_CLASS_PHONE);



        addView(mView);
    }

    public String getInputStr(){
        return mEtInput.getText().toString().trim();
    }
}
