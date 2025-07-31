package Service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.Booking;
import Model.Transaksi;

public class DataService {

    // Nanti, metode ini akan mengambil data dari database
    public ObservableList<Transaksi> getRecentTransactions() {
        return FXCollections.observableArrayList(
                new Transaksi("/img/bapa-kos-icon-png.png", "Madrid Home", "Rent", "14-04-2024", "$3.500.00", "Check Out"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Javae Home", "Buy", "14-04-2024", "$23.500.00", "Waiting"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Kebumen Home", "Buy", "14-04-2024", "$25.500.00", "Paid"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed"),
                new Transaksi("/img/bapa-kos-icon-png.png", "Tropical Home", "Rent", "14-04-2024", "$2.500.00", "Failed")
        );
    }

    // Nanti, metode ini juga akan mengambil data dari database
    public ObservableList<Booking> getPendingBookings() {
        return FXCollections.observableArrayList(
                new Booking("Alice", "Kamar 02C"),
                new Booking("Bob", "Kamar 08D")
        );
    }
}
