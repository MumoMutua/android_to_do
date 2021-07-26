package com.example.todo;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class ScrollingActivity extends AppCompatActivity {

    CollapsingToolbarLayout toolBarLayout;
    TextView txtDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id. toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        Button btnComplete = findViewById(R.id.btn_complete);
        txtDetails = findViewById(R.id.txtDetails);

        btnComplete.setOnClickListener(v -> Snackbar.make(v, "Task Added Successfully", Snackbar.LENGTH_SHORT)
        .setAction("Undo", null).show());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().hasExtra("TITLE")){
            toolBarLayout.setTitle(getIntent().getStringExtra("TITLE"));
        }
        if (getIntent().hasExtra("DETAILS")){
            txtDetails.setText(getIntent().getStringExtra("DETAILS"));
        }
    }
}