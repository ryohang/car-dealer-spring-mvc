ALTER TABLE images ADD COLUMN "extension" character varying(32) DEFAULT NULL;
ALTER TABLE images ADD COLUMN "uuid" character varying(255) DEFAULT NULL;