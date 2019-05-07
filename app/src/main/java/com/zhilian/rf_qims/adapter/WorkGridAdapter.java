package com.zhilian.rf_qims.adapter;

import android.view.View;
import android.widget.ImageView;

import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.bean.WorkResBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-9-26.
 */
public class WorkGridAdapter extends BaseGridViewAdapter<WorkResBean, WorkGridAdapter.ViewHolder> {
    public WorkGridAdapter(List<WorkResBean> list) {
        super(list);
    }

    @Override
    protected int layoutRes() {
        return R.layout.work_grid_item;
    }

    @Override
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void initView(int i) {
        holder.mIvItem.setBackgroundResource(list.get(i).getResId());
    }


    static class ViewHolder {
        @BindView(R.id.iv_item)
        ImageView mIvItem;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
