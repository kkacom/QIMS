package com.zhilian.rf_qims.widget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;

/**
 * Created by x6ti on 2018/1/29.
 */

public class HorizontalProgress {
    private Context mContext;
    private ProgressDialog mDialog;
    OnCancel onCancel;

    public HorizontalProgress(Context context, final OnCancel onCancel) {
        mContext = context;
        mDialog = new ProgressDialog(mContext, ProgressDialog.THEME_HOLO_LIGHT);
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setMessage("下载中...");
        mDialog.setIndeterminate(false);
        mDialog.setCancelable(false);
        this.onCancel = onCancel;
        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onCancel != null) {
                    onCancel.onCancel();
                }
            }
        });
    }
    public HorizontalProgress(Context context, String mes,final OnCancel onCancel) {
        mContext = context;
        mDialog = new ProgressDialog(mContext, ProgressDialog.THEME_HOLO_LIGHT);
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setMessage(mes);
        mDialog.setIndeterminate(false);
        mDialog.setCancelable(false);
        this.onCancel = onCancel;
        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onCancel != null) {
                    onCancel.onCancel();
                }
            }
        });
    }
    public void show() {
        if (isValidContext(mContext)) {
            mDialog.show();
        }

    }


    public void setProgress(int i) {
        if (mDialog != null) {
            mDialog.setProgress(i);
        }
    }


    public void dismiss() {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
            }

        }
    }
    public boolean isShowing(){
        return mDialog.isShowing();
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

    interface OnCancel {
        public void onCancel();
    }
}
