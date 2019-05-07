package com.zhilian.rf_qims.mvp.sample_data.pro_quality;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ProQualityGBHD extends LazyLoadFragment {

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
	@BindView(R.id.ed_4_1)
	EditText ed41;
	@BindView(R.id.ed_4_2)
	EditText ed42;
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
	@BindView(R.id.tv_6_4)
	TextView tv64;
	@BindView(R.id.tv_6_5)
	TextView tv65;
	@BindView(R.id.tv_6_6)
	TextView tv66;
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
	@BindView(R.id.ed_7_5)
	EditText ed75;
	@BindView(R.id.ed_7_6)
	EditText ed76;
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
	@BindView(R.id.ed_8_5)
	EditText ed85;
	@BindView(R.id.ed_8_6)
	EditText ed86;
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
	@BindView(R.id.ed_9_5)
	EditText ed95;
	@BindView(R.id.ed_9_6)
	EditText ed96;
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
	@BindView(R.id.tv_title_11_2)
	TextView tvTitle112;
	@BindView(R.id.ed_11_1)
	EditText ed111;
	@BindView(R.id.tv_title_11_3)
	TextView tvTitle113;
	@BindView(R.id.ed_11_2)
	EditText ed112;
	@BindView(R.id.ly_11_1)
	RelativeLayout ly111;
	@BindView(R.id.tv_title_12_1)
	TextView tvTitle121;
	@BindView(R.id.ed_12_1_remark)
	EditText ed121Remark;
	@BindView(R.id.ly_12_1)
	RelativeLayout ly121;
	Unbinder unbinder;
	@BindView(R.id.tv_title_4_2)
	TextView mTvTitle42;

	Unbinder unbinder1;
	private EditText[] editTexts1;
	private EditText[] editTexts2;
	private EditText[] editTexts3;
	private EditText[] editTexts4;
	private EditText[] editTextsAll;
	Map<Integer,String> map = new HashMap<>();
	Map<Integer,String> serialNumber = new HashMap<>();

	@Override
	protected int setContentView() {
		return R.layout.fragment_sczl_gbhd;
	}

	@Override
	protected void lazyLoad() {
		unbinder = ButterKnife.bind(this, view);
		editTexts1 = new EditText[]{ed21, ed22, ed23, ed24, ed25, ed26};
		editTexts2 = new EditText[]{ed31, ed32, ed33, ed34, ed35, ed36};
		editTexts3 = new EditText[]{ed71, ed72, ed73, ed74, ed75, ed76, ed81, ed82, ed83, ed84, ed85, ed86};
		editTexts4 = new EditText[]{ed91, ed92, ed93, ed94, ed95, ed96, ed101, ed102, ed103, ed104, ed105, ed106};
		editTextsAll = new EditText[]{ed21, ed22, ed23, ed24, ed25,
			ed26, ed31, ed32, ed33, ed34, ed35, ed36, ed71, ed72, ed73, ed74, ed75, ed76, ed81, ed82, ed83, ed84, ed85,
			ed86, ed91, ed92, ed93, ed94, ed95, ed96, ed101, ed102, ed103, ed104, ed105, ed106};

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
			editTexts1[0].setText(EdUtil.split(sampleCheck.getDoor_frame_left1())[0]);
			editTexts1[1].setText(EdUtil.split(sampleCheck.getDoor_frame_left1())[1]);
			editTexts1[2].setText(EdUtil.split(sampleCheck.getDoor_frame_left1())[2]);
			editTexts1[3].setText(EdUtil.split(sampleCheck.getDoor_frame_left1())[3]);
			editTexts1[4].setText(EdUtil.split(sampleCheck.getDoor_frame_left1())[4]);
			editTexts1[5].setText(EdUtil.split(sampleCheck.getDoor_frame_left1())[5]);

			editTexts2[0].setText(EdUtil.split(sampleCheck.getDoor_frame_right1())[0]);
			editTexts2[1].setText(EdUtil.split(sampleCheck.getDoor_frame_right1())[1]);
			editTexts2[2].setText(EdUtil.split(sampleCheck.getDoor_frame_right1())[2]);
			editTexts2[3].setText(EdUtil.split(sampleCheck.getDoor_frame_right1())[3]);
			editTexts2[4].setText(EdUtil.split(sampleCheck.getDoor_frame_right1())[4]);
			editTexts2[5].setText(EdUtil.split(sampleCheck.getDoor_frame_right1())[5]);

			editTexts3[0].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[0]);
			editTexts3[1].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[1]);
			editTexts3[2].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[2]);
			editTexts3[3].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[3]);
			editTexts3[4].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[4]);
			editTexts3[5].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[5]);
			editTexts3[6].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[6]);
			editTexts3[7].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[7]);
			editTexts3[8].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[8]);
			editTexts3[9].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[9]);
			editTexts3[10].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[10]);
			editTexts3[11].setText(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[11]);

			editTexts4[0].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[0]);
			editTexts4[1].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[1]);
			editTexts4[2].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[2]);
			editTexts4[3].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[3]);
			editTexts4[4].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[4]);
			editTexts4[5].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[5]);
			editTexts4[6].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[6]);
			editTexts4[7].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[7]);
			editTexts4[8].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[8]);
			editTexts4[9].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[9]);
			editTexts4[10].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[10]);
			editTexts4[11].setText(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[11]);

			//判断检测值是否有值设置不可编辑(2019.3.26)
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_left1())[0].equals(""), ed21,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_left1())[1].equals(""), ed22,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_left1())[2].equals(""), ed23,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_left1())[3].equals(""), ed24,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_left1())[4].equals(""), ed25,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_left1())[5].equals(""), ed26,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_right1())[0].equals(""), ed31,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_right1())[1].equals(""), ed32,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_right1())[2].equals(""), ed33,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_right1())[3].equals(""), ed34,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_right1())[4].equals(""), ed35,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_frame_right1())[5].equals(""), ed36,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[0].equals(""), ed71,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[1].equals(""), ed72,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[2].equals(""), ed73,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[3].equals(""), ed74,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[4].equals(""), ed75,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[5].equals(""), ed76,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[6].equals(""), ed81,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[7].equals(""), ed82,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[8].equals(""), ed83,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[9].equals(""), ed84,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[10].equals(""), ed85,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_positive1())[11].equals(""), ed86,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[0].equals(""), ed91,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[1].equals(""), ed92,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[2].equals(""), ed93,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[3].equals(""), ed94,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[4].equals(""), ed95,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[5].equals(""), ed96,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[6].equals(""), ed101,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[7].equals(""), ed102,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[8].equals(""), ed103,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[9].equals(""), ed104,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[10].equals(""), ed105,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getDoor_leaf_opposite1())[11].equals(""), ed106,
				getContext(), sampleCheck.getId(), map, serialNumber);
			//设计
			/*UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getDoorframeSteelThick()), ed41);
			UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getSteel_angle_width()), ed42);
			UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getSteelPlatePositive()), ed111);
			UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getSteelPlateOpposite()), ed112);*/

			//设计值对比监听
			ed41.addTextChangedListener(new EdCompareDesign(editTexts1[0], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts1[1], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts1[2], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts1[3], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts1[4], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts1[5], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts2[0], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts2[1], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts2[2], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts2[3], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts2[4], ed41));
			ed41.addTextChangedListener(new EdCompareDesign(editTexts2[5], ed41));

			editTexts1[0].addTextChangedListener(new EdCompareDesign(editTexts1[0], ed41));
			editTexts1[1].addTextChangedListener(new EdCompareDesign(editTexts1[1], ed41));
			editTexts1[2].addTextChangedListener(new EdCompareDesign(editTexts1[2], ed41));
			editTexts1[3].addTextChangedListener(new EdCompareDesign(editTexts1[3], ed41));
			editTexts1[4].addTextChangedListener(new EdCompareDesign(editTexts1[4], ed41));
			editTexts1[5].addTextChangedListener(new EdCompareDesign(editTexts1[5], ed41));
			editTexts2[0].addTextChangedListener(new EdCompareDesign(editTexts2[0], ed41));
			editTexts2[1].addTextChangedListener(new EdCompareDesign(editTexts2[1], ed41));
			editTexts2[2].addTextChangedListener(new EdCompareDesign(editTexts2[2], ed41));
			editTexts2[3].addTextChangedListener(new EdCompareDesign(editTexts2[3], ed41));
			editTexts2[4].addTextChangedListener(new EdCompareDesign(editTexts2[4], ed41));
			editTexts2[5].addTextChangedListener(new EdCompareDesign(editTexts2[5], ed41));

			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[0], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[1], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[2], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[3], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[4], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[5], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[6], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[7], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[8], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[9], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[10], ed111));
			ed111.addTextChangedListener(new EdCompareDesign(editTexts3[11], ed111));

			editTexts3[0].addTextChangedListener(new EdCompareDesign(editTexts3[0], ed111));
			editTexts3[1].addTextChangedListener(new EdCompareDesign(editTexts3[1], ed111));
			editTexts3[2].addTextChangedListener(new EdCompareDesign(editTexts3[2], ed111));
			editTexts3[3].addTextChangedListener(new EdCompareDesign(editTexts3[3], ed111));
			editTexts3[4].addTextChangedListener(new EdCompareDesign(editTexts3[4], ed111));
			editTexts3[5].addTextChangedListener(new EdCompareDesign(editTexts3[5], ed111));
			editTexts3[6].addTextChangedListener(new EdCompareDesign(editTexts3[6], ed111));
			editTexts3[7].addTextChangedListener(new EdCompareDesign(editTexts3[7], ed111));
			editTexts3[8].addTextChangedListener(new EdCompareDesign(editTexts3[8], ed111));
			editTexts3[9].addTextChangedListener(new EdCompareDesign(editTexts3[9], ed111));
			editTexts3[10].addTextChangedListener(new EdCompareDesign(editTexts3[10], ed111));
			editTexts3[11].addTextChangedListener(new EdCompareDesign(editTexts3[11], ed111));

			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[0], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[1], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[2], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[3], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[4], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[5], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[6], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[7], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[8], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[9], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[10], ed112));
			ed112.addTextChangedListener(new EdCompareDesign(editTexts4[11], ed112));

			editTexts4[0].addTextChangedListener(new EdCompareDesign(editTexts4[0], ed112));
			editTexts4[1].addTextChangedListener(new EdCompareDesign(editTexts4[1], ed112));
			editTexts4[2].addTextChangedListener(new EdCompareDesign(editTexts4[2], ed112));
			editTexts4[3].addTextChangedListener(new EdCompareDesign(editTexts4[3], ed112));
			editTexts4[4].addTextChangedListener(new EdCompareDesign(editTexts4[4], ed112));
			editTexts4[5].addTextChangedListener(new EdCompareDesign(editTexts4[5], ed112));
			editTexts4[6].addTextChangedListener(new EdCompareDesign(editTexts4[6], ed112));
			editTexts4[7].addTextChangedListener(new EdCompareDesign(editTexts4[7], ed112));
			editTexts4[8].addTextChangedListener(new EdCompareDesign(editTexts4[8], ed112));
			editTexts4[9].addTextChangedListener(new EdCompareDesign(editTexts4[9], ed112));
			editTexts4[10].addTextChangedListener(new EdCompareDesign(editTexts4[10], ed112));
			editTexts4[11].addTextChangedListener(new EdCompareDesign(editTexts4[11], ed112));
		}
		//填充设计值
		ed41.setText(StrKit.notBlank(sample.getDoorframeSteelThick()) ? sample.getDoorframeSteelThick() + "" : "");
		ed42.setText(StrKit.notBlank(sample.getSteel_angle_width()) ? sample.getSteel_angle_width() + "" : "");
		ed111.setText(StrKit.notBlank(sample.getSteelPlatePositive()) ? sample.getSteelPlatePositive() + "" : "");
		ed112.setText(StrKit.notBlank(sample.getSteelPlateOpposite()) ? sample.getSteelPlateOpposite() + "" : "");


		//设计值保存监听
		ed41.addTextChangedListener(new EdDesignChangeSave("setDoorframeSteelThick", sample, sample.getDoorframeSteelThick() + "", ed41));
		ed42.addTextChangedListener(new EdDesignChangeSave("setSteel_angle_width", sample, sample.getSteel_angle_width() + "", ed42));
		ed111.addTextChangedListener(new EdDesignChangeSave("setSteelPlatePositive", sample, sample.getSteelPlatePositive() + "", ed111));
		ed112.addTextChangedListener(new EdDesignChangeSave("setSteelPlateOpposite", sample, sample.getSteelPlateOpposite() + "", ed112));

		//检测值保存监听
		editTexts1[0].addTextChangedListener(new EdTextChange("setDoor_frame_left1", sampleCheck, sampleCheck.getDoor_frame_left1(), editTexts1));
		editTexts1[1].addTextChangedListener(new EdTextChange("setDoor_frame_left1", sampleCheck, sampleCheck.getDoor_frame_left1(), editTexts1));
		editTexts1[2].addTextChangedListener(new EdTextChange("setDoor_frame_left1", sampleCheck, sampleCheck.getDoor_frame_left1(), editTexts1));
		editTexts1[3].addTextChangedListener(new EdTextChange("setDoor_frame_left1", sampleCheck, sampleCheck.getDoor_frame_left1(), editTexts1));
		editTexts1[4].addTextChangedListener(new EdTextChange("setDoor_frame_left1", sampleCheck, sampleCheck.getDoor_frame_left1(), editTexts1));
		editTexts1[5].addTextChangedListener(new EdTextChange("setDoor_frame_left1", sampleCheck, sampleCheck.getDoor_frame_left1(), editTexts1));

		editTexts2[0].addTextChangedListener(new EdTextChange("setDoor_frame_right1", sampleCheck, sampleCheck.getDoor_frame_right1(), editTexts2));
		editTexts2[1].addTextChangedListener(new EdTextChange("setDoor_frame_right1", sampleCheck, sampleCheck.getDoor_frame_right1(), editTexts2));
		editTexts2[2].addTextChangedListener(new EdTextChange("setDoor_frame_right1", sampleCheck, sampleCheck.getDoor_frame_right1(), editTexts2));
		editTexts2[3].addTextChangedListener(new EdTextChange("setDoor_frame_right1", sampleCheck, sampleCheck.getDoor_frame_right1(), editTexts2));
		editTexts2[4].addTextChangedListener(new EdTextChange("setDoor_frame_right1", sampleCheck, sampleCheck.getDoor_frame_right1(), editTexts2));
		editTexts2[5].addTextChangedListener(new EdTextChange("setDoor_frame_right1", sampleCheck, sampleCheck.getDoor_frame_right1(), editTexts2));

		editTexts3[0].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[1].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[2].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[3].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[4].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[5].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[6].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[7].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[8].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[9].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[10].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));
		editTexts3[11].addTextChangedListener(new EdTextChange("setDoor_leaf_positive1", sampleCheck, sampleCheck.getDoor_leaf_positive1(), editTexts3));

		editTexts4[0].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[1].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[2].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[3].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[4].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[5].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[6].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[7].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[8].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[9].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[10].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));
		editTexts4[11].addTextChangedListener(new EdTextChange("setDoor_leaf_opposite1", sampleCheck, sampleCheck.getDoor_leaf_opposite1(), editTexts4));

		UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
	}

	private void fillMap(){
		map.put(ed21.getId(),"door_frame_left");
		map.put(ed22.getId(),"door_frame_left");
		map.put(ed23.getId(),"door_frame_left");
		map.put(ed24.getId(),"door_frame_left");
		map.put(ed25.getId(),"door_frame_left");
		map.put(ed26.getId(),"door_frame_left");
		map.put(ed31.getId(),"door_frame_right");
		map.put(ed32.getId(),"door_frame_right");
		map.put(ed33.getId(),"door_frame_right");
		map.put(ed34.getId(),"door_frame_right");
		map.put(ed35.getId(),"door_frame_right");
		map.put(ed36.getId(),"door_frame_right");
		map.put(ed71.getId(),"door_leaf_positive");
		map.put(ed72.getId(),"door_leaf_positive");
		map.put(ed73.getId(),"door_leaf_positive");
		map.put(ed74.getId(),"door_leaf_positive");
		map.put(ed75.getId(),"door_leaf_positive");
		map.put(ed76.getId(),"door_leaf_positive");
		map.put(ed81.getId(),"door_leaf_positive");
		map.put(ed82.getId(),"door_leaf_positive");
		map.put(ed83.getId(),"door_leaf_positive");
		map.put(ed84.getId(),"door_leaf_positive");
		map.put(ed85.getId(),"door_leaf_positive");
		map.put(ed86.getId(),"door_leaf_positive");
		map.put(ed91.getId(),"door_leaf_opposite");
		map.put(ed92.getId(),"door_leaf_opposite");
		map.put(ed93.getId(),"door_leaf_opposite");
		map.put(ed94.getId(),"door_leaf_opposite");
		map.put(ed95.getId(),"door_leaf_opposite");
		map.put(ed96.getId(),"door_leaf_opposite");
		map.put(ed101.getId(),"door_leaf_opposite");
		map.put(ed102.getId(),"door_leaf_opposite");
		map.put(ed103.getId(),"door_leaf_opposite");
		map.put(ed104.getId(),"door_leaf_opposite");
		map.put(ed105.getId(),"door_leaf_opposite");
		map.put(ed106.getId(),"door_leaf_opposite");

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
		serialNumber.put(ed71.getId(),"0");
		serialNumber.put(ed72.getId(),"1");
		serialNumber.put(ed73.getId(),"2");
		serialNumber.put(ed74.getId(),"3");
		serialNumber.put(ed75.getId(),"4");
		serialNumber.put(ed76.getId(),"5");
		serialNumber.put(ed81.getId(),"6");
		serialNumber.put(ed82.getId(),"7");
		serialNumber.put(ed83.getId(),"8");
		serialNumber.put(ed84.getId(),"9");
		serialNumber.put(ed85.getId(),"10");
		serialNumber.put(ed86.getId(),"11");
		serialNumber.put(ed91.getId(),"0");
		serialNumber.put(ed92.getId(),"1");
		serialNumber.put(ed93.getId(),"2");
		serialNumber.put(ed94.getId(),"3");
		serialNumber.put(ed95.getId(),"4");
		serialNumber.put(ed96.getId(),"5");
		serialNumber.put(ed101.getId(),"6");
		serialNumber.put(ed102.getId(),"7");
		serialNumber.put(ed103.getId(),"8");
		serialNumber.put(ed104.getId(),"9");
		serialNumber.put(ed105.getId(),"10");
		serialNumber.put(ed106.getId(),"11");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder1 = ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder1.unbind();
	}
}
