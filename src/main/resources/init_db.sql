GRANT ALL ON SCHEMA public TO federation_agri_user;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO federation_agri_user;

GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO federation_agri_user;

GRANT ALL ON SCHEMA public TO federation_agri_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO federation_agri_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO federation_agri_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL ON TABLES TO cfederation_agri_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL ON SEQUENCES TO federation_agri_user;

GRANT USAGE, CREATE
    ON SCHEMA public TO federation_agri_user;
GRANT ALL PRIVILEGES ON SCHEMA public TO federation_agri_user;