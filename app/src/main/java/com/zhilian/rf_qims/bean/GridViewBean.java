package com.zhilian.rf_qims.bean;

import android.widget.ImageView;

/**
 * Created by luocong on 2017/3/30.
 */

public class GridViewBean {
    String textName ;
    ImageView GvImageView;

    public GridViewBean() {
    }

    public GridViewBean(String textName, ImageView gvImageView) {
        this.textName = textName;
        GvImageView = gvImageView;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public ImageView getGvImageView() {
        return GvImageView;
    }

    public void setGvImageView(ImageView gvImageView) {
        GvImageView = gvImageView;
    }
}
