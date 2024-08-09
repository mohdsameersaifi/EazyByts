package com.example.chatapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMessages;
    private EditText editTextMessage;
    private ImageButton buttonSend;

    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        recyclerViewMessages = findViewById(R.id.recyclerViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Initialize message list and adapter
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);

        // Set up RecyclerView
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMessages.setAdapter(messageAdapter);

        // Send button onClick listener
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String messageText = editTextMessage.getText().toString().trim();

        if (!TextUtils.isEmpty(messageText)) {
            // Create a new message object
            Message message = new Message("User", messageText, System.currentTimeMillis());

            // Add the message to the message list
            messageList.add(message);

            // Notify the adapter that a new item has been inserted
            messageAdapter.notifyItemInserted(messageList.size() - 1);

            // Scroll to the bottom of the RecyclerView
            recyclerViewMessages.scrollToPosition(messageList.size() - 1);

            // Clear the input field
            editTextMessage.setText("");
        }
    }

}
