

DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
    id bigint NOT NULL,
    name text,
    age int,
    CONSTRAINT author_pkey PRIMARY KEY (id)
);

CREATE TABLE books (
    isbn bigint,
    title text,
    author_id bigint,
    CONSTRAINT books_pkey PRIMARY KEY (isbn),
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id)
);


INSERT INTO authors value (1,"Blah",20);