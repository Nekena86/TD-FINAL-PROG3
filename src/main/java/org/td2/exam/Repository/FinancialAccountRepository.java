package org.td2.exam.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.td2.exam.Model.FinancialAccount;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FinancialAccountRepository {

    private DataSource dataSource;

    @Autowired
    public FinancialAccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(FinancialAccount account) {
        String sql = "INSERT INTO financial_account (id, collectivity_id, type_compte, montant_initial, titulaire, numero_telephone) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getId());
            stmt.setString(2, account.getCollectivityId());
            stmt.setString(3, account.getTypeCompte());
            stmt.setInt(4, account.getMontantInitial());
            stmt.setString(5, account.getTitulaire());
            stmt.setString(6, account.getNumeroTelephone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting financial account", e);
        }
    }

    public FinancialAccount findById(String id) {
        String sql = "SELECT * FROM financial_account WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FinancialAccount fa = new FinancialAccount();
                fa.setId(rs.getString("id"));
                fa.setCollectivityId(rs.getString("collectivity_id"));
                fa.setTypeCompte(rs.getString("type_compte"));
                fa.setMontantInitial(rs.getInt("montant_initial"));
                fa.setTitulaire(rs.getString("titulaire"));
                fa.setNumeroTelephone(rs.getString("numero_telephone"));
                return fa;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding financial account by id", e);
        }
    }

    public List<FinancialAccount> findByCollectivityId(String collectivityId) {
        String sql = "SELECT * FROM financial_account WHERE collectivity_id = ?";
        List<FinancialAccount> accounts = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FinancialAccount fa = new FinancialAccount();
                fa.setId(rs.getString("id"));
                fa.setCollectivityId(rs.getString("collectivity_id"));
                fa.setTypeCompte(rs.getString("type_compte"));
                fa.setMontantInitial(rs.getInt("montant_initial"));
                fa.setTitulaire(rs.getString("titulaire"));
                fa.setNumeroTelephone(rs.getString("numero_telephone"));
                accounts.add(fa);
            }
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding financial accounts by collectivity id", e);
        }
    }

    public int getCurrentBalance(String accountId, LocalDate atDate) {
        FinancialAccount account = findById(accountId);
        int initial = account != null ? account.getMontantInitial() : 0;

        String sql = "SELECT COALESCE(SUM(montant), 0) FROM payment WHERE compte_credite_id = ? AND date_paiement <= ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountId);
            stmt.setDate(2, Date.valueOf(atDate));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return initial + rs.getInt(1);
            }
            return initial;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting current balance", e);
        }
    }
}