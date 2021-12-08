CREATE DATABASE ProjectZero;

CREATE TABLE Users (
	user_id INT NOT NULL AUTO_INCREMENT,
	user_first_name VARCHAR(200),
    user_last_name VARCHAR(200),
    user_email_address VARCHAR(255),
    user_password VARCHAR(255),
	PRIMARY KEY (user_id)
);

CREATE TABLE Employees (
	employee_id INT NOT NULL AUTO_INCREMENT,
	employee_first_name VARCHAR(200),
	employee_last_name VARCHAR(200),
	employee_email_address VARCHAR(255),
	employee_phone_number VARCHAR(12),
	PRIMARY KEY (employee_id)
);

CREATE TABLE Notes (
	note_id INT NOT NULL AUTO_INCREMENT,
	note_employee INT NOT NULL,
	note_text VARCHAR(),
	note_created DATETIME,
	FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
	PRIMARY KEY (note_id)
);