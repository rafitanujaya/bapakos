package View.Admin;

import Model.BookingDummy;
import Model.TransaksiDummy;
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
import Service.DataService;
import javafx.scene.layout.StackPane;

public class AdminDashboardView {
    private Button tambahPropertiBtn;
    private Button editPropertiBtn;
    private Button hapusPropertiBtn;


    public Parent getView() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));

        DataService dataService = new DataService();

        // Kartu Statistik Tunggal (tidak berubah)
        HBox singleStatCard = createStatsCardBox();

        // HBox ini sekarang akan menjadi satu kartu besar
        HBox bodyContent = new HBox(20);
        bodyContent.getStyleClass().add("content-card");
        bodyContent.setPadding(new Insets(20));

        // Panel kiri (Transaksi)
        Node transaksiPanel = createTransaksiPanel(dataService);

        // Panel kanan (Booking & Aksi Cepat)
        Node rightSidebar = createRightSidebar(dataService);

        // --- PERBAIKAN DI SINI ---

        // 1. Beri panel kanan lebar minimum agar tidak terlalu terhimpit
        rightSidebar.setStyle("-fx-min-width: 280px;");

        // 2. Perintahkan panel transaksi untuk mengambil semua sisa ruang
        HBox.setHgrow(transaksiPanel, Priority.ALWAYS);

        // -------------------------

        // Masukkan kedua panel ke dalam HBox
        bodyContent.getChildren().addAll(transaksiPanel, rightSidebar);

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
        VBox transaksiBlock = createStatBlock("Jumlah TransaksiDummy", "89", "5 transaksi berhasil", "/img/transaction-success-icon.png");

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
        ObservableList<TransaksiDummy> transactions = dataService.getRecentTransactions();
        for (TransaksiDummy trx : transactions) {
            rowsContainer.getChildren().add(createTransaksiRow(trx));
        }

        // Bungkus VBox dengan ScrollPane
        ScrollPane scrollPane = new ScrollPane(rowsContainer);
        scrollPane.setFitToWidth(true); // Penting agar konten memenuhi lebar
        scrollPane.getStyleClass().add("table-scroll-pane");

        // Atur agar ScrollPane meregang secara vertikal
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        panel.getChildren().addAll(title, scrollPane);
        return panel;
    }

    /**
     * Membuat satu baris HBox untuk menampilkan data transaksiDummy.
     */
    private Node createTransaksiRow(TransaksiDummy transaksiDummy) {
        HBox row = new HBox(10);
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.getStyleClass().add("transaction-row");

        // Kolom Nama (Gambar + Teks)
        ImageView imageView = new ImageView(new Image(transaksiDummy.getImagePath()));
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.getStyleClass().add("table-image");
        Label namaLabel = new Label(transaksiDummy.getNama());
        HBox namaBox = new HBox(10, imageView, namaLabel);
        namaBox.setAlignment(Pos.CENTER_LEFT);

        // Kolom-kolom lain sebagai Label atau StackPane
        Label unitLabel = new Label(transaksiDummy.getKamar());
        unitLabel.setAlignment(Pos.CENTER);
        Label tanggalLabel = new Label(transaksiDummy.getTanggal());
        tanggalLabel.setAlignment(Pos.CENTER);
        Label jumlahLabel = new Label(transaksiDummy.getJumlah());
        jumlahLabel.setAlignment(Pos.CENTER);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label statusLabel = new Label(transaksiDummy.getStatus());
        statusLabel.getStyleClass().add("status-label");
        statusLabel.getStyleClass().add("status-" + transaksiDummy.getStatus().toLowerCase().replace(" ", "-"));
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
        ImageView unitIcon = new ImageView(new Image("/img/property-icon.png"));
        unitIcon.setFitHeight(16);
        unitIcon.setFitWidth(16);
        HBox unitContent = new HBox(5, unitIcon, new Label("Unit"));
        unitContent.setAlignment(Pos.CENTER);
        StackPane unitPane = new StackPane(unitContent);
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

    private Node createRightSidebar(DataService dataService) {
        // VBox utama untuk menampung panel di sisi kanan
        VBox rightSidebarContent = new VBox(20);

        // 1. Panggil metode helper untuk membuat panel booking
        Node bookingPanel = createBookingPanel(dataService);

        // 2. Panggil metode helper untuk membuat panel aksi cepat
        Node quickActionsPanel = createQuickActionsPanel();

        // Spacer untuk mendorong panel aksi cepat ke bawah
        VBox spacer = new VBox();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // 3. Susun semua panel di dalam VBox utama
        rightSidebarContent.getChildren().addAll(bookingPanel, spacer, quickActionsPanel);

        return rightSidebarContent;
    }

    private Node createBookingPanel(DataService dataService) {
        VBox panel = new VBox(15);
        Label title = new Label("Booking Perlu Persetujuan");
        title.getStyleClass().add("panel-title");

        VBox bookingCardsContainer = new VBox(10);
        ObservableList<BookingDummy> bookings = dataService.getPendingBookings();
        for (BookingDummy booking : bookings) {
            bookingCardsContainer.getChildren().add(createBookingCard(booking));
        }

        ScrollPane scrollPane = new ScrollPane(bookingCardsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("no-border-scroll-pane");

        panel.getChildren().addAll(title, scrollPane);
        return panel;
    }

    /**
     * Membuat panel baru untuk tombol-tombol Aksi Cepat.
     */
    private Node createQuickActionsPanel() {
        VBox panel = new VBox(15);
        Label title = new Label("Aksi Cepat");
        title.getStyleClass().add("panel-title");

        // --- Tombol Tambah Properti ---
        ImageView addIcon = new ImageView(new Image("/img/add-circle-icon.png"));
        addIcon.setFitWidth(18);
        addIcon.setFitHeight(18);
        tambahPropertiBtn = new Button("Tambah Properti");
        tambahPropertiBtn.setGraphic(addIcon); // Tambahkan ikon
        tambahPropertiBtn.setGraphicTextGap(10); // Atur jarak ikon & teks
        tambahPropertiBtn.setMaxWidth(Double.MAX_VALUE);
        tambahPropertiBtn.getStyleClass().add("quick-action-button");
        tambahPropertiBtn.setId("add-button");

        // --- Tombol Edit Properti ---
        ImageView editIcon = new ImageView(new Image("/img/edit-icon.png"));
        editIcon.setFitWidth(18);
        editIcon.setFitHeight(18);
        editPropertiBtn = new Button("Edit Properti");
        editPropertiBtn.setGraphic(editIcon); // Tambahkan ikon
        editPropertiBtn.setGraphicTextGap(10);
        editPropertiBtn.setMaxWidth(Double.MAX_VALUE);
        editPropertiBtn.getStyleClass().add("quick-action-button");
        editPropertiBtn.getStyleClass().addAll("quick-action-button", "edit-button");
        editPropertiBtn.getStyleClass().add("quick-action-button");
        editPropertiBtn.setId("edit-button");

        // --- Tombol Hapus Properti ---
        ImageView deleteIcon = new ImageView(new Image("/img/delete-icon.png"));
        deleteIcon.setFitWidth(18);
        deleteIcon.setFitHeight(18);
        hapusPropertiBtn = new Button("Hapus Properti");
        hapusPropertiBtn.setGraphic(deleteIcon); // Tambahkan ikon
        hapusPropertiBtn.setGraphicTextGap(10);
        hapusPropertiBtn.setMaxWidth(Double.MAX_VALUE);
        hapusPropertiBtn.getStyleClass().add("quick-action-button");
        hapusPropertiBtn.getStyleClass().add("quick-action-button");
        hapusPropertiBtn.setId("delete-button");

        panel.getChildren().addAll(title, tambahPropertiBtn, editPropertiBtn, hapusPropertiBtn);
        return panel;
    }

    private Node createBookingCard(BookingDummy bookingDummy) {
        HBox card = new HBox(10);
        card.getStyleClass().add("bookingDummy-card");
        card.setPadding(new Insets(10));

        VBox info = new VBox(2);
        Label nama = new Label(bookingDummy.getNama());
        nama.setStyle("-fx-font-weight: bold;");
        Label kamar = new Label(bookingDummy.getKamar());
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

    public Button getTambahPropertiBtn() {return tambahPropertiBtn;}
    public Button getEditPropertiBtn() {return editPropertiBtn;}
    public Button getHapusPropertiBtn() {return hapusPropertiBtn;}
}