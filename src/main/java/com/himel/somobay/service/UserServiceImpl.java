package com.himel.somobay.service;

import com.himel.somobay.dto.UserDto;
import com.himel.somobay.dto.UserPage;
import com.himel.somobay.exceptions.EmailAlreadyUsedException;
import com.himel.somobay.exceptions.PhoneNumberAlreadyUsedException;
import com.himel.somobay.exceptions.UserNotFoundException;
import com.himel.somobay.model.Role;
import com.himel.somobay.model.User;
import com.himel.somobay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> signup(User user, HttpServletRequest request) {
        try {
            if(isNotUniqueEmail(user.getEmail())){
                throw new EmailAlreadyUsedException("Email already used!");
            }
            else if(isNotUniquePhoneNumber(user.getPhoneNumber())){
                throw new PhoneNumberAlreadyUsedException("Phone number already used!");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreateDate(LocalDateTime.now());
            Role role = roleService.getRoleById(1);
            user.setRole(role);
            role.getUsers().add(user);
            userRepository.save(user);
            return ResponseEntity.ok().body(getUserByPhoneNumber(user.getPhoneNumber()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @Override
    public ResponseEntity<?> updateUser(Long userId, UserDto userDto, HttpServletRequest request) {
        try {
            User currentUser = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
            if(isNotUniqueEmail(userDto.getEmail())){
                throw new EmailAlreadyUsedException("Email already used!");
            }
            else if(isNotUniquePhoneNumber(userDto.getPhoneNumber())){
                throw new PhoneNumberAlreadyUsedException("Phone number already used!");
            }
            currentUser.setFirstName(userDto.getFirstName());
            currentUser.setLastName(userDto.getLastName());
            currentUser.setPhoneNumber(userDto.getPhoneNumber());
            currentUser.setEmail(userDto.getEmail());
            userRepository.save(currentUser);
            return ResponseEntity.ok().body(getUserByPhoneNumber(userDto.getPhoneNumber()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @Override
    public Principal login(Principal userPrincipal, String number, HttpServletRequest request) {
        User user = findUserByPhoneNumber(number);
        user.setLastLoginDateTime(user.getLoginDateTime());
        user.setLoginDateTime(LocalDateTime.now());
        user.setLastLoginIP(user.getLoginIP());
        user.setLoginIP(request.getRemoteAddr());
        userRepository.save(user);
        return userPrincipal;
    }



    @Override
    public boolean isNotUniqueEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    @Override
    public boolean isNotUniquePhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber){
        User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    @Override
    public UserDto getUserByUserId(Long userId) {
        return convertToDTO(getUser(userId));
    }

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber){
        return convertToDTO(findUserByPhoneNumber(phoneNumber));

    }

    @Override
    public Page<UserDto> getUsers(UserPage userPage) {
        Sort sort = Sort.by(userPage.getSortDirection(),userPage.getSortBy());
        Pageable pageable = PageRequest.of(userPage.getPageNumber(),userPage.getPageSize(), sort);
        return userRepository.findAllByRoleRoleId(1,pageable).map(this::convertToDTO);
    }

    private UserDto convertToDTO(User user){
        return UserDto.fromUser(user);
    }
}
