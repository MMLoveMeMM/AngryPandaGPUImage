package com.panda.org.angrypandagpuimage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.panda.org.imagefilter.CameraActivity;
import com.panda.org.imagewrapper.ImageFilterActivity;

public class MainActivity extends Activity {

    private Button mButton;
    private Button mCameraBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton=(Button)findViewById(R.id.buttom);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,ImageFilterActivity.class);
                startActivity(in);
            }
        });

        mCameraBtn=(Button)findViewById(R.id.camerabtn);
        mCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,CameraActivity.class);
                startActivity(in);
            }
        });

    }
}
