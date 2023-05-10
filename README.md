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
  "username":"ssar"
}
```
- response body : 
```json
{
  "success": true,
  "response": {
    "userId": 3,
    "email": "ssar@nate.com",
    "username": "ssar"
  },
  "error": null
}
```

### 2. 로그인
- method : post
- url : http://localhost:8080/login
- request body :
```json
{
  "email":"ssar@nate.com",
  "password":"1234"
}
```
- response body :
```json
{
  "success": true,
  "response": null,
  "error": null
}
```

- response header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjozLCJleHAiOjE2ODM3MzEzOTZ9.4o5as1_PpUcMjUTTYI9i7Xz7lgisqC62wBNyE85qbE5stBMxYIBlWgE4tdNKKzBsLyJ3ZhsiNYssh8y6v7zs0A
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
{
    "success": true,
    "response": null,
    "error": null
}
```

