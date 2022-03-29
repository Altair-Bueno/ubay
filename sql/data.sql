INSERT INTO
    client (name, last_name, address, city, birth_date, gender)
VALUES
    ('Pepe', 'Botella', 'Calle Palomo 2', 'Toledo', '12-25-1900', 'male');

INSERT INTO
    client (name, last_name, address, city, birth_date, gender)
VALUES
    ('Toshiba', 'null', 'Calle Vallecillo 34', 'Segovia', '04-12-1903', 'female');

INSERT INTO
    category (name, description)
VALUES
    ('Videojuegos', 'Como su nombre indica, aqui se venden videojuegos');

INSERT INTO
    product (title, description, out_price, images, close_date, publish_date, vendor_id, category_id)
VALUES
    (
        'Play 2 como nueva',
        'PS2 Impecable con 2 juegos',
        20.0,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/PS2-Versions.png/1920px-PS2-Versions.png',
        null,
        '03-28-2022',
        1,
        1
    );

INSERT INTO
    product (title, description, out_price, images, close_date, publish_date, vendor_id, category_id)
VALUES
    (
        'LEGO Batman PS3',
        'El batman pero de Lego',
        10.0,
        'https://m.media-amazon.com/images/I/919s2f-r0uL._SL1500_.jpg',
        '03-27-2022',
        '03-25-2022',
        2,
        1
    );