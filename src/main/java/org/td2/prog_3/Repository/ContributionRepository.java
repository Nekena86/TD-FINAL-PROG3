

package org.td2.prog_3.Repository;

import org.springframework.stereotype.Repository;
import org.td2.prog_3.DataSource.DatabaseConnection;
import org.td2.prog_3.Model.Contribution;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContributionRepository {

    public Contribution save(Contribution contribution) {
        String sql = "INSERT INTO contribution (amount, date_encashment, member_id, collectivity_id, type, payment_mode) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, contribution.getAmount());
            ps.setDate(2, Date.valueOf(contribution.getDate()));
            ps.setLong(3, contribution.getMemberId());
            ps.setLong(4, contribution.getCollectivityId());
            ps.setString(5, contribution.getType());
            ps.setString(6, contribution.getPaymentMode());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                contribution.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating contribution");
        }
        return contribution;
    }

    public List<Contribution> findByMemberId(Long memberId) {
        List<Contribution> contributions = new ArrayList<>();
        String sql = "SELECT * FROM contribution WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, memberId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contribution c = new Contribution();
                c.setId(rs.getLong("id"));
                c.setAmount(rs.getDouble("amount"));
                c.setDate(rs.getDate("date_encashment") != null ? rs.getDate("date_encashment").toLocalDate() : null);
                c.setMemberId(rs.getLong("member_id"));
                c.setCollectivityId(rs.getLong("collectivity_id"));
                c.setType(rs.getString("type"));
                c.setPaymentMode(rs.getString("payment_mode"));
                contributions.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contributions;
    }
}
