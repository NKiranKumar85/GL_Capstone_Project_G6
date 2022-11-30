create database capstone_project;

use capstone_project;

INSERT INTO `capstone_project`.`catogeries` VALUES ( 301,'Furniture','Nice furniture avialable');
INSERT INTO `capstone_project`.`catogeries` VALUES ( 302,'Wall Decor','Nice wall decor stuff avialable');

INSERT INTO `capstone_project`.`product` VALUES ( 801,'Nice chairs',500,'Chair',10000,301);
INSERT INTO `capstone_project`.`product` VALUES ( 802,'Nice chairs',5000,'Table',10000,301);
INSERT INTO `capstone_project`.`product` VALUES ( 803,'Nice Stickers',200,'Sticker',100000,301);

INSERT INTO `capstone_project`.`users` VALUES 
('1', 'user1@mail.com', 'jai', 'kumar', '11111111111', '$2a$10$H4DmydXICN4aoev2jIGutuA..JmV0inlGnIUH32TssPNFsJ4ZNPUy', 'ROLE_USER', 'jaikumar');
INSERT INTO `capstone_project`.`users` VALUES 
('21', 'string', 'string', 'string', 'string', '$2a$10$3XDJqVzF.PsJ1J1wlVIt..Cf/FvxrE8sTR0r9ge2qp6/qeL5.ml9q', 'ROLE_ADMIN', 'admin');

INSERT INTO `capstone_project`.`discount_coupon` VALUES (401,'Mythic',2000, 25);
INSERT INTO `capstone_project`.`discount_coupon` VALUES (402,'Rare',1000, 15);
INSERT INTO `capstone_project`.`discount_coupon` VALUES (403,'Lucky',1000, 10);

INSERT INTO `capstone_project`.`wish_list_items`(`wish_list_id`,`product_id`,`user_id`)VALUES(1234, 801, 1);
INSERT INTO `capstone_project`.`wish_list_items`(`wish_list_id`,`product_id`,`user_id`)VALUES(1235, 802, 1);

INSERT INTO user_discount_coupons VALUES(2022221, 401, 1);
INSERT INTO user_discount_coupons VALUES(2022222, 402, 1);






