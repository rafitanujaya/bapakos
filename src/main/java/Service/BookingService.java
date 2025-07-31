package Service;

import Dao.BookingDAO;
import Dao.KostDAO;
import Model.BookingModel;
import Model.KostModel;
import Model.UserModel;

import java.sql.SQLException;
import java.util.UUID;

public class BookingService {
    private KostDAO kostDAO;
    private BookingDAO bookingDAO;

    public BookingService(KostDAO kostDAO, BookingDAO bookingDAO) {
        this.kostDAO = kostDAO;
        this.bookingDAO = bookingDAO;
    }

    public boolean create(KostModel kost, UserModel user) throws SQLException {
        KostModel currentKost = kostDAO.findById(kost.getId());
        if (currentKost == null) {
            return false;
        }

        BookingModel booking = new BookingModel();
        booking.setId(UUID.randomUUID().toString());
        booking.setKostId(kost.getId());
        booking.setUserId(user.getId());

        return this.bookingDAO.createByKostId(booking.getId(), booking.getKostId(), booking.getUserId());

    }

//    public boolean approve(KostModel kost) throws SQLException {
//
//    }
//
//    public boolean reject(KostModel kost) throws SQLException {
//
//    }
}
