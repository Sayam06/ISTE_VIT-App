package com.sayam.iste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BoardAnnouncements extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_board_announcements);

        mEditTextMessage = findViewById(R.id.announcementText);

        Button buttonSend = findViewById(R.id.sendMailButton);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextMessage.getText().toString().length()!=0) {
                    sendMail();
                }
            }
        });
    }

    private void sendMail() {
        String[] recepientList = new String[] {"mdtiham.hossain2020@vitstudent.ac.in", "soham.mukhopadhyay2020@vitstudent.ac.in", "sayam.sarkar2020@vitstudent.ac.in",
                "adeelabdul.sakkeer2020@vitstudent.ac.in", "amanali.siddiqui2020@vitstudent.ac.in", "avinash.singh2020@vitstudent.ac.in",
                "sai.prasannaabhinav2020@vitstudent.ac.in", "chetan.shiraguppi2020@vitstudent.ac.in","dwija.patel2020@vitstudent.ac.in" ,
                "gauri.gupta2020@vitstudent.ac.in" ,"hrithik.purwar2020@vitstudent.ac.in" , "khushang.thakkar2020@vitstudent.ac.in",
                "melvinpaulsam.p2020@vitstudent.ac.in", "rishit.anand2020@vitstudent.ac.in","sahil.kaling2020@vitstudent.ac.in" ,
                "shahil.choudhary2020@vitstudent.ac.in", "shashank.suresh2020@vitstudent.ac.in","sourish.gupta2020@vitstudent.ac.in" ,
                "srinivasan.r2020@vitstudent.ac.in" ,"suhani.bajaj2020@vitstudent.ac.in" ,"tisha.chawla2020@vitstudent.ac.in" ,
                "vidyarth.gs2020@vitstudent.ac.in"};
        String subject = new String("IMPORTANT ISTE ANNOUNCEMENT");
        String message = mEditTextMessage.getText().toString();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Announcements");
        reference.setValue(message);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recepientList);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client to send the announcement"));


    }

}