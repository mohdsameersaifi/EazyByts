package com.example.chatapp;
import android.content.SharedPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            if (checkCredentials(username, password)) {
                // Save login status
                SharedPreferences sharedPreferences = getSharedPreferences("chatAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("username", username);
                editor.apply();

                // Redirect to MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Close LoginActivity
            } else {
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkCredentials(String username, String password) {
        if (username.equals("admin")) {
            return true;
        }
        return false; // Default return value for other cases
    }


    private String getHashedPasswordFromDatabase(String username) {
    }

}
