package View.Controller.Admin;

import Config.DBConfig;
import Dao.KostDAO;
import Model.KostModel;
import Model.UserModel;
import Service.Dummy.KosServiceDummy;
import Service.Dummy.TransaksiServiceDummy;
import Service.KostService;
import Session.Session;
import View.ViewManager;
import View.Admin.AdminPageView;
import View.Admin.AdminDashboardView;
import View.Admin.TambahKosMenuView; // Pastikan semua view ini ada
import View.Admin.TransaksiMenuView;
import View.Admin.BookingMenuView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AdminPageController {

    private final BorderPane rootPane;
    private List<Button> menuButtons;
    private final ViewManager viewManager;
    private AdminPageView view;
    private AdminDashboardController dashboardController;

    // Field untuk menyimpan setiap panel konten (Lazy Initialization)
    private Parent dashboardContent;
    private Parent transaksiContent;
    private Parent bookingContent;
    private Parent tambahKosContent;
    // Tambahkan field untuk view lain jika perlu

    public AdminPageController(AdminPageView view, ViewManager viewManager) {
        this.rootPane = view.getRootPane();
        this.viewManager = viewManager;
        this.view = view;
        this.menuButtons = Arrays.asList(
                view.getDashboardBtn(),
                view.getTransactionBtn(),
                view.getOrderBtn(),
                view.getAddKosBtn()
        );

        loadUserData(view);
        attachEventHandlers(view);
        view.getDashboardBtn().fire();
    }

    private void attachEventHandlers(AdminPageView view) {
        // --- Event Handler untuk Tombol Menu Sidebar ---
        this.menuButtons = Arrays.asList(
                view.getDashboardBtn(),
                view.getTransactionBtn(),
                view.getOrderBtn(),
                view.getAddKosBtn()
        );

        // Di dalam AdminPageController.java
        view.getDashboardBtn().setOnAction(event -> {
            setActiveButton(view.getDashboardBtn());
            if (dashboardContent == null) {
                KostService dataService = null;
                try {
                    dataService = new KostService(new KostDAO(new DBConfig().getConnection()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                AdminDashboardView adminDashboardView = new AdminDashboardView();

                dashboardController = new AdminDashboardController(adminDashboardView, dataService, this.viewManager, this);

                dashboardContent = adminDashboardView.getView();
            }
            rootPane.setCenter(dashboardContent);
        });

        // Di dalam AdminPageController.java
        view.getTransactionBtn().setOnAction(event -> {
            setActiveButton(view.getTransactionBtn());
            if (transaksiContent == null) {
                TransaksiMenuView transaksiView = new TransaksiMenuView();
                // Hubungkan dengan controller-nya
                new AdminTransaksiController(transaksiView, new TransaksiServiceDummy());
                transaksiContent = transaksiView.getView();
            }
            rootPane.setCenter(transaksiContent);
        });

        view.getOrderBtn().setOnAction(event -> {
            setActiveButton(view.getOrderBtn());
            if (bookingContent == null) {
                BookingMenuView bookingView = new BookingMenuView();
                // Hubungkan dengan controller-nya
                new AdminBookingController(bookingView, new TransaksiServiceDummy());
                bookingContent = bookingView.getView();
            }
            rootPane.setCenter(bookingContent);
        });

        // Di dalam AdminPageController.java
        view.getAddKosBtn().setOnAction(event -> {
            setActiveButton(view.getAddKosBtn());
            TambahKosMenuView formView = new TambahKosMenuView();
            // Kirim 'null' karena ini mode tambah baru
            new TambahKosController(formView, viewManager, this, null);
            rootPane.setCenter(formView.getView());
        });

        // Event handler untuk tombol Logout
        view.getLogoutBtn().setOnAction(event -> {
            viewManager.showLoginView();
        });
    }

    public void refreshDashboardData() {
        if (dashboardController != null) {
            dashboardController.loadInitialData();
        }
    }

    public void showTambahKosPage() {
        // Atur tombol "Tambah Kos" menjadi aktif di sidebar
        setActiveButton(view.getAddKosBtn());

        // Gunakan pola lazy-initialization jika perlu
        if (tambahKosContent == null) {
            TambahKosMenuView tambahKosView = new TambahKosMenuView();
            // Hubungkan dengan controllernya jika ada
            // new TambahKosController(tambahKosView, ...);
            tambahKosContent = tambahKosView.getView();
        }

        // Ganti panel tengah dengan view Tambah Kos
        rootPane.setCenter(tambahKosContent);
    }

    public void showUbahKosPage(KostModel kos) {
        TambahKosMenuView formView = new TambahKosMenuView();
        new TambahKosController(formView, viewManager, this, kos);

        rootPane.setCenter(formView.getView());
    }

    private void setActiveButton(Button activeButton) {
        // Loop melalui daftar tombol yang sudah disimpan
        for (Button button : menuButtons) {
            button.getStyleClass().remove("active");
        }
        activeButton.getStyleClass().add("active");
    }

    private void loadUserData(AdminPageView view) {
        new Session();
        UserModel currentUser = Session.get();
        if (currentUser != null) {
            // Gunakan getter untuk mengubah teks di sidebar
            view.getProfileNameLabel().setText(currentUser.getUsername());
        }
    }

}