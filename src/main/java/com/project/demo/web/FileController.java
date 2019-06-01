package com.project.demo.web;

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
    public String save(Integer UserId){
        try {
           // fileService.save(UserId,GroupId,type,Url);
            return "success";
        }catch (RuntimeException e){
            e.printStackTrace();
            return "404";
        }
    }
}
