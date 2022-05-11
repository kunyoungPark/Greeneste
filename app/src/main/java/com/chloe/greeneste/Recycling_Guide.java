package com.chloe.greeneste;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Recycling_Guide extends AppCompatActivity implements GuidelineAdapter.OnItemClickListener   {
    RecyclerView rcGuide;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycling_guide);

        ProgressDialog progressDialog ;
        SearchView sv = findViewById(R.id.sv);
        TextView tv = findViewById(R.id.lets);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
                DatabaseReference myRef = database.getReference();
                myRef.child("guideline").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<GuideDTO> getGuideList = new ArrayList<>();
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (DataSnapshot guide: dataSnapshot.getChildren()){
                            GuideDTO getDto = guide.getValue(GuideDTO.class);
                            Log.e(TAG, getDto.toString());
                            getDto.key = guide.getKey();
                            getGuideList.add(getDto);
                        }
                        rcGuide = (RecyclerView) findViewById(R.id.rc_guide);
                        rcGuide.setLayoutManager(new LinearLayoutManager(Recycling_Guide.this));
                        GuidelineAdapter guidelineAdapter = new GuidelineAdapter(getGuideList, getApplicationContext());
                        guidelineAdapter.setOnItemClickListener(Recycling_Guide.this);
                        guidelineAdapter.getFilter().filter(s);
                        rcGuide.setAdapter(guidelineAdapter);
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
                return false;
            }
        });

        progressDialog = new ProgressDialog(Recycling_Guide.this);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();
        init();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 1000); //딜레이 타임 조절

    }

    private void init() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
        DatabaseReference myRef = database.getReference();
        myRef.child("guideline").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<GuideDTO> getGuideList = new ArrayList<>();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot guide: dataSnapshot.getChildren()){
                    GuideDTO getDto = guide.getValue(GuideDTO.class);
                    Log.e(TAG, getDto.toString());
                    getDto.key = guide.getKey();
                    getGuideList.add(getDto);
                }
                rcGuide = (RecyclerView) findViewById(R.id.rc_guide);
                rcGuide.setLayoutManager(new LinearLayoutManager(Recycling_Guide.this));
                GuidelineAdapter guidelineAdapter = new GuidelineAdapter(getGuideList, getApplicationContext());
                guidelineAdapter.setOnItemClickListener(Recycling_Guide.this);
                rcGuide.setAdapter(guidelineAdapter);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    public void onItemClick(View view, GuideDTO guideDTO) {
        Log.e("RecyclerVIew :: ", guideDTO.toString());
        Intent it = new Intent(Recycling_Guide.this, RGDetail.class);
        it.putExtra("data",guideDTO);
        startActivity(it);
    }


}