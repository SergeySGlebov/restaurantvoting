DELETE FROM USER_ROLE;
DELETE FROM VOTE;
DELETE FROM DISH;
DELETE FROM MENU;
DELETE FROM RESTAURANT;
DELETE FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO RESTAURANT (NAME)
VALUES ('KFC'),
       ('ViT'),
       ('Burger King');

INSERT INTO MENU (DATE, RESTAURANT_ID)
VALUES (now, 100003),
       (now, 100004);

INSERT INTO DISH (MENU_ID, NAME, PRICE)
VALUES (100006, 'Twister', 198.57),
       (100006, 'Chicken Strips', 234),
       (100006, 'Milkshake', 150),
       (100007, 'Big Mac', 199),
       (100007, 'French fries', 80),
       (100007, 'Coca-cola', 111),
       (100007, 'Cherry pie', 69);

INSERT INTO VOTE (DATE, RESTAURANT_ID, USER_ID)
VALUES (now, 100003, 100000);
