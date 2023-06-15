# KAKAO 쇼핑

## API 문서
- url (aws)
- pdf
 
## 할일
1. 회원가입 (비밀번호 체크 - 특수문자) - 정규표현식 수정
2. 회원가입 실패 테스트 다 하고, MD 파일도 실패 테스트 추가
3. 로그인 (비밀번호 체크 - 특수문자) - 정규표현식 수정
4. 로그인 실패 테스트 다 하고, MD 파일도 실패 테스트 추가
5. 이메일 - 테스트 추가
6. README 변경
7. html 파일 생성
8. 사진 변경하고 (비슷한 사진)


## 배포 완료
- host : http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com

- 회원가입(POST) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/join
- 로그인(POST) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//login
- 전체 상품 목록 조회 (GET) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//products
- 개별 상품 상세 조회 (GET) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//products/{id}
- 장바구니 담기 (POST) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//carts/add
- 장바구니 조회 (GET) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//carts
- 주문(POST) - 주문버튼 클릭 : 장바구니 업데이트 http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//carts/update
- 결제(POST) - 결재버튼 클릭 : 주문하기 http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//orders/save
- 주문 결과 확인(GET) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//orders/{id}

## 남은 일
- API 요청 컨트롤러 기능 매칭 (완)
- 유효성 검사 - UserRequest 유효성 검사 (완)
- 화면에 따른 DTO 정리 (완)
- AWS 배포 (완)
- 쿼리 튜닝
- 테스트 코드 작성
- RestDoc API 문서 작성

## 데이터 초기화
- admin@nate.com 으로 로그인 (비번 meta1234!)
- http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/admin/reset (post 요청)

## 사용자 시나리오

### 1. 로그인 (기능2)
- method : post
- url : http://localhost:8080/login
- request body :
```json
{
  "email":"ssar@nate.com",
  "password":"meta1234!"
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
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjoxLCJleHAiOjE2ODQ2NjQ3NzB9.xr-zkl6eshxAeahfi2PMaDrqyrFSsDiIqhF0_VZD-f0ByTrBQruL96iLl1EFDGm8SbHxPSjjybsApl6hZvTd5g
```
```text
토큰에 담긴 페이로드
email = ssar@nate.com, roles = ROLE_USER
```

### 2. 전체 상품 목록 조회 (기능4)
- method : get
- url : http://localhost:8080/products
- param : page={number}
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjoxLCJleHAiOjE2ODQ2NjQ3NzB9.xr-zkl6eshxAeahfi2PMaDrqyrFSsDiIqhF0_VZD-f0ByTrBQruL96iLl1EFDGm8SbHxPSjjybsApl6hZvTd5g
```
- response body :
```json
{
  "success": true,
  "response": [
    {
      "id": 1,
      "productName": "기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전",
      "description": "",
      "image": "/images/1.jpg",
      "price": 1000
    },
    {
      "id": 2,
      "productName": "[황금약단밤 골드]2022년산 햇밤 칼집밤700g외/군밤용/생율",
      "description": "",
      "image": "/images/2.jpg",
      "price": 2000
    },
    {
      "id": 3,
      "productName": "삼성전자 JBL JR310 외 어린이용/성인용 헤드셋 3종!",
      "description": "",
      "image": "/images/3.jpg",
      "price": 30000
    },
    {
      "id": 4,
      "productName": "바른 누룽지맛 발효효소 2박스 역가수치보장 / 외 7종",
      "description": "",
      "image": "/images/4.jpg",
      "price": 4000
    },
    {
      "id": 5,
      "productName": "[더주] 컷팅말랑장족, 숏다리 100g/300g 외 주전부리 모음 /중독성 최고/마른안주",
      "description": "",
      "image": "/images/5.jpg",
      "price": 5000
    },
    {
      "id": 6,
      "productName": "굳지않는 앙금절편 1,050g 2팩 외 우리쌀떡 모음전",
      "description": "",
      "image": "/images/6.jpg",
      "price": 15900
    },
    {
      "id": 7,
      "productName": "eoe 이너딜리티 30포, 오렌지맛 고 식이섬유 보충제",
      "description": "",
      "image": "/images/7.jpg",
      "price": 26800
    },
    {
      "id": 8,
      "productName": "제나벨 PDRN 크림 2개. 피부보습/진정 케어",
      "description": "",
      "image": "/images/8.jpg",
      "price": 25900
    },
    {
      "id": 9,
      "productName": "플레이스테이션 VR2 호라이즌 번들. 생생한 몰입감",
      "description": "",
      "image": "/images/9.jpg",
      "price": 797000
    }
  ],
  "error": null
}
```

### 3. 개별 상품 상세 조회 (기능5)
- method : get
- url : http://localhost:8080/products/{id}
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjoxLCJleHAiOjE2ODQ2NjQ3NzB9.xr-zkl6eshxAeahfi2PMaDrqyrFSsDiIqhF0_VZD-f0ByTrBQruL96iLl1EFDGm8SbHxPSjjybsApl6hZvTd5g
```
- response body :
```json
{
  "success": true,
  "response": {
    "id": 1,
    "productName": "기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전",
    "description": "",
    "image": "/images/1.jpg",
    "price": 1000,
    "starCount": 5,
    "options": [
      {
        "id": 1,
        "optionName": "01. 슬라이딩 지퍼백 크리스마스에디션 4종",
        "price": 10000
      },
      {
        "id": 2,
        "optionName": "02. 슬라이딩 지퍼백 플라워에디션 5종",
        "price": 10900
      },
      {
        "id": 3,
        "optionName": "고무장갑 베이지 S(소형) 6팩",
        "price": 9900
      },
      {
        "id": 4,
        "optionName": "뽑아쓰는 키친타올 130매 12팩",
        "price": 16900
      },
      {
        "id": 5,
        "optionName": "2겹 식빵수세미 6매",
        "price": 8900
      }
    ]
  },
  "error": null
}
```

