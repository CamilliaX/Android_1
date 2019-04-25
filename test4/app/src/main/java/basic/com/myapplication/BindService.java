package basic.com.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
public class BindService extends Service {
    private int count;
    private boolean quit;
    private MyIBinder binder=new MyIBinder();  //声明一个IBinder对象
    //1.定义一个IBinder子类并实现一个获取Service运行状态(count)的方法
    public class MyIBinder extends Binder
    {
        public int getCount()
        {
            return count;     //返回Service运行状态:count
        }
    }
    //2.Service子类必须实现的一个类，用于返回IBinder对象
    public IBinder onBind(Intent intent) {
        System.out.println("Service is Binded!");
        return binder;    //返回IBinder对象
    }
    //3.Service被创建时回调该方法
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service is Created.");
        //创建并启动一个线程，实现动态修改count状态值
        new Thread()
        {
            @Override
            public void run()
            {
                while(!quit)    //标识Service关闭启动状态
                {
                    try
                    {
                        Thread.sleep(1000);
                    }catch(InterruptedException e)
                    {
                    }
                    count++;
                }
            }
        }.start();
    }
    //4.Service被断开连接时回调方法
    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("Service is Unbinded");
        return true;
    }
    //5.Service被关闭之前回调该方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit=true;    //当调用该方法后(!quit)为假，线程结束
        System.out.println("Service is Destroy");
    }
}