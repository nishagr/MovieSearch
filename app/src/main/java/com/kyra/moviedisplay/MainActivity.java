package com.kyra.moviedisplay;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buSearch(View view) {
        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(),ListDisplay.class);
                EditText etQuery= findViewById(R.id.etQuery);
                TextView tvEmptyQuery=findViewById(R.id.tvEmptyQuery);
                String query=etQuery.getText().toString();
                intent.putExtra("query",query);
                if(check(query)){
                    startActivity(intent);
                    finish();
                }
                else {
                    tvEmptyQuery.setVisibility(View.VISIBLE);
                    check(query);
                }
            }
        };
        handler.postDelayed(runnable,500);
    }
    public boolean check(String query){
        return query.length()!=0;
    }
}
