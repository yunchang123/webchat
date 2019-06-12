package com.project.demo.dal.entity;

import javax.persistence.*;

@Entity
@Table(name="GroupUser")
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupUserId;

    private int userId;

    private int groupId;

    public int getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(int groupUserId) {
        this.groupUserId = groupUserId;
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
        return "GroupUser{" +
                "groupUserId=" + groupUserId +
                ", userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}
