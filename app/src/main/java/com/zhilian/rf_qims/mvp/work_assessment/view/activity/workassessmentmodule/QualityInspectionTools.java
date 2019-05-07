package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description271;

import java.util.List;

/**
 * 质检工具
 */
public class QualityInspectionTools extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "2.8 质检工具";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM2_8() == null ? "0.00" : Common.getWorkAbilityJson().getITEM2_8();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "2.8%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent = new Intent(QualityInspectionTools.this, Description271.class);
        intent.putExtra("item",item);
        intent.putExtra("title",description);
        startActivity(intent);
        /*switch (position){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                Intent intent = new Intent(QualityInspectionTools.this, Description271.class);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description12);
                break;
        }*/
    }
}
