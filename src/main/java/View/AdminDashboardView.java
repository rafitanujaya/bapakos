package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
public class AdminDashboardView {

    private final BorderPane root;
    private ImageView maskot1, maskot2, profile;
    private Button dashboardBtn;
    private Button messageBtn;
    private Button addKosBtn;
    private Button editKosBtn;
    private Button deleteKosBtn;
    private Button profileBtn;

    public AdminDashboardView() {
        root = new BorderPane();
        root.getStyleClass().add("dashboard-root"); // Tambahkan style class untuk root

        root.setTop(createTopBar());
        root.setLeft(createSidebar());

        // Placeholder untuk konten utama di tengah
        Label contentPlaceholder = new Label("Konten Dashboard Muncul di Sini");
        contentPlaceholder.getStyleClass().add("content-placeholder");
        root.setCenter(contentPlaceholder);
    }

    private Node createTopBar() {
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15, 30, 15, 30));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.getStyleClass().add("top-bar");

        // 1. Judul diganti dengan HBox berisi 2 placeholder ikon/gambar
        HBox titleSection = new HBox(0);
        titleSection.setAlignment(Pos.CENTER);
        Image bapaKosIconImg = new Image(getClass().getResourceAsStream("/img/bapa-kos-icon-png.png"));
        maskot1 = new ImageView(bapaKosIconImg);
        maskot1.setFitHeight(60);
        maskot1.setFitWidth(60);
        Image bapaKosTextImg = new Image(getClass().getResourceAsStream("/img/bapa-kos-text-png.png"));
        maskot2 = new ImageView(bapaKosTextImg);
        maskot2.setFitHeight(60);
        maskot2.setFitWidth(110);
        titleSection.getChildren().addAll(maskot1, maskot2);

        // Spacer untuk mendorong elemen ke kanan
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView profileIcon = new ImageView(new Image("/img/profile-icon.png"));
        profileIcon.setFitHeight(24);
        profileIcon.setFitWidth(24);

        profileBtn = new Button("Nama Admin");
        profileBtn.setGraphic(profileIcon);
        profileBtn.getStyleClass().add("profile-button"); // Class CSS baru
        profileBtn.setGraphicTextGap(10);

        // Masukkan bagian-bagian yang sudah diubah ke topBar
        topBar.getChildren().addAll(titleSection, spacer, profileBtn);
        return topBar;
    }

    private Node createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(20));
        sidebar.setSpacing(10);
        sidebar.getStyleClass().add("sidebar");

        // 2. Inisialisasi setiap field di dalam createSidebar()
        dashboardBtn = createMenuButton("Dashboard", "/img/home-icon.png");
        messageBtn = createMenuButton("Pesan", "/img/message-icon.png");
        addKosBtn = createMenuButton("Tambah Kos", "/img/add-circle-icon.png");
        editKosBtn = createMenuButton("Ubah Kos", "/img/edit-icon.png");
        deleteKosBtn = createMenuButton("Hapus Kos", "/img/delete-icon.png");

        // Tambahkan tombol-tombol yang sudah menjadi field ke sidebar
        sidebar.getChildren().addAll(
                dashboardBtn,
                messageBtn,
                addKosBtn,
                editKosBtn,
                deleteKosBtn
        );

        return sidebar;
    }

    // Helper method diubah untuk menyertakan placeholder ikon
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
    public Button getProfileBtn() { return profileBtn; }
    public Button getDashboardBtn() { return dashboardBtn; }
    public Button getAddKosBtn() { return addKosBtn; }
    public Button getEditKosBtn() { return editKosBtn; }
    public Button getDeleteKosBtn() { return deleteKosBtn; }
    public Button getMessageBtn() { return messageBtn; }
}