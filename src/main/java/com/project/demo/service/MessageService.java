package com.project.demo.service;

import com.google.gson.JsonArray;

public interface MessageService  {
    JsonArray getMessageInfo(Integer userId);

    void newPrivateChat(Integer userId1,Integer userId2);

    void newPublicChat();
}
