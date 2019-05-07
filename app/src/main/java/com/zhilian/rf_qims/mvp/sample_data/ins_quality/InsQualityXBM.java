package com.zhilian.rf_qims.mvp.sample_data.ins_quality;


import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.interfaces.EdTextChange;
import com.zhilian.rf_qims.util.EdUtil;
import com.zhilian.rf_qims.util.UpdateSampleCheckStatu;
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

public class InsQualityXBM extends LazyLoadFragment {


//    @BindView(R.id.tv_page_title)
//    TextView tvPageTitle;
//    @BindView(R.id.tv_page_before)
//    TextView tvPageBefore;
//    @BindView(R.id.tv_page_curpage)
//    TextView tvPageCurpage;
//    @BindView(R.id.tv_page_next)
//    TextView tvPageNext;
    @BindView(R.id.tv_title_1_1)
    TextView tvTitle11;
    @BindView(R.id.tv_1_1)
    TextView tv11;
    @BindView(R.id.tv_1_2)
    TextView tv12;
    @BindView(R.id.tv_1_3)
    TextView tv13;
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
    @BindView(R.id.ly_2_1)
    RelativeLayout ly21;
    @BindView(R.id.tv_title_3_1)
    TextView tvTitle31;
    @BindView(R.id.tv_3_1)
    TextView tv31;
    @BindView(R.id.tv_3_2)
    TextView tv32;
    @BindView(R.id.tv_3_3)
    TextView tv33;
    @BindView(R.id.ly_3_1)
    RelativeLayout ly31;
    @BindView(R.id.tv_title_4_1)
    TextView tvTitle41;
    @BindView(R.id.ed_4_1)
    EditText ed41;
    @BindView(R.id.ed_4_2)
    EditText ed42;
    @BindView(R.id.ed_4_3)
    EditText ed43;
    @BindView(R.id.ly_4_1)
    RelativeLayout ly41;
    @BindView(R.id.tv_title_5_1)
    TextView tvTitle51;
    @BindView(R.id.ed_5_1_remark)
    EditText ed51Remark;
    @BindView(R.id.ly_5_1)
    RelativeLayout ly51;
    @BindView(R.id.tv_title_6_1)
    TextView tvTitle61;
    @BindView(R.id.tv_6_1)
    TextView tv61;
    @BindView(R.id.tv_6_2)
    TextView tv62;
    @BindView(R.id.tv_6_3)
    TextView tv63;
    @BindView(R.id.ly_6_1)
    RelativeLayout ly61;
    @BindView(R.id.tv_title_7_1)
    TextView tvTitle71;
    @BindView(R.id.ed_7_1)
    EditText ed71;
    @BindView(R.id.ed_7_2)
    EditText ed72;
    @BindView(R.id.ed_7_3)
    EditText ed73;
    @BindView(R.id.ly_7_1)
    RelativeLayout ly71;
    @BindView(R.id.tv_title_8_1)
    TextView tvTitle81;
    @BindView(R.id.ed_8_1_remark)
    EditText ed81Remark;
    @BindView(R.id.ly_8_1)
    RelativeLayout ly81;
    @BindView(R.id.tv_title_9_1)
    TextView tvTitle91;
    @BindView(R.id.tv_9_1)
    TextView tv91;
    @BindView(R.id.tv_9_2)
    TextView tv92;
    @BindView(R.id.tv_9_3)
    TextView tv93;
    @BindView(R.id.ly_9_1)
    RelativeLayout ly91;
    @BindView(R.id.tv_title_10_1)
    TextView tvTitle101;
    @BindView(R.id.ed_10_1)
    EditText ed101;
    @BindView(R.id.ed_10_2)
    EditText ed102;
    @BindView(R.id.ed_10_3)
    EditText ed103;
    @BindView(R.id.ly_10_1)
    RelativeLayout ly101;
    @BindView(R.id.tv_title_11_1)
    TextView tvTitle111;
    @BindView(R.id.ed_11_1_remark)
    EditText ed111Remark;
    @BindView(R.id.ly_11_1)
    RelativeLayout ly111;
    @BindView(R.id.tv_title_12_1)
    TextView tvTitle121;
    @BindView(R.id.tv_12_1)
    TextView tv121;
    @BindView(R.id.tv_12_2)
    TextView tv122;
    @BindView(R.id.tv_12_3)
    TextView tv123;
    @BindView(R.id.ly_12_1)
    RelativeLayout ly121;
    @BindView(R.id.tv_title_13_1)
    TextView tvTitle131;
    @BindView(R.id.ed_13_1)
    EditText ed131;
    @BindView(R.id.ed_13_2)
    EditText ed132;
    @BindView(R.id.ed_13_3)
    EditText ed133;
    @BindView(R.id.ly_13_1)
    RelativeLayout ly131;
    @BindView(R.id.tv_title_14_1)
    TextView tvTitle141;
    @BindView(R.id.ed_12_1_remark)
    EditText ed121Remark;
    @BindView(R.id.ly_14_1)
    RelativeLayout ly141;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTexts4;
    private EditText[] editTexts5;
    private EditText[] editTexts6;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_azzl_xbm;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);

        editTexts1 = new EditText[]{ed21, ed22, ed23};
        editTexts2 = new EditText[]{ed41};
        editTexts3 = new EditText[]{ed42};
        editTexts4 = new EditText[]{ed71,ed72,ed73};
        editTexts5 = new EditText[]{ed101,ed102,ed103};
        editTexts6 = new EditText[]{ed131,ed132,ed133};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed41, ed42,
            ed71, ed72, ed73, ed101, ed102, ed103, ed131, ed132, ed133};

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
            //填充检测值
            editTexts1[0].setText(EdUtil.split(sampleCheck.getClose_dangling_board_starting_force_top1())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getClose_dangling_board_starting_force_top1())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getClose_dangling_board_starting_force_top1())[2]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getSwing_plate_quality())[0]);


            editTexts3[0].setText(EdUtil.split(sampleCheck.getSwing_plate_hingeseat_quality())[0]);

            editTexts4[0].setText(EdUtil.split(sampleCheck.getSwing_plate_closing_the_most_strongly_top1())[0]);
            editTexts4[1].setText(EdUtil.split(sampleCheck.getSwing_plate_closing_the_most_strongly_top1())[1]);
            editTexts4[2].setText(EdUtil.split(sampleCheck.getSwing_plate_closing_the_most_strongly_top1())[2]);

            editTexts5[0].setText(EdUtil.split(sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1())[0]);
            editTexts5[1].setText(EdUtil.split(sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1())[1]);
            editTexts5[2].setText(EdUtil.split(sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1())[2]);

            editTexts6[0].setText(EdUtil.split(sampleCheck.getDoor_frame_leaf_max_clearance1())[0]);
            editTexts6[1].setText(EdUtil.split(sampleCheck.getDoor_frame_leaf_max_clearance1())[1]);
            editTexts6[2].setText(EdUtil.split(sampleCheck.getDoor_frame_leaf_max_clearance1())[2]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getClose_dangling_board_starting_force_top1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getClose_dangling_board_starting_force_top1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getClose_dangling_board_starting_force_top1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_quality())[0].equals(""), ed41,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_hingeseat_quality())[0].equals(""), ed42,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_closing_the_most_strongly_top1())[0].equals(""), ed71,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_closing_the_most_strongly_top1())[1].equals(""), ed72,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_closing_the_most_strongly_top1())[2].equals(""), ed73,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1())[0].equals(""), ed101,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1())[1].equals(""), ed102,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1())[2].equals(""), ed103,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_leaf_max_clearance1())[0].equals(""), ed131,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_leaf_max_clearance1())[1].equals(""), ed132,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_leaf_max_clearance1())[2].equals(""), ed133,
                getContext(), sampleCheck.getId(), map, serialNumber);
        }

        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setClose_dangling_board_starting_force_top1", sampleCheck, sampleCheck.getClose_dangling_board_starting_force_top1(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setClose_dangling_board_starting_force_top1", sampleCheck, sampleCheck.getClose_dangling_board_starting_force_top1(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setClose_dangling_board_starting_force_top1", sampleCheck, sampleCheck.getClose_dangling_board_starting_force_top1(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setSwing_plate_quality", sampleCheck, sampleCheck.getSwing_plate_quality(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setSwing_plate_hingeseat_quality", sampleCheck, sampleCheck.getSwing_plate_hingeseat_quality(), editTexts3));

        editTexts4[0].addTextChangedListener(new EdTextChange("setSwing_plate_closing_the_most_strongly_top1", sampleCheck, sampleCheck.getSwing_plate_closing_the_most_strongly_top1(), editTexts4));
        editTexts4[1].addTextChangedListener(new EdTextChange("setSwing_plate_closing_the_most_strongly_top1", sampleCheck, sampleCheck.getSwing_plate_closing_the_most_strongly_top1(), editTexts4));
        editTexts4[2].addTextChangedListener(new EdTextChange("setSwing_plate_closing_the_most_strongly_top1", sampleCheck, sampleCheck.getSwing_plate_closing_the_most_strongly_top1(), editTexts4));

        editTexts5[0].addTextChangedListener(new EdTextChange("setSwing_plate_biggest_gap_between_door_leaf_top1", sampleCheck, sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1(), editTexts5));
        editTexts5[1].addTextChangedListener(new EdTextChange("setSwing_plate_biggest_gap_between_door_leaf_top1", sampleCheck, sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1(), editTexts5));
        editTexts5[2].addTextChangedListener(new EdTextChange("setSwing_plate_biggest_gap_between_door_leaf_top1", sampleCheck, sampleCheck.getSwing_plate_biggest_gap_between_door_leaf_top1(), editTexts5));

        editTexts6[0].addTextChangedListener(new EdTextChange("setDoor_frame_leaf_max_clearance1", sampleCheck, sampleCheck.getDoor_frame_leaf_max_clearance1(), editTexts6));
        editTexts6[1].addTextChangedListener(new EdTextChange("setDoor_frame_leaf_max_clearance1", sampleCheck, sampleCheck.getDoor_frame_leaf_max_clearance1(), editTexts6));
        editTexts6[2].addTextChangedListener(new EdTextChange("setDoor_frame_leaf_max_clearance1", sampleCheck, sampleCheck.getDoor_frame_leaf_max_clearance1(), editTexts6));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"close_dangling_board_starting_force_top");
        map.put(ed22.getId(),"close_dangling_board_starting_force_top");
        map.put(ed23.getId(),"close_dangling_board_starting_force_top");
        map.put(ed41.getId(),"swing_plate_quality");
        map.put(ed42.getId(),"swing_plate_hingeseat_quality");
        map.put(ed71.getId(),"swing_plate_closing_the_most_strongly_top");
        map.put(ed72.getId(),"swing_plate_closing_the_most_strongly_top");
        map.put(ed73.getId(),"swing_plate_closing_the_most_strongly_top");
        map.put(ed101.getId(),"swing_plate_biggest_gap_between_door_leaf_top");
        map.put(ed102.getId(),"swing_plate_biggest_gap_between_door_leaf_top");
        map.put(ed103.getId(),"swing_plate_biggest_gap_between_door_leaf_top");
        map.put(ed131.getId(),"door_frame_leaf_max_clearance");
        map.put(ed132.getId(),"door_frame_leaf_max_clearance");
        map.put(ed133.getId(),"door_frame_leaf_max_clearance");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed41.getId(),"0");
        serialNumber.put(ed42.getId(),"0");
        serialNumber.put(ed71.getId(),"0");
        serialNumber.put(ed72.getId(),"1");
        serialNumber.put(ed73.getId(),"2");
        serialNumber.put(ed101.getId(),"0");
        serialNumber.put(ed102.getId(),"1");
        serialNumber.put(ed103.getId(),"2");
        serialNumber.put(ed131.getId(),"0");
        serialNumber.put(ed132.getId(),"1");
        serialNumber.put(ed133.getId(),"2");
    }

//    @OnClick({R.id.tv_page_before, R.id.tv_page_next})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_page_before:
//                break;
//            case R.id.tv_page_next:
//                break;
//        }
//    }
}