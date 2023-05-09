package com.example.kakao.users;

import com.example.kakao.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/join")
    public User join(@RequestBody UserDTO.Request userDTO) {
        return userService.save(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userService.login(user);

        if(member != null) {
            System.out.println(member.getUsername() + "님 로그인 (" + member.getRole() + ")");
            return jwtTokenProvider.createToken(member.getUsername(), member.getRole());
        } else {
            return "fail";
        }
    }

    @PostMapping("/check")
    public String check(@RequestBody Map<String, String> user) {
        User member = userService.findById(user.get("email"));

        if(member == null) {
            return "available";
        } else {
            return "unavailable";
        }
    }
}
