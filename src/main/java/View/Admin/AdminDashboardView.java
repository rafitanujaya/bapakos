package View.Admin;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import Model.Booking;
import Model.Transaksi;
import Service.DataService;
import javafx.scene.layout.StackPane;

public class AdminDashboardView {

    public Parent getView() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));

        DataService dataService = new DataService();

        // Kartu Statistik Tunggal
        HBox singleStatCard = createStatsCardBox();

        // Layout 70-30 untuk Body
        HBox bodyContent = new HBox(20);
        Node transaksiPanel = createTransaksiPanel(dataService);
        Node bookingPanel = createBookingPanel(dataService);
        HBox.setHgrow(transaksiPanel, Priority.ALWAYS); // Panel kiri meregang
        bodyContent.getChildren().addAll(transaksiPanel, bookingPanel);

        // Gabungkan semua
        mainContent.getChildren().addAll(singleStatCard, bodyContent);
        return mainContent;
    }

    private HBox createStatsCardBox() {
        HBox cardBox = new HBox();
        cardBox.setPadding(new Insets(20));
        cardBox.setSpacing(20);
        cardBox.getStyleClass().add("stat-card");

        VBox revenueBlock = createStatBlock("Total Revenue", "Rp9.000.000,00", "+7.5% dari bulan lalu", "/img/balance-icon.png");
        VBox kosBlock = createStatBlock("Jumlah Kos Dimiliki", "12", "+2 kos baru", "/img/property-icon.png");
        VBox transaksiBlock = createStatBlock("Jumlah Transaksi", "89", "5 transaksi berhasil", "/img/transaction-success-icon.png");

        HBox.setHgrow(revenueBlock, Priority.ALWAYS);
        HBox.setHgrow(kosBlock, Priority.ALWAYS);
        HBox.setHgrow(transaksiBlock, Priority.ALWAYS);

        Separator s1 = new Separator(Orientation.VERTICAL);
        Separator s2 = new Separator(Orientation.VERTICAL);

        cardBox.getChildren().addAll(revenueBlock, s1, kosBlock, s2, transaksiBlock);
        return cardBox;
    }

    private VBox createStatBlock(String title, String value, String subtext, String imagePath) {
        VBox block = new VBox(5);
        block.setMaxWidth(Double.MAX_VALUE);
        HBox titleBox = new HBox(10);
        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitWidth(24);
        icon.setFitHeight(24);
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("stat-title");
        titleBox.getChildren().addAll(icon, titleLabel);
        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("stat-value");
        Label subtextLabel = new Label(subtext);
        subtextLabel.getStyleClass().add("stat-subtext");
        block.getChildren().addAll(titleBox, valueLabel, subtextLabel);
        return block;
    }

    private Node createTransaksiPanel(DataService dataService) {
        VBox panel = new VBox(15);
        Label title = new Label("Recent Transaction");
        title.getStyleClass().add("panel-title");

        // VBox untuk menampung semua baris transaksi
        VBox rowsContainer = new VBox();

        // Buat header kustom
        rowsContainer.getChildren().add(createTransaksiHeader());

        // Ambil data dan buat satu HBox untuk setiap baris
        ObservableList<Transaksi> transactions = dataService.getRecentTransactions();
        for (Transaksi trx : transactions) {
            rowsContainer.getChildren().add(createTransaksiRow(trx));
        }

        // Bungkus VBox dengan ScrollPane
        ScrollPane scrollPane = new ScrollPane(rowsContainer);
        scrollPane.setFitToWidth(true); // Penting agar konten memenuhi lebar
        scrollPane.getStyleClass().add("no-border-scroll-pane");

        // Atur agar ScrollPane meregang secara vertikal
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        panel.getChildren().addAll(title, scrollPane);
        return panel;
    }

    /**
     * Membuat satu baris HBox untuk menampilkan data transaksi.
     */
    private Node createTransaksiRow(Transaksi transaksi) {
        HBox row = new HBox(10);
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().add("transaction-row");

        // Kolom Nama (Gambar + Teks)
        ImageView imageView = new ImageView(new Image(transaksi.getImagePath()));
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.getStyleClass().add("table-image");
        Label namaLabel = new Label(transaksi.getNama());
        HBox namaBox = new HBox(10, imageView, namaLabel);
        namaBox.setAlignment(Pos.CENTER_LEFT);

        // Kolom-kolom lain sebagai Label atau StackPane
        Label unitLabel = new Label(transaksi.getKamar());
        unitLabel.setAlignment(Pos.CENTER);
        Label tanggalLabel = new Label(transaksi.getTanggal());
        tanggalLabel.setAlignment(Pos.CENTER);
        Label jumlahLabel = new Label(transaksi.getJumlah());
        jumlahLabel.setAlignment(Pos.CENTER);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label statusLabel = new Label(transaksi.getStatus());
        statusLabel.getStyleClass().add("status-label");
        statusLabel.getStyleClass().add("status-" + transaksi.getStatus().toLowerCase().replace(" ", "-"));
        StackPane statusContainer = new StackPane(statusLabel);
        statusContainer.setAlignment(Pos.CENTER);

        // --- PERUBAHAN DI SINI: Hapus setPrefWidth dan ganti dengan binding ---
        namaBox.prefWidthProperty().bind(row.widthProperty().multiply(0.30));
        unitLabel.prefWidthProperty().bind(row.widthProperty().multiply(0.15));
        tanggalLabel.prefWidthProperty().bind(row.widthProperty().multiply(0.20));
        jumlahLabel.prefWidthProperty().bind(row.widthProperty().multiply(0.20));
        statusContainer.prefWidthProperty().bind(row.widthProperty().multiply(0.15));

        row.getChildren().addAll(namaBox, unitLabel, tanggalLabel, jumlahLabel, spacer, statusContainer);
        return row;
    }

    /**
     * Membuat header kustom untuk daftar transaksi.
     */
    // Di dalam kelas AdminDashboardView
    private Node createTransaksiHeader() {
        HBox header = new HBox(10);
        header.setPadding(new Insets(10, 15, 10, 15));
        header.getStyleClass().add("transaction-header");

        // --- Kolom Name ---
        ImageView nameIcon = new ImageView(new Image("/img/list-icon.png"));
        nameIcon.setFitHeight(16);
        nameIcon.setFitWidth(16);
        HBox nameContent = new HBox(5, nameIcon, new Label("Nama"));
        nameContent.setAlignment(Pos.CENTER);
        StackPane namaPane = new StackPane(nameContent);
        namaPane.setAlignment(Pos.CENTER);

        // --- Kolom Unit ---
        // (Karena tidak ada ikon, kita hanya pakai Label)
        StackPane unitPane = new StackPane(new Label("Unit"));
        unitPane.setAlignment(Pos.CENTER);

        // --- Kolom Date ---
        ImageView dateIcon = new ImageView(new Image("/img/calendar-icon.png"));
        dateIcon.setFitHeight(16);
        dateIcon.setFitWidth(16);
        HBox dateContent = new HBox(5, dateIcon, new Label("Tanggal"));
        dateContent.setAlignment(Pos.CENTER);
        StackPane tanggalPane = new StackPane(dateContent);
        tanggalPane.setAlignment(Pos.CENTER);

        // --- Kolom Amount ---
        ImageView amountIcon = new ImageView(new Image("/img/order-icon.png"));
        amountIcon.setFitHeight(16);
        amountIcon.setFitWidth(16);
        HBox amountContent = new HBox(5, amountIcon, new Label("Harga"));
        amountContent.setAlignment(Pos.CENTER);
        StackPane jumlahPane = new StackPane(amountContent);
        jumlahPane.setAlignment(Pos.CENTER);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // --- Kolom Status ---
        ImageView statusIcon = new ImageView(new Image("/img/status-icon.png"));
        statusIcon.setFitHeight(16);
        statusIcon.setFitWidth(16);
        HBox statusContent = new HBox(5, statusIcon, new Label("Status"));
        statusContent.setAlignment(Pos.CENTER);
        StackPane statusPane = new StackPane(statusContent);
        statusPane.setAlignment(Pos.CENTER);

        // Atur lebar proporsional untuk setiap StackPane
        namaPane.prefWidthProperty().bind(header.widthProperty().multiply(0.30));
        unitPane.prefWidthProperty().bind(header.widthProperty().multiply(0.15));
        tanggalPane.prefWidthProperty().bind(header.widthProperty().multiply(0.20));
        jumlahPane.prefWidthProperty().bind(header.widthProperty().multiply(0.20));
        statusPane.prefWidthProperty().bind(header.widthProperty().multiply(0.15));

        header.getChildren().addAll(namaPane, unitPane, tanggalPane, jumlahPane, spacer, statusPane);
        return header;
    }

    private Node createBookingPanel(DataService dataService) {
        VBox panel = new VBox(15);
        Label title = new Label("Booking Perlu Persetujuan");
        title.getStyleClass().add("panel-title");

        VBox bookingCardsContainer = new VBox(10);
        ObservableList<Booking> bookings = dataService.getPendingBookings();
        for (Booking booking : bookings) {
            bookingCardsContainer.getChildren().add(createBookingCard(booking));
        }

        ScrollPane scrollPane = new ScrollPane(bookingCardsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("no-border-scroll-pane");

        panel.getChildren().addAll(title, scrollPane);
        return panel;
    }

    private Node createBookingCard(Booking booking) {
        HBox card = new HBox(10);
        card.getStyleClass().add("booking-card");
        card.setPadding(new Insets(10));

        VBox info = new VBox(2);
        Label nama = new Label(booking.getNama());
        nama.setStyle("-fx-font-weight: bold;");
        Label kamar = new Label(booking.getKamar());
        info.getChildren().addAll(nama, kamar);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button approveBtn = new Button("✔");
        approveBtn.getStyleClass().add("approve-button");
        Button declineBtn = new Button("✖");
        declineBtn.getStyleClass().add("decline-button");

        card.getChildren().addAll(info, spacer, approveBtn, declineBtn);
        return card;
    }
}