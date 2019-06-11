package com.project.demo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface MessageService  {
    JsonArray getMessageInfo(Integer userId);

    JsonObject newPrivateChat(Integer userId1, Integer userId2);

    void newPublicChat();
}
