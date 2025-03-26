INSERT INTO card (id, name, arena)
VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'UFC 295', 'T-Mobile Arena'),
    ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'UFC 296', 'Madison Square Garden'),
    ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'UFC 297', 'Las Vegas Stadium');

INSERT INTO result (id, winner, bonus)
VALUES
    ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 1, true), -- Победил боец 1, с бонусом
    ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10', 2, false); -- Победил боец 2, без бонуса


INSERT INTO event (id, red_fighter_id, blue_fighter_id, result_id, card_id)
VALUES
    ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a03', 1, 2, 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
    ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a06', 3, 4, 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a10', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
