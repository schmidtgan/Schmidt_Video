package com.example.ghy_video;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.ghy_video.base.BaseFragment;

/**
 * Created by Administrator on 2017/10/21.
 */

public class FragmentAbout extends BaseFragment {
    @Override
    protected void initView() {
        TextView textView = bindViewId(R.id.tv_app_des);
        textView.setAutoLinkMask(Linkify.ALL);//表示文字中有链接可点
        textView.setMovementMethod(LinkMovementMethod.getInstance());//表示文字可以滚动
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}
