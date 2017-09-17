INSERT INTO bookcategory (bookcategory_id, bookcategory_name) VALUES (0, 'SCIFI'); 
INSERT INTO bookcategory (bookcategory_id, bookcategory_name) VALUES (1, 'LITERATURE');
INSERT INTO bookcategory (bookcategory_id, bookcategory_name) VALUES (2, 'HISTORICAL');
INSERT INTO bookcategory (bookcategory_id, bookcategory_name) VALUES (3, 'PHILOSOPHY');

INSERT INTO book (book_isbn, book_author, book_title, book_bookcategory_id, book_price, book_number_of_pages) VALUES ('978-0441172719', 'Frank Herbert', 'Dune', 0, 3500, 896);
INSERT INTO book (book_isbn, book_author, book_title, book_bookcategory_id, book_price, book_number_of_pages) VALUES ('978-5955000831', 'Paulo Coelho', 'Veronika Decides to Die', 1, 2300, 240);
INSERT INTO book (book_isbn, book_author, book_title, book_bookcategory_id, book_price, book_number_of_pages) VALUES ('978-0684824710', 'Daniel C. Dennett', E'Darwin\'s dangerous idea', 3, 4500, 586);
