CREATE TABLE IF NOT EXISTS families (
    id UUID PRIMARY KEY,
    family_type SMALLINT NOT NULL,
    income_type SMALLINT NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_children_user FOREIGN KEY (user_id) REFERENCES users(id)
)