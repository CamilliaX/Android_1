package com.dzhb.test;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements View.OnClickListener {

    Button bt1, bt2, bt4, bt5, bt8;

    @BindView(R.id.bt6)
    Button bt6;

    final int PROGRESS_DIALOG = 0; // 声明进度对话框id
    final int INCREASE = 0; // Handler消息类型
    ProgressDialog progressDialog;
    Handler myHandler; // Handler对象引用
    int max = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(this);

        bt2 = findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "使用匿名内部类作为监听器,第2个按钮被点击了！！！", Toast.LENGTH_SHORT).show();
            }
        });

        bt4 = findViewById(R.id.bt4);
        bt4.setOnClickListener(new Button4Listener());

        bt5 = findViewById(R.id.bt5);
        bt5.setOnClickListener(new Button5Listener(MainActivity.this));

        bt8 = findViewById(R.id.bt8);
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(PROGRESS_DIALOG);// 显示进度对话框
            }
        });
        myHandler = new Handler() { // 创建Handler对象
            public void handleMessage(Message msg) {// 处理传过的信息

                switch (msg.what) {
                    case INCREASE:
                        progressDialog.incrementProgressBy(msg.arg1);// 进度每次加1
                        if (progressDialog.getProgress() == max) { // 判断是否结束进度
                            progressDialog.dismiss(); // 如果进度条走完则关闭窗口
                        } else {
                            myHandler.post(runnable);// 将线程添加进消息队列
                        }
                        break;
                }
                super.handleMessage(msg);
            }
        };

    }

    @OnClick(R.id.bt6)
    public void button6Clicked() {
        Toast.makeText(this, "使用ButterKnife框架绑定实现监听，button6被点击了！！！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "使用Activity作为事件监听器，button1被点击了！！！", Toast.LENGTH_SHORT).show();
    }

    public void button3Clicked(View source) {
        Toast.makeText(this, "使用绑定到标签方式实现监听，button3被点击了！！！", Toast.LENGTH_SHORT).show();
    }

    class Button4Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "使用绑内部类的方式实现监听，button4被点击了！！！", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.bt7)
    public void button7Clicked() {
        Intent intent = new Intent(MainActivity.this, ConfigurationTest.class);
        startActivity(intent);
    }

    @Override
    public Dialog onCreateDialog(int id) {// 重写onCreateDialog方法
        switch (id) { // 对id进行判断
            case PROGRESS_DIALOG: // 创建进度对话框
                progressDialog = new ProgressDialog(this);// 创建进度对话框
                progressDialog.setMax(max);// 设置最大值
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("正在同步......");// 设置标题
                progressDialog.setCancelable(false);// 设置进度对话框不能用回退按钮关闭
                break;
        }
        return progressDialog;
    }

    Runnable runnable = new Runnable() {
        int i = 0;

        public void run() {
            i = i + 10;
            Message message = myHandler.obtainMessage();
            message.what = INCREASE;
            message.arg1 = i;
            myHandler.sendMessage(message); // 发送Handler消息
            if (i == max) {
                myHandler.removeCallbacks(runnable);
            }

        }

    };

    // 每次弹出对话框时被回调以动态更新对话框内容的方法
    @Override
    public void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case PROGRESS_DIALOG:
                progressDialog.incrementProgressBy(-progressDialog.getProgress());// 对话框进度清零
                myHandler.post(runnable);// 将线程添加进消息队列
                break;
        }
    }
}