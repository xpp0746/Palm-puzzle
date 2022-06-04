package com.xpp.handheldpuzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DifficultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficult);
        //初始化控件
        initView();

        //图片不可移动
        ib00.setClickable(false);
        ib01.setClickable(false);
        ib02.setClickable(false);
        ib03.setClickable(false);
        ib04.setClickable(false);
        ib10.setClickable(false);
        ib11.setClickable(false);
        ib12.setClickable(false);
        ib13.setClickable(false);
        ib14.setClickable(false);
        ib20.setClickable(false);
        ib21.setClickable(false);
        ib22.setClickable(false);
        ib23.setClickable(false);
        ib24.setClickable(false);
        ib30.setClickable(false);
        ib31.setClickable(false);
        ib32.setClickable(false);
        ib33.setClickable(false);
        ib34.setClickable(false);
        ib40.setClickable(false);
        ib41.setClickable(false);
        ib42.setClickable(false);
        ib43.setClickable(false);
        ib44.setClickable(false);

    }




    ImageButton ib00,ib01,ib02,ib03,ib04,ib10,ib11,ib12,ib13,ib14,
            ib20,ib21,ib22,ib23,ib24,ib30,ib31,ib32,ib33,ib34,ib40,ib41,ib42,ib43,ib44;
    Button startBtn;
    TextView timeTv;
    //初始化控件
    private void initView() {
        ib00 = findViewById(R.id.pt_line0_00x00);
        ib01 = findViewById(R.id.pt_line0_00x01);
        ib02 = findViewById(R.id.pt_line0_00x02);
        ib03 = findViewById(R.id.pt_line0_00x03);
        ib04 = findViewById(R.id.pt_line0_00x04);
        ib10 = findViewById(R.id.pt_line1_01x00);
        ib11 = findViewById(R.id.pt_line1_01x01);
        ib12 = findViewById(R.id.pt_line1_01x02);
        ib13 = findViewById(R.id.pt_line1_01x03);
        ib14 = findViewById(R.id.pt_line1_01x04);
        ib20 = findViewById(R.id.pt_line2_02x00);
        ib21 = findViewById(R.id.pt_line2_02x01);
        ib22 = findViewById(R.id.pt_line2_02x02);
        ib23 = findViewById(R.id.pt_line2_02x03);
        ib24 = findViewById(R.id.pt_line2_02x04);
        ib30 = findViewById(R.id.pt_line3_03x00);
        ib31 = findViewById(R.id.pt_line3_03x01);
        ib32 = findViewById(R.id.pt_line3_03x02);
        ib33 = findViewById(R.id.pt_line3_03x03);
        ib34 = findViewById(R.id.pt_line3_03x04);
        ib40 = findViewById(R.id.pt_line4_04x00);
        ib41 = findViewById(R.id.pt_line4_04x01);
        ib42 = findViewById(R.id.pt_line4_04x02);
        ib43 = findViewById(R.id.pt_line4_04x03);
        ib44 = findViewById(R.id.pt_line4_04x04);
        timeTv = findViewById(R.id.pt_time);
        startBtn = findViewById(R.id.pt_Butten_start);
    }


    //时间计时完成并停止完整逻辑
    int time = 0;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message gametime) {
            if (gametime.what==1) {
                time++;
                timeTv.setText("时间："+time+"秒");
                //接收消息的同时再发送一条消息
                handler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };

    //图片行列个数及其总数
    private int imageX = 5;
    private int imageY = 5;
    private int imageCount = imageX*imageY;

    //空白区域的位置
    private int blankSwap = imageCount-1;

    //初始化空白区域的按钮id
    private int blankImageid = R.id.pt_line4_04x04;

    //存储图片的数组
    private int[]image = {R.mipmap.di_00x00,R.mipmap.di_00x01,R.mipmap.di_00x02,R.mipmap.di_00x03,R.mipmap.di_00x04,
            R.mipmap.di_01x00,R.mipmap.di_01x01,R.mipmap.di_01x02,R.mipmap.di_01x03,R.mipmap.di_01x04,
            R.mipmap.di_02x00,R.mipmap.di_02x01,R.mipmap.di_02x02,R.mipmap.di_02x03,R.mipmap.di_02x04,
            R.mipmap.di_03x00,R.mipmap.di_03x01,R.mipmap.di_03x02,R.mipmap.di_03x03,R.mipmap.di_03x04,
            R.mipmap.di_04x00,R.mipmap.di_04x01,R.mipmap.di_04x02,R.mipmap.di_04x03,R.mipmap.di_04x04,};
    private int[]imageIndex = new int[image.length];

    //随机排列数组逻辑,打乱
    private void disruptRandom() {
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }

        //随机选择两个角标交换100次
        int rand1, rand2;

        for (int j = 0; j < 100; j++) {
            //随机生成第一个0-8的角标
            rand1 = (int) (Math.random() * (imageIndex.length - 1));
            //随机生成第二个且不与第一个相同的角标
            do {
                rand2 = (int) (Math.random() * (imageIndex.length - 1));
                if (rand1!=rand2){
                    break;
                }
            } while (true);
            //交换两个角标
            swap(rand1, rand2);
        }
//        随机排列到指定的控件上
        ib00.setImageResource(image[imageIndex[0]]);
        ib01.setImageResource(image[imageIndex[1]]);
        ib02.setImageResource(image[imageIndex[2]]);
        ib03.setImageResource(image[imageIndex[3]]);
        ib04.setImageResource(image[imageIndex[4]]);
        ib10.setImageResource(image[imageIndex[5]]);
        ib11.setImageResource(image[imageIndex[6]]);
        ib12.setImageResource(image[imageIndex[7]]);
        ib13.setImageResource(image[imageIndex[8]]);
        ib14.setImageResource(image[imageIndex[9]]);
        ib20.setImageResource(image[imageIndex[10]]);
        ib21.setImageResource(image[imageIndex[11]]);
        ib22.setImageResource(image[imageIndex[12]]);
        ib23.setImageResource(image[imageIndex[13]]);
        ib24.setImageResource(image[imageIndex[14]]);
        ib30.setImageResource(image[imageIndex[15]]);
        ib31.setImageResource(image[imageIndex[16]]);
        ib32.setImageResource(image[imageIndex[17]]);
        ib33.setImageResource(image[imageIndex[18]]);
        ib34.setImageResource(image[imageIndex[19]]);
        ib40.setImageResource(image[imageIndex[20]]);
        ib41.setImageResource(image[imageIndex[21]]);
        ib42.setImageResource(image[imageIndex[22]]);
        ib43.setImageResource(image[imageIndex[23]]);
        ib44.setImageResource(image[imageIndex[24]]);
    }


    private void swap(int rand1, int rand2) {
        int temp = imageIndex[rand1];
        imageIndex[rand1] = imageIndex[rand2];
        imageIndex[rand2] = temp;
    }

    //图片的点击事件
    public void onClick(View view) {
        int id = view.getId();
        //有空格在周围可以改变图片的显示
        switch (id) {
            case R.id.pt_line0_00x00:
                move(R.id.pt_line0_00x00,0);
                break;
            case R.id.pt_line0_00x01:
                move(R.id.pt_line0_00x01,1);
                break;
            case R.id.pt_line0_00x02:
                move(R.id.pt_line0_00x02,2);
                break;
            case R.id.pt_line0_00x03:
                move(R.id.pt_line0_00x03,3);
                break;
            case R.id.pt_line0_00x04:
                move(R.id.pt_line0_00x04,4);
                break;
            case R.id.pt_line1_01x00:
                move(R.id.pt_line1_01x00,5);
                break;
            case R.id.pt_line1_01x01:
                move(R.id.pt_line1_01x01,6);
                break;
            case R.id.pt_line1_01x02:
                move(R.id.pt_line1_01x02,7);
                break;
            case R.id.pt_line1_01x03:
                move(R.id.pt_line1_01x03,8);
                break;
            case R.id.pt_line1_01x04:
                move(R.id.pt_line1_01x04,9);
                break;
            case R.id.pt_line2_02x00:
                move(R.id.pt_line2_02x00,10);
                break;
            case R.id.pt_line2_02x01:
                move(R.id.pt_line2_02x01,11);
                break;
            case R.id.pt_line2_02x02:
                move(R.id.pt_line2_02x02,12);
                break;
            case R.id.pt_line2_02x03:
                move(R.id.pt_line2_02x03,13);
                break;
            case R.id.pt_line2_02x04:
                move(R.id.pt_line2_02x04,14);
                break;
            case R.id.pt_line3_03x00:
                move(R.id.pt_line3_03x00,15);
                break;
            case R.id.pt_line3_03x01:
                move(R.id.pt_line3_03x01,16);
                break;
            case R.id.pt_line3_03x02:
                move(R.id.pt_line3_03x02,17);
                break;
            case R.id.pt_line3_03x03:
                move(R.id.pt_line3_03x03,18);
                break;
            case R.id.pt_line3_03x04:
                move(R.id.pt_line3_03x04,19);
                break;
            case R.id.pt_line4_04x00:
                move(R.id.pt_line4_04x00,20);
                break;
            case R.id.pt_line4_04x01:
                move(R.id.pt_line4_04x01,21);
                break;
            case R.id.pt_line4_04x02:
                move(R.id.pt_line4_04x02,22);
                break;
            case R.id.pt_line4_04x03:
                move(R.id.pt_line4_04x03,23);
                break;
            case R.id.pt_line4_04x04:
                move(R.id.pt_line4_04x04,24);
                break;
        }
    }
    //移动指定位置的函数，将图片和空白区域进行交换
    private void move(int imagebuttonId, int site) {

        //判断选中的图片在第几行第几列
        int sitex = site / imageX;
        int sitey = site % imageY;

        //获取空白区域的坐标
        int blankx = blankSwap / imageX;
        int blanky = blankSwap % imageY;

        //可以移动的条件：
        //1.在同一行：列数相减，绝对值为1，可以移动
        //2.在同一列：行数相减，绝对值为1，可以移动
        int x = Math.abs(sitex-blankx);
        int y = Math.abs(sitey-blanky);
        if ((x==0&&y==1)||(x==1&&y==0)){

            //查找可以移动的图片
            ImageButton clickButton = findViewById(imagebuttonId);
            clickButton.setVisibility(View.INVISIBLE);

            //找到空白的图片
            ImageButton blankButton = findViewById(blankImageid);

            //将空白区域的按钮设置成图片
            blankButton.setImageResource(image[imageIndex[site]]);

            //将之前不可见的控件调成可见
            blankButton.setVisibility(View.VISIBLE);

            //将改变角标的过程记录到存储图片位置数组中
            swap(site,blankSwap);

            //空白区域换成移动的那张图片
            blankSwap = site;
            blankImageid = imagebuttonId;
        }
        //判断是否复原了拼图
        judgeGameOver();
    }

    //判断是否复原了拼图事件
    private void judgeGameOver() {
        //定义标志位
        boolean loop = true;
        for (int i = 0; i < imageIndex.length; i++) {
            if (imageIndex[i]!=i){
                loop = false;
                break;
            }
        }
        if (loop) {

            //拼图成功，停止计时，停止图片移动
            handler.removeMessages(1);
            ib00.setClickable(false);
            ib01.setClickable(false);
            ib02.setClickable(false);
            ib03.setClickable(false);
            ib04.setClickable(false);
            ib10.setClickable(false);
            ib11.setClickable(false);
            ib12.setClickable(false);
            ib13.setClickable(false);
            ib14.setClickable(false);
            ib20.setClickable(false);
            ib21.setClickable(false);
            ib22.setClickable(false);
            ib23.setClickable(false);
            ib24.setClickable(false);
            ib30.setClickable(false);
            ib31.setClickable(false);
            ib32.setClickable(false);
            ib33.setClickable(false);
            ib34.setClickable(false);
            ib40.setClickable(false);
            ib41.setClickable(false);
            ib42.setClickable(false);
            ib43.setClickable(false);
            ib44.setClickable(false);

            //将图片25(最后一个图片)显示可见
            ib44.setImageResource(image[24]);
            ib44.setVisibility(View.VISIBLE);

            //弹出提示用户成功的对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("恭喜您拼图成功！您所用时间为："+time+"秒")
                    .setPositiveButton("确认",null);
            AlertDialog dialog = builder.create();
            dialog.show();

            //按钮改成开始游戏
            startBtn.setText("开始游戏");
        }
    }


    //开始游戏按钮的点击事件
    public void start(View view) {
        //将拼图回到最初状态
        restore();
        //移除之前发送的消息
        handler.removeMessages(1);
        //将时间归零并开始计时
        time = 0;
        timeTv.setText("时间："+time+"秒");
        //开始发送消息
        handler.sendEmptyMessageDelayed(1,1000);
        //按钮改成重新开始
        startBtn.setText("重新开始");
        //将拼图打乱
        disruptRandom();
    }

    //打乱拼图，图片可以移动
    private void restore() {
        ib00.setClickable(true);
        ib01.setClickable(true);
        ib02.setClickable(true);
        ib03.setClickable(true);
        ib04.setClickable(true);
        ib10.setClickable(true);
        ib11.setClickable(true);
        ib12.setClickable(true);
        ib13.setClickable(true);
        ib14.setClickable(true);
        ib20.setClickable(true);
        ib21.setClickable(true);
        ib22.setClickable(true);
        ib23.setClickable(true);
        ib24.setClickable(true);
        ib30.setClickable(true);
        ib31.setClickable(true);
        ib32.setClickable(true);
        ib33.setClickable(true);
        ib34.setClickable(true);
        ib40.setClickable(true);
        ib41.setClickable(true);
        ib42.setClickable(true);
        ib43.setClickable(true);
        ib44.setClickable(true);

        //还原被点击图片按钮变成初始化模样
        ImageButton clickBtn = findViewById(blankImageid);
        clickBtn.setVisibility(View.VISIBLE);

        //隐藏第16张图片
        ImageButton blankBtn = findViewById(R.id.pt_line4_04x04);
        blankBtn.setVisibility(View.INVISIBLE);

        //将第16张图片设置成空白
        blankImageid = R.id.pt_line4_04x04;
        blankSwap = imageCount - 1;
    }


}
