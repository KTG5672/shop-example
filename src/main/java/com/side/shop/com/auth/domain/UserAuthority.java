package com.side.shop.com.auth.domain;

import com.side.shop.com.auth.constants.AuthorityCode;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "com_user_auth")
@NoArgsConstructor
@Getter
public class UserAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "com_user_id")
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(name = "authority_code", nullable = false, length = 10)
  private AuthorityCode authorityCode;

  private UserAuthority(final User user, final AuthorityCode authorityCode) {
    this.user = user;
    this.authorityCode = authorityCode;
  }

  public static UserAuthority createUserAuthority(final User user,
      final AuthorityCode authorityCode) {
    return new UserAuthority(user, authorityCode);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAuthority that = (UserAuthority) o;
    return Objects.equals(getUser(), that.getUser())
        && getAuthorityCode() == that.getAuthorityCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUser(), getAuthorityCode());
  }
}
