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

public class InsQualityCZD extends LazyLoadFragment {

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
    @BindView(R.id.ly_7_1)
    RelativeLayout ly71;
    @BindView(R.id.tv_title_8_1)
    TextView tvTitle81;
    @BindView(R.id.ed_8_1_remark)
    EditText ed81Remark;
    @BindView(R.id.ly_8_1)
    RelativeLayout ly81;
    Unbinder unbinder;
    private EditText[] editTexts1;
    private EditText[] editTexts2;
    private EditText[] editTexts3;
    private EditText[] editTexts4;
    private EditText[] editTexts5;
    private EditText[] editTexts6;
    private EditText[] editTexts7;
    private EditText[] editTexts8;
    private EditText[] editTextsAll;
    Map<Integer,String> map = new HashMap<>();
    Map<Integer,String> serialNumber = new HashMap<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_azzl_czd;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);

        editTexts1 = new EditText[]{ed21};
        editTexts2 = new EditText[]{ed22};
        editTexts3 = new EditText[]{ed23};
        editTexts4 = new EditText[]{ed51,ed52};
        editTexts5 = new EditText[]{ed53,ed54};
        editTexts6 = new EditText[]{ed61,ed62};
        editTexts7 = new EditText[]{ed63,ed64};
        editTexts8 = new EditText[]{ed71};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed51, ed52,
            ed53, ed54, ed61, ed62, ed63, ed64, ed71};

        fillMap();

       /* Sample tentsample = (Sample) getActivity().getIntent().getSerializableExtra("sample");
        Sample sample = GreenDaoManager.getInstance().getNewSession()
                .getSampleDao().queryBuilder().where(SampleDao.Properties.Id.eq(tentsample.getId())).unique();
        SampleCheck sampleCheck = GreenDaoManager.getInstance().getNewSession()
                .getSampleCheckDao().queryBuilder().where(SampleCheckDao.Properties.Id.eq(tentsample.getId())).unique();*/
        Common common = new Common();
        SampleCheck sampleCheck = common.getSampleCheck();
        Sample sample = common.getSample();
        if (sampleCheck != null) {
            //填充检测值
            editTexts1[0].setText(EdUtil.split(sampleCheck.getHinge_axis_perpendicularity_bothway())[0]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getHinge_axis_perpendicularity_vertical())[0]);

            editTexts3[0].setText(EdUtil.split(sampleCheck.getHinge_axis_perpendicularity_parallel())[0]);

            editTexts4[0].setText(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality1())[0]);
            editTexts4[1].setText(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality1())[1]);

            editTexts5[0].setText(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality2())[0]);
            editTexts5[1].setText(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality2())[1]);

            editTexts6[0].setText(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality3())[0]);
            editTexts6[1].setText(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality3())[1]);

            editTexts7[0].setText(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality4())[0]);
            editTexts7[1].setText(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality4())[1]);

            editTexts8[0].setText(EdUtil.split(sampleCheck.getLine_hammer_specifications())[0]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHinge_axis_perpendicularity_bothway())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHinge_axis_perpendicularity_vertical())[0].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHinge_axis_perpendicularity_parallel())[0].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality1())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality1())[1].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality2())[0].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality2())[1].equals(""), ed54,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality3())[0].equals(""), ed61,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality3())[1].equals(""), ed62,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality4())[0].equals(""), ed63,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFrame_inter_wall_verticality4())[1].equals(""), ed64,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getLine_hammer_specifications())[0].equals(""), ed71,
                getContext(), sampleCheck.getId(), map, serialNumber);
        }

        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setHinge_axis_perpendicularity_bothway", sampleCheck, sampleCheck.getHinge_axis_perpendicularity_bothway(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setHinge_axis_perpendicularity_vertical", sampleCheck, sampleCheck.getHinge_axis_perpendicularity_vertical(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setHinge_axis_perpendicularity_parallel", sampleCheck, sampleCheck.getHinge_axis_perpendicularity_parallel(), editTexts3));

        editTexts4[0].addTextChangedListener(new EdTextChange("setFrame_inter_wall_verticality1", sampleCheck, sampleCheck.getFrame_inter_wall_verticality1(), editTexts4));
        editTexts4[1].addTextChangedListener(new EdTextChange("setFrame_inter_wall_verticality1", sampleCheck, sampleCheck.getFrame_inter_wall_verticality1(), editTexts4));

        editTexts5[0].addTextChangedListener(new EdTextChange("setFrame_inter_wall_verticality2", sampleCheck, sampleCheck.getFrame_inter_wall_verticality2(), editTexts5));
        editTexts5[1].addTextChangedListener(new EdTextChange("setFrame_inter_wall_verticality2", sampleCheck, sampleCheck.getFrame_inter_wall_verticality2(), editTexts5));

        editTexts6[0].addTextChangedListener(new EdTextChange("setFrame_inter_wall_verticality3", sampleCheck, sampleCheck.getFrame_inter_wall_verticality3(), editTexts6));
        editTexts6[1].addTextChangedListener(new EdTextChange("setFrame_inter_wall_verticality3", sampleCheck, sampleCheck.getFrame_inter_wall_verticality3(), editTexts6));

        editTexts7[0].addTextChangedListener(new EdTextChange("setFrame_inter_wall_verticality4", sampleCheck, sampleCheck.getFrame_inter_wall_verticality4(), editTexts7));
        editTexts7[1].addTextChangedListener(new EdTextChange("setFrame_inter_wall_verticality4", sampleCheck, sampleCheck.getFrame_inter_wall_verticality4(), editTexts7));

        editTexts8[0].addTextChangedListener(new EdTextChange("setLine_hammer_specifications", sampleCheck, sampleCheck.getLine_hammer_specifications(), editTexts8));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"hinge_axis_perpendicularity_bothway");
        map.put(ed22.getId(),"hinge_axis_perpendicularity_vertical");
        map.put(ed23.getId(),"hinge_axis_perpendicularity_parallel");
        map.put(ed51.getId(),"frame_inter_wall_verticality");
        map.put(ed52.getId(),"frame_inter_wall_verticality");
        map.put(ed53.getId(),"frame_inter_wall_verticality");
        map.put(ed54.getId(),"frame_inter_wall_verticality");
        map.put(ed61.getId(),"frame_inter_wall_verticality");
        map.put(ed62.getId(),"frame_inter_wall_verticality");
        map.put(ed63.getId(),"frame_inter_wall_verticality");
        map.put(ed64.getId(),"frame_inter_wall_verticality");
        map.put(ed71.getId(),"line_hammer_specifications");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"0");
        serialNumber.put(ed23.getId(),"0");
        serialNumber.put(ed51.getId(),"0");
        serialNumber.put(ed52.getId(),"1");
        serialNumber.put(ed53.getId(),"0");
        serialNumber.put(ed54.getId(),"1");
        serialNumber.put(ed61.getId(),"0");
        serialNumber.put(ed62.getId(),"1");
        serialNumber.put(ed63.getId(),"0");
        serialNumber.put(ed64.getId(),"1");
        serialNumber.put(ed71.getId(),"0");
    }
}
