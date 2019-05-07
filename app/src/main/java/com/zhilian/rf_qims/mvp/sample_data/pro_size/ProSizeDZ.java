package com.zhilian.rf_qims.mvp.sample_data.pro_size;

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
import com.zhilian.rf_qims.interfaces.EdDifferenceDesign;
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

public class ProSizeDZ extends LazyLoadFragment {
//    @BindView(R.id.tv_page_title)
//    TextView tvPageTitle;
//    @BindView(R.id.tv_page_before)
//    TextView tvPageBefore;
//    @BindView(R.id.tv_page_curpage)
//    TextView tvPageCurpage;
//    @BindView(R.id.tv_page_next)
//    TextView tvPageNext;
//    @BindView(R.id.btn_save)
//    Button btnSave;
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
    @BindView(R.id.tv_title_15_1)
    TextView tvTitle151;
    @BindView(R.id.tv_15_1)
    TextView tv151;
    @BindView(R.id.tv_15_2)
    TextView tv152;
    @BindView(R.id.tv_15_3)
    TextView tv153;
    @BindView(R.id.tv_15_4)
    TextView tv154;
    @BindView(R.id.tv_15_5)
    TextView tv155;
    @BindView(R.id.ly_15_1)
    RelativeLayout ly151;
    @BindView(R.id.tv_title_16_1)
    TextView tvTitle161;
    @BindView(R.id.ed_16_1)
    EditText ed161;
    @BindView(R.id.ed_16_2)
    EditText ed162;
    @BindView(R.id.ed_16_3)
    EditText ed163;
    @BindView(R.id.ed_16_4)
    EditText ed164;
    @BindView(R.id.ed_16_5)
    EditText ed165;
    @BindView(R.id.ly_16_1)
    RelativeLayout ly161;
    @BindView(R.id.tv_title_17_1)
    TextView tvTitle171;
    @BindView(R.id.tv_17_1)
    TextView tv171;
    @BindView(R.id.tv_17_2)
    TextView tv172;
    @BindView(R.id.tv_17_3)
    TextView tv173;
    @BindView(R.id.tv_17_4)
    TextView tv174;
    @BindView(R.id.tv_17_5)
    TextView tv175;
    @BindView(R.id.ly_17_1)
    RelativeLayout ly171;
    @BindView(R.id.tv_title_18_1)
    TextView tvTitle181;
    @BindView(R.id.ed_18_1)
    EditText ed181;
    @BindView(R.id.ed_18_2)
    EditText ed182;
    @BindView(R.id.ed_18_3)
    EditText ed183;
    @BindView(R.id.ed_18_4)
    EditText ed184;
    @BindView(R.id.ed_18_5)
    EditText ed185;
    @BindView(R.id.ly_18_1)
    RelativeLayout ly181;
    @BindView(R.id.tv_title_19_1)
    TextView tvTitle191;
    @BindView(R.id.ed_19_1_remark)
    EditText ed191Remark;
    @BindView(R.id.ly_19_1)
    RelativeLayout ly191;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTexts4;
    private EditText[] editTexts5;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    //    public EditText med_2;
    @Override
    protected int setContentView() {
        return R.layout.fragment_sccc_ms;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);

        editTexts1 = new EditText[]{ed21, ed22, ed23};
        editTexts2 = new EditText[]{ed51, ed52, ed53};
        editTexts3 = new EditText[]{ed81, ed82};
        editTexts4 = new EditText[]{ed111, ed112,ed113,ed131,ed132,ed133};
        editTexts5 = new EditText[]{ed161,ed162,ed163,ed164,ed165,ed181,ed182,ed183,ed184};
        editTextsAll = new EditText[]{ed21, ed22, ed23,
            ed51, ed52, ed53,  ed81, ed82, ed111, ed112, ed113, ed111 ,ed112 ,ed113 ,ed131 ,ed132 ,ed133 ,  ed161,
            ed162 ,ed163 ,ed164 ,ed165 ,ed181 ,ed182 ,ed183 ,ed184};

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
            ed21.setText(EdUtil.split(sampleCheck.getDoor_leaf_height1())[0]);
            ed22.setText(EdUtil.split(sampleCheck.getDoor_leaf_height1())[1]);
            ed23.setText(EdUtil.split(sampleCheck.getDoor_leaf_height1())[2]);

            ed51.setText(EdUtil.split(sampleCheck.getDoor_leaf_width1())[0]);
            ed52.setText(EdUtil.split(sampleCheck.getDoor_leaf_width1())[1]);
            ed53.setText(EdUtil.split(sampleCheck.getDoor_leaf_width1())[2]);

            ed81.setText(EdUtil.split(sampleCheck.getDoor_leaf_diagonal())[0]);
            ed82.setText(EdUtil.split(sampleCheck.getDoor_leaf_diagonal())[1]);

            ed111.setText(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[0]);
            ed112.setText(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[1]);
            ed113.setText(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[2]);
            ed131.setText(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[3]);
            ed132.setText(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[4]);
            ed133.setText(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[5]);

