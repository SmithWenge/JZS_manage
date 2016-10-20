package com.jzs.function.admin.authority.service;

import com.jzs.function.admin.authority.Authority;

import java.util.List;

public interface AuthorityServiceI {
    List<Authority> list(Authority authority);
    List<Authority> listFunction(Authority authority);
}
