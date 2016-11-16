package com.example.chenghao.mytb.Activitys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.chenghao.mytb.Fragments.CookInfoFragment;
import com.example.chenghao.mytb.Fragments.GetCookBookFragment;
import com.example.chenghao.mytb.Fragments.GetPM2_5Fragment;
import com.example.chenghao.mytb.Fragments.GetWeatherFragment;
import com.example.chenghao.mytb.R;

public class HttpTestActivity extends TableActivity {

    private  FragmentManager fragmentManager=null;
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
                                    //当点击GetCookBookFragment中的cooklist，回调此方法,跳转至CookInfoFragment,同时更新toolbar
                                    toolbar.setNavigationIcon(R.mipmap.back);
                                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Log.d("Qin:","setNavigationOnClickListener");
                                            getFragmentManager().popBackStack();
                                        }
                                    });

                                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, new CookInfoFragment());
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

    }

}
