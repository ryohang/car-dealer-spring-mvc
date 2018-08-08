--
-- Table: email_actions.
--
CREATE SEQUENCE email_action_id_seq;
CREATE TABLE "email_actions" (
  id bigint NOT NULL DEFAULT NEXTVAL('email_action_id_seq'),
  create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_at TIMESTAMP DEFAULT NULL,
  category integer DEFAULT NULL,
  user_id bigint NOT NULL,
  CONSTRAINT fk_email_action_user
    FOREIGN KEY (user_id)
    REFERENCES Users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  PRIMARY KEY (id)
);

ALTER SEQUENCE email_action_id_seq OWNED BY email_actions.id;
CREATE INDEX "fk_email_action_user_idx" ON email_actions (user_id asc );
