package com.jzs.function.admin.login;

import com.jzs.arc.Entry;

public class AdminUser extends Entry {
    private int adminId;
    private String userName;
    private String userLoginName;
    private String userLoginPass;
    private int adminIsLock;
    private String roleId;
    private int deleteFlag;
    private String userEmail;
    private String adminLoginPassNew;
    private String adminLoginPassNewRe;
    private int adminIsChanged;
    private int adminIsReturn;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLoginPass() {
        return userLoginPass;
    }

    public void setUserLoginPass(String userLoginPass) {
        this.userLoginPass = userLoginPass;
    }

    public void setAdminIsChanged(int adminIsChanged) {
        this.adminIsChanged = adminIsChanged;
    }

    public void setAdminIsReturn(int adminIsReturn) {
        this.adminIsReturn = adminIsReturn;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public int getAdminIsChanged() {

        return adminIsChanged;
    }

    public int getAdminIsReturn() {
        return adminIsReturn;
    }

    public void setAdminLoginPassNew(String adminLoginPassNew) {
        this.adminLoginPassNew = adminLoginPassNew;
    }

    public void setAdminLoginPassNewRe(String adminLoginPassNewRe) {
        this.adminLoginPassNewRe = adminLoginPassNewRe;
    }

    public String getAdminLoginPassNew() {

        return adminLoginPassNew;
    }

    public String getAdminLoginPassNewRe() {
        return adminLoginPassNewRe;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setAdminIsLock(int adminIsLock) {
        this.adminIsLock = adminIsLock;
    }

    public int getAdminId() {

        return adminId;
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

    public int getAdminIsLock() {
        return adminIsLock;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getDeleteFlag() {

        return deleteFlag;
    }
}
