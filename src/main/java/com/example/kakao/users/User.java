package com.example.kakao.users;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="user_tb")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email; // 인증시 필요한 필드
    private String password;
    private String username;

    @Convert(converter = StringArrayConverter.class)
    private List<String> roles = new ArrayList<>(); // role은 한 개 이상

    @Builder
    public User(int userId, String email, String password, String username, List<String> roles) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }
}
