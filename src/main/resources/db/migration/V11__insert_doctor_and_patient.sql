INSERT INTO doctor (full_name, email, gender, phone, specialty, medical_license, user_id, created_at, updated_at)
SELECT 'Dr. John Doe', 'doctor@example.com', 'MALE', '+55 11 91234-5678', 'CARDIOLOGY', '123456-SP', u.id, NOW(), NOW()
FROM users u
WHERE u.user_name = 'doctor_user'
  AND NOT EXISTS (SELECT 1 FROM doctor d WHERE d.user_id = u.id);

INSERT INTO patient (full_name, email, gender, phone, date_of_birth, cpf, health_insurance, user_id, created_at, updated_at)
SELECT 'Jane Patient', 'patient@example.com', 'FEMALE', '+55 11 99876-5432', '1985-07-15', '123.456.789-10', 'HealthPlus', u.id, NOW(), NOW()
FROM users u
WHERE u.user_name = 'patient_user'
  AND NOT EXISTS (SELECT 1 FROM patient p WHERE p.user_id = u.id);