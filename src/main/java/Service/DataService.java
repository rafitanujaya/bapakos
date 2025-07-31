package Service;

import Model.BookingDummy;
import Model.TransaksiDummy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataService {

    // Nanti, metode ini akan mengambil data dari database
    public ObservableList<TransaksiDummy> getRecentTransactions() {
        return FXCollections.observableArrayList(
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Madrid Home", "Rent", "14-04-2024", "$3.500.00", "Check Out"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Javae Home", "Buy", "14-04-2024", "$23.500.00", "Waiting"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Kebumen Home", "Buy", "14-04-2024", "$25.500.00", "Paid"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new TransaksiDummy("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed")
        );
    }

    // Nanti, metode ini juga akan mengambil data dari database
    public ObservableList<BookingDummy> getPendingBookings() {
        return FXCollections.observableArrayList(
                new BookingDummy("Alice", "Kamar 02C"),
                new BookingDummy("Bob", "Kamar 08D")
        );
    }
}
