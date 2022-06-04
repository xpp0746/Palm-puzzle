package com.xpp.handheldpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //再按一次退出游戏
    private long exitTime = 0;
    public void onBackPressed(){
        if (new Date().getTime() - exitTime < 2000){
            finish();
        }   else {
            exitTime = new Date().getTime();
            Toast.makeText(this,"再按一次退出游戏",Toast.LENGTH_SHORT).show();
        }
    }

    //简单模式按钮点击事件
    public void easy_onclick(View view) {
        Intent intent = new Intent(MainActivity.this,EasyActivity.class);
        startActivity(intent);
    }

    //一般模式按钮点击事件
    public void average_onclick(View view) {
        Intent intent = new Intent(MainActivity.this,AverageActivity.class);
        startActivity(intent);
    }

    //困难模式按钮点击事件
    public void difficult_onclick(View view) {
        Intent intent = new Intent(MainActivity.this,DifficultActivity.class);
        startActivity(intent);
    }
}