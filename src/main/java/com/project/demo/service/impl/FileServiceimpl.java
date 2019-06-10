package com.project.demo.service.impl;

import com.project.demo.dal.entity.FileRepo;
import com.project.demo.dal.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceimpl implements FileService{
    @Autowired
    private FileRepo fileRepo;
    @Override
    public void save(Integer userId, Integer groupId, String type, String url) {
        Message file = new Message();
        file.setUserId(userId);
        file.setGroupId(groupId);
        file.setType(type);
        file.setContent(url);
        fileRepo.save(file);
    }
}
