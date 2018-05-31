package com.example.app2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends FragmentActivity implements View.OnClickListener {

    List<Fragment> mFragmentList;
    ViewPager mViewPager;
    public BaseFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        findViewById(R.id.btn_change).setOnClickListener(this);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(getFg("AAA"));
        mFragmentList.add(getFg("BBB"));
        mFragmentList.add(getFg("CCC"));
        mFragmentList.add(getFg("DDD"));
        mAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mAdapter);
    }

    private TestFragment getFg(String a) {
        TestFragment fragment = new TestFragment();
        fragment.setTest(a);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        TestFragment eee = getFg("EEE");
        //新增
        mAdapter.addFragment(eee);
        //插入
        mAdapter.insertFragment(1, eee);
        //删除
        mAdapter.removeFragment(1);
        //删除
        mAdapter.removeFragment((BaseFragment) mFragmentList.get(1));
        //替换
        mAdapter.replaceFragment(1, eee);
        //替换
        mAdapter.replaceFragment((BaseFragment) mFragmentList.get(0), eee);
    }
}
