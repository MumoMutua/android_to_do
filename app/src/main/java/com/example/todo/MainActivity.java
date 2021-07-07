package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnnew = findViewById(R.id.btn_new);
        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityNew = new Intent(MainActivity.this, ActivityNewToDo.class);
                startActivity(ActivityNew);
            }
        });

        Button btn = findViewById(R.id.btn_do_stuff);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity2 = new Intent(MainActivity.this, Activity2.class);
                startActivity(Activity2);
            }
        });
    }
}