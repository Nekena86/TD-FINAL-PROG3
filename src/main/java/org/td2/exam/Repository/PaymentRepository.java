package org.td2.exam.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.td2.exam.Model.Payment;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {

    private DataSource dataSource;

    @Autowired
    public PaymentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Payment payment) {
        String sql = "INSERT INTO payment (collectivity_id, member_id, montant, compte_credite_id, moyen_paiement, date_paiement) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, payment.getCollectivityId());
            stmt.setString(2, payment.getMemberId());
            stmt.setInt(3, payment.getMontant());
            stmt.setString(4, payment.getCompteCrediteId());
            stmt.setString(5, payment.getMoyenPaiement());
            stmt.setDate(6, payment.getDatePaiement() != null ? Date.valueOf(payment.getDatePaiement()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting payment", e);
        }
    }

    public List<Payment> findByCollectivityId(String collectivityId) {
        String sql = "SELECT * FROM payment WHERE collectivity_id = ?";
        List<Payment> payments = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Payment p = new Payment();
                p.setCollectivityId(rs.getString("collectivity_id"));
                p.setMemberId(rs.getString("member_id"));
                p.setMontant(rs.getInt("montant"));
                p.setCompteCrediteId(rs.getString("compte_credite_id"));
                p.setMoyenPaiement(rs.getString("moyen_paiement"));
                Date datePaiement = rs.getDate("date_paiement");
                if (datePaiement != null) {
                    p.setDatePaiement(datePaiement.toLocalDate());
                }
                payments.add(p);
            }
            return payments;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding payments by collectivity id", e);
        }
    }

    public List<Payment> findByMemberId(String memberId) {
        String sql = "SELECT * FROM payment WHERE member_id = ?";
        List<Payment> payments = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, memberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Payment p = new Payment();
                p.setCollectivityId(rs.getString("collectivity_id"));
                p.setMemberId(rs.getString("member_id"));
                p.setMontant(rs.getInt("montant"));
                p.setCompteCrediteId(rs.getString("compte_credite_id"));
                p.setMoyenPaiement(rs.getString("moyen_paiement"));
                Date datePaiement = rs.getDate("date_paiement");
                if (datePaiement != null) {
                    p.setDatePaiement(datePaiement.toLocalDate());
                }
                payments.add(p);
            }
            return payments;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding payments by member id", e);
        }
    }

    public int getTotalPaidByMember(String memberId) {
        String sql = "SELECT COALESCE(SUM(montant), 0) FROM payment WHERE member_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, memberId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting total paid by member", e);
        }
    }
}