package com.example.kakao.users;

import com.example.kakao._core.security.JwtTokenProvider;
import com.example.kakao._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinInDTO joinInDTO) {
        UserResponse.JoinOutDTO joinOutDTO = userService.join(joinInDTO);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(joinOutDTO);
        return ResponseEntity.ok(apiResult);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginInDTO loginInDTO) {
        String jwt = userService.login(loginInDTO);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok().header(JwtTokenProvider.HEADER, jwt).body(apiResult);
    }

    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestBody Map<String, String> user) {
        userService.sameCheckEmail(user.get("email"));
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
    }
}
