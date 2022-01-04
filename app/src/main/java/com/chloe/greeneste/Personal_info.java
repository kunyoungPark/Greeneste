package com.chloe.greeneste;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Personal_info extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);
        Button done = findViewById(R.id.info_done_button);
        TextView school = findViewById(R.id.info_school_input);
        TextView birthday = findViewById(R.id.info_birthday_input);
        TextView grade = findViewById(R.id.info_grade_input);
        TextView address = findViewById(R.id.info_address_input);
        TextView city = findViewById(R.id.info_city_input);
        TextView name = findViewById(R.id.info_name_input);


        done.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View v) {


            }
            });
        }

    }

