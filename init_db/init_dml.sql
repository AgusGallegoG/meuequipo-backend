INSERT INTO `meuequipo-local`.user (name, surnames, email, password, rol, is_active,
                                    created_by, created_date,
                                    last_modified_by, last_modified_date)
VALUES ('Super',
        'Admin',
        'admin@example.com',
        '$2y$10$Ze91ZmjhD1hmUYGMST5uFeJBtvSu4QPUfWtjlJ4CWeQCy69ROdldq',
        'ADMIN',
        true,
        'system',
        '2025-06-25T00:00:00',
        NULL,
        NULL);

# Creación de usuario base con credenciais: admin@example.com; 123admin