package View.Controller;

import View.ViewManager;
import View.Admin.AdminPageView;
import View.Admin.OrderMenuView; // Ganti/buat view yang sesuai
import View.Admin.AdminDashboardView;
import View.Admin.TransaksiMenuView; // Ganti/buat view yang sesuai
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.util.Arrays;
import java.util.List;

public class AdminPageController {

    private final BorderPane rootPane;
    private final List<Button> menuButtons;
    private final ViewManager viewManager;

    // Field untuk menyimpan panel yang sudah dibuat (Lazy Initialization)
    private Parent dashboardContent;
    private Parent transaksiContent;
    private Parent bookingContent;

    public AdminPageController(AdminPageView view, ViewManager viewManager) {
        this.rootPane = view.getRootPane();
        this.viewManager = viewManager;
        // Sesuaikan dengan tombol yang ada di AdminPageView Anda
        this.menuButtons = Arrays.asList(
                view.getDashboardBtn(), view.getTransactionBtn(), view.getOrderBtn(),
                view.getAddKosBtn(), view.getEditKosBtn(), view.getDeleteKosBtn()
        );

        // --- Event Handler ---
        attachEventHandlers(view);

        // Atur tombol dashboard sebagai yang aktif saat pertama kali dibuka
        view.getDashboardBtn().fire();
    }

    private void attachEventHandlers(AdminPageView view) {
        // Event Handler untuk Profil
        view.getProfileBtn().setOnAction(event -> {
            ContextMenu profileMenu = createProfileMenu();
            profileMenu.show(view.getProfileBtn(), Side.BOTTOM, 0, 10);
        });

        // Event Handler untuk Tombol Sidebar
        view.getDashboardBtn().setOnAction(event -> {
            setActiveButton(view.getDashboardBtn());
            // Cek jika konten belum dibuat, maka buat sekali saja
            if (dashboardContent == null) {
                AdminDashboardView adminDashboardView = new AdminDashboardView();
                dashboardContent = adminDashboardView.getView();
            }
            rootPane.setCenter(dashboardContent);
        });

        view.getTransactionBtn().setOnAction(event -> {
            setActiveButton(view.getTransactionBtn());
            if (transaksiContent == null) {
                TransaksiMenuView transaksiView = new TransaksiMenuView(); // Buat view untuk transaksi
                transaksiContent = transaksiView.getView();
            }
            rootPane.setCenter(transaksiContent);
        });

        view.getOrderBtn().setOnAction(event -> {
            setActiveButton(view.getOrderBtn());
            if (bookingContent == null) {
                OrderMenuView orderMenuView = new OrderMenuView(); // Buat view untuk booking
                bookingContent = orderMenuView.getView();
            }
            rootPane.setCenter(bookingContent);
        });

        // Tombol CRUD yang dinonaktifkan tidak perlu event handler untuk saat ini
    }

    private void setActiveButton(Button activeButton) {
        for (Button button : menuButtons) {
            button.getStyleClass().remove("active");
        }
        activeButton.getStyleClass().add("active");
    }

    private ContextMenu createProfileMenu() {
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getStyleClass().add("profile-menu");

        ImageView accountIcon = new ImageView(new Image("/img/profile-icon.png"));
        accountIcon.setFitWidth(18);
        accountIcon.setFitHeight(18);
        MenuItem accountItem = new MenuItem("Akun Saat Ini", accountIcon);

        ImageView logoutIcon = new ImageView(new Image("/img/logout-icon.png"));
        logoutIcon.setFitWidth(18);
        logoutIcon.setFitHeight(18);
        MenuItem logoutItem = new MenuItem("Logout", logoutIcon);
        logoutItem.getStyleClass().add("logout-menu-item");
        logoutItem.setOnAction(e -> viewManager.showLoginView());

        contextMenu.getItems().addAll(accountItem, logoutItem);
        return contextMenu;
    }
}