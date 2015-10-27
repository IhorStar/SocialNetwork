INSERT INTO "user"(user_id, name, password, email, role_id) VALUES (1, 'Ihor', 'qwerty', 'ihor@gmail.com', 2);
INSERT INTO "user"(user_id, name, password, email, role_id) VALUES (2, 'Anton', 'qwerty', 'anton@gmail.com', 2);
INSERT INTO "user"(user_id, name, password, email, role_id) VALUES (3, 'Alex', 'qwerty', 'alex@gmail.com', 2);


INSERT INTO news(
  news_id, description, date, "time", "user")
VALUES (1, 'Java is a general-purpose computer programming language that is concurrent,
  class-based, object-oriented, and specifically
  designed to have as few implementation dependencies as possible.', '27/10/2015', '22:26', 1);

INSERT INTO news(
  news_id, description, date, "time", "user")
VALUES (2, 'James Gosling, Mike Sheridan, and Patrick Naughton initiated the Java
  language project in June 1991.', '27/10/2015', '22:28', 1);

INSERT INTO news(
  news_id, description, date, "time", "user")
VALUES (3, 'Sun Microsystems released the first public implementation as Java 1.0 in 1995.', '27/10/2015', '22:30', 2);


INSERT INTO comment(
  comment_id, text, date, "time", news, "user")
VALUES (1, 'This is the comment', '27/10/2015', '22:33', 1, 1);

INSERT INTO comment(
  comment_id, text, date, "time", news, "user")
VALUES (2, 'This is the comment 2', '27/10/2015', '22:35', 1, 3);

INSERT INTO comment(
  comment_id, text, date, "time", news, "user")
VALUES (3, 'This is the comment 3', '27/10/2015', '22:37', 1, 2);


INSERT INTO relation_type(
  relation_type_id, name)
VALUES (1, 'friend');


INSERT INTO relation(
  relation_id, user1, user2, relation_type)
VALUES (1, 1, 2, 1);

INSERT INTO relation(
  relation_id, user1, user2, relation_type)
VALUES (2, 1, 3, 1);

INSERT INTO relation(
  relation_id, user1, user2, relation_type)
VALUES (3, 2, 3, 1);