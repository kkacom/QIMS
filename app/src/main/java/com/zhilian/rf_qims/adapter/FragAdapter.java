package com.zhilian.rf_qims.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class FragAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
    private FragmentManager manager;

    public FragAdapter(FragmentManager fm) {
        super(fm);
    }  
      
    public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        manager=fm;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
  
    @Override  
    public int getCount() {  
        return fragments.size();  
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Fragment fragment = fragments.get(position);
//        //判断当前的fragment是否已经被添加进入Fragmentanager管理器中
//        if (!fragment.isAdded()) {
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(fragment, fragment.getClass().getSimpleName());
//            //不保存系统参数，自己控制加载的参数
//            transaction.commitAllowingStateLoss();
//            //手动调用,立刻加载Fragment片段
//            manager.executePendingTransactions();
//        }
//        if (fragment.getView().getParent() == null) {
//            //添加布局
//            container.addView(fragment.getView());
//        }
//        return fragment.getView();
//    }
//
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        //移除布局
////        container.removeView(fragments.get(position).getView());
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Fragment fragment = (Fragment) super.instantiateItem(container, position);
//        manager.beginTransaction().show(fragment).commit();
//        return fragment;
//    }
//
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        Fragment fragment = fragments.get(position);
//        manager.beginTransaction().hide(fragment).commit();
    }
}  