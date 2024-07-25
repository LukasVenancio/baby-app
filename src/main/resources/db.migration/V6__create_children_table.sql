CREATE TABLE IF NOT EXISTS children (
    id UUID PRIMARY KEY,
    birth_date TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    gender SMALLINT NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_children_user FOREIGN KEY (user_id) REFERENCES users(id)
)