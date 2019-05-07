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

public class InsQualityTHM extends LazyLoadFragment {

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
    @BindView(R.id.tv_4_5)
    TextView tv45;
    @BindView(R.id.tv_4_6)
    TextView tv46;
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
    @BindView(R.id.ed_5_5)
    EditText ed55;
    @BindView(R.id.ed_5_6)
    EditText ed56;
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
    @BindView(R.id.ed_11_1_remark)
    EditText ed111Remark;
    @BindView(R.id.ly_11_1)
    RelativeLayout ly111;
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
    private EditText[] editTexts15;
    private EditText[] editTexts16;
    private EditText[] editTexts17;
    private EditText[] editTexts18;
    private EditText[] editTexts19;
    private EditText[] editTexts20;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_azzl_thm;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1 = new EditText[]{ed21, ed22, ed23, ed24,ed25,ed26};
        editTexts2 = new EditText[]{ed51};
        editTexts3 = new EditText[]{ed52};
        editTexts4 = new EditText[]{ed53};
        editTexts5 = new EditText[]{ed54};
        editTexts6 = new EditText[]{ed55};
        editTexts7 = new EditText[]{ed56};
        editTexts8 = new EditText[]{ed61};
        editTexts9 = new EditText[]{ed62};
        editTexts10 = new EditText[]{ed63};
        editTexts11 = new EditText[]{ed64};
        editTexts12 = new EditText[]{ed65};
        editTexts13 = new EditText[]{ed66};
        editTexts14 = new EditText[]{ed71};
        editTexts15 = new EditText[]{ed101};
        editTexts16 = new EditText[]{ed102};
        editTexts17 = new EditText[]{ed103};
        editTexts18 = new EditText[]{ed104};
        editTexts19 = new EditText[]{ed105};
        editTexts20 = new EditText[]{ed106};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed24, ed25,
            ed26, ed51, ed52, ed53, ed54, ed55, ed56, ed61, ed62, ed63, ed64,
            ed65, ed66, ed71, ed101, ed102, ed103, ed104, ed105, ed106};

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
            editTexts1[0].setText(EdUtil.split(sampleCheck.getSurface_gap1())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getSurface_gap1())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getSurface_gap1())[2]);
            editTexts1[3].setText(EdUtil.split(sampleCheck.getSurface_gap1())[3]);
            editTexts1[4].setText(EdUtil.split(sampleCheck.getSurface_gap1())[4]);
            editTexts1[5].setText(EdUtil.split(sampleCheck.getSurface_gap1())[5]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getLm_fillister_left_top())[0]);

            editTexts3[0].setText(EdUtil.split(sampleCheck.getLm_fillister_left_center())[0]);

            editTexts4[0].setText(EdUtil.split(sampleCheck.getLm_fillister_left_bottom())[0]);

            editTexts5[0].setText(EdUtil.split(sampleCheck.getLm_fillister_right_top())[0]);

            editTexts6[0].setText(EdUtil.split(sampleCheck.getLm_fillister_right_center())[0]);

            editTexts7[0].setText(EdUtil.split(sampleCheck.getLm_fillister_right_bottom())[0]);

            editTexts8[0].setText(EdUtil.split(sampleCheck.getLm_indentation_left_top())[0]);

            editTexts9[0].setText(EdUtil.split(sampleCheck.getLm_indentation_left_center())[0]);

            editTexts10[0].setText(EdUtil.split(sampleCheck.getLm_indentation_left_bottom())[0]);

            editTexts11[0].setText(EdUtil.split(sampleCheck.getLm_indentation_right_top())[0]);

            editTexts12[0].setText(EdUtil.split(sampleCheck.getLm_indentation_right_center())[0]);

            editTexts13[0].setText(EdUtil.split(sampleCheck.getLm_indentation_right_bottom())[0]);

            editTexts14[0].setText(EdUtil.split(sampleCheck.getPanel_thickness())[0]);

            editTexts15[0].setText(EdUtil.split(sampleCheck.getBlm_fillister_left_top())[0]);

            editTexts16[0].setText(EdUtil.split(sampleCheck.getBlm_fillister_left_center())[0]);

            editTexts17[0].setText(EdUtil.split(sampleCheck.getBlm_fillister_left_bottom())[0]);

            editTexts18[0].setText(EdUtil.split(sampleCheck.getBlm_fillister_right_top())[0]);

            editTexts19[0].setText(EdUtil.split(sampleCheck.getBlm_fillister_right_center())[0]);

            editTexts20[0].setText(EdUtil.split(sampleCheck.getBlm_fillister_right_bottom())[0]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSurface_gap1())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSurface_gap1())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSurface_gap1())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSurface_gap1())[3].equals(""), ed24,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSurface_gap1())[4].equals(""), ed25,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSurface_gap1())[5].equals(""), ed26,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_fillister_left_top())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_fillister_left_center())[0].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_fillister_left_bottom())[0].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_fillister_right_top())[0].equals(""), ed54,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_fillister_right_center())[0].equals(""), ed55,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_fillister_right_bottom())[0].equals(""), ed56,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_indentation_left_top())[0].equals(""), ed61,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_indentation_left_center())[0].equals(""), ed62,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_indentation_left_bottom())[0].equals(""), ed63,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_indentation_right_top())[0].equals(""), ed64,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_indentation_right_center())[0].equals(""), ed65,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLm_indentation_right_bottom())[0].equals(""), ed66,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getPanel_thickness())[0].equals(""), ed71,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBlm_fillister_left_top())[0].equals(""), ed101,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBlm_fillister_left_center())[0].equals(""), ed102,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBlm_fillister_left_bottom())[0].equals(""), ed103,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBlm_fillister_right_top())[0].equals(""), ed104,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBlm_fillister_right_center())[0].equals(""), ed105,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getBlm_fillister_right_bottom())[0].equals(""), ed106,
                getContext(), sampleCheck.getId(), map, serialNumber);
        }

        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setSurface_gap1", sampleCheck, sampleCheck.getSurface_gap1(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setSurface_gap1", sampleCheck, sampleCheck.getSurface_gap1(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setSurface_gap1", sampleCheck, sampleCheck.getSurface_gap1(), editTexts1));
        editTexts1[3].addTextChangedListener(new EdTextChange("setSurface_gap1", sampleCheck, sampleCheck.getSurface_gap1(), editTexts1));
        editTexts1[4].addTextChangedListener(new EdTextChange("setSurface_gap1", sampleCheck, sampleCheck.getSurface_gap1(), editTexts1));
        editTexts1[5].addTextChangedListener(new EdTextChange("setSurface_gap1", sampleCheck, sampleCheck.getSurface_gap1(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setLm_fillister_left_top", sampleCheck, sampleCheck.getLm_fillister_left_top(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setLm_fillister_left_center", sampleCheck, sampleCheck.getLm_fillister_left_center(), editTexts3));

        editTexts4[0].addTextChangedListener(new EdTextChange("setLm_fillister_left_bottom", sampleCheck, sampleCheck.getLm_fillister_left_bottom(), editTexts4));

        editTexts5[0].addTextChangedListener(new EdTextChange("setLm_fillister_right_top", sampleCheck, sampleCheck.getLm_fillister_right_top(), editTexts5));

        editTexts6[0].addTextChangedListener(new EdTextChange("setLm_fillister_right_center", sampleCheck, sampleCheck.getLm_fillister_right_center(), editTexts6));

        editTexts7[0].addTextChangedListener(new EdTextChange("setLm_fillister_right_bottom", sampleCheck, sampleCheck.getLm_fillister_right_bottom(), editTexts7));

        editTexts8[0].addTextChangedListener(new EdTextChange("setLm_indentation_left_top", sampleCheck, sampleCheck.getLm_indentation_left_top(), editTexts8));

        editTexts9[0].addTextChangedListener(new EdTextChange("setLm_indentation_left_center", sampleCheck, sampleCheck.getLm_indentation_left_center(), editTexts9));

        editTexts10[0].addTextChangedListener(new EdTextChange("setLm_indentation_left_bottom", sampleCheck, sampleCheck.getLm_indentation_left_bottom(), editTexts10));

        editTexts11[0].addTextChangedListener(new EdTextChange("setLm_indentation_right_top", sampleCheck, sampleCheck.getLm_indentation_right_top(), editTexts11));

        editTexts12[0].addTextChangedListener(new EdTextChange("setLm_indentation_right_center", sampleCheck, sampleCheck.getLm_indentation_right_center(), editTexts12));

        editTexts13[0].addTextChangedListener(new EdTextChange("setLm_indentation_right_bottom", sampleCheck, sampleCheck.getLm_indentation_right_bottom(), editTexts13));

        editTexts14[0].addTextChangedListener(new EdTextChange("setPanel_thickness", sampleCheck, sampleCheck.getPanel_thickness(), editTexts14));

        editTexts15[0].addTextChangedListener(new EdTextChange("setBlm_fillister_left_top", sampleCheck, sampleCheck.getBlm_fillister_left_top(), editTexts15));

        editTexts16[0].addTextChangedListener(new EdTextChange("setBlm_fillister_left_center", sampleCheck, sampleCheck.getBlm_fillister_left_center(), editTexts16));

        editTexts17[0].addTextChangedListener(new EdTextChange("setBlm_fillister_left_bottom", sampleCheck, sampleCheck.getBlm_fillister_left_bottom(), editTexts17));

        editTexts18[0].addTextChangedListener(new EdTextChange("setBlm_fillister_right_top", sampleCheck, sampleCheck.getBlm_fillister_right_top(), editTexts18));

        editTexts19[0].addTextChangedListener(new EdTextChange("setBlm_fillister_right_center", sampleCheck, sampleCheck.getBlm_fillister_right_center(), editTexts19));

        editTexts20[0].addTextChangedListener(new EdTextChange("setBlm_fillister_right_bottom", sampleCheck, sampleCheck.getBlm_fillister_right_bottom(), editTexts20));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"surface_gap");
        map.put(ed22.getId(),"surface_gap");
        map.put(ed23.getId(),"surface_gap");
        map.put(ed24.getId(),"surface_gap");
        map.put(ed25.getId(),"surface_gap");
        map.put(ed26.getId(),"surface_gap");
        map.put(ed51.getId(),"lm_fillister_left_top");
        map.put(ed52.getId(),"lm_fillister_left_center");
        map.put(ed53.getId(),"lm_fillister_left_bottom");
        map.put(ed54.getId(),"lm_fillister_right_top");
        map.put(ed55.getId(),"lm_fillister_right_center");
        map.put(ed56.getId(),"lm_fillister_right_bottom");
        map.put(ed61.getId(),"lm_indentation_left_top");
        map.put(ed62.getId(),"lm_indentation_left_center");
        map.put(ed63.getId(),"lm_indentation_left_bottom");
        map.put(ed64.getId(),"lm_indentation_right_top");
        map.put(ed65.getId(),"lm_indentation_right_center");
        map.put(ed66.getId(),"lm_indentation_right_bottom");
        map.put(ed71.getId(),"panel_thickness");
        map.put(ed101.getId(),"blm_fillister_left_top");
        map.put(ed102.getId(),"blm_fillister_left_center");
        map.put(ed103.getId(),"blm_fillister_left_bottom");
        map.put(ed104.getId(),"blm_fillister_right_top");
        map.put(ed105.getId(),"blm_fillister_right_center");
        map.put(ed106.getId(),"blm_fillister_right_bottom");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed24.getId(),"3");
        serialNumber.put(ed25.getId(),"4");
        serialNumber.put(ed26.getId(),"5");
        serialNumber.put(ed51.getId(),"0");
        serialNumber.put(ed52.getId(),"0");
        serialNumber.put(ed53.getId(),"0");
        serialNumber.put(ed54.getId(),"0");
        serialNumber.put(ed55.getId(),"0");
        serialNumber.put(ed56.getId(),"0");
        serialNumber.put(ed61.getId(),"0");
        serialNumber.put(ed62.getId(),"0");
        serialNumber.put(ed63.getId(),"0");
        serialNumber.put(ed64.getId(),"0");
        serialNumber.put(ed65.getId(),"0");
        serialNumber.put(ed66.getId(),"0");
        serialNumber.put(ed71.getId(),"0");
        serialNumber.put(ed101.getId(),"0");
        serialNumber.put(ed102.getId(),"0");
        serialNumber.put(ed103.getId(),"0");
        serialNumber.put(ed104.getId(),"0");
        serialNumber.put(ed105.getId(),"0");
        serialNumber.put(ed106.getId(),"0");

    }
}