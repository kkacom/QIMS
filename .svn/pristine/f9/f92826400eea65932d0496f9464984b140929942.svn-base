package com.zhilian.rf_qims.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zhilian.rf_qims.R;


/**
 * Created by pc-home on 2016/3/30.
 * 处理某事情时的弹出窗体（正在进行什么中）
 */
public class DialogProgress {
    private LayoutInflater inflater;
    private Context mContext;
    private WindowManager.LayoutParams lp;
    MaterialDialog materialDialog;

    public DialogProgress(Context context, String alertMsg) {
        this.mContext = context;
//        inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.dialogmprogress, null);
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        LinearLayout lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
//        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
//                .getWidth() * 0.41), LinearLayout.LayoutParams.WRAP_CONTENT));
//
//        TextView textView =(TextView)view.findViewById(R.id.tip);
//        textView.setText(alertMsg);
//        setContentView(view);
//        materialDialog = new MaterialDialog.Builder(context)
//                .content(alertMsg)
//                .progress(true, 0).widgetColor(context.getResources().getColor(R.color.app_color_blue)).build();
        materialDialog=new MaterialDialog.Builder(context).content(alertMsg).progress(true,0)
                .widgetColor(context.getResources().getColor(R.color.app_color_blue))
                .contentColor(context.getResources().getColor(R.color.black)).build();

        //        new MaterialDialog.Builder(MainActivity.this)
//                .title("123")
//                .content("456")
//                .negativeText("取消")
//                .positiveText("确定")
//                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                    }
//                })
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                    }
//                })
//                .show();
    }

    public void show() {
        materialDialog.show();
    }

    public void dismiss() {
        if (isValidContext(mContext) && materialDialog.isShowing()) {
            materialDialog.dismiss();
        }
//        materialDialog.dismiss();
    }

    private boolean isValidContext(Context c) {

        Activity a = (Activity) c;
        if (Build.VERSION.SDK_INT >= 17) {
            if (a.isDestroyed() || a.isFinishing()) {
                Log.i("YXH", "Activity is invalid." + " isDestoryed-->" + a.isDestroyed() + " isFinishing-->" + a.isFinishing());
                return false;
            } else {
                return true;
            }
        } else {
            if (a.isFinishing()) {
//                Log.i("YXH", "Activity is invalid." + " isDestoryed-->" + a.isDestroyed() + " isFinishing-->" + a.isFinishing());
                return false;
            } else {
                return true;
            }
        }

    }
    public  void setCanceledOnTouchOutside(boolean cancel ){
        materialDialog.setCanceledOnTouchOutside(cancel);
    }
}