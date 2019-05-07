package com.zhilian.rf_qims.mvp.sample_data.pro_size;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.adapter.FragAdapter;
import com.zhilian.rf_qims.widget.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by colin on 2018/3/23 11:46 .
 */

public class ProSizeFragment extends LazyLoadFragment {
    ProSizeDK proSizeDK;
    ProSizeDZ proSizeDZ;
    ProSizeXBM proSizeXBM;
    FragAdapter fragAdapter;
    List<Fragment> fragments;
    public ViewPager viewPager;
    @BindView(R.id.tv_page_title)
    TextView tvPageTitle;
    @BindView(R.id.tv_page_before)
    TextView tvPageBefore;
    @BindView(R.id.tv_page_curpage)
    TextView tvPageCurpage;
    @BindView(R.id.tv_page_next)
    TextView tvPageNext;
    Unbinder unbinder;
    int mcurrentpos=0;
    @Override
    protected int setContentView() {
        return R.layout.fragment_prosize;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        fragments = new ArrayList<>();
        proSizeDK = new ProSizeDK();
        proSizeDZ = new ProSizeDZ();
        proSizeXBM = new ProSizeXBM();
        fragments.add(proSizeDK);
        fragments.add(proSizeDZ);
        fragments.add(proSizeXBM);
//        fragmentManager = getFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(proSizeDK, "one");
//        fragmentTransaction.add(proSizeDZ, "two");
//        fragmentTransaction.add(proSizeXBM, "three");
        viewPager = findViewById(R.id.viewpager);
        fragAdapter = new FragAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(fragAdapter);
        viewPager.setCurrentItem(mcurrentpos, false);
        tvPageCurpage.setText((mcurrentpos+1)+"/"+fragments.size());
        tvPageTitle.setText("生产尺寸-门框(底框)");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mcurrentpos=position;
                switch (position){
                    case 0:
                        tvPageTitle.setText("生产尺寸-门框(底框)");
                        break;
                    case 1:
                        tvPageTitle.setText("生产尺寸-门扇(底座)");
                        break;
                    case 2:
                        tvPageTitle.setText("生产尺寸-悬摆门");
                        break;
                    default:
                        break;
                }
                tvPageCurpage.setText((mcurrentpos+1)+"/"+fragments.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        getFragmentManager().findFragmentByTag("one");
//        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                Toast.makeText(getActivity(), proSizeDK.med_1.getText().toString().trim() + "--" + proSizeDZ.med_2.getText().toString().trim() + "--" + proSizeXBM.med_3.getText().toString().trim(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        Map<String, String> params = new HashMap<>();
//        params.put("id", 1650 + "");
//        params.put("door_frame_height1", "1,2,3");
//        params.put("door_frame_width1", "4,5,6");
//        params.put("door_frame_diagonal", "7,8");
//        params.put("door_frame_diagonal_bottom_fulcrum", "9,10");
//        params.put("door_frame_diagonal_bottom_fulcrum_difference", "11,12");
//        params.put("door_frame_diagonal_top_fulcrum", "13,14");
//        params.put("door_frame_diagonal_top_fulcrum_difference", "15,16");
//        Log.d("prosize", HttpUtil.initUrl());
//        Log.d("prosize", HttpUtil.initSaveParams("save", "updateDetectFH", params));
//        HttpServiceManager.getInstance()
//                .getHttpService()
//                .getServerData(HttpUtil.initUrl(), HttpUtil.initSaveParams("save", "updateDetectFH", params))
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ResponseBody>() {
//                    @Override
//                    public void accept( ResponseBody responseBody) throws Exception {
//                        String json = responseBody.string();
//                        Log.d("prosize", json);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.d("prosize","getServerProjects:" + throwable.getMessage());
//                    }
//                });
//        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(2, false);
//            }
//        });
    }



    @OnClick({R.id.tv_page_before, R.id.tv_page_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_page_before:
                if (mcurrentpos==0){
                    return;
                }else {
                    mcurrentpos--;
                    viewPager.setCurrentItem(mcurrentpos,false);
                }
                break;
            case R.id.tv_page_next:
                if (mcurrentpos==fragments.size()-1){
                    return;
                }else {
                    mcurrentpos++;
                    viewPager.setCurrentItem(mcurrentpos,false);
                }
                break;
        }
    }
}
