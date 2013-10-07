CREATE TABLE credentials
(
  id bigserial NOT NULL,
  login character varying(50) NOT NULL,
  password character varying(50) NOT NULL,
  CONSTRAINT credentials_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE credentials
  OWNER TO practice;


INSERT INTO credentials (id, login, password) VALUES (1, 'masha', '123');
INSERT INTO credentials (id, login, password) VALUES (2, 'tanya', '456');
INSERT INTO credentials (id, login, password) VALUES (3, 'victor', '789');


