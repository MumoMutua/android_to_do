package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todo.Settings.SharedPrefConfig;
import com.example.todo.auth.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefConfig myPreferenceStorage = new SharedPrefConfig(getApplicationContext());

        if (myPreferenceStorage.isLoggedIn()){

            Intent intent = new Intent(MainActivity.this, ActivityNewToDo.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }


        View image = findViewById(R.id.image_view);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MotionActivity.class);
                startActivity(intent);
            }
        });

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