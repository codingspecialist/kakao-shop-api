package com.example.kakao.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserJPARepository userJPARepository;

    private int memberId = 1;

    public User save(UserDTO.Request userDTO) {
        userDTO.setMemberId(memberId++);
        User newUser = userDTO.toEntity();
        System.out.println("pwd : " + newUser.getPwd());
        newUser.setPassword(passwordEncoder.encode(newUser.getPwd()));
        System.out.println("Role : " + newUser.getRoles());
        return userJPARepository.save(newUser);
    }

    public User login(Map<String, String> user) {
        User member = userJPARepository.findByEmail(user.get("userId")).orElse(null);

        if (member != null && passwordEncoder.matches(user.get("password"), member.getPassword()))
            return member;

        return null;
    }

    public User findById(String userId) {
        System.out.println("findById:" + userId);

        return userJPARepository.findByEmail(userId).orElse(null);
    }
}
