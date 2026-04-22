package org.td2.prog_3.Repository;


import org.springframework.stereotype.Repository;
import org.td2.prog_3.DataSource.DatabaseConnection;
import org.td2.prog_3.Model.Transaction;

import java.sql.*;

@Repository
public class TransactionRepository {

    public Transaction save(Transaction transaction) {
        String sql = "INSERT INTO transaction_compte (montant, date_transaction, type, compte_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, transaction.getMontant());
            ps.setDate(2, java.sql.Date.valueOf(transaction.getDate()));
            ps.setString(3, transaction.getType());
            ps.setLong(4, transaction.getCompteId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                transaction.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public void updateCompteSolde(Long compteId, Double montant, String operation) {
        String sql = "UPDATE compte SET solde = solde " + ("ENTREE".equals(operation) ? "+" : "-") + " ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, montant);
            ps.setLong(2, compteId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
