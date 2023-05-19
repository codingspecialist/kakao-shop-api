package com.example.kakao.integration;

import com.example.kakao.user.User;
import com.example.kakao.user.UserJPARepository;
import com.example.kakao.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserRestControllerTest extends MyRestDoc{
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserJPARepository userRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
    }

    @Test
    // (기능1) 회원가입
    public void join_test() throws Exception {
        // given
        UserRequest.JoinDTO joinDTO = new UserRequest.JoinDTO();
        joinDTO.setEmail("cos@nate.com");
        joinDTO.setPassword("cos1234!");
        joinDTO.setUsername("cos");

        String requestBody = om.writeValueAsString(joinDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
    }

    // (기능2) 로그인
    @Test
    public void login() throws Exception {
        // given
        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO();
        loginDTO.setEmail("alss@nate.com");
        loginDTO.setPassword("alss1234!");

        String requestBody = om.writeValueAsString(loginDTO);

        // when
        ResultActions resultActions = mvc.perform(
                post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE));


        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
    }
}
