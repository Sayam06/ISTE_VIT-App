package com.sayam.iste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BoardMeeting extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private EditText meetDate;
    private EditText meetTopic;
    private EditText meetName;
    private EditText meetTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_meeting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        meetDate = findViewById(R.id.meetDate);
        meetTopic = findViewById(R.id.meetTopic);
        meetName = findViewById(R.id.meetName);
        meetTime = findViewById(R.id.meetTime);


        Button FixMeetingButton = findViewById(R.id.FixMeetingButton);
        FixMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDate();
            }
        });
    }

    private void sendDate() {

        String meetingDate = meetDate.getText().toString();
        String meetingTopic = meetTopic.getText().toString();
        String meetingTime = meetTime.getText().toString();
        String meetingName = meetName.getText().toString();

        String meeting;

        if(meetingDate.length()==0||meetingTopic.length()==0||meetingTime.length()==0||meetingName.length() == 0 ){
            meeting = "No Meetings Scheduled";
        }
        else {

            meeting = meetingName + " will be taking a meeting on " + meetingDate + " at " + meetingTime + " on the topic " + meetingTopic;
        }
        TextView confirm = (TextView)findViewById(R.id.confirmText);
        confirm.setVisibility(View.VISIBLE);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Meeting Details");
        reference.setValue(meeting);

        String[] recepientList = new String[] {"mdtiham.hossain2020@vitstudent.ac.in", "soham.mukhopadhyay2020@vitstudent.ac.in", "sayam.sarkar2020@vitstudent.ac.in",
                "adeelabdul.sakkeer2020@vitstudent.ac.in", "amanali.siddiqui2020@vitstudent.ac.in", "avinash.singh2020@vitstudent.ac.in",
                "sai.prasannaabhinav2020@vitstudent.ac.in", "chetan.shiraguppi2020@vitstudent.ac.in","dwija.patel2020@vitstudent.ac.in" ,
                "gauri.gupta2020@vitstudent.ac.in" ,"hrithik.purwar2020@vitstudent.ac.in" , "khushang.thakkar2020@vitstudent.ac.in",
                "melvinpaulsam.p2020@vitstudent.ac.in", "rishit.anand2020@vitstudent.ac.in","sahil.kaling2020@vitstudent.ac.in" ,
                "shahil.choudhary2020@vitstudent.ac.in", "shashank.suresh2020@vitstudent.ac.in","sourish.gupta2020@vitstudent.ac.in" ,
                "srinivasan.r2020@vitstudent.ac.in" ,"suhani.bajaj2020@vitstudent.ac.in" ,"tisha.chawla2020@vitstudent.ac.in" ,
                "vidyarth.gs2020@vitstudent.ac.in"};
        String subject = new String("NEW MEETING SCHEDULED");

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recepientList);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, meeting);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client to send the meeting details"));
    }
}