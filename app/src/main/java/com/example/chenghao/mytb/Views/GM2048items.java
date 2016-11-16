package com.example.chenghao.mytb.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenghao on 2016/10/17.
 */
public class GM2048items extends View {

    private int mNumber;
    private String sColor;;

    private String numberVelue;
    private Paint mPaint;
    private Rect mBound;

    public GM2048items(Context context){
        this(context,null);
    }

    public GM2048items(Context context,AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public GM2048items(Context context, AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setmNumber(int number){
        this.mNumber=number;
        numberVelue=number+"";
        mPaint.setTextSize(30.0f);
        mPaint.setColor(Color.parseColor(getColor()));
        mBound=new Rect();
        mPaint.getTextBounds(numberVelue,0,numberVelue.length(),mBound);
        invalidate();
    }
    public int getmNumber(){
        return mNumber;
    }

    public String getColor(){

        switch (mNumber){
            case 0:
                sColor="#CCC0B3";break;
            case 2:
                sColor="#EEE4DA";break;
            case 4:
                sColor="#EDE0C8";break;
            case 8:
                sColor="#F2B179";break;
            case 16:
                sColor="F49563";break;
            case 32:
                sColor = "#F5794D";break;
            case 64:
                sColor = "#F55D37";break;
            case 128:
                sColor = "#EEE863";break;
            case 256:
                sColor = "#EDB04D";break;
            case 512:
                sColor = "#ECB04D";break;
            case 1024:
                sColor = "#EB9437";break;
            case 2048:
                sColor = "#EA7821";break;
            default:
                sColor = "#EA7821";
                break;
        }

        return sColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        if(mNumber!=0)drawText(canvas);
    }

    private void drawText(Canvas canvas){
        mPaint.setColor(Color.BLACK);
        float x = (getWidth() - mBound.width()) / 2;
        float y = getHeight() / 2 + mBound.height() / 2;
        canvas.drawText(numberVelue, x, y, mPaint);
    }
}
