CREATE TABLE IF NOT EXISTS childbirths (
    id UUID PRIMARY KEY,
    childbirth_type SMALLINT NOT NULL,
    weight REAL NOT NULL,
    height REAL NOT NULL,
    intercurrence VARCHAR(255) NOT NULL,
    apgar_first_minute INTEGER NOT NULL,
    apgar_fifth_minute INTEGER NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_childbirth_user FOREIGN KEY (user_id) REFERENCES users(id)
)