package com.example.chenghao.mytb.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenghao on 2016/10/9.
 */
//纵向列表布局
public class MyLinearLayout extends ViewGroup {
    public MyLinearLayout(Context context){
        super(context);
    }
    public MyLinearLayout(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
    }
    public MyLinearLayout(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(widthMeasureSpec);

        int width=0;
        int height=0;
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int cCount=getChildCount();
        int cWidth=0;
        int cHeight=0;

        MarginLayoutParams cParam=null;

        //测量出所有view所需的总高度和最大宽度
        for(int i=0;i<cCount;i++){
            View childView=getChildAt(i);
            cParam=(MarginLayoutParams)childView.getLayoutParams();
            cWidth=childView.getMeasuredWidth()+cParam.leftMargin+cParam.rightMargin;
            cHeight=childView.getHeight()+cParam.topMargin+cParam.bottomMargin;
            if(width<cWidth){
                width=cWidth;
            }
            height+=cHeight;
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int r, int t, int b) {
        int cCount=getChildCount();
        int cWidth=0;
        int cHeight=0;
        MarginLayoutParams cParam=null;


        int usedHeight=0;//表示已经布局的view所占的高度
        for(int i=0;i<cCount;i++){
            View childView=getChildAt(i);
            cWidth=childView.getMeasuredWidth();
            cHeight=childView.getMeasuredHeight();
            cParam=(MarginLayoutParams)childView.getLayoutParams();

            int cl=0,cr=0,ct=0,cb=0;
            cl=cParam.leftMargin;
            cr=cl+cWidth;
            ct=usedHeight+cParam.topMargin;
            cb=ct+cHeight;

            usedHeight=cb+cParam.bottomMargin;
            childView.layout(cl,ct,cr,cb);
        }
    }
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(
            ViewGroup.LayoutParams p)
    {
        return new MarginLayoutParams(p);
    }
}
