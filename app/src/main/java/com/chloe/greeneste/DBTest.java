package com.chloe.greeneste;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.InputStream;
import java.net.URL;

public class DBTest extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest);
        TextView tv1 = findViewById(R.id.dbtv1);
        TextView tv2 = findViewById(R.id.dbtv2);
        TextView tv4 = findViewById(R.id.dbtv4);
        ImageView iv = findViewById(R.id.dbiv);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
        DatabaseReference myRef = database.getReference();
        TextView tv3 = findViewById(R.id.dbtv3);
        tv1.setText(myRef.toString());

        myRef.child("guideline").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               for (DataSnapshot guide: dataSnapshot.getChildren()){
                   GuideDTO getDto = guide.getValue(GuideDTO.class);
                   tv2.setText(getDto.getTitle());
                   tv3.setText(getDto.getContent());
                   tv4.setText(getDto.getImg());
                   FirebaseStorage storage = FirebaseStorage.getInstance("gs://greeneste-92f66.appspot.com");
                   StorageReference storageRef = storage.getReference();
                   storageRef.child("guideline/"+guide.getKey() +"/img.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                           //이미지 로드 성공시
                           Glide.with(getApplicationContext())
                                   .load(uri)
                                   .into(iv);
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           //이미지 로드 실패시
                           Toast.makeText(getApplicationContext(), storageRef.child("guideline/"+guide.getKey() +"/img.png").toString(), Toast.LENGTH_SHORT).show();
                       }
                   });
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
