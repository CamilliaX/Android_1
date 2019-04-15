package cn.example.a55396.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FifthActivity extends Activity {
    private static final String TAG="FifthActivity";
    private Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);//指定当前APP显示布局的文件。R文件是个索引
        initview();
        initListener();
    }

    private void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"button clicked..");
                handler();
            }
        });
    }

    private void handler() {
        Intent intent=new Intent(this,HelloWorld.class);
        startActivity(intent);
    }

    private void initview(){
        button = (Button) this.findViewById(R.id.button);
    }
}
