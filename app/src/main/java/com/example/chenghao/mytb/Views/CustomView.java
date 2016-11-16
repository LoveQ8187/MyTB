package com.example.chenghao.mytb.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;


import com.example.chenghao.mytb.R;


/**
 * Created by chenghao on 2016/9/29.
 * 自定义view
 */
public class CustomView extends View {

    private String mtitleText;
    private int mtitleSize;
    private int mtitleColor;

    private Rect mBound;
    private Paint mPaint;

    private int radio;
    private int px;
    private int py;
    public CustomView(Context context){
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attr){
        this(context, attr,0);
    }

    public CustomView(Context context,AttributeSet attr,int defStyle){
        super(context,attr,defStyle);
        TypedArray ta=context.getTheme().obtainStyledAttributes(attr, R.styleable.CustomView,defStyle,0);
        for(int i=0;i<ta.getIndexCount();i++) {
            int attrs=ta.getIndex(i);
            switch (attrs) {
                case R.styleable.CustomView_titleText:
                    mtitleText=ta.getString(attrs);
                    break;
                case R.styleable.CustomView_titleTextColor:
                    mtitleColor=ta.getColor(attrs,Color.BLACK);
                    break;
                case R.styleable.CustomView_titleTextSize:
                    mtitleSize=ta.getDimensionPixelSize(attrs,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        ta.recycle();

        mPaint=new Paint();
        mPaint.setTextSize(mtitleSize);
        mBound=new Rect();
        mPaint.getTextBounds(mtitleText,0,mtitleText.length(),mBound);


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("chenghao","click");
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int heigth;
        if(MeasureSpec.getMode(widthMeasureSpec)==MeasureSpec.EXACTLY){
            width=MeasureSpec.getSize(widthMeasureSpec);
        }else {
            mPaint.setTextSize(mtitleSize);
            mPaint.getTextBounds(mtitleText,0,mtitleText.length(),mBound);
            float textWidth=mBound.width();
            int desired=(int)(getPaddingLeft()+textWidth+getPaddingRight());
            width=desired;
        }

        if(MeasureSpec.getMode(heightMeasureSpec)==MeasureSpec.EXACTLY){
            heigth=MeasureSpec.getSize(heightMeasureSpec);
        }else {
            mPaint.setTextSize(mtitleSize);
            mPaint.getTextBounds(mtitleText,0,mtitleText.length(),mBound);
            float textHeight=mBound.height();
            int desired=(int)(getPaddingTop()+textHeight+getPaddingBottom());
            heigth=desired;
        }

        radio=getMeasuredWidth()/12;
        px=getMeasuredWidth()/2;
        py=getMeasuredHeight()/2;
        setMeasuredDimension(width,heigth);
       // super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        //mPaint.setColor(mtitleColor);
        //canvas.drawText(mtitleText,getWidth()/2-mBound.width()/2,getHeight()/2-mBound.height()/2,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(px,py,radio,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(px,py,radio*4/5,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(px,py,radio*3/5,mPaint);
        mPaint.setColor(Color.CYAN);
        canvas.drawCircle(px,py,radio*2/5,mPaint);
        mPaint.setColor(Color.parseColor("#ff00ff"));
        canvas.drawCircle(px,py,radio/5,mPaint);
    }



}
