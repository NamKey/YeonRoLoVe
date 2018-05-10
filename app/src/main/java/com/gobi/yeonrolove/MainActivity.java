package com.gobi.yeonrolove;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public Button btn_play,btn_stop;                                              // play&pause 버튼과 stop 버튼
    public MediaPlayer mp3;
    public Button btn_mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play=findViewById(R.id.btn_play);                                       // play버튼 참조획득 및 리스너 등록
        btn_play.setOnClickListener(this);

        btn_stop=findViewById(R.id.btn_stop);                                       // stop버튼 참조획득 및 리스너 등록
        btn_stop.setOnClickListener(this);

        mp3 = MediaPlayer.create(this,R.raw.backgroundmusic);               //mp3 객체 생성
        mp3.setLooping(true);

        btn_mail = findViewById(R.id.btn_mail);                                     //Mail Activity로 넘어가는 버튼
        btn_mail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean mp3_isPlaying=false;                                // 노래가 재생되고 있는지를 나타내는 불리언 변수
        try{                                                         // 노래가 재생되고있는지를 얻고, 만약 처음 시작할때나 객체를 release(stop 버튼 눌렀을때) 해주었을때 runtime exception을 방지하기 위해서 예외처리
            mp3_isPlaying=mp3.isPlaying();
        } catch (Exception e){
            mp3 = MediaPlayer.create(MainActivity.this,R.raw.backgroundmusic);
            mp3.setLooping(true);
        }

        switch (v.getId()){                                         // 눌린 버튼의 id에 따라서 case문을 통해서 버튼이 구현된다. stop은 뮤직이 재생되고가 상관없지만
            case R.id.btn_play:                                    // play 와 pause 버튼은 뮤직이 재생됨에 따라 구별되야 한다.
                if(!mp3_isPlaying)
                    musicPlay();
                else
                    musicPause();
                break;
            case R.id.btn_stop:
                musicStop();
                break;

            case R.id.btn_mail :                                    //MainActivity에서 MailActivity로 넘어가도록 해주는 버튼
                Intent intent = new Intent(MainActivity.this, MailActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void musicPlay(){                                   // 뮤직을 시작하는 함수 , 음악을 재생하고 버튼의 텍스트를 "pause" 로 바꿔준다
        mp3.start();
        btn_play.setText("Pause");
    }
    private void musicPause(){                                  // 뮤직을 멈추게 하는 함수, 음악을 중지시키고 현재 위치로 음악을 이동시키고 버튼의 텍스트를 "play"로 바꿔준다
        mp3.pause();
        mp3.seekTo(mp3.getCurrentPosition());
        btn_play.setText("Play");
    }
    private void musicStop(){                                   // 뮤직을 끝내는 함수, 음악을 중지시키고, 자원을 해제하고 버튼의 텍스트를 "play"로 바꿔 사용자에게 음악을 다시 play할수있도록 알려준다.
        mp3.stop();
        mp3.release();
        mp3=null;
        btn_play.setText("Play");
    }
}
