package com.example.chatapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.textViewUserName.setText(message.getUserName());
        holder.textViewMessageContent.setText(message.getMessageContent());

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String formattedTime = sdf.format(message.getTimestamp());
        holder.textViewMessageTimestamp.setText(formattedTime);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName, textViewMessageContent, textViewMessageTimestamp;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewMessageContent = itemView.findViewById(R.id.textViewMessageContent);
            textViewMessageTimestamp = itemView.findViewById(R.id.textViewMessageTimestamp);
        }
    }

}
