package com.example.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.todo.Settings.SharedPrefConfig;
import com.example.todo.models.Note;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import io.objectbox.Box;


public class ScrollingActivity extends AppCompatActivity {

    CollapsingToolbarLayout toolBarLayout;
    TextView txtDetails;
    long idToUse = 0;
    private Box<Note> notesBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesBox = ObjectBox.get().boxFor(Note.class);

        setContentView(R.layout.activity_scrolling);

        Button btnDelete = findViewById(R.id.btnCancel);

        btnDelete.setOnClickListener(v -> {
            // TODO: Add alert to ask user if he/ she really wants to delete this

            alertUser();

        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id. toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        Button btnComplete = findViewById(R.id.btn_complete);
        txtDetails = findViewById(R.id.txtDetails);

        btnComplete.setOnClickListener(v -> Snackbar.make(v, "Task Added Successfully", Snackbar.LENGTH_SHORT)
        .setAction("Undo", null).show());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (idToUse == 0) {
                    Toast.makeText(ScrollingActivity.this, "Nothing to edit", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent = new Intent(ScrollingActivity.this, ActivityNewToDo.class);
                    intent.putExtra("ID", idToUse);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().hasExtra("ID")){

            idToUse = getIntent().getLongExtra("ID",0);
            Note savedNote = notesBox.get(idToUse);

            toolBarLayout.setTitle(savedNote.getTitle());
            txtDetails.setText(savedNote.getDescription());
        }

    }
    public void deleteTodo(){

        if (idToUse == 0){
            Toast.makeText(this, "No Task Selected", Toast.LENGTH_SHORT).show();
        }
        else {
            notesBox.remove(idToUse);
            Toast.makeText(this, "Task Deleted ", Toast.LENGTH_SHORT).show();
            onBackPressed();
            finish();
//                Intent intent = new Intent(this, Activity.class);
//                startActivity(intent);
//                finish();
        }

    }
    public void alertUser(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ScrollingActivity.this);
        builder.setTitle("Delete ToDo")
                .setMessage("Are you sure you want to delete this message?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTodo();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ScrollingActivity.this, "Deleting Stopped", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_id = item.getItemId();

        if (menu_id == R.id.action_logout){

            SharedPrefConfig sharedPrefConfig = new SharedPrefConfig(this);
            sharedPrefConfig.setLoggingInStatus(false);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

            return true;

        }

        else if (menu_id == R.id.action_settings){
            Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            return  true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

}