INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, autoapprove)
values ('postman',
        '$2a$10$KCroi.THbmXdKXOgBud1zOzdmHrfpNxSytd/o5ZQLhCTzFXib1p66',
        'any'
        'password,authorization_code,refresh_token',
        true);
INSERT INTO oauth_client_details (client_id, client_secret, authorized_grant_types, autoapprove)
values ('app',
        '$2a$10$1vUvCP9fzPeXIRzoOaPROuIRvq2nrh7iauWLIa371qZSmaP0p6ave',
        'any'
        'implicit',
        true);