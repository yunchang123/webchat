package com.project.demo.web;

import com.google.gson.JsonObject;
import com.project.demo.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;
    @PostMapping("save")
    @ResponseBody
    public String save(Integer userId,Integer groupId,String type,String url){
        try {
            fileService.save(userId,groupId,type,url);
            JsonObject json = new JsonObject();
            json.addProperty("state","success");
            return json.toString();
            //return "success";
        }catch (RuntimeException e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("state","fail");
            json.addProperty("msg","发送失败");
            return json.toString();
            //return "404";
        }
    }
}
