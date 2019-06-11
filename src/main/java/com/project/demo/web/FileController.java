package com.project.demo.web;

import com.aliyun.oss.OSSClient;
import com.google.gson.JsonObject;

import com.project.demo.dal.entity.Message;
import com.project.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;
    @PostMapping("save")
    @ResponseBody
    public String save(Integer userId,Integer groupId,String type,String content){
        try {
            fileService.save(userId,groupId,type,content);
            JsonObject json = new JsonObject();
            json.addProperty("state","success");
            return json.toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("state","fail");
            json.addProperty("msg","发送失败");
            return json.toString();
        }
    }
    @PostMapping("upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,String type){
        try {
            System.out.println(type);
            String fileName = UUID.randomUUID().toString()+file.getOriginalFilename().replaceAll(" ", "");
            String endpoint = "oss-cn-shenzhen.aliyuncs.com";
            String accessKeyId = "LTAIowgddTtjQafq";
            String accessKeySecret = "YLMAwAlM04Nb8KvLCgPcK086tlAQeG";
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            File toFile = null;
            if(file.equals("")||file.getSize()<=0){
                file = null;
            }else {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            }
            ossClient.putObject("tto", fileName,toFile);
            // 关闭OSSClient。
            ossClient.shutdown();
            JsonObject json = new JsonObject();
            json.addProperty("state","success");
            json.addProperty("filename",fileName);

            return json.toString();
        }catch (Exception e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("state","fail");
            json.addProperty("msg","发送失败");
            return json.toString();
        }
    }
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
