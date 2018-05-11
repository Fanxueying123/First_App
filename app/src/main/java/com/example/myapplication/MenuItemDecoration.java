package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 董寒冰 on 2018/4/20.
 */

public class MenuItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;
    private int dividerHeight;
    private Context mcontext;
    public MenuItemDecoration(Context context){
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        mcontext=context;
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childcount=parent.getChildCount();
        for (int i=0;i<childcount;i++){
            View view=parent.getChildAt(i);
            int index=parent.getChildAdapterPosition(view);
            if (index==0){
                continue;
            }
            float dividerTop = view.getTop() - dividerHeight;
            float dividerLeft = parent.getPaddingLeft();
            float dividerBottom = view.getTop();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();
            if (i%2==0){
                paint.setColor(Color.BLUE);
            }else {
                paint.setColor(Color.RED);
            }
            c.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = 30;
            dividerHeight = 30;
        }
    }
}
