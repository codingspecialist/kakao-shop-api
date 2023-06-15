package com.example.kakao.integration;

import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.security.JwtTokenProvider;
import com.example.kakao.order.OrderResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OrderRestControllerTest extends MyRestDoc {
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
    // (기능13) 주문 결과 확인
    public void findAll_test() throws Exception {

        // given
        int id = 1;

        // when
        ResultActions resultActions = mvc.perform(
                get("/orders/" + id)
                        .header("Authorization", token)
        );

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(1));
        resultActions.andExpect(jsonPath("$.response.products[0].productName").value("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전"));
        resultActions.andExpect(jsonPath("$.response.products[0].items[0].id").value(1));
        resultActions.andExpect(jsonPath("$.response.products[0].items[0].optionName").value("01. 슬라이딩 지퍼백 크리스마스에디션 4종"));
        resultActions.andExpect(jsonPath("$.response.products[0].items[0].quantity").value(5));
        resultActions.andExpect(jsonPath("$.response.products[0].items[0].price").value(50000));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    // (기능13) 주문하기 - 결재하기 버튼 클릭시 (결재시 해당 유저의 장바구니 비워짐)
    public void saveOrder_test() throws Exception {

        // when
        ResultActions resultActions = mvc.perform(
                post("/orders/save")
                        .header("Authorization", token)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.id").value(2));
        resultActions.andExpect(jsonPath("$.response.products[0].productName").value("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전"));
        resultActions.andExpect(jsonPath("$.response.products[0].items[0].id").value(4));
        resultActions.andExpect(jsonPath("$.response.products[0].items[0].optionName").value("2겹 식빵수세미 6매"));
        resultActions.andExpect(jsonPath("$.response.products[0].items[0].quantity").value(3));
        resultActions.andExpect(jsonPath("$.response.products[0].items[0].price").value(26700));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

}
