package View.Controller;

import Service.KosServiceDummy;
import View.ViewManager;
import View.Admin.AdminPageView;
import View.Admin.AdminDashboardView;
import View.Admin.TambahKosMenuView; // Pastikan semua view ini ada
import View.Admin.TransaksiMenuView;
import View.Admin.BookingMenuView;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.util.Arrays;
import java.util.List;

public class AdminPageController {

    private final BorderPane rootPane;
    private final List<Button> menuButtons;
    private final ViewManager viewManager;

    // Field untuk menyimpan setiap panel konten (Lazy Initialization)
    private Parent dashboardContent;
    private Parent transaksiContent;
    private Parent orderContent;
    private Parent tambahKosContent;
    // Tambahkan field untuk view lain jika perlu

    public AdminPageController(AdminPageView view, ViewManager viewManager) {
        this.rootPane = view.getRootPane();
        this.viewManager = viewManager;
        this.menuButtons = Arrays.asList(
                view.getDashboardBtn(), view.getTransactionBtn(), view.getOrderBtn(),
                view.getAddKosBtn(), view.getEditKosBtn(), view.getDeleteKosBtn()
        );

        attachEventHandlers(view);
        view.getDashboardBtn().fire();
    }

    private void attachEventHandlers(AdminPageView view) {
        // --- Event Handler untuk Tombol Menu Sidebar ---

        // Di dalam AdminPageController.java
        view.getDashboardBtn().setOnAction(event -> {
            setActiveButton(view.getDashboardBtn());
            if (dashboardContent == null) {
                AdminDashboardView adminDashboardView = new AdminDashboardView();

                // Buat controller-nya terlebih dahulu
                AdminDashboardController dashboardController = new AdminDashboardController(adminDashboardView, new KosServiceDummy(), this.viewManager);

                // Berikan controller tersebut saat memanggil getView()
                dashboardContent = adminDashboardView.getView();
            }
            rootPane.setCenter(dashboardContent);
        });

        view.getTransactionBtn().setOnAction(event -> {
            setActiveButton(view.getTransactionBtn());
            if (transaksiContent == null) {
                transaksiContent = new TransaksiMenuView().getView();
            }
            rootPane.setCenter(transaksiContent);
        });

        view.getOrderBtn().setOnAction(event -> {
            setActiveButton(view.getOrderBtn());
            if (orderContent == null) {
                orderContent = new BookingMenuView().getView();
            }
            rootPane.setCenter(orderContent);
        });

        // Aktifkan event untuk tombol Tambah Kos
        view.getAddKosBtn().setOnAction(event -> {
            setActiveButton(view.getAddKosBtn());
            if (tambahKosContent == null) {
                tambahKosContent = new TambahKosMenuView().getView();
            }
            rootPane.setCenter(tambahKosContent);
        });

        // Event handler untuk tombol Logout
        view.getLogoutBtn().setOnAction(event -> {
            viewManager.showLoginView();
        });
    }


    private void setActiveButton(Button activeButton) {
        for (Button button : menuButtons) {
            button.getStyleClass().remove("active");
        }
        activeButton.getStyleClass().add("active");
    }
}