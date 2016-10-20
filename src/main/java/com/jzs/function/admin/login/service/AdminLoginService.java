package com.jzs.function.admin.login.service;

import com.google.common.base.Optional;
import com.jzs.arc.utils.PasswordUtils;
import com.jzs.function.admin.login.AdminUser;
import com.jzs.function.admin.login.repository.AdminLoginRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminLoginService implements AdminLoginServiceI {
    @Autowired
    private AdminLoginRepositoryI adminLoginRepository;
    @Autowired
    private LogRepositoryI logRepository;

    public AdminUser login(AdminUser adminUser) {
        adminUser.setUserLoginPass(PasswordUtils.encrypt(adminUser.getUserLoginPass()));
        AdminUser loginUser = adminLoginRepository.selectUnique(adminUser);
        Optional<AdminUser> optional = Optional.fromNullable(loginUser);

        if (optional.isPresent()) {
            LogContent logContent = new LogContent(loginUser.getUserName(), "登陆系统", 1, 5);
            logRepository.insertLog(logContent);
        }

        return loginUser;
    }

    @Override
    public AdminUser resetPassword(AdminUser adminUser,String logUser) {
        adminUser.setUserLoginPass(PasswordUtils.encrypt(adminUser.getUserLoginPass()));
        AdminUser canLogin = adminLoginRepository.selectUnique(adminUser);

        Optional<AdminUser> optional = Optional.fromNullable(canLogin);

        if (optional.isPresent()) {
            adminUser.setAdminLoginPassNew(PasswordUtils.encrypt(adminUser.getAdminLoginPassNew()));

            if (adminLoginRepository.resetPassword(adminUser)) {
                LogContent logContent = new LogContent(logUser, "更改密码", 1, 4);
                logRepository.insertLog(logContent);
                adminUser.setUserLoginPass(adminUser.getAdminLoginPassNew());

                return adminLoginRepository.selectUnique(adminUser);
            }
        }

        return null;
    }
}
