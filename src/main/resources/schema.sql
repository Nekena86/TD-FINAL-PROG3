CREATE TYPE genre_enum AS ENUM ('FEMININ', 'MASCULIN');

/*CREATE TYPE poste_enum AS ENUM (
    'PRESIDENT',
    'PRESIDENT_ADJOINT',
    'TRESORIER',
    'SECRETAIRE',
    'MEMBRE_CONFIRME',
    'MEMBRE_JUNIOR'
    );*/

CREATE TYPE mode_paiement_enum AS ENUM ('ESPECE', 'VIREMENT', 'MOBILE_MONEY');

CREATE TYPE type_compte_enum AS ENUM ('CAISSE', 'BANCAIRE', 'MOBILE_MONEY');

CREATE TYPE banque_enum AS ENUM ('BRED','MCB','BMOI','BOA','BGFI','AFG','ACCES_BANQUE','BAOBAB','SIPEM');

CREATE TYPE mobile_money_enum AS ENUM ('ORANGE_MONEY','MVOLA','AIRTEL_MONEY');

CREATE TYPE type_cotisation_enum AS ENUM ('MENSUELLE','ANNUELLE','PONCTUELLE');

CREATE TYPE type_activite_enum AS ENUM ('ASSEMBLEE_GENERALE','FORMATION','EXCEPTIONNELLE');

CREATE TYPE niveau_activite_enum AS ENUM ('FEDERATION','COLLECTIVITE');

CREATE TABLE collectivite (
                              id SERIAL PRIMARY KEY,
                              nom VARCHAR(100) UNIQUE NOT NULL,
                              ville VARCHAR(100) NOT NULL,
                              specialite VARCHAR(100) NOT NULL,
                              date_creation DATE NOT NULL,
                              autorisation_federation BOOLEAN DEFAULT FALSE
);

CREATE TABLE membre (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(100),
                        prenom VARCHAR(100),
                        date_naissance DATE,
                        genre genre_enum,
                        adresse TEXT,
                        metier VARCHAR(100),
                        telephone VARCHAR(20),
                        email VARCHAR(150) UNIQUE,
                        date_adhesion DATE,
                        actif BOOLEAN DEFAULT TRUE
);

CREATE TABLE historique_affectation (
                                        id SERIAL PRIMARY KEY,
                                        membre_id INT REFERENCES membre(id),
                                        collectivite_id INT REFERENCES collectivite(id),
                                        date_debut DATE,
                                        date_fin DATE
);

CREATE TABLE mandat_poste (
                              id SERIAL PRIMARY KEY,
                              membre_id INT REFERENCES membre(id),
                              collectivite_id INT REFERENCES collectivite(id),
                              poste poste_enum,
                              date_debut DATE,
                              date_fin DATE
);

CREATE TABLE parrainage (
                            id SERIAL PRIMARY KEY,
                            membre_parrain_id INT REFERENCES membre(id),
                            collectivite_id INT REFERENCES collectivite(id),
                            relation VARCHAR(50),
                            date_parrainage DATE
);

CREATE TABLE adhesion (
                          id SERIAL PRIMARY KEY,
                          membre_id INT REFERENCES membre(id),
                          collectivite_id INT REFERENCES collectivite(id),
                          frais_adhesion INT DEFAULT 50000,
                          cotisation_annuelle INT,
                          montant_total_paye INT,
                          date_paiement DATE
);

CREATE TABLE cotisation (
                            id SERIAL PRIMARY KEY,
                            membre_id INT REFERENCES membre(id),
                            collectivite_id INT REFERENCES collectivite(id),
                            type type_cotisation_enum,
                            montant INT,
                            date_encaissement DATE,
                            mode_paiement mode_paiement_enum
);

CREATE TABLE compte (
                        id SERIAL PRIMARY KEY,
                        collectivite_id INT REFERENCES collectivite(id),
                        type type_compte_enum,
                        solde NUMERIC DEFAULT 0
);

CREATE TABLE compte_bancaire (
                                 id SERIAL PRIMARY KEY,
                                 compte_id INT REFERENCES compte(id),
                                 titulaire VARCHAR(150),
                                 banque banque_enum,
                                 numero_compte VARCHAR(23) UNIQUE
);

CREATE TABLE compte_mobile_money (
                                     id SERIAL PRIMARY KEY,
                                     compte_id INT REFERENCES compte(id),
                                     titulaire VARCHAR(150),
                                     service mobile_money_enum,
                                     numero_telephone VARCHAR(20) UNIQUE
);

CREATE TABLE transaction_compte (
                                    id SERIAL PRIMARY KEY,
                                    compte_id INT REFERENCES compte(id),
                                    montant INT,
                                    type VARCHAR(50),
                                    date_transaction DATE
);

CREATE TABLE activite (
                          id SERIAL PRIMARY KEY,
                          collectivite_id INT REFERENCES collectivite(id),
                          type type_activite_enum,
                          niveau niveau_activite_enum,
                          description TEXT,
                          date_activite DATE,
                          obligatoire BOOLEAN DEFAULT FALSE
);

CREATE TABLE presence (
                          id SERIAL PRIMARY KEY,
                          activite_id INT REFERENCES activite(id),
                          membre_id INT REFERENCES membre(id),
                          present BOOLEAN DEFAULT FALSE,
                          excuse BOOLEAN DEFAULT FALSE,
                          motif TEXT
);

CREATE TABLE reversement_federation (
                                        id SERIAL PRIMARY KEY,
                                        collectivite_id INT REFERENCES collectivite(id),
                                        montant INT,
                                        pourcentage NUMERIC,
                                        date_reversement DATE
);

CREATE TABLE rapport_collectivite (
                                      id SERIAL PRIMARY KEY,
                                      collectivite_id INT REFERENCES collectivite(id),
                                      periode_debut DATE,
                                      periode_fin DATE,
                                      taux_assiduite NUMERIC,
                                      nombre_membres INT
);

CREATE TABLE rapport_federation (
                                    id SERIAL PRIMARY KEY,
                                    collectivite_id INT REFERENCES collectivite(id),
                                    periode_debut DATE,
                                    periode_fin DATE,
                                    taux_assiduite_global NUMERIC,
                                    taux_cotisation_global NUMERIC,
                                    nouveaux_adhérents INT
);