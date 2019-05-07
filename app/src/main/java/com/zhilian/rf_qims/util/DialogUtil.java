package com.zhilian.rf_qims.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.zhilian.rf_qims.R;

import java.util.Date;
import java.util.HashSet;

/**
 * Dialog对话框的工具管理类
 */
public class DialogUtil {
    private Context context;

    /**
     * 对话框
     * 目前用于从业评估的删除
     * @param context
     */
    public static void skipSeting(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = null;// 对话框对象

        //builder.setTitle("警告信息");// 标题
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setMessage("下载同步数据将覆盖原有数据,请确认？");
        builder.setCancelable(false);// 不能取消这个对话框，点击了对话框里的按钮会自动关闭

        // 确定
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Intent intent =new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
            }
        });

        // 取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    /**
     * 对话框
     * 目前用于从业评估的删除
     * @param context
     */
    public static void createAlertDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = null;// 对话框对象

        //builder.setTitle("警告信息");// 标题
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setMessage("确认删除该企业在本地的所有数据吗？");
        builder.setCancelable(false);// 不能取消这个对话框，点击了对话框里的按钮会自动关闭

        // 确定
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "已删除", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // 取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "取消", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    /**
     * 对话框
     * @param context
     */
    public static void createAlertDialog1(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = null;// 对话框对象

        //builder.setTitle("警告信息");// 标题
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setMessage("此操作会覆盖上次企业资料和打分数据，确认继续吗？");
        builder.setCancelable(false);// 不能取消这个对话框，点击了对话框里的按钮会自动关闭

        // 确定
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // 取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog = builder.create();
        dialog.show();
    }

    /**
     * 对话框
     * 目前用于从业评估的删除
     * @param context
     */
    public static void createAlertDialog2(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = null;// 对话框对象

        //builder.setTitle("警告信息");// 标题
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setMessage("提示：考评完成后，将不可修改考评数据，是否确认考评完成？");
        builder.setCancelable(false);// 不能取消这个对话框，点击了对话框里的按钮会自动关闭

        // 确定
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "已删除", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // 取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "取消", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    /**
     * 自定义对话框（诚信评估弹框）
     */
    public static void createDiyDialog(Context context, int layout, final int eid, final int cid) {
        View view = CommonUtils.getView(layout);
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setContentView(view);
        dialog.show();

        TextView upload = (TextView) view.findViewById(R.id.upload);
        TextView delete = (TextView) view.findViewById(R.id.delete);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        // 上传
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PfAssessmentDemoTestActivity.upload(eid, cid);
                dialog.dismiss();
            }
        });
        // 删除
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PfAssessmentDemoTestActivity.delete(eid, cid);
                dialog.dismiss();
            }
        });
        // 取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    //public static boolean def_chooesd[] = { true, false, false };// 标示默认需要被选中的选项
    public static HashSet<Integer> h_choosed = new HashSet<Integer>();
    /**
     * 创建复选框对话框
     * @param context
     * @param data 对话框列表数据
     * @param view 显示选中项的TextView
     */
    public static void createChecksDialog(final Context context, final String[] data, final TextView view) {
        h_choosed.add(0);
        boolean[] def_chooesd = new boolean[data.length];
        for (int i = 0 ; i < data.length ; i++){
            if (i == 0 ){
                def_chooesd[i] = true;
            }else {
                def_chooesd[i] = false;
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("考评实操人员");
        builder.setCancelable(true);

        builder.setMultiChoiceItems(data, def_chooesd,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            h_choosed.add(which);
                        } else {
                            h_choosed.remove(which);
                        }
                    }
                });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = "";
                for (Integer i : h_choosed) {
                    str += "," + data[i];
                }
                view.setText(str.substring(1));
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    /**
     * 时间选择对话框
     */
    @SuppressLint({"ValidFragment","NewApi"})
    public static class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Date date = new Date();// 用于弹出对话框时，时间为当前时间
            TimePickerDialog tdp = new TimePickerDialog(this.getActivity(),
                                       this, date.getHours(), date.getMinutes(),
                                       DateFormat.is24HourFormat(this.getActivity()));
            // 创建一个显示时间设置的Dialog（Fragment）
            return tdp;
        }

        // 设置时间事件
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            ToastUtils.show("set:  "+hourOfDay+":"+minute);
        }

    }

    /**
     * 日期选择对话框
     */
    @SuppressLint({"ValidFragment","NewApi"})
    public static class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        TextView textView;
        public DateDialog(TextView textView){
            this.textView = textView;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Date date = new Date();
            DatePickerDialog dpd = new DatePickerDialog(this.getActivity(),this,date.getYear()+1900,date.getMonth(),date.getDate());
            return dpd;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
							  int dayOfMonth) {
            textView.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            //ToastUtils.show(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
        }

    }

    /**根据布局自定义对话样式*/
    public static class dialog{
        public TextView old_value;
        public EditText new_value;
        public EditText desc;
        public Button btn_sure;
        public Button btn_cancel;
        public final Dialog dialog;
        public dialog(Context context){
            //AlertDialog.Builder builder = new AlertDialog.Builder(context);//不用这个是应为有白色框底
            dialog = new Dialog(context, R.style.MyDialogTwo);//框体样式
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.update_sample_check_dialog, null);//布局
            dialog.setContentView(v);//填充内容
            dialog.setCancelable(true);
            old_value = v.findViewById(R.id.old_value);
            new_value = v.findViewById(R.id.new_value);
            desc = v.findViewById(R.id.desc);
            btn_sure = v.findViewById(R.id.dialog_btn_sure);
            btn_cancel = v.findViewById(R.id.dialog_btn_cancel);
            dialog.show();
        }
    }
}
