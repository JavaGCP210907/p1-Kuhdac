INSERT INTO roles ("role")
VALUES ('Manager'),
	   ('Employee');
	  
INSERT INTO status ("status")
VALUES ('APPROVED'),
	   ('DENIED'),
	   ('PENDING');
	  
INSERT INTO types ("type")
VALUES ('Lodging'),
	   ('Meals'),
	   ('Travel'),
	   ('Fuel'),
	   ('Misc');

INSERT INTO users ("email", "first_name", "last_name", "password", "username", role_id)
VALUES ('gbennings@sus.com', 'George', 'Bennings', 'password', 'gbennings', 2),
	   ('rmacready@arctic.com', 'R.J.', 'MacReady', 'password', 'rmacready', 1),
	   ('vnorris2@netscape.com', 'Vance', 'Norris', 'password', 'vnorris', 2),
	   ('kchilds@artic.com', 'Keith', 'Childs', 'password', 'kchilds', 2),
	   ('dblair@outlook.com', 'Doctor', 'Blair', 'password', 'dblair', 2),
	   ('rclark@gmail.com', 'Richard', 'Clark', 'password', 'rclark', 2);
	   

SELECT * FROM roles;
SELECT * FROM TYPES;
SELECT * FROM status;
SELECT * FROM reimbursements;
SELECT * FROM users;



DROP TABLE TYPES cascade;
DROP TABLE reimbursements CASCADE;
DROP TABLE users CASCADE;
DROP TABLE status CASCADE;
DROP TABLE roles CASCADE;