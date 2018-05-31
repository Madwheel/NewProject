package com.example.app2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/22
 */

public class TestFragment extends BaseFragment {
    private final static String TAG = TestFragment.class.getSimpleName();
    private String test;
    public View mContentView;

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate:  test = " + test);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.layout_fg, null);
        Log.e(TAG, "onCreateView: test = " + test);
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated: test = " + test);
        TextView testText = (TextView) mContentView.findViewById(R.id.tv_test);
        testText.setText(test);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: test = " + test);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy:  test = " + test);
    }
}
