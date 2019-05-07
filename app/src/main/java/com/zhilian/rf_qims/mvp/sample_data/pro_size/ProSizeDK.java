package com.zhilian.rf_qims.mvp.sample_data.pro_size;

import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.api.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.Model;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.interfaces.EdCompareDesign;
import com.zhilian.rf_qims.interfaces.EdDesignChangeSave;
import com.zhilian.rf_qims.interfaces.EdDifferenceDesign;
import com.zhilian.rf_qims.interfaces.EdTextChange;
import com.zhilian.rf_qims.util.EdUtil;
import com.zhilian.rf_qims.util.UpdateSampleCheckStatu;
import com.zhilian.rf_qims.widget.LastInputEditText;
import com.zhilian.rf_qims.widget.LazyLoadFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.zhilian.rf_qims.util.UpdateSampleCheckStatu.UpdateSampleCheckStatu;


/**
 * Created by colin on 2018/3/23 15:29 .
 */

public class ProSizeDK extends LazyLoadFragment{

    @BindView(R.id.tv_title_1_1)
    TextView tvTitle11;
    @BindView(R.id.tv_1_1)
    TextView tv11;
    @BindView(R.id.tv_1_2)
    TextView tv12;
    @BindView(R.id.tv_1_3)
    TextView tv13;
    @BindView(R.id.tv_1_4)
    TextView tv14;
    @BindView(R.id.ly_1_1)
    RelativeLayout ly11;
    @BindView(R.id.tv_title_2_1)
    TextView tvTitle21;
    @BindView(R.id.ed_2_1)
    EditText ed21;
    @BindView(R.id.ed_2_2)
    EditText ed22;
    @BindView(R.id.ed_2_3)
    EditText ed23;
    @BindView(R.id.ed_2_4)
    LastInputEditText ed24;
    @BindView(R.id.ly_2_1)
    RelativeLayout ly21;
    @BindView(R.id.tv_title_3_1)
    TextView tvTitle31;
    @BindView(R.id.ed_3_1_remark)
    EditText ed31Remark;
    @BindView(R.id.ly_3_1)
    RelativeLayout ly31;
    @BindView(R.id.tv_title_4_1)
    TextView tvTitle41;
    @BindView(R.id.tv_4_1)
    TextView tv41;
    @BindView(R.id.tv_4_2)
    TextView tv42;
    @BindView(R.id.tv_4_3)
    TextView tv43;
    @BindView(R.id.tv_4_4)
    TextView tv44;
    @BindView(R.id.ly_4_1)
    RelativeLayout ly41;
    @BindView(R.id.tv_title_5_1)
    TextView tvTitle51;
    @BindView(R.id.ed_5_1)
    EditText ed51;
    @BindView(R.id.ed_5_2)
    EditText ed52;
    @BindView(R.id.ed_5_3)
    EditText ed53;
    @BindView(R.id.ed_5_4)
    EditText ed54;
    @BindView(R.id.ly_5_1)
    RelativeLayout ly51;
    @BindView(R.id.tv_title_6_1)
    TextView tvTitle61;
    @BindView(R.id.ed_6_1_remark)
    EditText ed61Remark;
    @BindView(R.id.ly_6_1)
    RelativeLayout ly61;
    @BindView(R.id.tv_title_7_1)
    TextView tvTitle71;
    @BindView(R.id.tv_7_1)
    TextView tv71;
    @BindView(R.id.tv_7_2)
    TextView tv72;
    @BindView(R.id.tv_7_3)
    TextView tv73;
    @BindView(R.id.ly_7_1)
    RelativeLayout ly71;
    @BindView(R.id.tv_title_8_1)
    TextView tvTitle81;
    @BindView(R.id.ed_8_1)
    EditText ed81;
    @BindView(R.id.ed_8_2)
    EditText ed82;
    @BindView(R.id.ed_8_3)
    EditText ed83;
    @BindView(R.id.ly_8_1)
    RelativeLayout ly81;
    @BindView(R.id.tv_title_9_1)
    TextView tvTitle91;
    @BindView(R.id.ed_9_1_remark)
    EditText ed91Remark;
    @BindView(R.id.ly_9_1)
    RelativeLayout ly91;
    @BindView(R.id.tv_title_10_1)
    TextView tvTitle101;
    @BindView(R.id.tv_10_1)
    TextView tv101;
    @BindView(R.id.tv_10_2)
    TextView tv102;
    @BindView(R.id.tv_10_3)
    TextView tv103;
    @BindView(R.id.tv_10_4)
    TextView tv104;
    @BindView(R.id.tv_10_5)
    TextView tv105;
    @BindView(R.id.ly_10_1)
    RelativeLayout ly101;
    @BindView(R.id.tv_title_11_1)
    TextView tvTitle111;
    @BindView(R.id.ed_11_1)
    EditText ed111;
    @BindView(R.id.ed_11_2)
    EditText ed112;
    @BindView(R.id.ed_11_3)
    EditText ed113;
    @BindView(R.id.ed_11_4)
    EditText ed114;
    @BindView(R.id.ed_11_5)
    EditText ed115;
    @BindView(R.id.ly_11_1)
    RelativeLayout ly111;
    @BindView(R.id.tv_title_12_1)
    TextView tvTitle121;
    @BindView(R.id.ed_12_1_remark)
    EditText ed121Remark;
    @BindView(R.id.ly_12_1)
    RelativeLayout ly121;
    @BindView(R.id.tv_title_13_1)
    TextView tvTitle131;
    @BindView(R.id.tv_13_1)
    TextView tv131;
    @BindView(R.id.tv_13_2)
    TextView tv132;
    @BindView(R.id.tv_13_3)
    TextView tv133;
    @BindView(R.id.tv_13_4)
    TextView tv134;
    @BindView(R.id.tv_13_5)
    TextView tv135;
    @BindView(R.id.ly_13_1)
    RelativeLayout ly131;
    @BindView(R.id.tv_title_14_1)
    TextView tvTitle141;
    @BindView(R.id.ed_14_1)
    EditText ed141;
    @BindView(R.id.ed_14_2)
    EditText ed142;
    @BindView(R.id.ed_14_3)
    EditText ed143;
    @BindView(R.id.ed_14_4)
    EditText ed144;
    @BindView(R.id.ed_14_5)
    EditText ed145;
    @BindView(R.id.ly_14_1)
    RelativeLayout ly141;
    @BindView(R.id.tv_title_15_1)
    TextView tvTitle151;
    @BindView(R.id.ed_15_1_remark)
    EditText ed151Remark;
    @BindView(R.id.ly_15_1)
    RelativeLayout ly151;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTexts4;
    private EditText[] editTexts5;
    private EditText[] editTexts6;
    private EditText[] editTexts7;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();
    private Model model;

