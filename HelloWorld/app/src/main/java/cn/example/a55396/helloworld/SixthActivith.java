package cn.example.a55396.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class SixthActivith extends Activity {
    private static final String TAG="SixthActivith";
    private Button button;
    GridLayout gridLayout;
    String[] chars = new String[]{
            "1","2","3",
            "4","5","6",
            "7","8","9",
            "0","+","-",
            "*","/","."
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);//指定当前APP显示布局的文件。R文件是个索引
        initview();
        initListener();
        gridLayout = findViewById(R.id.gridLayout);
        for (int i=0;i<chars.length;i++){
            Button bt = new Button(this);
            bt.setText(chars[i]);
            bt.setTextSize(14);
            bt.setPadding(5,35,5,35);
            GridLayout.Spec rowSpec = GridLayout.spec(i/3,1f);//设置比重，达到均分效果
            GridLayout.Spec columnSpec = GridLayout.spec(i%3,1f);
            GridLayout.LayoutParams params =new GridLayout.LayoutParams(rowSpec,columnSpec);
            /*params.height=0;
            params.width=0;*/
            params.setGravity(Gravity.FILL);
            gridLayout.addView(bt,params);

        }
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
