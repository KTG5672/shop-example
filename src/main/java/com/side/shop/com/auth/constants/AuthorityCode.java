package com.side.shop.com.auth.constants;

public enum AuthorityCode {
  ADMIN, MEMBER;

  public static AuthorityCode getAuthorityCodeByString(final String authorityCode) {
    return AuthorityCode.valueOf(authorityCode);
  }

}
