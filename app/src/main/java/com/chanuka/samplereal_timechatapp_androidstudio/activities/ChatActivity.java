package com.chanuka.samplereal_timechatapp_androidstudio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chanuka.samplereal_timechatapp_androidstudio.R;
import com.chanuka.samplereal_timechatapp_androidstudio.databinding.ActivityChatBinding;
import com.chanuka.samplereal_timechatapp_androidstudio.models.User;
import com.chanuka.samplereal_timechatapp_androidstudio.utilities.Constants;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private User receiverUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadReceiverDetails();
    }

    private void loadReceiverDetails() {
        receiverUser = (User) getIntent().getSerializableExtra(Constants.KEY_USER);
        binding.textName.setText(receiverUser.name);
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }
}