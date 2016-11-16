package com.example.chenghao.mytb.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by chenghao on 2016/10/17.
 */
public class GM2048Layout extends ViewGroup {

    private final static int itemColumn=4;
    private int mMargin=10;
    private int padding;


    //判断是否刚进去游戏
    private boolean once;
    //用于存放所有小方块
    private GM2048items mGm2048items[];

    public GM2048Layout(Context context){super(context);}

    public GM2048Layout(Context context, AttributeSet attributeSet){super(context,attributeSet);}

    public GM2048Layout(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
        mMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                mMargin, getResources().getDisplayMetrics());
        padding=min(getPaddingLeft(),getPaddingRight(),getPaddingRight(),getPaddingBottom());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int sideLength=Math.min(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
        int itemWidth=(sideLength-padding*2-mMargin*(itemColumn-1))/itemColumn;
        if(!once){
            if (mGm2048items==null){
                mGm2048items=new GM2048items[itemColumn*itemColumn];
            }
            for(int i=0;i<mGm2048items.length;i++){
                GM2048items gm2048item=new GM2048items(getContext());
                gm2048item.setId(i+1);
                mGm2048items[i]=gm2048item;
                RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(itemWidth,itemWidth);
                if ((i + 1) % itemColumn != 0)
                {
                    lp.rightMargin = mMargin;
                }
                // 如果不是第一列
                if (i % itemColumn != 0)
                {
                    lp.addRule(RelativeLayout.RIGHT_OF,//
                            mGm2048items[i - 1].getId());
                }
                // 如果不是第一行，//设置纵向边距，非最后一行
                if ((i + 1) > itemColumn)
                {
                    lp.topMargin = mMargin;
                    lp.addRule(RelativeLayout.BELOW,//
                            mGm2048items[i - itemColumn].getId());
                }
                addView(gm2048item, lp);if ((i + 1) % itemColumn != 0)
                {
                    lp.rightMargin = mMargin;
                }
                // 如果不是第一列
                if (i % itemColumn != 0)
                {
                    lp.addRule(RelativeLayout.RIGHT_OF,//
                            mGm2048items[i - 1].getId());
                }
                // 如果不是第一行，//设置纵向边距，非最后一行
                if ((i + 1) > itemColumn)
                {
                    lp.topMargin = mMargin;
                    lp.addRule(RelativeLayout.BELOW,//
                            mGm2048items[i - itemColumn].getId());
                }
                addView(gm2048item, lp);
            }
        }
        once=true;
        setMeasuredDimension(sideLength,sideLength);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    private int min(int a,int b,int c,int d){
        return Math.min(Math.min(a,b),Math.min(c,d));
    }

}
