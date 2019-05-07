package com.zhilian.rf_qims.mvp.sample_data.pro_quality;


import android.view.View;
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

public class ProQualityJGZL extends LazyLoadFragment {


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
    @BindView(R.id.tv_1_7)
    TextView tv17;
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
    @BindView(R.id.ed_2_7)
    EditText ed27;
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
    @BindView(R.id.ed_3_5)
    EditText ed35;
    @BindView(R.id.ed_3_6)
    EditText ed36;
    @BindView(R.id.ly_3_1)
    RelativeLayout ly31;
    @BindView(R.id.tv_title_4_1)
    TextView tvTitle41;
    @BindView(R.id.ed_4_1_remark)
    EditText ed41Remark;
    @BindView(R.id.ly_4_1)
    RelativeLayout ly41;
    @BindView(R.id.tv_title_5_1)
    TextView tvTitle51;
    @BindView(R.id.tv_5_1)
    TextView tv51;
    @BindView(R.id.tv_5_2)
    TextView tv52;
    @BindView(R.id.tv_5_3)
    TextView tv53;
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
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.ly_8_1)
    RelativeLayout ly81;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_sczl_jgzl;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1 = new EditText[]{ed21, ed22, ed23, ed24, ed25, ed26};
        editTexts2 = new EditText[]{ed31, ed32, ed33, ed34, ed35, ed36};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed24, ed25,
            ed26, ed31, ed32, ed33, ed34,
            ed35, ed36};

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
            editTexts1[0].setText(EdUtil.split(sampleCheck.getLeg_height_vertical1())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getLeg_height_vertical1())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getLeg_height_vertical1())[2]);
            editTexts1[3].setText(EdUtil.split(sampleCheck.getLeg_height_vertical1())[3]);
            editTexts1[4].setText(EdUtil.split(sampleCheck.getLeg_height_vertical1())[4]);
            editTexts1[5].setText(EdUtil.split(sampleCheck.getLeg_height_vertical1())[5]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getLeg_height_across1())[0]);
            editTexts2[1].setText(EdUtil.split(sampleCheck.getLeg_height_across1())[1]);
            editTexts2[2].setText(EdUtil.split(sampleCheck.getLeg_height_across1())[2]);
            editTexts2[3].setText(EdUtil.split(sampleCheck.getLeg_height_across1())[3]);
            editTexts2[4].setText(EdUtil.split(sampleCheck.getLeg_height_across1())[4]);
            editTexts2[5].setText(EdUtil.split(sampleCheck.getLeg_height_across1())[5]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_vertical1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_vertical1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_vertical1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_vertical1())[3].equals(""), ed24,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_vertical1())[4].equals(""), ed25,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_vertical1())[5].equals(""), ed26,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_across1())[0].equals(""), ed31,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_across1())[1].equals(""), ed32,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_across1())[2].equals(""), ed33,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_across1())[3].equals(""), ed34,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_across1())[4].equals(""), ed35,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLeg_height_across1())[5].equals(""), ed36,
                getContext(), sampleCheck.getId(), map, serialNumber);
            //设计
            //UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getLegHeightAcross()), ed27);

            //设计值对比监听
            ed27.addTextChangedListener(new EdCompareDesign(editTexts1[0], ed27));
            ed27.addTextChangedListener(new EdCompareDesign(editTexts1[1], ed27));
            ed27.addTextChangedListener(new EdCompareDesign(editTexts1[2], ed27));
            ed27.addTextChangedListener(new EdCompareDesign(editTexts1[3], ed27));
            ed27.addTextChangedListener(new EdCompareDesign(editTexts1[4], ed27));
            ed27.addTextChangedListener(new EdCompareDesign(editTexts1[5], ed27));

            editTexts1[0].addTextChangedListener(new EdCompareDesign(editTexts1[0], ed27));
            editTexts1[1].addTextChangedListener(new EdCompareDesign(editTexts1[1], ed27));
            editTexts1[2].addTextChangedListener(new EdCompareDesign(editTexts1[2], ed27));
            editTexts1[3].addTextChangedListener(new EdCompareDesign(editTexts1[3], ed27));
            editTexts1[4].addTextChangedListener(new EdCompareDesign(editTexts1[4], ed27));
            editTexts1[5].addTextChangedListener(new EdCompareDesign(editTexts1[5], ed27));
        }
        //填充设计值
        ed27.setText(StrKit.notBlank(sample.getLegHeightAcross()) ? sample.getLegHeightAcross() + "" : "");


        //设计值保存监听
        ed27.addTextChangedListener(new EdDesignChangeSave("setLegHeightAcross", sample, sample.getLegHeightAcross() + "", ed27));

        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setLeg_height_vertical1", sampleCheck, sampleCheck.getLeg_height_vertical1(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setLeg_height_vertical1", sampleCheck, sampleCheck.getLeg_height_vertical1(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setLeg_height_vertical1", sampleCheck, sampleCheck.getLeg_height_vertical1(), editTexts1));
        editTexts1[3].addTextChangedListener(new EdTextChange("setLeg_height_vertical1", sampleCheck, sampleCheck.getLeg_height_vertical1(), editTexts1));
        editTexts1[4].addTextChangedListener(new EdTextChange("setLeg_height_vertical1", sampleCheck, sampleCheck.getLeg_height_vertical1(), editTexts1));
        editTexts1[5].addTextChangedListener(new EdTextChange("setLeg_height_vertical1", sampleCheck, sampleCheck.getLeg_height_vertical1(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setLeg_height_across1", sampleCheck, sampleCheck.getLeg_height_across1(), editTexts2));
        editTexts2[1].addTextChangedListener(new EdTextChange("setLeg_height_across1", sampleCheck, sampleCheck.getLeg_height_across1(), editTexts2));
        editTexts2[2].addTextChangedListener(new EdTextChange("setLeg_height_across1", sampleCheck, sampleCheck.getLeg_height_across1(), editTexts2));
        editTexts2[3].addTextChangedListener(new EdTextChange("setLeg_height_across1", sampleCheck, sampleCheck.getLeg_height_across1(), editTexts2));
        editTexts2[4].addTextChangedListener(new EdTextChange("setLeg_height_across1", sampleCheck, sampleCheck.getLeg_height_across1(), editTexts2));
        editTexts2[5].addTextChangedListener(new EdTextChange("setLeg_height_across1", sampleCheck, sampleCheck.getLeg_height_across1(), editTexts2));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"leg_height_vertical");
        map.put(ed22.getId(),"leg_height_vertical");
        map.put(ed23.getId(),"leg_height_vertical");
        map.put(ed24.getId(),"leg_height_vertical");
        map.put(ed25.getId(),"leg_height_vertical");
        map.put(ed26.getId(),"leg_height_vertical");
        map.put(ed31.getId(),"leg_height_across");
        map.put(ed32.getId(),"leg_height_across");
        map.put(ed33.getId(),"leg_height_across");
        map.put(ed34.getId(),"leg_height_across");
        map.put(ed35.getId(),"leg_height_across");
        map.put(ed36.getId(),"leg_height_across");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed24.getId(),"3");
        serialNumber.put(ed25.getId(),"4");
        serialNumber.put(ed26.getId(),"5");
        serialNumber.put(ed31.getId(),"0");
        serialNumber.put(ed32.getId(),"1");
        serialNumber.put(ed33.getId(),"2");
        serialNumber.put(ed34.getId(),"3");
        serialNumber.put(ed35.getId(),"4");
        serialNumber.put(ed36.getId(),"5");
    }
}
