package com.gobi.yeonrolove;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

    public final int SPLASH_DISPLAY_LENGTH = 5000;
    private RelativeLayout rl_context;

    private ObjectAnimator  fadeOutView;
    private AnimatorSet fadeInOutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rl_context = findViewById(R.id.rl_context);
        ImageView ballon_image = findViewById(R.id.ballon);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_ballonup);

        ballon_image.startAnimation(anim);

        createAnimator();
        createAnimatorSet();

        fadeInOutView.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* 메뉴액티비티를 실행하고 로딩화면을 죽인다.*/
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(0,0);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void createAnimator() {
        fadeOutView = ObjectAnimator.ofFloat(rl_context, "alpha", 1f, 0f);
        fadeOutView.setDuration(2000);
    }

    private void createAnimatorSet() {
        fadeInOutView = new AnimatorSet();
        fadeInOutView.play(fadeOutView).after(3000);
    }

}
