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

public class HomePage extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    MyUser myUser;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ImageButton trash_can_location = findViewById(R.id.imageView15);
        ImageButton qr_code = findViewById(R.id.imageView16);
        ImageButton report = findViewById(R.id.imageView17);
        ImageButton recycling_guide = findViewById(R.id.imageView19);

        init();
        Log.e(">>>>>>>>>>>>>>>>>>", myUser.uid);
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

    public void init(){

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        if (mUser == null){
            Intent it = new Intent(HomePage.this, LogIn.class);
            startActivity(it);
            finish();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
        DatabaseReference myRef = database.getReference();
        myRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot user: dataSnapshot.getChildren()){
                   myUser= user.getValue(MyUser.class);
                    TextView tv = findViewById(R.id.home_display);
                    tv.setText("Hello, ");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}