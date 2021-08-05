package com.chloe.greeneste;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Mileage_QRScan extends AppCompatActivity {

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mileage_qrscan);
        TextView num_mileage = findViewById(R.id.mileage_textview_scan);
        TextView num_scan = findViewById(R.id.mileage_textview_prize);
        ImageView pfp = findViewById(R.id.mileage_imageView_pfp);



    }
}