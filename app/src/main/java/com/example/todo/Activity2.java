package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo.adapters.NotesAdapter;
import com.example.todo.models.Note;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class Activity2 extends AppCompatActivity {

    TextView welcomeText;

    int numberOfSearches = 0;
    private Box<Note> notesBox;
    private List<Note> todos = new ArrayList<>();
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        notesBox = ObjectBox.get().boxFor(Note.class);

        Toast.makeText(this, "You have " + notesBox.count() + "To do's", Toast.LENGTH_SHORT).show();

        notesRecyclerView = findViewById(R.id.notes_recycler);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
            welcomeText.setText("Hello" + (getIntent().getStringExtra("userName")));
        }

        todos = notesBox.getAll();
        notesAdapter = new NotesAdapter(todos, Activity2.this);
        notesRecyclerView.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();

        notesRecyclerView.setAdapter(notesAdapter);
    }

}