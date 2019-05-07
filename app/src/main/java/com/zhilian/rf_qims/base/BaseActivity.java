package com.zhilian.rf_qims.base;

//import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.MotionEvent;

import com.zhilian.App;
import com.zhilian.rf_qims.widget.DialogProgress;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-9-21.
 */
public abstract class BaseActivity<P> extends AppCompatActivity {
    public static final Integer RESULT_CODE=0x2;
    //private CompositeDisposable mCompositeDisposable;
    com.colin.base.ActivityManager manager;
    protected P presenter= createPresenter();
    public DialogProgress dialogProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LogUtil.e("-->"+getClass().getName());
        if (null == manager){
            manager = ((App)getApplication()).getManager();
        }
        setContentView(loadLayoutResource());
        ButterKnife.bind(this);
        manager.addActivity(this);
        dialogProgress=new DialogProgress(this,"正在加载中...");
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected  void setContentTransition(){
        //overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());
    }

    protected abstract int loadLayoutResource();

    protected abstract void initView();

    protected P createPresenter(){
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        LogUtil.e("-->"+this.getClass().getName());
        manager.removeActivity(this);

    }
    // 点击屏幕关闭软键盘
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        return super.onTouchEvent(event);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void finish() {
        super.finish();
        setContentTransition();
    }
}
