package com.jzs.function.admin.adminUser.service;

import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.function.admin.adminUser.AdminManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserServiceI {

    Boolean add(AdminManager adminManager,String logUsesr)throws BatchRollbackException;
    AdminManager select(int userId);
    Boolean update(AdminManager adminManager,String LogUser)throws BatchRollbackException;
    Boolean promote(AdminManager adminManager,String LogUser)throws BatchRollbackException;
    Boolean delete(int userId,String logUser)throws BatchRollbackException;
    Boolean reuse(int userId,String logUser)throws BatchRollbackException;
    Page<AdminManager> list(Pageable pageable);
    List<AdminManager> selectRoles();
    Boolean nameIsUnique(String userLoginName);
    Boolean resetPass(AdminManager adminManager,String LogUser);
}
