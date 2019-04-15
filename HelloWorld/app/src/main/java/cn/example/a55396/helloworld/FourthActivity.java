package cn.example.a55396.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class FourthActivity extends Activity {
    private static final String TAG="ThirdActivity";
    private Button button;
    private ImageView iv;
    private  int currentPhoto = 0;
    private int[] photos = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                currentPhoto++;
                iv.setImageResource(photos[currentPhoto%5]);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Button returnBt = findViewById(R.id.button);
        iv = findViewById(R.id.iv);


        returnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FourthActivity.this,HelloWorld.class);
                startActivity(intent);//开启
                finish();//关闭
            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        },0,1500);
    }

//    private void initListener() {
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG,"button clicked..");
//                handler();
//            }
//        });
//    }
//
//    private void handler() {
//        Intent intent=new Intent(this,HelloWorld.class);
//        startActivity(intent);
//    }
//
//    private void initview(){
//        button = (Button) this.findViewById(R.id.button);
//    }
}
