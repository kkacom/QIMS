package com.zhilian.rf_qims.mvp.sample_data.pro_quality;


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

public class ProQualityQMZL extends LazyLoadFragment {

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
    @BindView(R.id.tv_7_4)
    TextView tv74;
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
        return R.layout.fragment_sczl_qmzl;
    }

    @Override
    protected void lazyLoad() {
        unbinder = ButterKnife.bind(this, view);
        editTexts1 = new EditText[]{ed21, ed22, ed23, ed24};
        editTexts2 = new EditText[]{ed31, ed32, ed33};
        editTexts3 = new EditText[]{ed41, ed42, ed43, ed44};
        editTexts4 = new EditText[]{ed51, ed52, ed53};
        editTexts5 = new EditText[]{ed81, ed82, ed83, ed84};
        editTexts6 = new EditText[]{ed91, ed92, ed93};
        editTexts7 = new EditText[]{ed101, ed102, ed103, ed104};
        editTexts8 = new EditText[]{ed111, ed112, ed113};
        editTextsAll = new EditText[]{ed21, ed22, ed23, ed24, ed31,
            ed32, ed33, ed41, ed42, ed43, ed44, ed51, ed52, ed53, ed81, ed82,
            ed83, ed84, ed91, ed92, ed93, ed101, ed102, ed103, ed104, ed111, ed112, ed113};

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
            editTexts1[0].setText(EdUtil.split(sampleCheck.getFilm_thickness11())[0]);
            editTexts1[1].setText(EdUtil.split(sampleCheck.getFilm_thickness11())[1]);
            editTexts1[2].setText(EdUtil.split(sampleCheck.getFilm_thickness11())[2]);
            editTexts1[3].setText(EdUtil.split(sampleCheck.getFilm_thickness11())[3]);

            editTexts2[0].setText(EdUtil.split(sampleCheck.getFilm_thickness21())[0]);
            editTexts2[1].setText(EdUtil.split(sampleCheck.getFilm_thickness21())[1]);
            editTexts2[2].setText(EdUtil.split(sampleCheck.getFilm_thickness21())[2]);

            editTexts3[0].setText(EdUtil.split(sampleCheck.getFilm_thickness31())[0]);
            editTexts3[1].setText(EdUtil.split(sampleCheck.getFilm_thickness31())[1]);
            editTexts3[2].setText(EdUtil.split(sampleCheck.getFilm_thickness31())[2]);
            editTexts3[3].setText(EdUtil.split(sampleCheck.getFilm_thickness31())[3]);

            editTexts4[0].setText(EdUtil.split(sampleCheck.getFilm_thickness41())[0]);
            editTexts4[1].setText(EdUtil.split(sampleCheck.getFilm_thickness41())[1]);
            editTexts4[2].setText(EdUtil.split(sampleCheck.getFilm_thickness41())[2]);

            editTexts5[0].setText(EdUtil.split(sampleCheck.getFilm_adhesion11())[0]);
            editTexts5[1].setText(EdUtil.split(sampleCheck.getFilm_adhesion11())[1]);
            editTexts5[2].setText(EdUtil.split(sampleCheck.getFilm_adhesion11())[2]);
            editTexts5[3].setText(EdUtil.split(sampleCheck.getFilm_adhesion11())[3]);

            editTexts6[0].setText(EdUtil.split(sampleCheck.getFilm_adhesion21())[0]);
            editTexts6[1].setText(EdUtil.split(sampleCheck.getFilm_adhesion21())[1]);
            editTexts6[2].setText(EdUtil.split(sampleCheck.getFilm_adhesion21())[2]);

            editTexts7[0].setText(EdUtil.split(sampleCheck.getFilm_adhesion31())[0]);
            editTexts7[1].setText(EdUtil.split(sampleCheck.getFilm_adhesion31())[1]);
            editTexts7[2].setText(EdUtil.split(sampleCheck.getFilm_adhesion31())[2]);
            editTexts7[3].setText(EdUtil.split(sampleCheck.getFilm_adhesion31())[3]);

            editTexts8[0].setText(EdUtil.split(sampleCheck.getFilm_adhesion41())[0]);
            editTexts8[1].setText(EdUtil.split(sampleCheck.getFilm_adhesion41())[1]);
            editTexts8[2].setText(EdUtil.split(sampleCheck.getFilm_adhesion41())[2]);

            //判断检测值是否有值设置不可编辑(2019.3.26)
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness11())[0].equals(""), ed21,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness11())[1].equals(""), ed22,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness11())[2].equals(""), ed23,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness11())[3].equals(""), ed24,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness21())[0].equals(""), ed31,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness21())[1].equals(""), ed32,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness21())[2].equals(""), ed33,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness31())[0].equals(""), ed41,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness31())[1].equals(""), ed42,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness31())[2].equals(""), ed43,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness31())[3].equals(""), ed44,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness41())[0].equals(""), ed51,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness41())[1].equals(""), ed52,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_thickness41())[1].equals(""), ed53,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion11())[0].equals(""), ed81,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion11())[1].equals(""), ed82,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion11())[2].equals(""), ed83,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion11())[3].equals(""), ed84,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion21())[0].equals(""), ed91,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion21())[1].equals(""), ed92,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion21())[2].equals(""), ed93,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion31())[0].equals(""), ed101,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion31())[1].equals(""), ed102,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion31())[2].equals(""), ed103,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion31())[3].equals(""), ed104,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion41())[0].equals(""), ed111,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion41())[1].equals(""), ed112,
                getContext(), sampleCheck.getId(), map, serialNumber);
            UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getFilm_adhesion41())[2].equals(""), ed113,
                getContext(), sampleCheck.getId(), map, serialNumber);
        }


        //检测值保存监听
        editTexts1[0].addTextChangedListener(new EdTextChange("setFilm_thickness11", sampleCheck, sampleCheck.getFilm_thickness11(), editTexts1));
        editTexts1[1].addTextChangedListener(new EdTextChange("setFilm_thickness11", sampleCheck, sampleCheck.getFilm_thickness11(), editTexts1));
        editTexts1[2].addTextChangedListener(new EdTextChange("setFilm_thickness11", sampleCheck, sampleCheck.getFilm_thickness11(), editTexts1));
        editTexts1[3].addTextChangedListener(new EdTextChange("setFilm_thickness11", sampleCheck, sampleCheck.getFilm_thickness11(), editTexts1));

        editTexts2[0].addTextChangedListener(new EdTextChange("setFilm_thickness21", sampleCheck, sampleCheck.getFilm_thickness21(), editTexts2));
        editTexts2[1].addTextChangedListener(new EdTextChange("setFilm_thickness21", sampleCheck, sampleCheck.getFilm_thickness21(), editTexts2));
        editTexts2[2].addTextChangedListener(new EdTextChange("setFilm_thickness21", sampleCheck, sampleCheck.getFilm_thickness21(), editTexts2));

        editTexts3[0].addTextChangedListener(new EdTextChange("setFilm_thickness31", sampleCheck, sampleCheck.getFilm_thickness31(), editTexts3));
        editTexts3[1].addTextChangedListener(new EdTextChange("setFilm_thickness31", sampleCheck, sampleCheck.getFilm_thickness31(), editTexts3));
        editTexts3[2].addTextChangedListener(new EdTextChange("setFilm_thickness31", sampleCheck, sampleCheck.getFilm_thickness31(), editTexts3));
        editTexts3[3].addTextChangedListener(new EdTextChange("setFilm_thickness31", sampleCheck, sampleCheck.getFilm_thickness31(), editTexts3));

        editTexts4[0].addTextChangedListener(new EdTextChange("setFilm_thickness41", sampleCheck, sampleCheck.getFilm_thickness41(), editTexts4));
        editTexts4[1].addTextChangedListener(new EdTextChange("setFilm_thickness41", sampleCheck, sampleCheck.getFilm_thickness41(), editTexts4));
        editTexts4[2].addTextChangedListener(new EdTextChange("setFilm_thickness41", sampleCheck, sampleCheck.getFilm_thickness41(), editTexts4));

        editTexts5[0].addTextChangedListener(new EdTextChange("setFilm_adhesion11", sampleCheck, sampleCheck.getFilm_adhesion11(), editTexts5));
        editTexts5[1].addTextChangedListener(new EdTextChange("setFilm_adhesion11", sampleCheck, sampleCheck.getFilm_adhesion11(), editTexts5));
        editTexts5[2].addTextChangedListener(new EdTextChange("setFilm_adhesion11", sampleCheck, sampleCheck.getFilm_adhesion11(), editTexts5));
        editTexts5[3].addTextChangedListener(new EdTextChange("setFilm_adhesion11", sampleCheck, sampleCheck.getFilm_adhesion11(), editTexts5));

        editTexts6[0].addTextChangedListener(new EdTextChange("setFilm_adhesion21", sampleCheck, sampleCheck.getFilm_adhesion21(), editTexts6));
        editTexts6[1].addTextChangedListener(new EdTextChange("setFilm_adhesion21", sampleCheck, sampleCheck.getFilm_adhesion21(), editTexts6));
        editTexts6[2].addTextChangedListener(new EdTextChange("setFilm_adhesion21", sampleCheck, sampleCheck.getFilm_adhesion21(), editTexts6));

        editTexts7[0].addTextChangedListener(new EdTextChange("setFilm_adhesion31", sampleCheck, sampleCheck.getFilm_adhesion31(), editTexts7));
        editTexts7[1].addTextChangedListener(new EdTextChange("setFilm_adhesion31", sampleCheck, sampleCheck.getFilm_adhesion31(), editTexts7));
        editTexts7[2].addTextChangedListener(new EdTextChange("setFilm_adhesion31", sampleCheck, sampleCheck.getFilm_adhesion31(), editTexts7));
        editTexts7[3].addTextChangedListener(new EdTextChange("setFilm_adhesion31", sampleCheck, sampleCheck.getFilm_adhesion31(), editTexts7));

        editTexts8[0].addTextChangedListener(new EdTextChange("setFilm_adhesion41", sampleCheck, sampleCheck.getFilm_adhesion41(), editTexts8));
        editTexts8[1].addTextChangedListener(new EdTextChange("setFilm_adhesion41", sampleCheck, sampleCheck.getFilm_adhesion41(), editTexts8));
        editTexts8[2].addTextChangedListener(new EdTextChange("setFilm_adhesion41", sampleCheck, sampleCheck.getFilm_adhesion41(), editTexts8));

        UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
    }

    private void fillMap(){
        map.put(ed21.getId(),"film_thickness");
        map.put(ed22.getId(),"film_thickness");
        map.put(ed23.getId(),"film_thickness");
        map.put(ed24.getId(),"film_thickness");
        map.put(ed31.getId(),"film_thickness");
        map.put(ed32.getId(),"film_thickness");
        map.put(ed33.getId(),"film_thickness");
        map.put(ed41.getId(),"film_thickness");
        map.put(ed42.getId(),"film_thickness");
        map.put(ed43.getId(),"film_thickness");
        map.put(ed44.getId(),"film_thickness");
        map.put(ed51.getId(),"film_thickness");
        map.put(ed52.getId(),"film_thickness");
        map.put(ed53.getId(),"film_thickness");
        map.put(ed81.getId(),"film_adhesion");
        map.put(ed82.getId(),"film_adhesion");
        map.put(ed83.getId(),"film_adhesion");
        map.put(ed84.getId(),"film_adhesion");
        map.put(ed91.getId(),"film_adhesion");
        map.put(ed92.getId(),"film_adhesion");
        map.put(ed93.getId(),"film_adhesion");
        map.put(ed101.getId(),"film_adhesion");
        map.put(ed102.getId(),"film_adhesion");
        map.put(ed103.getId(),"film_adhesion");
        map.put(ed104.getId(),"film_adhesion");
        map.put(ed111.getId(),"film_adhesion");
        map.put(ed112.getId(),"film_adhesion");
        map.put(ed113.getId(),"film_adhesion");

        serialNumber.put(ed21.getId(),"0");
        serialNumber.put(ed22.getId(),"1");
        serialNumber.put(ed23.getId(),"2");
        serialNumber.put(ed24.getId(),"3");
        serialNumber.put(ed31.getId(),"0");
        serialNumber.put(ed32.getId(),"1");
        serialNumber.put(ed33.getId(),"2");
        serialNumber.put(ed41.getId(),"0");
        serialNumber.put(ed42.getId(),"1");
        serialNumber.put(ed43.getId(),"2");
        serialNumber.put(ed44.getId(),"3");
        serialNumber.put(ed51.getId(),"0");
        serialNumber.put(ed52.getId(),"1");
        serialNumber.put(ed53.getId(),"2");
        serialNumber.put(ed81.getId(),"0");
        serialNumber.put(ed82.getId(),"1");
        serialNumber.put(ed83.getId(),"2");
        serialNumber.put(ed84.getId(),"3");
        serialNumber.put(ed91.getId(),"0");
        serialNumber.put(ed92.getId(),"1");
        serialNumber.put(ed93.getId(),"2");
        serialNumber.put(ed101.getId(),"0");
        serialNumber.put(ed102.getId(),"1");
        serialNumber.put(ed103.getId(),"2");
        serialNumber.put(ed104.getId(),"3");
        serialNumber.put(ed111.getId(),"0");
        serialNumber.put(ed112.getId(),"1");
        serialNumber.put(ed113.getId(),"2");
    }
}
