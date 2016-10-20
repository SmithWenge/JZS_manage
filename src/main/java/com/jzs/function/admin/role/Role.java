package com.jzs.function.admin.role;

import com.jzs.arc.Entry;

public class Role extends Entry {

    private String roleId;
    private String roleName;
    private String authorityroleId;
    private int authorityTwoId;
    private int authorityTwoNum;
    private String navigationURL;
    private int authorityOneId;
    private String authorityTwoName;
    private int functionId;
    private String functionIdS;
    private String functionName;
    private String mark;
    private String MarkAuthorityRole;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthorityroleId() {
        return authorityroleId;
    }

    public void setAuthorityroleId(String authorityroleId) {
        this.authorityroleId = authorityroleId;
    }

    public String getMarkAuthorityRole() {
        return MarkAuthorityRole;
    }

    public void setMarkAuthorityRole(String markAuthorityRole) {
        MarkAuthorityRole = markAuthorityRole;
    }

    public String getFunctionIdS() {
        return functionIdS;
    }

    public void setFunctionIdS(String functionIdS) {
        this.functionIdS = functionIdS;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getAuthorityTwoId() {
        return authorityTwoId;
    }

    public void setAuthorityTwoId(int authorityTwoId) {
        this.authorityTwoId = authorityTwoId;
    }

    public int getAuthorityTwoNum() {
        return authorityTwoNum;
    }

    public void setAuthorityTwoNum(int authorityTwoNum) {
        this.authorityTwoNum = authorityTwoNum;
    }

    public String getNavigationURL() {
        return navigationURL;
    }

    public void setNavigationURL(String navigationURL) {
        this.navigationURL = navigationURL;
    }

    public int getAuthorityOneId() {
        return authorityOneId;
    }

    public void setAuthorityOneId(int authorityOneId) {
        this.authorityOneId = authorityOneId;
    }

    public String getAuthorityTwoName() {
        return authorityTwoName;
    }

    public void setAuthorityTwoName(String authorityTwoName) {
        this.authorityTwoName = authorityTwoName;
    }
}
