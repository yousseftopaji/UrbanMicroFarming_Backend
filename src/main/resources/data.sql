-- Insert Sensor Types
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Temperature');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Light');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Soil_Moisture');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Humidity');

-- Insert Test User
INSERT INTO urban_micro_farm_app.users (email, name, password) VALUES ('test@example.com', 'Test User', 'hashed_password_123');

-- Insert Sensors with correct sensor_id mapping
INSERT INTO urban_micro_farm_app.sensor (id, unit, setup_id, sensor_type_name) VALUES (1, '°C', 1, 'Temperature');

-- Light
INSERT INTO urban_micro_farm_app.sensor (id, unit, setup_id, sensor_type_name) VALUES (2, 'lux', 1, 'Light');

-- Soil_Moisture
INSERT INTO urban_micro_farm_app.sensor (id, unit, setup_id, sensor_type_name) VALUES (3, '%', 1, 'Soil_Moisture');

-- Humidity
INSERT INTO urban_micro_farm_app.sensor (id, unit, setup_id, sensor_type_name) VALUES (4, '%', 1, 'Humidity');