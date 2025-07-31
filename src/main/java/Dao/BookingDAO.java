package Dao;

import Model.BookingModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookingDAO {
    private final Connection conn;

    public BookingDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean createByKostId(String id, String kostId, String userId) throws SQLException {
        String query = "INSERT INTO bookings(id, kost_id, user_id) VALUES (?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            ps.setString(2, kostId);
            ps.setString(3, userId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateStatus(String id, BookingModel.Status status) throws SQLException {
        String query = "UPDATE bookings SET status = ? WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status.toString().toLowerCase());
            ps.setString(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<BookingModel> getByOwnerIdAndKostId(String ownerId, String kostId) throws SQLException {
        String query = "SELECT * FROM bookings WHERE owner = ?";
    }
}
