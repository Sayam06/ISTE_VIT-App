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

public class memberLogin extends AppCompatActivity {

    public void cLogin(View view) {
        isUser();
    }

    private void isUser() {

        EditText username = (EditText)findViewById(R.id.coreUsername);
        final String coreUsername = username.getText().toString();
        EditText password = (EditText)findViewById(R.id.corePassword);
        final String corePassword = password.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Members");

        Query checkUser = reference.orderByChild("Username").equalTo(coreUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView errorText = (TextView) findViewById(R.id.coreLoginError);

                if (dataSnapshot.exists()) {
                    String passwordFromDatabase = dataSnapshot.child(coreUsername).child("Password").getValue().toString();
                    if (passwordFromDatabase.equals(corePassword)) {
                        Intent intent = new Intent(getApplicationContext(), coreProfile.class);

                        String nameFromDatabase = dataSnapshot.child(coreUsername).child("Name").getValue().toString();

                        intent.putExtra("Name", nameFromDatabase);
                        intent.putExtra("Username", coreUsername);

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
        setContentView(R.layout.activity_core_login);


    }
}