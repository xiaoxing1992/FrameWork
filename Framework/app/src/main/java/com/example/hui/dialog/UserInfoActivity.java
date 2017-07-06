package com.example.hui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hui.dialog.builder.CommonDialog;
import com.example.hui.ioc.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by hui on 2016/8/24.
 */
public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ALBUM_OK = 0x0011;
    private static final int CUT_OK = 0x0013;
    private static final int CAMERA_REQUEST = 0x0012;

    private File tempFile;

    private ImageView mUserLogoIv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_info);

        mUserLogoIv = (ImageView) findViewById(R.id.user_logo);

        mUserLogoIv.setOnClickListener(this);

        // 临时存放拍照的文件
        tempFile = new File(Environment.getExternalStorageDirectory(),"temp.png");
    }

    @Override
    public void onClick(View view) {
        // 弹出选择照片或是拍照的dialog
        showDialog();
    }

    /**
     * 弹出一个弹出框
     */
    private void showDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(this);

        final CommonDialog dialog = builder.fromBottom().setView(R.layout.photo_choose_dialog)
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT).create();
        dialog.show();

        dialog.setOnClicklisenter(R.id.user_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setText(R.id.user_cancel,"CANCEL");





      /*  // 4.因为有头部  ， 那么需要给他设置一些参数    ，用style 来配置
        // 1.利用Dialog
        final Dialog dialog = new Dialog(this,R.style.dialog);

        // 3.利用View.inflate 获取dialog需要显示的布局
        View dialogView = View.inflate(this,R.layout.photo_choose_dialog,null);

        // 2.设置dialog的显示布局  Java里面的思想要什么你就给什么
        dialog.setContentView(dialogView);

        // 5.从底部弹出 这是一个动画   控制动画我们都是用View
        // 5.1 获取弹出框的View
        Window window = dialog.getWindow();
        // 5.2 设置动画  resouceId 又是一个style
        window.setWindowAnimations(R.style.main_menu_animstyle);

        // 5.3 固定在底部
        window.setGravity(Gravity.BOTTOM);

        // 5.4宽度全屏
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 6.处理点击事件
        Button cancelBt = (Button) dialogView.findViewById(R.id.user_cancel);
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 关闭dialog
                dialog.dismiss();
            }
        });

        Button imageDepotBt = (Button) dialogView.findViewById(R.id.image_depot);
        imageDepotBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 选择照片   利用系统   隐示意图
                Intent albumIntent = new Intent(Intent.ACTION_PICK);
                // 匹配类型 只需要匹配一个就可以了
                albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
                // 为什么不是直接开启  选完相片之后 startActivityForResult 需要获取图片信息
                startActivityForResult(albumIntent, ALBUM_OK);
                dialog.dismiss();
            }
        });

        Button photoCamreBt = (Button) dialogView.findViewById(R.id.photo_camre);
        photoCamreBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 拍照
                Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                // MediaStore.EXTRA_OUTPUT 调用系统拍完之后的照片，就会放到这个tempFile文件
                getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
                startActivityForResult(getImageByCamera, CAMERA_REQUEST);
                dialog.cancel();
            }
        });


        // 显示dialog
        dialog.show();*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){// resultCode是相册应用Activity给我们 RESULT_OK -1
            if(requestCode == ALBUM_OK){
                // 相片选择成功之后  数据在data里面
                Uri uri = data.getData(); // 其实就是一个路径  可以通过uri去获取文件路径  ， BitmapFactory解析Bitmap

                // 一定要裁剪  并且需要裁剪成为  1:1  调用系统的裁剪方法
                clipImage(uri);

            }

            if(requestCode == CAMERA_REQUEST){// 拍照成功  我们照片 给他设置了一个临时文件
                clipImage(Uri.fromFile(tempFile));
            }

            if(requestCode == CUT_OK){
                // 获取裁剪的图片数据
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap bitmap = extras.getParcelable("data");
                    mUserLogoIv.setImageBitmap(bitmap);
                    // 提交到服务器  上传提交到服务器  File
                    // 需要把裁剪完后的图片传到服务器  Bitmap  -- > File
                    // 1.把bitmap保存到文件  tempFile
                    saveBitmapToFile(bitmap);
                }
            }
        }
    }

    private void saveBitmapToFile(Bitmap bitmap) {
        try {
            OutputStream os = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 调用系统的裁剪方法
     */
    private void clipImage(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        // 数据 uri 代表裁剪哪一张
        intent.setDataAndType(uri, "image/*");
        // 传递数据
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例，这里设置的是正方形（长宽比为1:1）
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        // 你待会裁剪完之后需要获取数据   startActivityForResult
        startActivityForResult(intent, CUT_OK);
    }
}
