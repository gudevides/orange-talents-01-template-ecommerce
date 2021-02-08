INSERT INTO USUARIO(email, senha) VALUES('dev@email.com', '$2a$10$Ll9lJ7PRfgRhhAtCEeSytuLR/FFHBhNSTM/IL66B3XoEMU/VBffeq');

INSERT INTO PERFIL(id, nome) VALUES (1, 'ROLE_ADMIN');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES (1, 1);