package com.chloe.greeneste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class LogIn extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher;




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

