package com.project.demo.web;

import com.aliyun.oss.OSSClient;
import com.google.gson.JsonObject;

import com.project.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
//    @PostMapping("ss")
//    @ResponseBody
//    public String ss(@RequestParam("file") MultipartFile file){
//        try {
//            String path = this.getClass().getResource("/").getPath();
//            String fileName = file.getName();
//            System.out.println(path+"    "+fileName);
//            String endpoint = "oss-cn-shenzhen.aliyuncs.com";
//            String accessKeyId = "LTAIowgddTtjQafq";
//            String accessKeySecret = "YLMAwAlM04Nb8KvLCgPcK086tlAQeG";
//            // 创建OSSClient实例。
//            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//            ossClient.putObject("tto", fileName, new File(path));
//            //file.transferTo(new File(path+fileName));
//            // 关闭OSSClient。
//            ossClient.shutdown();
//
//            JsonObject json = new JsonObject();
//            json.addProperty("state","success");
//            return json.toString();
//        }catch (Exception e){
//            e.printStackTrace();
//            JsonObject json = new JsonObject();
//            json.addProperty("state","fail");
//            json.addProperty("msg","发送失败");
//            return json.toString();
//        }
//    }
//    /*@PostMapping("tourl")
//    @ResponseBody
//    public String tourl(String type){
//
//
//        return "";
//    }*/

}
