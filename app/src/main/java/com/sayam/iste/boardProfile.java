package com.sayam.iste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class boardProfile extends AppCompatActivity {

    private void showData(){
        Intent intent = getIntent();
        String board_username = intent.getStringExtra("Name");
        TextView boardGreetText=(TextView)findViewById(R.id.boardGreetText);
        boardGreetText.setText("Hello, " + board_username);
    }

    public void announcement(View view) {
        Intent intent = new Intent(getApplicationContext(), BoardAnnouncements.class);
        startActivity(intent);
    }

    public void meetingDate(View view) {
        Intent intent = new Intent(getApplicationContext(), BoardMeeting.class);
        startActivity(intent);
    }

    public void viewDetails(View view) {
        Intent intent = new Intent(getApplicationContext(), BoardDetails.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_board_profile);

        showData();

    }
}