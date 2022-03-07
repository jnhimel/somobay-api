package com.himel.somobay.service;

import com.himel.somobay.dto.UserDto;
import com.himel.somobay.dto.UserPage;
import com.himel.somobay.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


public interface UserService {
    User findUserByPhoneNumber(String phoneNumber);
    User getUser(Long userId);
    UserDto getUserByUserId(Long userId);
    ResponseEntity<?> signup(User user, HttpServletRequest request);
    ResponseEntity<?> updateUser(Long userid, UserDto userDto, HttpServletRequest request);
    Principal login(Principal userPrincipal, String number, HttpServletRequest request);
    UserDto getUserByPhoneNumber(String phoneNumber);
    Page<UserDto> getUsers(UserPage userPage);
    boolean isNotUniqueEmail(String email);
    boolean isNotUniquePhoneNumber(String phoneNumber);
}
