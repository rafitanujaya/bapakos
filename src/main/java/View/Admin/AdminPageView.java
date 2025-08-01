package View.Admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AdminPageView {

    private final BorderPane root;
    private Button dashboardBtn;
    private Button transactionBtn;
    private Button orderBtn;
    // Tombol CRUD yang nonaktif
    private Button addKosBtn;
    private Button editKosBtn;
    private Button deleteKosBtn;
    // Tombol baru untuk logout
    private Button logoutBtn;

    public AdminPageView() {
        root = new BorderPane();
        root.getStyleClass().add("dashboard-root");

        // Top bar tidak lagi digunakan
        // root.setTop(createTopBar());
        root.setLeft(createSidebar());

        // Konten utama tetap di tengah
        // Ini akan diganti oleh controller nanti
        root.setCenter(new Label("Konten Awal"));
    }

    private Node createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(25, 20, 25, 20)); // Padding disesuaikan
        sidebar.setSpacing(10);
        sidebar.getStyleClass().add("sidebar");

        // --- 1. Bagian Logo (pindah dari top bar) ---
        VBox logoBox = new VBox(10);
        logoBox.setAlignment(Pos.CENTER_LEFT);
        ImageView logoIcon = new ImageView(new Image("/img/bapa-kos-icon-png.png"));
        logoIcon.setFitHeight(110);
        logoIcon.setFitWidth(110);
        Label logoText = new Label("BapaKos");
        logoText.getStyleClass().add("logo-text");
        logoBox.getChildren().addAll(logoIcon, logoText);
        logoBox.setAlignment(Pos.CENTER);
        VBox.setMargin(logoBox, new Insets(0, 0, 30, 0));

        // --- 2. Bagian Menu Utama ---
        dashboardBtn = createMenuButton("Dashboard", "/img/home-icon.png");
        transactionBtn = createMenuButton("Transaksi", "/img/transaction-icon.png");
        orderBtn = createMenuButton("Order", "/img/order-icon.png");

        Separator separator = new Separator();
        separator.setPadding(new Insets(10, 0, 10, 0));

        // Menu CRUD (Nonaktif)
        addKosBtn = createMenuButton("Tambah Kos", "/img/add-circle-icon.png");
        editKosBtn = createMenuButton("Ubah Kos", "/img/edit-icon.png");
        deleteKosBtn = createMenuButton("Hapus Kos", "/img/delete-icon.png");

        VBox menuBox = new VBox(5, dashboardBtn, transactionBtn, orderBtn, separator, addKosBtn, editKosBtn, deleteKosBtn);

        // --- 3. Spacer untuk mendorong profil ke bawah ---
        VBox spacer = new VBox();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // --- 4. Bagian Profil & Logout (pindah dari top bar) ---
        ImageView profileIcon = new ImageView(new Image("/img/profile-icon.png"));
        profileIcon.setFitHeight(36);
        profileIcon.setFitWidth(36);
        Label profileName = new Label("Admin");
        profileName.getStyleClass().add("profile-name");
        HBox profileBox = new HBox(10, profileIcon, profileName);
        profileBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(profileBox, new Insets(0, 0, 10, 0));

        logoutBtn = new Button("Logout");
        logoutBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn.getStyleClass().add("logout-button-sidebar");

        // Susun semua bagian di dalam sidebar
        sidebar.getChildren().addAll(logoBox, menuBox, spacer, profileBox, logoutBtn);
        return sidebar;
    }

    private Button createMenuButton(String text, String imagePath) {
        Button button = new Button(text);
        Image icon = new Image(imagePath);
        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(20);
        iconView.setFitHeight(20);
        button.setGraphic(iconView);
        button.setGraphicTextGap(15);
        button.getStyleClass().add("menu-button");
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
    }

    public Parent getView() {
        return root;
    }
    public BorderPane getRootPane() {return root;}

    // --- Getter Disesuaikan ---
    public Button getDashboardBtn() { return dashboardBtn; }
    public Button getTransactionBtn() { return transactionBtn; }
    public Button getOrderBtn() { return orderBtn; }
    public Button getAddKosBtn() { return addKosBtn; }
    public Button getEditKosBtn() { return editKosBtn; }
    public Button getDeleteKosBtn() { return deleteKosBtn; }
    public Button getLogoutBtn() { return logoutBtn; }
}