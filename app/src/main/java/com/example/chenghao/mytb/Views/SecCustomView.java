package com.example.chenghao.mytb.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.chenghao.mytb.R;

/**
 * Created by chenghao on 2016/10/8.
 */
public class SecCustomView extends View {

    private Bitmap mImage1;
    private Bitmap mImage2;
    private String mText;
    private int mColor;
    private int mTextSize;

    private Paint mPaint;
    private Rect rect;
    private Rect mTextBound;

    private int mWidth;
    private int mHeight;
    public SecCustomView(Context context){
      this(context,null);
    }

    public SecCustomView(Context context, AttributeSet attr){
        this(context, attr,0);
    }

    public SecCustomView(Context context, AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
        TypedArray getType=context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SecCustomView,defStyle,0);
        for(int i=0;i<getType.getIndexCount();i++){
            int attr=getType.getIndex(i);
            switch (attr){
                case R.styleable.SecCustomView_sec_image1:
                    mImage1= BitmapFactory.decodeResource(getResources(),getType.getResourceId(attr,0));
                    break;
                case R.styleable.SecCustomView_sec_image2:
                    mImage2=BitmapFactory.decodeResource(getResources(),getType.getResourceId(attr,0));
                    break;
                case R.styleable.CustomView_titleText:
                    mText=getType.getString(attr);
                    break;
                case R.styleable.CustomView_titleTextColor:
                    mColor=getType.getColor(attr,Color.BLACK);
                    break;
                case R.styleable.CustomView_titleTextSize:
                    mTextSize=getType.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            16,getResources().getDisplayMetrics()));
                    break;
            }
        }
        getType.recycle();

        mPaint=new Paint();
        rect=new Rect();
        mTextBound=new Rect();
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mText,0,mText.length(),mTextBound);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specMode=MeasureSpec.getMode(widthMeasureSpec);
        int specSize=MeasureSpec.getSize(widthMeasureSpec);

        //根据图片来设置宽度
        if(specMode==MeasureSpec.EXACTLY){
            mWidth=specSize;
        }else {
            int desireByImg1=getPaddingLeft()+getPaddingRight()+mImage1.getWidth();
            int desireByImg2=getPaddingLeft()+getPaddingRight()+mImage2.getWidth();
            int desireByTitle=getPaddingLeft()+getPaddingRight()+mTextBound.width();

            if(specMode==MeasureSpec.AT_MOST){
                int desire=Math.max(Math.max(desireByImg1,desireByImg2),desireByTitle);
                mWidth=Math.min(desire,specSize);
            }
        }

        //绘制第二张图
        specMode=MeasureSpec.getMode(heightMeasureSpec);
        specSize=MeasureSpec.getSize(heightMeasureSpec);

        if(specMode==MeasureSpec.EXACTLY){
            mHeight=specSize;
        }else {
            int desireByImg=getPaddingBottom()+getPaddingTop()+mImage1.getHeight()+mTextBound.height()+mImage2.getHeight();
            mHeight=Math.min(desireByImg,specSize);
        }

        setMeasuredDimension(mWidth,mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制边框
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setStyle(Paint.Style.FILL);

        //绘制第一张图
        rect.left=getPaddingLeft();
        rect.right=mWidth-getPaddingRight();
        rect.top=getPaddingTop();
        rect.bottom=mHeight-getPaddingBottom()-mImage1.getHeight();
        canvas.drawBitmap(mImage1,null,rect,mPaint);

        //绘制第二张图
        rect.top-=mImage1.getHeight();
        rect.bottom=mImage1.getHeight();
        canvas.drawBitmap(mImage2,null,rect,mPaint);

    }
}
