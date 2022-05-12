package com.chloe.greeneste;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AfterReport_Page extends AppCompatActivity {
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterreport_page);

        TextView address = findViewById(R.id.address);
        address.setText(getIntent().getStringExtra("address"));

        TextView location = findViewById(R.id.location);
        location.setText(getIntent().getStringExtra("location"));


        WebView myWebView = (WebView) findViewById(R.id.reportWebView);
        myWebView.loadUrl("https://minwon.me.go.kr/security/loginForm.do?menuKey=69");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClientClass());


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://greeneste-92f66-default-rtdb.asia-southeast1.firebasedatabase.app" );
        DatabaseReference myRef = database.getReference();

        String userId = "1";
        Button doneReport = findViewById(R.id.done_report_btn);
        doneReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> updates = new HashMap<>();
                updates.put("user/"+userId+"/point", ServerValue.increment(1));
                myRef.updateChildren(updates);
                Toast.makeText(getApplicationContext(), "point +1", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest wrr) {
            Log.d("check URL", String.valueOf(wrr.getUrl()));
            view.loadUrl(String.valueOf(wrr.getUrl()));
            return true;
        }
    }
}