### 4. 장바구니 담기 (기능8)
- method : post
- url : http://localhost:8080/carts/add
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjoxLCJleHAiOjE2ODQ2NjQwNjh9.RZdncM5UYLq9eSjqi6tNlaf0bq62WEqahgBGFmgMWF56yxti4ZQWw5GcC71EjnpjWnfoPjd7YOStsEqU6pz8HA
```
- request body :
```json
[
  {
    "optionId":1,
    "quantity":5
  },
  {
    "optionId":2,
    "quantity":5
  }
]
```
- response body :
```json
{
  "success": true,
  "response": null,
  "error": null
}
```

### 5. 장바구니 조회 (기능9)
- method : get
- url : http://localhost:8080/carts
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjoxLCJleHAiOjE2ODQ2NjQ3NzB9.xr-zkl6eshxAeahfi2PMaDrqyrFSsDiIqhF0_VZD-f0ByTrBQruL96iLl1EFDGm8SbHxPSjjybsApl6hZvTd5g
```
- response body :
```json
{
  "success": true,
  "response": {
    "products": [
      {
        "id": 1,
        "productName": "기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전",
        "carts": [
          {
            "id": 1,
            "option": {
              "id": 1,
              "optionName": "01. 슬라이딩 지퍼백 크리스마스에디션 4종",
              "price": 10000
            },
            "quantity": 5,
            "price": 50000
          },
          {
            "id": 2,
            "option": {
              "id": 2,
              "optionName": "02. 슬라이딩 지퍼백 플라워에디션 5종",
              "price": 10900
            },
            "quantity": 5,
            "price": 54500
          }
        ]
      }
    ],
    "totalPrice": 104500
  },
  "error": null
}
```

### 6. 장바구니 수정 (기능11)
- 주문하기를 할 때 장바구니 데이터를 update하고 그 결과를 응답받는다.
- 결재페이지에서 이 응답을 사용해도 되고, 다시 장바구니 조회 API를 사용해도 된다
- method : post
- url : http://localhost:8080/carts/update
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjoxLCJleHAiOjE2ODQ2NjQ3NzB9.xr-zkl6eshxAeahfi2PMaDrqyrFSsDiIqhF0_VZD-f0ByTrBQruL96iLl1EFDGm8SbHxPSjjybsApl6hZvTd5g
```
- request body :
```json
[
  {
    "cartId":1,
    "quantity":10
  },
  {
    "cartId":2,
    "quantity":10
  }
]
```
- response body :
```json
{
  "success": true,
  "response": {
    "carts": [
      {
        "cartId": 1,
        "optionId": 1,
        "optionName": "01. 슬라이딩 지퍼백 크리스마스에디션 4종",
        "quantity": 10,
        "price": 100000
      },
      {
        "cartId": 2,
        "optionId": 2,
        "optionName": "02. 슬라이딩 지퍼백 플라워에디션 5종",
        "quantity": 10,
        "price": 109000
      }
    ],
    "totalPrice": 209000
  },
  "error": null
}
```

### 7. 주문하기 (기능12)
- 결재하기 버튼을 클릭하면 주문이 되고, 결재는 생략한다. 주문이 완료 되면 해당 유저의 장바구니가 비워지고 주문 결과를 응답받는다. 
- 주문결과 페이지에서 이 응답을 사용해도 되고, 주문결과 확인 API 요청을 해도 된다.
- method : post
- url : http://localhost:8080/orders/save
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjoxLCJleHAiOjE2ODQ2NjQ3NzB9.xr-zkl6eshxAeahfi2PMaDrqyrFSsDiIqhF0_VZD-f0ByTrBQruL96iLl1EFDGm8SbHxPSjjybsApl6hZvTd5g
```
- response body :
```json
{
  "success": true,
  "response": {
    "id": 1,
    "products": [
      {
        "productName": "기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전",
        "items": [
          {
            "id": 1,
            "optionName": "01. 슬라이딩 지퍼백 크리스마스에디션 4종",
            "quantity": 10,
            "price": 100000
          },
          {
            "id": 2,
            "optionName": "02. 슬라이딩 지퍼백 플라워에디션 5종",
            "quantity": 10,
            "price": 109000
          }
        ]
      }
    ],
    "totalPrice": 209000
  },
  "error": null
}
```

### 8. 주문결과 확인 (기능13)
- method : get
- url : http://localhost:8080/orders/{id}
- request header :
```text
Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc2FyQG5hdGUuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlkIjoxLCJleHAiOjE2ODQ2NjQ3NzB9.xr-zkl6eshxAeahfi2PMaDrqyrFSsDiIqhF0_VZD-f0ByTrBQruL96iLl1EFDGm8SbHxPSjjybsApl6hZvTd5g
```

- response body :
```json
{
  "success": true,
  "response": {
    "id": 1,
    "products": [
      {
        "productName": "기본에 슬라이딩 지퍼백 크리스마스/플라워에디션 에디션 외 주방용품 특가전",
        "items": [
          {
            "id": 1,
            "optionName": "01. 슬라이딩 지퍼백 크리스마스에디션 4종",
            "quantity": 10,
            "price": 100000
          },
          {
            "id": 2,
            "optionName": "02. 슬라이딩 지퍼백 플라워에디션 5종",
            "quantity": 10,
            "price": 109000
          }
        ]
      }
    ],
    "totalPrice": 209000
  },
  "error": null
}
```