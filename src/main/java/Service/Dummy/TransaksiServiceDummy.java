package Service.Dummy;

import Model.Dummy.TransaksiModelDummy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.stream.Collectors;
import java.util.List;

public class TransaksiServiceDummy {

    private final ObservableList<TransaksiModelDummy> semuaAktivitas;

    public TransaksiServiceDummy() {
        // Satu daftar utama untuk menyimpan semua data
        semuaAktivitas = FXCollections.observableArrayList(
                // Ini adalah data untuk Halaman Booking (status "Pending")
                new TransaksiModelDummy(1, "Kos Elit", "Budi Santoso", "Rp 2.500.000,00", "Pending"),
                new TransaksiModelDummy(2, "Wisma Mawar", "Cindy Aulia", "Rp 1.800.000,00", "Pending"),

                // Ini adalah data untuk Halaman Transaksi
                new TransaksiModelDummy(3, "Kos Jamet", "Nico Hamba Allah", "Rp 1.500.000,00", "Diterima"),
                new TransaksiModelDummy(4, "Pondok Indah", "John Doe", "Rp 1.200.000,00", "Ditolak"),
                new TransaksiModelDummy(5, "Graha Kencana", "Jane Smith", "Rp 1.900.000,00", "Paid")
        );
    }

    public ObservableList<TransaksiModelDummy> searchPendingBookings(String keyword) {
        // Ambil dulu semua booking yang pending
        ObservableList<TransaksiModelDummy> pendingBookings = getPendingBookings();

        if (keyword == null || keyword.trim().isEmpty()) {
            return pendingBookings;
        }

        String lowerCaseKeyword = keyword.toLowerCase();

        // Saring hasil dari daftar pending
        List<TransaksiModelDummy> filteredList = pendingBookings.stream()
                .filter(booking ->
                        booking.getNamaPenyewa().toLowerCase().contains(lowerCaseKeyword) ||
                                booking.getNamaKos().toLowerCase().contains(lowerCaseKeyword)
                )
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(filteredList);
    }

    public ObservableList<TransaksiModelDummy> searchHistoryTransactions(String keyword) {
        // Ambil dulu semua riwayat transaksi
        ObservableList<TransaksiModelDummy> history = getHistoryTransactions();

        if (keyword == null || keyword.trim().isEmpty()) {
            return history;
        }

        String lowerCaseKeyword = keyword.toLowerCase();

        // Saring hasil dari daftar riwayat
        List<TransaksiModelDummy> filteredList = history.stream()
                .filter(trx ->
                        trx.getNamaPenyewa().toLowerCase().contains(lowerCaseKeyword) ||
                                trx.getNamaKos().toLowerCase().contains(lowerCaseKeyword)
                )
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(filteredList);
    }

    /**
     * Mengambil HANYA data booking yang masih menunggu persetujuan.
     * @return Daftar booking yang berstatus "Pending".
     */
    public ObservableList<TransaksiModelDummy> getPendingBookings() {
        List<TransaksiModelDummy> pendingList = semuaAktivitas.stream()
                .filter(trx -> "Pending".equalsIgnoreCase(trx.getStatus()))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(pendingList);
    }

    /**
     * Mengambil HANYA riwayat transaksi (yang sudah tidak pending).
     * @return Daftar transaksi yang statusnya bukan "Pending".
     */
    public ObservableList<TransaksiModelDummy> getHistoryTransactions() {
        List<TransaksiModelDummy> historyList = semuaAktivitas.stream()
                .filter(trx -> !"Pending".equalsIgnoreCase(trx.getStatus()))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(historyList);
    }
}