    //    public EditText med_1;
    @Override
    protected int setContentView() {
        return R.layout.fragment_sccc_mk;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1 = new EditText[]{ed21, ed22, ed23};
        editTexts2 = new EditText[]{ed51, ed52, ed53};
        editTexts3 = new EditText[]{ed81, ed82};
        editTexts4 = new EditText[]{ed111, ed112};
        editTexts5 = new EditText[]{ed113, ed114};
        editTexts6 = new EditText[]{ed141, ed142};
        editTexts7 = new EditText[]{ed143, ed144};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed51, ed52, ed53, ed81, ed82,
            ed111, ed112, ed113, ed114, ed141, ed142, ed143, ed144};

        fillMap();
        /*Sample tentsample = (Sample) getActivity().getIntent().getSerializableExtra("sample");
        Sample sample = GreenDaoManager.getInstance().getNewSession()
                .getSampleDao().queryBuilder().where(SampleDao.Properties.Id.eq(tentsample.getId())).unique();
        SampleCheck sampleCheck = GreenDaoManager.getInstance().getNewSession()
                .getSampleCheckDao().queryBuilder().where(SampleCheckDao.Properties.Id.eq(tentsample.getId())).unique();*/
        Common common = new Common();
        SampleCheck sampleCheck = common.getSampleCheck();
        Sample sample = common.getSample();
        if (sampleCheck != null) {
            //差值监听
            ed81.addTextChangedListener(new EdDifferenceDesign(ed81,ed82,ed83));
            ed82.addTextChangedListener(new EdDifferenceDesign(ed81,ed82,ed83));

            //填充检测值
            ed21.setText(EdUtil.split(sampleCheck.getDoor_frame_height1())[0]);
            ed22.setText(EdUtil.split(sampleCheck.getDoor_frame_height1())[1]);
            ed23.setText(EdUtil.split(sampleCheck.getDoor_frame_height1())[2]);
            ed51.setText(EdUtil.split(sampleCheck.getDoor_frame_width1())[0]);
            ed52.setText(EdUtil.split(sampleCheck.getDoor_frame_width1())[1]);
            ed53.setText(EdUtil.split(sampleCheck.getDoor_frame_width1())[2]);

            ed81.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal())[0]);
            ed82.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal())[1]);

            ed111.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal_bottom_fulcrum())[0]);
            ed112.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal_bottom_fulcrum())[1]);

            ed113.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal_bottom_fulcrum_difference())[0]);
            ed114.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal_bottom_fulcrum_difference())[1]);

            ed141.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal_top_fulcrum())[0]);
            ed142.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal_top_fulcrum())[1]);

            ed143.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal_top_fulcrum_difference())[0]);
            ed144.setText(EdUtil.split(sampleCheck.getDoor_frame_diagonal_top_fulcrum_difference())[1]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_height1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_height1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_height1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_width1())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_width1())[1].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_width1())[2].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal())[0].equals(""), ed81,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal())[1].equals(""), ed82,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal_bottom_fulcrum())[0].equals(""), ed111,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal_bottom_fulcrum())[1].equals(""), ed112,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal_bottom_fulcrum_difference())[0].equals(""), ed113,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal_bottom_fulcrum_difference())[1].equals(""), ed114,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal_top_fulcrum())[0].equals(""), ed141,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal_top_fulcrum())[1].equals(""), ed142,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal_top_fulcrum_difference())[0].equals(""), ed143,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_diagonal_top_fulcrum_difference())[1].equals(""), ed144,
                getContext(), sampleCheck.getId(), map, serialNumber);
            //设计
            /*UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoor_frame_height()), ed24);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoor_frame_width()), ed54);*/

            //设计值对比监听
            ed24.addTextChangedListener(new EdCompareDesign(ed21, ed24));
            ed24.addTextChangedListener(new EdCompareDesign(ed22, ed24));
            ed24.addTextChangedListener(new EdCompareDesign(ed23, ed24));

            ed21.addTextChangedListener(new EdCompareDesign(ed21, ed24));
            ed22.addTextChangedListener(new EdCompareDesign(ed22, ed24));
            ed23.addTextChangedListener(new EdCompareDesign(ed23, ed24));

            ed54.addTextChangedListener(new EdCompareDesign(ed51, ed54));
            ed54.addTextChangedListener(new EdCompareDesign(ed52, ed54));
            ed54.addTextChangedListener(new EdCompareDesign(ed53, ed54));

            ed51.addTextChangedListener(new EdCompareDesign(ed51, ed54));
            ed52.addTextChangedListener(new EdCompareDesign(ed52, ed54));
            ed53.addTextChangedListener(new EdCompareDesign(ed53, ed54));
        }
        //填充设计值
        ed24.setText(StrKit.notBlank(sample.getDoor_frame_height() )? sample.getDoor_frame_height() +"": "");
        ed54.setText(StrKit.notBlank(sample.getDoor_frame_width() )? sample.getDoor_frame_width() +"": "");

        //设计值保存监听
        ed24.addTextChangedListener(new EdDesignChangeSave("setDoor_frame_height", sample, sample.getDoor_frame_height() + "", ed24));
        ed54.addTextChangedListener(new EdDesignChangeSave("setDoor_frame_width", sample, sample.getDoor_frame_width() + "", ed54));

        //检测值保存监听
        ed21.addTextChangedListener(new EdTextChange("setDoor_frame_height1", sampleCheck, sampleCheck.getDoor_frame_height1(), editTexts1));
        ed22.addTextChangedListener(new EdTextChange("setDoor_frame_height1", sampleCheck, sampleCheck.getDoor_frame_height1(), editTexts1));
        ed23.addTextChangedListener(new EdTextChange("setDoor_frame_height1", sampleCheck, sampleCheck.getDoor_frame_height1(), editTexts1));

        ed51.addTextChangedListener(new EdTextChange("setDoor_frame_width1", sampleCheck, sampleCheck.getDoor_frame_width1(), editTexts2));
        ed52.addTextChangedListener(new EdTextChange("setDoor_frame_width1", sampleCheck, sampleCheck.getDoor_frame_width1(), editTexts2));
        ed53.addTextChangedListener(new EdTextChange("setDoor_frame_width1", sampleCheck, sampleCheck.getDoor_frame_width1(), editTexts2));

        ed81.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal", sampleCheck, sampleCheck.getDoor_frame_diagonal(), editTexts3));
        ed82.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal", sampleCheck, sampleCheck.getDoor_frame_diagonal(), editTexts3));

        ed111.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal_bottom_fulcrum", sampleCheck, sampleCheck.getDoor_frame_diagonal_bottom_fulcrum(), editTexts4));
        ed112.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal_bottom_fulcrum", sampleCheck, sampleCheck.getDoor_frame_diagonal_bottom_fulcrum(), editTexts4));

        ed113.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal_bottom_fulcrum_difference", sampleCheck, sampleCheck.getDoor_frame_diagonal_bottom_fulcrum_difference(), editTexts5));
        ed114.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal_bottom_fulcrum_difference", sampleCheck, sampleCheck.getDoor_frame_diagonal_bottom_fulcrum_difference(), editTexts5));

        ed141.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal_top_fulcrum", sampleCheck, sampleCheck.getDoor_frame_diagonal_top_fulcrum(), editTexts6));
        ed142.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal_top_fulcrum", sampleCheck, sampleCheck.getDoor_frame_diagonal_top_fulcrum(), editTexts6));

        ed143.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal_top_fulcrum_difference", sampleCheck, sampleCheck.getDoor_frame_diagonal_top_fulcrum_difference(), editTexts7));
        ed144.addTextChangedListener(new EdTextChange("setDoor_frame_diagonal_top_fulcrum_difference", sampleCheck, sampleCheck.getDoor_frame_diagonal_top_fulcrum_difference(), editTexts7));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"door_frame_height");
        map.put(ed22.getId(),"door_frame_height");
        map.put(ed23.getId(),"door_frame_height");
        map.put(ed51.getId(),"door_frame_width");
        map.put(ed52.getId(),"door_frame_width");
        map.put(ed53.getId(),"door_frame_width");
        map.put(ed81.getId(),"door_frame_diagonal");
        map.put(ed82.getId(),"door_frame_diagonal");
        map.put(ed111.getId(),"door_frame_diagonal_bottom_fulcrum");
        map.put(ed112.getId(),"door_frame_diagonal_bottom_fulcrum");
        map.put(ed113.getId(),"door_frame_diagonal_bottom_fulcrum_difference");
        map.put(ed114.getId(),"door_frame_diagonal_bottom_fulcrum_difference");
        map.put(ed141.getId(),"door_frame_diagonal_top_fulcrum");
        map.put(ed142.getId(),"door_frame_diagonal_top_fulcrum");
        map.put(ed143.getId(),"door_frame_diagonal_top_fulcrum_difference");
        map.put(ed144.getId(),"door_frame_diagonal_top_fulcrum_difference");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed51.getId(),"0");
        serialNumber.put(ed52.getId(),"1");
        serialNumber.put(ed53.getId(),"2");
        serialNumber.put(ed81.getId(),"0");
        serialNumber.put(ed82.getId(),"1");
        serialNumber.put(ed111.getId(),"0");
        serialNumber.put(ed112.getId(),"1");
        serialNumber.put(ed113.getId(),"0");
        serialNumber.put(ed114.getId(),"1");
        serialNumber.put(ed141.getId(),"0");
        serialNumber.put(ed142.getId(),"1");
        serialNumber.put(ed143.getId(),"0");
        serialNumber.put(ed144.getId(),"1");
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }


