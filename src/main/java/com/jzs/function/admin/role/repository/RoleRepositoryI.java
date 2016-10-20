package com.jzs.function.admin.role.repository;

import com.jzs.function.admin.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleRepositoryI {

    Boolean addRole(Role role);
    int addAuthorityRole(Object[] args);
    int addAuthorityRoleFunction(Object[] args);
    Boolean delete(String roleId);
    Page<Role> list(Pageable pageable);
    List<Role> selectFunctions(int authorityTwoId);
    Role selectRole(String roleId);
    List<Role> selectRoleFunction(Role role);
    List<Role> selectRoleFunctionId(Role role);
    List<Role> selectRoleAuthorityTwoId(Role role);
    Boolean deleteRole(Role role);
    Boolean deleteAuthorityTwo(Role role);
    Boolean updateRole(Role role);
    Boolean authorityTwoIsExit(Role role);
}
