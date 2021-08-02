package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.todo.Settings.SharedPrefConfig;
import com.example.todo.auth.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText inputName, inputEmail, inputNumber, inputPassword, inputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnLogin = findViewById(R.id.btnLogIn);
        Button btnRegister = findViewById(R.id.btnRegister);

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputNumber = findViewById(R.id.inputPhoneNumber);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirm);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /**
                Adding function to register a new user
                */

              registerUser();
            }
        });
    }
    private void registerUser(){

        String name, email, number, password, confirm;
        name = inputName.getText().toString().trim();
        email = inputEmail.getText().toString().trim();
        number = inputNumber.getText().toString().trim();
        password = inputPassword.getText().toString().trim();
        confirm = inputConfirmPassword.getText().toString().trim();

        SharedPrefConfig sharedPrefConfig = new SharedPrefConfig(RegisterActivity.this);
        sharedPrefConfig.setUserInfo(name, email, number, password);

        //Mark the user as logged in
        sharedPrefConfig.setLoggingInStatus(true);

        Toast.makeText(this,"User"+" "+ name +" "+ "registered successfully", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(RegisterActivity.this,Activity2.class);
        intent.putExtra("userName", name);
        startActivity(intent);
        finish();
    }
}