INSERT INTO user_permission (id_user, id_permission)
SELECT u.id, p.id
FROM users u, permission p
WHERE u.user_name = 'admin_user' AND p.description = 'ADMIN'
  AND NOT EXISTS (
    SELECT 1 FROM user_permission up WHERE up.id_user = u.id AND up.id_permission = p.id
  );

INSERT INTO user_permission (id_user, id_permission)
SELECT u.id, p.id
FROM users u, permission p
WHERE u.user_name = 'doctor_user' AND p.description = 'DOCTOR'
  AND NOT EXISTS (
    SELECT 1 FROM user_permission up WHERE up.id_user = u.id AND up.id_permission = p.id
  );

INSERT INTO user_permission (id_user, id_permission)
SELECT u.id, p.id
FROM users u, permission p
WHERE u.user_name = 'patient_user' AND p.description = 'PATIENT'
  AND NOT EXISTS (
    SELECT 1 FROM user_permission up WHERE up.id_user = u.id AND up.id_permission = p.id
  );