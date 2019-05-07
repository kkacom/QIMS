package com.zhilian.rf_qims.mvp.sample_data.pro_quality;


import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.api.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.interfaces.EdCompareDesign;
import com.zhilian.rf_qims.interfaces.EdDesignChangeSave;
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

public class ProQualityXBM extends LazyLoadFragment {

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
    EditText ed24;
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
    @BindView(R.id.tv_6_4)
    TextView tv64;
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
    @BindView(R.id.ed_7_4)
    EditText ed74;
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
    @BindView(R.id.tv_9_4)
    TextView tv94;
    @BindView(R.id.tv_9_5)
    TextView tv95;
    @BindView(R.id.tv_9_6)
    TextView tv96;
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
    @BindView(R.id.ed_10_4)
    EditText ed104;
    @BindView(R.id.ed_10_5)
    EditText ed105;
    @BindView(R.id.ed_10_6)
    EditText ed106;
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
    @BindView(R.id.ed_11_6)
    EditText ed116;
    @BindView(R.id.ly_11_1)
    RelativeLayout ly111;
    @BindView(R.id.tv_title_12_1)
    TextView tvTitle121;
    @BindView(R.id.ed_12_1)
    EditText ed121;
    @BindView(R.id.ed_12_2)
    EditText ed122;
    @BindView(R.id.ly_12_1)
    RelativeLayout ly121;
    @BindView(R.id.tv_title_13_1)
    TextView tvTitle131;
    @BindView(R.id.ed_13_1_remark)
    EditText ed131Remark;
    @BindView(R.id.ly_13_1)
    RelativeLayout ly131;
    @BindView(R.id.tv_title_14_1)
    TextView tvTitle141;
    @BindView(R.id.tv_14_1)
    TextView tv141;
    @BindView(R.id.tv_14_2)
    TextView tv142;
    @BindView(R.id.tv_14_3)
    TextView tv143;
    @BindView(R.id.tv_14_4)
    TextView tv144;
    @BindView(R.id.tv_14_5)
    TextView tv145;
    @BindView(R.id.tv_14_6)
    TextView tv146;
    @BindView(R.id.ly_14_1)
    RelativeLayout ly141;
    @BindView(R.id.tv_title_15_1)
    TextView tvTitle151;
    @BindView(R.id.ed_15_1)
    EditText ed151;
    @BindView(R.id.ed_15_2)
    EditText ed152;
    @BindView(R.id.ed_15_3)
    EditText ed153;
    @BindView(R.id.ed_15_4)
    EditText ed154;
    @BindView(R.id.ed_15_5)
    EditText ed155;
    @BindView(R.id.ed_15_6)
    EditText ed156;
    @BindView(R.id.ly_15_1)
    RelativeLayout ly151;
    @BindView(R.id.tv_title_16_1)
    TextView tvTitle161;
    @BindView(R.id.ed_16_1)
    EditText ed161;
    @BindView(R.id.ly_16_1)
    RelativeLayout ly161;
    @BindView(R.id.tv_title_17_1)
    TextView tvTitle171;
    @BindView(R.id.ed_17_1_remark)
    EditText ed171Remark;
    @BindView(R.id.ly_17_1)
    RelativeLayout ly171;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTexts4;
    private EditText[] editTexts5;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_sczl_xbm;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1 = new EditText[]{ed21, ed22, ed23};
        editTexts2 = new EditText[]{ed51, ed52, ed53, ed71, ed72, ed73};
        editTexts3 = new EditText[]{ed101, ed102, ed103, ed104, ed105, ed106};
        editTexts4 = new EditText[]{ed111, ed112, ed113, ed114, ed115, ed116};
        editTexts5 = new EditText[]{ed151, ed152, ed153, ed154, ed155, ed156};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed51, ed52,
            ed53, ed71, ed72, ed73, ed101, ed102, ed103, ed104, ed105, ed106, ed111,
            ed112, ed113, ed114, ed115, ed116, ed151, ed152, ed153, ed154, ed155, ed156};

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
            editTexts1[0].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_width1())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_width1())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_width1())[2]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[0]);
            editTexts2[1].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[1]);
            editTexts2[2].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[2]);
            editTexts2[3].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[3]);
            editTexts2[4].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[4]);
            editTexts2[5].setText(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[5]);

            editTexts3[0].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[0]);
            editTexts3[1].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[1]);
            editTexts3[2].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[2]);
            editTexts3[3].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[3]);
            editTexts3[4].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[4]);
            editTexts3[5].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[5]);

            editTexts4[0].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[0]);
            editTexts4[1].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[1]);
            editTexts4[2].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[2]);
            editTexts4[3].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[3]);
            editTexts4[4].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[4]);
            editTexts4[5].setText(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[5]);

            editTexts5[0].setText(EdUtil.split(sampleCheck.getInside_plate_thickness1())[0]);
            editTexts5[1].setText(EdUtil.split(sampleCheck.getInside_plate_thickness1())[1]);
            editTexts5[2].setText(EdUtil.split(sampleCheck.getInside_plate_thickness1())[2]);
            editTexts5[3].setText(EdUtil.split(sampleCheck.getInside_plate_thickness1())[3]);
            editTexts5[4].setText(EdUtil.split(sampleCheck.getInside_plate_thickness1())[4]);
            editTexts5[5].setText(EdUtil.split(sampleCheck.getInside_plate_thickness1())[5]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_width1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_width1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_width1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[1].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[2].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[3].equals(""), ed71,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[4].equals(""), ed72,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_base_vent_height1())[5].equals(""), ed73,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[0].equals(""), ed101,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[1].equals(""), ed102,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[2].equals(""), ed103,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[3].equals(""), ed104,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[4].equals(""), ed105,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_top1())[5].equals(""), ed106,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[0].equals(""), ed111,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[1].equals(""), ed112,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[2].equals(""), ed113,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[3].equals(""), ed114,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[4].equals(""), ed115,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_thickness_bottom1())[5].equals(""), ed116,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getInside_plate_thickness1())[0].equals(""), ed151,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getInside_plate_thickness1())[1].equals(""), ed152,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getInside_plate_thickness1())[2].equals(""), ed153,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getInside_plate_thickness1())[3].equals(""), ed154,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getInside_plate_thickness1())[4].equals(""), ed155,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getInside_plate_thickness1())[5].equals(""), ed156,
                getContext(), sampleCheck.getId(), map, serialNumber);
            //设计
            /*UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoorLeafBaseVentWidth()) , ed24);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoorLeafBaseVentHeight()), ed74);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getHangingPlateThicknessTop()), ed121);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getHangingPlateThicknessBottom()), ed122);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getSteelPlantThickness()), ed161);*/
        }
        //设计值对比监听
        ed24.addTextChangedListener(new EdCompareDesign(editTexts1[0], ed24));
        ed24.addTextChangedListener(new EdCompareDesign(editTexts1[1], ed24));
        ed24.addTextChangedListener(new EdCompareDesign(editTexts1[2], ed24));
        editTexts1[0].addTextChangedListener(new EdCompareDesign(editTexts1[0], ed24));
        editTexts1[1].addTextChangedListener(new EdCompareDesign(editTexts1[1], ed24));
        editTexts1[2].addTextChangedListener(new EdCompareDesign(editTexts1[2], ed24));

        ed74.addTextChangedListener(new EdCompareDesign(editTexts2[0], ed74));
        ed74.addTextChangedListener(new EdCompareDesign(editTexts2[1], ed74));
        ed74.addTextChangedListener(new EdCompareDesign(editTexts2[2], ed74));
        ed74.addTextChangedListener(new EdCompareDesign(editTexts2[3], ed74));
        ed74.addTextChangedListener(new EdCompareDesign(editTexts2[4], ed74));
        ed74.addTextChangedListener(new EdCompareDesign(editTexts2[5], ed74));
        editTexts2[0].addTextChangedListener(new EdCompareDesign(editTexts2[0], ed74));
        editTexts2[1].addTextChangedListener(new EdCompareDesign(editTexts2[1], ed74));
        editTexts2[2].addTextChangedListener(new EdCompareDesign(editTexts2[2], ed74));
        editTexts2[3].addTextChangedListener(new EdCompareDesign(editTexts2[3], ed74));
        editTexts2[4].addTextChangedListener(new EdCompareDesign(editTexts2[4], ed74));
        editTexts2[5].addTextChangedListener(new EdCompareDesign(editTexts2[5], ed74));

        ed121.addTextChangedListener(new EdCompareDesign(editTexts3[0], ed121));
        ed121.addTextChangedListener(new EdCompareDesign(editTexts3[1], ed121));
        ed121.addTextChangedListener(new EdCompareDesign(editTexts3[2], ed121));
        ed121.addTextChangedListener(new EdCompareDesign(editTexts3[3], ed121));
        ed121.addTextChangedListener(new EdCompareDesign(editTexts3[4], ed121));
        ed121.addTextChangedListener(new EdCompareDesign(editTexts3[5], ed121));
        editTexts3[0].addTextChangedListener(new EdCompareDesign(editTexts3[0], ed121));
        editTexts3[1].addTextChangedListener(new EdCompareDesign(editTexts3[1], ed121));
        editTexts3[2].addTextChangedListener(new EdCompareDesign(editTexts3[2], ed121));
        editTexts3[3].addTextChangedListener(new EdCompareDesign(editTexts3[3], ed121));
        editTexts3[4].addTextChangedListener(new EdCompareDesign(editTexts3[4], ed121));
        editTexts3[5].addTextChangedListener(new EdCompareDesign(editTexts3[5], ed121));

        ed122.addTextChangedListener(new EdCompareDesign(editTexts4[0], ed122));
        ed122.addTextChangedListener(new EdCompareDesign(editTexts4[1], ed122));
        ed122.addTextChangedListener(new EdCompareDesign(editTexts4[2], ed122));
        ed122.addTextChangedListener(new EdCompareDesign(editTexts4[3], ed122));
        ed122.addTextChangedListener(new EdCompareDesign(editTexts4[4], ed122));
        ed122.addTextChangedListener(new EdCompareDesign(editTexts4[5], ed122));
        editTexts4[0].addTextChangedListener(new EdCompareDesign(editTexts4[0], ed122));
        editTexts4[1].addTextChangedListener(new EdCompareDesign(editTexts4[1], ed122));
        editTexts4[2].addTextChangedListener(new EdCompareDesign(editTexts4[2], ed122));
        editTexts4[3].addTextChangedListener(new EdCompareDesign(editTexts4[3], ed122));
        editTexts4[4].addTextChangedListener(new EdCompareDesign(editTexts4[4], ed122));
        editTexts4[5].addTextChangedListener(new EdCompareDesign(editTexts4[5], ed122));

        ed161.addTextChangedListener(new EdCompareDesign(editTexts5[0], ed161));
        ed161.addTextChangedListener(new EdCompareDesign(editTexts5[1], ed161));
        ed161.addTextChangedListener(new EdCompareDesign(editTexts5[2], ed161));
        ed161.addTextChangedListener(new EdCompareDesign(editTexts5[3], ed161));
        ed161.addTextChangedListener(new EdCompareDesign(editTexts5[4], ed161));
        ed161.addTextChangedListener(new EdCompareDesign(editTexts5[5], ed161));
        editTexts5[0].addTextChangedListener(new EdCompareDesign(editTexts5[0], ed161));
        editTexts5[1].addTextChangedListener(new EdCompareDesign(editTexts5[1], ed161));
        editTexts5[2].addTextChangedListener(new EdCompareDesign(editTexts5[2], ed161));
        editTexts5[3].addTextChangedListener(new EdCompareDesign(editTexts5[3], ed161));
        editTexts5[4].addTextChangedListener(new EdCompareDesign(editTexts5[4], ed161));
        editTexts5[5].addTextChangedListener(new EdCompareDesign(editTexts5[5], ed161));
        //填充设计值
        ed24.setText(StrKit.notBlank(sample.getDoorLeafBaseVentWidth()) ? sample.getDoorLeafBaseVentWidth() + "" : "");
        ed74.setText(StrKit.notBlank(sample.getDoorLeafBaseVentHeight()) ? sample.getDoorLeafBaseVentHeight() + "" : "");
        ed121.setText(StrKit.notBlank(sample.getHangingPlateThicknessTop()) ? sample.getHangingPlateThicknessTop() + "" : "");
        ed122.setText(StrKit.notBlank(sample.getHangingPlateThicknessBottom()) ? sample.getHangingPlateThicknessBottom() + "" : "");
        ed161.setText(StrKit.notBlank(sample.getSteelPlantThickness()) ? sample.getSteelPlantThickness() + "" : "");
        //设计值保存监听
        ed24.addTextChangedListener(new EdDesignChangeSave("setDoorLeafBaseVentWidth", sample, sample.getDoorLeafBaseVentWidth() + "", ed24));
        ed74.addTextChangedListener(new EdDesignChangeSave("setDoorLeafBaseVentHeight", sample, sample.getDoorLeafBaseVentHeight() + "", ed74));
        ed121.addTextChangedListener(new EdDesignChangeSave("setHangingPlateThicknessTop", sample, sample.getHangingPlateThicknessTop() + "", ed121));
        ed122.addTextChangedListener(new EdDesignChangeSave("setHangingPlateThicknessBottom", sample, sample.getHangingPlateThicknessBottom() + "", ed122));
        ed161.addTextChangedListener(new EdDesignChangeSave("setSteelPlantThickness", sample, sample.getSteelPlantThickness() + "", ed161));


        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_width1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_width1(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_width1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_width1(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_width1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_width1(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_height1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_height1(), editTexts2));
        editTexts2[1].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_height1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_height1(), editTexts2));
        editTexts2[2].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_height1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_height1(), editTexts2));
        editTexts2[3].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_height1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_height1(), editTexts2));
        editTexts2[4].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_height1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_height1(), editTexts2));
        editTexts2[5].addTextChangedListener(new EdTextChange("setDoor_leaf_base_vent_height1", sampleCheck, sampleCheck.getDoor_leaf_base_vent_height1(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_top1", sampleCheck, sampleCheck.getHanging_plate_thickness_top1(), editTexts3));
        editTexts3[1].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_top1", sampleCheck, sampleCheck.getHanging_plate_thickness_top1(), editTexts3));
        editTexts3[2].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_top1", sampleCheck, sampleCheck.getHanging_plate_thickness_top1(), editTexts3));
        editTexts3[3].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_top1", sampleCheck, sampleCheck.getHanging_plate_thickness_top1(), editTexts3));
        editTexts3[4].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_top1", sampleCheck, sampleCheck.getHanging_plate_thickness_top1(), editTexts3));
        editTexts3[5].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_top1", sampleCheck, sampleCheck.getHanging_plate_thickness_top1(), editTexts3));

        editTexts4[0].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_bottom1", sampleCheck, sampleCheck.getHanging_plate_thickness_bottom1(), editTexts4));
        editTexts4[1].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_bottom1", sampleCheck, sampleCheck.getHanging_plate_thickness_bottom1(), editTexts4));
        editTexts4[2].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_bottom1", sampleCheck, sampleCheck.getHanging_plate_thickness_bottom1(), editTexts4));
        editTexts4[3].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_bottom1", sampleCheck, sampleCheck.getHanging_plate_thickness_bottom1(), editTexts4));
        editTexts4[4].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_bottom1", sampleCheck, sampleCheck.getHanging_plate_thickness_bottom1(), editTexts4));
        editTexts4[5].addTextChangedListener(new EdTextChange("setHanging_plate_thickness_bottom1", sampleCheck, sampleCheck.getHanging_plate_thickness_bottom1(), editTexts4));

        editTexts5[0].addTextChangedListener(new EdTextChange("setInside_plate_thickness1", sampleCheck, sampleCheck.getInside_plate_thickness1(), editTexts5));
        editTexts5[1].addTextChangedListener(new EdTextChange("setInside_plate_thickness1", sampleCheck, sampleCheck.getInside_plate_thickness1(), editTexts5));
        editTexts5[2].addTextChangedListener(new EdTextChange("setInside_plate_thickness1", sampleCheck, sampleCheck.getInside_plate_thickness1(), editTexts5));
        editTexts5[3].addTextChangedListener(new EdTextChange("setInside_plate_thickness1", sampleCheck, sampleCheck.getInside_plate_thickness1(), editTexts5));
        editTexts5[4].addTextChangedListener(new EdTextChange("setInside_plate_thickness1", sampleCheck, sampleCheck.getInside_plate_thickness1(), editTexts5));
        editTexts5[5].addTextChangedListener(new EdTextChange("setInside_plate_thickness1", sampleCheck, sampleCheck.getInside_plate_thickness1(), editTexts5));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"door_leaf_base_vent_width");
        map.put(ed22.getId(),"door_leaf_base_vent_width");
        map.put(ed23.getId(),"door_leaf_base_vent_width");
        map.put(ed51.getId(),"door_leaf_base_vent_height");
        map.put(ed52.getId(),"door_leaf_base_vent_height");
        map.put(ed53.getId(),"door_leaf_base_vent_height");
        map.put(ed71.getId(),"door_leaf_base_vent_height");
        map.put(ed72.getId(),"door_leaf_base_vent_height");
        map.put(ed73.getId(),"door_leaf_base_vent_height");
        map.put(ed101.getId(),"hanging_plate_thickness_top");
        map.put(ed102.getId(),"hanging_plate_thickness_top");
        map.put(ed103.getId(),"hanging_plate_thickness_top");
        map.put(ed104.getId(),"hanging_plate_thickness_top");
        map.put(ed105.getId(),"hanging_plate_thickness_top");
        map.put(ed106.getId(),"hanging_plate_thickness_top");
        map.put(ed111.getId(),"hanging_plate_thickness_bottom");
        map.put(ed112.getId(),"hanging_plate_thickness_bottom");
        map.put(ed113.getId(),"hanging_plate_thickness_bottom");
        map.put(ed114.getId(),"hanging_plate_thickness_bottom");
        map.put(ed115.getId(),"hanging_plate_thickness_bottom");
        map.put(ed116.getId(),"hanging_plate_thickness_bottom");
        map.put(ed151.getId(),"inside_plate_thickness");
        map.put(ed152.getId(),"inside_plate_thickness");
        map.put(ed153.getId(),"inside_plate_thickness");
        map.put(ed154.getId(),"inside_plate_thickness");
        map.put(ed155.getId(),"inside_plate_thickness");
        map.put(ed156.getId(),"inside_plate_thickness");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed51.getId(),"0");
        serialNumber.put(ed52.getId(),"1");
        serialNumber.put(ed53.getId(),"2");
        serialNumber.put(ed71.getId(),"3");
        serialNumber.put(ed72.getId(),"4");
        serialNumber.put(ed73.getId(),"5");
        serialNumber.put(ed101.getId(),"0");
        serialNumber.put(ed102.getId(),"1");
        serialNumber.put(ed103.getId(),"2");
        serialNumber.put(ed104.getId(),"3");
        serialNumber.put(ed105.getId(),"4");
        serialNumber.put(ed106.getId(),"5");
        serialNumber.put(ed111.getId(),"0");
        serialNumber.put(ed112.getId(),"1");
        serialNumber.put(ed113.getId(),"2");
        serialNumber.put(ed114.getId(),"3");
        serialNumber.put(ed115.getId(),"4");
        serialNumber.put(ed116.getId(),"5");
        serialNumber.put(ed151.getId(),"0");
        serialNumber.put(ed152.getId(),"1");
        serialNumber.put(ed153.getId(),"2");
        serialNumber.put(ed154.getId(),"3");
        serialNumber.put(ed155.getId(),"4");
        serialNumber.put(ed156.getId(),"5");
    }
}
