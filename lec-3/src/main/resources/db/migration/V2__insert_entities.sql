INSERT INTO books (author, name) VALUES
('Клиффорд Саймак', 'Заповедник гоблинов'),
('Фёдор Достоевский', 'Преступление и наказание'),
('William Shakespeare', 'Romeo and Juliet'),
('Кард Маркс', 'Капитал'),
('Лев Толстой', 'Война и мир'),
('Эмануэль Арсан', 'Эмануэль');

INSERT INTO readers (name) VALUES
('Michael Miller'),
('Ryosuke Yamada'),
('Keiko Sato');

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