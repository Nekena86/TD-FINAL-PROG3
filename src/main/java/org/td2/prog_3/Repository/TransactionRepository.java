
package org.td2.prog_3.Repository;

import org.springframework.stereotype.Repository;
import org.td2.prog_3.DataSource.DatabaseConnection;
import org.td2.prog_3.Model.Transaction;

import java.sql.*;

@Repository
public class TransactionRepository {

    public Transaction save(Transaction transaction) {
        String sql = "INSERT INTO account_transaction (amount, transaction_date, type, account_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, transaction.getAmount());
            ps.setDate(2, Date.valueOf(transaction.getDate()));
            ps.setString(3, transaction.getType());
            ps.setLong(4, transaction.getAccountId());

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

    public void updateAccountBalance(Long accountId, Double amount, String operation) {
        String sql = "UPDATE account SET balance = balance " + ("INCOME".equals(operation) ? "+" : "-") + " ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setLong(2, accountId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}