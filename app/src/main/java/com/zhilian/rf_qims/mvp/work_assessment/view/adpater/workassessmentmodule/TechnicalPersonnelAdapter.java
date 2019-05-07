package com.zhilian.rf_qims.mvp.work_assessment.view.adpater.workassessmentmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.Common;
import com.zhilian.rf_qims.entity.CaConfigDao;
import com.zhilian.rf_qims.entity.CaConfigJson;
import com.zhilian.rf_qims.entity.CaTestDao;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.util.DoubleUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 从业能力（技术人员）的适配器
 */
public class TechnicalPersonnelAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<CaConfigJson> mList;
    private String cid;
    private double point = 0;
    private int[] flag;

    public TechnicalPersonnelAdapter(Context mContext, List<CaConfigJson> mList, Long wid) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
        this.cid = String.valueOf(wid);
        this.flag = new int[mList.size()];
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int sum = 0;
        double[] allScore;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.activity_technical_personnel_item, null);
        }
        TextView item = convertView.findViewById(R.id.item);
        TextView description = convertView.findViewById(R.id.description);
        TextView score = convertView.findViewById(R.id.score);

        List<CaTestJson> caTestJsonList = CaTestDao.query(cid,mList.get(position).getSN());
        if (caTestJsonList.size() > 0){
            int number;
            if (caTestJsonList.get(0).getITEM().equals("2.6.1")){
                number = 0;
            }else {
                number = CaConfigDao.query(caTestJsonList.get(0).getITEM()).get(0).getNUMBER(); //配置表人员或设备数量
            }
            if (caTestJsonList.size() > 1){ //当可能是多个人员或设备
                allScore = new double[caTestJsonList.size()];
                for (int i = 0; i < caTestJsonList.size(); i++){
                    if (caTestJsonList.get(i).getSCORE() != null){ //获取所有检测的分数
                        if (caTestJsonList.get(i).getSCORE().equals("")){
                            allScore[i] = 0.00;
                        }else {
                            allScore[i] = Double.parseDouble(caTestJsonList.get(i).getSCORE());
                        }
                    }
                    if (caTestJsonList.get(i).getSTATUS() != null){ //获取完成的数量
                        if (caTestJsonList.get(i).getSTATUS().equals("1")){
                            sum = sum + 1;
                        }
                    }
                }
                //算得分以及判断是否改变完成背景色
                Arrays.sort(allScore); //升序排序
                if (allScore.length >= number){
                    for (int i = 0; i < number; i++){ // 循环获取分数最大的number个，从最后往前面获取，因为升序排序过
                        point = DoubleUtil.add(point, allScore[allScore.length - (i+1)]);
                    }
                }else {
                    for (int i = 0; i < allScore.length; i++){
                        point = DoubleUtil.add(point, allScore[i]);
                    }
                }
                if (sum >= number){ //判断是否完成
                    flag[position] = 1; //用于解决list完成状态错乱解决
                }
            }else {                                 //只创建了单个检测数据（可能是规定多人只创建了1人）
                if (caTestJsonList.get(0).getSCORE() != null){
                    point = Double.valueOf(!caTestJsonList.get(0).getSCORE().equals("") ? caTestJsonList.get(0).getSCORE() : "0.00");
                    if (number == 1 || number == 0){ //当配置规定为1人
                        if (caTestJsonList.get(0).getSTATUS().equals("1")){
                            flag[position] = 1; //用于解决list完成状态错乱解决
                        }
                    }
                }else {
                    point = 0.00;
                }
            }
        }else {
            point = 0.00;
        }
        item.setText(mList.get(position).getSN());
        description.setText(mList.get(position).getTITLE());
        score.setText(String.valueOf(point));// mList.get(position).getScore()+""
        if (flag[position] == 1){
            score.setBackgroundColor(mContext.getResources().getColor(R.color.holo_green_light));//状态为已完成把分值背景色变成绿色
        }else {
            score.setBackground(mContext.getResources().getDrawable(R.drawable.style_edittext)); //其他为正常方框
        }
        point = 0.00;
        return convertView;
    }
}
