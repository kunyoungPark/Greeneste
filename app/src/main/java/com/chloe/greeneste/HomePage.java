package com.chloe.greeneste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ImageButton trash_can_location = findViewById(R.id.imageView15);
        ImageButton qr_code = findViewById(R.id.imageView16);
        ImageButton report = findViewById(R.id.imageView17);
        ImageButton recycling_guide = findViewById(R.id.imageView19);

       trash_can_location.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent it = new Intent(HomePage.this, Trashcan_Pathfinding.class);
               startActivity(it);
           }
       });
       qr_code.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent it = new Intent(HomePage.this, Mileage_QRScan.class);
               startActivity(it);
           }
        });
       report.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent it = new Intent(HomePage.this, Report_Page.class);
              startActivity(it);
          }
        });
       recycling_guide.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent it = new Intent(HomePage.this, Recycling_Guide.class);
               startActivity(it);
           }
        });
    }
}