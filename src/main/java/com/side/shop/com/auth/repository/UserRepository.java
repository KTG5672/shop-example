package com.side.shop.com.auth.repository;

import com.side.shop.com.auth.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User save(final User user) {
        em.persist(user);
        return user;
    }

    public User find(final Long userId) {
        return em.find(User.class, userId);
    }

}
