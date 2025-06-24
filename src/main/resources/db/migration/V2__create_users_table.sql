CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(255) UNIQUE NOT NULL,
  full_name VARCHAR(255),
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  account_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
  account_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
  credentials_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
  enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE user_permission (
  id_user BIGINT NOT NULL,
  id_permission BIGINT NOT NULL,
  PRIMARY KEY (id_user, id_permission)
);