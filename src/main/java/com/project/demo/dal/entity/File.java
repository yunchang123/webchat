package com.project.demo.dal.entity;

import javax.persistence.*;

@Entity
@Table(name="File")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;

    private String type;

    private String url;

    private int userId;

    private int groupId;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}
