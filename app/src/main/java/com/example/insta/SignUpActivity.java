package com.example.insta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    private TextView uName, uPss,uId;
    private TextView uPer,login;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        setTitle("SignUp");
        uName = findViewById(R.id.uName);
        uPss = findViewById(R.id.uPass);
        uId = findViewById(R.id.uId);
        uPer = findViewById(R.id.per);
        login = findViewById(R.id.login);
        button = findViewById(R.id.button);
        uPss.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(button);
                }

                return false;
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        if (ParseUser.getCurrentUser() != null){
            transitionActivity();
        }
    }
    public void onClick(View view) {
        final ParseUser userLogin = new ParseUser();
        userLogin.setUsername(uName.getText().toString());
        userLogin.setPassword(uPss.getText().toString());
        userLogin.setEmail(uId.getText().toString());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait a moment...");
        progressDialog.show();
        userLogin.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(SignUpActivity.this,"Signed up successfully",Toast.LENGTH_LONG).show();
                    transitionActivity();
                }
                else {
                    Toast.makeText(SignUpActivity.this,"Something went Wrong",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    public void hideKeyboardMethod(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e){
            e.printStackTrace();
        }
        }
        private void transitionActivity(){
            Intent intent = new Intent(SignUpActivity.this,WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
}

