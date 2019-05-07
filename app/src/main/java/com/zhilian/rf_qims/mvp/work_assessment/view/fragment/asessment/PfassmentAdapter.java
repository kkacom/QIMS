package com.zhilian.rf_qims.mvp.work_assessment.view.fragment.asessment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by luocong on 2017/3/31.
 */

public class PfassmentAdapter extends FragmentPagerAdapter {
    public ArrayList<Fragment> mFragments;

    public PfassmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);

        mFragments = fragments;
    }




    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
