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
-- Soil moisture readings (sensor_id = 3), spikes after watering events then dries out
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (38.0, 3, '2026-05-22 07:50:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (76.4, 3, '2026-05-22 08:10:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (70.1, 3, '2026-05-22 09:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (63.5, 3, '2026-05-22 10:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (78.9, 3, '2026-05-22 10:40:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (72.3, 3, '2026-05-22 11:30:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (65.8, 3, '2026-05-22 12:30:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (80.0, 3, '2026-05-22 13:10:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (74.2, 3, '2026-05-22 14:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (67.6, 3, '2026-05-22 15:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (79.1, 3, '2026-05-22 15:40:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (73.5, 3, '2026-05-22 16:30:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (66.9, 3, '2026-05-22 17:30:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (77.8, 3, '2026-05-22 18:10:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (71.2, 3, '2026-05-22 19:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (64.4, 3, '2026-05-22 20:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (57.3, 3, '2026-05-22 22:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (49.8, 3, '2026-05-23 00:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (43.1, 3, '2026-05-23 04:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (37.5, 3, '2026-05-23 07:50:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (78.3, 3, '2026-05-23 08:10:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (71.7, 3, '2026-05-23 09:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (64.2, 3, '2026-05-23 10:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (57.6, 3, '2026-05-23 11:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (79.5, 3, '2026-05-23 12:10:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (73.1, 3, '2026-05-23 13:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (66.4, 3, '2026-05-23 14:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (59.8, 3, '2026-05-23 15:00:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (80.0, 3, '2026-05-23 16:10:00+00:00');
INSERT INTO urban_micro_farm_app.sensor_reading (value, sensor_id, timestamp) VALUES (74.5, 3, '2026-05-23 17:00:00+00:00');

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

-- Watering events
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (284, 1, '2026-05-22 08:00:00+00:00', 'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (310, 1, '2026-05-22 10:30:00+00:00', 'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (295, 1, '2026-05-22 13:00:00+00:00', 'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (320, 1, '2026-05-22 15:30:00+00:00', 'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (284, 1, '2026-05-22 18:00:00+00:00', 'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (305, 1, '2026-05-23 08:00:00+00:00', 'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (290, 1, '2026-05-23 12:00:00+00:00', 'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (315, 1, '2026-05-23 16:00:00+00:00', 'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (280, 1, '2026-05-23 19:00:00+00:00', 'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (300, 1, '2026-05-24 09:00:00+00:00', 'manual');