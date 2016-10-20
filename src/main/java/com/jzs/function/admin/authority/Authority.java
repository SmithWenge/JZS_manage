package com.jzs.function.admin.authority;

import com.jzs.arc.Entry;

public class Authority extends Entry{
    private String roleId;
    private int authorityTwoId;
    private int functionId;

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public int getAuthorityTwoId() {
        return authorityTwoId;
    }

    public void setAuthorityTwoId(int authorityTwoId) {
        this.authorityTwoId = authorityTwoId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
