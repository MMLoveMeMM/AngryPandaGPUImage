package com.panda.org.imagewrapper;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageBoxBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;

public class ImageFilterActivity extends Activity {

    private ImageView imagefilter;
    private ImageView imageBaohe;
    private ImageView imageUrl;
    private SeekBar mSeekBar;
    private int progress=0;

    private GPUImage gpuImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_filter);

        imageUrl=(ImageView)findViewById(R.id.urlimage);
        imagefilter=(ImageView)findViewById(R.id.image);
        imageBaohe=(ImageView)findViewById(R.id.baohe);
        mSeekBar=(SeekBar)findViewById(R.id.seek);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int gress, boolean fromUser) {
                progress=gress;
                gpuImage.setFilter(new GPUImageSaturationFilter(progress));
                Bitmap bitmap2=gpuImage.getBitmapWithFilterApplied();
                imageBaohe.setImageBitmap(bitmap2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Bitmap bitmap=ImageUtils.getImageFromAssets(ImageFilterActivity.this,"panda.jpg");

        gpuImage=new GPUImage(this);
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageGaussianBlurFilter());
        Bitmap bitmap1=gpuImage.getBitmapWithFilterApplied();

        imagefilter.setImageBitmap(bitmap1);

        gpuImage.setFilter(new GPUImageSaturationFilter(progress));
        Bitmap bitmap2=gpuImage.getBitmapWithFilterApplied();
        imageBaohe.setImageBitmap(bitmap2);

        // filterTask.execute();

    }

    private FilterTask filterTask=new FilterTask();
    class FilterTask extends AsyncTask<Integer,Integer,Bitmap>{

        @Override
        protected Bitmap doInBackground(Integer... integers) {
            // http://f.hiphotos.baidu.com/image/h%3D300/sign=d51dfe7a6c380cd7f91ea4ed9145ad14/ca1349540923dd54332da6a2d809b3de9d824899.jpg
            Bitmap bitmap=ImageUtils.getImageFromURL("http://f.hiphotos.baidu.com/image/h%3D300/sign=d51dfe7a6c380cd7f91ea4ed9145ad14/ca1349540923dd54332da6a2d809b3de9d824899.jpg");

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //super.onPostExecute(bitmap);
            // 使用GPUImage处理图像
            gpuImage = new GPUImage(getApplicationContext());
            gpuImage.setImage(bitmap);
            gpuImage.setFilter(new GPUImageGrayscaleFilter());
            bitmap = gpuImage.getBitmapWithFilterApplied();
            //显示处理后的图片
            imageUrl.setImageBitmap(bitmap);
        }
    }



}
