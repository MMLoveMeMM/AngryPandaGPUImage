package com.panda.org.imagefilter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.seu.magicfilter.MagicEngine;
import com.seu.magicfilter.widget.MagicCameraView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraActivity extends Activity{

    private final int MODE_PIC = 1;
    private final int MODE_VIDEO = 2;
    private int mode = MODE_PIC;
    private boolean isRecording = false;

    private MagicEngine magicEngine;

    private Button mTakePhotoBtn;
    private Button mVedioBtn;
    private Button mModeBtn;

    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); 		// 隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  // 隐藏状态栏

        setContentView(R.layout.activity_camera);

        MagicEngine.Builder builder = new MagicEngine.Builder();
        magicEngine = builder
                .build((MagicCameraView)findViewById(R.id.glsurfaceview_camera));

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        MagicCameraView cameraView = (MagicCameraView)findViewById(R.id.glsurfaceview_camera);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cameraView.getLayoutParams();
        params.width = screenSize.x;
        params.height = screenSize.x * 4 / 3;
        cameraView.setLayoutParams(params);

        initView();

    }

    private void initView(){

        mTakePhotoBtn=(Button)findViewById(R.id.btn_camera_photo);
        mVedioBtn=(Button)findViewById(R.id.btn_camera_vedio);
        mModeBtn=(Button)findViewById(R.id.btn_camera_mode);

        mTakePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        mVedioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeVideo();
            }
        });

        mModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMode();
            }
        });

        animator = ObjectAnimator.ofFloat(mVedioBtn,"rotation",0,360);
        animator.setDuration(500);
        animator.setRepeatCount(ValueAnimator.INFINITE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (grantResults.length != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(mode == MODE_PIC)
                takePhoto();
            else
                takeVideo();
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void switchMode(){
        if(mode == MODE_PIC){
            mode = MODE_VIDEO;
            //btn_mode.setImageResource(R.drawable.icon_camera);
        }else{
            mode = MODE_PIC;
            //btn_mode.setImageResource(R.drawable.icon_video);
        }
    }

    private void takePhoto(){
        magicEngine.savePicture(getOutputMediaFile(),null);
    }

    private void takeVideo(){
        if(isRecording) {
            animator.end();
            magicEngine.stopRecord();
        }else {
            animator.start();
            magicEngine.startRecord();
        }
        isRecording = !isRecording;
    }

    public File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MagicCamera");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINESE).format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }


}
