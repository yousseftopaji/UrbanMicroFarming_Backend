-- Insert Sensor Types
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Temperature');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Light');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Soil_Moisture');
INSERT INTO urban_micro_farm_app.sensor_type (name) VALUES ('Humidity');

-- Insert Test User
INSERT INTO urban_micro_farm_app.users (email, name, password_hash, theme, created_at) VALUES ('test@example.com', 'Test User', 'hashed_password_123', 'SYSTEM', CURRENT_TIMESTAMP);

-- Insert Growing Setup with correct user_id mapping
INSERT INTO urban_micro_farm_app.growing_setup(location, serial_number, user_id) VALUES ('Rooftop Garden', 'SN123456', 1);

-- Insert Sensors with correct sensor_id mapping
INSERT INTO urban_micro_farm_app.sensor (unit, setup_id, sensor_type_name) VALUES ('°C', 1, 'Temperature');

-- Light
INSERT INTO urban_micro_farm_app.sensor (unit, setup_id, sensor_type_name) VALUES ('lux', 1, 'Light');

-- Soil_Moisture
INSERT INTO urban_micro_farm_app.sensor (unit, setup_id, sensor_type_name) VALUES ('%', 1, 'Soil_Moisture');

-- Humidity
INSERT INTO urban_micro_farm_app.sensor (unit, setup_id, sensor_type_name) VALUES ('%', 1, 'Humidity');
