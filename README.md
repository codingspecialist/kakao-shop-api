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
```json
{
    "success": true,
    "response": null,
    "error": null
}
```

### 4. 상품목록 보기
- method : post
- url : http://localhost:8080/products
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MzczMjY1MiwidXNlcklkIjozfQ.xlQksBtOBczgeuaanYViiqrMTx5jijsRmiaEdlm-AB_ykIerS5vtZIFKPVZrQGhE2ofBS_jQD891vxyOBt4G1g
```
- response body :
```json
{
  "success": true,
  "response": [
    {
      "productId": 1,
      "productName": "기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전",
      "description": "",
      "image": "/images/1.jpg",
      "price": 1000
    },
    {
      "productId": 2,
      "productName": "[황금약단밤 골드]2022년산 햇밤 칼집밤700g외/군밤용/생율",
      "description": "",
      "image": "/images/2.jpg",
      "price": 2000
    },
    {
      "productId": 3,
      "productName": "삼성전자 JBL JR310 외 어린이용/성인용 헤드셋 3종!",
      "description": "",
      "image": "/images/3.jpg",
      "price": 30000
    },
    {
      "productId": 4,
      "productName": "바른 누룽지맛 발효효소 2박스 역가수치보장 / 외 7종",
      "description": "",
      "image": "/images/4.jpg",
      "price": 4000
    },
    {
      "productId": 5,
      "productName": "[더주] 컷팅말랑장족, 숏다리 100g/300g 외 주전부리 모음 /중독성 최고/마른안주",
      "description": "",
      "image": "/images/5.jpg",
      "price": 5000
    },
    {
      "productId": 6,
      "productName": "굳지않는 앙금절편 1,050g 2팩 외 우리쌀떡 모음전",
      "description": "",
      "image": "/images/6.jpg",
      "price": 15900
    },
    {
      "productId": 7,
      "productName": "eoe 이너딜리티 30포, 오렌지맛 고 식이섬유 보충제",
      "description": "",
      "image": "/images/7.jpg",
      "price": 26800
    },
    {
      "productId": 8,
      "productName": "제나벨 PDRN 크림 2개. 피부보습/진정 케어",
      "description": "",
      "image": "/images/8.jpg",
      "price": 25900
    },
    {
      "productId": 9,
      "productName": "플레이스테이션 VR2 호라이즌 번들. 생생한 몰입감",
      "description": "",
      "image": "/images/9.jpg",
      "price": 797000
    }
  ],
  "error": null
}
```

### 5. 상품 한건 보기
- method : post`
- url : http://localhost:8080/products/{id}
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MzczMjY1MiwidXNlcklkIjozfQ.xlQksBtOBczgeuaanYViiqrMTx5jijsRmiaEdlm-AB_ykIerS5vtZIFKPVZrQGhE2ofBS_jQD891vxyOBt4G1g
```
- response body :
```json
{
  "success": true,
  "response": {
    "productId": 1,
    "productName": "기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전",
    "description": "",
    "image": "/images/1.jpg",
    "price": 1000
  },
  "error": null
}
```

### 6. 상품 옵션 보기
- method : post`
- url : http://localhost:8080/products/{id}/option
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY4MzczMjY1MiwidXNlcklkIjozfQ.xlQksBtOBczgeuaanYViiqrMTx5jijsRmiaEdlm-AB_ykIerS5vtZIFKPVZrQGhE2ofBS_jQD891vxyOBt4G1g
```
- response body :
```json
{
  "success": true,
  "response": [
    {
      "optionId": 1,
      "productId": 1,
      "optionName": "01. 슬라이딩 지퍼백 크리스마스에디션 4종",
      "price": 10000
    },
    {
      "optionId": 2,
      "productId": 1,
      "optionName": "02. 슬라이딩 지퍼백 플라워에디션 5종",
      "price": 10900
    },
    {
      "optionId": 3,
      "productId": 1,
      "optionName": "고무장갑 베이지 S(소형) 6팩",
      "price": 9900
    },
    {
      "optionId": 4,
      "productId": 1,
      "optionName": "뽑아쓰는 키친타올 130매 12팩",
      "price": 16900
    },
    {
      "optionId": 5,
      "productId": 1,
      "optionName": "2겹 식빵수세미 6매",
      "price": 8900
    }
  ],
  "error": null
}
```