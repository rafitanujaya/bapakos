package View.Admin;

import Model.Dummy.TransaksiModelDummy;
import View.Controller.Admin.AdminBookingController;
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
import javafx.scene.shape.Rectangle;

public class BookingMenuView {

    // Deklarasikan semua komponen yang perlu diakses sebagai field
    private VBox mainContent;
    private VBox rowsContainer;
    private TextField searchField;
    private Button searchButton;

    public BookingMenuView() {
        // --- Inisialisasi semua komponen di konstruktor ---
        mainContent = new VBox(20);
        mainContent.setPadding(new Insets(30));

        // Header Halaman
        HBox pageHeader = new HBox();
        pageHeader.setAlignment(Pos.CENTER_LEFT);
        Label title = new Label("Booking Kost");
        title.getStyleClass().add("welcome-title");
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        searchField = new TextField();
        searchField.setPromptText("Cari Bookingan....");
        searchField.getStyleClass().add("search-field");
        ImageView searchIcon = new ImageView(new Image("/img/search-icon-white.png"));
        searchIcon.setFitWidth(16);
        searchIcon.setFitHeight(16);
        searchButton = new Button();
        searchButton.setGraphic(searchIcon);
        searchButton.getStyleClass().add("create-button");
        pageHeader.getChildren().addAll(title, spacer, searchField, searchButton);

        // Panel Tabel (kerangka kosong)
        VBox bookingTablePanel = createBookingTablePanel();
        VBox.setVgrow(bookingTablePanel, Priority.ALWAYS);

        mainContent.getChildren().addAll(pageHeader, bookingTablePanel);
    }

    public Parent getView() {
        return mainContent;
    }

    private VBox createBookingTablePanel() {
        VBox tableWrapper = new VBox();
        tableWrapper.getStyleClass().add("table-panel");

        Node headerRow = createBookingHeaderRow();

        // Inisialisasi field rowsContainer di sini
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

    // Metode ini sekarang menerima controller sebagai parameter
    public Node createBookingDataRow(TransaksiModelDummy booking, AdminBookingController controller) {
        HBox row = new HBox();
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().add("kos-data-row");

        Label no = new Label(String.valueOf(booking.getNo()));
        no.setPrefWidth(40);
        Label namaKos = new Label(booking.getNamaKos());
        namaKos.setPrefWidth(220);
        Label penyewa = new Label(booking.getNamaPenyewa());
        penyewa.setPrefWidth(380);
        Label waktu = new Label(booking.getHargaSewa());
        waktu.setPrefWidth(250);

        ImageView rejectIcon = new ImageView(new Image("/img/reject-icon-white.png"));
        rejectIcon.setFitWidth(18);
        rejectIcon.setFitHeight(18);
        Button rejectBtn = new Button();
        rejectBtn.setGraphic(rejectIcon);
        rejectBtn.getStyleClass().add("action-button-delete");
        rejectBtn.setOnAction(e -> controller.handleReject(booking));

        ImageView approveIcon = new ImageView(new Image("/img/approve-icon-white.png"));
        approveIcon.setFitWidth(18);
        approveIcon.setFitHeight(18);
        Button approveBtn = new Button();
        approveBtn.setGraphic(approveIcon);
        approveBtn.getStyleClass().add("action-button-approve");
        approveBtn.setOnAction(e -> controller.handleApprove(booking));

        HBox aksiBox = new HBox(5, rejectBtn, approveBtn);
        HBox.setHgrow(penyewa, Priority.ALWAYS);
        row.getChildren().addAll(no, namaKos, penyewa, waktu, aksiBox);

        return row;
    }

    private Node createBookingHeaderRow() {
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
        Label aksi = new Label("Aksi");
        aksi.setPrefWidth(100);

        HBox.setHgrow(penyewa, Priority.ALWAYS);

        header.getChildren().addAll(no, namaKos, penyewa, waktu, aksi);
        return header;
    }

    // Getter untuk diakses Controller
    public VBox getRowsContainer() { return rowsContainer; }
    public TextField getSearchField() { return searchField; }
    public Button getSearchButton() { return searchButton; }
}