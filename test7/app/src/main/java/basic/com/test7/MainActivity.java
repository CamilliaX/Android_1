package basic.com.test7;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManger;
    private TextView accelerate;
    private TextView orientation;
    private TextView gyro;
    private TextView magnetic;
    private TextView gravity;
    private TextView temerature;
    private TextView light;
    private TextView pressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerate = (TextView) findViewById(R.id.txt1);
        orientation = (TextView) findViewById(R.id.orientation1);
        gyro = (TextView) findViewById(R.id.gyro1);
        magnetic = (TextView) findViewById(R.id.magnetic1);
        gravity = (TextView) findViewById(R.id.gravity1);
        temerature = (TextView) findViewById(R.id.temerature1);
        light = (TextView) findViewById(R.id.light1);
        //获取系统的传感器服务
        sensorManger = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //为系统的加速度传感器注册监听器
        sensorManger.registerListener(this, sensorManger.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_UI);
        //方向
        sensorManger.registerListener(this,sensorManger.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
        //陀螺仪
        sensorManger.registerListener(this,sensorManger.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_UI);
        //磁场
        sensorManger.registerListener(this,sensorManger.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_UI);
        //重力
        sensorManger.registerListener(this,sensorManger.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_UI);
        //温度
        sensorManger.registerListener(this,sensorManger.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                SensorManager.SENSOR_DELAY_UI);
        //光
        sensorManger.registerListener(this,sensorManger.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_UI);
        //压力
        sensorManger.registerListener(this,sensorManger.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onStop(){
        sensorManger.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onPause(){
        //程序暂停时取消注册监听传感器监听器
        sensorManger.unregisterListener(this);
        super.onPause();
    }

    //以下是实现SensorEventListener接口必须实现的方法
    //当传感器的只发生改变时回调该方法

    //当传感器精度改变时回调该方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event){
        float[] values = event.values;
        //获取触发event的传感器类型
        int sensorType = event.sensor.getType();
        StringBuilder sb = null;
        switch(sensorType){
            case Sensor.TYPE_LINEAR_ACCELERATION:
                sb = new StringBuilder();
                sb.append("X方向上的加速度：");
                sb.append(values[0]);
                sb.append("\nY方向上的加速度");
                sb.append(values[1]);
                sb.append("\nZ方向上的加速度");
                sb.append(values[2]);
                accelerate.setText(sb.toString());
                break;

            case Sensor.TYPE_ORIENTATION:
                sb = new StringBuilder();
                sb.append("绕Z轴旋转的角度：");
                sb.append(values[0]);
                sb.append("\n绕X轴旋转的角度：");
                sb.append(values[1]);
                sb.append("\n绕Y轴旋转的角度：");
                sb.append(values[2]);
                orientation.setText(sb.toString());
                break;

            case Sensor.TYPE_GYROSCOPE:
                sb = new StringBuilder();
                sb.append("绕X轴旋转的角速度：");
                sb.append(values[0]);
                sb.append("\n绕Y轴旋转的角速度：");
                sb.append(values[1]);
                sb.append("\n绕Z轴旋转的角速度：");
                sb.append(values[2]);
                gyro.setText(sb.toString());
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                sb = new StringBuilder();
                sb.append("X轴方向上的重力：");
                sb.append(values[0]);
                sb.append("\n轴方向上的重力：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的重力：");
                sb.append(values[2]);
                magnetic.setText(sb.toString());
                break;

            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb = new StringBuilder();
                sb.append("当前温度为：");
                sb.append(values[0]);
                temerature.setText(sb.toString());
                break;

            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("当前光的强度为：");
                sb.append(values[0]);
                light.setText(sb.toString());
                break;

            case Sensor.TYPE_PRESSURE:
                sb = new StringBuilder();
                sb.append("当前压力为：");
                sb.append(values[0]);
                pressure.setText(sb.toString());
                break;
        }
    }
}
