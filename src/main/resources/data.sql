INSERT INTO USER(name, email, password) VALUES('Student', 'any@email.com', '123456');

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programing');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPIC(title, message, create_date, status, author_id, course_id) VALUES('Question', 'error', '2019-05-05 18:00:00', 'NOT_ANSWER', 1, 1);
INSERT INTO TOPIC(title, message, create_date, status, author_id, course_id) VALUES('Question 2', 'Test', '2019-05-05 19:00:00', 'NOT_ANSWER', 1, 1);
INSERT INTO TOPIC(title, message, create_date, status, author_id, course_id) VALUES('Question 3', 'Tag HTML', '2019-05-05 20:00:00', 'NOT_ANSWER', 1, 2);