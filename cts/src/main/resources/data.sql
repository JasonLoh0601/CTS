INSERT INTO users (ID, USERNAME, EMAIl, PASSWORD, PHONE_NUMBER) VALUES (10001, 'jason', 'jasonloh@gmail.com', '', '12345678');
INSERT INTO USER_WALLET (balance, id, user_id, symbol) VALUES (50000, 10001, 10001, 'USDT');
INSERT INTO USER_WALLET (balance, id, user_id, symbol) VALUES (0, 10002, 10001, 'ETH');
INSERT INTO USER_WALLET (balance, id, user_id, symbol) VALUES (0, 10003, 10001, 'BTC');