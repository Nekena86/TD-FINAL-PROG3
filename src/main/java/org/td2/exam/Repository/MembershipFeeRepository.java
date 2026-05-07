package org.td2.exam.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.td2.exam.Model.MembershipFee;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MembershipFeeRepository {

    private DataSource dataSource;

    @Autowired
    public MembershipFeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(MembershipFee fee) {
        String sql = "INSERT INTO membership_fee (id, collectivity_id, label, statut, frequence, eligible_depuis, montant) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fee.getId());
            stmt.setString(2, fee.getCollectivityId());
            stmt.setString(3, fee.getLabel());
            stmt.setString(4, fee.getStatut());
            stmt.setString(5, fee.getFrequence());
            stmt.setDate(6, fee.getEligibleDepuis() != null ? Date.valueOf(fee.getEligibleDepuis()) : null);
            stmt.setInt(7, fee.getMontant());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting membership fee", e);
        }
    }

    public List<MembershipFee> findByCollectivityId(String collectivityId) {
        String sql = "SELECT * FROM membership_fee WHERE collectivity_id = ?";
        List<MembershipFee> fees = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MembershipFee mf = new MembershipFee();
                mf.setId(rs.getString("id"));
                mf.setCollectivityId(rs.getString("collectivity_id"));
                mf.setLabel(rs.getString("label"));
                mf.setStatut(rs.getString("statut"));
                mf.setFrequence(rs.getString("frequence"));
                Date eligibleDepuis = rs.getDate("eligible_depuis");
                if (eligibleDepuis != null) {
                    mf.setEligibleDepuis(eligibleDepuis.toLocalDate());
                }
                mf.setMontant(rs.getInt("montant"));
                fees.add(mf);
            }
            return fees;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding membership fees by collectivity id", e);
        }
    }


    public int getTotalActiveAnnualFee(String collectivityId, LocalDate periodStartDate) {
        String sql = "SELECT COALESCE(SUM(montant), 0) FROM membership_fee " +
                "WHERE collectivity_id = ? AND statut = 'ACTIVE' AND eligible_depuis <= ? " +
                "AND frequence = 'ANNUALLY'";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(periodStartDate));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting total active annual fee", e);
        }
    }


    public void updateStatut(String id, String statut) {
        String sql = "UPDATE membership_fee SET statut = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, statut);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating membership fee status", e);
        }
    }

    public MembershipFee findById(String id) {
        String sql = "SELECT * FROM membership_fee WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MembershipFee mf = new MembershipFee();
                mf.setId(rs.getString("id"));
                mf.setCollectivityId(rs.getString("collectivity_id"));
                mf.setLabel(rs.getString("label"));
                mf.setStatut(rs.getString("statut"));
                mf.setFrequence(rs.getString("frequence"));
                Date eligibleDepuis = rs.getDate("eligible_depuis");
                if (eligibleDepuis != null) {
                    mf.setEligibleDepuis(eligibleDepuis.toLocalDate());
                }
                mf.setMontant(rs.getInt("montant"));
                return mf;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding membership fee by id", e);
        }
    }
}