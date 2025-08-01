package Service;

import Model.TransaksiModelDummy;
import Model.BookingModelDummy;
import Model.KosDummy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KosServiceDummy {

    public ObservableList<KosDummy> getAllKos() {
        return FXCollections.observableArrayList(
                new KosDummy(1, "Kos Jamet", "Jl. Tubagus 5 No 9", "Rp 1.500.000,00"),
                new KosDummy(2, "Kos Mawar", "Jl. Mawar No 10", "Rp 1.200.000,00"),
                new KosDummy(3, "Kos Anggrek", "Jl. Anggrek No 1", "Rp 1.800.000,00"),
                new KosDummy(4, "Kos Melati", "Jl. Melati No 22", "Rp 1.000.000,00"),
                new KosDummy(5, "Kos Dahlia", "Jl. Dahlia No 3", "Rp 1.600.000,00"),
                new KosDummy(6, "Kos Asoka", "Jl. Asoka No 15", "Rp 1.350.000,00"),
                new KosDummy(5, "Kos Dahlia", "Jl. Dahlia No 3", "Rp 1.600.000,00"),
                new KosDummy(5, "Kos Dahlia", "Jl. Dahlia No 3", "Rp 1.600.000,00")
        );
    }

    public ObservableList<BookingModelDummy> getAllBookings() {
        return FXCollections.observableArrayList(
                new BookingModelDummy(1, "Kos Jamet", "Nico Hamba Allah", "Rp 1.500.000,00"),
                new BookingModelDummy(2, "Kos Mawar", "Jane Doe", "Rp 1.200.000,00"),
                new BookingModelDummy(3, "Kos Anggrek", "John Smith", "Rp 1.800.000,00")
                // Tambahkan data booking lain di sini
        );
    }

    public ObservableList<TransaksiModelDummy> getAllTransactions() {
        return FXCollections.observableArrayList(
                new TransaksiModelDummy(1, "Kos Jamet", "Nico Hamba Allah", "Rp 1.500.000,00", "Pending"),
                new TransaksiModelDummy(2, "Kos Mawar", "Jane Doe", "Rp 1.200.000,00", "Paid"),
                new TransaksiModelDummy(3, "Kos Anggrek", "John Smith", "Rp 1.800.000,00", "Diterima"),
                new TransaksiModelDummy(4, "Kos Melati", "Peter Jones", "Rp 1.000.000,00", "Ditolak")
                // Tambahkan data transaksi lain di sini
        );
    }
}
