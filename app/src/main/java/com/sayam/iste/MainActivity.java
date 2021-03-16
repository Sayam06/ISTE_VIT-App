package com.sayam.iste;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation logoAnim, botAnim;
    ImageView logo;
    TextView iste, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        logoAnim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo = findViewById(R.id.imageView);
        iste = findViewById(R.id.ISTE);
        slogan = findViewById(R.id.ISTEVIT);

        logo.setAnimation(logoAnim);
        iste.setAnimation(botAnim);
        slogan.setAnimation(botAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Loginboard.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}