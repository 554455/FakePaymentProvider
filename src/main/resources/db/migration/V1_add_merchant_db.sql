-- Добавляем записи в таблицу users
INSERT INTO users (id) VALUES (1), (2), (3);

-- Добавляем записи в таблицу cards
INSERT INTO cards (id, cardNumber, expDate, cvv) VALUES (1, '1234567812345678', '12/24', '123'), (2, '2345678923456789', '01/25', '456');

-- Добавляем записи в таблицу customers
INSERT INTO customers (id, firstName, lastName, country, users_id) VALUES (1, 'John', 'Doe', 'USA', 1), (2, 'Jane', 'Doe', 'USA', 2);

-- Добавляем записи в таблицу accounts
INSERT INTO accounts (id, currency, balance, enabled) VALUES (1, 'USD', 1000.00, 'true'), (2, 'USD', 2000.00, 'true');

-- Добавляем записи в таблицу merchants
INSERT INTO merchants (merchantId, secretKey, enabled, users_id) VALUES ('merchant1', 'secretKey1', 'true', 1), ('merchant2', 'secretKey2', 'true', 2);

-- Добавляем записи в таблицу transactions
INSERT INTO transactions (id, transactionType, amount, currency, createdAt, updatedAt, status, language, card_id, account_id)
VALUES (1, 'debit', 100, 'USD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'completed', 'en', 1, 1),
       (2, 'credit', 200, 'USD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'completed', 'en', 2, 2);


