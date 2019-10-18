SET foreign_key_checks = 0;
insert into task (`id`, `name`, description, status)
values
    (1, 'task', 'description', 'CREATED'),
    (2, 'task', 'description', 'CREATED'),
    (3, 'task', 'description', 'CREATED');

insert into `user` (`id`)
values (1), (2), (3), (4);

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