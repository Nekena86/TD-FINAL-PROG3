
-- =====================================================
-- 1. COLLECTIVITÉS (Tableau 1)
-- =====================================================
INSERT INTO collectivity (id, numero, nom, localite, specialisation) VALUES
                                                                           ('col-1', '1', 'Mpanorina', 'Ambatondrazaka', 'Riziculture'),
                                                                           ('col-2', '2', 'Dobo voalahany', 'Ambatondrazaka', 'Pisciculture'),
                                                                           ('col-3', '3', 'Tantely mamy', 'Brickaville', 'Apiculture');

-- =====================================================
-- 2. MEMBRES EXISTANTS
-- =====================================================

-- Collectivité 1 (Tableau 2)
INSERT INTO member (id, collectivity_id, nom, prenom, date_naissance, genre, adresse, profession, telephone, email, occupation, referent_ids, date_adhesion) VALUES
                                                                                                                                                                  ('C1-M1', 'col-1', 'Nom membre 1', 'Prénom membre 1', '1980-02-01', 'M', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'PRESIDENT', NULL, '2026-01-01'),
                                                                                                                                                                  ('C1-M2', 'col-1', 'Nom membre 2', 'Prénom membre 2', '1982-03-05', 'M', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'VICE_PRESIDENT', NULL, '2026-01-01'),
                                                                                                                                                                  ('C1-M3', 'col-1', 'Nom membre 3', 'Prénom membre 3', '1992-03-10', 'M', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'SECRETARY', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C1-M4', 'col-1', 'Nom membre 4', 'Prénom membre 4', '1988-05-22', 'F', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'TREASURER', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C1-M5', 'col-1', 'Nom membre 5', 'Prénom membre 5', '1999-08-21', 'M', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'CONFIRMED', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C1-M6', 'col-1', 'Nom membre 6', 'Prénom membre 6', '1998-08-22', 'F', 'Lot UV 6 Ambato.', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'CONFIRMED', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C1-M7', 'col-1', 'Nom membre 7', 'Prénom membre 7', '1998-01-31', 'M', 'Lot UV 7 Ambato.', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'CONFIRMED', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C1-M8', 'col-1', 'Nom membre 8', 'Prénom membre 8', '1975-08-20', 'M', 'Lot UV 8 Ambato.', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'CONFIRMED', 'C1-M6,C1-M7', '2026-01-01');

-- Collectivité 2 (Tableau 3)
INSERT INTO member (id, collectivity_id, nom, prenom, date_naissance, genre, adresse, profession, telephone, email, occupation, referent_ids, date_adhesion) VALUES
                                                                                                                                                                  ('C2-M1', 'col-2', 'Nom membre 1', 'Prénom membre 1', '1980-02-01', 'M', 'Lot II V M Ambato.', 'Riziculteur', '0341234567', 'member.1@fed-agri.mg', 'CONFIRMED', NULL, '2026-01-01'),
                                                                                                                                                                  ('C2-M2', 'col-2', 'Nom membre 2', 'Prénom membre 2', '1982-03-05', 'M', 'Lot II F Ambato.', 'Agriculteur', '0321234567', 'member.2@fed-agri.mg', 'CONFIRMED', NULL, '2026-01-01'),
                                                                                                                                                                  ('C2-M3', 'col-2', 'Nom membre 3', 'Prénom membre 3', '1992-03-10', 'M', 'Lot II J Ambato.', 'Collecteur', '0331234567', 'member.3@fed-agri.mg', 'CONFIRMED', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C2-M4', 'col-2', 'Nom membre 4', 'Prénom membre 4', '1988-05-22', 'F', 'Lot A K 50 Ambato.', 'Distributeur', '0381234567', 'member.4@fed-agri.mg', 'CONFIRMED', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C2-M5', 'col-2', 'Nom membre 5', 'Prénom membre 5', '1999-08-21', 'M', 'Lot UV 80 Ambato.', 'Riziculteur', '0373434567', 'member.5@fed-agri.mg', 'PRESIDENT', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C2-M6', 'col-2', 'Nom membre 6', 'Prénom membre 6', '1998-08-22', 'F', 'Lot UV 6 Ambato.', 'Riziculteur', '0372234567', 'member.6@fed-agri.mg', 'VICE_PRESIDENT', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C2-M7', 'col-2', 'Nom membre 7', 'Prénom membre 7', '1998-01-31', 'M', 'Lot UV 7 Ambato.', 'Riziculteur', '0374234567', 'member.7@fed-agri.mg', 'SECRETARY', 'C1-M1,C1-M2', '2026-01-01'),
                                                                                                                                                                  ('C2-M8', 'col-2', 'Nom membre 8', 'Prénom membre 8', '1975-08-20', 'M', 'Lot UV 8 Ambato.', 'Riziculteur', '0370234567', 'member.8@fed-agri.mg', 'TREASURER', 'C1-M6,C1-M7', '2026-01-01');

-- Collectivité 3 (Tableau 4)
INSERT INTO member (id, collectivity_id, nom, prenom, date_naissance, genre, adresse, profession, telephone, email, occupation, referent_ids, date_adhesion) VALUES
                                                                                                                                                                  ('C3-M1', 'col-3', 'Nom membre 9', 'Prénom membre 9', '1988-01-02', 'M', 'Lot 33 J Antsirabe', 'Apiculteur', '034034567', 'member.9@fed-agri.mg', 'PRESIDENT', NULL, '2026-01-01'),
                                                                                                                                                                  ('C3-M2', 'col-3', 'Nom membre 10', 'Prénom membre 10', '1982-03-05', 'M', 'Lot 2 J Antsirabe', 'Agriculteur', '0338634567', 'member.10@fed-agri.mg', 'VICE_PRESIDENT', NULL, '2026-01-01'),
                                                                                                                                                                  ('C3-M3', 'col-3', 'Nom membre 11', 'Prénom membre 11', '1992-03-12', 'M', 'Lot 8 KM Antsirabe', 'Collecteur', '0338234567', 'member.11@fed-agri.mg', 'SECRETARY', 'C3-M1,C3-M2', '2026-01-01'),
                                                                                                                                                                  ('C3-M4', 'col-3', 'Nom membre 12', 'Prénom membre 12', '1988-05-10', 'F', 'Lot A K 50 Antsirabe', 'Distributeur', '0382334567', 'member.12@fed-agri.mg', 'TREASURER', 'C3-M1,C3-M2', '2026-01-01'),
                                                                                                                                                                  ('C3-M5', 'col-3', 'Nom membre 13', 'Prénom membre 13', '1999-08-11', 'M', 'Lot UV 80 Antsirabe.', 'Apiculteur', '0373365567', 'member.13@fed-agri.mg', 'CONFIRMED', 'C3-M1,C3-M2', '2026-01-01'),
                                                                                                                                                                  ('C3-M6', 'col-3', 'Nom membre 14', 'Prénom membre 14', '1998-08-09', 'F', 'Lot UV 6 Antsirabe.', 'Apiculteur', '0378234567', 'member.14@fed-agri.mg', 'CONFIRMED', 'C3-M1,C3-M2', '2026-01-01'),
                                                                                                                                                                  ('C3-M7', 'col-3', 'Nom membre 15', 'Prénom membre 15', '1998-01-13', 'M', 'Lot UV 7 Antsirabe', 'Apiculteur', '0374914567', 'member.15@fed-agri.mg', 'CONFIRMED', 'C3-M1,C3-M2', '2026-01-01'),
                                                                                                                                                                  ('C3-M8', 'col-3', 'Nom membre 16', 'Prénom membre 16', '1975-08-02', 'M', 'Lot UV 8 Antsirabe', 'Apiculteur', '0370634567', 'member.16@fed-agri.mg', 'CONFIRMED', 'C3-M1,C3-M2', '2026-01-01');

-- =====================================================
-- 3. COMPTES FINANCIERS
-- =====================================================

-- Comptes existants
INSERT INTO financial_account (id, collectivity_id, type, balance, holder_name, phone_number, bank_name, account_number) VALUES
                                                                                                                              ('C1-A-CASH', 'col-1', 'CASH', 0, NULL, NULL, NULL, NULL),
                                                                                                                              ('C1-A-MOBILE-1', 'col-1', 'ORANGE_MONEY', 0, 'Mozartia', '0370489612', NULL, NULL),
                                                                                                                              ('C2-A-CASH', 'col-2', 'CASH', 0, NULL, NULL, NULL, NULL),
                                                                                                                              ('C2-A-MOBILE-1', 'col-2', 'ORANGE_MONEY', 0, 'Dobo voalchany', '0320489612', NULL, NULL),
                                                                                                                              ('C3-A-CASH', 'col-3', 'CASH', 0, NULL, NULL, NULL, NULL);

-- Nouveaux comptes à ajouter (page 24)
INSERT INTO financial_account (id, collectivity_id, type, balance, holder_name, bank_name, account_number, rib_key) VALUES
                                                                                                                         ('C3-A-BANK-1', 'col-3', 'BANK', 0, 'Koto', 'BMOI', '0000400001123456789012', 'Kot'),
                                                                                                                         ('C3-A-BANK-2', 'col-3', 'BANK', 0, 'Naivo', 'BRED', '0000800003456789012358', 'Nai');

INSERT INTO financial_account (id, collectivity_id, type, balance, holder_name, phone_number) VALUES
    ('C3-A-MOBILE-1', 'col-3', 'MVOLA', 0, 'Kolo', '0341889612');

-- =====================================================
-- 4. COTISATIONS
-- =====================================================

-- Collectivité 1 (Tableau 12)
INSERT INTO membership_fee (id, collectivity_id, label, status, frequency, eligible_from, amount) VALUES
                                                                                                       ('cot-1', 'col-1', 'Cotisation annuelle', 'ACTIVE', 'ANNUALLY', '2026-01-01', 200000),
                                                                                                       ('cot-2', 'col-1', 'Famangiana', 'ACTIVE', 'PUNCTUALLY', '2026-04-30', 20000);

-- Collectivité 2 (Tableau 13)
INSERT INTO membership_fee (id, collectivity_id, label, status, frequency, eligible_from, amount) VALUES
                                                                                                       ('cot-3', 'col-2', 'Cotisation annuelle', 'ACTIVE', 'ANNUALLY', '2026-01-01', 200000),
                                                                                                       ('cot-4', 'col-2', 'Cotisation 2025', 'INACTIVE', 'ANNUALLY', '2025-01-01', 100000);

-- Collectivité 3 (Tableau 14)
INSERT INTO membership_fee (id, collectivity_id, label, status, frequency, eligible_from, amount) VALUES
    ('cot-5', 'col-3', 'Cotisation mensuelle', 'ACTIVE', 'MONTHLY', '2026-04-01', 25000);

-- =====================================================
-- 5. PAIEMENTS (Tableaux 15, 16, 17)
-- =====================================================

-- Collectivité 1 (Tableau 15)
INSERT INTO payment (id, collectivity_id, member_id, amount, account_credited_id, payment_method, payment_date) VALUES
                                                                                                                     (gen_random_uuid(), 'col-1', 'C1-M1', 200000, 'C1-A-CASH', 'CASH', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-1', 'C1-M2', 200000, 'C1-A-CASH', 'CASH', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-1', 'C1-M3', 200000, 'C1-A-MOBILE-1', 'MOBILE_MONEY', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-1', 'C1-M4', 200000, 'C1-A-MOBILE-1', 'MOBILE_MONEY', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-1', 'C1-M5', 150000, 'C1-A-MOBILE-1', 'MOBILE_MONEY', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-1', 'C1-M6', 100000, 'C1-A-CASH', 'CASH', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-1', 'C1-M7', 60000, 'C1-A-CASH', 'CASH', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-1', 'C1-M8', 90000, 'C1-A-CASH', 'CASH', '2026-05-01');

-- Collectivité 2 (Tableau 16)
INSERT INTO payment (id, collectivity_id, member_id, amount, account_credited_id, payment_method, payment_date) VALUES
                                                                                                                     (gen_random_uuid(), 'col-2', 'C2-M1', 120000, 'C2-A-CASH', 'CASH', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-2', 'C2-M2', 180000, 'C2-A-CASH', 'CASH', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-2', 'C2-M3', 200000, 'C2-A-CASH', 'CASH', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-2', 'C2-M4', 200000, 'C2-A-CASH', 'CASH', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-2', 'C2-M5', 200000, 'C2-A-CASH', 'CASH', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-2', 'C2-M6', 200000, 'C2-A-CASH', 'CASH', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-2', 'C2-M7', 80000, 'C2-A-MOBILE-1', 'MOBILE_MONEY', '2026-01-01'),
                                                                                                                     (gen_random_uuid(), 'col-2', 'C2-M8', 120000, 'C2-A-MOBILE-1', 'MOBILE_MONEY', '2026-01-01');

-- Collectivité 3 (Tableau 17)
INSERT INTO payment (id, collectivity_id, member_id, amount, account_credited_id, payment_method, payment_date) VALUES
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M1', 25000, 'C3-A-BANK-1', 'BANK', '2026-04-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M2', 25000, 'C3-A-BANK-1', 'BANK', '2026-04-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M3', 25000, 'C3-A-BANK-1', 'BANK', '2026-04-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M4', 25000, 'C3-A-BANK-1', 'BANK', '2026-04-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M5', 25000, 'C3-A-BANK-2', 'BANK', '2026-04-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M6', 25000, 'C3-A-BANK-2', 'BANK', '2026-04-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M7', 25000, 'C3-A-CASH', 'CASH', '2026-04-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M8', 25000, 'C3-A-CASH', 'CASH', '2026-04-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M1', 25000, 'C3-A-BANK-1', 'BANK', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M2', 25000, 'C3-A-BANK-1', 'BANK', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M3', 15000, 'C3-A-MOBILE-1', 'MOBILE_MONEY', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M4', 15000, 'C3-A-MOBILE-1', 'MOBILE_MONEY', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M5', 20000, 'C3-A-BANK-2', 'BANK', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M6', 25000, 'C3-A-BANK-2', 'BANK', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M7', 5000, 'C3-A-CASH', 'CASH', '2026-05-01'),
                                                                                                                     (gen_random_uuid(), 'col-3', 'C3-M8', 5000, 'C3-A-CASH', 'CASH', '2026-05-01');

-- =====================================================
-- 6. TRANSACTIONS
-- =====================================================

INSERT INTO transaction (id, collectivity_id, member_id, amount, account_id, type, transaction_date)
SELECT gen_random_uuid(), collectivity_id, member_id, amount, account_credited_id, 'CREDIT', payment_date
FROM payment;

-- =====================================================
-- 7. NOUVEAUX MEMBRES ADHÉRENTS (Tableaux 18, 19, 20)
-- =====================================================

-- Collectivité 1 (Tableau 18)
INSERT INTO member (id, collectivity_id, nom, prenom, date_naissance, genre, adresse, profession, telephone, email, occupation, referent_ids, date_adhesion) VALUES
                                                                                                                                                                  ('C1-NEW-1', 'col-1', 'Randria', 'Mampionona', '1995-04-15', 'M', 'Lot A 123 Ambato', 'Agriculteur', '0341122334', 'new1@email.mg', 'JUNIOR', 'C1-M1,C1-M2', '2026-04-01'),
                                                                                                                                                                  ('C1-NEW-2', 'col-1', 'Rakoto', 'Faneva', '1996-05-20', 'M', 'Lot B 456 Ambato', 'Riziculteur', '0342233445', 'new2@email.mg', 'JUNIOR', 'C1-M1,C1-M2', '2026-04-01'),
                                                                                                                                                                  ('C1-NEW-3', 'col-1', 'Raso', 'Miora', '1997-06-10', 'F', 'Lot C 789 Ambato', 'Collecteur', '0343344556', 'new3@email.mg', 'JUNIOR', 'C1-M1,C1-M2', '2026-05-01'),
                                                                                                                                                                  ('C1-NEW-4', 'col-1', 'Andry', 'Nantenaina', '1998-07-25', 'M', 'Lot D 101 Ambato', 'Apiculteur', '0344455667', 'new4@email.mg', 'JUNIOR', 'C1-M1,C1-M2', '2026-06-01');

-- Collectivité 2 (Tableau 19)
INSERT INTO member (id, collectivity_id, nom, prenom, date_naissance, genre, adresse, profession, telephone, email, occupation, referent_ids, date_adhesion) VALUES
                                                                                                                                                                  ('C2-NEW-1', 'col-2', 'Ravelo', 'Hery', '1995-02-14', 'M', 'Lot E 202 Ambato', 'Pisciculteur', '0345566778', 'new5@email.mg', 'JUNIOR', 'C2-M1,C2-M2', '2026-03-01'),
                                                                                                                                                                  ('C2-NEW-2', 'col-2', 'Raman', 'Tiana', '1996-03-18', 'F', 'Lot F 303 Ambato', 'Distributeur', '0346677889', 'new6@email.mg', 'JUNIOR', 'C2-M1,C2-M2', '2026-03-01'),
                                                                                                                                                                  ('C2-NEW-3', 'col-2', 'Razafy', 'Lova', '1997-04-22', 'M', 'Lot G 404 Ambato', 'Agriculteur', '0347788990', 'new7@email.mg', 'JUNIOR', 'C2-M1,C2-M2', '2026-03-01');

-- Collectivité 3 (Tableau 20)
INSERT INTO member (id, collectivity_id, nom, prenom, date_naissance, genre, adresse, profession, telephone, email, occupation, referent_ids, date_adhesion) VALUES
                                                                                                                                                                  ('C3-NEW-1', 'col-3', 'Nomenjanahary', 'Toky', '1994-11-01', 'M', 'Lot H 505 Antsirabe', 'Apiculteur', '0348899001', 'new8@email.mg', 'JUNIOR', 'C3-M1,C3-M2', '2026-01-01'),
                                                                                                                                                                  ('C3-NEW-2', 'col-3', 'Rakotomalala', 'Mamy', '1995-12-15', 'F', 'Lot I 606 Antsirabe', 'Agriculteur', '0349900112', 'new9@email.mg', 'JUNIOR', 'C3-M1,C3-M2', '2026-02-01'),
                                                                                                                                                                  ('C3-NEW-3', 'col-3', 'Andriamanantena', 'Fidy', '1996-01-20', 'M', 'Lot J 707 Antsirabe', 'Collecteur', '0340011223', 'new10@email.mg', 'JUNIOR', 'C3-M1,C3-M2', '2026-02-01'),
                                                                                                                                                                  ('C3-NEW-4', 'col-3', 'Randrianasolo', 'Nirina', '1997-02-10', 'F', 'Lot K 808 Antsirabe', 'Apiculteur', '0341122335', 'new11@email.mg', 'JUNIOR', 'C3-M1,C3-M2', '2026-03-01'),
                                                                                                                                                                  ('C3-NEW-5', 'col-3', 'Rakotobe', 'Haja', '1998-03-18', 'M', 'Lot L 909 Antsirabe', 'Distributeur', '0342233446', 'new12@email.mg', 'JUNIOR', 'C3-M1,C3-M2', '2026-03-01'),
                                                                                                                                                                  ('C3-NEW-6', 'col-3', 'Razafindrakoto', 'Tahina', '1999-04-25', 'M', 'Lot M 1010 Antsirabe', 'Apiculteur', '0343344557', 'new13@email.mg', 'JUNIOR', 'C3-M1,C3-M2', '2026-03-01');

-- =====================================================
-- 8. MISE À JOUR DES BALANCES DES COMPTES
-- =====================================================

UPDATE financial_account fa
SET balance = (
    SELECT COALESCE(SUM(t.amount), 0)
    FROM transaction t
    WHERE t.account_id = fa.id AND t.type = 'CREDIT'
)
WHERE fa.id IN (SELECT DISTINCT account_id FROM transaction);