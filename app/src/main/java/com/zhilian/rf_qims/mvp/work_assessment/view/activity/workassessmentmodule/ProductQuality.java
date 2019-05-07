package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description316;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description321;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description322;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description323;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description324;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description325;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description326;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description327;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description328;

import java.util.List;

/**
 * 产品质量
 */
public class ProductQuality extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "3.2 产品质量";
    }

    //得分
    @Override
    public String totalScore() {
        String totalScore = Common.getWorkAbilityJson().getITEM3_2() == null ? "0.00" : Common.getWorkAbilityJson().getITEM3_2();
        return totalScore;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "3.2%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        switch (position){
            case 0://3.2.1
                intent = new Intent(ProductQuality.this, Description321.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 1://3.2.2
                intent = new Intent(ProductQuality.this, Description322.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 2://3.2.3
                intent = new Intent(ProductQuality.this, Description323.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 3://3.2.4
                intent = new Intent(ProductQuality.this, Description324.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
            case 4://3.2.5
                intent = new Intent(ProductQuality.this, Description325.class);
                intent.putExtra("item",item);
                intent.putExtra("title",description);
                startActivity(intent);
                break;
        }
    }
}
