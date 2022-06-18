package com.side.shop.com.auth.domain;

import com.side.shop.com.auth.constants.AuthorityCode;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "com_user")
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserAuthority> userAuthorities = new HashSet<>();

    protected User() {}

    private User(String userId, String email, String password
        , String userName, String phoneNumber) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public static User createUser(String userId, String email, String password
            , String userName, String phoneNumber) {
        return new User(userId, email, password, userName, phoneNumber);
    }

    public void authorizeUser(AuthorityCode... authorityCodes) {
        for (AuthorityCode authorityCode : authorityCodes) {
            userAuthorities.add(UserAuthority.createUserAuthority(this, authorityCode));
        }
    }
}
