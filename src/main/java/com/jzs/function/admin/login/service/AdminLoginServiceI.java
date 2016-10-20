package com.jzs.function.admin.login.service;

import com.jzs.function.admin.login.AdminUser;

public interface AdminLoginServiceI {
    AdminUser login(AdminUser adminUser);
    AdminUser resetPassword(AdminUser adminUser,String logUser);
}
