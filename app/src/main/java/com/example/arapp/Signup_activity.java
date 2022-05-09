package com.example.arapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_activity extends AppCompatActivity {
    private EditText email_signup,password_signup;
    private Button signup_btn;
    private TextView login_tv;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email_signup = findViewById(R.id.username_signup);
        password_signup = findViewById(R.id.password_signup);
        signup_btn = findViewById(R.id.signup_button);
        login_tv = findViewById(R.id.login_tv);
        mAuth = FirebaseAuth.getInstance();

        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signup_activity.this,Login_activity.class);
                startActivity(i);
            }
        });
        signup_btn.setOnClickListener(view -> {
            register_newuser();
        });

    }

    private void register_newuser() {
        String email,password;
        email = email_signup.getText().toString();
        password = password_signup.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Enter Email!!", Toast.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Enter Password!!",Toast.LENGTH_LONG).show();
            return;
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Sign-Up successfull",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Signup_activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Sign-Up Failed!!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}