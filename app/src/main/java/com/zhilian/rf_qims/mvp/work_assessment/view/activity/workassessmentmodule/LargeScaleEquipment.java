package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description5;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description6;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description7;

import java.util.List;

/**
 * 大型设备
 */
public class LargeScaleEquipment extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "2.6 大型加工设备";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM2_6() == null ? "0.00" : Common.getWorkAbilityJson().getITEM2_6();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "2.6%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        switch (position){
            case 0://2.6.1
                intent = new Intent(LargeScaleEquipment.this, EquipmentList.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 1://2.6.2
                intent = new Intent(LargeScaleEquipment.this, Description5.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 2://2.6.3
                intent = new Intent(LargeScaleEquipment.this, Description6.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 3://2.6.4
                intent = new Intent(LargeScaleEquipment.this, Description6.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 4://2.6.5
                intent = new Intent(LargeScaleEquipment.this, Description6.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 5://2.6.6
                intent = new Intent(LargeScaleEquipment.this, Description6.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 6://2.6.7
                intent = new Intent(LargeScaleEquipment.this, Description6.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 7://2.6.8
                intent = new Intent(LargeScaleEquipment.this, Description6.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 8://2.6.9
                intent = new Intent(LargeScaleEquipment.this, Description6.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 9://2.6.10
                intent = new Intent(LargeScaleEquipment.this, Description7.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
        }
    }
}
