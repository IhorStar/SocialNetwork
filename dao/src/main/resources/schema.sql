CREATE TABLE "user"
(
  user_id integer NOT NULL, -- Primary key
  name character varying(100) NOT NULL,
  password character varying(50) NOT NULL,
  email character varying(100) NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT pk_user_id PRIMARY KEY (user_id),
  CONSTRAINT unique_email UNIQUE (email),
  CONSTRAINT unique_login UNIQUE (name),
  CONSTRAINT unique_user_id UNIQUE (user_id)
);

CREATE TABLE news
(
  news_id integer NOT NULL,
  description text NOT NULL,
  date date NOT NULL,
  "time" time(6) with time zone NOT NULL,
  "user" integer NOT NULL,
  CONSTRAINT pk_news_id PRIMARY KEY (news_id),
  CONSTRAINT "user" FOREIGN KEY ("user")
  REFERENCES "user" (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT unique_news_id UNIQUE (news_id)
);

CREATE TABLE comment
(
  comment_id integer NOT NULL,
  text text NOT NULL,
  date date NOT NULL,
  "time" time(6) with time zone NOT NULL,
  news integer NOT NULL,
  "user" integer NOT NULL,
  CONSTRAINT comment_id PRIMARY KEY (comment_id),
  CONSTRAINT news FOREIGN KEY (news)
  REFERENCES news (news_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "user" FOREIGN KEY ("user")
  REFERENCES "user" (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT unique_comment_id UNIQUE (comment_id)
);

CREATE TABLE relation
(
  relation_id integer NOT NULL,
  user1 integer NOT NULL,
  user2 integer NOT NULL,
  relation_type integer NOT NULL,
  CONSTRAINT relation_id PRIMARY KEY (relation_id),
  CONSTRAINT relation_type FOREIGN KEY (relation_type)
  REFERENCES relation_type (relation_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user1 FOREIGN KEY (user1)
  REFERENCES "user" (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user2 FOREIGN KEY (user2)
  REFERENCES "user" (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT unique_relation_id UNIQUE (relation_id)
);

CREATE TABLE relation_type
(
  relation_type_id integer NOT NULL,
  name character varying NOT NULL,
  CONSTRAINT relation_type_id PRIMARY KEY (relation_type_id),
  CONSTRAINT unique_name UNIQUE (name),
  CONSTRAINT unique_relation_type_id UNIQUE (relation_type_id)
);