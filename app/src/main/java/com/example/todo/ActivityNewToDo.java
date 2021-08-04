package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.todo.models.Note;
import com.google.android.material.textfield.TextInputEditText;

import io.objectbox.Box;

public class ActivityNewToDo extends AppCompatActivity {

    EditText editTitle;
    private Box<Note> notesBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);

        notesBox = ObjectBox.get().boxFor(Note.class);

        Button btnCancel=findViewById(R.id.btn_delete);
        Button btnCreateTasks = findViewById(R.id.createTask);
        ImageView imageAddSubtask = findViewById(R.id.AddSubtask);

        ImageView firstFile = findViewById(R.id.firstFile);
        ImageView secondFile = findViewById(R.id.secondFile);

        editTitle = findViewById(R.id.editTitle);
        TextInputEditText editDetails = findViewById(R.id.editDetails);
//        TextInputEditText editSubtasks = findViewById(R.id.Subtasks);

        Note newNote = new Note();

        btnCreateTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDetails.getText().toString());

                long id = notesBox.put(newNote); // creates a new note in the database

                Intent intent = new Intent(ActivityNewToDo.this, ScrollingActivity.class);
                intent.putExtra("ID", id);

                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (editTitle.getText().toString().trim().isEmpty()){

            Toast.makeText(this, "Draft not Saved", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Note Saved to Draft", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}