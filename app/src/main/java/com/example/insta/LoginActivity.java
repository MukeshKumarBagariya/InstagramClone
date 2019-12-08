package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LogIn");
        final TextView lUsername = findViewById(R.id.lUser);
        final TextView lPassword = findViewById(R.id.lPassword);
        Button login = findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Wait a moment..");
                progressDialog.show();
                ParseUser.logInInBackground(lUsername.getText().toString(), lPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(LoginActivity.this,"Please enter valid credentials",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }
        });
    }
}
