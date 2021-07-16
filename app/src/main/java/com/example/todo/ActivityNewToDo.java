package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.todo.models.Note;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityNewToDo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);

        Button btnCancel=findViewById(R.id.btn_cancel);
        Button btnCreateTasks = findViewById(R.id.createTask);
        ImageView imageAddSubtask = findViewById(R.id.AddSubtask);

        ImageView firstFile = findViewById(R.id.firstFile);
        ImageView secondFile = findViewById(R.id.secondFile);

        EditText editTitle = findViewById(R.id.editTitle);
        TextInputEditText editDetails = findViewById(R.id.editDetails);
//        TextInputEditText editSubtasks = findViewById(R.id.Subtasks);

        Note newNote = new Note();

        btnCreateTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDetails.getText().toString());

                Toast.makeText(ActivityNewToDo.this,newNote.getTitle(), Toast.LENGTH_LONG).show();

                Snackbar.make(v, newNote.getDescription(),Snackbar.LENGTH_LONG).show();
            }
        });
    }
}