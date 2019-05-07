package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description23;

import java.util.List;

/**
 * 管理制度
 */
public class ManagementSystem extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "2.4 管理制度";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM2_4() == null ? "0.00" : Common.getWorkAbilityJson().getITEM2_4();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "2.4%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        switch (viewType){
            //case 0:
            case 4:
                intent = new Intent(ManagementSystem.this, Description23.class);
                intent.putExtra("item", item);
                intent.putExtra("title", description);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description1);
                break;
            /*case 2:
                intent = new Intent(ManagementSystem.this, Description233.class);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description2);
                break;
            case 3:
                intent = new Intent(ManagementSystem.this, Description234.class);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description3);
                break;
            case 4:
                intent = new Intent(ManagementSystem.this, Description235.class);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description4);
                break;
            case 5:
            case 6:
            case 7:
                intent = new Intent(ManagementSystem.this, Description231.class);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description1);
                break;*/
        }
    }
}
