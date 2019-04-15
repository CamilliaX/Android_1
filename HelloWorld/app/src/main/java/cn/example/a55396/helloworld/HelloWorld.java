package cn.example.a55396.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.view.Menu;

public class HelloWorld extends AppCompatActivity {

    private static final String TAG="HelloWorld";
    private Button button,button2,button3,button4,button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);//指定当前APP显示布局的文件。R文件是个索引
        initview();
        initListener();
    }

    private void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"button clicked..");
                handler1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"button clicked..");
                handler2();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"button clicked..");
                handler3();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"button clicked..");
                handler4();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"button clicked..");
                handler5();
            }
        });
    }

    private void handler5() {
        Intent intent4=new Intent(this,SixthActivith.class);
        startActivity(intent4);
    }

    private void handler4() {
        Intent intent3=new Intent(this,FifthActivity.class);
        startActivity(intent3);
    }

    private void handler2() {
        Intent intent1=new Intent(this,ThirdActivity.class);
        startActivity(intent1);
    }

    private void handler3() {
        Intent intent2=new Intent(this,FourthActivity.class);
        startActivity(intent2);
    }

    private void handler1() {
        Intent intent=new Intent(this,OtherActivity.class);
        startActivity(intent);
    }

    private void initview(){
        button = (Button) this.findViewById(R.id.button);
        button2 = (Button) this.findViewById(R.id.button2);
        button3 = (Button) this.findViewById(R.id.button3);
        button4 = (Button) this.findViewById(R.id.button4);
        button5 = (Button) this.findViewById(R.id.button5);
    }

}
