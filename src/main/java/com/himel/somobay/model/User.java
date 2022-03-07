package com.himel.somobay.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDate dateOfBirth;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    private LocalDateTime createDate;
    private LocalDateTime loginDateTime;
    private String loginIP;
    private LocalDateTime lastLoginDateTime;
    private String lastLoginIP;
    private LocalDateTime lastFailedLoginDateTime;
    private String lastFailedLoginIP;
    private Integer failedLoginAttempts;
    @Column(columnDefinition = "boolean default false")
    private boolean agreedToTermOfUse;
    @Column(columnDefinition = "boolean default false")
    private boolean emailVerified;
    @Column(columnDefinition = "boolean default false")
    private boolean userVerified;
    @Column(columnDefinition = "boolean default false")
    private boolean archived;
    @Transient
    private Integer age;
    @OneToMany(mappedBy = "user")
    private List<Deposit> deposits;
    @OneToMany(mappedBy = "user")
    private List<Loan> loans;
    public Integer getAge() {
        return Period.between(this.dateOfBirth,LocalDate.now()).getYears();
    }
}
