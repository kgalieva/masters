CREATE TABLE users (
  id bigint NOT NULL,
  city character varying(255),
  email character varying(255),
  name character varying(255),
  password character varying(255),
  phone character varying(255),
  salt character varying(255)
);
ALTER TABLE public.users OWNER TO postgres;

INSERT INTO users (id, city, email, name, password, phone, salt) VALUES (1, 'Казань', 'alex@gmail.com',  'Александр Семенов', 'alex', '2121212', 'alex');
ALTER TABLE ONLY users
ADD CONSTRAINT users_pkey PRIMARY KEY (id);
REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-03-29 06:26:46 MSK

--
-- PostgreSQL database dump complete
--

