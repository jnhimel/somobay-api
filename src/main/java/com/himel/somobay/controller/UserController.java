package com.himel.somobay.controller;

import com.himel.somobay.dto.UserDto;
import com.himel.somobay.dto.UserPage;
import com.himel.somobay.model.User;
import com.himel.somobay.service.UserService;
import org.apache.catalina.realm.GenericPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping(value = "signup",consumes="application/json")
    public ResponseEntity<?> signup(@RequestBody User user, HttpServletRequest request) {
        return userService.signup(user,request);
    }

    @GetMapping(value = "login")
    public ResponseEntity<Principal> login(Principal userPrincipal, @RequestParam("phoneNumber") String phoneNumber, HttpServletRequest request ) {
//        System.out.println("name: " + userPrincipal.getName());
        return ResponseEntity.ok(userService.login(userPrincipal,phoneNumber,request));
    }

    @PostMapping(value = "updateUser",consumes="application/json")
    public ResponseEntity<?> updateUser(@RequestParam Long userId, @RequestBody UserDto userDto, HttpServletRequest request) {
        return userService.updateUser(userId,userDto,request);
    }

    @PostMapping("logoutUser")
    @ResponseBody
    public Principal logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return request.getUserPrincipal();
    }

    @GetMapping("getUserByPhoneNumber")
    public ResponseEntity<UserDto> getUserByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber){
        return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
    }

    @GetMapping("getUserByUserId")
    public ResponseEntity<UserDto> getUserByUserId(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @GetMapping("getUsers")
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(value = "sortBy", required = false, defaultValue = "userId") String sortBy
    ){
        UserPage userPage = new UserPage();
        if(pageNumber>0){
            userPage.setPageNumber(pageNumber);
        }
        if(pageSize>10 || pageSize <10){
            userPage.setPageSize(pageSize);
        }
        if (sortDirection!=null){
            if(sortDirection.toLowerCase().equals("asc")){
                userPage.setSortDirection(Sort.Direction.ASC);
            }
            else if(sortDirection.toLowerCase().equals("desc")){
                userPage.setSortDirection(Sort.Direction.DESC);
            }
        }
        if(sortBy!="firstName" || !sortBy.isEmpty() || sortBy!=null){
            System.out.println(sortBy);
            userPage.setSortBy(sortBy);
        }
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.ok(userService.getUsers(userPage));
    }

}
