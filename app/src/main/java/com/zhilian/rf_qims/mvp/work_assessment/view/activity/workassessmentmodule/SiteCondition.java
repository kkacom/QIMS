package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description241;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description242;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description243;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description245;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description246;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description257;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description258;

import java.util.List;

/**
 * 场地条件
 */
public class SiteCondition extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "2.5 场地条件";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM2_5() == null ? "0.00" : Common.getWorkAbilityJson().getITEM2_5();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "2.5%");
    }

    // 描述列表的数据
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        switch (position){
            case 0://2.5.1
                intent = new Intent(SiteCondition.this, Description241.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 1://2.5.2
                intent = new Intent(SiteCondition.this, Description242.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 2://2.5.3
                intent = new Intent(SiteCondition.this, Description243.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 3://2.5.4
                intent = new Intent(SiteCondition.this, Description243.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 4://2.5.5
                intent = new Intent(SiteCondition.this, Description245.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 5://2.5.6
                intent = new Intent(SiteCondition.this, Description246.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 6://2.5.7
                intent = new Intent(SiteCondition.this, Description257.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 7://2.5.8
                intent = new Intent(SiteCondition.this, Description258.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
        }
    }
}
