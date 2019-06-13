package com.project.demo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.List;

public interface MessageService  {
    JsonArray getMessageInfo(Integer userId);

    JsonObject newPrivateChat(Integer userId1, Integer userId2);

    JsonObject newPublicChat(String groupName, Integer[] users,String avatar);

    JsonObject sendMessage(Integer userId,Integer groupId,String content, String type,Date time);

    JsonArray chatRecords(Integer groupId);
}
