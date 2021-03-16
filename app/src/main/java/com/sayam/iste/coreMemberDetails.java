package com.sayam.iste;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class coreMemberDetails extends AppCompatActivity {

    ImageView imageView;
    Button btn;

    public void showData() {

        Intent intent = getIntent();
        String memberUsername = intent.getStringExtra("Name");
        TextView nameText = (TextView) findViewById(R.id.nameText);
        TextView dobText = (TextView) findViewById(R.id.dobText);
        TextView mailText = (TextView) findViewById(R.id.mailText);
        TextView phoneText = (TextView) findViewById(R.id.phoneText);
        TextView domainText = (TextView) findViewById(R.id.domainText);
        TextView regnoText = (TextView) findViewById(R.id.regnoText);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Members");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nameFromDB = dataSnapshot.child(memberUsername).child("Name").getValue().toString();
                String dobFromDB = dataSnapshot.child(memberUsername).child("DOB").getValue().toString();
                String mailFromDB = dataSnapshot.child(memberUsername).child("E-Mail").getValue().toString();
                String phoneFromDB = dataSnapshot.child(memberUsername).child("Phone").getValue().toString();
                String domainFromDB = dataSnapshot.child(memberUsername).child("Domain").getValue().toString();
                String regnoFromDB = dataSnapshot.child(memberUsername).child("Reg No").getValue().toString();
                String imageFromDB = dataSnapshot.child(memberUsername).child("Picture").getValue().toString();

                nameText.setText("Name: " + nameFromDB);
                dobText.setText("DOB: " + dobFromDB);
                mailText.setText("E-Mail: " + mailFromDB);
                phoneText.setText("Phone: " + phoneFromDB);
                domainText.setText("Domain: " + domainFromDB);
                regnoText.setText("Reg. No.: " + regnoFromDB);

                ImageDownloader task = new ImageDownloader();
                Bitmap myImage;

                try {
                    myImage = task.execute(imageFromDB).get();
                    imageView.setImageBitmap(myImage);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                btn = findViewById(R.id.whatsappTextButton);

                String number = "+91" + phoneFromDB;

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + number + "&text=Hey"));
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_core_member_details);
        showData();

        imageView = (ImageView)findViewById(R.id.memberImage);
    }
}