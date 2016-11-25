package com.example.chenghao.mytb.Activitys;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.example.chenghao.mytb.Fragments.TableFragment;
import com.example.chenghao.mytb.R;
import com.example.chenghao.mytb.Utils.ProjectsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
   登陆的界面，亦是其他Test activity的父类
*/
public class TableActivity extends AppCompatActivity {

    static boolean firstBoot=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


        if(TableActivity.firstBoot){
            Toolbar myToolbar=(Toolbar)findViewById(R.id.toolbar);
            myToolbar.setTitle(getString(R.string.app_name));
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container,new TableFragment());
            fragmentTransaction.commit();
            TableActivity.firstBoot=false;
        }

        ListView projectList=(ListView)findViewById(R.id.project_list);
        SimpleAdapter adapter=new SimpleAdapter(TableActivity.this,
                getITEM(),
                R.layout.list_layout,
                new String[]{"name"},
                new int[]{R.id.project_name});
        projectList.setAdapter(adapter);

        projectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent=setIntent(i);
                if (intent!=null) {
                    startActivity(intent);
                    finish();
                }
            }
        });

        IntentFilter mIntentFilter=new IntentFilter();
        mIntentFilter.addAction("finish");
        registerReceiver(mFinishReceiver,mIntentFilter);

        Button exitButton=(Button)findViewById(R.id.exit_sys_button);
        exitButton.getBackground().setAlpha(0);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableActivity.firstBoot=true;
                Intent mIntent=new Intent();
                mIntent.setAction("finish");
                sendBroadcast(mIntent);
            }
        });
    }

   BroadcastReceiver mFinishReceiver=new BroadcastReceiver() {
       @Override
       public void onReceive(Context context, Intent intent) {
            if("finish".equals(intent.getAction())){
                finish();
            }
       }
   };
   private Intent setIntent(int i){
        ProjectsManager pm=new ProjectsManager(this);
        return pm.getIntent(i);
    }

    private List<Map<String,String>>getITEM(){
        List<Map<String,String>> list=new ArrayList<>();
        String[] itemArray=ProjectsManager.getItemArray();
        int length=itemArray.length;
        for (int i=0;i<length;i++){
            Map<String,String> map=new HashMap<>();
            map.put("name",itemArray[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        unregisterReceiver(mFinishReceiver);
        super.finish();
    }
}
