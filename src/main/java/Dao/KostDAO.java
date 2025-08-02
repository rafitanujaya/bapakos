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
        String query = "INSERT INTO kosts(id, owner_id, name, location, price, description, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kost.getId());
            ps.setString(2, kost.getOwnerId());
            ps.setString(3, kost.getName());
            ps.setString(4, kost.getLocation());
            ps.setInt(5, kost.getPrice());
            ps.setString(6, kost.getDescription());
            ps.setBytes(7, kost.getImage()); // ⬅ tambahkan image di sini

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
                kost.setImage(rs.getBytes("image"));
                list.add(kost);
            }
            return list;
        }
    }

    public List<KostModel> findAllByOwnerId(String ownerId) throws SQLException {
        String query = "SELECT * FROM kosts WHERE owner_id = ?";
        List<KostModel> kostList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)) {
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
                kost.setImage(rs.getBytes("image"));
                kostList.add(kost);
            }
            return kostList;
        }
    }

    public List<KostModel> findByOwnerIdAndKeyword(String ownerId, String keyword) throws SQLException {
        List<KostModel> kostList = new ArrayList<>();
        String query = "SELECT * FROM kosts WHERE owner_id = ? AND (name LIKE ? OR location LIKE ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
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
                kost.setImage(rs.getBytes("image"));
                kostList.add(kost);
            }
        }
        return kostList;
    }

    public List<KostWithOwnerDTO> findAllByKeyword(String keyword) throws SQLException {
        List<KostWithOwnerDTO> kostList = new ArrayList<>();
        String query = "SELECT k.id, k.owner_id, k.name, k.location, k.price, k.description, k.image, u.username AS owner_name FROM kosts k JOIN users u ON k.owner_id = u.id WHERE k.name LIKE ? OR k.location LIKE ? OR u.username LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
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
                //kost.setImage(rs.getBytes("image")); // jika DTO-nya support image
                kostList.add(kost);
            }
        }
        return kostList;
    }

    public boolean updateById(KostModel kost) throws SQLException {
        String query = "UPDATE kosts SET name = ?, location = ?, price = ?, description = ?, image = ? WHERE id = ? AND owner_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, kost.getName());
            ps.setString(2, kost.getLocation());
            ps.setInt(3, kost.getPrice());
            ps.setString(4, kost.getDescription());
            ps.setBytes(5, kost.getImage());
            ps.setString(6, kost.getId());
            ps.setString(7, kost.getOwnerId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(String id) throws SQLException {
        String query = "DELETE FROM kosts WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public KostModel findById(String id) throws SQLException {
        String query = "SELECT * FROM kosts WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
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
                kost.setImage(rs.getBytes("image"));
                return kost;
            }
            return null;
        }
    }
}