//    @OnClick({R.id.tv_page_before, R.id.tv_page_curpage})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_page_before:
//                break;
//            case R.id.tv_page_curpage:
//                break;
//            case R.id.btn_save:
////                SampleCheck sampleCheck = null;
////                Sample sample = (Sample) getActivity().getIntent().getSerializableExtra("sample");
////                List<SampleCheck> sampleChecks = GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().queryBuilder().where(SampleCheckDao.Properties.Id.eq(sample.getId())).list();
////                if (sampleChecks != null && sampleChecks.size() > 0) {
////                    sampleCheck = sampleChecks.get(0);
////                } else {
////
////                }
////                sampleCheck.setDoor_frame_height1(EdUtil.mergeText(ed21, ed22, ed23));
////                sampleCheck.setDoor_frame_width1(EdUtil.mergeText(ed51, ed52, ed53));
////                sampleCheck.setDoor_frame_diagonal(EdUtil.mergeText(ed81, ed82));
////                sampleCheck.setDoor_frame_diagonal_bottom_fulcrum(EdUtil.mergeText(ed111, ed112));
////                sampleCheck.setDoor_frame_diagonal_bottom_fulcrum_difference(EdUtil.mergeText(ed113, ed114));
////                sampleCheck.setDoor_frame_diagonal_top_fulcrum(EdUtil.mergeText(ed141, ed142));
////                sampleCheck.setDoor_frame_diagonal_top_fulcrum_difference(EdUtil.mergeText(ed143, ed144));
////                GreenDaoManager.getInstance().getNewSession().getSampleCheckDao().update(sampleCheck);
////                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
////                btnSave.setText("已保存");
////                btnSave.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
//                break;
//        }
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("hello", "fragment is onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("hello", "fragment is onDestroy()");
    }
}