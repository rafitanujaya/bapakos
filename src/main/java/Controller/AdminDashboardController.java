package Controller;

import View.AdminDashboardView;
import View.UbahKosMenuView;
import View.TambahKosMenuView;
import View.HapusKosMenuView;
import View.MessageMenuView;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.Arrays;
import java.util.List;

public class AdminDashboardController {

    private final BorderPane rootPane;
    private final List<Button> menuButtons;

    public AdminDashboardController(AdminDashboardView view, ViewManager viewManager) {
        this.rootPane = view.getRootPane();
        this.menuButtons = Arrays.asList(
                view.getDashboardBtn(), view.getMessageBtn(), view.getAddKosBtn(),
                view.getEditKosBtn(), view.getDeleteKosBtn()
        );

        // --- Event Handler untuk Profil ---
        view.getProfileBtn().setOnAction(event -> {
            ContextMenu profileMenu = createProfileMenu(viewManager);
            profileMenu.show(view.getProfileBtn(), Side.BOTTOM, 0, 10);
        });

        // --- Event Handler untuk Tombol Sidebar ---
        view.getDashboardBtn().setOnAction(event -> {
            setActiveButton(view.getDashboardBtn());
            rootPane.setCenter(createPlaceholder("Halaman Utama Dashboard"));
        });

        view.getMessageBtn().setOnAction(event -> {
            setActiveButton(view.getMessageBtn());
            MessageMenuView messageMenuView = new MessageMenuView();
            rootPane.setCenter(messageMenuView.getView());
        });

        view.getAddKosBtn().setOnAction(event -> {
            setActiveButton(view.getAddKosBtn());
            TambahKosMenuView tambahKosView = new TambahKosMenuView();
            rootPane.setCenter(tambahKosView.getView());
        });

        view.getEditKosBtn().setOnAction(event -> {
            setActiveButton(view.getEditKosBtn());
            UbahKosMenuView ubahKosView = new UbahKosMenuView();
            rootPane.setCenter(ubahKosView.getView());
        });

        view.getDeleteKosBtn().setOnAction(event -> {
            setActiveButton(view.getDeleteKosBtn());
            HapusKosMenuView hapusKosView = new HapusKosMenuView();
            rootPane.setCenter(hapusKosView.getView());
        });

        // Atur tombol dashboard sebagai yang aktif saat pertama kali dibuka
        view.getDashboardBtn().fire();
    }

    /**
     * Metode helper untuk menandai tombol yang aktif dan menonaktifkan yang lain.
     */
    private void setActiveButton(Button activeButton) {
        // Hapus class 'active' dari semua tombol
        for (Button button : menuButtons) {
            button.getStyleClass().remove("active");
        }
        // Tambahkan class 'active' hanya pada tombol yang diklik
        activeButton.getStyleClass().add("active");
    }

    /**
     * Metode helper untuk membuat panel placeholder.
     */
    private Node createPlaceholder(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("content-placeholder");
        StackPane layout = new StackPane(label);
        return layout;
    }

    private ContextMenu createProfileMenu(ViewManager viewManager) {
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getStyleClass().add("profile-menu");

        // --- 1. Opsi "Akun Saat Ini" ---
        // Buat ImageView dan atur ukurannya
        ImageView accountIcon = new ImageView(new Image("/img/profile-icon.png"));
        accountIcon.setFitWidth(18);
        accountIcon.setFitHeight(18);
        // Buat MenuItem hanya dengan teks
        MenuItem accountItem = new MenuItem("Akun Saat Ini");
        // Pasang ImageView yang sudah diatur ukurannya sebagai graphic
        accountItem.setGraphic(accountIcon);
        accountItem.setOnAction(e -> System.out.println("Opsi 'Akun Saat Ini' diklik."));


        // --- 2. Opsi "Logout" ---
        // Lakukan hal yang sama untuk ikon logout
        ImageView logoutIcon = new ImageView(new Image("/img/logout-icon.png"));
        logoutIcon.setFitWidth(18);
        logoutIcon.setFitHeight(18);
        MenuItem logoutItem = new MenuItem("Logout");
        logoutItem.setGraphic(logoutIcon);
        logoutItem.getStyleClass().add("logout-menu-item");
        logoutItem.setOnAction(e -> viewManager.showLoginView());

        contextMenu.getItems().addAll(accountItem, logoutItem);
        return contextMenu;
    }
}