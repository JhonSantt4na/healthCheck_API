INSERT INTO permission (description)
SELECT 'ADMIN' WHERE NOT EXISTS (SELECT 1 FROM permission WHERE description = 'ROLE_ADMIN');

INSERT INTO permission (description)
SELECT 'DOCTOR' WHERE NOT EXISTS (SELECT 1 FROM permission WHERE description = 'ROLE_DOCTOR');

INSERT INTO permission (description)
SELECT 'PATIENT' WHERE NOT EXISTS (SELECT 1 FROM permission WHERE description = 'ROLE_PATIENT');