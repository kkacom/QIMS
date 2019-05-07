package com.zhilian.rf_qims.mvp.sample_data.pro_quality;


import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.api.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
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

public class ProQualityGJFB extends LazyLoadFragment {

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
    @BindView(R.id.tv_1_5)
    TextView tv15;
    @BindView(R.id.tv_1_6)
    TextView tv16;
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
    @BindView(R.id.ed_2_5)
    EditText ed25;
    @BindView(R.id.ed_2_6)
    EditText ed26;
    @BindView(R.id.ly_2_1)
    RelativeLayout ly21;
    @BindView(R.id.tv_title_3_1)
    TextView tvTitle31;
    @BindView(R.id.ed_3_1)
    EditText ed31;
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
    @BindView(R.id.ed_4_4)
    EditText ed44;
    @BindView(R.id.ed_4_5)
    EditText ed45;
    @BindView(R.id.ed_4_6)
    EditText ed46;
    @BindView(R.id.ly_4_1)
    RelativeLayout ly41;
    @BindView(R.id.tv_title_5_1)
    TextView tvTitle51;
    @BindView(R.id.ed_5_1)
    EditText ed51;
    @BindView(R.id.ly_5_1)
    RelativeLayout ly51;
    @BindView(R.id.tv_title_6_1)
    TextView tvTitle61;
    @BindView(R.id.ed_6_1)
    EditText ed61;
    @BindView(R.id.ed_6_2)
    EditText ed62;
    @BindView(R.id.ed_6_3)
    EditText ed63;
    @BindView(R.id.ed_6_4)
    EditText ed64;
    @BindView(R.id.ed_6_5)
    EditText ed65;
    @BindView(R.id.ed_6_6)
    EditText ed66;
    @BindView(R.id.ly_6_1)
    RelativeLayout ly61;
    @BindView(R.id.tv_title_7_1)
    TextView tvTitle71;
    @BindView(R.id.ed_7_1)
    EditText ed71;
    @BindView(R.id.ly_7_1)
    RelativeLayout ly71;
    @BindView(R.id.tv_title_8_1)
    TextView tvTitle81;
    @BindView(R.id.ed_8_1)
    EditText ed81;
    @BindView(R.id.tv_title_8_2)
    TextView tvTitle82;
    @BindView(R.id.ed_8_2)
    EditText ed82;
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
    @BindView(R.id.ly_11_1)
    RelativeLayout ly111;
    @BindView(R.id.tv_title_12_1)
    TextView tvTitle121;
    @BindView(R.id.ed_12_1_remark)
    EditText ed121Remark;
    @BindView(R.id.ly_12_1)
    RelativeLayout ly121;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_sczl_gjfb;
    }
    //设计值没有用
    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1 = new EditText[]{ed21, ed22, ed23,ed24,ed25,ed26,ed41,ed42,ed43,ed44,ed45,ed46,ed61,ed62,ed63,ed64,ed65,ed66};
        editTexts2 = new EditText[]{ed111, ed112};
        editTexts3 = new EditText[]{ed31, ed51,ed71};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed24, ed25,
            ed26, ed41, ed42, ed43, ed44, ed45, ed46, ed61, ed62, ed63, ed64,
            ed65, ed66, ed111, ed112, ed31, ed51, ed71};

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
            editTexts1[0].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[2]);
            editTexts1[3].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[3]);
            editTexts1[4].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[4]);
            editTexts1[5].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[5]);
            editTexts1[6].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[6]);
            editTexts1[7].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[7]);
            editTexts1[8].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[8]);
            editTexts1[9].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[9]);
            editTexts1[10].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[10]);
            editTexts1[11].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[11]);
            editTexts1[12].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[12]);
            editTexts1[13].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[13]);
            editTexts1[14].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[14]);
            editTexts1[15].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[15]);
            editTexts1[16].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[16]);
            editTexts1[17].setText(EdUtil.split(sampleCheck.getProtective_layer_thickness())[17]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getSteelbar_spacing())[0]);
            editTexts2[1].setText(EdUtil.split(sampleCheck.getSteelbar_spacing())[1]);

            editTexts3[0].setText(EdUtil.split(sampleCheck.getBar_spacing_file_name())[0]);
            editTexts3[1].setText(EdUtil.split(sampleCheck.getBar_spacing_file_name())[1]);
            editTexts3[2].setText(EdUtil.split(sampleCheck.getBar_spacing_file_name())[2]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[3].equals(""), ed24,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[4].equals(""), ed25,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[5].equals(""), ed26,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[6].equals(""), ed41,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[7].equals(""), ed42,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[8].equals(""), ed43,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[9].equals(""), ed44,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[10].equals(""), ed45,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[11].equals(""), ed46,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[12].equals(""), ed61,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[13].equals(""), ed62,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[14].equals(""), ed63,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[15].equals(""), ed64,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[16].equals(""), ed65,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getProtective_layer_thickness())[17].equals(""), ed66,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSteelbar_spacing())[0].equals(""), ed111,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSteelbar_spacing())[1].equals(""), ed112,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBar_spacing_file_name())[0].equals(""), ed31,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBar_spacing_file_name())[1].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBar_spacing_file_name())[2].equals(""), ed71,
                getContext(), sampleCheck.getId(), map, serialNumber);
            //设计
            /*UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getSteelbarProtectThick() ), ed81);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getBarDiameterDesignValue() ), ed82);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getSteelbarSpacing() ), ed113);*/
        }
        //设计值对比监听


        //填充设计值
        ed81.setText(StrKit.notBlank(sample.getSteelbarProtectThick() )? sample.getSteelbarProtectThick() +"": "");
        ed82.setText(StrKit.notBlank(sample.getBarDiameterDesignValue() )? sample.getBarDiameterDesignValue() +"": "");
        ed113.setText(StrKit.notBlank(sample.getSteelbarSpacing() )? sample.getSteelbarSpacing() +"": "");


        //设计值保存监听
        ed81.addTextChangedListener(new EdDesignChangeSave("setSteelbarProtectThick", sample, sample.getSteelbarProtectThick() + "", ed81));
        ed82.addTextChangedListener(new EdDesignChangeSave("setBarDiameterDesignValue", sample, sample.getBarDiameterDesignValue() + "", ed82));
        ed113.addTextChangedListener(new EdDesignChangeSave("setSteelbarSpacing", sample, sample.getSteelbarSpacing() + "", ed113));

        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[3].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[4].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[5].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[6].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[7].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[8].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[9].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[10].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[11].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[12].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[13].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[14].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[15].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[16].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));
        editTexts1[17].addTextChangedListener(new EdTextChange("setProtective_layer_thickness", sampleCheck, sampleCheck.getProtective_layer_thickness(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setSteelbar_spacing", sampleCheck, sampleCheck.getSteelbar_spacing(), editTexts2));
        editTexts2[1].addTextChangedListener(new EdTextChange("setSteelbar_spacing", sampleCheck, sampleCheck.getSteelbar_spacing(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setBar_spacing_file_name", sampleCheck, sampleCheck.getBar_spacing_file_name(), editTexts3));
        editTexts3[1].addTextChangedListener(new EdTextChange("setBar_spacing_file_name", sampleCheck, sampleCheck.getBar_spacing_file_name(), editTexts3));
        editTexts3[2].addTextChangedListener(new EdTextChange("setBar_spacing_file_name", sampleCheck, sampleCheck.getBar_spacing_file_name(), editTexts3));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"protective_layer_thickness");
        map.put(ed22.getId(),"protective_layer_thickness");
        map.put(ed23.getId(),"protective_layer_thickness");
        map.put(ed24.getId(),"protective_layer_thickness");
        map.put(ed25.getId(),"protective_layer_thickness");
        map.put(ed26.getId(),"protective_layer_thickness");
        map.put(ed41.getId(),"protective_layer_thickness");
        map.put(ed42.getId(),"protective_layer_thickness");
        map.put(ed43.getId(),"protective_layer_thickness");
        map.put(ed44.getId(),"protective_layer_thickness");
        map.put(ed45.getId(),"protective_layer_thickness");
        map.put(ed46.getId(),"protective_layer_thickness");
        map.put(ed61.getId(),"protective_layer_thickness");
        map.put(ed62.getId(),"protective_layer_thickness");
        map.put(ed63.getId(),"protective_layer_thickness");
        map.put(ed64.getId(),"protective_layer_thickness");
        map.put(ed65.getId(),"protective_layer_thickness");
        map.put(ed66.getId(),"protective_layer_thickness");
        map.put(ed111.getId(),"steelbar_spacing");
        map.put(ed112.getId(),"steelbar_spacing");
        map.put(ed31.getId(),"bar_spacing_file_name");
        map.put(ed51.getId(),"bar_spacing_file_name");
        map.put(ed71.getId(),"bar_spacing_file_name");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed24.getId(),"3");
        serialNumber.put(ed25.getId(),"4");
        serialNumber.put(ed26.getId(),"5");
        serialNumber.put(ed41.getId(),"6");
        serialNumber.put(ed42.getId(),"7");
        serialNumber.put(ed43.getId(),"8");
        serialNumber.put(ed44.getId(),"9");
        serialNumber.put(ed45.getId(),"10");
        serialNumber.put(ed46.getId(),"11");
        serialNumber.put(ed61.getId(),"12");
        serialNumber.put(ed62.getId(),"13");
        serialNumber.put(ed63.getId(),"14");
        serialNumber.put(ed64.getId(),"15");
        serialNumber.put(ed65.getId(),"16");
        serialNumber.put(ed66.getId(),"17");
        serialNumber.put(ed111.getId(),"0");
        serialNumber.put(ed112.getId(),"1");
        serialNumber.put(ed31.getId(),"0");
        serialNumber.put(ed51.getId(),"1");
        serialNumber.put(ed71.getId(),"2");
    }
}
