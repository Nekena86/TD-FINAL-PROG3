package org.td2.exam.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.td2.exam.Model.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private DataSource dataSource;

    @Autowired
    public TransactionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Transaction transaction) {
        String sql = "INSERT INTO transaction (collectivity_id, member_debite_id, montant, compte_credite_id, moyen_paiement, date_creation) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaction.getCollectivityId());
            stmt.setString(2, transaction.getMemberDebiteId());
            stmt.setInt(3, transaction.getMontant());
            stmt.setString(4, transaction.getCompteCrediteId());
            stmt.setString(5, transaction.getMoyenPaiement());
            stmt.setDate(6, transaction.getDateCreation() != null ? Date.valueOf(transaction.getDateCreation()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting transaction", e);
        }
    }

    public List<Transaction> findByCollectivityId(String collectivityId) {
        String sql = "SELECT * FROM transaction WHERE collectivity_id = ?";
        List<Transaction> transactions = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setCollectivityId(rs.getString("collectivity_id"));
                t.setMemberDebiteId(rs.getString("member_debite_id"));
                t.setMontant(rs.getInt("montant"));
                t.setCompteCrediteId(rs.getString("compte_credite_id"));
                t.setMoyenPaiement(rs.getString("moyen_paiement"));
                Date dateCreation = rs.getDate("date_creation");
                if (dateCreation != null) {
                    t.setDateCreation(dateCreation.toLocalDate());
                }
                transactions.add(t);
            }
            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding transactions by collectivity id", e);
        }
    }

    public List<Transaction> findByCollectivityIdAndDateBetween(String collectivityId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM transaction WHERE collectivity_id = ? AND date_creation BETWEEN ? AND ?";
        List<Transaction> transactions = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setCollectivityId(rs.getString("collectivity_id"));
                t.setMemberDebiteId(rs.getString("member_debite_id"));
                t.setMontant(rs.getInt("montant"));
                t.setCompteCrediteId(rs.getString("compte_credite_id"));
                t.setMoyenPaiement(rs.getString("moyen_paiement"));
                Date dateCreation = rs.getDate("date_creation");
                if (dateCreation != null) {
                    t.setDateCreation(dateCreation.toLocalDate());
                }
                transactions.add(t);
            }
            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding transactions by date range", e);
        }
    }
}