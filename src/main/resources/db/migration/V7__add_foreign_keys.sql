ALTER TABLE patient
  ADD CONSTRAINT FK_patient_user FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE doctor
  ADD CONSTRAINT FK_doctor_user FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE schedules
  ADD CONSTRAINT FK_schedules_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id);

ALTER TABLE appointment
  ADD CONSTRAINT FK_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
  ADD CONSTRAINT FK_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id),
  ADD CONSTRAINT FK_appointment_schedule FOREIGN KEY (schedule_id) REFERENCES schedules(id);

ALTER TABLE user_permission
  ADD CONSTRAINT FK_user_permission_user FOREIGN KEY (id_user) REFERENCES users(id),
  ADD CONSTRAINT FK_user_permission_permission FOREIGN KEY (id_permission) REFERENCES permission(id);