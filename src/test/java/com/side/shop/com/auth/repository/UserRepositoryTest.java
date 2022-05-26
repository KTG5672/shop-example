package com.side.shop.com.auth.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.side.shop.com.auth.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {
  @Autowired UserRepository userRepository;

  User user;

  @BeforeEach
  void init() {
    user = User.createUser(
        "userId1"
        , "zzang5992@gmail.com"
        , "1234"
        , "김태경"
        , "010-0000-0000"
    );
  }

  @Test
  public void 유저_생성() throws Exception {
    // given
    // when
    User saveUser = userRepository.save(user);
    // then
    assertThat(user).isEqualTo(saveUser);
  }

  @Test
  public void 유저_찾기() throws Exception {
    // given
    userRepository.save(user);
    // when
    User findUser = userRepository.find(user.getId());
    assertThat(user).isEqualTo(findUser);
    // then
  }
}