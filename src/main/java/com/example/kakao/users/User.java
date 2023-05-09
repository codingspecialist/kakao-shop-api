package com.example.kakao.users;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="member_tb")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
    private String email;
    private String password;
    private String userName;

    @Convert(converter = StringArrayConverter.class)
    private List<String> roles = new ArrayList<>();

    User(String name, String email, String password, List<String> roles) {
        Assert.hasText(name, "name must be provided");
        Assert.hasText(email, "id must be provided");
        Assert.hasText(password,"pwd must not be null");
        Assert.notNull(roles,"role must not be null");

        this.userName=name;
        this.email=email;
        this.password=password;
        this.roles=roles;
    }

    public String getName(){
        return userName;
    }

    public String getUserId() {
        return email;
    }

    public String getPwd() {
        return password;
    }

    /**
     * @param password
     * 원래 setter는 만들면 안되는데, 테스트용으로 만듦
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRole() {
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public static class Builder {
        private int memberId;
        private String userName;
        private String email;
        private String password;

        @Convert(converter = StringArrayConverter.class)
        private List<String> roles;

        public Builder(int memberId, String name, String email, String password, List<String> roles) {
            this.memberId=memberId;
            this.userName=name;
            this.email=email;
            this.password=password;
            this.roles=roles;
        }

        public User build() {
            return new User(memberId, email,password,userName,roles);
        }
    }
}
