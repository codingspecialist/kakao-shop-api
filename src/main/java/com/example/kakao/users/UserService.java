package com.example.kakao.users;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception401;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserJPARepository userJPARepository;

<<<<<<< HEAD
    @Autowired
    private UserJPARepository userJPARepository;

    private int memberId = 1;

    synchronized public User save(UserDTO.Request userDTO) {
        userDTO.setMemberId(memberId++);
        User newUser = userDTO.toEntity();
        System.out.println("pwd : " + newUser.getPwd());
        newUser.setPassword(passwordEncoder.encode(newUser.getPwd()));
        System.out.println("Role : " + newUser.getRoles());
        return userJPARepository.save(newUser);
=======
    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO requestDTO) {
        requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        try {
            User userPS = userJPARepository.save(requestDTO.toEntity());
            return new UserResponse.JoinDTO(userPS);
        }catch (Exception e){
            throw new Exception500(e.getMessage());
        }
>>>>>>> f6ee6a8e827ebfddedaf7bb2a27a5730e98a3576
    }

    public String login(UserRequest.LoginDTO requestDTO) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            CustomUserDetails myUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return JwtTokenProvider.create(myUserDetails.getUser());
        }catch (Exception e){
            throw new Exception401("인증되지 않았습니다");
        }
    }

    public void sameCheckEmail(String email) {
        Optional<User> userOP = userJPARepository.findByEmail(email);
        if(userOP.isPresent()){
            throw new Exception400("동일한 이메일이 존재합니다 : "+email);
        }
    }
}
