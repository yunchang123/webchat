package com.project.demo.service.impl;

import com.project.demo.dal.FileRepo;
import com.project.demo.dal.entity.Message;
import com.project.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceimpl implements FileService {
    @Autowired
    private FileRepo fileRepo;
    @Override
    public void save(Integer userId, Integer groupId, String type, String content) {
        Message message = new Message();
        message.setUserId(userId);
        message.setGroupId(groupId);
        message.setType(type);
        message.setContent(content);
        fileRepo.save(message);

    }
}
