package com.project.demo.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.demo.dal.GroupRepo;
import com.project.demo.dal.GroupUserRepo;
import com.project.demo.dal.MessageRepo;
import com.project.demo.dal.UserRepo;
import com.project.demo.dal.entity.Group;
import com.project.demo.dal.entity.GroupUser;
import com.project.demo.dal.entity.Message;
import com.project.demo.dal.entity.User;
import com.project.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UserRepo userRepo;

    @Override
    public JsonArray getMessageInfo(Integer userId) {
        ArrayList<GroupUser> groupUser= groupUserRepo.findByUserId(userId);
        JsonArray array = new JsonArray();
        System.out.println(groupUser.size()+"   ......");
        for(GroupUser groupUserList:groupUser)
        {
            int groupId = groupUserList.getGroupId();
            Optional<Group> groupOptional = groupRepo.findById(groupId);
            List<Message> lastMsg = messageRepo.findByGroupIdOrderByTimeDesc(groupId);
            Group group = groupOptional.get();
            JsonObject object = new JsonObject();

            if(group.getChatType().equals("private"))
            {
               GroupUser otherGroupUser = groupUserRepo.findByGroupIdAndUserIdNot(groupId,userId);
               Integer  otherUserId = otherGroupUser.getUserId();
               Optional<User> otherUser = userRepo.findById(otherUserId);

               object.addProperty("groupId", groupId);
               object.addProperty("avatarType", otherUser.get().getAvatarType());
               object.addProperty("avatar", otherUser.get().getAvatar());
               object.addProperty("name", otherUser.get().getNickname());
               if (lastMsg.size() != 0)
                   if(lastMsg.get(0).getContent().length()>10)
                       object.addProperty("lastmsg", lastMsg.get(0).getContent().substring(0,10));
                    else
                       object.addProperty("lastmsg", lastMsg.get(0).getContent());

            }
            else{
                object.addProperty("groupId", groupId);
                object.addProperty("avatarType", group.getAvatarType());
                object.addProperty("avatar", group.getAvatar());
                object.addProperty("name", group.getName());
                if (lastMsg.size() != 0)
                    object.addProperty("lastmsg", lastMsg.get(0).getContent());
        }
            array.add(object);
        }
        System.out.println(array.size());
       return array;

    }

    @Override
    public JsonObject newPrivateChat(Integer userId1,Integer userId2) {
        try {
            Group newGroup = new Group();
            newGroup.setChatType("private");
            Group group = groupRepo.save(newGroup);
            System.out.println("group 新建id:" + group.getGroupId());

            GroupUser groupUser1 = new GroupUser();
            GroupUser groupUser2 = new GroupUser();
            groupUser1.setGroupId(group.getGroupId());
            groupUser1.setUserId(userId1);
            groupUser2.setGroupId(group.getGroupId());
            groupUser2.setUserId(userId2);
            groupUserRepo.save(groupUser1);
            groupUserRepo.save(groupUser2);
            JsonObject object = new JsonObject();
            object.addProperty("state","success");
            object.addProperty("groupId",group.getGroupId());
            return object;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JsonObject object = new JsonObject();
            object.addProperty("state","fail");
            return object;

        }


    }

    @Override
    public JsonObject newPublicChat(String groupName, Integer[] users,String avatar) {
        JsonObject object = new JsonObject();
        try {
            Group newGroup = new Group();
            newGroup.setName(groupName);
            newGroup.setChatType("public");
            newGroup.setAvatar(avatar);
            Group group = groupRepo.save(newGroup);
            Integer groupId = group.getGroupId();
            for(Integer userId:users)
            {
                GroupUser groupUser = new GroupUser();
                groupUser.setUserId(userId);
                groupUser.setGroupId(groupId);
                groupUserRepo.save(groupUser);
            }

            object.addProperty("state","success");
            object.addProperty("groupId",groupId);
            return object;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            object.addProperty("state","fail");
            return object;
        }
    }

    @Override
    public JsonObject sendMessage(Integer userId,Integer groupId,String content, String type,Date time) {
        JsonObject object = new JsonObject();
        try {
            Message message = new Message();
            message.setUserId(userId);
            message.setGroupId(groupId);
            message.setContent(content);
            message.setType(type);
            message.setTime(time);
            messageRepo.save(message);
            object.addProperty("state","success");
            return object;
        }catch (Exception ex)
        {
            ex.printStackTrace();
            object.addProperty("state","fail");
            return object;
        }
    }

    @Override
    public JsonArray chatRecords(Integer groupId) {
        ArrayList<Message>messageList = messageRepo.findByGroupId(groupId);
        JsonArray array = new JsonArray();
        for(Message message:messageList)
        {
            JsonObject object = new JsonObject();
            int userId = message.getUserId();
            Optional<User> user = userRepo.findById(userId);
            object.addProperty("messageId",message.getMessageId());
            object.addProperty("userId",userId);
            object.addProperty("groupId",groupId);
            object.addProperty("nickname",user.get().getNickname());
            object.addProperty("avatarType",user.get().getAvatarType());
            object.addProperty("avatar",user.get().getAvatar());
            object.addProperty("content",message.getContent());
            object.addProperty("type",message.getType());
            object.addProperty("time",message.getTime().toString());
            array.add(object);


        }
        return array;
    }
}
