package com.chloe.greeneste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Report_Page extends AppCompatActivity {

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_page);
        Button trash_can = findViewById(R.id.no_trash_can_btn);
        Button too_much_trash = findViewById(R.id.too_much_trash_btn);
        TextView question = findViewById(R.id.question_textview);


        trash_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Report_Page.this, AfterReport_Page.class);
                startActivity(it);
            }
        });
        too_much_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Report_Page.this, AfterReport_Page.class);
                startActivity(it);
            }
        });
    }
}