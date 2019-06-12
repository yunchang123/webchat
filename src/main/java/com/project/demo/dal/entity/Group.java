package com.project.demo.dal.entity;

import javax.persistence.*;

@Entity
@Table(name="Groupchat")

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    private String name;

    private String avatar= "rgb("+(int)(1+Math.random()*(255-1+1))+","+(int)(1+Math.random()*(255-1+1))+","+(int)(1+Math.random()*(255-1+1))+")";//头像

    private String avatarType="nzText";//头像类型

    private String chatType;

    public int getGroupId() {

        return groupId;
    }

    public void setGroupId(int groupId) {

        this.groupId = groupId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarType() {
        return avatarType;
    }

    public void setAvatarType(String avatarType) {
        this.avatarType = avatarType;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", avatarType='" + avatarType + '\'' +
                ", chatType='" + chatType + '\'' +
                '}';
    }
}
