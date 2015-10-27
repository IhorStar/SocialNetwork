CREATE OR REPLACE FUNCTION insert_user(
    user_id integer,
    name character varying,
    password character varying,
    email character varying,
    role_id integer)
  RETURNS void AS
$BODY$begin
insert into "user" values(user_id, name, password, email, role_id);
end$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION insert_user(integer, character varying, character varying, character varying, integer)
  OWNER TO postgres;



CREATE OR REPLACE FUNCTION insert_news(
    news_id integer,
    description text,
    date date,
    "time" time with time zone,
    "user" integer)
  RETURNS void AS
$BODY$begin
insert into news values(news_id, description, date, time, user);
end$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION insert_news(integer, text, date, time with time zone, integer)
  OWNER TO postgres;





CREATE OR REPLACE FUNCTION insert_comment(
    comment_id integer,
    text text,
    date date,
    news integer,
    "user" integer)
  RETURNS void AS
$BODY$begin
insert into comment values(comment_id, text, date, time, news, user);
end
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION insert_comment(integer, text, date, integer, integer)
  OWNER TO postgres;



CREATE OR REPLACE FUNCTION insert_relation(
    relation_id integer,
    user1 integer,
    user2 integer,
    relation_type integer)
  RETURNS void AS
$BODY$begin
insert into relation values(relation_id, user1, user2, relation_type);
end
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION insert_relation(integer, integer, integer, integer)
  OWNER TO postgres;




CREATE OR REPLACE FUNCTION insert_relation_type(
    relation_type_id integer,
    name character varying)
  RETURNS void AS
$BODY$begin
insert into relation_type values(relation_type_id, name);
end
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION insert_relation_type(integer, character varying)
  OWNER TO postgres;
