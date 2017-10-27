package com.example.ghy_video;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.ghy_video.base.BaseActivity;

public class HomeActivity extends BaseActivity {
  /*  private FragmentManager mFragmentManager ;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MenuItem mPreItem;
    private BaseFragment mResultFragment;
    private Fragment mCurrentFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.drawable.ic_drawer_home);
        setTitle("首页");
        mDrawerLayout = bindViewId(R.id.drawer_layout);
        mNavigationView = bindViewId(R.id.navigation_view);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mPreItem = mNavigationView.getMenu().getItem(0);
        mPreItem.setCheckable(true);
        initFragment();
        handleNavigationView();
    }

    private void initFragment() {
        mFragmentManager =getSupportFragmentManager();
        mCurrentFragment = FragmentManagerWrapper.getInstance().createFragment(FragmentHome.class);
        mFragmentManager.beginTransaction().add(R.id.fl_main_content,mCurrentFragment).commit();
    }

    private void handleNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (mPreItem != null){
                    mPreItem.setCheckable(false);
                }
                switch (item.getItemId()){
                    case R.id.navigation_item_video:
                        switchFragment(FragmentHome.class);
                        mToolbar.setTitle(R.string.home_title);
                        break;
                    case R.id.navigation_item_blog:
                        switchFragment(FragmentBlog.class);
                        mToolbar.setTitle(R.string.blog_title);
                        break;
                    case R.id.navigation_item_about:
                        switchFragment(FragmentAbout.class);
                        mToolbar.setTitle(R.string.about_title);
                        break;
                    default:
                        break;

                }
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mPreItem = item;
                return false;
            }
        });
    }

    private void switchFragment(Class<?> clazz) {
        Fragment fragment = FragmentManagerWrapper.getInstance().createFragment(clazz);
        if (fragment.isAdded()){
            //事务回滚操作
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(fragment);//隐藏当前Fragment,显示被添加的Fragment

        }else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commitAllowingStateLoss();//当前全部提交状态
        }
        mCurrentFragment = fragment;
    }

    @Override
    protected void initData() {

    }*/
  private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MenuItem mPreItem;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        setSupportActionBar();
        setActionBarIcon(R.drawable.ic_drawer_home);
        setTitle("首页");

        mDrawerLayout = bindViewId(R.id.drawer_layout);
        mNavigationView = bindViewId(R.id.navigation_view);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,R.string.drawer_open,R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        //TODO
        mPreItem = mNavigationView.getMenu().getItem(0);
        mPreItem.setChecked(true);
        initFragment();
        handleNatigationView();
    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = FragmentManagerWrapper.getInstance().createFragment(FragmentHome.class);
        mFragmentManager.beginTransaction().add(R.id.fl_main_content, mCurrentFragment).commit();
    }

    private void handleNatigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (mPreItem != null) {
                    mPreItem.setChecked(false);
                }
                switch (item.getItemId()) {
                    case R.id.navigation_item_video:
                        switchFragment(FragmentHome.class);
                        mToolbar.setTitle(R.string.home_title);
                        break;
                    case R.id.navigation_item_blog:
                        switchFragment(FragmentBlog.class);
                        mToolbar.setTitle(R.string.blog_title);
                        break;
                    case R.id.navigation_item_about:
                        switchFragment(FragmentAbout.class);
                        mToolbar.setTitle(R.string.about_title);
                        break;
                }
                mPreItem = item;
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                item.setChecked(true);
                return false;
            }
        });
    }

    private void switchFragment(Class<?> clazz) {
        Fragment fragment = FragmentManagerWrapper.getInstance().createFragment(clazz);
        if (fragment.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(fragment).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.fl_main_content, fragment).commitAllowingStateLoss();
        }
        mCurrentFragment = fragment;
    }

    @Override
    protected void initData() {
        //TODO
    }
}
