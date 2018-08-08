Alter TABLE authorities add CONSTRAINT fk_authorities_users
  FOREIGN KEY (user_id)
  REFERENCES Users (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;