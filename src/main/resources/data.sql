INSERT INTO collectivite (nom, ville, specialite, date_creation, autorisation_federation)
VALUES
    ('CoopAgri Antananarivo', 'Antananarivo', 'Riziculture', '2024-01-01', TRUE),
    ('Agri Vakinankaratra', 'Antsirabe', 'Légumes', '2024-02-01', TRUE);

INSERT INTO membre (nom, prenom, date_naissance, genre, adresse, metier, telephone, email, date_adhesion, actif)
VALUES
    ('Rakoto', 'Jean', '1990-05-10', 'MASCULIN', 'Tana', 'Agriculteur', '0340000001', 'jean@mail.com', '2024-01-10', true),
    ('Rasoanaivo', 'Marie', '1995-03-20', 'FEMININ', 'Antsirabe', 'Agricultrice', '0340000002', 'marie@mail.com', '2024-02-10', false);

INSERT INTO mandat_poste (membre_id, collectivite_id, poste, date_debut, date_fin)
VALUES
    (1, 1, 'PRESIDENT', '2025-01-01', '2025-12-31'),
    (2, 2, 'TRESORIER', '2025-01-01', '2025-12-31');

INSERT INTO parrainage (membre_parrain_id, collectivite_id, date_parrainage)
VALUES
    (1, 1, '2026-01-10'),
    (2, 2, '2026-01-10');

INSERT INTO adhesion (membre_id, collectivite_id, frais_adhesion, cotisation_annuelle, montant_total_paye, date_paiement)
VALUES
    (1, 1, 50000, 200000, 250000, '2026-01-10');

INSERT INTO cotisation (membre_id, collectivite_id, type, montant, date_encaissement, mode_paiement)
VALUES
    (1, 1, 'MENSUELLE', 20000, '2026-02-01', 'MOBILE_MONEY');

INSERT INTO activite (collectivite_id, type, description, date_activite, obligatoire)
VALUES
    (1, 'ASSEMBLEE_GENERALE', 'Réunion mensuelle', '2026-04-10', TRUE);

INSERT INTO presence (activite_id, membre_id, present, excuse, motif)
VALUES
    (1, 1, TRUE, FALSE, NULL);