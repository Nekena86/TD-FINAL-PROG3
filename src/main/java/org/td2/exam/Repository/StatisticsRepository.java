package org.td2.exam.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StatisticsRepository {

    private DataSource dataSource;

    @Autowired
    public StatisticsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * PUSH DOWN PROCESSING: Calcul du montant encaissé par membre sur une période
     * Le calcul est fait directement en SQL avec SUM et GROUP BY
     */
    public Map<String, Integer> getAmountCollectedByMember(String collectivityId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT member_id, COALESCE(SUM(montant), 0) as total_collected " +
                "FROM payment " +
                "WHERE collectivity_id = ? AND date_paiement BETWEEN ? AND ? " +
                "GROUP BY member_id";

        Map<String, Integer> result = new HashMap<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("member_id"), rs.getInt("total_collected"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error calculating amount collected by member", e);
        }
    }

    /**
     * PUSH DOWN PROCESSING: Calcul du montant impayé potentiel par membre
     * Jointure entre les membres et les cotisations actives, soustraction des paiements existants
     */
    public Map<String, Integer> getPotentialUnpaidByMember(String collectivityId, LocalDate periodStartDate) {
        String sql = "SELECT m.id as member_id, " +
                "GREATEST(0, (SELECT COALESCE(SUM(mf.montant), 0) FROM membership_fee mf " +
                "WHERE mf.collectivity_id = ? AND mf.statut = 'ACTIVE' AND mf.eligible_depuis <= ?) " +
                "- COALESCE((SELECT SUM(p.montant) FROM payment p WHERE p.member_id = m.id), 0)) as potential_unpaid " +
                "FROM member m WHERE m.collectivity_id = ?";

        Map<String, Integer> result = new HashMap<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(periodStartDate));
            stmt.setString(3, collectivityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("member_id"), rs.getInt("potential_unpaid"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error calculating potential unpaid by member", e);
        }
    }

    /**
     * PUSH DOWN PROCESSING: Calcul du pourcentage de membres à jour de cotisation
     * Ratio entre membres ayant payé le total des cotisations actives et nombre total de membres
     */
    public BigDecimal getPercentageUpToDateMembers(String collectivityId, LocalDate periodStartDate, LocalDate periodEndDate) {
        String sql = "WITH active_fee_total AS ( " +
                "  SELECT COALESCE(SUM(montant), 0) as total_fee " +
                "  FROM membership_fee " +
                "  WHERE collectivity_id = ? AND statut = 'ACTIVE' AND eligible_depuis <= ? " +
                "), " +
                "members_paid AS ( " +
                "  SELECT m.id, COALESCE(SUM(p.montant), 0) as paid_amount " +
                "  FROM member m " +
                "  LEFT JOIN payment p ON m.id = p.member_id AND p.date_paiement BETWEEN ? AND ? " +
                "  WHERE m.collectivity_id = ? " +
                "  GROUP BY m.id " +
                ") " +
                "SELECT COUNT(*) as up_to_date_count, (SELECT COUNT(*) FROM member WHERE collectivity_id = ?) as total_members " +
                "FROM members_paid mp, active_fee_total aft " +
                "WHERE mp.paid_amount >= aft.total_fee";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(periodStartDate));
            stmt.setDate(3, Date.valueOf(periodEndDate));
            stmt.setDate(4, Date.valueOf(periodEndDate));
            stmt.setString(5, collectivityId);
            stmt.setString(6, collectivityId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int upToDateCount = rs.getInt("up_to_date_count");
                int totalMembers = rs.getInt("total_members");
                if (totalMembers > 0) {
                    return BigDecimal.valueOf((double) upToDateCount / totalMembers * 100);
                }
            }
            return BigDecimal.ZERO;
        } catch (SQLException e) {
            throw new RuntimeException("Error calculating percentage up to date members", e);
        }
    }

    /**
     * PUSH DOWN PROCESSING: Comptage des nouveaux adhérents sur une période
     */
    public int getNewMembersCount(String collectivityId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM member " +
                "WHERE collectivity_id = ? AND date_adhesion BETWEEN ? AND ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error counting new members", e);
        }
    }

    /**
     * PUSH DOWN PROCESSING: Récupération des statistiques pour toutes les collectivités
     */
    public Map<String, Object> getAllCollectivitiesStatistics(LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT " +
                "  c.id as collectivity_id, " +
                "  c.nom as collectivity_name, " +
                "  (SELECT COUNT(*) FROM member m2 WHERE m2.collectivity_id = c.id) as total_members, " +
                "  (SELECT COUNT(*) FROM member m3 WHERE m3.collectivity_id = c.id AND m3.date_adhesion BETWEEN ? AND ?) as new_members, " +
                "  (SELECT COALESCE(SUM(mf.montant), 0) FROM membership_fee mf WHERE mf.collectivity_id = c.id AND mf.statut = 'ACTIVE') as total_fee " +
                "FROM collectivity c";

        Map<String, Object> result = new HashMap<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String collectivityId = rs.getString("collectivity_id");
                Map<String, Object> stats = new HashMap<>();
                stats.put("collectivity_name", rs.getString("collectivity_name"));
                stats.put("new_members_count", rs.getInt("new_members"));

                int totalMembers = rs.getInt("total_members");
                int totalFee = rs.getInt("total_fee");

                // Calcul du nombre de membres à jour
                String paidSql = "SELECT COUNT(DISTINCT p.member_id) as paid_count " +
                        "FROM payment p " +
                        "WHERE p.collectivity_id = ? AND p.date_paiement BETWEEN ? AND ? " +
                        "GROUP BY p.member_id " +
                        "HAVING SUM(p.montant) >= ?";
                try (PreparedStatement paidStmt = conn.prepareStatement(paidSql)) {
                    paidStmt.setString(1, collectivityId);
                    paidStmt.setDate(2, Date.valueOf(startDate));
                    paidStmt.setDate(3, Date.valueOf(endDate));
                    paidStmt.setInt(4, totalFee);
                    ResultSet paidRs = paidStmt.executeQuery();
                    int paidCount = paidRs.next() ? paidRs.getInt("paid_count") : 0;

                    if (totalMembers > 0) {
                        stats.put("percentage_up_to_date", BigDecimal.valueOf((double) paidCount / totalMembers * 100));
                    } else {
                        stats.put("percentage_up_to_date", BigDecimal.ZERO);
                    }
                }
                result.put(collectivityId, stats);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all collectivities statistics", e);
        }
    }
}