package View.Controller;

import Model.KosDummy;
import Service.KosServiceDummy;
import View.Admin.AdminDashboardView;
import View.Admin.AdminPageView;
import View.ViewManager;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class AdminDashboardController {
    private AdminDashboardView view;
    private KosServiceDummy dataService;
    private ViewManager viewManager;

    public AdminDashboardController(AdminDashboardView view, KosServiceDummy dataService, ViewManager viewManager) {
        this.view = view;
        this.dataService = dataService;
        this.viewManager = viewManager;

        loadInitialData();
        attachEventHandlers();
    }

    // Metode ini tugasnya mengisi data ke View
    private void loadInitialData() {
        ObservableList<KosDummy> daftarKos = dataService.getAllKos();

        // Ambil kontainer yang kosong dari View
        VBox rowsContainer = view.getRowsContainer();
        rowsContainer.getChildren().clear(); // Bersihkan dulu

        // Loop data, minta View untuk membuat baris, lalu masukkan ke kontainer
        for (KosDummy kos : daftarKos) {
            rowsContainer.getChildren().add(view.createKosDataRow(kos, this));
        }
    }

    private void attachEventHandlers() {
        view.getCreateButton().setOnAction(event -> {
            System.out.println("Tombol 'Buat' diklik!");
        });

        view.getSearchField().setOnAction(event -> {
            System.out.println("Mencari kos...");
        });
    }

    // Metode ini akan dipanggil oleh View saat tombol Edit di sebuah baris diklik
    public void handleEditKos(KosDummy kos) {
        System.out.println("Tombol Edit diklik untuk: " + kos.getNama());
        // Di sini Anda bisa navigasi ke halaman Ubah Kos sambil membawa data 'kos'
        // viewManager.showUbahKosPage(kos);
    }

    // Metode ini akan dipanggil oleh View saat tombol Hapus di sebuah baris diklik
    public void handleDeleteKos(KosDummy kos) {
        System.out.println("Tombol Hapus diklik untuk: " + kos.getNama());
        // Di sini Anda bisa menampilkan konfirmasi hapus, lalu menghapus data
    }

}