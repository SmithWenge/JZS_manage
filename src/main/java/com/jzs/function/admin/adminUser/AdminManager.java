package com.jzs.function.admin.adminUser;

import com.jzs.arc.Entry;

public class AdminManager extends Entry {

    private int userId;
    private String roleName;
    private String userName;
    private String userLoginName;
    private String userLoginPass;
    private String roleId;
    private int deleteFlag;
    private int userGender;
    private String userTelOne;
    private int userPost;
    private String mark;
    private String newUserLoginPass;

    public String getNewUserLoginPass() {
        return newUserLoginPass;
    }

    public void setNewUserLoginPass(String newUserLoginPass) {
        this.newUserLoginPass = newUserLoginPass;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getUserPost() {
        return userPost;
    }

    public void setUserPost(int userPost) {
        this.userPost = userPost;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserLoginPass() {
        return userLoginPass;
    }

    public void setUserLoginPass(String userLoginPass) {
        this.userLoginPass = userLoginPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getDeleteFlag() {

        return deleteFlag;
    }
}
