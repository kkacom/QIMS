package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zhilian.api.StrKit;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.common.BusinessContant;
import com.zhilian.rf_qims.json.T_Department;
import com.zhilian.rf_qims.json.T_Selectman;
import com.zhilian.rf_qims.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程环节选择用户的适配器
 */
public class SelectmanAdapter extends BaseAdapter {
    private List<T_Selectman> list = null;
    private List<T_Department> deptlist = null;
    private Context mContext;
    private LayoutInflater mInflater;
    private CheckBox selectman;
    private TextView title;
    private static HashMap<Integer, Boolean> isSelected;private int sum = 0;
	private String mens, menid, mensid = "", lid = "id", pid = "pid", did = "type", name = "name";
	BusinessContant bc = new BusinessContant();
    public HashMap<Integer, Boolean> state = new HashMap<Integer, Boolean>();
    private List<Map<String,String>> countlist = new ArrayList<>();
    private HashMap<String, Integer> menmap = new HashMap<>();
    //不生成标题，直接显示所有人
	public SelectmanAdapter(List<T_Selectman> list, Context context) {
		this.list = list;
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}
    //此方法暂时唯独初审分办使用（生成部门标题，给每个人员分布所属部门）
	public SelectmanAdapter(List<T_Selectman> list, List<T_Department> deptlist, Context context) {
        this.list = list;
        this.deptlist = deptlist;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        initDeptClassify(list);
    }

    @Override
    public int getCount() {
	    if (deptlist != null && deptlist.size() > 0){
            return countlist.size();
        }else {
	        Log.e("list的长度", String.valueOf(list.size()));
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (deptlist != null && deptlist.size() > 0){
            //获取不同的布局进行listView赋值
            if (countlist.get(position).size() == 2){ //当为标题
                convertView = mInflater.inflate(R.layout.step_item_title_layout, null);
                title = convertView.findViewById(R.id.stepid);
                title.setText(countlist.get(position).get(name));
            }else { //当为人员
                convertView = mInflater.inflate(R.layout.step_item_layout, null);
                selectman = (CheckBox) convertView.findViewById(R.id.stepid);
                Map<String,String> map = countlist.get(position);
                selectman.setText(map.get(name) + " ( " + map.get(pid) + " )");
                menmap.put(map.get(name) + " ( " + map.get(pid) + " )", Integer.valueOf(map.get(lid)));
                checkBoxListener(selectman,position);
            }
        }else {
            convertView = mInflater.inflate(R.layout.step_item_layout, null);
            selectman = (CheckBox) convertView.findViewById(R.id.stepid);
            selectman.setText(list.get(position).getName() + " ( " + list.get(position).getPid() + " : " + list.get(position).getD_id() + " )");
            menmap.put(list.get(position).getName() + " ( " + list.get(position).getPid() + " : " + list.get(position).getD_id() + " )", list.get(position).getId());
            checkBoxListener(selectman,position);
        }

        if ( deptlist != null && deptlist.size() > 0){
            if (position == (list.size()+deptlist.size())){//刷新完数据清空部门科室
                deptlist.clear();
                Log.e("是否清空集合", String.valueOf(deptlist.size()));
            }
        }
        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        SelectmanAdapter.isSelected = isSelected;
    }

    public String arrayDelstr(String s, String s1) {
        String tmpstr = "";
        if (s == null)
            s = "";
        String str[] = s.split(",");
        for (int i = 0; i < str.length; i++) {
            if (!str[i].equals(s1)) {
                tmpstr += "," + str[i];
            }
        }
        return tmpstr;
    }

    //复选按钮监听
    private void checkBoxListener(CheckBox selectman,int position){
        selectman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // 指定人员
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                mens = String.valueOf(buttonView.getText());
                menid = String.valueOf(menmap.get(mens));
                if (isChecked) {
                    state.put(position, isChecked);
                    mensid += "," + menid;
                    sum++;
                } else {
                    state.remove(position);
                    mensid = arrayDelstr(mensid, menid);
                    sum--;
                }
                bc.setListCheckedJudge(sum);//用于判断是否有选中人，有-上传，无-提示请选中
                //Log.e("sum值", String.valueOf(sum));
                if (StrKit.notBlank(mensid)) {
                    if (mensid.charAt(0) == ',') {
                        mensid = mensid.substring(1);
                    }
                    bc.setUserid(mensid);
                }
                LogUtil.e(mensid);
            }
        });
        selectman.setChecked((state.get(position) == null ? false : true));
    }

    //初始化（把所有人按部门分类）
    private void initDeptClassify(List<T_Selectman> list){
        for (int i = 0 ; i < deptlist.size() ; i++ ){   //小到大排序装填所有人，先装部门
            String flag;
            HashMap<String,String> dept = new HashMap<>();
            flag = String.valueOf(deptlist.get(i).getId());
            dept.put(did,String.valueOf(deptlist.get(i).getId()));
            dept.put(name,deptlist.get(i).getFname());
            countlist.add(dept);
            for (int j = 0 ; j < list.size() ; j++){    //然后再装部门所属人。全部是如此
                if (String.valueOf(flag).equals(list.get(j).getD_id())){
                    HashMap<String,String> selectman = new HashMap<>();
                    selectman.put(lid, String.valueOf(list.get(j).getId()));
                    selectman.put(pid,list.get(j).getPid());
                    selectman.put(did,list.get(j).getD_id());
                    selectman.put(name,list.get(j).getName());
                    countlist.add(selectman);
                }
            }
        }
    }
}
