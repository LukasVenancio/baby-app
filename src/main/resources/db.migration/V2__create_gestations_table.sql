CREATE TABLE IF NOT EXISTS gestations (
     id UUID PRIMARY KEY,
     last_menstruation TIMESTAMP NOT NULL,
     probable_birth_date TIMESTAMP NOT NULL,
     pregnancy_number INT NOT NULL,
     had_abortion BOOLEAN NOT NULL,
     abortion_number INT NOT NULL,
     gestation_type SMALLINT NOT NULL,
     baby_name VARCHAR(255) NOT NULL,
     baby_gender SMALLINT NOT NULL,
     user_id UUID NOT NULL,
     CONSTRAINT fk_gestation_user FOREIGN KEY (user_id) REFERENCES users(id)
   );