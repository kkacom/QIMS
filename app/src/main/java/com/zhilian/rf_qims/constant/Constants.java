package com.zhilian.rf_qims.constant;

import android.Manifest;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.constant.RxHttpServiceConstants;

import java.io.File;

/**
 * Created by Administrator on 2017-12-29.
 */

public interface Constants extends RxHttpServiceConstants {
	String[] PERMISSIONS = { //外部存储权限判断
		Manifest.permission.READ_EXTERNAL_STORAGE,
		Manifest.permission.WRITE_EXTERNAL_STORAGE};
	String[] IMAGE_SUFFIX_NAMES = {"bmp", "jpg", "jpeg", "png"};
	String[] OFFICE_SUFFIX_NAMES = {"doc", "xls", "ppt", "pdf", "docx", "xlsx", "pptx", "wps", "wpt"};
	// 任务类型
	int TASK_NEW = 0x101;
	int TASK_TODO = 0x102;
	int TASK_DONE = 0x103;
	int UNDO = 0x201;
	int SUBMIT = 0X202;
	//常用符号
	String BRACKET1 = "[ ";
	String BRACKET2 = " ]";
	// 资源定义
	String[] DAY_TYPE = {"全天", "上午", "下午"};
	String[] DAY_TIME={"","01:00:00","02:00:00","03:00:00","04:00:00","05:00:00","06:00:00","07:00:00","08:00:00","09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00","18:00:00","19:00:00","20:00:00","21:00:00","22:00:00","23:00:00","24:00:00"};
	//String[] BRAND= {"粤L RF639","粤L RF639"};
	String[] TYPESL= {"市内用车","市外用车"};
	String appGlobalConfigCacheName = "hzrf_oa";//缓存文件名
	String internal_open = "internal_open";	//文档是否内部打开缓存key
}
