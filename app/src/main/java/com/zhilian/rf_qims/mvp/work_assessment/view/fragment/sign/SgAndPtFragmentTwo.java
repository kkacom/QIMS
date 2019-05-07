package com.zhilian.rf_qims.mvp.work_assessment.view.fragment.sign;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment.UserSignActivity;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.SPUtil;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.MediaLoader;

import java.io.File;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * Created by luocong on 2017/4/10.
 */

public class SgAndPtFragmentTwo extends Fragment {
    /**这是保存签名图片的地址*/
    public static String USER_SIGN_PATH = "";
    @BindView(R.id.tv_take_photo)
	TextView mTvTakePhoto;
    @BindView(R.id.tv_save_photo)
	TextView mTvSavePhoto;
    @BindView(R.id.tv_clear_photo)
	TextView mTvClearPhoto;
    @BindView(R.id.iv_user_photo)
	ImageView mIvUserPhoto;
    @BindView(R.id.iv_sign)
	ImageView mIvSign;
    @BindView(R.id.tv_take_sign)
	TextView mTvTakeSign;
    @BindView(R.id.tv_save_sign)
	TextView mTvSaveSign;
    @BindView(R.id.tv_clear_sign)
	TextView mTvClearSign;


    public final static int SIGN_RESULT = 102;
    public final static int ALBUM_RESULT = 101;
    private Bitmap mBitmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = CommonUtils.getView(R.layout.fragment_sign_demo);
        ButterKnife.bind(this, view);
        USER_SIGN_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "signTwo" + ".png";
        initView();
        return view;
    }

    private void initView() {
        //初始化Album配置
        Album.initialize(AlbumConfig.newBuilder(getContext())
            .setAlbumLoader(new MediaLoader())
            .setLocale(Locale.getDefault())
            .build());
        //读取保存的位图（图片）
        mBitmap = SPUtil.getBitmap(getContext(),"PHOTOTTWO",null);
        if(mBitmap!=null){
            mIvUserPhoto.setImageBitmap(mBitmap);
        }
        mIvSign.setImageBitmap(SPUtil.getBitmap(getActivity(),"SIGNTWO",null));
        mTvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Album.startAlbum(SgAndPtFragmentTwo.this,ALBUM_RESULT,1);
                Album.camera(getContext()) // Camera function.
                    .image() // Take Picture.
                    //.filePath() // File save path, not required.
                    .onResult(new Action<String>() {
                        @Override
                        public void onAction(@NonNull String result) {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            int inSampleSize = 4;
                            //Options 只保存图片尺寸大小，不保存图片到内存
                            //options.inJustDecodeBounds = true;//为true的时候不会真正加载图片，而是得到图片的宽高信息。
                            options.inSampleSize = inSampleSize;
                            List<String> strings = null;
                            if (result != null) {
                                //strings = Album.parseResult(data);
                                mBitmap = BitmapFactory.decodeFile(result, options);
                                mIvUserPhoto.setImageBitmap(mBitmap);
                            }else {
                                ToastUtils.show("没有选择图片");
                            }
                        }
                    })
                    .onCancel(new Action<String>() {
                        @Override
                        public void onAction(@NonNull String result) {
                        }
                    })
                    .start();
            }
        });
        mTvTakeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserSignActivity.class);
                intent.putExtra("sign", "signTwo");
                startActivityForResult(intent, SIGN_RESULT);
            }
        });

        mTvClearPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvUserPhoto.setImageResource(R.drawable.bg_blank);
                ToastUtils.show("清除照片成功");
            }
        });
        /**
         * 设置图片的采样率，降低图片像素
         * @param filePath
         * @param file
         */
        mTvSavePhoto.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               SPUtil.putBitmap(getActivity(),"PHOTOTTWO",mBitmap);
               ToastUtils.show("保存照片成功");
            }
        });
        mTvSaveSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bm = BitmapFactory.decodeFile(USER_SIGN_PATH);
                SPUtil.putBitmap(getActivity(),"SIGNTWO",bm);
                ToastUtils.show("保存签名成功");
            }
        });
        mTvClearSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvSign.setVisibility(View.INVISIBLE);
                ToastUtils.show("清除签名成功");
            }
        });
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALBUM_RESULT){
            BitmapFactory.Options options = new BitmapFactory.Options();
            int inSampleSize = 4;
            //Options 只保存图片尺寸大小，不保存图片到内存
            //options.inJustDecodeBounds = true;//为true的时候不会真正加载图片，而是得到图片的宽高信息。
            options.inSampleSize = inSampleSize;
            List<String> strings = null;
            if (data != null) {
                strings = Album.parseResult(data);
                mBitmap = BitmapFactory.decodeFile(strings.get(0), options);
                mIvUserPhoto.setImageBitmap(mBitmap);
            }else {
                ToastUtils.show("没有选择图片");
            }

        }
        if (requestCode == SIGN_RESULT){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(USER_SIGN_PATH);
            mIvSign.setImageBitmap(bm);
        }
    }
*/

}
