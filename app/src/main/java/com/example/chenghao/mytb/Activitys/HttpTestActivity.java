package com.example.chenghao.mytb.Activitys;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.chenghao.mytb.Fragments.CookInfoFragment;
import com.example.chenghao.mytb.Fragments.GetCookBookFragment;
import com.example.chenghao.mytb.Fragments.GetPM2_5Fragment;
import com.example.chenghao.mytb.Fragments.GetWeatherFragment;
import com.example.chenghao.mytb.R;

public class HttpTestActivity extends TableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, new GetWeatherFragment());
                            fragmentTransaction.commit();
                        }catch (Exception ex){
                            Toast.makeText(HttpTestActivity.this,getString(R.string.already_selected),Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.get_pm2_5:
                        try {
                            toolbar.setTitle("Air");
                            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, new GetPM2_5Fragment());
                            fragmentTransaction.commit();
                        }catch (Exception ex){
                            Toast.makeText(HttpTestActivity.this,getString(R.string.already_selected),Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.get_cookbook:
                        try {
                            toolbar.setTitle("CookBook");
                            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                            GetCookBookFragment getCookBookFragment=new GetCookBookFragment();
                            getCookBookFragment.setOnItemClick(new GetCookBookFragment.OnItemClick() {
                                //当点击GetCookBookFragment中的cooklist，回调此方法,跳转至CookInfoFragment
                                @Override
                                public void OnClick() {
                                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, new CookInfoFragment());
                                    fragmentTransaction.commit();
                                }
                            });
                            fragmentTransaction.replace(R.id.fragment_container, getCookBookFragment);
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

    }
}
