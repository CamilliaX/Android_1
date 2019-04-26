package basic.com.test52;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context useCount = null;
        try
        {
            // 获取其他程序所对应的Context
            useCount = createPackageContext("com.example.test52",
                    Context.CONTEXT_IGNORE_SECURITY);
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        // 使用其他程序的Context获取对应的SharedPreferences
        SharedPreferences prefs = useCount.getSharedPreferences("count", Context.MODE_PRIVATE);
        // 读取数据
        int count = prefs.getInt("count", 0);
        TextView show = (TextView) findViewById(R.id.show);
        // 显示读取的数据内容
        show.setText("UseCount应用程序以前被使用了" + count + "次。");
    }

}
