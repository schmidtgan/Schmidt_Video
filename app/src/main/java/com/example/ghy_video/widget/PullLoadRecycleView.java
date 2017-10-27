package com.example.ghy_video.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ghy_video.R;

/**
 * Created by Administrator on 2017/10/25.
 */

public class PullLoadRecycleView extends LinearLayout {
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private boolean misRefresh =false;
    private boolean misLoadMore = false;
    private View mFootView;
    private AnimationDrawable mAnimationDrawable;
    private OnPullLoadMoreListener mOnPullLoadMoreListener;
    public PullLoadRecycleView(Context context) {
        super(context);
        initView(context);
    }

    public PullLoadRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PullLoadRecycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context){
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.pull_loadmore_layout,null);
        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swiprefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark,android.R.color.holo_blue_dark,android.R.color.holo_orange_dark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayoutOnRefresh());
        mRecycleView = (RecyclerView) view.findViewById(R.id.recycleView);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());//使用默认刷新模式
        mRecycleView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return misRefresh||misLoadMore;
            }
        });
        mRecycleView.setVerticalScrollBarEnabled(false);//隐藏滚动条
        mRecycleView.addOnScrollListener(new RecyclerViewOnScroll());
        mFootView =view.findViewById(R.id.footer_view);
        ImageView image= (ImageView) mFootView.findViewById(R.id.iv_home_item_img);
        image.setBackgroundResource(R.drawable.imooc_loading);
        mAnimationDrawable = (AnimationDrawable)image.getBackground();
        TextView text = (TextView) mFootView.findViewById(R.id.iv_load_text);
        mFootView.setVisibility(View.GONE);
        //view包含swipeRefreshLayout,RecycleView,FootView
        this.addView(view);
    }
    class RecyclerViewOnScroll extends RecyclerView.OnScrollListener{
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            int firstItem =0;
            int lastItem =0;

            RecyclerView.LayoutManager manger = recyclerView.getLayoutManager();
            int totalCount= manger.getItemCount();
            if (manger instanceof GridLayoutManager){
                   GridLayoutManager gridLayoutManager = (GridLayoutManager)manger;
                //第一完全可见
                    firstItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();

                    lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                if (firstItem ==0||firstItem == RecyclerView.NO_POSITION){
                    lastItem = gridLayoutManager.findLastVisibleItemPosition();
                }
            }
            //什么时候触发上拉加载更多
            if (mSwipeRefreshLayout.isEnabled()){
                mSwipeRefreshLayout.setEnabled(true);
            }else{
                mSwipeRefreshLayout.setEnabled(false);
            }
            //1.加载更多是false
            //2.total -1==lastItem
            //3.mSwipeRefreshLayout可以用
            //4.不是处于下拉刷新状态
            //5.偏移量dx或dy>0
            if (!misLoadMore
                    &&totalCount==lastItem
                    &&mSwipeRefreshLayout.isEnabled()
                    &&!misRefresh
                    &&(dx>0||dy>0)){
                misLoadMore = true;
                loadMoreData();
            }
        }

    }
    //外部可以设置recucleview的列数
        public void setGridLayout(int spanCount){
            GridLayoutManager manager = new GridLayoutManager(mContext,spanCount);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecycleView.setLayoutManager(manager);
        }
        public void setAdapter(RecyclerView.Adapter adapter){
            if (adapter!=null){
                mRecycleView.setAdapter(adapter);
            }
        }


    class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            if (!misRefresh){
                misRefresh = true;
                refreshData();
            }
        }
    }

    private void refreshData() {
        if (mOnPullLoadMoreListener!=null){
            mOnPullLoadMoreListener.reFresh();
        }
    }
    private void loadMoreData() {
        if (mOnPullLoadMoreListener!=null){

            mFootView.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator()
            ).setDuration(300).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    mFootView.setVisibility(View.VISIBLE);
                    mAnimationDrawable.start();
                }
            }).start();
            invalidate();
            mOnPullLoadMoreListener.loadMore();
        }
    }
    //设置刷新完毕
    public void setRefreshCompleted(){
        misRefresh = false;
        setRefreshing(false);
    }
    //设置是否刷新完毕
    private void setRefreshing(final boolean isRefreshing) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(isRefreshing);
            }
        });
    }
    public void setLoadMoreCompleted(){
        misLoadMore = false;
        setRefreshing(false);
        mFootView.animate().translationY(mFootView.getHeight()).setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(300).start();
    }
    public interface OnPullLoadMoreListener{
        void reFresh();
        void loadMore();
    }
    public void setOnPullLoadMoreListener(OnPullLoadMoreListener listener){
        mOnPullLoadMoreListener = listener;
    }
}
