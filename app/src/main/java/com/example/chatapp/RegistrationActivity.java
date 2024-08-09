package com.example.chatapp;

import org.mindrot.jbcrypt.BCrypt;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            // Hash the password (use a secure hashing algorithm like BCrypt)
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Insert the user into the database
            if (insertUserIntoDatabase(username, hashedPassword)) {
                Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                finish(); // Close registration and return to login
            } else {
                Toast.makeText(RegistrationActivity.this, "Registration failed, try a different username", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean insertUserIntoDatabase(String username, String hashedPassword) {
        // Insert user data into the database
        // Use your database helper or connection class to perform the insert operation
        // Return true if successful, false otherwise
    }
}
