package com.example.chenghao.mytb.Activitys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chenghao.mytb.Fragments.BackHandleFragment;
import com.example.chenghao.mytb.Fragments.CookInfoFragment;
import com.example.chenghao.mytb.Fragments.GetCookBookFragment;
import com.example.chenghao.mytb.Fragments.GetPM2_5Fragment;
import com.example.chenghao.mytb.Fragments.GetWeatherFragment;
import com.example.chenghao.mytb.Fragments.HttpFragment;
import com.example.chenghao.mytb.Interfaces.BackHandleInterface;
import com.example.chenghao.mytb.R;

public class HttpTestActivity extends TableActivity implements BackHandleInterface{

    private FragmentManager fragmentManager=null;
    private BackHandleFragment mBackHandleFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager=getFragmentManager();
        final Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.selected_serve);
        toolbar.setTitle("HttpTest");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.get_weather:
                        try {
                            toolbar.setTitle("Weather");
                            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, new GetWeatherFragment());
                            fragmentTransaction.commit();
                        }catch (Exception ex){
                            Toast.makeText(HttpTestActivity.this,getString(R.string.already_selected),Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.get_pm2_5:
                        try {
                            toolbar.setTitle("Air");
                            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, new GetPM2_5Fragment());
                            fragmentTransaction.commit();
                        }catch (Exception ex){
                            Toast.makeText(HttpTestActivity.this,getString(R.string.already_selected),Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.get_cookbook:
                        try {
                            toolbar.setTitle("CookBook");
                            final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                            GetCookBookFragment getCookBookFragment=new GetCookBookFragment();
                            getCookBookFragment.setOnItemClick(new GetCookBookFragment.OnItemClick() {
                                @Override
                                public void OnClick() {
                                    //将isReturnForBackStack设置为true，当CookInfoFragment从back栈返回时，加载listview
                                    GetCookBookFragment.isReturnForBackStack=true;
                                    FragmentTransaction fragmentTransaction=fragmentManager .beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, new CookInfoFragment());
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
                                }
                            });
                            fragmentTransaction.replace(R.id.fragment_container, getCookBookFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }catch (Exception ex){
                            Toast.makeText(HttpTestActivity.this,getString(R.string.already_selected),Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:break;
                }
                 return false;
            }

        });

        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        HttpFragment httpFragment=new HttpFragment();
        httpFragment.setChangeAcitityToolbarAction(new HttpFragment.ChangeAcitityToolbar() {
            @Override
            public void setToolbar(int tag) {
                switch (tag){
                    case 1:
                        toolbar.setTitle("Weather");break;
                    case 2:
                        toolbar.setTitle("Air");break;
                    case 3:
                        toolbar.setTitle("CookBook");break;
                    default:
                        break;
                }
            }
        });
        fragmentTransaction.add(R.id.fragment_container,httpFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setSelectedFragment(BackHandleFragment mBackHandleFragment) {
        this.mBackHandleFragment=mBackHandleFragment;
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()==0){
            super.onBackPressed();
        }else {
            getFragmentManager().popBackStack();
        }
    }
}
