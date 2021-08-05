package com.chloe.greeneste;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Button sign_in = findViewById(R.id.login_button_sign_in);
        Button facebook = findViewById(R.id.login_button_facebook);
        Button email_gmail = findViewById(R.id.login_button_email);
        ImageView logo = findViewById(R.id.login_imageview_logo);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        email_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

