-- Ruoli
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

-- Utenti
INSERT INTO users (id, username, password) VALUES (1, 'admin', '$2a$12$NgSVEftG8/JQ2bNjhEy0te0q1fV.Re3lPq.AGPWQ4O53XZWw0Pu2C');
INSERT INTO users (id, username, password) VALUES (2, 'operator', '$2a$12$JhG8syLUbtpIviRExrcugeArh93lFGZr1dEIo4H/9hMNOE9iSfUnu'); 
INSERT INTO users (id, username, password) VALUES (3, 'operator2', '$2a$12$AEMZ5huEB3crRvpbz2FrZe6a/cr9io77JYAUkrjchx7Zjp27WGJXq');

-- Associazioni Utenti-Ruoli
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);