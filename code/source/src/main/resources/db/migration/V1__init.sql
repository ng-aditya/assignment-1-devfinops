CREATE TABLE IF NOT EXISTS clients (
  id UUID PRIMARY KEY,
  name TEXT NOT NULL,
  email TEXT NOT NULL UNIQUE,
  phone TEXT NOT NULL,
  address TEXT NOT NULL
);

INSERT INTO clients (id, name, email, phone, address) VALUES
('9b90c111-3fd9-4c87-bc42-5ef2c67144c1', 'Aarav Mehta', 'aarav.mehta@example.com', '+91-90000-00001', '221B MG Road, Pune, MH'),
('1795d4d6-1c50-49da-a3f0-12ad3f60a2cc', 'Diya Sharma', 'diya.sharma@example.com', '+91-90000-00002', '17 Park Street, Kolkata, WB'),
('9a0e6d77-9e30-4d2f-bb3e-0b2ce26a0f3c', 'Ishaan Gupta', 'ishaan.gupta@example.com', '+91-90000-00003', '44 Sector 62, Noida, UP'),
('0f8f8f77-636b-4a4c-b5bd-53a55f5d6c8f', 'Ananya Iyer', 'ananya.iyer@example.com', '+91-90000-00004', '8 Residency Road, Bengaluru, KA'),
('6c0d31e5-64d2-4e3e-a4f4-0bb0adbf3e9e', 'Kabir Singh', 'kabir.singh@example.com', '+91-90000-00005', '12 Banjara Hills, Hyderabad, TS');

