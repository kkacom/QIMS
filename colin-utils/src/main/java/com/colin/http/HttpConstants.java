package com.colin.http;

/**
 * Created by sl on 2017-10-17.
 */

public interface HttpConstants {

	//请求参数
//	String BASE_URL = "http://www.gdcda.com:81/RF-QIMS/";// 云服务器
	String BASE_URL = "";// 不适用这个值，这里只能赋值一次，无法被灵活使用
//	String BASE_URL = "http://192.168.9.124:8080/";// 湖南测试从业评估，使用login1登录
	String DOWNLOADURL = BASE_URL + "/File/File/";

	int REQUEST_NUM = 5;//请求失败时，重新请求的次数
	int REQUEST_INTERVAL = 1000 * 3;//请求失败时，重新请求的时间间隔

	//登录选择网络地址(赋值给Ip类的ip)
	String CLOUD_SERVICES = "http://www.gdcda.com:81/RF-QIMS/"; //云服务
//	String CLOUD_SERVICES = "http://192.168.9.123:8090/"; //美基
//	String CLOUD_SERVICES = "http://192.168.9.168:8090/"; //粤鹏
//	String CLOUD_SERVICES = "http://192.168.9.5:8090/"; //尹总
	String LAN = "http://172.16.17.200:81/RF-QIMS/"; //局域网

	//后台接口方法类型
	String TYPE_QUERY = "query";
	String TYPE_SAVE = "save";
	//后台 query 接口方法类型
	String QUERY_LEAVE_TODO = "getLeaveTodoList";//查询待办理请休假
	String QUERY_LEAVE_DONE = "getLeaveDoneList";//查询已办理请休假
	String QUERY_MY_LEAVE = "getMyLeaveList";// 获取我的请休假列表
	String QUERY_LEAVE_NEW = "";//查询新申请请休假
	String QUERY_LEAVE_DETAIL = "LeaveDetail";//查询新申请请休假
	String QUERY_LEAVE_DAYT = "countWorkingDay";//查询可请假天数
	String QUERY_EGRESS_TODO = "getEgressTodoList";//查询待办外出公干
	String QUERY_EGRESS_DONE = "getEgressDoneList";//查询已办外出公干
	String QUERY_MY_EGRESS = "getMyEgressList";// 获取我的外出公干列表
	String QUERY_EGRESS_DETAIL = "EgressDetail";//查询新申请请休假
	String QUERY_VERSION = "updateVersion";//查询App版本

	//后台 save 接口方法类型
	String SAVE_EGRESS = "egresssave";//保存外出报备
	String QUERY_FSONG = "fasong";//查询可请假天数
	String SAVE_OPINION = "editopinion";//意见保存
	String SAVE_LEAVE = "leavesave";//

	//错误反馈
	String RESPONSE_ERROR = "用户登录超时！";
	String HTTP_SERVER_ERROR = "HTTP 500 Server Error";
	String HTTP_NOT_FOUND = "HTTP 404 Not Found";
	//视图错误提示
	String LEAVE_DETAIL_ERROR = "获取请休假详情失败";
	/**
	 * *********************************************************************************************
	 *
	 * ProjectName:RF-QIMS-APP
	 * Description:Backstage interface list
	 * Time:2018-2-2 17:53
	 *
	 * *********************************************************************************************
	 */
	String QUERY_ENTERPRISE_SIZE = "getEnterpriseListSize";//查询委托企业总数
	String QUERY_ENTERPRISE_List = "getEnterpriseList";//查询委托企业列表
	String QUERY_SAMPLE_LIST = "getSampleList";//查询样品列表
	String QUERY_SAMPLE_SIZE = "getSampleListSize";//查询样品总数
	String QUERY_PROJECT_LIST = "getProjectCodeFHlist";//查询项目工程列表
	String QUERY_PROJECT_SIZE = "getCommissionSize";//查询项目工程总数


}
