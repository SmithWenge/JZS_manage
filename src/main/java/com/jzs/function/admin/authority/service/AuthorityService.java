package com.jzs.function.admin.authority.service;

import com.jzs.function.admin.authority.Authority;
import com.jzs.function.admin.authority.repository.AuthorityRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorityService implements AuthorityServiceI{

    @Autowired
    private AuthorityRepositoryI authorityRepository;
    @Override
    public List<Authority> list(Authority authority) {
        List<Authority> list =  authorityRepository.list(authority);
        int[] ids = new int[list.size()];
        for (int i = 0;i < list.size();i++) {
            ids[i] = list.get(i).getAuthorityTwoId();
        }
        List<Authority> listTwo = new ArrayList<>();
        Arrays.sort(ids);
        for (int i = 0;i < ids.length;i++) {
            Authority authorityTwo = new Authority();
            authorityTwo.setAuthorityTwoId(ids[i]);
            listTwo.add(authorityTwo);
        }

        return listTwo;
    }

    @Override
    public List<Authority> listFunction(Authority authority) {
        return authorityRepository.listFunction(authority);
    }
}
