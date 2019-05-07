package com.zhilian.rf_qims.entity;

import java.util.List;

/**
 * 诚信评估所有表（List）
 * Created by YiFan on 2017/5/10.
 */
public class EvaluatePackage {
	List<EvaluateStandardJson> evaluateStandardList;// 配置表
	List<EnterpriseCreditJson> enterpriseCreditList;// 企业名单表
	List<EvaluateAddJson> evaluateAddList;// 加分表
	List<EvaluateDeductJson> evaluateDeductList;// 减分表
	List<EvaluateTestJson> evaluateTestList;// 打分表

	public List<EvaluateStandardJson> getEvaluateStandardList() {
		return evaluateStandardList;
	}

	public void setEvaluateStandardList(List<EvaluateStandardJson> evaluateStandardList) {
		this.evaluateStandardList = evaluateStandardList;
	}

	public List<EnterpriseCreditJson> getEnterpriseCreditList() {
		return enterpriseCreditList;
	}

	public void setEnterpriseCreditList(List<EnterpriseCreditJson> enterpriseCreditList) {
		this.enterpriseCreditList = enterpriseCreditList;
	}

	public List<EvaluateAddJson> getEvaluateAddList() {
		return evaluateAddList;
	}

	public void setEvaluateAddList(List<EvaluateAddJson> evaluateAddList) {
		this.evaluateAddList = evaluateAddList;
	}

	public List<EvaluateDeductJson> getEvaluateDeductList() {
		return evaluateDeductList;
	}

	public void setEvaluateDeductList(List<EvaluateDeductJson> evaluateDeductList) {
		this.evaluateDeductList = evaluateDeductList;
	}

	public List<EvaluateTestJson> getEvaluateTestList() {
		return evaluateTestList;
	}

	public void setEvaluateTestList(List<EvaluateTestJson> evaluateTestList) {
		this.evaluateTestList = evaluateTestList;
	}
}
