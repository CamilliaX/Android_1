package basic.com.test8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView)findViewById(R.id.vdvwFilm);
        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        Button btnPause = (Button)findViewById(R.id.btnPause);
        Button btnReplay = (Button)findViewById(R.id.btnReplay);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnReplay.setOnClickListener(this);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
            initVideoPath();//初始化MediaPlayer
        }
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "1.mp4");
        videoView.setVideoPath(file.getPath());//指定视频文件路径
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);//让电影循环播放
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                }else{
                    Toast.makeText(this, "拒绝权限，无法使用程序。", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                if(!videoView.isPlaying()){
                    videoView.start();//播放
                }
                break;
            case R.id.btnPause:
                if(videoView.isPlaying()){
                    videoView.pause();//暂停
                }
                break;
            case R.id.btnReplay:
                if(videoView.isPlaying()){
                    videoView.resume();//重新播放
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView != null){
            videoView.suspend();
        }
    }
}