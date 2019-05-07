package com.zhilian.rf_qims.api;

import com.zhilian.api.InMsg;
import com.zhilian.rf_qims.entity.EnterpriseCreditJson;
import com.zhilian.rf_qims.entity.EvaluateTestJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 接收保存消息

 {
    createTime : '1348831860'
    ,msgType : 'save'

    ,modelName : 'model名'
    ,modelProperty ：{
        name : 'abc'
        ,code : 'def'
    }
 }
 */
public class InSaveMsg extends InMsg {

	private String modelName;   // 要保存的Model名
	private Map<String,String> modelProperty = new HashMap<>(); // Model属性
	private String key;
	private List<EvaluateTestJson> testListProperty = new ArrayList<>();//列表属性
	private List<EnterpriseCreditJson> creditListProperty = new ArrayList<>();//列表属性
	/*private Map<String, EvaluateSavePackage> listProperty = new HashMap<>();//列表属性
	
	
    public Map<String, EvaluateSavePackage> getListProperty() {
		return listProperty;
	}

	public void setListProperty(Map<String, EvaluateSavePackage> listProperty) {
		this.listProperty = listProperty;
	}*/

	
	public String getKey() {
		return key;
	}

	

	public List<EvaluateTestJson> getTestListProperty() {
		return testListProperty;
	}



	public void setTestListProperty(List<EvaluateTestJson> testListProperty) {
		this.testListProperty = testListProperty;
	}



	public List<EnterpriseCreditJson> getCreditListProperty() {
		return creditListProperty;
	}



	public void setCreditListProperty(
			List<EnterpriseCreditJson> creditListProperty) {
		this.creditListProperty = creditListProperty;
	}



	public void setKey(String key) {
		this.key = key;
	}

	public InSaveMsg() {
    }

    public InSaveMsg(Integer createTime, String msgType) {
        super(createTime, msgType);
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Map<String, String> getModelProperty() {
        return modelProperty;
    }

    public void setModelProperty(Map<String, String> modelProperty) {
        this.modelProperty = modelProperty;
    }
}




