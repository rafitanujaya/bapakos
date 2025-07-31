package View.Admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator; // <-- Import baru
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AdminPageView {

    private final BorderPane root;
    private Button profileBtn;

    // --- 1. Deklarasikan semua properti Button ---
    private Button dashboardBtn;
    private Button transactionBtn;
    private Button orderBtn;
    private Button addKosBtn;
    private Button editKosBtn;
    private Button deleteKosBtn;

    public AdminPageView() {
        root = new BorderPane();
        root.getStyleClass().add("dashboard-root");
        root.setTop(createTopBar());
        root.setLeft(createSidebar());
        Label contentPlaceholder = new Label("Konten Dashboard Muncul di Sini");
        contentPlaceholder.getStyleClass().add("content-placeholder");
        root.setCenter(contentPlaceholder);
    }

    private Node createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(20));
        sidebar.setSpacing(10);
        sidebar.getStyleClass().add("sidebar");

        // --- 2. Inisialisasi semua tombol ---

        // Menu Utama (Aktif)
        dashboardBtn = createMenuButton("Dashboard", "/img/home-icon.png");
        transactionBtn = createMenuButton("Transaksi", "/img/transaction-icon.png");
        orderBtn = createMenuButton("Order", "/img/order-icon.png");

        // Menu Aksi CRUD (Nonaktif)
        addKosBtn = createMenuButton("Tambah Kos", "/img/add-circle-icon.png");
        addKosBtn.setDisable(true); // <-- Dinonaktifkan

        editKosBtn = createMenuButton("Ubah Kos", "/img/edit-icon.png");
        editKosBtn.setDisable(true); // <-- Dinonaktifkan

        deleteKosBtn = createMenuButton("Hapus Kos", "/img/delete-icon.png");
        deleteKosBtn.setDisable(true); // <-- Dinonaktifkan

        // Pemisah visual antar grup menu
        Separator separator = new Separator();
        separator.setPadding(new Insets(10, 0, 10, 0));

        // Tambahkan semua tombol ke sidebar
        sidebar.getChildren().addAll(
                dashboardBtn,
                transactionBtn,
                orderBtn,
                separator, // Tambahkan pemisah
                addKosBtn,
                editKosBtn,
                deleteKosBtn
        );

        return sidebar;
    }

    // Metode createTopBar() dan createMenuButton() tidak perlu diubah
    private Node createTopBar() {
        // ... (isi createTopBar Anda tetap sama)
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15, 30, 15, 30));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.getStyleClass().add("top-bar");

        HBox titleSection = new HBox(10);
        titleSection.setAlignment(Pos.CENTER);

        ImageView maskot1 = new ImageView(new Image("/img/bapa-kos-icon-png.png"));
        maskot1.setFitHeight(60);
        maskot1.setFitWidth(60);

        ImageView maskot2 = new ImageView(new Image("/img/bapa-kos-text-png.png"));
        maskot2.setFitHeight(60);
        maskot2.setFitWidth(110);

        titleSection.getChildren().addAll(maskot1, maskot2);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView profileIcon = new ImageView(new Image("/img/profile-icon.png"));
        profileIcon.setFitHeight(24);
        profileIcon.setFitWidth(24);

        profileBtn = new Button("Nama Admin");
        profileBtn.setGraphic(profileIcon);
        profileBtn.getStyleClass().add("profile-button");
        profileBtn.setGraphicTextGap(10);
        topBar.getChildren().addAll(titleSection, spacer, profileBtn);

        return topBar;
    }

    private Button createMenuButton(String text, String imagePath) {
        // ... (isi createMenuButton Anda tetap sama)
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

    // --- 3. Getter untuk semua tombol ---
    public Button getProfileBtn() { return profileBtn; }
    public Button getDashboardBtn() { return dashboardBtn; }
    public Button getTransactionBtn() { return transactionBtn; }
    public Button getOrderBtn() { return orderBtn; }
    public Button getAddKosBtn() { return addKosBtn; }
    public Button getEditKosBtn() { return editKosBtn; }
    public Button getDeleteKosBtn() { return deleteKosBtn; }
}