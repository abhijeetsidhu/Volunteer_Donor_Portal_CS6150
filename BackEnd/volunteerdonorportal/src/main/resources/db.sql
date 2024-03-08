CREATE TABLE user_table (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    phone_number VARCHAR,
    role VARCHAR,
    dob DATE,
    name VARCHAR,
    email_notification BOOLEAN DEFAULT true,
    whatsapp_notification BOOLEAN DEFAULT true
);


CREATE TABLE event_table (
    event_id SERIAL PRIMARY KEY,
    event_name VARCHAR NOT NULL,
    date TIMESTAMP,
    description VARCHAR(250),
    location VARCHAR,
    registration_head_count INTEGER
);


CREATE TABLE beneficiary_table (
    beneficiary_id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    beneficiary_since_date DATE,
    birth_date DATE,
    gender VARCHAR
);

CREATE TABLE registration_table (
    registration_id SERIAL PRIMARY KEY,
    event_id INTEGER REFERENCES event_table(event_id),
    email VARCHAR REFERENCES user_table(email),
    UNIQUE (event_id, email)
);

CREATE TABLE donation_tile_table (
    donation_tile_id SERIAL PRIMARY KEY,
    donation_title VARCHAR NOT NULL,
    description VARCHAR(250),
    target_amount DECIMAL(10, 2) NOT NULL,
    start_date DATE,
    end_date DATE,
    isActive BOOLEAN
);

-- Mock data

INSERT INTO user_table (email, password, phone_number, role, dob, name, email_notification, whatsapp_notification)
VALUES
    ('admin@gmail.com','admin','123-456-7890','ADMIN','1970-01-01','Mr. Admin', true, true),
    ('user1@yahoo.com','password1','+54 354-655-4579','MEMBER','1975-05-14','John Doe', true, false),
    ('user2@gmail.com','password2','987-654-3210','MEMBER','1991-07-12','Jane Smith', false, true),
    ('user3@gmail.com','password3','555-123-4567','MEMBER','1984-12-21','Alice Johnson', true, false),
    ('user4@gmail.com','password4','444-987-6543','MEMBER','1987-08-04','Bob Anderson', false, true),
    ('user5@gmail.com','password5','666-111-2233','MEMBER','1969-11-25','Eva Davis', true, true),
    ('user6@yahoo.com','password6','888-444-5555','MEMBER','2000-11-20','Michael Brown', false, false),
    ('user7@gatech.edu','password7','222-777-8888','MEMBER','2002-07-08','Sophia White', true, true),
    ('user8@gatech.edu','password8','999-000-1111','MEMBER','1998-01-31','Liam Miller', false, false),
    ('user9@gmail.com','password9','333-666-9999','MEMBER','1994-05-26','Olivia Moore', true, true),
    ('user10@gmail.com','password10','777-222-3333','MEMBER','1982-01-17','Lucas Martinez', false, true),
    ('user11@gatech.edu','password11','111-555-6666','MEMBER','1968-02-29','Ava Robinson', true, false),
    ('user12@gmail.com','password12','444-555-6666','MEMBER','1964-06-13','Noah Johnson', false, true),
    ('user13@yahoo.com','password13','555-444-6666','MEMBER','1989-05-15','Emma Smith', true, true),
    ('user14@gmail.com','password14','666-444-5555','MEMBER','1998-04-09','William Davis', false, false),
    ('user15@gmail.com','password15','777-444-5555','MEMBER','1992-10-30','Sophia Taylor', true, true);

INSERT INTO event_table (event_name, date, description, location, registration_head_count)
VALUES
    ('Event 1', '2024-03-15 09:00:00', 'Description for Event 1', 'Location 1', 3),
    ('Event 2', '2024-04-20 10:30:00', 'Description for Event 2', 'Location 2', 2),
    ('Event 3', '2024-05-10 13:15:00', 'Description for Event 3', 'Location 3', 3),
    ('Event 4', '2024-06-05 16:45:00', 'Description for Event 4', 'Location 4', 5),
    ('Event 5', '2024-07-15 18:00:00', 'Description for Event 5', 'Location 5', 1);

