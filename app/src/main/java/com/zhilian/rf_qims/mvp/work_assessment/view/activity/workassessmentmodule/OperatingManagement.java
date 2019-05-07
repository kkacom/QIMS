package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description311;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description312;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description313;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description314;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description315;

import java.util.List;

/**
 * 经营管理
 */
public class OperatingManagement extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "3.1 经营管理";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM3_1() == null ? "0.00" : Common.getWorkAbilityJson().getITEM3_1();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "3.1%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        switch (position){
            case 0:// 3.1.1
                intent = new Intent(OperatingManagement.this, Description311.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 1:// 3.1.2
                intent = new Intent(OperatingManagement.this, Description312.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 2:// 3.1.3
                intent = new Intent(OperatingManagement.this, Description313.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 3:// 3.1.4
                intent = new Intent(OperatingManagement.this, Description314.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 4:// 3.1.5
                intent = new Intent(OperatingManagement.this, Description315.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
        }
    }
}
