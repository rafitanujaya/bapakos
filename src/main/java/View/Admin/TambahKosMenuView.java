package View.Admin;

import Model.KostModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;

public class TambahKosMenuView {

    private VBox mainContent;
    // --- Deklarasikan SEMUA komponen yang butuh interaksi sebagai field ---
    private TextField hargaKosField;
    private TextField namaKosField;
    private TextArea deskripsiArea;
    private ComboBox<String> provinsiComboBox;
    private ComboBox<String> kotaComboBox;
    private TextField kodePosField;
    private TextArea alamatArea;
    private StackPane dropZoneContainer;
    private ImageView previewImageView;
    private VBox dropZoneContent;
    private Button deleteImageButton;
    private Button simpanButton;
    private Button batalButton;

    public TambahKosMenuView() {
        mainContent = new VBox(25);
        mainContent.setPadding(new Insets(15));

        VBox generalPanel = createGeneralPanel();
        VBox locationPanel = createLocationPanel();
        HBox actionButtons = createActionButtons();

        mainContent.getChildren().addAll(generalPanel, locationPanel, actionButtons);
    }

    public Parent getView() {
        return mainContent;
    }

    private VBox createGeneralPanel() {
        VBox panel = new VBox(15);
        panel.getStyleClass().add("form-panel");
        panel.setPadding(new Insets(10));

        // Baris untuk Nama Kos
        Label namaKosLabel = new Label("Nama Kos");
        namaKosLabel.getStyleClass().add("input-label");
        namaKosField = new TextField();
        namaKosField.setPromptText("Contoh: Kos Bapa Fauzi");
        VBox namaKosBox = new VBox(5, namaKosLabel, namaKosField);

        // --- BAGIAN BARU UNTUK HARGA KOS ---
        Label hargaKosLabel = new Label("Harga Kos (per bulan)");
        hargaKosLabel.getStyleClass().add("input-label");
        hargaKosField = new TextField();
        hargaKosField.setPromptText("Contoh: 1500000");
        VBox hargaKosBox = new VBox(5, hargaKosLabel, hargaKosField);
        // ------------------------------------

        // Baris untuk Deskripsi
        Label deskripsiLabel = new Label("Deskripsi");
        deskripsiLabel.getStyleClass().add("input-label");
        deskripsiArea = new TextArea();
        deskripsiArea.setPromptText("Berikan deskripsi singkat mengenai properti Anda...");
        deskripsiArea.setPrefRowCount(4);
        VBox deskripsiBox = new VBox(5, deskripsiLabel, deskripsiArea);

        // Masukkan semua komponen ke dalam panel
        panel.getChildren().addAll(namaKosBox, hargaKosBox, deskripsiBox);
        return panel;
    }

    private VBox createLocationPanel() {
        VBox panel = new VBox(15);
        panel.getStyleClass().add("form-panel");
        panel.setPadding(new Insets(10));
        Label title = new Label("Lokasi");
        title.getStyleClass().add("panel-title");

        HBox columnsBox = new HBox(30);
        Node locationForm = createLocationForm();
        Node imageInputPanel = createImageInputPanel();
        columnsBox.getChildren().addAll(locationForm, imageInputPanel);

        panel.getChildren().addAll(title, columnsBox);
        return panel;
    }

    private Node createLocationForm() {
        VBox form = new VBox(15);
        HBox.setHgrow(form, Priority.ALWAYS);

        // Provinsi
        Label provinsiLabel = new Label("Provinsi");
        provinsiLabel.getStyleClass().add("input-label");
        provinsiComboBox = new ComboBox<>();
        provinsiComboBox.setPromptText("Pilih Provinsi");
        provinsiComboBox.setMaxWidth(Double.MAX_VALUE);
        VBox provinsiBox = new VBox(5, provinsiLabel, provinsiComboBox);
        HBox.setHgrow(provinsiBox, Priority.ALWAYS);

        // Kota/Kabupaten
        Label kotaLabel = new Label("Kota/Kabupaten");
        kotaLabel.getStyleClass().add("input-label");
        kotaComboBox = new ComboBox<>();
        kotaComboBox.setPromptText("Pilih Kota/Kabupaten");
        kotaComboBox.setMaxWidth(Double.MAX_VALUE);
        VBox kotaBox = new VBox(5, kotaLabel, kotaComboBox);
        HBox.setHgrow(kotaBox, Priority.ALWAYS);

        // Gabung Provinsi dan Kota dalam satu baris
        HBox provinsiKotaRow = new HBox(20, provinsiBox, kotaBox);

        // Kode Pos
        Label kodePosLabel = new Label("Kode Pos");
        kodePosLabel.getStyleClass().add("input-label");
        kodePosField = new TextField();
        kodePosField.setPromptText("Contoh: 40288");
        VBox kodePosBox = new VBox(5, kodePosLabel, kodePosField);

        // Alamat
        Label alamatLabel = new Label("Alamat");
        alamatLabel.getStyleClass().add("input-label");
        alamatArea = new TextArea();
        alamatArea.setPromptText("Kecamatan, Desa/Kelurahan, RT/RW....");
        alamatArea.setPrefRowCount(4);
        VBox alamatBox = new VBox(5, alamatLabel, alamatArea);

        form.getChildren().addAll(provinsiKotaRow, kodePosBox, alamatBox);
        return form;
    }


