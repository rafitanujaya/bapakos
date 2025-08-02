package View.Controller.Admin;

import Config.DBConfig;
import Dao.KostDAO;
import Model.KostModel;
import Model.UserModel;
import Service.KostService;
import Session.Session;
import View.Admin.AdminDashboardView;
import View.ViewManager;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminDashboardController {
    private AdminDashboardView view;
    private KostService kostService;
    private ViewManager viewManager;
    private KostService dataService;
    private AdminPageController adminPageController;


    public AdminDashboardController(AdminDashboardView view, KostService dataService, ViewManager viewManager, AdminPageController adminPageController) {
        this.view = view;
        this.dataService = dataService;
        this.viewManager = viewManager;
        this.adminPageController = adminPageController;

        try {
            this.kostService = new KostService(new KostDAO(new DBConfig().getConnection()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadInitialData();
        loadUserData();
        attachEventHandlers();
    }

    public void loadInitialData() {
        UserModel currentUser = Session.get();
        if (currentUser == null) {
            System.err.println("Tidak ada user yang login!");
            return;
        }

        try {
            // 1. Ambil data kos dari database
            List<KostModel> daftarKosDariDB = kostService.findAllByOwnerId(currentUser.getId());

            // 2. Bersihkan tabel yang ada di view
            view.getRowsContainer().getChildren().clear();

            // 3. Loop data dan masukkan ke tabel
            int number = 1;
            for (KostModel kos : daftarKosDariDB) {
                view.getRowsContainer().getChildren().add(view.createKosDataRow(number++, kos, this));
            }
        } catch (SQLException e) {
            System.err.println("Gagal memuat data kos dari database.");
            e.printStackTrace();
        }
    }

    private void performSearch() {
        UserModel currentUser = Session.get();
        String keyword = view.getSearchField().getText();
        if (currentUser == null) return;

        try {
            // Panggil metode pencarian dari service (Anda perlu membuatnya di KostDAO & KostService)
            // List<KostModel> hasilPencarian = kostService.findByOwnerIdAndKeyword(currentUser.getId(), keyword);
            // updateTable(FXCollections.observableArrayList(hasilPencarian));
            System.out.println("Fitur pencarian belum diimplementasikan di Service/DAO.");
        } catch (Exception e) { // Ganti SQLException jika sudah ada metodenya
            e.printStackTrace();
        }
    }

    private void updateTable(ObservableList<KostModel> daftarKos) {
        view.getRowsContainer().getChildren().clear();
        int number = 1;
        for (KostModel kos : daftarKos) {
            view.getRowsContainer().getChildren().add(view.createKosDataRow(number++, kos, this));
        }
    }

    private void attachEventHandlers() {
        view.getSearchButton().setOnAction(event -> performSearch());
        view.getSearchField().setOnAction(event -> performSearch());

        view.getSearchButton().setOnAction(event -> {
            System.out.println("Tombol 'Buat' diklik.");
            // Navigasi ke halaman Tambah Kos
            adminPageController.showTambahKosPage();
        });
    }

    public void handleEditKos(KostModel kos) {
        System.out.println("Tombol Edit diklik untuk: " + kos.getName());
        // Panggil metode navigasi di controller utama
        adminPageController.showUbahKosPage(kos);
    }

    public void handleDeleteKos(KostModel kos) {
        // 1. Buat pop-up konfirmasi
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText("Apakah Anda yakin ingin menghapus kos ini?");
        alert.setContentText(kos.getName() + " (" + kos.getLocation() + ")");

        // 2. Tampilkan pop-up dan tunggu respons dari pengguna
        Optional<ButtonType> result = alert.showAndWait();

        // 3. Cek jika pengguna menekan tombol "OK" (Konfirmasi)
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Jika dikonfirmasi, jalankan logika hapus
            try {
                boolean isDeleted = kostService.deleteById(kos);
                if (isDeleted) {
                    System.out.println("Data berhasil dihapus.");
                    loadInitialData(); // Muat ulang data untuk me-refresh tabel
                } else {
                    System.out.println("Gagal menghapus data.");
                    // Tampilkan pesan error jika perlu
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Jika pengguna menekan "Batal" atau menutup pop-up
            System.out.println("Aksi hapus dibatalkan.");
        }
    }

    private void loadUserData() {
        UserModel currentUser = Session.get();
        if (currentUser != null) {
            view.getWelcomeLabel().setText("Selamat Datang " + currentUser.getUsername());
        }
    }
}