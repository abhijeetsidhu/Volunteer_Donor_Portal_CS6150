CREATE TABLE user_table (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR UNIQUE NOT NULL,
	password VARCHAR NOT NULL,
    phone_number VARCHAR,
    role VARCHAR,
    name VARCHAR
);

CREATE TABLE event_table (
    event_id SERIAL PRIMARY KEY,
    event_name VARCHAR NOT NULL,
    date DATE,
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


-- Mock data


INSERT INTO user_table (email, password, phone_number, role, name)
VALUES
  ('admin@gmail.com', 'admin', '123-456-7890', 'admin', 'Mr. Admin'),
  ('user1@yahoo.com', 'password1', '+54 354-655-4579', 'volunteer', 'John Doe'),
  ('user2@gmail.com', 'password2', '987-654-3210', 'donor', 'Jane Smith'),
  ('user3@gmail.com', 'password3', '555-123-4567', 'volunteer', 'Alice Johnson'),
  ('user4@gmail.com', 'password4', '444-987-6543', 'donor', 'Bob Anderson'),
  ('user5@gmail.com', 'password5', '666-111-2233', 'volunteer', 'Eva Davis'),
  ('user6@yahoo.com', 'password6', '888-444-5555', 'donor', 'Michael Brown'),
  ('user7@gatech.edu', 'password7', '222-777-8888', 'volunteer', 'Sophia White'),
  ('user8@gatech.edu', 'password8', '999-000-1111', 'donor', 'Liam Miller'),
  ('user9@gmail.com', 'password9', '333-666-9999', 'volunteer', 'Olivia Moore'),
  ('user10@gmail.com', 'password10', '777-222-3333', 'donor', 'Lucas Martinez'),
  ('user11@gatech.edu', 'password11', '111-555-6666', 'volunteer', 'Ava Robinson'),
  ('user12@gmail.com', 'password12', '444-555-6666', 'donor', 'Noah Johnson'),
  ('user13@yahoo.com', 'password13', '555-444-6666', 'volunteer', 'Emma Smith'),
  ('user14@gmail.com', 'password14', '666-444-5555', 'donor', 'William Davis'),
  ('user15@gmail.com', 'password15', '777-444-5555', 'volunteer', 'Sophia Taylor');

INSERT INTO event_table (event_name, date, description, location, registration_head_count)
VALUES
  ('Event 1', '2024-03-15', 'Description for Event 1', 'Location 1', 3),
  ('Event 2', '2024-04-20', 'Description for Event 2', 'Location 2', 2),
  ('Event 3', '2024-05-10', 'Description for Event 3', 'Location 3', 3),
  ('Event 4', '2024-06-05', 'Description for Event 4', 'Location 4', 5),
  ('Event 5', '2024-07-15', 'Description for Event 5', 'Location 5', 1);

INSERT INTO beneficiary_table (name, beneficiary_since_date, birth_date, gender)
VALUES
  ('Beneficiary 1', '2020-02-29', '2000-03-15', 'Male'),
  ('Beneficiary 2', '2021-04-02', '2001-04-20', 'Female'),
  ('Beneficiary 3', '2016-07-03', '2002-05-10', 'Other'),
  ('Beneficiary 4', '2022-03-04', '2003-06-05', 'Male'),
  ('Beneficiary 5', '2019-02-20', '2004-07-15', 'Female'),
  ('Beneficiary 6', '2024-03-30', '2005-08-20', 'Other'),
  ('Beneficiary 7', '202-08-31', '2006-09-25', 'Male'),
  ('Beneficiary 8', '2024-02-28', '2007-10-30', 'Female'),
  ('Beneficiary 9', '2024-08-04', '2008-11-05', 'Other'),
  ('Beneficiary 10', '2024-08-14', '2009-12-10', 'Male'),
  ('Beneficiary 11', '2024-03-16', '2010-01-15', 'Female'),
  ('Beneficiary 12', '2024-01-22', '2011-02-20', 'Other'),
  ('Beneficiary 13', '2024-03-03', '2012-03-25', 'Male'),
  ('Beneficiary 14', '2024-11-24', '2013-04-30', 'Female'),
  ('Beneficiary 15', '2024-12-25', '2014-05-05', 'Other'),
  ('Beneficiary 16', '2024-11-06', '2015-06-10', 'Male'),
  ('Beneficiary 17', '2024-10-19', '2016-07-15', 'Female'),
  ('Beneficiary 18', '2024-09-16', '2017-08-20', 'Other'),
  ('Beneficiary 19', '2024-03-13', '2018-09-25', 'Male'),
  ('Beneficiary 20', '2024-08-20', '2019-10-30', 'Female'),
  ('Beneficiary 21', '2024-04-21', '2020-11-05', 'Other'),
  ('Beneficiary 22', '2024-06-13', '2021-12-10', 'Male'),
  ('Beneficiary 23', '2024-06-02', '2022-01-15', 'Female'),
  ('Beneficiary 24', '2024-01-24', '2023-02-20', 'Other'),
  ('Beneficiary 25', '2024-01-23', '2024-03-25', 'Male');

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


-- Retrieve data
Select * from user_table;
Select * from event_table;
Select * from beneficiary_table;
Select * from registration_table;
