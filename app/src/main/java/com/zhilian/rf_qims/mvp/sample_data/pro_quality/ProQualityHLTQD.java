package com.zhilian.rf_qims.mvp.sample_data.pro_quality;


import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhilian.api.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.interfaces.EdAverageAndDiffer;
import com.zhilian.rf_qims.interfaces.EdDesignChangeSave;
import com.zhilian.rf_qims.interfaces.EdTextChange;
import com.zhilian.rf_qims.interfaces.EdThreeAverage;
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

public class ProQualityHLTQD extends LazyLoadFragment{

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
    @BindView(R.id.ed_3_1)
    EditText ed31;
    @BindView(R.id.ed_3_2)
    EditText ed32;
    @BindView(R.id.ed_3_3)
    EditText ed33;
    @BindView(R.id.ed_3_4)
    EditText ed34;
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
    @BindView(R.id.ed_6_1)
    EditText ed61;
    @BindView(R.id.ed_6_2)
    EditText ed62;
    @BindView(R.id.ed_6_3)
    EditText ed63;
    @BindView(R.id.ed_6_4)
    EditText ed64;
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
    @BindView(R.id.ed_8_1)
    EditText ed81;
    @BindView(R.id.ed_8_2)
    EditText ed82;
    @BindView(R.id.ed_8_3)
    EditText ed83;
    @BindView(R.id.ed_8_4)
    EditText ed84;
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
    @BindView(R.id.ed_9_4)
    EditText ed94;
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
    @BindView(R.id.ed_11_1)
    EditText ed111;
    @BindView(R.id.ed_11_2)
    EditText ed112;
    @BindView(R.id.ed_11_3)
    EditText ed113;
    @BindView(R.id.ed_11_4)
    EditText ed114;
    @BindView(R.id.ly_11_1)
    RelativeLayout ly111;
    @BindView(R.id.tv_title_12_1)
    TextView tvTitle121;
    @BindView(R.id.ed_12_1)
    EditText ed121;
    @BindView(R.id.tv_title_12_2)
    TextView tvTitle122;
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
    @BindView(R.id.ly_15_1)
    RelativeLayout ly151;
    @BindView(R.id.tv_title_16_1)
    TextView tvTitle161;
    @BindView(R.id.ed_16_1_remark)
    EditText ed161Remark;
    @BindView(R.id.ly_16_1)
    RelativeLayout ly161;
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
    private EditText[] editTexts12;
    private EditText[] editTexts13;
    private EditText[] editTexts14;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_sczl_hntqd;
    }
    //设计值没有用
    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1=new EditText[]{ed21,ed22,ed23};
        editTexts2=new EditText[]{ed31,ed32,ed33};
        editTexts3=new EditText[]{ed41,ed42,ed43};
        editTexts4=new EditText[]{ed51,ed52,ed53};
        editTexts5=new EditText[]{ed61,ed62,ed63};
        editTexts6=new EditText[]{ed71,ed72,ed73};
        editTexts7=new EditText[]{ed81,ed82,ed83};
        editTexts8=new EditText[]{ed91,ed92,ed93};
        editTexts9=new EditText[]{ed101,ed102,ed103};
        editTexts10=new EditText[]{ed111,ed112,ed113};
        editTexts11=new EditText[]{ed121};
        editTexts12=new EditText[]{ed122};
        editTexts13=new EditText[]{ed151};
        editTexts14=new EditText[]{ed153};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed31, ed32,
            ed33, ed41, ed42, ed43, ed51, ed52, ed53, ed61, ed62, ed63, ed71,
            ed72, ed73, ed81, ed82, ed83, ed91, ed92, ed93, ed101, ed102, ed103, ed111, ed112, ed113, ed121, ed122, ed151, ed153};

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
            //碳化深度极差值和碳化深度平均值
            ed24.addTextChangedListener(new EdAverageAndDiffer(ed24,ed34,ed44,ed121,ed122));
            ed34.addTextChangedListener(new EdAverageAndDiffer(ed24,ed34,ed44,ed121,ed122));
            ed44.addTextChangedListener(new EdAverageAndDiffer(ed24,ed34,ed44,ed121,ed122));

            //三个数的平均值
            editTexts1[0].addTextChangedListener(new EdThreeAverage(editTexts1[0],editTexts1[1],editTexts1[2],ed24));
            editTexts1[1].addTextChangedListener(new EdThreeAverage(editTexts1[0],editTexts1[1],editTexts1[2],ed24));
            editTexts1[2].addTextChangedListener(new EdThreeAverage(editTexts1[0],editTexts1[1],editTexts1[2],ed24));

            editTexts2[0].addTextChangedListener(new EdThreeAverage(editTexts2[0],editTexts2[1],editTexts2[2],ed34));
            editTexts2[1].addTextChangedListener(new EdThreeAverage(editTexts2[0],editTexts2[1],editTexts2[2],ed34));
            editTexts2[2].addTextChangedListener(new EdThreeAverage(editTexts2[0],editTexts2[1],editTexts2[2],ed34));

            editTexts3[0].addTextChangedListener(new EdThreeAverage(editTexts3[0],editTexts3[1],editTexts3[2],ed44));
            editTexts3[1].addTextChangedListener(new EdThreeAverage(editTexts3[0],editTexts3[1],editTexts3[2],ed44));
            editTexts3[2].addTextChangedListener(new EdThreeAverage(editTexts3[0],editTexts3[1],editTexts3[2],ed44));


            editTexts4[0].addTextChangedListener(new EdThreeAverage(editTexts4[0],editTexts4[1],editTexts4[2],ed54));
            editTexts4[1].addTextChangedListener(new EdThreeAverage(editTexts4[0],editTexts4[1],editTexts4[2],ed54));
            editTexts4[2].addTextChangedListener(new EdThreeAverage(editTexts4[0],editTexts4[1],editTexts4[2],ed54));

            editTexts5[0].addTextChangedListener(new EdThreeAverage(editTexts5[0],editTexts5[1],editTexts5[2],ed64));
            editTexts5[1].addTextChangedListener(new EdThreeAverage(editTexts5[0],editTexts5[1],editTexts5[2],ed64));
            editTexts5[2].addTextChangedListener(new EdThreeAverage(editTexts5[0],editTexts5[1],editTexts5[2],ed64));

            editTexts6[0].addTextChangedListener(new EdThreeAverage(editTexts6[0],editTexts6[1],editTexts6[2],ed74));
            editTexts6[1].addTextChangedListener(new EdThreeAverage(editTexts6[0],editTexts6[1],editTexts6[2],ed74));
            editTexts6[2].addTextChangedListener(new EdThreeAverage(editTexts6[0],editTexts6[1],editTexts6[2],ed74));

            editTexts7[0].addTextChangedListener(new EdThreeAverage(editTexts7[0],editTexts7[1],editTexts7[2],ed84));
            editTexts7[1].addTextChangedListener(new EdThreeAverage(editTexts7[0],editTexts7[1],editTexts7[2],ed84));
            editTexts7[2].addTextChangedListener(new EdThreeAverage(editTexts7[0],editTexts7[1],editTexts7[2],ed84));

            editTexts8[0].addTextChangedListener(new EdThreeAverage(editTexts8[0],editTexts8[1],editTexts8[2],ed94));
            editTexts8[1].addTextChangedListener(new EdThreeAverage(editTexts8[0],editTexts8[1],editTexts8[2],ed94));
            editTexts8[2].addTextChangedListener(new EdThreeAverage(editTexts8[0],editTexts8[1],editTexts8[2],ed94));

            editTexts9[0].addTextChangedListener(new EdThreeAverage(editTexts9[0],editTexts9[1],editTexts9[2],ed104));
            editTexts9[1].addTextChangedListener(new EdThreeAverage(editTexts9[0],editTexts9[1],editTexts9[2],ed104));
            editTexts9[2].addTextChangedListener(new EdThreeAverage(editTexts9[0],editTexts9[1],editTexts9[2],ed104));

            editTexts10[0].addTextChangedListener(new EdThreeAverage(editTexts10[0],editTexts10[1],editTexts10[2],ed114));
            editTexts10[1].addTextChangedListener(new EdThreeAverage(editTexts10[0],editTexts10[1],editTexts10[2],ed114));
            editTexts10[2].addTextChangedListener(new EdThreeAverage(editTexts10[0],editTexts10[1],editTexts10[2],ed114));
            //填充检测值
            editTexts1[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth1())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth1())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth1())[2]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth2())[0]);
            editTexts2[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth2())[1]);
            editTexts2[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth2())[2]);


            editTexts3[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth3())[0]);
            editTexts3[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth3())[1]);
            editTexts3[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth3())[2]);

            editTexts4[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth4())[0]);
            editTexts4[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth4())[1]);
            editTexts4[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth4())[2]);

            editTexts5[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth5())[0]);
            editTexts5[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth5())[1]);
            editTexts5[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth5())[2]);


            editTexts6[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth6())[0]);
            editTexts6[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth6())[1]);
            editTexts6[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth6())[2]);


            editTexts7[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth7())[0]);
            editTexts7[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth7())[1]);
            editTexts7[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth7())[2]);

            editTexts8[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth8())[0]);
            editTexts8[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth8())[1]);
            editTexts8[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth8())[2]);

            editTexts9[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth9())[0]);
            editTexts9[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth9())[1]);
            editTexts9[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth9())[2]);

            editTexts10[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth10())[0]);
            editTexts10[1].setText(EdUtil.split(sampleCheck.getCarbonation_depth10())[1]);
            editTexts10[2].setText(EdUtil.split(sampleCheck.getCarbonation_depth10())[2]);

            editTexts11[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth())[0]);

            editTexts12[0].setText(EdUtil.split(sampleCheck.getCarbonation_depth_avg())[0]);

            editTexts13[0].setText(EdUtil.split(sampleCheck.getSpringback_prediction())[0]);

            editTexts14[0].setText(EdUtil.split(sampleCheck.getRebound_file_name())[0]);//字段名字错了

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth2())[0].equals(""), ed31,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth2())[1].equals(""), ed32,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth2())[2].equals(""), ed33,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth3())[0].equals(""), ed41,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth3())[1].equals(""), ed42,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth3())[2].equals(""), ed43,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth4())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth4())[1].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth4())[2].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth5())[0].equals(""), ed61,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth5())[1].equals(""), ed62,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth5())[2].equals(""), ed63,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth6())[0].equals(""), ed71,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth6())[1].equals(""), ed72,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth6())[2].equals(""), ed73,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth7())[0].equals(""), ed81,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth7())[1].equals(""), ed82,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth7())[2].equals(""), ed83,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth8())[0].equals(""), ed91,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth8())[1].equals(""), ed92,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth8())[2].equals(""), ed93,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth9())[0].equals(""), ed101,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth9())[1].equals(""), ed102,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth9())[2].equals(""), ed103,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth10())[0].equals(""), ed111,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth10())[1].equals(""), ed112,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth10())[2].equals(""), ed113,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth())[0].equals(""), ed121,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getCarbonation_depth_avg())[0].equals(""), ed122,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSpringback_prediction())[0].equals(""), ed151,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getRebound_file_name())[0].equals(""), ed153,
                getContext(), sampleCheck.getId(), map, serialNumber);
            //设计
            //UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getStrength()), ed152);
        }
        //设计值对比监听

        //填充设计值
        ed152.setText(StrKit.notBlank(sample.getStrength() )? sample.getStrength() +"": "");


        //设计值保存监听
        ed152.addTextChangedListener(new EdDesignChangeSave("setStrength", sample, sample.getStrength() + "", ed152));


        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setCarbonation_depth1", sampleCheck, sampleCheck.getCarbonation_depth1(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setCarbonation_depth1", sampleCheck, sampleCheck.getCarbonation_depth1(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setCarbonation_depth1", sampleCheck, sampleCheck.getCarbonation_depth1(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setCarbonation_depth2", sampleCheck, sampleCheck.getCarbonation_depth2(), editTexts2));
        editTexts2[1].addTextChangedListener(new EdTextChange("setCarbonation_depth2", sampleCheck, sampleCheck.getCarbonation_depth2(), editTexts2));
        editTexts2[2].addTextChangedListener(new EdTextChange("setCarbonation_depth2", sampleCheck, sampleCheck.getCarbonation_depth2(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setCarbonation_depth3", sampleCheck, sampleCheck.getCarbonation_depth3(), editTexts3));
        editTexts3[1].addTextChangedListener(new EdTextChange("setCarbonation_depth3", sampleCheck, sampleCheck.getCarbonation_depth3(), editTexts3));
        editTexts3[2].addTextChangedListener(new EdTextChange("setCarbonation_depth3", sampleCheck, sampleCheck.getCarbonation_depth3(), editTexts3));

        editTexts4[0].addTextChangedListener(new EdTextChange("setCarbonation_depth4", sampleCheck, sampleCheck.getCarbonation_depth4(), editTexts4));
        editTexts4[1].addTextChangedListener(new EdTextChange("setCarbonation_depth4", sampleCheck, sampleCheck.getCarbonation_depth4(), editTexts4));
        editTexts4[2].addTextChangedListener(new EdTextChange("setCarbonation_depth4", sampleCheck, sampleCheck.getCarbonation_depth4(), editTexts4));

        editTexts5[0].addTextChangedListener(new EdTextChange("setCarbonation_depth5", sampleCheck, sampleCheck.getCarbonation_depth5(), editTexts5));
        editTexts5[1].addTextChangedListener(new EdTextChange("setCarbonation_depth5", sampleCheck, sampleCheck.getCarbonation_depth5(), editTexts5));
        editTexts5[2].addTextChangedListener(new EdTextChange("setCarbonation_depth5", sampleCheck, sampleCheck.getCarbonation_depth5(), editTexts5));

        editTexts6[0].addTextChangedListener(new EdTextChange("setCarbonation_depth6", sampleCheck, sampleCheck.getCarbonation_depth6(), editTexts6));
        editTexts6[1].addTextChangedListener(new EdTextChange("setCarbonation_depth6", sampleCheck, sampleCheck.getCarbonation_depth6(), editTexts6));
        editTexts6[2].addTextChangedListener(new EdTextChange("setCarbonation_depth6", sampleCheck, sampleCheck.getCarbonation_depth6(), editTexts6));

        editTexts7[0].addTextChangedListener(new EdTextChange("setCarbonation_depth7", sampleCheck, sampleCheck.getCarbonation_depth7(), editTexts7));
        editTexts7[1].addTextChangedListener(new EdTextChange("setCarbonation_depth7", sampleCheck, sampleCheck.getCarbonation_depth7(), editTexts7));
        editTexts7[2].addTextChangedListener(new EdTextChange("setCarbonation_depth7", sampleCheck, sampleCheck.getCarbonation_depth7(), editTexts7));

        editTexts8[0].addTextChangedListener(new EdTextChange("setCarbonation_depth8", sampleCheck, sampleCheck.getCarbonation_depth8(), editTexts8));
        editTexts8[1].addTextChangedListener(new EdTextChange("setCarbonation_depth8", sampleCheck, sampleCheck.getCarbonation_depth8(), editTexts8));
        editTexts8[2].addTextChangedListener(new EdTextChange("setCarbonation_depth8", sampleCheck, sampleCheck.getCarbonation_depth8(), editTexts8));

        editTexts9[0].addTextChangedListener(new EdTextChange("setCarbonation_depth9", sampleCheck, sampleCheck.getCarbonation_depth9(), editTexts9));
        editTexts9[1].addTextChangedListener(new EdTextChange("setCarbonation_depth9", sampleCheck, sampleCheck.getCarbonation_depth9(), editTexts9));
        editTexts9[2].addTextChangedListener(new EdTextChange("setCarbonation_depth9", sampleCheck, sampleCheck.getCarbonation_depth9(), editTexts9));

        editTexts10[0].addTextChangedListener(new EdTextChange("setCarbonation_depth10", sampleCheck, sampleCheck.getCarbonation_depth10(), editTexts10));
        editTexts10[1].addTextChangedListener(new EdTextChange("setCarbonation_depth10", sampleCheck, sampleCheck.getCarbonation_depth10(), editTexts10));
        editTexts10[2].addTextChangedListener(new EdTextChange("setCarbonation_depth10", sampleCheck, sampleCheck.getCarbonation_depth10(), editTexts10));

        editTexts11[0].addTextChangedListener(new EdTextChange("setCarbonation_depth", sampleCheck, sampleCheck.getCarbonation_depth(), editTexts11[0]));

        editTexts12[0].addTextChangedListener(new EdTextChange("setCarbonation_depth_avg", sampleCheck, sampleCheck.getCarbonation_depth_avg(),editTexts12[0]));

        editTexts13[0].addTextChangedListener(new EdTextChange("setSpringback_prediction", sampleCheck, sampleCheck.getSpringback_prediction(), editTexts13[0]));

        editTexts14[0].addTextChangedListener(new EdTextChange("setRebound_file_name", sampleCheck, sampleCheck.getRebound_file_name(), editTexts14[0]));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    //填充变更列名、序值
    private void fillMap(){
        map.put(ed21.getId(),"carbonation_depth");
        map.put(ed22.getId(),"carbonation_depth");
        map.put(ed23.getId(),"carbonation_depth");
        map.put(ed31.getId(),"carbonation_depth");
        map.put(ed32.getId(),"carbonation_depth");
        map.put(ed33.getId(),"carbonation_depth");
        map.put(ed41.getId(),"carbonation_depth");
        map.put(ed42.getId(),"carbonation_depth");
        map.put(ed43.getId(),"carbonation_depth");
        map.put(ed51.getId(),"carbonation_depth");
        map.put(ed52.getId(),"carbonation_depth");
        map.put(ed53.getId(),"carbonation_depth");
        map.put(ed61.getId(),"carbonation_depth");
        map.put(ed62.getId(),"carbonation_depth");
        map.put(ed63.getId(),"carbonation_depth");
        map.put(ed71.getId(),"carbonation_depth");
        map.put(ed72.getId(),"carbonation_depth");
        map.put(ed73.getId(),"carbonation_depth");
        map.put(ed81.getId(),"carbonation_depth");
        map.put(ed82.getId(),"carbonation_depth");
        map.put(ed83.getId(),"carbonation_depth");
        map.put(ed91.getId(),"carbonation_depth");
        map.put(ed92.getId(),"carbonation_depth");
        map.put(ed93.getId(),"carbonation_depth");
        map.put(ed101.getId(),"carbonation_depth");
        map.put(ed102.getId(),"carbonation_depth");
        map.put(ed103.getId(),"carbonation_depth");
        map.put(ed111.getId(),"carbonation_depth");
        map.put(ed112.getId(),"carbonation_depth");
        map.put(ed113.getId(),"carbonation_depth");
        map.put(ed121.getId(),"carbonation_depth");
        map.put(ed122.getId(),"carbonation_depth_avg");
        map.put(ed151.getId(),"springback_prediction");
        map.put(ed153.getId(),"rebound_file_name");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed31.getId(),"3");
        serialNumber.put(ed32.getId(),"4");
        serialNumber.put(ed33.getId(),"5");
        serialNumber.put(ed41.getId(),"6");
        serialNumber.put(ed42.getId(),"7");
        serialNumber.put(ed43.getId(),"8");
        serialNumber.put(ed51.getId(),"9");
        serialNumber.put(ed52.getId(),"10");
        serialNumber.put(ed53.getId(),"11");
        serialNumber.put(ed61.getId(),"12");
        serialNumber.put(ed62.getId(),"13");
        serialNumber.put(ed63.getId(),"14");
        serialNumber.put(ed71.getId(),"15");
        serialNumber.put(ed72.getId(),"16");
        serialNumber.put(ed73.getId(),"17");
        serialNumber.put(ed81.getId(),"18");
        serialNumber.put(ed82.getId(),"19");
        serialNumber.put(ed83.getId(),"20");
        serialNumber.put(ed91.getId(),"21");
        serialNumber.put(ed92.getId(),"22");
        serialNumber.put(ed93.getId(),"23");
        serialNumber.put(ed101.getId(),"24");
        serialNumber.put(ed102.getId(),"25");
        serialNumber.put(ed103.getId(),"26");
        serialNumber.put(ed111.getId(),"27");
        serialNumber.put(ed112.getId(),"28");
        serialNumber.put(ed113.getId(),"29");
        serialNumber.put(ed121.getId(),"0");
        serialNumber.put(ed122.getId(),"0");
        serialNumber.put(ed151.getId(),"0");
        serialNumber.put(ed153.getId(),"0");
    }
}
