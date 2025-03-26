CREATE TABLE IF NOT EXISTS card (
                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                    name VARCHAR(255) NOT NULL,
                                    arena VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS result (
                                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                      winner INT NOT NULL,
                                      bonus BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS event (
                                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                     red_fighter_id INT NOT NULL,
                                     blue_fighter_id INT NOT NULL,
                                     result_id UUID REFERENCES result(id),
                                     card_id UUID REFERENCES card(id) ON DELETE CASCADE
);