package com.chloe.greeneste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        ImageButton signin = findViewById(R.id.signin_btn);
        Button btn = findViewById(R.id.join);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LogIn.this, Signup.class);
                startActivity(it);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth =  FirebaseAuth.getInstance();
                TextInputEditText aemail = findViewById(R.id.myEmail);
                TextInputEditText pw = findViewById(R.id.myPwd);
                String email = aemail.getText().toString();
                String pwd = pw.getText().toString();
                mAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LogIn.this, HomePage.class);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(LogIn.this,"로그인 오류",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}

