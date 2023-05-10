package com.example.kakao.users;

import lombok.Getter;
import lombok.Setter;


public class UserResponse {


    @Getter @Setter
    public static class JoinOutDTO {
        private int userId;
        private String email;
        private String username;

        public JoinOutDTO(User user) {
            this.userId = user.getUserId();
            this.email = user.getEmail();
            this.username = user.getUsername();
        }
    }
}
