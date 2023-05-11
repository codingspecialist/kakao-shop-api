package com.example.kakao.users;

import lombok.Getter;
import lombok.Setter;


public class UserResponse {

    @Getter @Setter
    public static class JoinDTO {
        private int id;
        private String email;
        private String username;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
        }
    }
}
