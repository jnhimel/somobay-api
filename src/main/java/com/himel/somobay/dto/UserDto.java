package com.himel.somobay.dto;

import com.himel.somobay.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Integer age;
    private LocalDateTime createDate;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.userId = user.getUserId();
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.email = user.getEmail();
        userDto.phoneNumber = user.getPhoneNumber();
        userDto.age = user.getAge();
        userDto.dateOfBirth = user.getDateOfBirth();
        userDto.createDate = user.getCreateDate();
        return userDto;
    }
}
