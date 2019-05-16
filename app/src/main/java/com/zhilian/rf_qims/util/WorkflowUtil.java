package com.zhilian.rf_qims.util;

import android.content.Context;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhilian.rf_qims.util.ContextUtil;
import com.zhilian.rf_qims.api.InQueryMsg;
import com.zhilian.rf_qims.api.JsonStringRequest;
import com.zhilian.rf_qims.util.JsonUtil;
import com.zhilian.rf_qims.util.RequestUtil;
import com.zhilian.rf_qims.adapter.SelectmanAdapter;
import com.zhilian.rf_qims.common.BusinessContant;
import com.zhilian.rf_qims.json.T_Selectman;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkflowUtil {

    private List<T_Selectman> selectmenlist = new ArrayList<T_Selectman>();
    private RadioButton tempButton;
    private RadioGroup radioGroup;
    private TextView tv_title;
    SelectmanAdapter selectmanAdapter;
    private ListView mansListview;

    public String HTTPFasong(Context context, String postData, String url){

        RequestQueue requestQueue = RequestUtil.getRequestQueue();

        JsonRequest jsonRequest = new JsonStringRequest(Request.Method.POST, url, postData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            BusinessContant bc = new BusinessContant();
                            JSONObject dataJson = new JSONObject(response.toString());
                            String type = dataJson.getString("type");
                            String ato, name;
                            int id;
                            selectmenlist.clear();
                            if (type.equals("9")) {
                                Toast.makeText(ContextUtil.getInstance(), "没有下一处理人，请联系管理员！", Toast.LENGTH_LONG).show();
                            } else if (type.equals("step")) {
                                bc.setNexttype("windows");
                                JSONArray was = dataJson.getJSONArray("trans");
                                List<T_Selectman> list = JsonUtil.getselectmanList(was.toString());
                                for (int i = 0; i < list.size(); i++) {

                                    id = list.get(i).getId();
                                    ato = list.get(i).getAto();

                                    tempButton = new RadioButton(context);
                                    tempButton.setText(ato);
                                    tempButton.setTextColor(0xFF505050);
                                    tempButton.setTextSize(16f);
                                    tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
                                    tempButton.setId(id);
                                    tempButton.setText(ato);
                                    //radioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                                    radioGroup.addView(tempButton);
                                    if (i == 0) {
                                        radioGroup.check(tempButton.getId());
                                    }

                                }
                                //selectmanAdapter=new SelectmanAdapter(selectmenlist,context());
                                //stepListView.setAdapter(selectmanAdapter);
                                tv_title.setText("下一环节");
                            } else {
                                bc.setCheckid(dataJson.getString("nextStep"));
                                bc.setNexttype("toast");
                                JSONArray user = dataJson.getJSONArray("user");
                                String amount = dataJson.getString("type");//你跟踪看一下这个值有没有不一样
                                String sname, pname;
                                List<T_Selectman> list = JsonUtil.getselectmanList(user.toString());
                                for (int i = 0; i < user.length(); i++) {
                                    id = list.get(i).getId();
                                    name = list.get(i).getName();
                                    sname = list.get(i).getD_id();
                                    pname = list.get(i).getPid();

                                    if (amount.equals("1")) {
                                        tempButton = new RadioButton(context);

                                        tempButton.setTextSize(16f);
                                        tempButton.setText(name + " ( " + pname + " : " + sname + " )");
                                        tempButton.setPadding(80, 0, 0, 0);  // 设置文字距离按钮四周的距离
                                        tempButton.setTextSize(16f);
                                        tempButton.setId(id);

                                        //radioGroup.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        radioGroup.addView(tempButton);
                                        if (i == 0) {
                                            radioGroup.check(tempButton.getId());
                                        }
                                    } else {
                                        selectmenlist.add(new T_Selectman(id, name, pname, sname));
                                    }
                                }
                                if (!amount.equals("1")) {
                                    selectmanAdapter = new SelectmanAdapter(selectmenlist, context);
                                    mansListview.setAdapter(selectmanAdapter);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context, "网络异常,请刷新或重新登录!", Toast.LENGTH_LONG).show();
                    }
                }
         );
        requestQueue.add(jsonRequest);

        return "1";
    }
}
