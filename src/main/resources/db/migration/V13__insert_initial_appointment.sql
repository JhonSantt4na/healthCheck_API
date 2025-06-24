INSERT INTO appointment (appointment_date, patient_id, doctor_id, schedule_id, status, cancelled_by, reason, duration, created_at, updated_at, confirmed_at)
SELECT
  '2025-07-01 08:30:00',
  p.id,
  d.id,
  s.id,
  'SCHEDULED',
  NULL,
  NULL,
  30,
  NOW(),
  NOW(),
  NULL
FROM patient p
JOIN doctor d ON d.user_id = (SELECT id FROM users WHERE user_name = 'doctor_user')
JOIN schedules s ON s.doctor_id = d.id AND s.start_time = '2025-07-01 08:00:00' AND s.end_time = '2025-07-01 12:00:00'
WHERE p.user_id = (SELECT id FROM users WHERE user_name = 'patient_user')
  AND NOT EXISTS (
    SELECT 1 FROM appointment a WHERE a.patient_id = p.id AND a.appointment_date = '2025-07-01 08:30:00'
  );