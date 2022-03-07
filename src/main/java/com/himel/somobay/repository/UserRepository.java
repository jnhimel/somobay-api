package com.himel.somobay.repository;

import com.himel.somobay.model.Role;
import com.himel.somobay.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String number);
    Optional<User> findByEmail(String email);
    Page<User> findAllByRoleRoleId(Integer roleId, Pageable pageable);
}
