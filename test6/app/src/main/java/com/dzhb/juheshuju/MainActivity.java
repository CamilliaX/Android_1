package com.dzhb.juheshuju;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends Activity {
    private Button sendRequest;
    private TextView responseText;
    public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    // 在这里进行UI操作，将结果显示到界面上
                    responseText.setText(response);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendRequestWithHttpURLConnection();
            }
        });
    }

    protected void sendRequestWithHttpURLConnection() {
        new Thread() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection connection = null;
                try {
                    // url = new
                    // URL("http://10.2.5.119:8080/Server/getData.json");
                    String cityName = URLEncoder.encode("滨州", "utf-8");
                    url = new URL(
                            "http://v.juhe.cn/weather/index?format=2&cityname="
                                    + cityName
                                    + "&key=ab9d7e2007472d723baf71fcdc4ba094");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    System.out.println("response=" + response.toString());
                    //parseWithJSON(response.toString());
                    parseWeatherWithJSON(response.toString());
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    // 将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }.start();

    }

    protected void parseWeatherWithJSON(String response) {
        try {
            JSONObject jsonObject=new JSONObject(response);
            String resultcode=jsonObject.getString("resultcode");
            if(resultcode.equals("200")){
                JSONObject resultObject=jsonObject.getJSONObject("result");
                JSONObject todayObject=resultObject.getJSONObject("today");
                String date_y=todayObject.getString("date_y");
                String week=todayObject.getString("week");
                String temperature=todayObject.getString("temperature");
                Log.d("MainActivity", "date_y="+date_y+"week="+week+"temp="+temperature);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void parseWithJSON(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity", "id=" + id + "name=" + name + "version="
                        + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}