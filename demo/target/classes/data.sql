insert into course(id, name, created_date, last_updated_date) values(101, 'Commerce', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values(102, 'Engineering', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values(103, 'Computer Science', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values(104, 'Dummy 1', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values(105, 'Dummy 2', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values(106, 'Dummy 3', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values(107, 'Dummy 4', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values(108, 'Dummy 5', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values(109, 'Dummy 6', sysdate(), sysdate());

insert into passport(id, passport_number) values(201,'H102217');
insert into passport(id, passport_number) values(202,'M938387');
insert into passport(id, passport_number) values(203,'E827363');

insert into student(id, name, passport_id) values(301,'Matt',201);
insert into student(id, name, passport_id) values(302,'Kate',202);
insert into student(id, name, passport_id) values(303,'John',203);

insert into review(id, rating, description, course_id) values(401,5,'Good', 101);
insert into review(id, rating, description, course_id) values(402,5,'Awesome', 102);
insert into review(id, rating, description, course_id) values(403,4,'Nice', 102);

insert into student_course(student_id, course_id) values(301,101);
insert into student_course(student_id, course_id) values(302,101);
insert into student_course(student_id, course_id) values(303,101);
insert into student_course(student_id, course_id) values(301,103);