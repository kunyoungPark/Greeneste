package com.chloe.greeneste;

import static android.content.ContentValues.TAG;

import android.content.Context;
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

import java.util.ArrayList;

public class Recycling_Guide extends AppCompatActivity implements GuidelineAdapter.OnItemClickListener   {

    private Context mContext;
    private RecyclerView rcGuide;
    private GuidelineAdapter guidelineAdapter;
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycling_guide);
        SearchView sv = findViewById(R.id.sv);

        mContext = this;
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
        }, 3000); //딜레이 타임 조절


    }

    private void init() {
       rcGuide = (RecyclerView) findViewById(R.id.rc_guide);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rcGuide.setLayoutManager(mLayoutManager);
        //Log.w(TAG, getGuideList().get(0).toString());
        guidelineAdapter = new GuidelineAdapter(getGuideList(), mContext);
        guidelineAdapter.setOnItemClickListener(this);
        rcGuide.setAdapter(guidelineAdapter);
    }

    @Override
    public void onItemClick(View view, GuideDTO guideDTO) {
        Log.e("RecyclerVIew :: ", guideDTO.toString());
    }

    public ArrayList<GuideDTO> getGuideList(){
        ArrayList<GuideDTO> getDtoList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
        DatabaseReference myRef = database.getReference();
        myRef.child("guideline").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot guide: dataSnapshot.getChildren()){
                    GuideDTO getDto = guide.getValue(GuideDTO.class);
                    Log.e(TAG, getDto.toString());
                    getDtoList.add(getDto);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return getDtoList;
    }
}