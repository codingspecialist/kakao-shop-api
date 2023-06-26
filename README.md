# KAKAO 쇼핑

## 로컬에서 실행 파일 만들기
```text
host : localhost:8080

1. git clone https://github.com/codingspecialist/kakao-shop-api.git
2. 다운 받은 프로젝트 폴더(kakao-shop-api) 내부로 들어가서 git bash를 열어주세요. Mac 사용자는 터미널을 이용해서 해당 경로로 가세요.
3. ./gradlew clean build
4. 윈도우 사용자는 git bash를 이용하여 터미널을 열고 3번 명령어를 실행해주세요.
5. cd build/libs
6. 위 경로에 kakao-1.0.jar파일이 생성됩니다
7. jar 파일 동일 경로에 images 폴더를 카피 한뒤 아래 명령어로 실행해주세요
8. images폴더는 kakao-shop-api/images 경로에 있습니다.
9. java -jar -Dspring.profiles.active=local kakao-1.0.jar
```

## 사진 접근하는 법
- http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/images/1.jpg
- ...
- http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/images/15.jpg
## API 문서
- http://localhost:8080/docs/api-docs.html
- http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/docs/api-docs.html

## 사용자 시나리오 API PDF 문서
- http://localhost:8080/docs/api-pdf.pdf
- https://github.com/codingspecialist/kakao-shop-api/blob/master/backup-data-v2/api-pdf.pdf

## 배포된 서버 주소
- host : http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com
- 이메일 중복 체크(POST) http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/check
- 회원가입(POST) http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/join
- 로그인(POST) http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/login
- 전체 상품 목록 조회 (GET) http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/products
- 개별 상품 상세 조회 (GET) http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/products/{id}
- 장바구니 담기 (POST) http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/carts/add
- 장바구니 조회 (GET) http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/carts
- 주문(POST) - 주문버튼 클릭 : 장바구니 업데이트 http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/carts/update
- 결제(POST) - 결재버튼 클릭 : 주문하기 http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/orders/save
- 주문 결과 확인(GET) http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/orders/{id}

## 데이터 초기화
- admin@nate.com 으로 로그인 (비번 meta1234!)
- http://localhost:8080/admin/reset (post 요청)
- http://kakao-app-env.eba-kfsgeb74.ap-northeast-2.elasticbeanstalk.com/admin/reset (post 요청)
