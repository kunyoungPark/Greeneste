package com.chloe.greeneste;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Button report = findViewById(R.id.home_report_btn);
        Button tracker = findViewById(R.id.home_tracking_btn);
        Button mileage = findViewById(R.id.home_mileage_btn);
        Button guide = findViewById(R.id.home_guide_btn);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tracker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mileage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                guide.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}