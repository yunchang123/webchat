package com.project.demo.service;

import com.google.gson.JsonArray;

public interface MessageService  {
    JsonArray getMessageInfo(Integer userId);
}
