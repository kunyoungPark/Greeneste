package com.chloe.greeneste;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        ImageButton google = findViewById(R.id.signin_google);
        ImageButton facebook = findViewById(R.id.signin_fb);
        ImageButton signin = findViewById(R.id.signin_btn);
        ImageButton forgotuser = findViewById(R.id.forgotuser);
        ImageButton forgotpass = findViewById(R.id.forgotpass);

    }
}

