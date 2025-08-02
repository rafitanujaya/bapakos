package View.Admin;

import Model.Dummy.KosDummy;
import Model.KostModel;
import View.Controller.Admin.AdminDashboardController;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.text.NumberFormat;
import java.util.Locale;

public class AdminDashboardView {

    private VBox mainContent;
    private Label welcomeLabel;
    private VBox rowsContainer;
    private Button searchButton;
    private TextField searchField;

    public AdminDashboardView() {
        // Inisialisasi semua komponen di constructor
        mainContent = new VBox(25);
        mainContent.setPadding(new Insets(30));

        welcomeLabel = new Label("memuat");
        welcomeLabel.getStyleClass().add("welcome-title");
        HBox statsCard = createStatsCard();

        VBox kosTablePanel = createKosTablePanel();
        VBox.setVgrow(kosTablePanel, Priority.ALWAYS);

        mainContent.getChildren().addAll(welcomeLabel, statsCard, kosTablePanel);
    }
    // Metode getView() sekarang hanya mengembalikan root yang sudah dibuat
    public Parent getView() {
        return mainContent;
    }

    // Metode untuk kartu statistik
    private HBox createStatsCard() {
        HBox cardBox = new HBox(1);
        cardBox.getStyleClass().add("stat-card-container");
        cardBox.getChildren().addAll(
                createStatBlock("Total Revenue", "Rp 9.000.000,00", "+7.5% dari bulan lalu"),
                createStatBlock("Jumlah Kos Dimiliki", "12", "+2 kos baru"),
                createStatBlock("Jumlah Transaksi", "89", "5 transaksi berhasil")
        );
        return cardBox;
    }

    private Node createStatBlock(String title, String value, String subtext) {
        VBox block = new VBox(5);
        block.setPadding(new Insets(20));
        block.getStyleClass().add("stat-block");
        HBox.setHgrow(block, Priority.ALWAYS);

        // 1. Buat Label untuk judul (teks kecil di atas)
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("stat-title");

        // 2. Buat Label untuk nilai utama (angka besar)
        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("stat-value");

        // 3. Buat Label untuk subteks (teks kecil di bawah)
        Label subtextLabel = new Label(subtext);
        subtextLabel.getStyleClass().add("stat-subtext");

        // Masukkan semua label ke dalam VBox
        block.getChildren().addAll(titleLabel, valueLabel, subtextLabel);

        return block;
    }

    // Metode untuk membuat seluruh panel tabel kos (tanpa data)
    private VBox createKosTablePanel() {
        VBox tablePanel = new VBox();
        tablePanel.getStyleClass().add("table-panel");
        VBox.setVgrow(tablePanel, Priority.ALWAYS);

        //search bar
        HBox tableHeader = new HBox();
        tableHeader.setPadding(new Insets(20));
        tableHeader.setAlignment(Pos.CENTER_LEFT);
        Label title = new Label("Semua Kost Yang dimiliki");
        title.getStyleClass().add("panel-title");
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        searchField = new TextField();
        searchField.setPromptText("Cari Kos...");
        searchField.getStyleClass().add("search-field");

        ImageView searchIcon = new ImageView(new Image("/img/search-icon-white.png"));
        searchIcon.setFitWidth(20);
        searchIcon.setFitHeight(20);
        searchButton = new Button();
        searchButton.setGraphic(searchIcon);
        searchButton.getStyleClass().add("create-button");
        tableHeader.getChildren().addAll(title, spacer, searchField, searchButton);


        VBox tableWrapper = new VBox();
        Node headerRow = createKosHeaderRow();

        rowsContainer = new VBox();
        rowsContainer.getStyleClass().add("rows-container");
        // -------------------------

        ScrollPane scrollPane = new ScrollPane(rowsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("no-border-scroll-pane");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        tableWrapper.getChildren().addAll(headerRow, scrollPane);

        // ... (kode untuk clip & margin tetap sama)
        Rectangle clip = new Rectangle();
        clip.setArcWidth(16);
        clip.setArcHeight(16);
        clip.widthProperty().bind(tableWrapper.widthProperty());
        clip.heightProperty().bind(tableWrapper.heightProperty());
        tableWrapper.setClip(clip);

        tablePanel.getChildren().addAll(tableHeader, tableWrapper);
        VBox.setMargin(tableWrapper, new Insets(0, 20, 20, 20));
        return tablePanel;
    }

    private Node createKosHeaderRow() {
        HBox header = new HBox();
        header.setPadding(new Insets(10, 15, 10, 15));
        header.getStyleClass().add("kos-table-header");

        Label no = new Label("No");
        no.setPrefWidth(90);
        Label nama = new Label("Nama");
        nama.setPrefWidth(280);
        Label alamat = new Label("Alamat");
        alamat.setPrefWidth(350);
        Label harga = new Label("Harga");
        harga.setPrefWidth(245);
        Label aksi = new Label("Aksi");
        aksi.setPrefWidth(100);

        HBox.setHgrow(alamat, Priority.ALWAYS);

        header.getChildren().addAll(no, nama, alamat, harga, aksi);
        return header;
    }

    public Node createKosDataRow(int number, KostModel kos, AdminDashboardController controller) {
        HBox row = new HBox();
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().add("kos-data-row");

        Label no = new Label(String.valueOf(number));
        no.setPrefWidth(60);
        Label nama = new Label(kos.getName()); // Diubah dari getNama()
        nama.setPrefWidth(220);
        Label alamat = new Label(kos.getLocation()); // Diubah dari getAlamat()
        alamat.setPrefWidth(410);

        // Format harga dari int menjadi Rupiah
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        Label harga = new Label(currencyFormat.format(kos.getPrice())); // Diubah dari getHarga()
        harga.setPrefWidth(230);

        ImageView editIcon = new ImageView(new Image("/img/edit-icon.png"));
        editIcon.setFitWidth(18);
        editIcon.setFitHeight(18);
        Button editBtn = new Button();
        editBtn.setGraphic(editIcon);
        editBtn.getStyleClass().add("action-button");
        editBtn.setOnAction(e -> controller.handleEditKos(kos));

        ImageView deleteIcon = new ImageView(new Image("/img/delete-icon-white.png"));
        deleteIcon.setFitWidth(18);
        deleteIcon.setFitHeight(18);
        Button deleteBtn = new Button();
        deleteBtn.setGraphic(deleteIcon);
        deleteBtn.getStyleClass().add("action-button-delete");
        deleteBtn.setOnAction(e -> controller.handleDeleteKos(kos));

        HBox aksiBox = new HBox(5, editBtn, deleteBtn);
        aksiBox.setPrefWidth(100);
        aksiBox.setAlignment(Pos.CENTER);

        HBox.setHgrow(alamat, Priority.ALWAYS);

        row.getChildren().addAll(no, nama, alamat, harga, aksiBox);
        return row;
    }

    public VBox getRowsContainer() { return rowsContainer; }
    public Button getSearchButton() { return searchButton; }
    public TextField getSearchField() { return searchField; }
    public Label getWelcomeLabel() { return welcomeLabel; }
}