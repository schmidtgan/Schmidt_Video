package com.example.ghy_video;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ghy_video.base.BaseFragment;
import com.example.ghy_video.model.Site;
import com.example.ghy_video.widget.PullLoadRecycleView;

/**
 * Created by Administrator on 2017/10/24.
 */

public class DetailListFragment extends BaseFragment {
    private  static int mSiteId;
    private  static int mChannelId;
    private static final String CHANNEL_ID = "channelid";
    private static final String SITE_ID = "siteid";
    private PullLoadRecycleView mRecycleView;
    private TextView mEmptyView;
    private int mColumns;//在这个页面中是几列
    private DetailListAdapter mAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());//表示在主线程
    private static final int REFRESH_DURATION =1500;
    private static final int LOADMORE_DURATION =3000;
  public DetailListFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadMoreData();
        mAdapter = new DetailListAdapter();
        if (mSiteId == Site.LETV){//乐视下相关频道两列
            mColumns =2;
            mAdapter.setColumns(mColumns);
        }
    }

    public static DetailListFragment newInstance(int siteId, int channedId) {

        Bundle args = new Bundle();

        DetailListFragment fragment = new DetailListFragment();
         mSiteId = siteId;
         mChannelId = channedId;
         args.putInt(CHANNEL_ID,mSiteId);
         args.putInt(SITE_ID,mChannelId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
       mEmptyView =  bindViewId(R.id.tv_empty);
        mEmptyView.setText(getActivity().getResources().getString(R.string.load_more_text));
        mRecycleView = bindViewId(R.id.pullloadrecycleview);
        mRecycleView.setGridLayout(3);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setOnPullLoadMoreListener(new PullLoadMoreListener());

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detaillist;
    }

    class DetailListAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
        public  void setColumns (int columns){

        }
    }
    class PullLoadMoreListener implements PullLoadRecycleView.OnPullLoadMoreListener{

        @Override
        public void reFresh() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reFreshData();
                    mRecycleView.setRefreshCompleted();
                }
            },REFRESH_DURATION);
        }

        @Override
        public void loadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMoreData();
                mRecycleView.setLoadMoreCompleted();
            }
        },LOADMORE_DURATION);
        }
    }

    private void loadMoreData() {
        //请求接口,加载更多数据
    }

    private void reFreshData() {
        //请求接口,加载数据
    }
}
