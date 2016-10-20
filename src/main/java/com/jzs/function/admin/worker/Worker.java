package com.jzs.function.admin.worker;

import com.jzs.arc.Entry;

public class Worker extends Entry {

    private int userId;
    private String userName;
    private int userGender;
    private String userTelOne;
    private String userTelTwo;
    private int userPost;
    private int userAttendState;

    public int getUserPost() {
        return userPost;
    }

    public void setUserPost(int userPost) {
        this.userPost = userPost;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getUserTelOne() {
        return userTelOne;
    }

    public void setUserTelOne(String userTelOne) {
        this.userTelOne = userTelOne;
    }

    public String getUserTelTwo() {
        return userTelTwo;
    }

    public void setUserTelTwo(String userTelTwo) {
        this.userTelTwo = userTelTwo;
    }

    public int getUserAttendState() {
        return userAttendState;
    }

    public void setUserAttendState(int userAttendState) {
        this.userAttendState = userAttendState;
    }
}
