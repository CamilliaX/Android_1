package cn.example.a55396.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class OtherActivity extends Activity {

    private static final String TAG="OtherActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        final LinearLayout linearLayout = findViewById(R.id.linear);
        Button returnBt = findViewById(R.id.returnBt);//返回按钮
        final Button changeHor = findViewById(R.id.changeHor);//水平
        final Button changeVer = findViewById(R.id.changeVer);//垂直
        final Button changeLeft = findViewById(R.id.changeLeft);//靠左对齐

        changeHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//水平布局
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);


            }
        });

        changeVer.setOnClickListener(new View.OnClickListener() {//垂直布局
            @Override
            public void onClick(View view) {//判断是否需要改
                int orientation = linearLayout.getOrientation();
                if(orientation==LinearLayout.VERTICAL){
                    return;
                }else{
                    //改变orientation
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    //改变各个按钮的gravity
                    LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params1.gravity= Gravity.CENTER_HORIZONTAL;//向左对齐
                    params1.setMargins(0,10,0,0);
                    changeVer.setLayoutParams(params1);//设置布局参数
                    changeLeft.setLayoutParams(params1);
                    LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params2.gravity=Gravity.CENTER_HORIZONTAL;
                    params2.setMargins(0,140,0,0);
                    changeHor.setLayoutParams(params2);
                }


            }
        });

        changeLeft.setOnClickListener(new View.OnClickListener() {//向左对齐,重写布局
            @Override
            public void onClick(View view) {
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.gravity=Gravity.LEFT;//向左对齐
                params1.setMargins(0,14,0,0);
                changeVer.setLayoutParams(params1);//设置布局参数
                changeLeft.setLayoutParams(params1);
                LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params2.gravity=Gravity.LEFT;
                params2.setMargins(0,140,0,0);
                changeHor.setLayoutParams(params2);
            }
        });

        returnBt.setOnClickListener(new View.OnClickListener() {//设置返回按钮的点击事件
            @Override
            public void onClick(View view) {//返回
                Intent intent = new Intent(OtherActivity.this,HelloWorld.class);
                startActivity(intent);//开启
                finish();//关闭
            }
        });
    }
}
