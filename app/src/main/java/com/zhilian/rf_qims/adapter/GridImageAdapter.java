package com.zhilian.rf_qims.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.dao.GreenDaoManager;
import com.zhilian.rf_qims.dao.ScenePicDao;
import com.zhilian.rf_qims.entity.ScenePic;

import java.util.ArrayList;
import java.util.List;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.pictureselector.adapter
 * email：893855882@qq.com
 * data：16/7/27
 */
public class GridImageAdapter extends
        RecyclerView.Adapter<GridImageAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<ScenePic> list = new ArrayList<>();

    private Context context;

    /**
     * 点击添加图片跳转
     */


    public interface onAddPicClickListener {
        void onAddPicClick();
    }

    public GridImageAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }


    public void setList(List<ScenePic> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImg;
        LinearLayout ll_del;
        TextView tv_status;

        public ViewHolder(View view) {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.fiv);
            ll_del = (LinearLayout) view.findViewById(R.id.ll_del);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
        }
    }

    @Override
    public int getItemCount() {

        return list.size();

    }


    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.gv_filter_image,
                viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.ll_del.setVisibility(View.VISIBLE);
        viewHolder.ll_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = viewHolder.getAdapterPosition();
                // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                if (index != RecyclerView.NO_POSITION) {
                    ScenePic scenePic = GreenDaoManager.getInstance().getNewSession().getScenePicDao().queryBuilder().where(ScenePicDao.Properties.Path.eq(list.get(index).getPath())).unique();
                    if (scenePic != null) {
                        GreenDaoManager.getInstance().getNewSession().getScenePicDao().delete(scenePic);
                    }
                    list.remove(index);
                    notifyItemRemoved(index);
                    notifyItemRangeChanged(index, list.size());
                }
            }
        });
        ScenePic scenePic = list.get(position);
//        LocalMedia media = list.get(position);
//        media.getCompressPath()
        if (scenePic.getIsupload() == 0) {
            viewHolder.tv_status.setTextColor(context.getResources().getColor(R.color.app_color_blue));
            viewHolder.tv_status.setText("已上传");
        } else {
            viewHolder.tv_status.setTextColor(Color.BLACK);
            viewHolder.tv_status.setText("未上传");
        }


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.color.color_f6)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(viewHolder.itemView.getContext())
                .load(scenePic.getPath())
                .apply(options)
                .into(viewHolder.mImg);

        //itemView 的点击事件
        if (mItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    mItemClickListener.onItemClick(adapterPosition, v);
                }
            });
        }

    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
