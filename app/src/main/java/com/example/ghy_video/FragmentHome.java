package com.example.ghy_video;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghy_video.model.Channel;
import com.example.ghy_video.base.BaseFragment;
import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;

/**
 * Created by Administrator on 2017/10/21.
 */

public class FragmentHome extends BaseFragment {
    private GridView mGridView;
    @Override
    protected void initView() {
        LoopViewPager loopViewPager = bindViewId(R.id.looperviewpager);
        CircleIndicator circleIndicator = bindViewId(R.id.indicator);
        loopViewPager.setAdapter(new HomePicAdpter(getActivity()));
        loopViewPager.setLooperPic(true);
        circleIndicator.setViewPager(loopViewPager);
        mGridView = bindViewId(R.id.gv_channel);
        mGridView.setAdapter( new ChannelAdapter());
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 6:
                        //跳直播
                        Log.e("TAG", "selectedLive");
                        break;
                    case 7:
                        //跳收藏
                        Log.e("TAG", "selectefavourite");
                        break;
                    case 8:
                        //跳历史记录
                        Log.e("TAG", "selectedhistory");
                        break;
                    default:
                        //跳转对应频道

                        Log.e("TAG", "selecteddefault");
                        DetailListActivity.LaunchDetailListActivity(getActivity(), position + 1);
                        break;

                }
            }


        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    class ChannelAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return new Channel(position+1,getActivity());
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Channel channel = (Channel) getItem(position);
                ViewHolder holder = null;
            if (convertView == null){
                convertView =LayoutInflater.from(getActivity()).inflate(R.layout.home_grid_item,null);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.tv_home_item_text);
                holder.imageView= (ImageView)convertView.findViewById(R.id.iv_home_item_img);

                convertView.setTag(holder);
            }else {
               holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(channel.getChannelName());
            int id= channel.getChannelId();
            int imgResId = -1;
            switch (id){
                case Channel.SHOW:
                    imgResId = R.drawable.ic_show;
                    break;
                case Channel.MOVIE:
                    imgResId = R.drawable.ic_movie;
                    break;
                case Channel.COMIC:
                    imgResId = R.drawable.ic_comic;
                    break;
                case Channel.DOCUMENTRY:
                    imgResId = R.drawable.ic_movie;
                    break;
                case Channel.MUSIC:
                    imgResId = R.drawable.ic_music;
                    break;
                case Channel.VARIEY:
                    imgResId = R.drawable.ic_variety;
                    break;
                case Channel.LIVE:
                    imgResId = R.drawable.ic_live;
                    break;
                case Channel.FAVORITE:
                    imgResId = R.drawable.ic_bookmark;
                    break;
                case Channel.HISTORY:
                    imgResId = R.drawable.ic_history;
                    break;
            }
            holder.imageView.setImageDrawable(getActivity().getResources().getDrawable(imgResId));
            return convertView;
        }
    }
    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
