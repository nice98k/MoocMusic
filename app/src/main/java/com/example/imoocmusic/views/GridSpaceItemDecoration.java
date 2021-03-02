package com.example.imoocmusic.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.views
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/22 12:54
 * @change
 * @chang time
 * @class describe
 ******/
public class GridSpaceItemDecoration  extends RecyclerView.ItemDecoration {
    private int mSpace;

    public GridSpaceItemDecoration(int mSpace,RecyclerView parent) {
        this.mSpace = mSpace;
        getRecyclerViewOffsets(parent);
    }

    
    /**
     * @description:    
     * @param:
     * @return:         
     * @author:         nice
     * @time:           2021/2/22 12:57
     */
    
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=mSpace;
    }

    private void getRecyclerViewOffsets(RecyclerView parent){
        LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)parent.getLayoutParams();
        layoutParams.leftMargin=-mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
