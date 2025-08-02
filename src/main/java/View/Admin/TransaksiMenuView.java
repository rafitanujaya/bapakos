package View.Admin;

import Model.Dummy.TransaksiModelDummy;
import Service.Dummy.TransaksiServiceDummy;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class TransaksiMenuView {
    private VBox mainContent;
    private VBox rowsContainer;
    private TextField searchField;
    private Button searchButton;

    public TransaksiMenuView() {
        // --- Inisialisasi semua komponen di konstruktor ---
        mainContent = new VBox(20);
        mainContent.setPadding(new Insets(30));

        // Header Halaman
        HBox pageHeader = new HBox();
        pageHeader.setAlignment(Pos.CENTER_LEFT);
        Label title = new Label("Riwayat Transaksi");
        title.getStyleClass().add("welcome-title");
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        searchField = new TextField();
        searchField.setPromptText("Cari Transaksi....");
        searchField.getStyleClass().add("search-field");
        ImageView searchIcon = new ImageView(new Image("/img/search-icon-white.png"));
        searchIcon.setFitWidth(16);
        searchIcon.setFitHeight(16);
        searchButton = new Button();
        searchButton.setGraphic(searchIcon);
        searchButton.getStyleClass().add("create-button");
        pageHeader.getChildren().addAll(title, spacer, searchField, searchButton);

        // Panel Tabel (kerangka kosong)
        VBox transaksiTablePanel = createTransaksiTablePanel();
        VBox.setVgrow(transaksiTablePanel, Priority.ALWAYS);

        mainContent.getChildren().addAll(pageHeader, transaksiTablePanel);
    }

    public Parent getView() {
        return mainContent;
    }

    private VBox createTransaksiTablePanel() {
        VBox tableWrapper = new VBox();
        tableWrapper.getStyleClass().add("table-panel");
        Node headerRow = createTransaksiHeaderRow();

        rowsContainer = new VBox();

        ScrollPane scrollPane = new ScrollPane(rowsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("no-border-scroll-pane");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        tableWrapper.getChildren().addAll(headerRow, scrollPane);

        // Clip untuk sudut tumpul
        Rectangle clip = new Rectangle();
        clip.setArcWidth(16);
        clip.setArcHeight(16);
        clip.widthProperty().bind(tableWrapper.widthProperty());
        clip.heightProperty().bind(tableWrapper.heightProperty());
        tableWrapper.setClip(clip);

        return tableWrapper;
    }

    private Node createTransaksiHeaderRow() {
        HBox header = new HBox();
        header.setPadding(new Insets(12, 15, 12, 15));
        header.getStyleClass().add("kos-table-header");

        Label no = new Label("No");
        no.setPrefWidth(70);
        Label namaKos = new Label("Nama");
        namaKos.setPrefWidth(270);
        Label penyewa = new Label("Penyewa");
        penyewa.setPrefWidth(370);
        Label waktu = new Label("Harga");
        waktu.setPrefWidth(275);
        Label status = new Label("Status"); // Kolom Aksi diubah menjadi Status
        status.setPrefWidth(100);

        HBox.setHgrow(penyewa, Priority.ALWAYS);

        header.getChildren().addAll(no, namaKos, penyewa, waktu, status);
        return header;
    }

    public Node createTransaksiDataRow(TransaksiModelDummy trx) {
        HBox row = new HBox();
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().add("kos-data-row");

        // ... (kode untuk membuat label-label tetap sama) ...
        Label no = new Label(String.valueOf(trx.getNo()));
        no.setPrefWidth(40);
        Label namaKos = new Label(trx.getNamaKos());
        namaKos.setPrefWidth(220);
        Label penyewa = new Label(trx.getNamaPenyewa());
        penyewa.setPrefWidth(380);
        Label waktu = new Label(trx.getHargaSewa());
        waktu.setPrefWidth(250);

        Label statusLabel = new Label(trx.getStatus());
        statusLabel.getStyleClass().add("status-label");
        statusLabel.getStyleClass().add("status-" + trx.getStatus().toLowerCase());
        StackPane statusContainer = new StackPane(statusLabel);
        statusContainer.setPrefWidth(100);
        statusContainer.setAlignment(Pos.CENTER);

        HBox.setHgrow(penyewa, Priority.ALWAYS);
        row.getChildren().addAll(no, namaKos, penyewa, waktu, statusContainer);

        return row;
    }

    public VBox getRowsContainer() { return rowsContainer; }
    public TextField getSearchField() { return searchField; }
    public Button getSearchButton() { return searchButton; }
}