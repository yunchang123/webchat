package com.project.demo.service;

import java.util.Date;

public interface FileService {
   void save(Integer userId, Integer groupId, String type, String url, Date time);

}
