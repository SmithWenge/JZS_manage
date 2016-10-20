package com.jzs.function.admin.adminUser.repository;

import com.jzs.function.admin.adminUser.AdminManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserRepositoryI {

    Boolean addUser(AdminManager adminManager);
    Boolean addUserInfo(AdminManager adminManager);
    AdminManager select(int userId);
    Boolean updateUser(AdminManager adminManager);
    Boolean updateUserInfo(AdminManager adminManager);
    Boolean resetPass(AdminManager adminManager);
    Boolean deleteUser(int userId);
    Boolean deleteUserInfo(int userId);
    Boolean reuseUser(int userId);
    Boolean reuseUserInfo(int userId);
    Page<AdminManager> list(Pageable pageable);
    AdminManager selectMark(String mark);
    List<AdminManager> selectRoleNames();
    Boolean nameIsUnique(String userLoginName);
}
