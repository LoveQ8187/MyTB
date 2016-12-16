package com.example.chenghao.mytb.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chenghao.mytb.Animations.Interpolators.InterpolatorStyles;
import com.example.chenghao.mytb.R;
import com.example.chenghao.mytb.Animations.Animations;
import com.example.chenghao.mytb.Utils.EyesTouchLisener;


public class AnimationTestActivity extends AppCompatActivity implements View.OnClickListener{

    private final static int ROTATE_TAG=1;
    private final static int SCALE_TAG=2;
    private final static int ALPHA_TAG=3;
    private final static int TRANSITION_TAG=4;
    private final static String TAG="MyTB:";

    private static String interpolatorName=null;//差值器名称
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_test_layout);

        Toolbar toolbar=(Toolbar)findViewById(R.id.animation_toolbar);
        toolbar.inflateMenu(R.menu.animation_activity_menu);
        toolbar.setTitle("AnimationTest");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.set_interpolator_styles:
                        createIStylesDialog().show();
                        break;
                }
                return false;
            }
        });

        Button rotateButton=(Button)findViewById(R.id.rotate_button);
        rotateButton.setTag(ROTATE_TAG);
        rotateButton.setOnClickListener(this);
        Button scaleButton=(Button)findViewById(R.id.scale_button);
        scaleButton.setTag(SCALE_TAG);
        scaleButton.setOnClickListener(this);
        Button alphaButton=(Button)findViewById(R.id.alpha_button);
        alphaButton.setTag(ALPHA_TAG);
        alphaButton.setOnClickListener(this);
        Button transitionButton=(Button)findViewById(R.id.transiton_button);
        transitionButton.setTag(TRANSITION_TAG);
        transitionButton.setOnClickListener(this);

        ImageView pic=(ImageView)findViewById(R.id.pic);
        pic.setOnTouchListener(new EyesTouchLisener(pic));

    }

    public void startClickAction(View view){
       // ObjectAnimator.ofFloat(view,"rotationX",0.0f,3600.0f).setDuration(10000).start();
    }
    @Override
    public void onClick(View view) {
        
        int getTag=(Integer)view.getTag();
        ImageView pic=(ImageView)findViewById(R.id.pic);
        pic.clearAnimation();
        AnimationSet animationSet=null;
        switch (getTag){
            case ROTATE_TAG:
                animationSet=Animations.setRotateAnimation();
               /* int width=pic.getWidth();
                int heigth=pic.getHeight();
                pic.startAnimation(Animations.setRotate3DAnimation(width/2.0f,heigth/2.0f));*/

                /*TestObjectAnimations testObjectAnimations=new TestObjectAnimations(pic);
                testObjectAnimations.runAnimation();*/
                break;
            case SCALE_TAG:
                animationSet=Animations.setScaleAnimation();
                break;
            case ALPHA_TAG:
                animationSet=Animations.setAlphaAnimation();
                break;
            case TRANSITION_TAG:
                animationSet=Animations.setTransitionAnimation();
                break;
            default:break;
        }
        Log.d(TAG,"Setted Animation");
        if(animationSet!=null) {
            pic.startAnimation(setInterpolatorStyle(animationSet));
        }else {
            Log.d(TAG,"Special Test");
        }
    }

    //弹出菜单，选择插值器,设置插值器名称
    private AlertDialog.Builder createIStylesDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(AnimationTestActivity.this);
        builder.setTitle(R.string.interpolator_styles);
        builder.setItems(InterpolatorStyles.getIStyles(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                interpolatorName=InterpolatorStyles.getIStyles()[i];
                Toast.makeText(AnimationTestActivity.this,interpolatorName,Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        return builder;
    }
    //将插值器注入到动画中
    private AnimationSet setInterpolatorStyle(AnimationSet animationSet){
        //根据名称返回相应插值器
        Interpolator setInterpolator=InterpolatorStyles.setInterpolatorStyle(interpolatorName);
        animationSet.setInterpolator(setInterpolator);
        return animationSet;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AnimationTestActivity.this,TableActivity.class));
        finish();
    }
}
