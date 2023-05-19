-- 모든 제약 조건 비활성화
SET REFERENTIAL_INTEGRITY FALSE;
truncate table user_tb;
truncate table product_tb;
truncate table order_tb;
truncate table option_tb;
truncate table item_tb;
truncate table cart_tb;
SET REFERENTIAL_INTEGRITY TRUE;
-- 모든 제약 조건 활성화