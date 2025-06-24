INSERT INTO users (user_name, full_name, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
SELECT 'admin_user', 'Admin User', 'admin@example.com', '{pbkdf2}dcd7db53d884377c8c7951f8140d6c87c3c0974c0ac404c87baddaa7e3b4a9f9a226eb0ce1eb6197', TRUE, TRUE, TRUE, TRUE
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_name = 'admin_user');

INSERT INTO users (user_name, full_name, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
SELECT 'doctor_user', 'Doctor User', 'doctor@example.com', '{pbkdf2}f88675533e478a5c8b344748d5c498e89d93a45a16ef80759c4fd3c67d4cae70cab2d9b1b00f5225', TRUE, TRUE, TRUE, TRUE
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_name = 'doctor_user');

INSERT INTO users (user_name, full_name, email, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
SELECT 'patient_user', 'Patient User', 'patient@example.com', '{pbkdf2}9c34cc5b0605f86bc94d71a10c7e0fd4f5237baf42b0d8b5c5c213f742093ec954992fdf6c964987', TRUE, TRUE, TRUE, TRUE
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_name = 'patient_user');