ALTER TABLE auto_post ADD COLUMN car_id int references cars(id);
ALTER TABLE auto_post ADD COLUMN car_photo bytea;