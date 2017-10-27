package com.example.ghy_video.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ghy_video.R;

/**
 * Created by Administrator on 2017/10/20.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected  <T extends View> T bindViewId(int resId){
        return (T)findViewById(resId);
    }
    protected void setSupportActionBar(){
        mToolbar = bindViewId(R.id.toolbar);
        if (mToolbar!=null){
            setSupportActionBar(mToolbar);
        }
    }
    protected void setActionBarIcon(int resId){
        if (mToolbar!=null){
            mToolbar.setNavigationIcon(resId);
        }
    }
    protected void setSupoortArrowActionBar(boolean isSupport){

            getSupportActionBar().setDisplayHomeAsUpEnabled(isSupport);


    }
}
