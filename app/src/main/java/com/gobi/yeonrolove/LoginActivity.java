package com.gobi.yeonrolove;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public Button btn_login;
    public Button btn_quit;
    public Button btn_ok_close_app;
    public Button btn_cancel_close_app;

    public EditText edt_input_ID;
    public EditText edt_input_PW;

    public Dialog dialog_default2;

    private LinearLayout ll_context;

    private ObjectAnimator fadeInView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        ll_context = findViewById(R.id.ll_context);

        btn_login = findViewById(R.id.btn_login);
        btn_quit = findViewById(R.id.btn_quit);

        btn_ok_close_app = findViewById(R.id.btn_ok_close_app);
        btn_cancel_close_app = findViewById(R.id.btn_cancel_close_app);

        edt_input_ID = findViewById(R.id.edt_input_ID);
        edt_input_PW = findViewById(R.id.edt_input_PW);

        btn_login.setOnClickListener(this);
        btn_quit.setOnClickListener(this);

        fadeInView = ObjectAnimator.ofFloat(ll_context, "alpha", 0f, 1f);
        fadeInView.setDuration(2000);

        fadeInView.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: //로그인 하기 버튼
                if (edt_input_ID.getText().toString().equals("yoon")&&edt_input_PW.getText().toString().equals("0515")) {     // EditText의 입력 비교
                    //사용자 if 문 예시 --> 이름 (한글 또는 영어) , 학번(숫자)
                    Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(0,0);
                }
                else if (edt_input_ID.getText().toString().equals("")||edt_input_PW.getText().toString().equals("")) {     // EditText의 입력 비교
                    Toast.makeText(this, "ID와 PASSWORD을 모두 입력해주세요.", Toast.LENGTH_SHORT).show(); // 이름, 학번 중 하나라도 빈칸이면 토스트
                }
                else {
                    Toast.makeText(this, "ID와 PASSWORD를 확인해주세요." , Toast.LENGTH_SHORT).show(); // 이름, 학번 중 하나라도 틀릴 경우 토스트
                }
                break;

            case R.id.btn_quit: //종료하기 버튼 --> 종료하기 누르면 확인을 위한 다이얼로그가 표시
                dialog_default2 = new Dialog(this);
                dialog_default2.setContentView(R.layout.dialog_finish);
                btn_ok_close_app = dialog_default2.findViewById(R.id.btn_ok_close_app);
                btn_cancel_close_app = dialog_default2.findViewById(R.id.btn_cancel_close_app);

                btn_ok_close_app.setOnClickListener(this);
                btn_cancel_close_app.setOnClickListener(this);

                dialog_default2.show();      // dialog 출력
                break;

            case R.id.btn_ok_close_app: //종료 확인 다이얼로그 '확인' 버튼
                dialog_default2.dismiss();       // dialog 제거
                finish();
                break;

            case R.id.btn_cancel_close_app: //종료 확인 다이얼로그 '취소' 버튼
                dialog_default2.dismiss(); //이전 화면으로 돌아가는 명령어
                break;

            default:
                break;
        }
    }
}
