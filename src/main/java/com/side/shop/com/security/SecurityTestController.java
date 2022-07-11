package com.side.shop.com.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

  @PostMapping("/auth/pass")
  public ResponseEntity<?> authPath() {
    return ResponseEntity.ok().body("auth/pass");
  }

  @PostMapping("/noAuth")
  public ResponseEntity<?> noAuth() {
    return ResponseEntity.ok().body("noAuth");
  }
}
