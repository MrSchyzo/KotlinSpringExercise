SET foreign_key_checks = 0;
insert into task (`name`, description, status)
values
    ('task', 'description', 'CREATED'),
    ('task', 'description', 'CREATED'),
    ('task', 'description', 'CREATED');

insert into `user` ()
values (), (), (), ();

insert into course_like (task_id, user_id)
values
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 3),
    (2, 4),
    (3, 1),
    (3, 3);
SET foreign_key_checks = 1;