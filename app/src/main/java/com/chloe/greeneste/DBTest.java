package com.chloe.greeneste;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBTest extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest);
        TextView tv1 = findViewById(R.id.dbtv1);
        TextView tv2 = findViewById(R.id.dbtv2);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
        DatabaseReference myRef = database.getReference();
        TextView tv3 = findViewById(R.id.dbtv3);
        tv3.setText(myRef.toString());

        MyUser user = new MyUser("2", "nnn", "123123", "123123123", "930909", 2);
        myRef.child("message").setValue(user);
        tv1.setText("done");
        myRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                MyUser value = dataSnapshot.getValue(MyUser.class);
                Log.d(TAG, "Value is: " + value.uid);
                tv2.setText("Value is: " + value.pointtotal);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
