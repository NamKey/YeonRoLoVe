package com.gobi.yeonrolove;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //메인 이미지 슬라이드 부분
    public ViewFlipper flipper;
    public Button btn_next;
    public Button btn_previous;
    int count=0; //첫, 끝화면을 알리기 위한 변수

    //BGM 재생 관련 View
    public Button btn_play,btn_stop;                                              // play&pause 버튼과 stop 버튼
    public MediaPlayer mp3;
    public MediaPlayer mp3_mail;

    //mail activity로 전환되는 버튼
    public Button btn_mail;

    public Dialog dialog_mailalarm;

    public Button btn_ok;
    public Button btn_cancel;

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

        mp3_mail = MediaPlayer.create(this,R.raw.mail_alarm_v2);

        btn_mail = findViewById(R.id.btn_mail);                                     //Mail Activity로 넘어가는 버튼
        btn_mail.setOnClickListener(this);


        flipper=findViewById(R.id.flipper);
        //ViewFlipper 객체 참조

        Animation showln= AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        //ViewFlipper가 View를 교체할 때 애니메이션이 적용되도록 설정
        //애니메이션은 안드로이드가 보유한 animation 리소스 파일 사용
        //ViewFlipper의 View가 교체될 때 새로 보여지는 View의 등장 애니메이션
        //AnimationUtils.loadAnimation()-트윈animation리소스 파일을 animation 객체로 만들어주는 메소드
        //첫번째 파라미터 : context
        //두번째 파라미터 : 트윈 animation 리소스 파일
        flipper.setInAnimation(showln);
        //왼쪽에서 슬라이딩되며 등장
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
        //오른쪽으로 슬라이딩되며 퇴장

        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        btn_previous = findViewById(R.id.btn_previous);
        btn_previous.setOnClickListener(this);
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

            case R.id.btn_next:
                count++;
                if(count<4)
                    flipper.showPrevious();//이전 view로 교체
                else {
                    dialog_mailalarm = new Dialog(this);
                    dialog_mailalarm.setContentView(R.layout.dialog_default);
                    btn_ok = dialog_mailalarm.findViewById(R.id.btn_ok);
                    btn_cancel = dialog_mailalarm.findViewById(R.id.btn_cancel);

                    btn_ok.setOnClickListener(this);
                    btn_cancel.setOnClickListener(this);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    dialog_mailalarm.show();      // dialog 출력
                    mp3_mail.setLooping(false);
                    mp3_mail.start();
                    count--; //카운터값이 범위 벗어나지 않도록
                }
                break;
            case R.id.btn_ok:
                dialog_mailalarm.dismiss();

                Intent mailintent = new Intent(this,MailActivity.class);
                startActivity(mailintent);

                break;

            case R.id.btn_cancel:
                dialog_mailalarm.dismiss();
                break;

            case R.id.btn_previous:
                count--;
                if(count>-1)
                    flipper.showNext();//다음 view로 교체
                else {
                    Toast.makeText(this, "첫번째장입니다.", Toast.LENGTH_SHORT).show();
                    count++;//카운터 값이 범위 벗어나지 않도록
                }
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
