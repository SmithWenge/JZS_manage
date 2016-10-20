package com.jzs.function.admin.role.service;

import com.jzs.arc.exception.BatchRollbackException;
import com.jzs.arc.utils.ChecksToList;
import com.jzs.arc.utils.MarkUtils;
import com.jzs.function.admin.role.Role;
import com.jzs.function.admin.role.repository.RoleRepositoryI;
import com.jzs.function.support.log.LogContent;
import com.jzs.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class RoleService implements RoleServiceI {

    @Autowired
    private LogRepositoryI logRepository;
    @Autowired
    private RoleRepositoryI roleRepository;

    @Transactional
    @Override
    public Boolean add(Role role, String logUsesr, List<String> list) throws BatchRollbackException {
        String roleId = UUID.randomUUID().toString();
        role.setRoleId(roleId);
        Boolean tmp = roleRepository.addRole(role);

        for (String authorityTwoAndFunction : list) {
            String[] checks = ChecksToList.getString(authorityTwoAndFunction);
            String[] authorityTwos = ChecksToList.getStringTwo(checks[0]);
            String authorityroleId = UUID.randomUUID().toString();
            Object[] args1 = new Object[3];
            args1[0] = authorityroleId;
            args1[1] = Integer.parseInt(authorityTwos[1]);
            args1[2] = roleId;
            roleRepository.addAuthorityRole(args1);
            for (String check : checks) {
                String[] functions = ChecksToList.getStringTwo(check);
                Object[] args2 = new Object[3];
                args2[0] = authorityroleId;
                args2[1] = Integer.parseInt(functions[0]);
                args2[2] = roleId;
                roleRepository.addAuthorityRoleFunction(args2);
            }
        }
        //关联了事务，只判断其中一个就可以了。
        if (tmp) {
            LogContent logContent = new LogContent(logUsesr, "添加角色" + role.getRoleName(), 1, 3);
            logRepository.insertLog(logContent);
        } else {
            throw new BatchRollbackException();
        }

        return true;
    }

    @Override
    public Role selectRole(String roleId) {
        return roleRepository.selectRole(roleId);
    }

    @Override
    public List<Role> selectRoleFunction(Role role) {
        return roleRepository.selectRoleFunction(role);
    }

    @Override
    public Boolean delete(String roleId, String logUser) {
        if (roleRepository.delete(roleId)) {
            LogContent logContent = new LogContent(logUser, "删除用户" + "删除用户ID为" + roleId, 1, 2);
            logRepository.insertLog(logContent);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Role> list(Pageable pageable) {
        return roleRepository.list(pageable);
    }

    @Override
    public List<Role> selectFunctions(int authorityTwoId) {
        return roleRepository.selectFunctions(authorityTwoId);
    }

    @Override
    public Boolean authorityTwoIsExit(Role role) {
        return roleRepository.authorityTwoIsExit(role);
    }

    @Transactional
    @Override
    public Boolean edit(Role role, String logUsesr, List<String> list) throws BatchRollbackException {

        Boolean tmp = roleRepository.updateRole(role);
        List<Role> listFunctionId = roleRepository.selectRoleFunctionId(role);
        List<Role> listAuthorityTwoId = roleRepository.selectRoleAuthorityTwoId(role);

        for (Role id : listFunctionId) {
            role.setFunctionId(id.getFunctionId());
            roleRepository.deleteRole(role);
        }

        for (Role id : listAuthorityTwoId) {
            role.setAuthorityTwoId(id.getAuthorityTwoId());
            roleRepository.deleteAuthorityTwo(role);
        }

        for (String authorityTwoAndFunction : list) {
            String[] checks = ChecksToList.getString(authorityTwoAndFunction);
            String[] authorityTwos = ChecksToList.getStringTwo(checks[0]);
            String authorityroleId = UUID.randomUUID().toString();
            Object[] args1 = new Object[3];
            args1[0] = authorityroleId;
            args1[1] = Integer.parseInt(authorityTwos[1]);
            args1[2] = role.getRoleId();
            roleRepository.addAuthorityRole(args1);
            for (String check : checks) {
                String[] functions = ChecksToList.getStringTwo(check);
                Object[] args2 = new Object[3];
                args2[0] = authorityroleId;
                args2[1] = Integer.parseInt(functions[0]);
                args2[2] = role.getRoleId();
                roleRepository.addAuthorityRoleFunction(args2);
            }
        }
        //关联了事务，只判断其中一个就可以了。
        if (tmp) {
            LogContent logContent = new LogContent(logUsesr, "编辑角色" + role.getRoleName(), 1, 4);
            logRepository.insertLog(logContent);
        } else {
            throw new BatchRollbackException();
        }

        return true;
    }
}
