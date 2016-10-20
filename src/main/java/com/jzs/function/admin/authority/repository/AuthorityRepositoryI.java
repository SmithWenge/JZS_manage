package com.jzs.function.admin.authority.repository;

import com.jzs.function.admin.authority.Authority;

import java.util.List;

public interface AuthorityRepositoryI {
    List<Authority> list(Authority authority);
    List<Authority> listFunction(Authority authority);
}
