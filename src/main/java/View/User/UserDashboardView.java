package View.User;

import Model.KostModel; // Ganti dengan path model Anda yang benar
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.io.ByteArrayInputStream;
import java.nio.Buffer;
import java.text.NumberFormat;
import java.util.Locale;

public class UserDashboardView {

    private HBox root;
    // Sediakan field untuk panel yang akan diupdate oleh Controller
    private VBox detailPanel;
    private FlowPane kosListContainer;


    public UserDashboardView() {
        root = new HBox(5);
        root.setPadding(new Insets(30));
        root.getStyleClass().add("user-dashboard-root");

        // --- Panel Kiri (60%): Daftar Kos ---
        Node leftPanel = createKosListingPanel();

        // --- Panel Kanan (40%): Detail Kos ---
        detailPanel = new VBox();

        // Atur proporsi 60/40
        HBox.setHgrow(leftPanel, Priority.ALWAYS);

        root.getChildren().addAll(leftPanel, detailPanel);
    }

    public Parent getView() {
        return root;
    }

    private Node createKosListingPanel() {
        // Gunakan FlowPane agar kartu otomatis turun baris
        kosListContainer = new FlowPane(15, 15); // Jarak horizontal & vertikal

        // Bungkus dengan ScrollPane
        ScrollPane scrollPane = new ScrollPane(kosListContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("no-border-scroll-pane");

        return scrollPane;
    }

    // Metode untuk membuat satu kartu kos
    public Node createKosCard(KostModel kos) {
        StackPane card = new StackPane();
        card.getStyleClass().add("kos-card-user");
        card.setPrefSize(260, 260);

        // Buat "pemotong" untuk sudut tumpul
        Rectangle clip = new Rectangle(260, 260);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        card.setClip(clip);

        // Gambar sebagai lapisan paling bawah
        ImageView imageView = new ImageView();
        byte[] imageData = kos.getImage();
        if (imageData != null && imageData.length > 0) {
            imageView.setImage(new Image(new ByteArrayInputStream(imageData)));
        }
        imageView.setFitWidth(260);
        imageView.setFitHeight(260);

        // --- Panel Teks ---
        VBox textPanel = new VBox(5); // Jarak antar elemen di dalam panel
        textPanel.setPadding(new Insets(12));
        textPanel.getStyleClass().add("kos-card-text-panel");

        // Baris 1: Nama Kos
        Label nama = new Label(kos.getName());
        nama.getStyleClass().add("kos-card-nama");

        // Baris 2: Alamat (Ikon + Teks)
        ImageView locationIcon = new ImageView(new Image("/img/location-icon.png")); // Siapkan ikon ini
        locationIcon.setFitHeight(12);
        locationIcon.setFitWidth(12);
        Label alamat = new Label(kos.getLocation()); // Asumsikan format: "Kota, Provinsi, Indonesia"
        alamat.getStyleClass().add("kos-card-alamat");
        HBox alamatBox = new HBox(5, locationIcon, alamat);
        alamatBox.setAlignment(Pos.CENTER_LEFT);

        // Baris 3: Garis Pemisah
        Separator separator = new Separator();

        // Baris 4: Harga
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedPrice = currencyFormat.format(kos.getPrice());
        Label harga = new Label(formattedPrice);
        harga.getStyleClass().add("kos-card-harga");
        Label perBulan = new Label("/perbulan");
        perBulan.getStyleClass().add("kos-card-alamat"); // Pakai gaya yang sama dengan alamat
        HBox hargaBox = new HBox(5, harga, perBulan);
        hargaBox.setAlignment(Pos.CENTER_LEFT);

        textPanel.getChildren().addAll(nama, alamatBox, separator, hargaBox);
        // Gunakan VBox wrapper untuk mengatur posisi dan padding
        VBox wrapper = new VBox(textPanel);
        wrapper.setAlignment(Pos.BOTTOM_LEFT);
        wrapper.setPadding(new Insets(10)); // Jarak 10px dari tepi gambar

        // Tumpuk gambar dan wrapper
        card.getChildren().addAll(imageView, wrapper);
        return card;
    }

    public Node createDetailPanel(KostModel kos) {
        VBox panel = new VBox(10); // Spasi antar elemen
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.TOP_CENTER);
        panel.getStyleClass().add("kos-detail-panel");

        // Gambar utama
        ImageView imageView = new ImageView();
        byte[] imageData = kos.getImage();
        if (imageData != null && imageData.length > 0) {
            imageView.setImage(new Image(new ByteArrayInputStream(imageData)));
        }
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        // Nama kos
        Label namaKos = new Label(kos.getName());
        namaKos.getStyleClass().add("kos-detail-nama");

        // Alamat
        Label alamat = new Label(kos.getLocation());
        alamat.getStyleClass().add("kos-detail-alamat");

        // Separator
        Separator separator = new Separator();

        // Deskripsi
        Label deskripsi = new Label(kos.getDescription());
        deskripsi.setWrapText(true);
        deskripsi.getStyleClass().add("kos-detail-deskripsi");

        // Harga
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedPrice = currencyFormat.format(kos.getPrice());
        Label harga = new Label("Harga: " + formattedPrice + " /bulan");
        harga.getStyleClass().add("kos-detail-harga");

        // Tombol Order Sekarang
        Button orderButton = new Button("Order Sekarang");
        orderButton.getStyleClass().add("kos-detail-button");

        // Gambar statis (misal: peta atau ikon lainnya)
        ImageView staticImage = new ImageView(new Image("/img/maps-pict.jpg")); // siapkan gambarnya di folder resource
        staticImage.setFitWidth(280);
        staticImage.setFitHeight(160);
        staticImage.setPreserveRatio(true);
        staticImage.setSmooth(true);

        panel.getChildren().addAll(
                imageView,
                namaKos,
                alamat,
                separator,
                deskripsi,
                harga,
                orderButton,
                staticImage
        );

        return panel;
    }


    // --- Getter untuk diakses Controller ---
    public FlowPane getKosListContainer() { return kosListContainer; }
    public VBox getDetailPanel() { return detailPanel; }

}