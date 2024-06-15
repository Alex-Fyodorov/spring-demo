INSERT INTO books (author, name) VALUES
('Клиффорд Саймак', 'Заповедник гоблинов'),
('Фёдор Достоевский', 'Преступление и наказание'),
('William Shakespeare', 'Romeo and Juliet'),
('Кард Маркс', 'Капитал'),
('Лев Толстой', 'Война и мир'),
('Эмануэль Арсан', 'Эмануэль');

INSERT INTO readers (name, password) VALUES
('Michael Miller', '$2a$12$L3bFnBI5AUIJUjk3y7FYROCbtaSsqtCufb5xtof9DVOwe6968VgPa'),
('Ryosuke Yamada', '$2a$12$NnGbs88zdSPhBChQboBMfu7PyK08irFpkigGqEHyxb4xJOZHAQvfq'),
('Keiko Sato', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

INSERT INTO roles (name) VALUES
('ROLE_READER'), ('ROLE_ADMIN');

INSERT INTO readers_roles (reader_id, role_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(3, 2);

INSERT INTO issues (book_id, reader_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(1, 3),
(2, 1),
(3, 2),
(4, 3),
(5, 1),
(1, 2),
(2, 3),
(3, 1),
(4, 2),
(5, 3);