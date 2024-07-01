-- Добавляем записи в таблицу users
INSERT INTO users (id) VALUES (1), (2), (3);

-- Добавляем записи в таблицу cards
INSERT INTO cards (card_number, expDate, cvv) VALUES ('1234567812345678', '12/24', '123'), ('2345678923456789', '01/25', '456');

-- Добавляем записи в таблицу customers
INSERT INTO customers (id, firstName, lastName, country, users_id) VALUES (1, 'John', 'Doe', 'USA', 1), (2, 'Jane', 'Doe', 'USA', 2);

-- Добавляем записи в таблицу accounts
INSERT INTO accounts (id, currency, balance, enabled) VALUES (1, 'USD', 1000.00, 'true'), (2, 'USD', 2000.00, 'true');

-- Добавляем записи в таблицу merchants
INSERT INTO merchants (merchant_id, secret_key) VALUES ('merchant1', 'c2VjcmV0S2V5MQ=='), ('merchant2', 'c2VjcmV0S2V5Mg==');

-- Добавляем записи в таблицу transactions
INSERT INTO transactions (id, transaction_type, amount, currency, created_at, updated_at, status, language, card_number, account_id)
VALUES (200, 'debit', 100, 'USD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'completed', 'en', 1, 1),
       (300, 'credit', 200, 'USD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'completed', 'en', 2, 2);


