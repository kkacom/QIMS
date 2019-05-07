package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description1;

import java.util.List;

/**
 * 技术人员
 */
public class TechnicistPersonnel extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "2.2 技术人员";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM2_2() == null ? "0.00" : Common.getWorkAbilityJson().getITEM2_2();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "2.2%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        /*switch (viewType){
            case 1:
                intent = new Intent(TechnicistPersonnel.this, TechnicistList.class);
                intent.putExtra("item", item);
                intent.putExtra("title", description);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(TechnicistPersonnel.this, TechnicistList.class);
                intent.putExtra("item", item);
                intent.putExtra("title", description);
                startActivity(intent);
                break;
            case 3:*/
                intent = new Intent(TechnicistPersonnel.this, TechnicistList.class);
                intent.putExtra("item", item);
                intent.putExtra("title", description);
                startActivity(intent);
            /*    break;
        }*/
    }

}
