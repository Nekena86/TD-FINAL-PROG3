package org.td2.exam.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.td2.exam.Model.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {

    private DataSource dataSource;

    @Autowired
    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Member member) {
        String sql = "INSERT INTO member (id, collectivity_id, nom, prenom, date_naissance, genre, adresse, profession, telephone, email, occupation, date_adhesion, referents) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getId());
            stmt.setString(2, member.getCollectivityId());
            stmt.setString(3, member.getNom());
            stmt.setString(4, member.getPrenom());
            stmt.setDate(5, member.getDateNaissance() != null ? Date.valueOf(member.getDateNaissance()) : null);
            stmt.setString(6, member.getGenre());
            stmt.setString(7, member.getAdresse());
            stmt.setString(8, member.getProfession());
            stmt.setString(9, member.getTelephone());
            stmt.setString(10, member.getEmail());
            stmt.setString(11, member.getOccupation());
            stmt.setDate(12, member.getDateAdhesion() != null ? Date.valueOf(member.getDateAdhesion()) : null);
            stmt.setString(13, member.getReferents());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting member", e);
        }
    }

    public Member findById(String id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Member m = new Member();
                m.setId(rs.getString("id"));
                m.setCollectivityId(rs.getString("collectivity_id"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                Date dateNaissance = rs.getDate("date_naissance");
                if (dateNaissance != null) {
                    m.setDateNaissance(dateNaissance.toLocalDate());
                }
                m.setGenre(rs.getString("genre"));
                m.setAdresse(rs.getString("adresse"));
                m.setProfession(rs.getString("profession"));
                m.setTelephone(rs.getString("telephone"));
                m.setEmail(rs.getString("email"));
                m.setOccupation(rs.getString("occupation"));
                Date dateAdhesion = rs.getDate("date_adhesion");
                if (dateAdhesion != null) {
                    m.setDateAdhesion(dateAdhesion.toLocalDate());
                }
                m.setReferents(rs.getString("referents"));
                return m;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding member by id", e);
        }
    }

    public List<Member> findByCollectivityId(String collectivityId) {
        String sql = "SELECT * FROM member WHERE collectivity_id = ?";
        List<Member> members = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getString("id"));
                m.setCollectivityId(rs.getString("collectivity_id"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                Date dateNaissance = rs.getDate("date_naissance");
                if (dateNaissance != null) {
                    m.setDateNaissance(dateNaissance.toLocalDate());
                }
                m.setGenre(rs.getString("genre"));
                m.setAdresse(rs.getString("adresse"));
                m.setProfession(rs.getString("profession"));
                m.setTelephone(rs.getString("telephone"));
                m.setEmail(rs.getString("email"));
                m.setOccupation(rs.getString("occupation"));
                Date dateAdhesion = rs.getDate("date_adhesion");
                if (dateAdhesion != null) {
                    m.setDateAdhesion(dateAdhesion.toLocalDate());
                }
                m.setReferents(rs.getString("referents"));
                members.add(m);
            }
            return members;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding members by collectivity id", e);
        }
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        List<Member> members = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getString("id"));
                m.setCollectivityId(rs.getString("collectivity_id"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                Date dateNaissance = rs.getDate("date_naissance");
                if (dateNaissance != null) {
                    m.setDateNaissance(dateNaissance.toLocalDate());
                }
                m.setGenre(rs.getString("genre"));
                m.setAdresse(rs.getString("adresse"));
                m.setProfession(rs.getString("profession"));
                m.setTelephone(rs.getString("telephone"));
                m.setEmail(rs.getString("email"));
                m.setOccupation(rs.getString("occupation"));
                Date dateAdhesion = rs.getDate("date_adhesion");
                if (dateAdhesion != null) {
                    m.setDateAdhesion(dateAdhesion.toLocalDate());
                }
                m.setReferents(rs.getString("referents"));
                members.add(m);
            }
            return members;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all members", e);
        }
    }

    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM member WHERE id = ?";
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