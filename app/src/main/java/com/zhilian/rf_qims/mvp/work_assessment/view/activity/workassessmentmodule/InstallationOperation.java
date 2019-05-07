package com.zhilian.rf_qims.mvp.work_assessment.view.activity.workassessmentmodule;

import android.content.Intent;

import com.zhilian.rf_qims.base.BaseListActivity;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description312;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description316;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.technical.personnel.Description413;

import java.util.List;

/**
 * 安装操作
 */
public class InstallationOperation extends BaseListActivity {

    // 模块标题
    @Override
    public String moduleTitle() {
        return "4.1 安装操作";
    }

    @Override
    public String totalScore() {
        return null;
    }

    // 描述列表的数据
    @Override
    public List<CaConfigJson> mList() {
        return CaConfigDao.query(2, "4.1%");
    }

    // 描述列表的事件监听
    @Override
    public void onListener(int position, int viewType, String item, String description) {
        Intent intent;
        switch (position){
            case 0:
            case 1:
                intent = new Intent(InstallationOperation.this, Description316.class);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description16);
                break;
            case 2:
                intent = new Intent(InstallationOperation.this, Description413.class);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description24);
                break;
            case 3:
            case 4:
                intent = new Intent(InstallationOperation.this, Description312.class);
                startActivity(intent);
                //DialogUtil.createDiyDialog(this, R.layout.dialog_description14);
                break;
        }
    }
}
