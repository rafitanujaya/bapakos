package Dao;

import Dto.KostWithOwnerDTO;
import Model.KostModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KostDAO {
    private final Connection conn;

    public KostDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(KostModel kost) throws SQLException {
        String query = "INSERT INTO kosts(id, owner_id, name, location, price, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kost.getId());
            ps.setString(2, kost.getOwnerId());
            ps.setString(3, kost.getName());
            ps.setString(4, kost.getLocation());
            ps.setInt(5, kost.getPrice());
            ps.setString(6, kost.getDescription());

            int result = ps.executeUpdate();
            return result > 0;
        }
    }

    public List<KostModel> findAll() throws SQLException {
        String query = "SELECT * FROM kosts";
        List<KostModel> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KostModel kost = new KostModel();
                kost.setId(rs.getString("id"));
                kost.setOwnerId(rs.getString("owner_id"));
                kost.setName(rs.getString("name"));
                kost.setLocation(rs.getString("location"));
                kost.setPrice(rs.getInt("price"));
                kost.setDescription(rs.getString("description"));
                list.add(kost);
            }
            return list;
        }
    }

    public List<KostModel> findAllByOwnerId(String ownerId) throws SQLException {
        String query = "SELECT * FROM kosts WHERE ownerId = ?";
        List<KostModel> kostList = new ArrayList<>();

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KostModel kost = new KostModel();
                kost.setId(rs.getString("id"));
                kost.setOwnerId(rs.getString("owner_id"));
                kost.setName(rs.getString("name"));
                kost.setLocation(rs.getString("location"));
                kost.setPrice(rs.getInt("price"));
                kost.setDescription(rs.getString("description"));
                kostList.add(kost);
            }
            return kostList;
        }
    }

    public List<KostModel> findByOwnerIdAndKeyword(String ownerId, String keyword) throws SQLException {
        List<KostModel> kostList = new ArrayList<>();
        String query = "SELECT * FROM kosts WHERE ownerId = ? AND name LIKE ? OR location LIKE ?";

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, ownerId);
            ps.setString(2, pattern);
            ps.setString(3, pattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KostModel kost = new KostModel();
                kost.setId(rs.getString("id"));
                kost.setOwnerId(rs.getString("owner_id"));
                kost.setName(rs.getString("name"));
                kost.setLocation(rs.getString("location"));
                kost.setPrice(rs.getInt("price"));
                kost.setDescription(rs.getString("description"));
                kostList.add(kost);
            }
        }
        return kostList;
    }

    public List<KostWithOwnerDTO> findAllByKeyword(String keyword) throws SQLException {
        List<KostWithOwnerDTO> kostList = new ArrayList<>();
        String query = "SELECT k.id, k.name, k.location, k.price, k.description, u.username AS owner_name FROM kosts k JOIN users u ON k.owner_id WHERE 1=1 AND k.name LIKE ? OR k.location LIKE ? OR u.username LIKE ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, keyword);
            ps.setString(2, pattern);
            ps.setString(3, pattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KostWithOwnerDTO kost = new KostWithOwnerDTO();
                kost.setId(rs.getString("id"));
                kost.setOwnerId(rs.getString("owner_id"));
                kost.setName(rs.getString("name"));
                kost.setLocation(rs.getString("location"));
                kost.setPrice(rs.getInt("price"));
                kost.setDescription(rs.getString("description"));
                kost.setOwnerName(rs.getString("owner_name"));
                kostList.add(kost);
            }
        }
        return kostList;
    }

    public boolean updateById(KostModel kost) throws SQLException {
        String query = "UPDATE kosts SET name = ?, location = ?, price = ?, description = ? WHERE id = ? AND owner_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kost.getName());
            ps.setString(2, kost.getLocation());
            ps.setInt(3, kost.getPrice());
            ps.setString(4, kost.getDescription());
            ps.setString(5, kost.getId());
            ps.setString(6, kost.getOwnerId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(String id) throws SQLException {
        String query = "DELETE FROM kosts WHERE id = ? AND owner_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            ps.setString(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public KostModel findById(String id) throws SQLException {
        String query = "SELECT * FROM kosts WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KostModel kost = new KostModel();
                kost.setId(rs.getString("id"));
                kost.setOwnerId(rs.getString("owner_id"));
                kost.setName(rs.getString("name"));
                kost.setLocation(rs.getString("location"));
                kost.setPrice(rs.getInt("price"));
                kost.setDescription(rs.getString("description"));
                return kost;
            }
            return null;
        }
    }
}
