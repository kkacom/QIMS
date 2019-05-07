package com.zhilian.rf_qims.mvp.sample_data.ins_size;


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

public class InsSizeZjgc extends LazyLoadFragment {
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
    @BindView(R.id.tv_9_1)
    TextView tv91;
    @BindView(R.id.tv_9_2)
    TextView tv92;
    @BindView(R.id.tv_9_3)
    TextView tv93;
    @BindView(R.id.tv_9_4)
    TextView tv94;
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
    @BindView(R.id.ly_13_1)
    RelativeLayout ly131;
    @BindView(R.id.tv_title_14_1)
    TextView tvTitle141;
    @BindView(R.id.ed_14_1_remark)
    EditText ed141Remark;
    @BindView(R.id.ly_14_1)
    RelativeLayout ly141;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTexts4;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_zpcc_zjgc;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1=new EditText[]{ed21,ed22,ed23};
        editTexts2=new EditText[]{ed51,ed52,ed53};
        editTexts3=new EditText[]{ed81,ed82,ed83,ed101,ed102,ed103};
        editTexts4=new EditText[]{ed131,ed132,ed133};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed51,
            ed52, ed53, ed81, ed82, ed83, ed101, ed102, ed104, ed131, ed132, ed133};

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
            editTexts1[0].setText(EdUtil.split(sampleCheck.getHingepage_shaft_diameter1())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getHingepage_shaft_diameter1())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getHingepage_shaft_diameter1())[2]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getHingepage_hole_diameter1())[0]);
            editTexts2[1].setText(EdUtil.split(sampleCheck.getHingepage_hole_diameter1())[1]);
            editTexts2[2].setText(EdUtil.split(sampleCheck.getHingepage_hole_diameter1())[2]);

            editTexts3[0].setText(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[0]);
            editTexts3[1].setText(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[1]);
            editTexts3[2].setText(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[2]);
            editTexts3[3].setText(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[3]);
            editTexts3[4].setText(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[4]);
            editTexts3[5].setText(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[5]);

            editTexts4[0].setText(EdUtil.split(sampleCheck.getAtresia_hole_diameter1())[0]);
            editTexts4[1].setText(EdUtil.split(sampleCheck.getAtresia_hole_diameter1())[1]);
            editTexts4[2].setText(EdUtil.split(sampleCheck.getAtresia_hole_diameter1())[2]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHingepage_shaft_diameter1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHingepage_shaft_diameter1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHingepage_shaft_diameter1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHingepage_hole_diameter1())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHingepage_hole_diameter1())[1].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHingepage_hole_diameter1())[2].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[0].equals(""), ed81,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[1].equals(""), ed82,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[2].equals(""), ed83,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[3].equals(""), ed101,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[4].equals(""), ed102,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_shaft_diameter1())[5].equals(""), ed103,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_hole_diameter1())[0].equals(""), ed131,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_hole_diameter1())[1].equals(""), ed132,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getAtresia_hole_diameter1())[2].equals(""), ed133,
                getContext(), sampleCheck.getId(), map, serialNumber);
            //设计
            /*UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getHingepageShaftDiameter()), ed24);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getHingepageHoleDiameter()), ed54);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getAtresiaShaftDiameter()), ed104);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getAtresiaHoleDiameter()), ed134);*/

            //设计值对比监听
            ed24.addTextChangedListener(new EdCompareDesign(editTexts1[0], ed24));
            ed24.addTextChangedListener(new EdCompareDesign(editTexts1[1], ed24));
            ed24.addTextChangedListener(new EdCompareDesign(editTexts1[2], ed24));

            ed54.addTextChangedListener(new EdCompareDesign(editTexts2[0], ed54));
            ed54.addTextChangedListener(new EdCompareDesign(editTexts2[1], ed54));
            ed54.addTextChangedListener(new EdCompareDesign(editTexts2[2], ed54));

            ed104.addTextChangedListener(new EdCompareDesign(editTexts3[0], ed104));
            ed104.addTextChangedListener(new EdCompareDesign(editTexts3[1], ed104));
            ed104.addTextChangedListener(new EdCompareDesign(editTexts3[2], ed104));
            ed104.addTextChangedListener(new EdCompareDesign(editTexts3[3], ed104));
            ed104.addTextChangedListener(new EdCompareDesign(editTexts3[4], ed104));
            ed104.addTextChangedListener(new EdCompareDesign(editTexts3[5], ed104));

            ed134.addTextChangedListener(new EdCompareDesign(editTexts4[0], ed134));
            ed134.addTextChangedListener(new EdCompareDesign(editTexts4[1], ed134));
            ed134.addTextChangedListener(new EdCompareDesign(editTexts4[2], ed134));

            editTexts1[0].addTextChangedListener(new EdCompareDesign(editTexts1[0], ed24));
            editTexts1[1].addTextChangedListener(new EdCompareDesign(editTexts1[1], ed24));
            editTexts1[2].addTextChangedListener(new EdCompareDesign(editTexts1[2], ed24));

            editTexts2[0].addTextChangedListener(new EdCompareDesign(editTexts2[0], ed54));
            editTexts2[1].addTextChangedListener(new EdCompareDesign(editTexts2[1], ed54));
            editTexts2[2].addTextChangedListener(new EdCompareDesign(editTexts2[2], ed54));

            editTexts3[0].addTextChangedListener(new EdCompareDesign(editTexts3[0], ed104));
            editTexts3[1].addTextChangedListener(new EdCompareDesign(editTexts3[1], ed104));
            editTexts3[2].addTextChangedListener(new EdCompareDesign(editTexts3[2], ed104));
            editTexts3[3].addTextChangedListener(new EdCompareDesign(editTexts3[3], ed104));
            editTexts3[4].addTextChangedListener(new EdCompareDesign(editTexts3[4], ed104));
            editTexts3[5].addTextChangedListener(new EdCompareDesign(editTexts3[5], ed104));

            editTexts4[0].addTextChangedListener(new EdCompareDesign(editTexts4[0], ed134));
            editTexts4[1].addTextChangedListener(new EdCompareDesign(editTexts4[1], ed134));
            editTexts4[2].addTextChangedListener(new EdCompareDesign(editTexts4[2], ed134));
        }

        //填充设计值
        ed24.setText(StrKit.notBlank(sample.getHingepageShaftDiameter() )? sample.getHingepageShaftDiameter() +"": "");
        ed54.setText(StrKit.notBlank(sample.getHingepageHoleDiameter() )? sample.getHingepageHoleDiameter() +"": "");
        ed104.setText(StrKit.notBlank(sample.getAtresiaShaftDiameter())? sample.getAtresiaShaftDiameter()+"": "");
        ed134.setText(StrKit.notBlank(sample.getAtresiaHoleDiameter())? sample.getAtresiaHoleDiameter() +"": "");

        //设计值保存监听
        ed24.addTextChangedListener(new EdDesignChangeSave("setHingepageShaftDiameter", sample, sample.getHingepageShaftDiameter()  + "", ed24));
        ed54.addTextChangedListener(new EdDesignChangeSave("setHingepageHoleDiameter", sample, sample.getHingepageHoleDiameter() + "", ed54));
        ed104.addTextChangedListener(new EdDesignChangeSave("setAtresiaShaftDiameter", sample, sample.getAtresiaShaftDiameter() + "", ed104));
        ed134.addTextChangedListener(new EdDesignChangeSave("setAtresiaHoleDiameter", sample, sample.getAtresiaHoleDiameter() + "", ed134));

        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setHingepage_shaft_diameter1", sampleCheck, sampleCheck.getHingepage_shaft_diameter1(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setHingepage_shaft_diameter1", sampleCheck, sampleCheck.getHingepage_shaft_diameter1(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setHingepage_shaft_diameter1", sampleCheck, sampleCheck.getHingepage_shaft_diameter1(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setHingepage_hole_diameter1", sampleCheck, sampleCheck.getHingepage_hole_diameter1(), editTexts2));
        editTexts2[1].addTextChangedListener(new EdTextChange("setHingepage_hole_diameter1", sampleCheck, sampleCheck.getHingepage_hole_diameter1(), editTexts2));
        editTexts2[2].addTextChangedListener(new EdTextChange("setHingepage_hole_diameter1", sampleCheck, sampleCheck.getHingepage_hole_diameter1(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setAtresia_shaft_diameter1", sampleCheck, sampleCheck.getAtresia_shaft_diameter1(), editTexts3));
        editTexts3[1].addTextChangedListener(new EdTextChange("setAtresia_shaft_diameter1", sampleCheck, sampleCheck.getAtresia_shaft_diameter1(), editTexts3));
        editTexts3[2].addTextChangedListener(new EdTextChange("setAtresia_shaft_diameter1", sampleCheck, sampleCheck.getAtresia_shaft_diameter1(), editTexts3));
        editTexts3[3].addTextChangedListener(new EdTextChange("setAtresia_shaft_diameter1", sampleCheck, sampleCheck.getAtresia_shaft_diameter1(), editTexts3));
        editTexts3[4].addTextChangedListener(new EdTextChange("setAtresia_shaft_diameter1", sampleCheck, sampleCheck.getAtresia_shaft_diameter1(), editTexts3));
        editTexts3[5].addTextChangedListener(new EdTextChange("setAtresia_shaft_diameter1", sampleCheck, sampleCheck.getAtresia_shaft_diameter1(), editTexts3));

        editTexts4[0].addTextChangedListener(new EdTextChange("setAtresia_hole_diameter1", sampleCheck, sampleCheck.getAtresia_hole_diameter1(), editTexts4));
        editTexts4[1].addTextChangedListener(new EdTextChange("setAtresia_hole_diameter1", sampleCheck, sampleCheck.getAtresia_hole_diameter1(), editTexts4));
        editTexts4[2].addTextChangedListener(new EdTextChange("setAtresia_hole_diameter1", sampleCheck, sampleCheck.getAtresia_hole_diameter1(), editTexts4));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"hingepage_shaft_diameter");
        map.put(ed22.getId(),"hingepage_shaft_diameter");
        map.put(ed23.getId(),"hingepage_shaft_diameter");
        map.put(ed51.getId(),"hingepage_hole_diameter");
        map.put(ed52.getId(),"hingepage_hole_diameter");
        map.put(ed53.getId(),"hingepage_hole_diameter");
        map.put(ed81.getId(),"atresia_shaft_diameter");
        map.put(ed82.getId(),"atresia_shaft_diameter");
        map.put(ed83.getId(),"atresia_shaft_diameter");
        map.put(ed101.getId(),"atresia_shaft_diameter");
        map.put(ed102.getId(),"atresia_shaft_diameter");
        map.put(ed103.getId(),"atresia_shaft_diameter");
        map.put(ed131.getId(),"atresia_hole_diameter");
        map.put(ed132.getId(),"atresia_hole_diameter");
        map.put(ed133.getId(),"atresia_hole_diameter");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed51.getId(),"0");
        serialNumber.put(ed52.getId(),"1");
        serialNumber.put(ed53.getId(),"2");
        serialNumber.put(ed81.getId(),"0");
        serialNumber.put(ed82.getId(),"1");
        serialNumber.put(ed83.getId(),"2");
        serialNumber.put(ed101.getId(),"3");
        serialNumber.put(ed102.getId(),"4");
        serialNumber.put(ed103.getId(),"5");
        serialNumber.put(ed131.getId(),"0");
        serialNumber.put(ed132.getId(),"1");
        serialNumber.put(ed133.getId(),"2");
    }
}
