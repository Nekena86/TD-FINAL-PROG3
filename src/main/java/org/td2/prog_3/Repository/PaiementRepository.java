package org.td2.prog_3.Repository;


import org.springframework.stereotype.Repository;
import org.td2.prog_3.DataSource.DatabaseConnection;
import org.td2.prog_3.Model.Paiement;

import java.sql.*;

@Repository
public class PaiementRepository {

    public Paiement save(Paiement paiement) {
        String sql = "INSERT INTO paiement (montant, date_paiement, mode, membre_id, cotisation_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, paiement.getMontant());
            ps.setDate(2, java.sql.Date.valueOf(paiement.getDate()));
            ps.setString(3, paiement.getMode());
            ps.setLong(4, paiement.getMembreId());
            ps.setLong(5, paiement.getCotisationId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                paiement.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du paiement");
        }
        return paiement;
    }
}
