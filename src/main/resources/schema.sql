-- Suppression des tables existantes
DROP TABLE IF EXISTS transaction CASCADE;
DROP TABLE IF EXISTS payment CASCADE;
DROP TABLE IF EXISTS membership_fee CASCADE;
DROP TABLE IF EXISTS financial_account CASCADE;
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS collectivity CASCADE;

-- =====================================================
-- TABLE collectivities
-- =====================================================
CREATE TABLE collectivity (
                                id VARCHAR(50) PRIMARY KEY,
                                numero VARCHAR(10) UNIQUE,
                                nom VARCHAR(100) UNIQUE NOT NULL,
                                localite VARCHAR(100) NOT NULL,
                                specialisation VARCHAR(100) NOT NULL,
                                date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                federation_authorization BOOLEAN DEFAULT TRUE
);

-- =====================================================
-- TABLE members
-- =====================================================
CREATE TABLE member (
                         id VARCHAR(50) PRIMARY KEY,
                         collectivity_id VARCHAR(50) NOT NULL,
                         nom VARCHAR(100) NOT NULL,
                         prenom VARCHAR(100) NOT NULL,
                         date_naissance DATE NOT NULL,
                         genre VARCHAR(10) CHECK (genre IN ('M', 'F')),
                         adresse TEXT,
                         profession VARCHAR(100),
                         telephone VARCHAR(20) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         occupation VARCHAR(50) CHECK (occupation IN ('PRESIDENT', 'VICE_PRESIDENT', 'TREASURER', 'SECRETARY', 'CONFIRMED', 'JUNIOR')),
                         referent_ids TEXT,
                         date_adhesion DATE NOT NULL,
                         is_active BOOLEAN DEFAULT TRUE,
                         FOREIGN KEY (collectivity_id) REFERENCES collectivity(id) ON DELETE CASCADE
);

-- =====================================================
-- TABLE financial_accounts
-- =====================================================
CREATE TABLE financial_account (
                                    id VARCHAR(50) PRIMARY KEY,
                                    collectivity_id VARCHAR(50) NOT NULL,
                                    type VARCHAR(20) CHECK (type IN ('CASH', 'BANK', 'ORANGE_MONEY', 'MVOLA', 'AIRTEL_MONEY')),
                                    balance DECIMAL(15,2) DEFAULT 0,
                                    holder_name VARCHAR(100),
                                    phone_number VARCHAR(20),
                                    bank_name VARCHAR(50),
                                    account_number VARCHAR(30),
                                    rib_key VARCHAR(2),
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    FOREIGN KEY (collectivity_id) REFERENCES collectivity(id) ON DELETE CASCADE
);

-- =====================================================
-- TABLE membership_fees
-- =====================================================
CREATE TABLE membership_fee (
                                 id VARCHAR(50) PRIMARY KEY,
                                 collectivity_id VARCHAR(50) NOT NULL,
                                 label VARCHAR(100) NOT NULL,
                                 status VARCHAR(20) CHECK (status IN ('ACTIVE', 'INACTIVE')),
                                 frequency VARCHAR(20) CHECK (frequency IN ('MONTHLY', 'ANNUALLY', 'PUNCTUALLY')),
                                 eligible_from DATE NOT NULL,
                                 amount DECIMAL(15,2) NOT NULL,
                                 FOREIGN KEY (collectivity_id) REFERENCES collectivity(id) ON DELETE CASCADE
);

-- =====================================================
-- TABLE payments
-- =====================================================
CREATE TABLE payment (
                          id UUID PRIMARY KEY,
                          collectivity_id VARCHAR(50) NOT NULL,
                          member_id VARCHAR(50) NOT NULL,
                          amount DECIMAL(15,2) NOT NULL,
                          account_credited_id VARCHAR(50) NOT NULL,
                          payment_method VARCHAR(20) CHECK (payment_method IN ('CASH', 'BANK', 'MOBILE_MONEY')),
                          payment_date DATE NOT NULL,
                          FOREIGN KEY (collectivity_id) REFERENCES collectivity(id) ON DELETE CASCADE,
                          FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
                          FOREIGN KEY (account_credited_id) REFERENCES financial_account(id) ON DELETE CASCADE
);

-- =====================================================
-- TABLE transactions
-- =====================================================
CREATE TABLE transaction (
                              id UUID PRIMARY KEY,
                              collectivity_id VARCHAR(50) NOT NULL,
                              member_id VARCHAR(50) NOT NULL,
                              amount DECIMAL(15,2) NOT NULL,
                              account_id VARCHAR(50) NOT NULL,
                              type VARCHAR(10) CHECK (type IN ('DEBIT', 'CREDIT')),
                              transaction_date DATE NOT NULL,
                              FOREIGN KEY (collectivity_id) REFERENCES collectivity(id) ON DELETE CASCADE,
                              FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
                              FOREIGN KEY (account_id) REFERENCES financial_account(id) ON DELETE CASCADE
);

-- =====================================================
-- INDEX
-- =====================================================
CREATE INDEX idx_members_collectivity ON member(collectivity_id);
CREATE INDEX idx_members_occupation ON member(occupation);
CREATE INDEX idx_members_date_adhesion ON member(date_adhesion);
CREATE INDEX idx_financial_accounts_collectivity ON financial_account(collectivity_id);
CREATE INDEX idx_membership_fees_collectivity ON membership_fee(collectivity_id);
CREATE INDEX idx_payments_collectivity ON payment(collectivity_id);
CREATE INDEX idx_payments_member ON payment(member_id);
CREATE INDEX idx_payments_date ON payment(payment_date);
CREATE INDEX idx_transactions_collectivity ON transaction(collectivity_id);
CREATE INDEX idx_transactions_member ON transaction(member_id);
CREATE INDEX idx_transactions_date ON transaction(transaction_date);