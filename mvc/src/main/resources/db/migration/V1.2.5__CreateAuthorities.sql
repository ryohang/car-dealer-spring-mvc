CREATE SEQUENCE authorities_id_seq;

CREATE TABLE "authorities" (
  "id" bigint NOT NULL DEFAULT NEXTVAL('authorities_id_seq'),
  "authority" character varying(255) NOT NULL,
  "user_id" bigint NOT NULL,
  PRIMARY KEY ("id")
);

ALTER SEQUENCE authorities_id_seq OWNED BY authorities.id;