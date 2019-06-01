package com.project.demo.dal.entity;

import javax.persistence.*;

@Entity
@Table(name="Group_user")
public class Group_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private int groupId;

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
        return "Group_user{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}
