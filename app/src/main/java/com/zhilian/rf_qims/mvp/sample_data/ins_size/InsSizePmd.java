package com.zhilian.rf_qims.mvp.sample_data.ins_size;


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

public class InsSizePmd extends LazyLoadFragment {

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
    @BindView(R.id.ed_3_1)
    EditText ed31;
    @BindView(R.id.ed_3_2)
    EditText ed32;
    @BindView(R.id.ed_3_3)
    EditText ed33;
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
    @BindView(R.id.ed_10_1_remark)
    EditText ed101Remark;
    @BindView(R.id.ly_10_1)
    RelativeLayout ly101;
    @BindView(R.id.tv_title_11_1)
    TextView tvTitle111;
    @BindView(R.id.tv_11_1)
    TextView tv111;
    @BindView(R.id.tv_11_2)
    TextView tv112;
    @BindView(R.id.tv_11_3)
    TextView tv113;
    @BindView(R.id.tv_11_4)
    TextView tv114;
    @BindView(R.id.tv_11_5)
    TextView tv115;
    @BindView(R.id.tv_11_6)
    TextView tv116;
    @BindView(R.id.ly_11_1)
    RelativeLayout ly111;
    @BindView(R.id.tv_title_12_1)
    TextView tvTitle121;
    @BindView(R.id.ed_12_1)
    EditText ed121;
    @BindView(R.id.ed_12_2)
    EditText ed122;
    @BindView(R.id.ed_12_3)
    EditText ed123;
    @BindView(R.id.ed_12_4)
    EditText ed124;
    @BindView(R.id.ed_12_5)
    EditText ed125;
    @BindView(R.id.ed_12_6)
    EditText ed126;
    @BindView(R.id.ly_12_1)
    RelativeLayout ly121;
    @BindView(R.id.tv_title_13_1)
    TextView tvTitle131;
    @BindView(R.id.ed_13_1_remark)
    EditText ed131Remark;
    @BindView(R.id.ly_13_1)
    RelativeLayout ly131;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_zpcc_pmd;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1=new EditText[]{ed21,ed22,ed23,ed31,ed32,ed33,ed41,ed42,ed43};
        editTexts2=new EditText[]{ed71,ed72,ed73,ed81,ed82,ed83,ed91,ed92,ed93};
        editTexts3=new EditText[]{ed121,ed122,ed123,ed124,ed125,ed126};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed31, ed32, ed33, ed41, ed42,
            ed43, ed71, ed72, ed73, ed81, ed82, ed83, ed91, ed92, ed93, ed121, ed122, ed123, ed124, ed125, ed126};

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
            editTexts1[0].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[2]);
            editTexts1[3].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[3]);
            editTexts1[4].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[4]);
            editTexts1[5].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[5]);
            editTexts1[6].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[6]);
            editTexts1[7].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[7]);
            editTexts1[8].setText(EdUtil.split(sampleCheck.getDoor_frame_surface1())[8]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[0]);
            editTexts2[1].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[1]);
            editTexts2[2].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[2]);
            editTexts2[3].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[3]);
            editTexts2[4].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[4]);
            editTexts2[5].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[5]);
            editTexts2[6].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[6]);
            editTexts2[7].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[7]);
            editTexts2[8].setText(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[8]);

            editTexts3[0].setText(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[0]);
            editTexts3[1].setText(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[1]);
            editTexts3[2].setText(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[2]);
            editTexts3[3].setText(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[3]);
            editTexts3[4].setText(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[4]);
            editTexts3[5].setText(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[5]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[3].equals(""), ed31,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[4].equals(""), ed32,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[5].equals(""), ed33,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[6].equals(""), ed41,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[7].equals(""), ed42,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_surface1())[8].equals(""), ed43,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[0].equals(""), ed71,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[1].equals(""), ed72,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[2].equals(""), ed73,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[3].equals(""), ed81,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[4].equals(""), ed82,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[5].equals(""), ed83,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[6].equals(""), ed91,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[7].equals(""), ed92,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_surface1())[8].equals(""), ed93,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[0].equals(""), ed121,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[1].equals(""), ed122,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[2].equals(""), ed123,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[3].equals(""), ed124,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[4].equals(""), ed125,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_flatness1())[5].equals(""), ed126,
                getContext(), sampleCheck.getId(), map, serialNumber);
        }

        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));
        editTexts1[3].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));
        editTexts1[4].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));
        editTexts1[5].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));
        editTexts1[6].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));
        editTexts1[7].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));
        editTexts1[8].addTextChangedListener(new EdTextChange("setDoor_frame_surface1", sampleCheck, sampleCheck.getDoor_frame_surface1(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));
        editTexts2[1].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));
        editTexts2[2].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));
        editTexts2[3].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));
        editTexts2[4].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));
        editTexts2[5].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));
        editTexts2[6].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));
        editTexts2[7].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));
        editTexts2[8].addTextChangedListener(new EdTextChange("setDoor_leaf_surface1", sampleCheck, sampleCheck.getDoor_leaf_surface1(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setSwing_plate_flatness1", sampleCheck, sampleCheck.getSwing_plate_flatness1(), editTexts3));
        editTexts3[1].addTextChangedListener(new EdTextChange("setSwing_plate_flatness1", sampleCheck, sampleCheck.getSwing_plate_flatness1(), editTexts3));
        editTexts3[2].addTextChangedListener(new EdTextChange("setSwing_plate_flatness1", sampleCheck, sampleCheck.getSwing_plate_flatness1(), editTexts3));
        editTexts3[3].addTextChangedListener(new EdTextChange("setSwing_plate_flatness1", sampleCheck, sampleCheck.getSwing_plate_flatness1(), editTexts3));
        editTexts3[4].addTextChangedListener(new EdTextChange("setSwing_plate_flatness1", sampleCheck, sampleCheck.getSwing_plate_flatness1(), editTexts3));
        editTexts3[5].addTextChangedListener(new EdTextChange("setSwing_plate_flatness1", sampleCheck, sampleCheck.getSwing_plate_flatness1(), editTexts3));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"door_frame_surface");
        map.put(ed22.getId(),"door_frame_surface");
        map.put(ed23.getId(),"door_frame_surface");
        map.put(ed31.getId(),"door_frame_surface");
        map.put(ed32.getId(),"door_frame_surface");
        map.put(ed33.getId(),"door_frame_surface");
        map.put(ed41.getId(),"door_frame_surface");
        map.put(ed42.getId(),"door_frame_surface");
        map.put(ed43.getId(),"door_frame_surface");
        map.put(ed71.getId(),"door_leaf_surface");
        map.put(ed72.getId(),"door_leaf_surface");
        map.put(ed73.getId(),"door_leaf_surface");
        map.put(ed81.getId(),"door_leaf_surface");
        map.put(ed82.getId(),"door_leaf_surface");
        map.put(ed83.getId(),"door_leaf_surface");
        map.put(ed91.getId(),"door_leaf_surface");
        map.put(ed92.getId(),"door_leaf_surface");
        map.put(ed93.getId(),"door_leaf_surface");
        map.put(ed121.getId(),"swing_plate_flatness");
        map.put(ed122.getId(),"swing_plate_flatness");
        map.put(ed123.getId(),"swing_plate_flatness");
        map.put(ed124.getId(),"swing_plate_flatness");
        map.put(ed125.getId(),"swing_plate_flatness");
        map.put(ed126.getId(),"swing_plate_flatness");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed31.getId(),"3");
        serialNumber.put(ed32.getId(),"4");
        serialNumber.put(ed33.getId(),"5");
        serialNumber.put(ed41.getId(),"6");
        serialNumber.put(ed42.getId(),"7");
        serialNumber.put(ed43.getId(),"8");
        serialNumber.put(ed71.getId(),"0");
        serialNumber.put(ed72.getId(),"1");
        serialNumber.put(ed73.getId(),"2");
        serialNumber.put(ed81.getId(),"3");
        serialNumber.put(ed82.getId(),"4");
        serialNumber.put(ed83.getId(),"5");
        serialNumber.put(ed91.getId(),"6");
        serialNumber.put(ed92.getId(),"7");
        serialNumber.put(ed93.getId(),"8");
        serialNumber.put(ed121.getId(),"0");
        serialNumber.put(ed122.getId(),"1");
        serialNumber.put(ed123.getId(),"2");
        serialNumber.put(ed124.getId(),"3");
        serialNumber.put(ed125.getId(),"4");
        serialNumber.put(ed126.getId(),"5");
    }
}
