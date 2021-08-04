package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

        Button btnDelete = findViewById(R.id.btn_delete);

        btnDelete.setOnClickListener(v -> {
            // TODO: Add alert to ask user if he/ she really wants to delete this

            if (idToUse == 0){
                Toast.makeText(this, "No Todo Selected", Toast.LENGTH_SHORT).show();
            }
            else {
                notesBox.remove(idToUse);
                Snackbar.make(v, "To do deleted successfully", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ActivityNewToDo.class);
                startActivity(intent);
                finish();
            }
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
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
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