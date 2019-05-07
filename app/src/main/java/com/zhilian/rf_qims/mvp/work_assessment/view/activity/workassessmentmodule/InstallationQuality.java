package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description312;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description316;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description331;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description332;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description333;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description334;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description424;

import java.util.List;

/**
 * 安装质量
 */
public class InstallationQuality extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "3.3 安装质量";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM3_3() == null ? "0.00" : Common.getWorkAbilityJson().getITEM3_3();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "3.3%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(InstallationQuality.this, Description331.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(InstallationQuality.this, Description332.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(InstallationQuality.this, Description333.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(InstallationQuality.this, Description334.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 4:
            case 5:
            case 6:
                /*intent = new Intent(InstallationQuality.this, Description424.class);
                startActivity(intent);*/
                break;
        }
    }
}
