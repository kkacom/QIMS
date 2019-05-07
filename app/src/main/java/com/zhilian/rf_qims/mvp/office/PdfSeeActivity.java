package com.zhilian.rf_qims.mvp.office;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.zhilian.rf_qims.R;
import com.zhilian.rf_qims.entity.Sample;

import java.io.File;
import java.net.URI;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PdfSeeActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_sign)
    ImageView ivSign;
    @BindView(R.id.iv_print)
    ImageView ivPrint;
    @BindView(R.id.topbar)
    RelativeLayout topbar;
    @BindView(R.id.pdfView)
    PDFView pdfView;
    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_see);
        ButterKnife.bind(this);
        sample = (Sample) getIntent().getSerializableExtra("sample");
        if (getIntent().getIntExtra("type", 0) == 1) {
            ivSign.setVisibility(View.INVISIBLE);
        } else {
            ivSign.setVisibility(View.VISIBLE);
        }
        if (sample==null){
            tvTitle.setText("浏览文件");
            ivSign.setVisibility(View.INVISIBLE);
        }else {
            tvTitle.setText(sample.getSampleModel());
        }
        File file=new File(getIntent().getStringExtra("path"));
        Log.d("pdfsee",getIntent().getStringExtra("path")+"//"+file.exists());
        pdfView.fromFile(new File(getIntent().getStringExtra("path")))
//                .pages(0, 2, 3, 4, 5); // 把0 , 2 , 3 , 4 , 5 过滤掉
                //是否允许翻页，默认是允许翻页
                .enableSwipe(true)
                //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .swipeHorizontal(false)
                //
                .enableDoubletap(false)

                //设置页面滑动监听
//                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
                // 首次提交文档后调用。
//                .onRender(onRenderListener)
                // 渲染风格（就像注释，颜色或表单）
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                // 改善低分辨率屏幕上的渲染
                .enableAntialiasing(true)
                // 页面间的间距。定义间距颜色，设置背景视图
                .spacing(0)
                .load();
    }

    @OnClick({R.id.iv_back, R.id.iv_sign, R.id.iv_print})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_sign:
                Intent intent = new Intent(PdfSeeActivity.this, SignActivity.class);
                intent.putExtra("sample", sample);
                startActivity(intent);
                break;
            case R.id.iv_print:
                try {
                    //只使用指定软件来打印
                    String param = getIntent().getStringExtra("path");
                    String cls = "com.dynamixsoftware.printershare.ActivityPrintDocuments";
                    String type = "application/pdf";
                    Intent intent1 = new Intent();
                    ComponentName comp = new ComponentName(
                            "com.dynamixsoftware.printershare", cls);
                    intent1 = new Intent();
                    intent1.setComponent(comp);
                    intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent1.setAction("android.intent.action.VIEW");
                    Uri uri = FileProvider.getUriForFile(this, "com.zhilian.rf_qims.fileprovider", new File(param));
                    intent1.setDataAndType(uri, type);
                    startActivity(intent1);

                    //寻找手机能打开路劲文件的软件，使用printerShare会无法呈现PDF
                    /*String param = getIntent().getStringExtra("path");
                    Intent intent2 = new Intent();
                    intent2.setAction(android.content.Intent.ACTION_VIEW);
                    Uri uri = FileProvider.getUriForFile(this, "com.zhilian.rf_qims.fileprovider", new File(param));
                    intent2.setDataAndType(uri, "application/pdf");
                    intent2.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent2);*/
                } catch (Exception e) {
                    Log.e("寻找打印应用",e.getMessage());
                    Toast.makeText(PdfSeeActivity.this, "没有检测到已安装的打印应用", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
