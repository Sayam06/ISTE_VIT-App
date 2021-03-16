package com.sayam.iste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class boardLogin extends AppCompatActivity {

    public void bLogin(View view) {
        isUser();
    }

    private void isUser() {

        EditText username = (EditText)findViewById(R.id.boardUsername);
        final String boardUsername = username.getText().toString();
        EditText password = (EditText)findViewById(R.id.boardPassword);
        final String boardPassword = password.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Board");

        Query checkUser = reference.orderByChild("Username").equalTo(boardUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView errorText = (TextView) findViewById(R.id.boardLoginError);

                if (dataSnapshot.exists()) {
                    String passwordFromDatabase = dataSnapshot.child(boardUsername).child("Password").getValue().toString();
                    if (passwordFromDatabase.equals(boardPassword)) {
                        Intent intent = new Intent(getApplicationContext(), boardProfile.class);

                        String nameFromDatabase = dataSnapshot.child(boardUsername).child("Name").getValue().toString();

                        intent.putExtra("Name", nameFromDatabase);
                        intent.putExtra("Username", boardUsername);
                        startActivity(intent);
                    }
                    else {
                        errorText.setText("Wrong Password");
                        errorText.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    errorText.setText("User Not Found");
                    errorText.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_board_login);
    }
}