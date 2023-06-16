CREATE TABLE roles(
	role_id serial PRIMARY KEY,
	role_title TEXT UNIQUE NOT null
);

CREATE TABLE users(
	user_id serial PRIMARY KEY,
	first_name TEXT,
	last_name TEXT,
	username TEXT UNIQUE NOT NULL,
	PASSWORD TEXT NOT NULL,
	role_id_fk int REFERENCES roles(role_id)
);

CREATE TABLE statuses(
	status_id serial PRIMARY KEY,
	status_name TEXT UNIQUE NOT null
);

CREATE TABLE reimbursements(
	reimb_id serial,
	amount int NOT NULL,
	description TEXT NOT NULL,
	user_id_fk int REFERENCES users(user_id),
	status_id_fk int REFERENCES statuses(status_id)
);