--DROP TABLE IF EXISTS BookStore;
--DROP TABLE IF EXISTS Book;
 
CREATE TABLE IF NOT EXISTS Book (
  isbn VARCHAR(20)  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  author VARCHAR(250) NOT NULL,
  price INT NOT NULL,
  qauntity INT DEFAULT 1
);

INSERT INTO Book (isbn, title, author,price,qauntity) VALUES
  ('000BOOK001', 'Artemis', 'Andy Weir',750,10),
  ('000BOOK002', 'The Martian', 'Andy Weir',600,5),
  ('000BOOK003', 'Raavan', 'Amish',500,15),
  ('000BOOK004', 'nesciunt quas', 'Newt',400,3),
  ('000BOOK005', 'Inferno', 'Dan',600,1),
  ('000BOOK006', 'possimus', 'Newt',300,4);

CREATE INDEX isbn ON Book(isbn);
CREATE INDEX title ON Book(title);
CREATE INDEX author ON Book(author);
