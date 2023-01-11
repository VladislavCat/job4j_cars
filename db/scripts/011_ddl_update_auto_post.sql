ALTER TABLE auto_post ADD COLUMN status boolean;
ALTER TABLE auto_post ADD COLUMN car_id int references cars(id);
ALTER TABLE auto_post ADD COLUMN car_photo bytea;
ALTER TABLE cars ADD COLUMN body_id int references bodies(id);
ALTER TABLE cars ADD COLUMN brand_id int references brands(id);