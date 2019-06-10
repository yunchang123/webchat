package com.project.demo.dal.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    private int userId;

    private int groupId;

    private String content;

    private String type;

    private Date time;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMessageId() {

        return messageId;
    }

    public void setMessageId(int messageId) {

        this.messageId = messageId;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public int getGroupId() {

        return groupId;
    }

    public void setGroupId(int groupId) {

        this.groupId = groupId;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public Date getTime() {

        return time;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", groupId=" + groupId +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                '}';
    }
}
