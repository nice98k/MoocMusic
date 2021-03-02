package com.example.imoocmusic.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.views
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/22 11:21
 * @change
 * @chang time
 * @class describe
 ******/
public class WEqualHeightImageView extends AppCompatImageView {
    public WEqualHeightImageView(Context context) {
        super(context);
    }

    public WEqualHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualHeightImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);



    }
}
