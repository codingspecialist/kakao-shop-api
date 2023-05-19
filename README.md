# KAKAO 쇼핑

## 백엔드 기능 정리
- 회원가입(완) http://localhost:8080/join
- 로그인(완) http://localhost:8080/login
- 전체 상품 목록 조회 (완) http://localhost:8080/products
- 개별 상품 상세 조회 (완) http://localhost:8080/products/{id}
- 장바구니 담기 (완) http://localhost:8080/carts/add
- 장바구니 조회 (완) http://localhost:8080/carts
- 주문(완) - 주문버튼 클릭 : 장바구니 업데이트 http://localhost:8080/carts/update
- 결제(완) - 결재버튼 클릭 : 주문하기 http://localhost:8080/orders/save
- 주문 결과 확인(완) http://localhost:8080/orders/{id}

## 남은 일
- API 요청 컨트롤러 기능 매칭 (완)
- 유효성 검사 - UserRequest 유효성 검사 (완)
- 화면에 따른 DTO 정리 (완)
- 쿼리 튜닝 + open in view 비활성화 (완)
- 테스트 코드 작성
- RestDoc API 문서 작성
- AWS 배포 (엘라스틱 빈 스톡 deploy폴더 - jar파일, images폴더)

## 사용자 시나리오
더미데이터가 들어가 있어서 바로 테스트 해보면 된다.

### 1. 로그인
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

### 2. 전체 상품 목록 조회
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

### 3. 개별 상품 상세 조회(상품 + 옵션리스트)
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

### 4. 장바구니 담기
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

### 5. 장바구니 조회
- method : post
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

### 6. 장바구니 수정 (주문하기)
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
  "response": null,
  "error": null
}
```

### 7. 장바구니 조회 (결재 페이지에서 확인)
- method : post
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
            "quantity": 10,
            "price": 100000
          },
          {
            "id": 2,
            "option": {
              "id": 2,
              "optionName": "02. 슬라이딩 지퍼백 플라워에디션 5종",
              "price": 10900
            },
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

### 8. 주문하기 - 결재하기 버튼 클릭시 (결재시 해당 유저의 장바구니 비워짐)
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
    "orderId": 1
  },
  "error": null
}
```

### 9. 주문결과 확인
- method : post
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