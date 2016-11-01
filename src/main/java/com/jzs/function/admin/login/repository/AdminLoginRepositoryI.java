package com.jzs.function.admin.login.repository;

import com.jzs.function.admin.login.AdminUser;

public interface AdminLoginRepositoryI {
    AdminUser selectUnique(AdminUser adminUser);
    boolean resetPassword(AdminUser adminUser);
    AdminUser selectNowDiaocheUserName();
}