    private Node createImageInputPanel() {
        VBox panel = new VBox(10);
        panel.setPrefWidth(300);

        Label title = new Label("Gambar Properti");
        title.getStyleClass().add("input-label");

        // --- PERBAIKAN DI SINI ---
        // Inisialisasi field previewImageView, bukan variabel lokal
        previewImageView = new ImageView();
        previewImageView.setFitWidth(280);
        previewImageView.setFitHeight(200);
        previewImageView.setPreserveRatio(true);
        previewImageView.getStyleClass().add("image-preview");

        ImageView uploadIcon = new ImageView(new Image("/img/add-circle-icon.png"));
        uploadIcon.setFitHeight(30);
        uploadIcon.setFitWidth(30);
        Label dropText = new Label("Klik untuk memilih gambar...");

        dropZoneContent = new VBox(10, uploadIcon, dropText);
        dropZoneContent.setAlignment(Pos.CENTER);

        deleteImageButton = new Button("✕");
        deleteImageButton.getStyleClass().add("delete-image-button");
        deleteImageButton.setVisible(false);

        dropZoneContainer = new StackPane();
        dropZoneContainer.getStyleClass().add("image-drop-zone");
        dropZoneContainer.getChildren().addAll(dropZoneContent, previewImageView, deleteImageButton);
        VBox.setVgrow(dropZoneContainer, Priority.ALWAYS);
        StackPane.setAlignment(deleteImageButton, Pos.TOP_RIGHT);
        StackPane.setMargin(deleteImageButton, new Insets(5));

        panel.getChildren().addAll(title, dropZoneContainer);
        return panel;
    }

    private HBox createActionButtons() {
        HBox buttonBox = new HBox(10);
        simpanButton = new Button("Simpan");
        simpanButton.getStyleClass().add("create-button");
        batalButton = new Button("Batal");
        batalButton.getStyleClass().add("cancel-button");
        buttonBox.getChildren().addAll(batalButton, simpanButton);
        return buttonBox;
    }

    public void setData(KostModel kos) {
        if (kos != null) {
            namaKosField.setText(kos.getName());
            hargaKosField.setText(String.valueOf(kos.getPrice()));
            deskripsiArea.setText(kos.getDescription());

            // Untuk alamat, kita perlu memecahnya kembali
            String[] lokasiParts = kos.getLocation().split(", ");
            if (lokasiParts.length >= 4) {
                provinsiComboBox.setValue(lokasiParts[0]);
                kotaComboBox.setValue(lokasiParts[1]);
                alamatArea.setText(lokasiParts[2]);
                kodePosField.setText(lokasiParts[3]);
            } else {
                alamatArea.setText(kos.getLocation());
            }

            // Logika untuk menampilkan gambar (jika ada)
            if (kos.getImage() != null) {
                Image image = new Image(new ByteArrayInputStream(kos.getImage()));
                previewImageView.setImage(image);
                deleteImageButton.setVisible(true);
            }
        }
    }

    // --- Getter untuk semua komponen ---
    public TextField getNamaKosField() { return namaKosField; }
    public TextArea getDeskripsiArea() { return deskripsiArea; }
    public ComboBox<String> getProvinsiComboBox() { return provinsiComboBox; }
    public ComboBox<String> getKotaComboBox() { return kotaComboBox; }
    public TextField getKodePosField() { return kodePosField; }
    public TextArea getAlamatArea() { return alamatArea; }
    public StackPane getDropZoneContainer() { return dropZoneContainer; }
    public ImageView getPreviewImageView() { return previewImageView; }
    public VBox getDropZoneContent() { return dropZoneContent; }
    public Button getDeleteImageButton() { return deleteImageButton; }
    public Button getSimpanButton() { return simpanButton; }
    public Button getBatalButton() { return batalButton; }
    public TextField getHargaKosField() { return hargaKosField; }

}