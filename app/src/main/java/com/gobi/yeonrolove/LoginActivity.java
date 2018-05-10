package com.gobi.yeonrolove;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public Button btn_login;
    public Button btn_quit;
    public Button btn_hint;
    public Button btn_ok_close_app;
    public Button btn_cancel_close_app;

    public EditText edt_input_ID;
    public EditText edt_input_PW;

    public Dialog dialog_default2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        btn_quit = findViewById(R.id.btn_quit);
        btn_hint = findViewById(R.id.btn_hint);

        btn_ok_close_app = findViewById(R.id.btn_ok_close_app);
        btn_cancel_close_app = findViewById(R.id.btn_cancel_close_app);

        edt_input_ID = findViewById(R.id.edt_input_ID);
        edt_input_PW = findViewById(R.id.edt_input_PW);

        btn_login.setOnClickListener(this);
        btn_quit.setOnClickListener(this);
        btn_hint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: //로그인 하기 버튼
                if (edt_input_ID.getText().toString().equals("KJW")&&edt_input_PW.getText().toString().equals("2013252004")) {     // EditText의 입력 비교
                    //사용자 if 문 예시 --> 이름 (한글 또는 영어) , 학번(숫자)
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show(); //모두 일치하면 성공 토스트 띄운 후 다음 액티비티로 전환(여기서는 임시로 메일 액티비티)
                    Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent2);
                }
                else if (edt_input_ID.getText().toString().equals("")||edt_input_PW.getText().toString().equals("")) {     // EditText의 입력 비교
                    Toast.makeText(this, "이름과 학번을 모두 입력해주세요.", Toast.LENGTH_SHORT).show(); // 이름, 학번 중 하나라도 빈칸이면 토스트
                }
                else {
                    Toast.makeText(this, "등록 되지 않은 사용자입니다 : 힌트 참조!" , Toast.LENGTH_SHORT).show(); // 이름, 학번 중 하나라도 틀릴 경우 토스트
                }
                // 액티비티 전환
                break;

            case R.id.btn_quit: //종료하기 버튼 --> 종료하기 누르면 확인을 위한 다이얼로그가 표시
                dialog_default2 = new Dialog(this);
                dialog_default2.setContentView(R.layout.dialog_default2);
                btn_ok_close_app = dialog_default2.findViewById(R.id.btn_ok_close_app);
                btn_cancel_close_app = dialog_default2.findViewById(R.id.btn_cancel_close_app);

                btn_ok_close_app.setOnClickListener(this);
                btn_cancel_close_app.setOnClickListener(this);

                dialog_default2.show();      // dialog 출력
                break;

            case R.id.btn_hint: //힌트 버튼
                Toast.makeText(this, "이름 예시 : 김정원 \n학번 예시 : 2013252004", Toast.LENGTH_SHORT).show();
                break;


            case R.id.btn_ok_close_app: //종료 확인 다이얼로그 '확인' 버튼
                dialog_default2.dismiss();       // dialog 제거
                //프로그램을 종료하는 명령어 추가해주세요!
                break;

            case R.id.btn_cancel_close_app: //종료 확인 다이얼로그 '취소' 버튼
                dialog_default2.dismiss(); //이전 화면으로 돌아가는 명령어
                break;

            default:
                break;
        }
    }
}
