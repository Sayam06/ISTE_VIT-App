package com.sayam.iste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Loginboard extends AppCompatActivity {


    public void goToMemberLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), memberLogin.class);
        startActivity(intent);
    }

    public void goToBoardLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), boardLogin.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loginboard);

    }
}