package com.himel.somobay.service;

import com.himel.somobay.model.Role;
import com.himel.somobay.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Integer id) {
        Role role = roleRepository.getById(id);
        return role;
    }
}
