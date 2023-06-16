package com.example.kakao.integration;

import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.security.JwtTokenProvider;
import com.example.kakao.user.UserJPARepository;
import com.example.kakao.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ProductRestControllerTest extends MyRestDoc {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private AuthenticationManager authenticationManager;

    private String token;

    @BeforeEach
    public void setUp() {
        // token 발행
        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO();
        loginDTO.setEmail("ssar@nate.com");
        loginDTO.setPassword("meta1234!");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        CustomUserDetails myUserDetails = (CustomUserDetails) authentication.getPrincipal();
        token = JwtTokenProvider.create(myUserDetails.getUser());
    }

    @Test
    // (기능4) 전체 상품 목록 조회
    public void findAll_test() throws Exception {

        // when
        ResultActions resultActions = mvc.perform(
                get("/products")
        );

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response[0].id").value(1));
        resultActions.andExpect(jsonPath("$.response[0].productName").value("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전"));
        resultActions.andExpect(jsonPath("$.response[0].description").value(""));
        resultActions.andExpect(jsonPath("$.response[0].image").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.response[0].price").value(1000));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    // (기능5) 개별 상품 상세 조회
    public void findById_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                get("/products/" + id)
        );

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(1));
        resultActions.andExpect(jsonPath("$.response.productName").value("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전"));
        resultActions.andExpect(jsonPath("$.response.description").value(""));
        resultActions.andExpect(jsonPath("$.response.image").value("/images/1.jpg"));
        resultActions.andExpect(jsonPath("$.response.price").value(1000));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}
