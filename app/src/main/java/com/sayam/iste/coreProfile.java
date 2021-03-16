package com.sayam.iste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class coreProfile extends AppCompatActivity {

    private void showData(){
        Intent intent = getIntent();
        String core_username = intent.getStringExtra("Name");
        TextView boardGreetText=(TextView)findViewById(R.id.coreGreetText);
        boardGreetText.setText("Hello, " + core_username);
    }

    public void announcement(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewAnnouncements.class);
        startActivity(intent);
    }

    public void meetingDate(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewMeeting.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_core_profile);
        showData();
    }
}