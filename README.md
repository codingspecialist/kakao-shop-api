# KAKAO 쇼핑

## API 요청

### 1. 회원가입
- method : post
- url : http://localhost:8080/join
- request body : 
```json
{
    "email":"ssar@nate.com",
    "password":"1234",
    "userName":"ssar"
}
```
- response body : 
```json
{
    "memberId": 3,
    "email": "ssar@nate.com",
    "password": "{bcrypt}$2a$10$7x7gfHymZvlvwlSHgEeWX.OQw5jDtuUQU7DfHYbz4StGefFeQYn9q",
    "roles": [
        "ROLE_USER"
    ],
    "name": "ssar",
    "enabled": true,
    "role": [
        "ROLE_USER"
    ],
    "username": "ssar@nate.com",
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "pwd": "{bcrypt}$2a$10$7x7gfHymZvlvwlSHgEeWX.OQw5jDtuUQU7DfHYbz4StGefFeQYn9q",
    "accountNonLocked": true,
    "userId": "ssar@nate.com",
    "credentialsNonExpired": true,
    "accountNonExpired": true
}
```

### 2. 로그인
- method : post
- url : http://localhost:8080/login
- request body :
```json
{
    "userId":"ssar@nate.com",
    "password":"1234"
}
```
- response body :
```text
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZXMiOlsissUk9MRV9VU0VSIl0sImlhdCI6MTY4MzYwMzg3MSwiZXhwIjoxNjgzNjA1NjcxfQ.TFuk17EJQtoMs1sjAfNZ1fgJdVrHjH3NXxv_F5nqSsw
```

```text
토큰에 담긴 페이로드
email, roles
```

### 3. 동일 이메일 체크
- method : post
- url : http://localhost:8080/check
- request body :
```json
{
    "email":"cos@nate.com"
}
```
- response body :
```text
available
```

