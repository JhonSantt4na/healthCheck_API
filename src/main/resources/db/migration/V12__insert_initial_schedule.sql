INSERT INTO schedules (doctor_id, start_time, end_time, available)
SELECT d.id, '2025-07-01 08:00:00', '2025-07-01 12:00:00', TRUE
FROM doctor d
WHERE d.user_id = (SELECT id FROM users WHERE user_name = 'doctor_user')
  AND NOT EXISTS (
    SELECT 1 FROM schedules s WHERE s.doctor_id = d.id AND s.start_time = '2025-07-01 08:00:00' AND s.end_time = '2025-07-01 12:00:00'
  );