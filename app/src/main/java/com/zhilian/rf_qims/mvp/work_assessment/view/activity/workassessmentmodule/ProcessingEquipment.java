package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description266;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description277;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description278;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description279;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description6;

import java.util.List;

/**
 * 普通加工设备
 */
public class ProcessingEquipment extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "2.7 普通加工设备";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM2_7() == null ? "0.00" : Common.getWorkAbilityJson().getITEM2_7();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "2.7%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        switch (position){
            case 0://2.7.1
                intent = new Intent(ProcessingEquipment.this, EquipmentList271.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 1://2.7.2
                intent = new Intent(ProcessingEquipment.this, EquipmentList272.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 2://2.7.3
                intent = new Intent(ProcessingEquipment.this, EquipmentList273.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 3://2.7.4
                intent = new Intent(ProcessingEquipment.this, Description6.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 4://2.7.5
                intent = new Intent(ProcessingEquipment.this, EquipmentList275.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 5://2.7.6
                intent = new Intent(ProcessingEquipment.this, Description266.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 6://2.7.7
                intent = new Intent(ProcessingEquipment.this, Description277.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 7://2.7.8
                intent = new Intent(ProcessingEquipment.this, Description278.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 8://2.7.9
                intent = new Intent(ProcessingEquipment.this, Description279.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
        }
    }
}
