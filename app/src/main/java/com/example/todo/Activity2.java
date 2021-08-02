package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    TextView welcomeText;

    int numberOfSearches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        welcomeText = findViewById(R.id.welcomeText);


        EditText inputSearch = findViewById(R.id.inputSearch);

        inputSearch.setOnEditorActionListener((v, actionId, event) -> {

            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(this, "Number of Searches is:" + addNumbers(), Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

    }

    public int addNumbers()
    {
        return numberOfSearches ++;
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (getIntent().hasExtra("userName")) {
            welcomeText.setText(getIntent().getStringExtra("userName"));
        }
    }

}