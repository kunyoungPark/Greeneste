package com.chloe.greeneste;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private MyUser myUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();
        TextInputEditText userid_input= findViewById(R.id.userid_input);
        TextInputEditText name_input= findViewById(R.id.name_input);
        TextInputEditText birth_input= findViewById(R.id.birth_input);
        TextInputEditText email_input= findViewById(R.id.email_input);
        TextInputEditText phone_number_input= findViewById(R.id.phone_number_input);
        TextInputEditText password_input= findViewById(R.id.password_input);
        ImageButton complete = findViewById(R.id.complete);


        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_input.getText().toString();
                String password = password_input.getText().toString();

                mAuth =  FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(" s i", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    MyUser myUser = new MyUser(email, userid_input.getText().toString(), user.getUid(), phone_number_input.getText().toString(), birth_input.getText().toString(),0  );
                                    saveUser(myUser);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("s i ", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }
        });


    }

    public void saveUser(MyUser user){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
        DatabaseReference myRef = database.getReference();
        myRef.child("user/"+user.uid).setValue(user);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }



}


