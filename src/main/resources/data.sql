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
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (21.3, 1, CURRENT_TIMESTAMP - INTERVAL '9 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (22.1, 1, CURRENT_TIMESTAMP - INTERVAL '8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (23.5, 1, CURRENT_TIMESTAMP - INTERVAL '7 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (24.0, 1, CURRENT_TIMESTAMP - INTERVAL '6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (25.2, 1, CURRENT_TIMESTAMP - INTERVAL '5 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (24.8, 1, CURRENT_TIMESTAMP - INTERVAL '4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (23.9, 1, CURRENT_TIMESTAMP - INTERVAL '3 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (22.7, 1, CURRENT_TIMESTAMP - INTERVAL '2 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (21.9, 1, CURRENT_TIMESTAMP - INTERVAL '1 hour');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (21.4, 1, CURRENT_TIMESTAMP);

-- Sensor readings for Light (sensor_id = 2)
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (300, 2, CURRENT_TIMESTAMP - INTERVAL '9 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (520, 2, CURRENT_TIMESTAMP - INTERVAL '8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (780, 2, CURRENT_TIMESTAMP - INTERVAL '7 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (920, 2, CURRENT_TIMESTAMP - INTERVAL '6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (1050, 2, CURRENT_TIMESTAMP - INTERVAL '5 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (980, 2, CURRENT_TIMESTAMP - INTERVAL '4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (870, 2, CURRENT_TIMESTAMP - INTERVAL '3 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (640, 2, CURRENT_TIMESTAMP - INTERVAL '2 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (410, 2, CURRENT_TIMESTAMP - INTERVAL '1 hour');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (210, 2, CURRENT_TIMESTAMP);

-- Sensor readings for Humidity (sensor_id = 4)
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (62.4, 4, CURRENT_TIMESTAMP - INTERVAL '9 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (63.1, 4, CURRENT_TIMESTAMP - INTERVAL '8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (64.7, 4, CURRENT_TIMESTAMP - INTERVAL '7 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (66.2, 4, CURRENT_TIMESTAMP - INTERVAL '6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (68.5, 4, CURRENT_TIMESTAMP - INTERVAL '5 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (67.3, 4, CURRENT_TIMESTAMP - INTERVAL '4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (65.9, 4, CURRENT_TIMESTAMP - INTERVAL '3 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (64.4, 4, CURRENT_TIMESTAMP - INTERVAL '2 hours');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (63.8, 4, CURRENT_TIMESTAMP - INTERVAL '1 hour');
INSERT INTO urban_micro_farm_app.sensor_readings  (value, sensor_id, timestamp) VALUES (62.9, 4, CURRENT_TIMESTAMP);

-- Clean slate for affected tables (optional, run if you want fresh data)
-- DELETE FROM urban_micro_farm_app.sensor_readings WHERE sensor_id = 3;
-- DELETE FROM urban_micro_farm_app.watering_event WHERE actuator_id = 1;
-- DELETE FROM urban_micro_farm_app.prediction WHERE plant_id = 1;

-- Soil moisture readings (sensor_id = 3) — 7 days, readings every ~2h
-- Day 1 (7 days ago)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (35.2, 3, NOW() - INTERVAL '7 days');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (32.8, 3, NOW() - INTERVAL '6 days 22 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (30.1, 3, NOW() - INTERVAL '6 days 20 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (78.5, 3, NOW() - INTERVAL '6 days 18 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (74.2, 3, NOW() - INTERVAL '6 days 16 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (68.9, 3, NOW() - INTERVAL '6 days 14 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (63.4, 3, NOW() - INTERVAL '6 days 12 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (58.1, 3, NOW() - INTERVAL '6 days 10 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (52.7, 3, NOW() - INTERVAL '6 days 8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (47.3, 3, NOW() - INTERVAL '6 days 6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (80.0, 3, NOW() - INTERVAL '6 days 4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (75.6, 3, NOW() - INTERVAL '6 days 2 hours');

-- Day 2 (5 days ago)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (70.1, 3, NOW() - INTERVAL '6 days');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (64.8, 3, NOW() - INTERVAL '5 days 22 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (59.3, 3, NOW() - INTERVAL '5 days 20 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (53.9, 3, NOW() - INTERVAL '5 days 18 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (48.4, 3, NOW() - INTERVAL '5 days 16 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (43.0, 3, NOW() - INTERVAL '5 days 14 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (37.6, 3, NOW() - INTERVAL '5 days 12 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (79.2, 3, NOW() - INTERVAL '5 days 10 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (73.8, 3, NOW() - INTERVAL '5 days 8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (68.5, 3, NOW() - INTERVAL '5 days 6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (63.1, 3, NOW() - INTERVAL '5 days 4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (57.7, 3, NOW() - INTERVAL '5 days 2 hours');

-- Day 3 (4 days ago)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (52.2, 3, NOW() - INTERVAL '5 days');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (46.8, 3, NOW() - INTERVAL '4 days 22 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (41.3, 3, NOW() - INTERVAL '4 days 20 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (36.0, 3, NOW() - INTERVAL '4 days 18 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (77.4, 3, NOW() - INTERVAL '4 days 16 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (72.1, 3, NOW() - INTERVAL '4 days 14 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (66.7, 3, NOW() - INTERVAL '4 days 12 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (61.3, 3, NOW() - INTERVAL '4 days 10 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (55.9, 3, NOW() - INTERVAL '4 days 8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (50.4, 3, NOW() - INTERVAL '4 days 6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (80.0, 3, NOW() - INTERVAL '4 days 4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (75.3, 3, NOW() - INTERVAL '4 days 2 hours');

-- Day 4 (3 days ago)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (69.8, 3, NOW() - INTERVAL '4 days');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (64.4, 3, NOW() - INTERVAL '3 days 22 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (59.0, 3, NOW() - INTERVAL '3 days 20 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (53.5, 3, NOW() - INTERVAL '3 days 18 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (48.1, 3, NOW() - INTERVAL '3 days 16 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (42.6, 3, NOW() - INTERVAL '3 days 14 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (78.8, 3, NOW() - INTERVAL '3 days 12 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (73.5, 3, NOW() - INTERVAL '3 days 10 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (68.1, 3, NOW() - INTERVAL '3 days 8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (62.6, 3, NOW() - INTERVAL '3 days 6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (57.2, 3, NOW() - INTERVAL '3 days 4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (51.7, 3, NOW() - INTERVAL '3 days 2 hours');

-- Day 5 (2 days ago)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (46.3, 3, NOW() - INTERVAL '3 days');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (40.8, 3, NOW() - INTERVAL '2 days 22 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (35.4, 3, NOW() - INTERVAL '2 days 20 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (79.6, 3, NOW() - INTERVAL '2 days 18 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (74.3, 3, NOW() - INTERVAL '2 days 16 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (68.9, 3, NOW() - INTERVAL '2 days 14 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (63.4, 3, NOW() - INTERVAL '2 days 12 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (58.0, 3, NOW() - INTERVAL '2 days 10 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (52.5, 3, NOW() - INTERVAL '2 days 8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (47.1, 3, NOW() - INTERVAL '2 days 6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (80.0, 3, NOW() - INTERVAL '2 days 4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (74.7, 3, NOW() - INTERVAL '2 days 2 hours');

-- Day 6 (yesterday)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (69.2, 3, NOW() - INTERVAL '2 days');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (63.8, 3, NOW() - INTERVAL '1 day 22 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (58.3, 3, NOW() - INTERVAL '1 day 20 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (52.9, 3, NOW() - INTERVAL '1 day 18 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (47.4, 3, NOW() - INTERVAL '1 day 16 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (42.0, 3, NOW() - INTERVAL '1 day 14 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (77.9, 3, NOW() - INTERVAL '1 day 12 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (72.6, 3, NOW() - INTERVAL '1 day 10 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (67.1, 3, NOW() - INTERVAL '1 day 8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (61.7, 3, NOW() - INTERVAL '1 day 6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (56.2, 3, NOW() - INTERVAL '1 day 4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (50.8, 3, NOW() - INTERVAL '1 day 2 hours');

-- Day 7 (today)
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (45.3, 3, NOW() - INTERVAL '1 day');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (39.9, 3, NOW() - INTERVAL '22 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (78.1, 3, NOW() - INTERVAL '20 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (72.8, 3, NOW() - INTERVAL '18 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (67.4, 3, NOW() - INTERVAL '16 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (61.9, 3, NOW() - INTERVAL '14 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (56.5, 3, NOW() - INTERVAL '12 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (51.0, 3, NOW() - INTERVAL '10 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (80.0, 3, NOW() - INTERVAL '8 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (75.4, 3, NOW() - INTERVAL '6 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (70.0, 3, NOW() - INTERVAL '4 hours');
INSERT INTO urban_micro_farm_app.sensor_readings (value, sensor_id, timestamp) VALUES (64.5, 3, NOW() - INTERVAL '2 hours');

-- Watering events — timed to match reading timestamps exactly
-- manual events at the spike moments
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (310, 1, NOW() - INTERVAL '6 days 18 hours', 'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (290, 1, NOW() - INTERVAL '6 days 4 hours',  'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (305, 1, NOW() - INTERVAL '5 days 10 hours', 'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (320, 1, NOW() - INTERVAL '4 days 16 hours', 'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (295, 1, NOW() - INTERVAL '4 days 4 hours',  'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (315, 1, NOW() - INTERVAL '3 days 12 hours', 'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (284, 1, NOW() - INTERVAL '2 days 18 hours', 'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (300, 1, NOW() - INTERVAL '2 days 4 hours',  'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (318, 1, NOW() - INTERVAL '1 day 12 hours',  'manual');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (292, 1, NOW() - INTERVAL '20 hours',         'automatic');
INSERT INTO urban_micro_farm_app.watering_event (water_used_ml, actuator_id, created_at, mode) VALUES (308, 1, NOW() - INTERVAL '8 hours',          'manual');

-- Predictions — last 30 days for plant_id = 1
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.28, NOW() - INTERVAL '30 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.31, NOW() - INTERVAL '29 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.27, NOW() - INTERVAL '28 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.33, NOW() - INTERVAL '27 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.30, NOW() - INTERVAL '26 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.35, NOW() - INTERVAL '25 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.29, NOW() - INTERVAL '24 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.26, NOW() - INTERVAL '23 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.32, NOW() - INTERVAL '22 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.34, NOW() - INTERVAL '21 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.29, NOW() - INTERVAL '20 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.31, NOW() - INTERVAL '19 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.36, NOW() - INTERVAL '18 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.28, NOW() - INTERVAL '17 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.33, NOW() - INTERVAL '16 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.27, NOW() - INTERVAL '15 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.30, NOW() - INTERVAL '14 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.35, NOW() - INTERVAL '13 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.32, NOW() - INTERVAL '12 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.28, NOW() - INTERVAL '11 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.34, NOW() - INTERVAL '10 days', 1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.31, NOW() - INTERVAL '9 days',  1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.29, NOW() - INTERVAL '8 days',  1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.36, NOW() - INTERVAL '7 days',  1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.33, NOW() - INTERVAL '6 days',  1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.30, NOW() - INTERVAL '5 days',  1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.27, NOW() - INTERVAL '4 days',  1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.32, NOW() - INTERVAL '3 days',  1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.35, NOW() - INTERVAL '2 days',  1);
INSERT INTO urban_micro_farm_app.prediction (predicted_value, created_at, plant_id) VALUES (0.31, NOW() - INTERVAL '1 day',   1);