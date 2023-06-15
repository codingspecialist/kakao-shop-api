package com.example.kakao.integration;

import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.security.JwtTokenProvider;
import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.cart.CartRequest;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CartRestControllerTest extends MyRestDoc {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
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
    // (기능8) 장바구니 담기
    public void addCartList_test() throws Exception {

        // given
        List<CartRequest.SaveDTO> requestDTOList = new ArrayList<>();
        CartRequest.SaveDTO item1 = new CartRequest.SaveDTO();
        item1.setOptionId(1);
        item1.setQuantity(5);
        CartRequest.SaveDTO item2 = new CartRequest.SaveDTO();
        item2.setOptionId(2);
        item2.setQuantity(5);
        requestDTOList.add(item1);
        requestDTOList.add(item2);

        String requestBody = om.writeValueAsString(requestDTOList);

        // when
        ResultActions resultActions = mvc.perform(
                post("/carts/add")
                        .header("Authorization", token)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    // (기능9) 장바구니 보기 - (주문화면, 결재화면)
    public void findAll_test() throws Exception {

        // when
        ResultActions resultActions = mvc.perform(
                get("/carts")
                        .header("Authorization", token)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andExpect(jsonPath("$.response.products[0].id").value(1));
        resultActions.andExpect(jsonPath("$.response.products[0].productName").value("기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전"));
        resultActions.andExpect(jsonPath("$.response.products[0].carts[0].id").value(1));
        resultActions.andExpect(jsonPath("$.response.products[0].carts[0].option.id").value(5));
        resultActions.andExpect(jsonPath("$.response.products[0].carts[0].option.optionName").value("2겹 식빵수세미 6매"));
        resultActions.andExpect(jsonPath("$.response.products[0].carts[0].option.price").value(8900));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    // (기능11) 주문하기 - (장바구니 업데이트)
    public void update_test() throws Exception {

        // given
        List<CartRequest.UpdateDTO> requestDTOList = new ArrayList<>();
        CartRequest.UpdateDTO item1 = new CartRequest.UpdateDTO();
        item1.setCartId(1);
        item1.setQuantity(3);
        CartRequest.UpdateDTO item2 = new CartRequest.UpdateDTO();
        item2.setCartId(2);
        item2.setQuantity(5);
        requestDTOList.add(item1);
        requestDTOList.add(item2);

        String requestBody = om.writeValueAsString(requestDTOList);

        // when
        ResultActions resultActions = mvc.perform(
                post("/carts/update")
                        .header("Authorization", token)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        );

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // verify
        resultActions.andExpect(jsonPath("$.success").value("true"));
        resultActions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

}