            ed161.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[0]);
            ed162.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[1]);
            ed163.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[2]);
            ed164.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[3]);
            ed165.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[4]);
            ed181.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[5]);
            ed182.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[6]);
            ed183.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[7]);
            ed184.setText(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[8]);

            //判断检测值是否有值设置不可编辑
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_height1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_height1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_height1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_width1())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_width1())[1].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_width1())[2].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_diagonal())[0].equals(""), ed81,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_diagonal())[1].equals(""), ed82,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[0].equals(""), ed111,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[1].equals(""), ed112,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[2].equals(""), ed113,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[3].equals(""), ed131,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[4].equals(""), ed132,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_thickess())[5].equals(""), ed133,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[0].equals(""), ed161,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[1].equals(""), ed162,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[2].equals(""), ed163,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[3].equals(""), ed164,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[4].equals(""), ed165,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[5].equals(""), ed181,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[6].equals(""), ed182,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[7].equals(""), ed183,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getEmbedded_plate_height1())[8].equals(""), ed184,
                getContext(), sampleCheck.getId(), map, serialNumber);
            //设计
            /*UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoor_leaf_height()), ed24);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoor_leaf_width()), ed54);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoor_leaf_width()), ed83);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoor_leaf_thickness()), ed134);
            UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getBattenHeight()), ed185);*/
        }

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

        ed134.addTextChangedListener(new EdCompareDesign(editTexts4[0],ed134));
        ed134.addTextChangedListener(new EdCompareDesign(editTexts4[1],ed134));
        ed134.addTextChangedListener(new EdCompareDesign(editTexts4[2],ed134));
        ed134.addTextChangedListener(new EdCompareDesign(editTexts4[3],ed134));
        ed134.addTextChangedListener(new EdCompareDesign(editTexts4[4],ed134));
        ed134.addTextChangedListener(new EdCompareDesign(editTexts4[5],ed134));

        editTexts4[0].addTextChangedListener(new EdCompareDesign(editTexts4[0],ed134));
        editTexts4[1].addTextChangedListener(new EdCompareDesign(editTexts4[1],ed134));
        editTexts4[2].addTextChangedListener(new EdCompareDesign(editTexts4[2],ed134));
        editTexts4[3].addTextChangedListener(new EdCompareDesign(editTexts4[3],ed134));
        editTexts4[4].addTextChangedListener(new EdCompareDesign(editTexts4[4],ed134));
        editTexts4[5].addTextChangedListener(new EdCompareDesign(editTexts4[5],ed134));

        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[0],ed185));
        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[1],ed185));
        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[2],ed185));
        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[3],ed185));
        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[4],ed185));
        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[5],ed185));
        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[6],ed185));
        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[7],ed185));
        ed185.addTextChangedListener(new EdCompareDesign(editTexts5[8],ed185));

        editTexts5[0].addTextChangedListener(new EdCompareDesign(editTexts5[0],ed185));
        editTexts5[1].addTextChangedListener(new EdCompareDesign(editTexts5[1],ed185));
        editTexts5[2].addTextChangedListener(new EdCompareDesign(editTexts5[2],ed185));
        editTexts5[3].addTextChangedListener(new EdCompareDesign(editTexts5[3],ed185));
        editTexts5[4].addTextChangedListener(new EdCompareDesign(editTexts5[4],ed185));
        editTexts5[5].addTextChangedListener(new EdCompareDesign(editTexts5[5],ed185));
        editTexts5[6].addTextChangedListener(new EdCompareDesign(editTexts5[6],ed185));
        editTexts5[7].addTextChangedListener(new EdCompareDesign(editTexts5[7],ed185));
        editTexts5[8].addTextChangedListener(new EdCompareDesign(editTexts5[8],ed185));
        //填充设计值
        ed24.setText(StrKit.notBlank(sample.getDoor_leaf_height() )? sample.getDoor_leaf_height() +"": "");
        ed54.setText(StrKit.notBlank(sample.getDoor_leaf_width() )? sample.getDoor_leaf_width() +"": "");
        ed134.setText(StrKit.notBlank(sample.getDoor_leaf_thickness() )? sample.getDoor_leaf_thickness() +"": "");
        ed185.setText(StrKit.notBlank(sample.getBattenHeight())? sample.getBattenHeight() +"": "");

        //设计值保存监听
        ed24.addTextChangedListener(new EdDesignChangeSave("setDoor_leaf_width", sample, sample.getDoor_leaf_width() + "", ed24));
        ed54.addTextChangedListener(new EdDesignChangeSave("setDoor_leaf_height", sample, sample.getDoor_leaf_height() + "", ed54));
        ed134.addTextChangedListener(new EdDesignChangeSave("setDoor_leaf_thickness", sample, sample.getDoor_leaf_thickness() + "", ed134));
        ed185.addTextChangedListener(new EdDesignChangeSave("setBattenHeight", sample, sample.getBattenHeight() + "", ed185));

        //检测值保存监听
        ed21.addTextChangedListener(new EdTextChange("setDoor_leaf_height1", sampleCheck, sampleCheck.getDoor_leaf_height1(), editTexts1));
        ed22.addTextChangedListener(new EdTextChange("setDoor_leaf_height1", sampleCheck, sampleCheck.getDoor_leaf_height1(), editTexts1));
        ed23.addTextChangedListener(new EdTextChange("setDoor_leaf_height1", sampleCheck, sampleCheck.getDoor_leaf_height1(), editTexts1));

        ed51.addTextChangedListener(new EdTextChange("setDoor_leaf_width1", sampleCheck, sampleCheck.getDoor_leaf_width1(), editTexts2));
        ed52.addTextChangedListener(new EdTextChange("setDoor_leaf_width1", sampleCheck, sampleCheck.getDoor_leaf_width1(), editTexts2));
        ed53.addTextChangedListener(new EdTextChange("setDoor_leaf_width1", sampleCheck, sampleCheck.getDoor_leaf_width1(), editTexts2));

        ed81.addTextChangedListener(new EdTextChange("setDoor_leaf_diagonal", sampleCheck, sampleCheck.getDoor_leaf_diagonal(), editTexts3));
        ed82.addTextChangedListener(new EdTextChange("setDoor_leaf_diagonal", sampleCheck, sampleCheck.getDoor_leaf_diagonal(), editTexts3));

        ed111.addTextChangedListener(new EdTextChange("setDoor_leaf_thickess", sampleCheck, sampleCheck.getDoor_leaf_thickess(), editTexts4));
        ed112.addTextChangedListener(new EdTextChange("setDoor_leaf_thickess", sampleCheck, sampleCheck.getDoor_leaf_thickess(), editTexts4));
        ed113.addTextChangedListener(new EdTextChange("setDoor_leaf_thickess", sampleCheck, sampleCheck.getDoor_leaf_thickess(), editTexts4));
        ed131.addTextChangedListener(new EdTextChange("setDoor_leaf_thickess", sampleCheck, sampleCheck.getDoor_leaf_thickess(), editTexts4));
        ed132.addTextChangedListener(new EdTextChange("setDoor_leaf_thickess", sampleCheck, sampleCheck.getDoor_leaf_thickess(), editTexts4));
        ed133.addTextChangedListener(new EdTextChange("setDoor_leaf_thickess", sampleCheck, sampleCheck.getDoor_leaf_thickess(), editTexts4));

        ed161.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));
        ed162.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));
        ed163.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));
        ed164.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));
        ed165.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));
        ed181.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));
        ed182.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));
        ed183.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));
        ed184.addTextChangedListener(new EdTextChange("setEmbedded_plate_height1", sampleCheck, sampleCheck.getEmbedded_plate_height1(), editTexts5));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"door_leaf_height");
        map.put(ed22.getId(),"door_leaf_height");
        map.put(ed23.getId(),"door_leaf_height");
        map.put(ed51.getId(),"door_leaf_width");
        map.put(ed52.getId(),"door_leaf_width");
        map.put(ed53.getId(),"door_leaf_width");
        map.put(ed81.getId(),"door_leaf_diagonal");
        map.put(ed82.getId(),"door_leaf_diagonal");
        map.put(ed111.getId(),"door_leaf_thickess");
        map.put(ed112.getId(),"door_leaf_thickess");
        map.put(ed113.getId(),"door_leaf_thickess");
        map.put(ed131.getId(),"door_leaf_thickess");
        map.put(ed132.getId(),"door_leaf_thickess");
        map.put(ed133.getId(),"door_leaf_thickess");
        map.put(ed161.getId(),"embedded_plate_height");
        map.put(ed162.getId(),"embedded_plate_height");
        map.put(ed163.getId(),"embedded_plate_height");
        map.put(ed164.getId(),"embedded_plate_height");
        map.put(ed165.getId(),"embedded_plate_height");
        map.put(ed181.getId(),"embedded_plate_height");
        map.put(ed182.getId(),"embedded_plate_height");
        map.put(ed183.getId(),"embedded_plate_height");
        map.put(ed184.getId(),"embedded_plate_height");

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
        serialNumber.put(ed113.getId(),"2");
        serialNumber.put(ed131.getId(),"3");
        serialNumber.put(ed132.getId(),"4");
        serialNumber.put(ed133.getId(),"5");
        serialNumber.put(ed161.getId(),"0");
        serialNumber.put(ed162.getId(),"1");
        serialNumber.put(ed163.getId(),"2");
        serialNumber.put(ed164.getId(),"3");
        serialNumber.put(ed165.getId(),"4");
        serialNumber.put(ed181.getId(),"5");
        serialNumber.put(ed182.getId(),"6");
        serialNumber.put(ed183.getId(),"7");
        serialNumber.put(ed184.getId(),"8");
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }


//    @OnClick({R.id.tv_page_before, R.id.tv_page_curpage, R.id.btn_save})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_page_before:
//                break;
//            case R.id.tv_page_curpage:
//                break;
//            case R.id.btn_save:
//
//                break;
//        }
//    }
}