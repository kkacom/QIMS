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

public class InsQualitySYXN extends LazyLoadFragment {


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
    @BindView(R.id.ed_9_1)
    EditText ed91;
    @BindView(R.id.ed_9_2)
    EditText ed92;
    @BindView(R.id.ed_9_3)
    EditText ed93;
    @BindView(R.id.ly_9_1)
    RelativeLayout ly91;
    @BindView(R.id.tv_title_10_1)
    TextView tvTitle101;
    @BindView(R.id.ed_10_1)
    EditText ed101;
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
    @BindView(R.id.tv_12_4)
    TextView tv124;
    @BindView(R.id.tv_12_5)
    TextView tv125;
    @BindView(R.id.tv_12_6)
    TextView tv126;
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
    @BindView(R.id.ed_13_4)
    EditText ed134;
    @BindView(R.id.ed_13_5)
    EditText ed135;
    @BindView(R.id.ed_13_6)
    EditText ed136;
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
    @BindView(R.id.tv_16_1)
    TextView tv161;
    @BindView(R.id.tv_16_2)
    TextView tv162;
    @BindView(R.id.tv_16_3)
    TextView tv163;
    @BindView(R.id.tv_16_4)
    TextView tv164;
    @BindView(R.id.tv_16_5)
    TextView tv165;
    @BindView(R.id.tv_16_6)
    TextView tv166;
    @BindView(R.id.ly_16_1)
    RelativeLayout ly161;
    @BindView(R.id.tv_title_17_1)
    TextView tvTitle171;
    @BindView(R.id.ed_17_1)
    EditText ed171;
    @BindView(R.id.ed_17_2)
    EditText ed172;
    @BindView(R.id.ed_17_3)
    EditText ed173;
    @BindView(R.id.ed_17_4)
    EditText ed174;
    @BindView(R.id.ed_17_5)
    EditText ed175;
    @BindView(R.id.ed_17_6)
    EditText ed176;
    @BindView(R.id.ly_17_1)
    RelativeLayout ly171;
    @BindView(R.id.tv_title_18_1)
    TextView tvTitle181;
    @BindView(R.id.ed_18_1_remark)
    EditText ed181Remark;
    @BindView(R.id.ly_18_1)
    RelativeLayout ly181;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTexts4;
    private EditText[] editTexts5;
    private EditText[] editTexts6;
    private EditText[] editTexts7;
    private EditText[] editTexts8;
    private EditText[] editTexts9;
    private EditText[] editTexts10;
    private EditText[] editTexts11;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_azzl_syxn;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1 = new EditText[]{ed21, ed22, ed23};
        editTexts2 = new EditText[]{ed51,ed52,ed53};
        editTexts3 = new EditText[]{ed81,ed82,ed83};
        editTexts4 = new EditText[]{ed91,ed92,ed93};
        editTexts5 = new EditText[]{ed101};
        editTexts6 = new EditText[]{ed131,ed132,ed133};
        editTexts7 = new EditText[]{ed134,ed135,ed136};
        editTexts8 = new EditText[]{ed151,ed152,ed153};
        editTexts9 = new EditText[]{ed154,ed155,ed156};
        editTexts10 = new EditText[]{ed171,ed172,ed173};
        editTexts11 = new EditText[]{ed174,ed175,ed176};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed51, ed52,
            ed53, ed81, ed82, ed83, ed91, ed92, ed93, ed101, ed131, ed132, ed133,
            ed134, ed135, ed136, ed151, ed152, ed153, ed154, ed155, ed156, ed171, ed172, ed173, ed174, ed175, ed176};

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
            editTexts1[0].setText(EdUtil.split(sampleCheck.getDoor_open_force1())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getDoor_open_force1())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getDoor_open_force1())[2]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getDoor_close_force1())[0]);
            editTexts2[1].setText(EdUtil.split(sampleCheck.getDoor_close_force1())[1]);
            editTexts2[2].setText(EdUtil.split(sampleCheck.getDoor_close_force1())[2]);

            editTexts3[0].setText(EdUtil.split(sampleCheck.getLock_control_force1())[0]);
            editTexts3[1].setText(EdUtil.split(sampleCheck.getLock_control_force1())[1]);
            editTexts3[2].setText(EdUtil.split(sampleCheck.getLock_control_force1())[2]);

            editTexts4[0].setText(EdUtil.split(sampleCheck.getLock_control_forceNM1())[0]);
            editTexts4[1].setText(EdUtil.split(sampleCheck.getLock_control_forceNM1())[1]);
            editTexts4[2].setText(EdUtil.split(sampleCheck.getLock_control_forceNM1())[2]);

            editTexts5[0].setText(EdUtil.split(sampleCheck.getHandleLength())[0]);

            editTexts6[0].setText(EdUtil.split(sampleCheck.getPerformance_of_door_fans_left())[0]);
            editTexts6[1].setText(EdUtil.split(sampleCheck.getPerformance_of_door_fans_left())[1]);
            editTexts6[2].setText(EdUtil.split(sampleCheck.getPerformance_of_door_fans_left())[2]);

            editTexts7[0].setText(EdUtil.split(sampleCheck.getPerformance_of_door_fans_right())[0]);
            editTexts7[1].setText(EdUtil.split(sampleCheck.getPerformance_of_door_fans_right())[1]);
            editTexts7[2].setText(EdUtil.split(sampleCheck.getPerformance_of_door_fans_right())[2]);

            editTexts8[0].setText(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_left())[0]);
            editTexts8[1].setText(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_left())[1]);
            editTexts8[2].setText(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_left())[2]);

            editTexts9[0].setText(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_right())[0]);
            editTexts9[1].setText(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_right())[1]);
            editTexts9[2].setText(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_right())[2]);

            editTexts10[0].setText(EdUtil.split(sampleCheck.getDoor_leaf_noise_left())[0]);
            editTexts10[1].setText(EdUtil.split(sampleCheck.getDoor_leaf_noise_left())[1]);
            editTexts10[2].setText(EdUtil.split(sampleCheck.getDoor_leaf_noise_left())[2]);

            editTexts11[0].setText(EdUtil.split(sampleCheck.getDoor_leaf_noise_right ())[0]);
            editTexts11[1].setText(EdUtil.split(sampleCheck.getDoor_leaf_noise_right ())[1]);
            editTexts11[2].setText(EdUtil.split(sampleCheck.getDoor_leaf_noise_right ())[2]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_open_force1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_open_force1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_open_force1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_close_force1())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_close_force1())[1].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_close_force1())[2].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLock_control_force1())[0].equals(""), ed81,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLock_control_force1())[1].equals(""), ed82,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLock_control_force1())[2].equals(""), ed83,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLock_control_forceNM1())[0].equals(""), ed91,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLock_control_forceNM1())[1].equals(""), ed92,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLock_control_forceNM1())[2].equals(""), ed93,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHandleLength())[0].equals(""), ed101,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getPerformance_of_door_fans_left())[0].equals(""), ed131,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getPerformance_of_door_fans_left())[1].equals(""), ed132,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getPerformance_of_door_fans_left())[2].equals(""), ed133,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getPerformance_of_door_fans_right())[0].equals(""), ed134,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getPerformance_of_door_fans_right())[1].equals(""), ed135,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getPerformance_of_door_fans_right())[2].equals(""), ed136,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_left())[0].equals(""), ed151,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_left())[1].equals(""), ed152,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_left())[2].equals(""), ed153,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_right())[0].equals(""), ed154,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_right())[1].equals(""), ed155,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_fan_ambient_noise_right())[2].equals(""), ed156,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_noise_left())[0].equals(""), ed171,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_noise_left())[1].equals(""), ed172,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_noise_left())[2].equals(""), ed173,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_noise_right ())[0].equals(""), ed174,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_noise_right ())[1].equals(""), ed175,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_noise_right ())[2].equals(""), ed176,
                getContext(), sampleCheck.getId(), map, serialNumber);
        }

        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setDoor_open_force1", sampleCheck, sampleCheck.getDoor_open_force1(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setDoor_open_force1", sampleCheck, sampleCheck.getDoor_open_force1(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setDoor_open_force1", sampleCheck, sampleCheck.getDoor_open_force1(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setDoor_close_force1", sampleCheck, sampleCheck.getDoor_close_force1(), editTexts2));
        editTexts2[1].addTextChangedListener(new EdTextChange("setDoor_close_force1", sampleCheck, sampleCheck.getDoor_close_force1(), editTexts2));
        editTexts2[2].addTextChangedListener(new EdTextChange("setDoor_close_force1", sampleCheck, sampleCheck.getDoor_close_force1(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setLock_control_force1", sampleCheck, sampleCheck.getLock_control_force1(), editTexts3));
        editTexts3[1].addTextChangedListener(new EdTextChange("setLock_control_force1", sampleCheck, sampleCheck.getLock_control_force1(), editTexts3));
        editTexts3[2].addTextChangedListener(new EdTextChange("setLock_control_force1", sampleCheck, sampleCheck.getLock_control_force1(), editTexts3));

        editTexts4[0].addTextChangedListener(new EdTextChange("setLock_control_forceNM1", sampleCheck, sampleCheck.getLock_control_forceNM1(), editTexts4));
        editTexts4[1].addTextChangedListener(new EdTextChange("setLock_control_forceNM1", sampleCheck, sampleCheck.getLock_control_forceNM1(), editTexts4));
        editTexts4[2].addTextChangedListener(new EdTextChange("setLock_control_forceNM1", sampleCheck, sampleCheck.getLock_control_forceNM1(), editTexts4));

        editTexts5[0].addTextChangedListener(new EdTextChange("setHandleLength", sampleCheck, sampleCheck.getHandleLength(), editTexts5));

        editTexts6[0].addTextChangedListener(new EdTextChange("setPerformance_of_door_fans_left", sampleCheck, sampleCheck.getPerformance_of_door_fans_left(), editTexts6));
        editTexts6[1].addTextChangedListener(new EdTextChange("setPerformance_of_door_fans_left", sampleCheck, sampleCheck.getPerformance_of_door_fans_left(), editTexts6));
        editTexts6[2].addTextChangedListener(new EdTextChange("setPerformance_of_door_fans_left", sampleCheck, sampleCheck.getPerformance_of_door_fans_left(), editTexts6));

        editTexts7[0].addTextChangedListener(new EdTextChange("setPerformance_of_door_fans_right", sampleCheck, sampleCheck.getPerformance_of_door_fans_right(), editTexts7));
        editTexts7[1].addTextChangedListener(new EdTextChange("setPerformance_of_door_fans_right", sampleCheck, sampleCheck.getPerformance_of_door_fans_right(), editTexts7));
        editTexts7[2].addTextChangedListener(new EdTextChange("setPerformance_of_door_fans_right", sampleCheck, sampleCheck.getPerformance_of_door_fans_right(), editTexts7));

        editTexts8[0].addTextChangedListener(new EdTextChange("setDoor_fan_ambient_noise_left", sampleCheck, sampleCheck.getDoor_fan_ambient_noise_left(), editTexts8));
        editTexts8[1].addTextChangedListener(new EdTextChange("setDoor_fan_ambient_noise_left", sampleCheck, sampleCheck.getDoor_fan_ambient_noise_left(), editTexts8));
        editTexts8[2].addTextChangedListener(new EdTextChange("setDoor_fan_ambient_noise_left", sampleCheck, sampleCheck.getDoor_fan_ambient_noise_left(), editTexts8));

        editTexts9[0].addTextChangedListener(new EdTextChange("setDoor_fan_ambient_noise_right", sampleCheck, sampleCheck.getDoor_fan_ambient_noise_right(), editTexts9));
        editTexts9[1].addTextChangedListener(new EdTextChange("setDoor_fan_ambient_noise_right", sampleCheck, sampleCheck.getDoor_fan_ambient_noise_right(), editTexts9));
        editTexts9[2].addTextChangedListener(new EdTextChange("setDoor_fan_ambient_noise_right", sampleCheck, sampleCheck.getDoor_fan_ambient_noise_right(), editTexts9));

        editTexts10[0].addTextChangedListener(new EdTextChange("setDoor_leaf_noise_left", sampleCheck, sampleCheck.getDoor_leaf_noise_left(), editTexts10));
        editTexts10[1].addTextChangedListener(new EdTextChange("setDoor_leaf_noise_left", sampleCheck, sampleCheck.getDoor_leaf_noise_left(), editTexts10));
        editTexts10[2].addTextChangedListener(new EdTextChange("setDoor_leaf_noise_left", sampleCheck, sampleCheck.getDoor_leaf_noise_left(), editTexts10));

        editTexts11[0].addTextChangedListener(new EdTextChange("setDoor_leaf_noise_right", sampleCheck, sampleCheck.getDoor_leaf_noise_right (), editTexts11));
        editTexts11[1].addTextChangedListener(new EdTextChange("setDoor_leaf_noise_right", sampleCheck, sampleCheck.getDoor_leaf_noise_right (), editTexts11));
        editTexts11[2].addTextChangedListener(new EdTextChange("setDoor_leaf_noise_right", sampleCheck, sampleCheck.getDoor_leaf_noise_right (), editTexts11));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"door_open_force");
        map.put(ed22.getId(),"door_open_force");
        map.put(ed23.getId(),"door_open_force");
        map.put(ed51.getId(),"door_close_force");
        map.put(ed52.getId(),"door_close_force");
        map.put(ed53.getId(),"door_close_force");
        map.put(ed81.getId(),"lock_control_force");
        map.put(ed82.getId(),"lock_control_force");
        map.put(ed83.getId(),"lock_control_force");
        map.put(ed91.getId(),"lock_control_forcenm");
        map.put(ed92.getId(),"lock_control_forcenm");
        map.put(ed93.getId(),"lock_control_forcenm");
        map.put(ed101.getId(),"handlelength");
        map.put(ed131.getId(),"performance_of_door_fans_left");
        map.put(ed132.getId(),"performance_of_door_fans_left");
        map.put(ed133.getId(),"performance_of_door_fans_left");
        map.put(ed134.getId(),"performance_of_door_fans_right");
        map.put(ed135.getId(),"performance_of_door_fans_right");
        map.put(ed136.getId(),"performance_of_door_fans_right");
        map.put(ed151.getId(),"door_fan_ambient_noise_left");
        map.put(ed152.getId(),"door_fan_ambient_noise_left");
        map.put(ed153.getId(),"door_fan_ambient_noise_left");
        map.put(ed154.getId(),"door_fan_ambient_noise_right");
        map.put(ed155.getId(),"door_fan_ambient_noise_right");
        map.put(ed156.getId(),"door_fan_ambient_noise_right");
        map.put(ed171.getId(),"door_leaf_noise_left");
        map.put(ed172.getId(),"door_leaf_noise_left");
        map.put(ed173.getId(),"door_leaf_noise_left");
        map.put(ed174.getId(),"door_leaf_noise_right");
        map.put(ed175.getId(),"door_leaf_noise_right");
        map.put(ed176.getId(),"door_leaf_noise_right");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed51.getId(),"0");
        serialNumber.put(ed52.getId(),"1");
        serialNumber.put(ed53.getId(),"2");
        serialNumber.put(ed81.getId(),"0");
        serialNumber.put(ed82.getId(),"1");
        serialNumber.put(ed83.getId(),"2");
        serialNumber.put(ed91.getId(),"0");
        serialNumber.put(ed92.getId(),"1");
        serialNumber.put(ed93.getId(),"2");
        serialNumber.put(ed101.getId(),"0");
        serialNumber.put(ed131.getId(),"0");
        serialNumber.put(ed132.getId(),"1");
        serialNumber.put(ed133.getId(),"2");
        serialNumber.put(ed134.getId(),"0");
        serialNumber.put(ed135.getId(),"1");
        serialNumber.put(ed136.getId(),"2");
        serialNumber.put(ed151.getId(),"0");
        serialNumber.put(ed152.getId(),"1");
        serialNumber.put(ed153.getId(),"2");
        serialNumber.put(ed154.getId(),"0");
        serialNumber.put(ed155.getId(),"1");
        serialNumber.put(ed156.getId(),"2");
        serialNumber.put(ed171.getId(),"0");
        serialNumber.put(ed172.getId(),"1");
        serialNumber.put(ed173.getId(),"2");
        serialNumber.put(ed174.getId(),"0");
        serialNumber.put(ed175.getId(),"1");
        serialNumber.put(ed176.getId(),"2");
    }
}
