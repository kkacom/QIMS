package com.zhilian.rf_qims.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.PersonnelJson;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.entity.WorkAbilityJson;

/**
 * Created by colin on 2018/8/30 16:52 .
 * 公共类，暂时用于门数据各个Fragment同步数据（解决样品门旧数据覆盖新数据）
 */
public class Common {
	public static SampleCheck sampleCheck;
	public static Sample sample;
	private static String eid;
	private static Long wid;
	private static WorkAbilityJson workAbilityJson;
	private static PersonnelJson personnelJson;
	private static CaTestJson caTestJson;
	private static String configPoint; //基础分值
	private static String configExplain;//扣分说明
	public SampleCheck getSampleCheck() {
		return sampleCheck;
	}

	public void setSampleCheck(SampleCheck sampleCheck) {
		Common.sampleCheck = sampleCheck;
	}

	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		Common.sample = sample;
	}

	public static String getEid() {
		return eid;
	}

	public static void setEid(String eid) {
		Common.eid = eid;
	}

	public static Long getWid() {
		return wid;
	}

	public static void setWid(Long wid) {
		Common.wid = wid;
	}

	public static WorkAbilityJson getWorkAbilityJson() {
		return workAbilityJson;
	}

	public static void setWorkAbilityJson(WorkAbilityJson workAbilityJson) {
		Common.workAbilityJson = workAbilityJson;
	}

	public static PersonnelJson getPersonnelJson() {
		return personnelJson;
	}

	public static void setPersonnelJson(PersonnelJson personnelJson) {
		Common.personnelJson = personnelJson;
	}

	public static CaTestJson getCaTestJson() {
		return caTestJson;
	}

	public static void setCaTestJson(CaTestJson caTestJson) {
		Common.caTestJson = caTestJson;
	}

	public static String getConfigPoint() {
		return configPoint;
	}

	public static void setConfigPoint(String configPoint) {
		Common.configPoint = configPoint;
	}

	public static String getConfigExplain() {
		return configExplain;
	}

	public static void setConfigExplain(String configExplain) {
		Common.configExplain = configExplain;
	}

	public static boolean judgeNet(Context mContext) {//判断是否有网络
		boolean flag = true;
		final ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo mobNetInfoActivity = connectivityManager
				.getActiveNetworkInfo();
		if (mobNetInfoActivity == null || !mobNetInfoActivity.isAvailable()) {
			Toast.makeText(mContext, "当前没有连接网络！", Toast.LENGTH_SHORT).show();
			flag = false;
		}

		return flag;
	}
}
