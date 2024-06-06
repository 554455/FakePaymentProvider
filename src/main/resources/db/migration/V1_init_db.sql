CREATE TABLE users (
  user_id UUID PRIMARY KEY,
  username VARCHAR(255) UNIQUE,
  password_hash VARCHAR(256),
  role VARCHAR(50),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  created_by VARCHAR(256),
  updated_by VARCHAR(256),
  status VARCHAR(256)
);

CREATE TABLE merchants (
  merchant_id UUID PRIMARY KEY REFERENCES users(user_id),
  secret_key VARCHAR(256),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  created_by VARCHAR(256),
  updated_by VARCHAR(256),
  status VARCHAR(256)
);

CREATE TABLE accounts (
  id SERIAL PRIMARY KEY,
  user_id UUID REFERENCES users(user_id),
  currency VARCHAR(55),
  amount BIGINT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  created_by VARCHAR(256),
  updated_by VARCHAR(256),
  status VARCHAR(256)
);

CREATE TABLE cards (
  card_number VARCHAR(16) PRIMARY KEY,
  account_id BIGINT REFERENCES accounts(id),
  exp_date VARCHAR(25),
  cvv VARCHAR(3),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  created_by VARCHAR(256),
  updated_by VARCHAR(256),
  status VARCHAR(256)
);

CREATE TABLE customers (
  customer_id UUID PRIMARY KEY REFERENCES users(user_id),
  card_number VARCHAR(16) REFERENCES cards(card_number),
  first_name VARCHAR(256),
  last_name VARCHAR(256),
  country VARCHAR(256),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  created_by VARCHAR(256),
  updated_by VARCHAR(256),
  status VARCHAR(256)
);

CREATE TABLE transactions (
  transaction_id UUID PRIMARY KEY,
  payment_method VARCHAR(25),
  amount BIGINT,
  currency VARCHAR(25),
  language VARCHAR(25),
  notification_url VARCHAR(256),
  card_number VARCHAR(16) REFERENCES cards(card_number),
  account_id BIGINT REFERENCES accounts(id),
  transaction_type VARCHAR(25),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  created_by VARCHAR(256),
  updated_by VARCHAR(256),
  status VARCHAR(256)
);

CREATE TABLE webhooks (
  id SERIAL PRIMARY KEY,
  transaction_id UUID REFERENCES transactions(transaction_id),
  transaction_attempt BIGINT DEFAULT 0,
  url_request VARCHAR(256),
  body_request TEXT,
  message VARCHAR(256),
  body_response VARCHAR(256),
  status_response VARCHAR(256),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  created_by VARCHAR(256),
  updated_by VARCHAR(256),
  status VARCHAR(256)
);
