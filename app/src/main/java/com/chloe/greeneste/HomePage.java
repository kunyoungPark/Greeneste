package com.chloe.greeneste;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseUser mUser;
    public String username;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ImageButton trash_can_location = findViewById(R.id.imageView15);
        ImageButton qr_code = findViewById(R.id.imageView16);
        ImageButton report = findViewById(R.id.imageView17);
        ImageButton recycling_guide = findViewById(R.id.imageView19);
        TextView tv = findViewById(R.id.home_display);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if (mUser == null){
            Intent it = new Intent(HomePage.this, LogIn.class);
            startActivity(it);
            finish();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
        DatabaseReference myRef = database.getReference("user/");

        myRef.child(mUser.getUid()).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.e(">>>>>>>>>>>>>>>>>>", String.valueOf(snapshot));
                    username = snapshot.getValue(String.class);
                Log.w(">>>>>>>>>>>>>>>>>>",username);
                tv.setText("Hello, "+ username + "!");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(">>>>>>>>>>>>>>>>>>", mUser.getUid());

            }
        });


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
               Intent it = new Intent(HomePage.this, Userpage.class);
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