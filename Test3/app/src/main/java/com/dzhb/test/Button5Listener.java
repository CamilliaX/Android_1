package com.dzhb.test;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


public class Button5Listener implements View.OnClickListener {
    Context context;
    public Button5Listener(Context context){
        this.context = context;
    }
    @Override
    public void onClick(View view) {
        Toast.makeText(context, "使用绑外部类的方式实现监听，button5被点击了！！！", Toast.LENGTH_SHORT).show();

    }
}
