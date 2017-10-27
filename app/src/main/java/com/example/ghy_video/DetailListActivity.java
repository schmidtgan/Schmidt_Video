package com.example.ghy_video;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.ghy_video.base.BaseActivity;
import com.example.ghy_video.model.Channel;
import com.example.ghy_video.model.Site;

import java.util.HashMap;

public class DetailListActivity extends BaseActivity {
    private static final String CHANNEL_ID = "channelid";
    private static int mChannId;
    private ViewPager mViewPager;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_list;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if(intent!=null){
            mChannId=intent.getIntExtra(CHANNEL_ID,0);
        }
        Channel channel=  new Channel(mChannId,this);
        String TitleName = channel.getChannelName();
        setSupportActionBar();
        setSupoortArrowActionBar(true);
        setTitle(TitleName);
        mViewPager = bindViewId(R.id.pager);
        mViewPager.setAdapter(new sitePagerAdapter(getSupportFragmentManager(),this,mChannId));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {

    }
    public static void LaunchDetailListActivity(Context context,int channelId){
        Intent intent = new Intent(context,DetailListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(CHANNEL_ID,channelId);
        context.startActivity(intent);

    }
    private class sitePagerAdapter extends FragmentPagerAdapter{
        private Context mContext;
        private int mChannelID;
        private HashMap<Integer,DetailListFragment> mPagerMap ;
        public static final int MAX_SITE =2;
        public  sitePagerAdapter(FragmentManager fm,Context context,int channelid){
            super(fm);
            mContext = context;
            mChannelID = channelid;
            mPagerMap = new HashMap<>();
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = DetailListFragment.newInstance(position + 1, mChannelID);
            return fragment;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object obj=super.instantiateItem(container, position);
            if(obj instanceof DetailListFragment){
                mPagerMap.put(position,(DetailListFragment)obj);
            }
            return obj;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            mPagerMap.remove(position);
        }

        @Override
        public int getCount() {
            return MAX_SITE;
        }
    }
}
