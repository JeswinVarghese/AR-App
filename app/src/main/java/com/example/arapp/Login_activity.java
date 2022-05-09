package com.example.arapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_activity extends AppCompatActivity {

    private EditText email_login,password_login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_login = findViewById(R.id.username_login);
        password_login = findViewById(R.id.password_login);
        Button login_btn = findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(view -> {
            loginuser();
        });

    }

    private void loginuser() {
        String email,password;
        email = email_login.getText().toString();
        password = password_login.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Enter Email!!",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Enter Password!!",Toast.LENGTH_LONG).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login_activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"LoginFailed!!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}