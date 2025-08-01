package View.Admin;

import Model.BookingModelDummy;
import Service.KosServiceDummy;
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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class BookingMenuView {
    private Button searchButton;

    public Parent getView() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(30));

        KosServiceDummy dataService = new KosServiceDummy();

        // --- 1. Header Halaman ---
        HBox pageHeader = new HBox();
        pageHeader.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Booking Kost");
        title.getStyleClass().add("welcome-title");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView searchIcon = new ImageView(new Image("/img/search-icon-white.png"));
        searchIcon.setFitWidth(20);
        searchIcon.setFitHeight(20);
        searchButton = new Button();
        searchButton.setGraphic(searchIcon);
        searchButton.getStyleClass().add("create-button");
        TextField searchField = new TextField();
        searchField.setPromptText("Cari Bookingan....");
        searchField.getStyleClass().add("search-field");


        pageHeader.getChildren().addAll(title, spacer, searchField, searchButton);

        // --- 2. Panel Tabel Booking ---
        VBox bookingTablePanel = createBookingTablePanel(dataService);
        VBox.setVgrow(bookingTablePanel, Priority.ALWAYS);

        mainContent.getChildren().addAll(pageHeader, bookingTablePanel);
        return mainContent;
    }

    private VBox createBookingTablePanel(KosServiceDummy dataService) {
        VBox tableWrapper = new VBox();
        tableWrapper.getStyleClass().add("table-panel"); // Gaya panel putih

        Node headerRow = createBookingHeaderRow();

        VBox rowsContainer = new VBox();
        ObservableList<BookingModelDummy> daftarBooking = dataService.getAllBookings();
        for (BookingModelDummy booking : daftarBooking) {
            rowsContainer.getChildren().add(createBookingDataRow(booking));
        }

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

    private Node createBookingDataRow(BookingModelDummy booking) {
        HBox row = new HBox();
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().add("kos-data-row");

        Label no = new Label(String.valueOf(booking.getNo()));
        no.setPrefWidth(40);
        Label namaKos = new Label(booking.getNamaKos());
        namaKos.setPrefWidth(240);
        Label penyewa = new Label(booking.getNamaPenyewa());
        penyewa.setPrefWidth(400);
        Label waktu = new Label(booking.getHargaSewa());
        waktu.setPrefWidth(270);

        Button rejectBtn = new Button("✕");
        rejectBtn.getStyleClass().add("action-button-delete");
        Button approveBtn = new Button("✓");
        approveBtn.getStyleClass().add("action-button-approve");
        HBox aksiBox = new HBox(5, rejectBtn, approveBtn);

        HBox.setHgrow(penyewa, Priority.ALWAYS);

        row.getChildren().addAll(no, namaKos, penyewa, waktu, aksiBox);
        return row;
    }
}