INSERT INTO beneficiary_table (name, beneficiary_since_date, birth_date, gender)
VALUES
  ('Harlow Haynes', '2020-02-29', '2000-03-15', 'Male'),
  ('Kason Rivera', '2021-04-02', '2001-04-20', 'Female'),
  ('Lillian Rosario', '2016-07-03', '2002-05-10', 'Other'),
  ('Jedidiah Daniels', '2022-03-04', '2003-06-05', 'Male'),
  ('Ember Sampson', '2019-02-20', '2004-07-15', 'Female'),
  ('Cain Brock', '2024-03-30', '2005-08-20', 'Other'),
  ('Jada McBride', '202-08-31',  '2006-09-25', 'Male'),
  ('Denver Gould', '2024-02-28', '2007-10-30', 'Female'),
  ('Violeta Flowers', '2024-08-04', '2008-11-05', 'Other'),
  ('Saul Combs', '2024-08-14','2009-12-10', 'Male'),
  ('Irene Greene', '2024-03-16','2010-01-15', 'Female'),
  ('Griffin Neal', '2024-01-22','2011-02-20', 'Other'),
  ('Talia Gomez', '2024-03-03','2012-03-25', 'Male'),
  ('Isaiah Garner', '2024-11-24','2013-04-30', 'Female'),
  ('Jacqueline Zimmerman', '2024-12-25','2014-05-05', 'Other'),
  ('Sergio Hoover', '2024-11-06','2015-06-10', 'Male'),
  ('Virginia Lynch', '2024-10-19','2016-07-15', 'Female'),
  ('Zane Callahan', '2024-09-16','2017-08-20', 'Other'),
  ('Kimber Walter', '2024-03-13','2018-09-25', 'Male'),
  ('Lochlan Clarke', '2024-08-20','2019-10-30', 'Female'),
  ('Kaitlyn Buck', '2024-04-21','2020-11-05', 'Other'),
  ('Jon Leonard', '2024-06-13','2021-12-10', 'Male'),
  ('Demi Carrillo', '2024-06-02','2022-01-15', 'Female'),
  ('Wade Ayers', '2024-01-24','2023-02-20', 'Other'),
  ('Damon Jimenez', '2024-01-23','2024-03-25', 'Male');

INSERT INTO registration_table (event_id, email)
VALUES
  (1, 'user1@yahoo.com'), -- Event 1, User 1
  (1, 'user2@gmail.com'), -- Event 1, User 2
  (1, 'user3@gmail.com'), -- Event 1, User 3

  (2, 'user4@gmail.com'), -- Event 2, User 4
  (2, 'user5@gmail.com'), -- Event 2, User 5

  (3, 'user6@yahoo.com'), -- Event 3, User 6
  (3, 'user7@gatech.edu'), -- Event 3, User 7
  (3, 'user8@gatech.edu'), -- Event 3, User 8

  (4, 'user9@gmail.com'), -- Event 4, User 9
  (4, 'user10@gmail.com'), -- Event 4, User 10
  (4, 'user11@gatech.edu'), -- Event 4, User 11
  (4, 'user12@gmail.com'), -- Event 4, User 12
  (4, 'user13@yahoo.com'), -- Event 4, User 13

  (5, 'user14@gmail.com'); -- Event 5, User 14

INSERT INTO donation_tile_table (donation_title, description, target_amount, start_date, end_date, isActive)
VALUES
	('Education Fund', 'Support education initiatives for underprivileged children.', 5000.00, '2023-12-20', '2024-05-31', true),
    ('Food Drive', 'Contribute to providing meals for the homeless.', 3000.00, '2024-01-01', '2024-06-30', true),
    ('General Donation', 'Make a general donation to support various causes.', 10000.00, '2024-03-15', '2024-06-15', true),
    ('Healthcare Support', 'Help in funding healthcare services for those in need.', 8000.00, '2024-04-15', '2024-07-31', true),
    ('Housing Support', 'Support initiatives for a housing for needy in slum aread.', 60000.00, '2024-01-20', '2024-06-20', false);


-- Retreive data

Select * from user_table;
Select * from event_table;
Select * from beneficiary_table;
Select * from registration_table;
Select * from donation_tile_table;