CREATE TABLE cards (
                       id BIGSERIAL PRIMARY KEY,
                       cardNumber VARCHAR(255) NOT NULL,
                       expDate VARCHAR(255) NOT NULL,
                       cvv VARCHAR(255) NOT NULL
);

CREATE TABLE customers (
                           id BIGSERIAL PRIMARY KEY,
                           firstName VARCHAR(255) NOT NULL,
                           lastName VARCHAR(255) NOT NULL,
                           country VARCHAR(255) NOT NULL,
                           users_id BIGINT,
                           FOREIGN KEY (users_id) REFERENCES users(id)
);
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY
);

CREATE TABLE accounts (
                          id BIGSERIAL PRIMARY KEY,
                          currency VARCHAR(255) NOT NULL,
                          balance NUMERIC(19, 2) NOT NULL,
                          enabled VARCHAR(255) NOT NULL
);

CREATE TABLE merchants (
                           merchantId VARCHAR(255) PRIMARY KEY,
                           secretKey VARCHAR(255) NOT NULL,
                           enabled VARCHAR(255) NOT NULL,
                           users_id BIGINT,
                           FOREIGN KEY (users_id) REFERENCES users(id)
);


CREATE TABLE transactions (
                              id BIGSERIAL PRIMARY KEY,
                              transactionType VARCHAR(255) NOT NULL,
                              amount BIGINT NOT NULL,
                              currency VARCHAR(255) NOT NULL,
                              createdAt TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                              updatedAt TIMESTAMP WITHOUT TIME ZONE,
                              status VARCHAR(255) NOT NULL,
                              language VARCHAR(255),
                              card_id BIGINT,
                              account_id BIGINT
);
