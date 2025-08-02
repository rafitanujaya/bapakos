package View.Controller.User;

import Config.DBConfig;
import Dao.KostDAO;
import Model.KostModel;
import Service.KostService;
import View.User.UserDashboardView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.List;

public class UserDashboardController {
    private UserDashboardView view;
    private KostService kostService;
    private VBox detailPanel;

    public UserDashboardController(UserDashboardView view) {
        this.view = view;
        detailPanel = new VBox();

        try {
            this.kostService = new KostService(new KostDAO(new DBConfig().getConnection()));
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error koneksi, misalnya dengan menampilkan pop-up
        }

        // Muat semua data kos saat controller pertama kali dibuat
        loadInitialData();
        // Anda bisa menambahkan attachEventHandlers() di sini jika ada event lain
    }

    /**
     * Mengambil semua data kos dari database dan menampilkannya.
     */
    private void loadInitialData() {
        try {
            // Panggil metode dari KostService untuk mengambil semua data kos
            List<KostModel> daftarKos = kostService.findAll();
            // Perbarui tampilan dengan data yang didapat
            updateKosList(daftarKos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Membersihkan dan mengisi ulang panel daftar kos dengan data baru.
     * @param daftarKos Daftar kos yang akan ditampilkan.
     */
    private void updateKosList(List<KostModel> daftarKos) {
        // Bersihkan kontainer kartu sebelum mengisi dengan data baru
        view.getKosListContainer().getChildren().clear();

        if (daftarKos == null || daftarKos.isEmpty()) {
            // Tampilkan pesan jika tidak ada hasil
            view.getKosListContainer().getChildren().add(new Label("Tidak ada kos yang ditemukan."));
        } else {
            // Tampilkan detail kos pertama sebagai default di panel kanan
            updateDetailPanel(daftarKos.get(0));

            // Loop melalui setiap data kos, buat kartunya, dan tambahkan event klik
            for (KostModel kos : daftarKos) {
                Node kosCard = view.createKosCard(kos);
                kosCard.setOnMouseClicked(e -> updateDetailPanel(kos));
                view.getKosListContainer().getChildren().add(kosCard);
            }
        }
    }

    /**
     * Mengisi panel detail di sisi kanan dengan informasi dari kos yang dipilih.
     */
    private void updateDetailPanel(KostModel kos) {
        // Bersihkan panel detail sebelum mengisi dengan data baru
        view.getDetailPanel().getChildren().clear();

        // Buat dan tampilkan detail kos yang dipilih
        Label nama = new Label(kos.getName());
        nama.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label alamat = new Label(kos.getLocation());
        ImageView fotoKos = new ImageView(new Image(new ByteArrayInputStream(kos.getImage())));
        fotoKos.setFitWidth(200); // misal
        Label harga = new Label("Rp " + kos.getPrice() + "/bulan");

        view.getDetailPanel().getChildren().addAll(fotoKos, nama, harga, alamat);

    }

    public VBox getDetailPanel() { return detailPanel; }
}