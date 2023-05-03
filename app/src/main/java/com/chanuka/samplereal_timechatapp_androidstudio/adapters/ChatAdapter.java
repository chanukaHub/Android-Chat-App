package com.chanuka.samplereal_timechatapp_androidstudio.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanuka.samplereal_timechatapp_androidstudio.databinding.ItemContainerReceivedMessageBinding;
import com.chanuka.samplereal_timechatapp_androidstudio.databinding.ItemContainerSentMessageBinding;
import com.chanuka.samplereal_timechatapp_androidstudio.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<ChatMessage> chatMessages;
    private  final Bitmap receiverProfileImage;
    private final String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;

    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap receiverProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.receiverProfileImage = receiverProfileImage;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_SENT) {
            return new SentMessageHolder(
                    ItemContainerSentMessageBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
            ));
        } else {
            return new ReceivedMessageHolder(
                    ItemContainerReceivedMessageBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
            ));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==VIEW_TYPE_SENT) {
            ((SentMessageHolder)holder).setData(chatMessages.get(position));
        } else {
            ((ReceivedMessageHolder)holder).setData(chatMessages.get(position), receiverProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).senderId.equals(senderId)) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    static class SentMessageHolder extends RecyclerView.ViewHolder {
        private  final ItemContainerSentMessageBinding binding;

        SentMessageHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        void setData(ChatMessage message) {
            binding.textMessage.setText(message.message);
            binding.textDateTime.setText(message.dateTime);
        }
    }

    static class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        private  final ItemContainerReceivedMessageBinding binding;

        ReceivedMessageHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding) {
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        void setData(ChatMessage message, Bitmap receiverProfileImage) {
            binding.textMessage.setText(message.message);
            binding.textDateTime.setText(message.dateTime);
            binding.imageProfile.setImageBitmap(receiverProfileImage);
        }
    }
}
