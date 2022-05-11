package com.chloe.greeneste;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RGDetail extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rgdetail);

        ProgressDialog progressDialog ;
        SearchView sv = findViewById(R.id.sv);
        TextView tv = findViewById(R.id.lets);
        progressDialog = new ProgressDialog(RGDetail.this);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();


        GuideDTO getDto = (GuideDTO) getIntent().getSerializableExtra("data");
        TextView title = findViewById(R.id.detail_title);
        title.setText(getDto.getTitle());
        TextView content = findViewById(R.id.detail_content);
        content.setText(getDto.getContent());
        ImageView imv = findViewById(R.id.detail_img);
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://greeneste-92f66.appspot.com");
        StorageReference storageRef = storage.getReference();
        storageRef.child("guideline/"+getDto.key+"/img.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //이미지 로드 성공시
                Glide.with(getApplicationContext())
                        .load(uri)
                        .into(imv);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Toast.makeText(getApplicationContext(), storageRef.child("guideline/"+getDto.key +"/img.png").toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 500); //딜레이 타임 조절


    }
}
