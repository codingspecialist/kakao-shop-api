# KAKAO 쇼핑

## 실행 파일 만들기
```text
host : localhost:8080

git clone {repo address}
cd kakao-shop-api
./gradlew clean build
cd build/libs
위 경로에 jar파일이 생성됩니다
jar 파일 동일겅로에 images 폴더를 카피 한뒤 아래 명령어로 실행해주세요
images폴더는 프로젝트 내부에 있습니다
java -jar kakao-1.0.jar
```

## API 문서
- http://localhost:8080/docs/api-docs.html
- http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/docs/api-docs.html

## 사용자 시나리오 API PDF 문서
- http://localhost:8080/docs/api-pdf.pdf
- http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/docs/api-pdf.pdf

## 배포된 서버 주소
- host : http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com
- 이메일 중복 체크(POST) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/check
- 회원가입(POST) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/join
- 로그인(POST) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//login
- 전체 상품 목록 조회 (GET) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/products
- 개별 상품 상세 조회 (GET) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/products/{id}
- 장바구니 담기 (POST) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/carts/add
- 장바구니 조회 (GET) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/carts
- 주문(POST) - 주문버튼 클릭 : 장바구니 업데이트 http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/carts/update
- 결제(POST) - 결재버튼 클릭 : 주문하기 http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/orders/save
- 주문 결과 확인(GET) http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com//orders/{id}

## 데이터 초기화
- admin@nate.com 으로 로그인 (비번 meta1234!)
- http://localhost:8080/admin/reset (post 요청)
- http://kakao-app-env.eba-ggmpdnhz.ap-northeast-2.elasticbeanstalk.com/admin/reset (post 요청)
