package com.side.shop.com.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class SecurityTestFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String token = getTokenFromHeader(request);
    if (token != null) {
      AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            "TEST_USER_ID"
          ,null
          ,null
      );
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //기본적으로 제공한 details 세팅
      SecurityContextHolder.getContext().setAuthentication(authentication); //세션에서 계속 사용하기 위해 securityContext에 Authentication 등록
    }
    chain.doFilter(request, response);
  }

  private String getTokenFromHeader(final HttpServletRequest request) {
    return request.getHeader("Authorization");
  }
}
