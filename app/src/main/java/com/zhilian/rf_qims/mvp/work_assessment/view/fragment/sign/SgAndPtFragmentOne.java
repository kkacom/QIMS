package com.zhilian.rf_qims.mvp.work_assessment.view.fragment.sign;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.mvp.work_assessment.view.activity.pfassessment.UserSignActivity;
import com.zhilian.rf_qims.util.CommonUtils;
import com.zhilian.rf_qims.util.GetDate;
import com.zhilian.rf_qims.util.SPUtil;
import com.zhilian.rf_qims.util.ToastUtils;
import com.zhilian.rf_qims.widget.MediaLoader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import retrofit2.http.Url;

/**
 * Created by luocong on 2017/4/10.
 */

public class SgAndPtFragmentOne extends Fragment {
    /**
     * 这是保存签名图片的地址
     */
    public static String USER_SIGN_PATH = "";
    @BindView(R.id.tv_take_photo)
	TextView mTvTakePhoto;
    @BindView(R.id.tv_save_photo)
	TextView mTvSavePhoto;
    @BindView(R.id.tv_clear_photo)
	TextView mTvClearPhoto;
    @BindView(R.id.iv_user_photo)
    RoundedImageView mIvUserPhoto;
    @BindView(R.id.iv_sign)
	ImageView mIvSign;
    @BindView(R.id.tv_take_sign)
	TextView mTvTakeSign;
    @BindView(R.id.tv_save_sign)
	TextView mTvSaveSign;
    @BindView(R.id.tv_clear_sign)
	TextView mTvClearSign;

    private Activity mActivity;
    public final static int SIGN_RESULT = 102;
    public final static int ALBUM_RESULT = 101;
    private Bitmap mBitmap;
    public static final String SD_APP_DIR_NAME = "RF-QIMS-Photo"; //存储程序在外部SD卡上的根目录的名字
    public static final String PHOTO_DIR_NAME = "RFImage";    //存储照片在根目录下的文件夹名字
    File file;
    Uri uri;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = CommonUtils.getView(R.layout.fragment_sign_demo);
        ButterKnife.bind(this, view);
        //这是保存签名的路径
        USER_SIGN_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "signOne" + ".png";
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
        mBitmap = SPUtil.getBitmap(getContext(),"PHOTOTONE",null);
        if(mBitmap!=null){
            mIvUserPhoto.setImageBitmap(mBitmap);
        }
        mIvSign.setImageBitmap(SPUtil.getBitmap(getActivity(), "SIGNONE", null));
        mTvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Album.startAlbum(SgAndPtFragmentOne.this,ALBUM_RESULT,1);
                Album.image(getContext()) // Image selection.
                    .singleChoice()
                    .camera(true)
                    .columnCount(2)
                    //.checkedList(mAlbumFiles)
                    //.filterSize() // Filter the file size.
                    //.filterMimeType() // Filter file format.
                    //.filePath() // File save path, not required.
                    .onResult(new Action<ArrayList<AlbumFile>>() {
                        @Override
                        public void onAction(@android.support.annotation.NonNull ArrayList<AlbumFile> result) {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            int inSampleSize = 4;
                            //Options 只保存图片尺寸大小，不保存图片到内存
                            //options.inJustDecodeBounds = true;//为true的时候不会真正加载图片，而是得到图片的宽高信息。
                            options.inSampleSize = inSampleSize;
                            if (result != null) {
                                //result.get(0).setBucketName(SD_APP_DIR_NAME + "/" + PHOTO_DIR_NAME + "/");
                                /*result.get(0).setPath(Environment.getExternalStorageDirectory().getAbsolutePath()
                                    + "/" + SD_APP_DIR_NAME + "/" + PHOTO_DIR_NAME + "/" + "per-"
									+ GetDate.getDate() + "jpg" );*/
                                //String imageName  = "per-" + GetDate.getDate() + ".jpg";
                                /*String imageName  = 1 + ".jpg";
                                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                    + "/" + SD_APP_DIR_NAME + "/" + PHOTO_DIR_NAME + "/" , imageName);
                                File file1 = new File(result.get(0).getPath());
                                uri = FileProvider.getUriForFile(mActivity, "com.zhilian.rf_qims.fileprovider", file1);
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), uri);
                                    savePhotoToSD(bitmap);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }*/
                                mBitmap = BitmapFactory.decodeFile(result.get(0).getPath(), options);
                                mIvUserPhoto.setImageBitmap(mBitmap);
                            }else {
                                ToastUtils.show("没有选择图片");
                            }
                        }
                    }).onCancel(new Action<String>() {
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
                intent.putExtra("sign", "signOne");
                startActivityForResult(intent, SIGN_RESULT);
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
                SPUtil.putBitmap(getActivity(),"PHOTOTONE",mBitmap);
                ToastUtils.show("保存照片成功");
            }
        });
        mTvSaveSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bm = BitmapFactory.decodeFile(USER_SIGN_PATH);
                SPUtil.putBitmap(getActivity(), "SIGNONE", bm);
                ToastUtils.show("保存签名成功");
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
        if (requestCode == SIGN_RESULT) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(USER_SIGN_PATH);
            mIvSign.setImageBitmap(bm);
        }

    }
*/

    /**
     * 保存照片到SD卡的指定位置
     */
    private void savePhotoToSD(Bitmap bitmap) {
        //Log.d(TAG, "将图片保存到指定位置。");
        //创建输出流缓冲区
        BufferedOutputStream os = null;
        try {
            //设置输出流
            os = new BufferedOutputStream(new FileOutputStream(file));
            //Log.d(TAG, "设置输出流。");
            //压缩图片，100表示不压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            //Log.d(TAG, "保存照片完成。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    //不管是否出现异常，都要关闭流
                    os.flush();
                    os.close();
                    //Log.d(TAG, "刷新、关闭流");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
