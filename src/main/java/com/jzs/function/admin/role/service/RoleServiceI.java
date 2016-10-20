package com.jzs.function.admin.role.service;

import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.function.admin.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleServiceI {

    Boolean add(Role role,String logUsesr,List<String> list)throws BatchRollbackException;
    Boolean edit(Role role,String logUsesr,List<String> list)throws BatchRollbackException;
    Role selectRole(String roleId);
    List<Role> selectRoleFunction(Role role);
    Boolean delete(String userId,String logUser);
    Page<Role> list(Pageable pageable);
    List<Role> selectFunctions(int authorityTwoId);
    Boolean authorityTwoIsExit(Role role);
};