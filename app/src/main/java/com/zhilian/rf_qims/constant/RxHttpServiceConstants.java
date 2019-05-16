package com.zhilian.rf_qims.constant;


/**
 * Created by sl on 2017-10-17.
 */

public interface RxHttpServiceConstants {
	/**
	 * ************************************* 请求参数 ***************************************
	 */

    String BASE_URL = "http://192.168.9.7:8083/"; 				// 尹总
    //String BASE_URL = "http://192.168.9.123:8084/";			// 美基地址
	//String BASE_URL = "http://hzrfoa.vicp.io:25246/hzrf-oa/";    // 云服务器

	String URL = null;
	String apkUrl = BASE_URL + "File/App/hzrf-oa.apk";
	String DOWNLOADURL = BASE_URL + "/File/File/";
	String TYPE_QUERY = "query";
	String TYPE_SAVE = "save";
	int REQUEST_NUM = 5;//请求失败时，重新请求的次数
	int REQUEST_INTERVAL = 1000 * 3;//请求失败时，重新请求的时间间隔

	/**
	 * ************************************* 方法名 ***************************************
	 */
	String QUERY_VERSION = "updateVersion";//查询App版本
	String QUERY_LEAVE_TODO = "getLeaveTodoList";//查询待办理请休假
	String QUERY_LEAVE_DONE = "getLeaveDoneList";//查询已办理请休假
	String QUERY_MY_LEAVE = "getMyLeaveList";// 获取我的请休假列表
	String QUERY_LEAVE_NEW = "";//查询新申请请休假
	String QUERY_LEAVE_DETAIL = "LeaveDetail";//查询新申请请休假
	String QUERY_VEHICLE_NEW = "";//查询新申请用车
	String QUERY_VEHICLE_DETAIL = "VehicleDetail";//查询新申请用车
	String QUERY_VEHICLE_TODO = "getVehicleTodoList";//查询待办理用车
	String QUERY_VEHICLE_DONE = "getVehicleDoneList";//查询已办理用车
	String QUERY_MY_VEHICLE = "getMyVehicleList";// 获取我的用车列表
	String QUERY_COUNT_WORKING_DAY = "countWorkingDay";//查询可请假天数
	String QUERY_EGRESS_TODO = "getEgressTodoList";//查询待办外出公干
	String QUERY_EGRESS_DONE = "getEgressDoneList";//查询已办外出公干
	String QUERY_MY_EGRESS = "getMyEgressList";// 获取我的外出公干列表
	String QUERY_EGRESS_DETAIL = "EgressDetail";//查询新申请请休假
	String SAVE_EGRESS = "egresssave";//保存外出报备
	String QUERY_FSONG = "fasong";//查询可请假天数
	String SAVE_OPINION = "editopinion";//意见保存
	String SAVE_LEAVE = "leavesave";//
	String QUERY_CAR_TODO = "getCarTodoList";//查询待办外出公干
	String QUERY_CAR_DONE = "getCarDoneList";//查询已办外出公干
	String QUERY_MY_CAR = "getMyCarList";// 获取我的外出公干列表
	String QUERY_CAR_DETAIL = "CarDetail";//查询新申请请休假
	String SAVE_CAR = "carsave";//保存外出报备

	/**
	 * ************************************* 错误反馈 ***************************************
	 */
	String RESPONSE_ERROR = "用户登录超时！";
	String HTTP_SERVER_ERROR = "HTTP 500 Server Error";
	String HTTP_NOT_FOUND = "HTTP 404 Not Found";
	/**
	 * ************************************* 视图错误提示 ***************************************
	 */
	String LEAVE_DETAIL_ERROR = "获取请休假详情失败";
}
