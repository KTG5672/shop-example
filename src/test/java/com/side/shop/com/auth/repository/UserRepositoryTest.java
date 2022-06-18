package com.side.shop.com.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.side.shop.com.auth.constants.AuthorityCode;
import com.side.shop.com.auth.domain.User;
import com.side.shop.com.auth.domain.UserAuthority;
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

  @Test
  public void MEMBER_권한을_가진_유저_생성() throws Exception {
    // given
    user.authorizeUser(AuthorityCode.MEMBER);
    userRepository.save(user);
    // when
    User findUser = userRepository.find(this.user.getId());
    // then
    assertThat(user.getUserAuthorities())
        .contains(UserAuthority.createUserAuthority(user, AuthorityCode.MEMBER));
  }

  @Test
  public void 여러_권한을_가진_유저_생성() throws Exception {
    // given
    user.authorizeUser(AuthorityCode.MEMBER, AuthorityCode.ADMIN);
    userRepository.save(user);
    // when
    User findUser = userRepository.find(this.user.getId());
    // then
    assertThat(user.getUserAuthorities())
        .contains(UserAuthority.createUserAuthority(user, AuthorityCode.MEMBER))
        .contains(UserAuthority.createUserAuthority(user, AuthorityCode.ADMIN));
  }

  @Test
  public void 한_권한을_여러개_넣었을때() throws Exception {
    // given
    user.authorizeUser(AuthorityCode.MEMBER, AuthorityCode.MEMBER);
    // when
    userRepository.save(user);
    // then
  }
}