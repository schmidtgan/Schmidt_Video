package com.example.ghy_video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    private List<View> mViewList;
    private ViewPager mViewPager;
    private ImageView[] mDotList;
    private int LastPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initAdapter();
        initDotList();
    }
    private void StartHomeActivity(){
        Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void initDotList() {
        LinearLayout dots = (LinearLayout)findViewById(R.id.ll_dots_layout);

        mDotList = new ImageView[mViewList.size()];
        for (int i =0;i<mViewList.size();i++){
            mDotList [i] = (ImageView)dots.getChildAt(i);
            mDotList[i].setEnabled(false);
        }
        mDotList[0].setEnabled(true);
    }

    private void initAdapter() {
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        MyPageAdapter adapter = new MyPageAdapter(this,mViewList);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }

    private void initView() {
        mViewList = new ArrayList<View>();
        LayoutInflater inflater=  LayoutInflater.from(this);
        mViewList.add(inflater.inflate(R.layout.guide_one_layout,null));
        mViewList.add(inflater.inflate(R.layout.guide_two_layout,null));
        mViewList.add(inflater.inflate(R.layout.guide_three_layout,null));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentPosition(position);
    }

    private void setCurrentPosition(int position) {
        mDotList[position].setEnabled(true);
        mDotList[LastPosition].setEnabled(false);
        LastPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPageAdapter extends PagerAdapter{
         private Context mContext;
         private List<View> mImageList;
            MyPageAdapter(Context context,List<View> list){
            super();
            this.mContext = context;
            this.mImageList = list;
        }

         @Override
         public Object instantiateItem(ViewGroup container, int position) {
             if (mImageList!= null){
                 if (mImageList.size()>0){
                     container.addView(mViewList.get(position));
                     if (position == mViewList.size() -1){
                         ImageView imageView = (ImageView)mViewList.get(position).findViewById(R.id.ic_start);
                         imageView.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 StartHomeActivity();
                                 startGuide();
                             }
                         });
                     }
                     return mViewList.get(position);
                 }

             }

            return null;
         } // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可

         @Override
         public void destroyItem(ViewGroup container, int position, Object object) {
            if (mImageList!=null){
                if (mImageList.size()>0){
                    container.removeView(mImageList.get(position));
                }
            }
         }

         @Override
         public int getCount() {
             if (mViewList!= null){
                 return mImageList.size();
             }
             return 0;
         }

         @Override
         public boolean isViewFromObject(View view, Object object) {
             return view == object;
         }
     }

    private void startGuide() {
        SharedPreferences sp = getSharedPreferences("config",MODE_PRIVATE);
       SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("misFirstIn",false);
        editor.commit();
    }
}

