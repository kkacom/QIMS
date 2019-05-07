package com.zhilian.rf_qims.mvp.sample_data.pro_size;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhilian.api.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.dao.GreenDaoManager;
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

public class ProSizeXBM extends LazyLoadFragment {
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
	@BindView(R.id.ed_4_4)
	Spinner mEd44;
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
	@BindView(R.id.ly_13_1)
	RelativeLayout ly131;
	@BindView(R.id.tv_title_14_1)
	TextView tvTitle141;
	@BindView(R.id.ed_14_1_remark)
	EditText ed141Remark;
	@BindView(R.id.ly_14_1)
	RelativeLayout ly141;
	Unbinder unbinder;

	Unbinder unbinder1;
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
		return R.layout.fragment_sccc_xbm;
	}

	@Override
	protected void lazyLoad() {
		unbinder = ButterKnife.bind(this, view);
		editTexts1 = new EditText[]{ed21, ed22};
		editTexts2 = new EditText[]{ed31, ed32};
		editTexts3 = new EditText[]{ed41, ed42};
		editTexts4 = new EditText[]{ed71, ed72};
		editTexts5 = new EditText[]{ed81, ed82};
		editTexts6 = new EditText[]{ed91, ed92};
		editTexts7 = new EditText[]{ed121, ed122};
		editTexts8 = new EditText[]{ed131, ed132};
		editTextsAll = new EditText[]{ed21, ed22, ed31,
			ed32, ed41, ed42, ed71, ed72, ed81, ed82, ed91, ed92, ed121 ,ed122, ed131, ed132};

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
			ed121.addTextChangedListener(new EdDifferenceDesign(ed121, ed122, ed123));
			ed122.addTextChangedListener(new EdDifferenceDesign(ed121, ed122, ed123));
			ed131.addTextChangedListener(new EdDifferenceDesign(ed131, ed132, ed133));
			ed132.addTextChangedListener(new EdDifferenceDesign(ed131, ed132, ed133));
			//填充检测值
			editTexts1[0].setText(EdUtil.split(sampleCheck.getHanging_plate_height1())[0]);
			editTexts1[1].setText(EdUtil.split(sampleCheck.getHanging_plate_height1())[1]);

			editTexts2[0].setText(EdUtil.split(sampleCheck.getHanging_plate_height_center1())[0]);
			editTexts2[1].setText(EdUtil.split(sampleCheck.getHanging_plate_height_center1())[1]);

			editTexts3[0].setText(EdUtil.split(sampleCheck.getHanging_plate_height_bottom1())[0]);
			editTexts3[1].setText(EdUtil.split(sampleCheck.getHanging_plate_height_bottom1())[1]);

			editTexts4[0].setText(EdUtil.split(sampleCheck.getHanging_plate_width1())[0]);
			editTexts4[1].setText(EdUtil.split(sampleCheck.getHanging_plate_width1())[1]);

			editTexts5[0].setText(EdUtil.split(sampleCheck.getHanging_plate_width_center1())[0]);
			editTexts5[1].setText(EdUtil.split(sampleCheck.getHanging_plate_width_center1())[1]);

			editTexts6[0].setText(EdUtil.split(sampleCheck.getHanging_plate_width_bottom1())[0]);
			editTexts6[1].setText(EdUtil.split(sampleCheck.getHanging_plate_width_bottom1())[1]);

			editTexts7[0].setText(EdUtil.split(sampleCheck.getSwing_plate_diagonal1())[0]);
			editTexts7[1].setText(EdUtil.split(sampleCheck.getSwing_plate_diagonal1())[1]);

			editTexts8[0].setText(EdUtil.split(sampleCheck.getSwing_plate_diagonal2())[0]);
			editTexts8[1].setText(EdUtil.split(sampleCheck.getSwing_plate_diagonal2())[1]);

			//判断检测值是否有值设置不可编辑
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_height1())[0].equals(""), ed21,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_height1())[1].equals(""), ed22,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_height_center1())[0].equals(""), ed31,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_height_center1())[1].equals(""), ed32,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_height_bottom1())[0].equals(""), ed41,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_height_bottom1())[1].equals(""), ed42,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_width1())[0].equals(""), ed71,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_width1())[1].equals(""), ed72,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_width_center1())[0].equals(""), ed81,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_width_center1())[1].equals(""), ed82,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_width_bottom1())[0].equals(""), ed91,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getHanging_plate_width_bottom1())[1].equals(""), ed92,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_diagonal1())[0].equals(""), ed121,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_diagonal1())[1].equals(""), ed122,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_diagonal2())[0].equals(""), ed131,
				getContext(), sampleCheck.getId(), map, serialNumber);
			UpdateSampleCheckStatu(EdUtil.split(sampleCheck.getSwing_plate_diagonal2())[1].equals(""), ed132,
				getContext(), sampleCheck.getId(), map, serialNumber);
			//设计
			/*UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getHangingPlateHeightBottom()), ed43);
			UpdateSampleCheckStatu(StrKit.notBlankJudge(sample.getHangingPlateWidthBottom()), ed93);*/

			//设计值对比监听
			ed23.addTextChangedListener(new EdCompareDesign(editTexts1[0], ed23));
			ed23.addTextChangedListener(new EdCompareDesign(editTexts1[1], ed23));

			ed33.addTextChangedListener(new EdCompareDesign(editTexts2[0], ed33));
			ed33.addTextChangedListener(new EdCompareDesign(editTexts2[1], ed33));

			ed43.addTextChangedListener(new EdCompareDesign(editTexts3[0], ed43));
			ed43.addTextChangedListener(new EdCompareDesign(editTexts3[1], ed43));

			ed73.addTextChangedListener(new EdCompareDesign(editTexts4[0], ed73));
			ed73.addTextChangedListener(new EdCompareDesign(editTexts4[1], ed73));

			ed83.addTextChangedListener(new EdCompareDesign(editTexts5[0], ed83));
			ed83.addTextChangedListener(new EdCompareDesign(editTexts5[1], ed83));

			ed93.addTextChangedListener(new EdCompareDesign(editTexts6[0], ed93));
			ed93.addTextChangedListener(new EdCompareDesign(editTexts6[1], ed93));


			editTexts1[0].addTextChangedListener(new EdCompareDesign(editTexts1[0], ed23));
			editTexts1[1].addTextChangedListener(new EdCompareDesign(editTexts1[1], ed23));

			editTexts2[0].addTextChangedListener(new EdCompareDesign(editTexts2[0], ed33));
			editTexts2[1].addTextChangedListener(new EdCompareDesign(editTexts2[1], ed33));

			editTexts3[0].addTextChangedListener(new EdCompareDesign(editTexts3[0], ed43));
			editTexts3[1].addTextChangedListener(new EdCompareDesign(editTexts3[1], ed43));

			editTexts4[0].addTextChangedListener(new EdCompareDesign(editTexts4[0], ed73));
			editTexts4[1].addTextChangedListener(new EdCompareDesign(editTexts4[1], ed73));

			editTexts5[0].addTextChangedListener(new EdCompareDesign(editTexts5[0], ed83));
			editTexts5[1].addTextChangedListener(new EdCompareDesign(editTexts5[1], ed83));

			editTexts6[0].addTextChangedListener(new EdCompareDesign(editTexts6[0], ed93));
			editTexts6[1].addTextChangedListener(new EdCompareDesign(editTexts6[1], ed93));
		}
		//填充设计值
		ed23.setText(StrKit.notBlank(sample.getHangingPlateHeight()) ? sample.getHangingPlateHeight() + "" : "");
		ed33.setText(StrKit.notBlank(sample.getHangingPlateHeightCenter()) ? sample.getHangingPlateHeightCenter() + "" : "");
		ed43.setText(StrKit.notBlank(sample.getHangingPlateHeightBottom()) ? sample.getHangingPlateHeightBottom() + "" : "");
		ed73.setText(StrKit.notBlank(sample.getHangingPlateWidth()) ? sample.getHangingPlateWidth() + "" : "");
		ed83.setText(StrKit.notBlank(sample.getHangingPlateWidthCenter()) ? sample.getHangingPlateWidthCenter() + "" : "");
		ed93.setText(StrKit.notBlank(sample.getHangingPlateWidthBottom()) ? sample.getHangingPlateWidthBottom() + "" : "");
		if (sample.getDemo2Ddate() == null){
			mEd44.setSelection(0);
		}else {
			switch (sample.getDemo2Ddate()){ //第几块由上至下
				case "":
					mEd44.setSelection(0);
					break;
				case "第1块(由上至下)":
					mEd44.setSelection(1);
					break;
				case "第2块(由上至下)":
					mEd44.setSelection(2);
					break;
				case "第3块(由上至下)":
					mEd44.setSelection(3);
					break;
				case "第4块(由上至下)":
					mEd44.setSelection(4);
					break;
			}
		}

		//设计值保存监听
		ed23.addTextChangedListener(new EdDesignChangeSave("setHangingPlateHeight", sample, sample.getHangingPlateHeight() + "", ed23));
		ed33.addTextChangedListener(new EdDesignChangeSave("setHangingPlateHeightCenter", sample, sample.getHangingPlateHeightCenter() + "", ed33));
		ed43.addTextChangedListener(new EdDesignChangeSave("setHangingPlateHeightBottom", sample, sample.getHangingPlateHeightBottom() + "", ed43));
		ed73.addTextChangedListener(new EdDesignChangeSave("setHangingPlateWidth", sample, sample.getHangingPlateWidth() + "", ed73));
		ed83.addTextChangedListener(new EdDesignChangeSave("setHangingPlateWidthCenter", sample, sample.getHangingPlateWidthCenter() + "", ed83));
		ed93.addTextChangedListener(new EdDesignChangeSave("setHangingPlateWidthBottom", sample, sample.getHangingPlateWidthBottom() + "", ed93));
		mEd44.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String value = null;
				switch (position){
					case 0:
						value = "";
						break;
					case 1:
						value = "第1块(由上至下)";
						break;
					case 2:
						value = "第2块(由上至下)";
						break;
					case 3:
						value = "第3块(由上至下)";
						break;
					case 4:
						value = "第4块(由上至下)";
						break;
				}
				sample.setDemo2Ddate(value);
				common.setSample(sample);
				GreenDaoManager.getInstance().getNewSession().getSampleDao().update(sample);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		//检测值保存监听
		editTexts1[0].addTextChangedListener(new EdTextChange("setHanging_plate_height1", sampleCheck, sampleCheck.getHanging_plate_height1(), editTexts1));
		editTexts1[1].addTextChangedListener(new EdTextChange("setHanging_plate_height1", sampleCheck, sampleCheck.getHanging_plate_height1(), editTexts1));

		editTexts2[0].addTextChangedListener(new EdTextChange("setHanging_plate_height_center1", sampleCheck, sampleCheck.getHanging_plate_height_center1(), editTexts2));
		editTexts2[1].addTextChangedListener(new EdTextChange("setHanging_plate_height_center1", sampleCheck, sampleCheck.getHanging_plate_height_center1(), editTexts2));

		editTexts3[0].addTextChangedListener(new EdTextChange("setHanging_plate_height_bottom1", sampleCheck, sampleCheck.getHanging_plate_height_bottom1(), editTexts3));
		editTexts3[1].addTextChangedListener(new EdTextChange("setHanging_plate_height_bottom1", sampleCheck, sampleCheck.getHanging_plate_height_bottom1(), editTexts3));

		editTexts4[0].addTextChangedListener(new EdTextChange("setHanging_plate_width1", sampleCheck, sampleCheck.getHanging_plate_width1(), editTexts4));
		editTexts4[1].addTextChangedListener(new EdTextChange("setHanging_plate_width1", sampleCheck, sampleCheck.getHanging_plate_width1(), editTexts4));

		editTexts5[0].addTextChangedListener(new EdTextChange("setHanging_plate_width_center1", sampleCheck, sampleCheck.getHanging_plate_width_center1(), editTexts5));
		editTexts5[1].addTextChangedListener(new EdTextChange("setHanging_plate_width_center1", sampleCheck, sampleCheck.getHanging_plate_width_center1(), editTexts5));

		editTexts6[0].addTextChangedListener(new EdTextChange("setHanging_plate_width_bottom1", sampleCheck, sampleCheck.getHanging_plate_width_bottom1(), editTexts6));
		editTexts6[1].addTextChangedListener(new EdTextChange("setHanging_plate_width_bottom1", sampleCheck, sampleCheck.getHanging_plate_width_bottom1(), editTexts6));

		editTexts7[0].addTextChangedListener(new EdTextChange("setSwing_plate_diagonal1", sampleCheck, sampleCheck.getSwing_plate_diagonal1(), editTexts7));
		editTexts7[1].addTextChangedListener(new EdTextChange("setSwing_plate_diagonal1", sampleCheck, sampleCheck.getSwing_plate_diagonal1(), editTexts7));

		editTexts8[0].addTextChangedListener(new EdTextChange("setSwing_plate_diagonal2", sampleCheck, sampleCheck.getSwing_plate_diagonal2(), editTexts8));
		editTexts8[1].addTextChangedListener(new EdTextChange("setSwing_plate_diagonal2", sampleCheck, sampleCheck.getSwing_plate_diagonal2(), editTexts8));

		UpdateSampleCheckStatu.editOnFocusChangeListener(getContext(), sampleCheck.getId(), map, serialNumber, editTextsAll);
	}

	private void fillMap(){
		map.put(ed21.getId(),"hanging_plate_height");
		map.put(ed22.getId(),"hanging_plate_height");
		map.put(ed31.getId(),"hanging_plate_height_center");
		map.put(ed32.getId(),"hanging_plate_height_center");
		map.put(ed41.getId(),"hanging_plate_height_bottom");
		map.put(ed42.getId(),"hanging_plate_height_bottom");
		map.put(ed71.getId(),"hanging_plate_width");
		map.put(ed72.getId(),"hanging_plate_width");
		map.put(ed81.getId(),"hanging_plate_width_center");
		map.put(ed82.getId(),"hanging_plate_width_center");
		map.put(ed91.getId(),"hanging_plate_width_bottom");
		map.put(ed92.getId(),"hanging_plate_width_bottom");
		map.put(ed121.getId(),"swing_plate_diagonal");
		map.put(ed122.getId(),"swing_plate_diagonal");
		map.put(ed131.getId(),"swing_plate_diagonal");
		map.put(ed132.getId(),"swing_plate_diagonal");

		serialNumber.put(ed21.getId(),"0");
		serialNumber.put(ed22.getId(),"1");
		serialNumber.put(ed31.getId(),"0");
		serialNumber.put(ed32.getId(),"1");
		serialNumber.put(ed41.getId(),"0");
		serialNumber.put(ed42.getId(),"1");
		serialNumber.put(ed71.getId(),"0");
		serialNumber.put(ed72.getId(),"1");
		serialNumber.put(ed81.getId(),"0");
		serialNumber.put(ed82.getId(),"1");
		serialNumber.put(ed91.getId(),"0");
		serialNumber.put(ed92.getId(),"1");
		serialNumber.put(ed121.getId(),"0");
		serialNumber.put(ed122.getId(),"1");
		serialNumber.put(ed131.getId(),"2");
		serialNumber.put(ed132.getId(),"3");
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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }

//    @Override
//    public void onDestroyView() {
//        unbinder.unbind();
//        super.onDestroyView();
//    }

//    @OnClick({R.id.tv_page_before, R.id.tv_page_next, R.id.btn_save})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_page_before:
//                break;
//            case R.id.tv_page_next:
//                break;
//            case R.id.btn_save:
//
//                break;
//        }
//    }
}