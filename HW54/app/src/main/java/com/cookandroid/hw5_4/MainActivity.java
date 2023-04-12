package com.cookandroid.hw5_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    myPictureView myPicture;
    TextView textNumber;
    int curNum = 1;
    File[] imageFiles;
    String imageFname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어 (변경)");
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        textNumber = (TextView) findViewById(R.id.textNumber);
        myPicture = (myPictureView) findViewById(R.id.myPictureView1);

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures").listFiles();
        imageFname = imageFiles[curNum].toString();
        myPicture.imagePath = imageFname;
        textNumber.setText("1/" + (imageFiles.length - 1));

        btnPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum <= 1) {
                    curNum = imageFiles.length - 1;
                } else {
                    curNum--;
                }
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
                textNumber.setText(curNum + "/" + (imageFiles.length - 1));
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum >= imageFiles.length - 1) {
                    curNum = 1;
                } else {
                    curNum++;
                }
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
                textNumber.setText(curNum + "/" + (imageFiles.length - 1));
            }
        });
    }
}