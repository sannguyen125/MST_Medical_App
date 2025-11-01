create database medical_app;
USE medical_app;


create table roles (
	role_id INT primary key auto_increment,
    role_name varchar(50) unique not null
);

create table users (
	user_id INT primary key auto_increment,
    username varchar(50) unique not null,
    password_hash varchar(50) not null,
    full_name varchar(50),
    email varchar(50) unique,
    phone_number varchar(20),
    role_id int,
    foreign key (role_id) references roles(role_id)
);

create table appointments(
	apponitment_id int primary key auto_increment,
    patient_id int not null,
    doctor_id int not null,
    apponitment_time datetime not null,
    status varchar(50) default 'Đã lên lịch',
    notes text,
    foreign key (patient_id) references users(user_id),
    foreign key (doctor_id) references users(user_id)
);

create table permissions(
	permission_id int primary key auto_increment,
    permission_name varchar(100) unique not null
);

CREATE TABLE role_permissions (
    role_id INT,
    permission_id INT,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id),
    FOREIGN KEY (permission_id) REFERENCES permissions(permission_id)
);

-- role_id
insert into roles(role_id, role_name) values (1, 'Patient'), (2, 'Doctor'), (3, 'Admin');

-- Các quyền
insert into permissions(permission_id, permission_name) values
(1, 'UPDATE_PASSWORD'),
(2, 'VIEW_PERSIONAL_PROFILE'),
(3, 'BOOK_APPOINTMENT'),
(4, 'VIEW_APOINTMENT'),
(5, 'VIEW_PATIENT_LIST'),
(6, 'MANAGE_ACCOUNTS'),
(7, 'MANAGE_PERMISSIONS'),
(8, 'VIEW_SYSTEM_STATS'),
(9, 'VIEW_DOCTOR_LIST');

-- Gán quyền
insert into role_permissions(role_id, permission_id) values

-- Patient (role_id = 1)
(1, 1), -- update password
(1, 2), -- view profile 
(1, 3), -- book appointment

-- Doctor (role_id = 2)
(2, 1), -- update password 
(2, 2), -- view profile 
(2, 4), -- view appointment
(2, 5), -- view patient list

-- Admin (role_id = 3)

(3, 1), -- update password
(3, 2), -- view profile 
(3, 3), -- book appointment
(3, 4), -- view appointment
(3, 5), -- view patient list
(3, 6), -- mange accounts
(3, 7), -- mange permision
(3, 8), -- view system_stats
(3, 9); -- vew doctor list

-- ...

Use medical_app;
INSERT INTO users (username, password_hash, full_name, email, role_id)
VALUES
('admin', '123', 'Admin User', 'admin@mail.com', 3),
('doctor1', '123', 'Dr. John', 'john@mail.com', 2),
('patient1', '123', 'Henry', 'henry@mail.com', 1);

Use medical_app;
select * from users;




