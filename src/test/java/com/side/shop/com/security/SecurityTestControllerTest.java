package com.side.shop.com.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SecurityTestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void auth로_시작하는_Path는_인증_X() throws Exception {
    mockMvc.perform(post("/auth/pass"))
        .andExpect(status().isOk())
        .andExpect(content().string("auth/pass"))
        .andDo(print());
  }

  @Test
  public void auth로_시작하지않는_Path는_인증_O() throws Exception {
    mockMvc.perform(post("/noAuth"))
        .andExpect(status().isForbidden())
        .andDo(print());
  }

  @Test
  public void request_header에_Authorization_항목속성이_있으면_인증O() throws Exception {
    mockMvc.perform(post("/noAuth")
            .header("Authorization", "TEST_ID")
        )
        .andExpect(status().isOk())
        .andExpect(content().string("noAuth"))
        .andDo(print());
  }
}