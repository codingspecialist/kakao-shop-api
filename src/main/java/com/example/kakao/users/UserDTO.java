package com.example.kakao.users;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @Getter
    @Setter
    public static class Request {
        private int memberId;
        private String email;
        private String password;
        private String userName;

        public User toEntity() {
            return new User.Builder(memberId, userName, email,
                    password,
                    Collections.singletonList("ROLE_USER"))
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private int memberId;
        private String email;
        private String password;
        private String useName;

        public Response(User user) {
            this.memberId = user.getMemberId();
            this.email = user.getUserId();
            this.useName = user.getName();
            this.password = user.getPassword();
        }
    }
}
