package org.td2.exam.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.td2.exam.Model.Collectivity;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityRepository {

    private DataSource dataSource;

    @Autowired
    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Collectivity collectivity) {
        String sql = "INSERT INTO collectivity (id, numero, nom, localite, specialisation, date_creation) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivity.getId());
            stmt.setString(2, collectivity.getNumero());
            stmt.setString(3, collectivity.getNom());
            stmt.setString(4, collectivity.getLocalite());
            stmt.setString(5, collectivity.getSpecialisation());
            stmt.setDate(6, collectivity.getDateCreation() != null ? Date.valueOf(collectivity.getDateCreation()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting collectivity", e);
        }
    }

    public Collectivity findById(String id) {
        String sql = "SELECT * FROM collectivity WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Collectivity c = new Collectivity();
                c.setId(rs.getString("id"));
                c.setNumero(rs.getString("numero"));
                c.setNom(rs.getString("nom"));
                c.setLocalite(rs.getString("localite"));
                c.setSpecialisation(rs.getString("specialisation"));
                Date dateCreation = rs.getDate("date_creation");
                if (dateCreation != null) {
                    c.setDateCreation(dateCreation.toLocalDate());
                }
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding collectivity by id", e);
        }
    }

    public List<Collectivity> findAll() {
        String sql = "SELECT * FROM collectivity";
        List<Collectivity> collectivities = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Collectivity c = new Collectivity();
                c.setId(rs.getString("id"));
                c.setNumero(rs.getString("numero"));
                c.setNom(rs.getString("nom"));
                c.setLocalite(rs.getString("localite"));
                c.setSpecialisation(rs.getString("specialisation"));
                Date dateCreation = rs.getDate("date_creation");
                if (dateCreation != null) {
                    c.setDateCreation(dateCreation.toLocalDate());
                }
                collectivities.add(c);
            }
            return collectivities;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all collectivities", e);
        }
    }

    public void update(Collectivity collectivity) {
        String sql = "UPDATE collectivity SET numero = ?, nom = ?, localite = ?, specialisation = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivity.getNumero());
            stmt.setString(2, collectivity.getNom());
            stmt.setString(3, collectivity.getLocalite());
            stmt.setString(4, collectivity.getSpecialisation());
            stmt.setString(5, collectivity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating collectivity", e);
        }
    }

    public boolean existsByNumero(String numero) {
        String sql = "SELECT COUNT(*) FROM collectivity WHERE numero = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error checking existence by numero", e);
        }
    }

    public boolean existsByNom(String nom) {
        String sql = "SELECT COUNT(*) FROM collectivity WHERE nom = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error checking existence by nom", e);
        }
    }

    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM collectivity WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error checking existence by id", e);
        }
    }
}