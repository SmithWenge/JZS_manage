package com.jzs.function.admin.adminUser.service;

import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.arc.utils.MarkUtils;
import com.jzs.arc.utils.PasswordUtils;
import com.jzs.function.admin.adminUser.AdminManager;
import com.jzs.function.admin.adminUser.repository.AdminUserRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminUserService implements AdminUserServiceI{

    @Autowired
    private LogRepositoryI logRepository;
    @Autowired
    private AdminUserRepositoryI adminUserRepositoryI;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public Boolean add(AdminManager adminManager, String logUsesr) throws BatchRollbackException {

        adminManager.setUserLoginPass(PasswordUtils.encrypt("000000"));
        String mark = MarkUtils.creatMarkNum();
        adminManager.setMark(mark);
        boolean tmp1 = adminUserRepositoryI.addUser(adminManager);
        adminManager.setUserId(adminUserRepositoryI.selectMark(mark).getUserId());
        boolean tmp2 = adminUserRepositoryI.addUserInfo(adminManager);
        if (tmp1 && tmp2) {
            LogContent logContent = new LogContent(logUsesr, "添加用户" + adminManager.getUserName(), 1, 3);
            logRepository.insertLog(logContent);
        } else {
            throw new BatchRollbackException();
        }

        return true;
    }

    @Override
    public AdminManager select(int userId) {
        return adminUserRepositoryI.select(userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public Boolean update(AdminManager adminManager, String LogUser) throws BatchRollbackException {
        boolean tmp1 = adminUserRepositoryI.updateUser(adminManager);
        boolean tmp2 = adminUserRepositoryI.updateUserInfo(adminManager);

        if (tmp1 && tmp2) {
            LogContent logContent = new LogContent(LogUser, "编辑用户" + adminManager.getUserName(), 1, 4);
            logRepository.insertLog(logContent);
        } else {
            throw new BatchRollbackException();
        }

        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public Boolean delete(int userId, String logUser) throws BatchRollbackException {
        boolean tmp1 = adminUserRepositoryI.deleteUser(userId);
        boolean tmp2 = adminUserRepositoryI.deleteUserInfo(userId);

        if (tmp1 && tmp2) {
            LogContent logContent = new LogContent(logUser, "停用用户ID为" + userId, 1, 2);
            logRepository.insertLog(logContent);
        } else {
            throw new BatchRollbackException();
        }

        return true;
    }

    @Override
    public Boolean reuse(int userId, String logUser) throws BatchRollbackException {
        boolean tmp1 = adminUserRepositoryI.reuseUser(userId);
        boolean tmp2 = adminUserRepositoryI.reuseUserInfo(userId);

        if (tmp1 && tmp2) {
            LogContent logContent = new LogContent(logUser, "启用用户ID为" + userId, 1, 4);
            logRepository.insertLog(logContent);
        } else {
            throw new BatchRollbackException();
        }

        return true;
    }

    @Override
    public Page<AdminManager> list(Pageable pageable) {
        return adminUserRepositoryI.list(pageable);
    }

    @Override
    public List<AdminManager> selectRoles() {
        return adminUserRepositoryI.selectRoleNames();
    }

    @Override
    public Boolean nameIsUnique(String userLoginName) {
        return adminUserRepositoryI.nameIsUnique(userLoginName);
    }

    @Override
    public Boolean resetPass(AdminManager adminManager, String LogUser) {
        adminManager.setUserLoginPass(PasswordUtils.encrypt(adminManager.getNewUserLoginPass()));
        boolean tmp = adminUserRepositoryI.resetPass(adminManager);
        if (tmp) {
            LogContent logContent = new LogContent(LogUser, "为" + adminManager.getUserName() + "重置密码", 1, 4);
            logRepository.insertLog(logContent);
        } else {
            return false;
        }

        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BatchRollbackException.class)
    @Override
    public Boolean promote(AdminManager adminManager, String LogUser) throws BatchRollbackException {
        adminManager.setUserLoginPass(PasswordUtils.encrypt("000000"));
        boolean tmp1 = adminUserRepositoryI.updateUser(adminManager);
        boolean tmp2 = adminUserRepositoryI.addUserInfo(adminManager);

        if (tmp1 && tmp2) {
            LogContent logContent = new LogContent(LogUser, "升职工人" + adminManager.getUserName(), 1, 4);
            logRepository.insertLog(logContent);
        } else {
            throw new BatchRollbackException();
        }

        return true;
    }
}
