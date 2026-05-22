-- Insert Sensor Types
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Soil_Moisture');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Temperature');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Light');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Humidity');

-- Insert Test User   -- 'Password123!'
INSERT INTO urban_micro_farm_app.users (email, name, password_hash, theme, created_at) VALUES ('test@example.com', 'Test User', '$2a$12$cE4Vkvgkr7bNBEIbuUQoke8KmZpHPnb0dFft4jXU3HWQpea5d/9QO', 'SYSTEM', CURRENT_TIMESTAMP);

-- Insert Growing Setup with correct user_id mapping
INSERT INTO urban_micro_farm_app.growing_setup(location, serial_number, user_id) VALUES ('Rooftop Garden', 'SN123456', 1);

-- Insert Actuator for growing setup 1
INSERT INTO urban_micro_farm_app.actuator (type, status, setup_id) VALUES ('water_pump', 'ACTIVE', 1);

-- Insert Sensors with correct sensor_id mapping
INSERT INTO urban_micro_farm_app.sensor (unit, setup_id, sensor_type_name) VALUES ('°C', 1, 'Temperature');

-- Light
INSERT INTO urban_micro_farm_app.sensor (unit, setup_id, sensor_type_name) VALUES ('lux', 1, 'Light');

-- Soil_Moisture
INSERT INTO urban_micro_farm_app.sensor (unit, setup_id, sensor_type_name) VALUES ('%', 1, 'Soil_Moisture');

-- Humidity
INSERT INTO urban_micro_farm_app.sensor (unit, setup_id, sensor_type_name) VALUES ('%', 1, 'Humidity');

INSERT INTO urban_micro_farm_app.plant (date_planted, id, sensor_id, description, name, photo, status, type)
VALUES ('2026-05-22 14:46:31.779307+00:00', 1, 3, 'Cute basil', 'Basil', null, 'growing', 'Herb');

-- Sensor readings for Temperature (sensor_id = 1)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (21.3, 1, CURRENT_TIMESTAMP - INTERVAL '9 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (22.1, 1, CURRENT_TIMESTAMP - INTERVAL '8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (23.5, 1, CURRENT_TIMESTAMP - INTERVAL '7 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (24.0, 1, CURRENT_TIMESTAMP - INTERVAL '6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (25.2, 1, CURRENT_TIMESTAMP - INTERVAL '5 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (24.8, 1, CURRENT_TIMESTAMP - INTERVAL '4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (23.9, 1, CURRENT_TIMESTAMP - INTERVAL '3 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (22.7, 1, CURRENT_TIMESTAMP - INTERVAL '2 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (21.9, 1, CURRENT_TIMESTAMP - INTERVAL '1 hour');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (21.4, 1, CURRENT_TIMESTAMP);

-- Sensor readings for Light (sensor_id = 2)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (300, 2, CURRENT_TIMESTAMP - INTERVAL '9 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (520, 2, CURRENT_TIMESTAMP - INTERVAL '8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (780, 2, CURRENT_TIMESTAMP - INTERVAL '7 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (920, 2, CURRENT_TIMESTAMP - INTERVAL '6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (1050, 2, CURRENT_TIMESTAMP - INTERVAL '5 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (980, 2, CURRENT_TIMESTAMP - INTERVAL '4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (870, 2, CURRENT_TIMESTAMP - INTERVAL '3 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (640, 2, CURRENT_TIMESTAMP - INTERVAL '2 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (410, 2, CURRENT_TIMESTAMP - INTERVAL '1 hour');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (210, 2, CURRENT_TIMESTAMP);

-- Sensor readings for Soil_Moisture (sensor_id = 3)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (58.2, 3, CURRENT_TIMESTAMP - INTERVAL '9 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (55.7, 3, CURRENT_TIMESTAMP - INTERVAL '8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (53.1, 3, CURRENT_TIMESTAMP - INTERVAL '7 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (50.4, 3, CURRENT_TIMESTAMP - INTERVAL '6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (47.9, 3, CURRENT_TIMESTAMP - INTERVAL '5 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (45.3, 3, CURRENT_TIMESTAMP - INTERVAL '4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (43.8, 3, CURRENT_TIMESTAMP - INTERVAL '3 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (42.1, 3, CURRENT_TIMESTAMP - INTERVAL '2 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (40.5, 3, CURRENT_TIMESTAMP - INTERVAL '1 hour');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (38.9, 3, CURRENT_TIMESTAMP);

-- Sensor readings for Humidity (sensor_id = 4)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (62.4, 4, CURRENT_TIMESTAMP - INTERVAL '9 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (63.1, 4, CURRENT_TIMESTAMP - INTERVAL '8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (64.7, 4, CURRENT_TIMESTAMP - INTERVAL '7 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (66.2, 4, CURRENT_TIMESTAMP - INTERVAL '6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (68.5, 4, CURRENT_TIMESTAMP - INTERVAL '5 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (67.3, 4, CURRENT_TIMESTAMP - INTERVAL '4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (65.9, 4, CURRENT_TIMESTAMP - INTERVAL '3 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (64.4, 4, CURRENT_TIMESTAMP - INTERVAL '2 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (63.8, 4, CURRENT_TIMESTAMP - INTERVAL '1 hour');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (62.9, 4, CURRENT_TIMESTAMP);
