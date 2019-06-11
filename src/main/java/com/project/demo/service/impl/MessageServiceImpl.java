package com.project.demo.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.demo.dal.GroupRepo;
import com.project.demo.dal.GroupUserRepo;
import com.project.demo.dal.MessageRepo;
import com.project.demo.dal.entity.Group;
import com.project.demo.dal.entity.GroupUser;
import com.project.demo.dal.entity.Message;
import com.project.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private GroupUserRepo groupUserRepo;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private GroupRepo groupRepo;

    @Override
    public JsonArray getMessageInfo(Integer userId) {
        ArrayList<GroupUser> groupUser= groupUserRepo.findByUserId(userId);
        JsonArray array = new JsonArray();
        for(GroupUser groupUserList:groupUser)
        {
            int groupId = groupUserList.getGroupId();
            Optional<Group> groupOptional = groupRepo.findById(groupId);
            List<Message> lastMsg = messageRepo.findByGroupIdOrderByTimeAsc(groupId);
            Group group = groupOptional.get();
            JsonObject object = new JsonObject();
            object.addProperty("groupId",groupId);
            object.addProperty("avatarType",group.getAvatarType());
            object.addProperty("avatar",group.getAvatar());
            object.addProperty("name",group.getName());
            if(lastMsg.size()!=0)
                object.addProperty("lastmsg",lastMsg.get(0).getContent());
        }

       return array;

    }

    @Override
    public void newPrivateChat(Integer userId1,Integer userId2) {
        Group newGroup = new Group();
        Group group = groupRepo.save(newGroup);
        System.out.println("group 新建id:"+group.getGroupId());
        
    }

    @Override
    public void newPublicChat() {

    }
}
