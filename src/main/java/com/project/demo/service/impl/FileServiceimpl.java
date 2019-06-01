package com.project.demo.service.impl;

import com.project.demo.dal.entity.File;
import com.project.demo.dal.entity.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceimpl implements FileService{
    @Autowired
    private FileRepo fileRepo;

    @Override
    public void save(Integer userId, Integer groupId, String type, String url) {
        File file = new File();
        file.setUserId(userId);
        file.setGroupId(groupId);
        file.setType(type);
        file.setUrl(url);
        fileRepo.save(file);
    }
}
