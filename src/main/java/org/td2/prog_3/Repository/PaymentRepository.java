
package org.td2.prog_3.Repository;

import org.springframework.stereotype.Repository;
import org.td2.prog_3.DataSource.DatabaseConnection;
import org.td2.prog_3.Model.Payment;

import java.sql.*;

@Repository
public class PaymentRepository {

    public Payment save(Payment payment) {
        String sql = "INSERT INTO payment (amount, payment_date, mode, member_id, contribution_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, payment.getAmount());
            ps.setDate(2, Date.valueOf(payment.getDate()));
            ps.setString(3, payment.getMode());
            ps.setLong(4, payment.getMemberId());
            ps.setLong(5, payment.getContributionId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                payment.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while processing payment");
        }
        return payment;
    }
}