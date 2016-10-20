package com.jzs.arc.utils;

import com.jzs.function.admin.role.Role;

import java.util.ArrayList;
import java.util.List;

public class ListCutUtils {

    public static List<Role> getList(List<Role> roleAlls,List<Role> roles) {

        List<Role> roleCut = new ArrayList<>();
        for (Role roleAll : roleAlls) {
            Boolean tmp = true;
            for (Role role : roles) {
                if (roleAll.getFunctionIdS().equals(role.getFunctionIdS())) {
                    tmp = false;
                }
            }
            if (tmp == true) {
                roleCut.add(roleAll);
            }
        }

        return roleCut;
    }
}
