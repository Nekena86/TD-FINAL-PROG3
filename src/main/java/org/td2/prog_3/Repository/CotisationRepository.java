package org.td2.prog_3.Repository;

import org.springframework.stereotype.Repository;
import org.td2.prog_3.DataSource.DatabaseConnection;
import org.td2.prog_3.Model.Cotisation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CotisationRepository {

    public Cotisation save(Cotisation cotisation) {
        String sql = "INSERT INTO cotisation (montant, date_encaissement, membre_id, collectivite_id, type, mode_paiement) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, cotisation.getMontant());
            ps.setDate(2, java.sql.Date.valueOf(cotisation.getDate()));
            ps.setLong(3, cotisation.getMembreId());
            ps.setLong(4, cotisation.getCollectiviteId());
            ps.setString(5, cotisation.getType());
            ps.setString(6, cotisation.getModePaiement());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cotisation.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la création de la cotisation");
        }
        return cotisation;
    }

    public List<Cotisation> findByMembreId(Long membreId) {
        List<Cotisation> cotisations = new ArrayList<>();
        String sql = "SELECT * FROM cotisation WHERE membre_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, membreId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cotisation c = new Cotisation();
                c.setId(rs.getLong("id"));
                c.setMontant(rs.getDouble("montant"));
                c.setDate(rs.getDate("date_encaissement") != null ? rs.getDate("date_encaissement").toLocalDate() : null);
                c.setMembreId(rs.getLong("membre_id"));
                c.setCollectiviteId(rs.getLong("collectivite_id"));
                c.setType(rs.getString("type"));
                c.setModePaiement(rs.getString("mode_paiement"));
                cotisations.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cotisations;
    }
}
