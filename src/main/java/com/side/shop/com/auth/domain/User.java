package com.side.shop.com.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "com_user")
@NoArgsConstructor
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_user_id")
    private Long id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_number")
    private String phoneNumber;

    public static User createUser(String userId, String email, String password
            , String userName, String phoneNumber) {

        User user = new User();
        user.userId = userId;
        user.email = email;
        user.password = password;
        user.userName = userName;
        user.phoneNumber = phoneNumber;

        return user;
    }

}
