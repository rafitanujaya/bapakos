package View.Controller.Admin;

import Config.DBConfig;
import Dao.KostDAO;
import Model.KostModel;
import Model.Location.Location;
import Model.UserModel;
import Service.KostService;
import Session.Session;
import View.Admin.TambahKosMenuView;
import View.ViewManager;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Map;

public class TambahKosController {
    private TambahKosMenuView view;
    private ViewManager viewManager;
    private Map<String, ObservableList<String>> dataKabupaten;
    private AdminPageController adminPageController;
    private KostService kostService;
    private File selectedImageFile;
    private KostModel kosToEdit;

    public TambahKosController(TambahKosMenuView view, ViewManager viewManager, AdminPageController adminPageController, KostModel kosToEdit) {
        this.view = view;
        this.viewManager = viewManager;
        this.adminPageController = adminPageController;
        this.kosToEdit = kosToEdit;

        // Inisialisasi KostService dengan koneksi database
        try {
            this.kostService = new KostService(new KostDAO(new DBConfig().getConnection()));
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error koneksi
        }

        if (this.kosToEdit != null) {
            view.setData(this.kosToEdit);
        }

        this.dataKabupaten = Location.getKabupatenPerProvinsi();
        initializeListeners();
    }

    private void initializeListeners() {
        // --- Logika untuk ComboBox Provinsi & Kabupaten ---
        view.getKotaComboBox().setDisable(true);
        view.getProvinsiComboBox().setItems(Location.getProvinsiJawa());

        view.getProvinsiComboBox().valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                view.getKotaComboBox().setDisable(false);
                view.getKotaComboBox().setItems(dataKabupaten.get(newVal));
            } else {
                view.getKotaComboBox().setDisable(true);
                view.getKotaComboBox().getItems().clear();
            }
        });

        // --- Logika untuk Input Gambar ---
        // Gunakan getDropZoneContainer() atau getDropZoneContent() untuk event klik
        view.getDropZoneContainer().setOnMouseClicked(event -> {
            openFileChooser();
        });

        view.getDeleteImageButton().setOnAction(event -> {
            // Hapus gambar dari preview
            view.getPreviewImageView().setImage(null);

            // Kembalikan ke kondisi awal
            view.getDeleteImageButton().setVisible(false);
            // Anda mungkin tidak memiliki getter untuk ini, bisa ditambahkan jika perlu
            // view.getUploadIcon().setVisible(true);
            // view.getDropText().setVisible(true);
        });


        // --- Logika untuk Tombol Simpan ---
        view.getSimpanButton().setOnAction(event -> handleSimpan());

        view.getDropZoneContainer().setOnMouseClicked(event -> openFileChooser());
        view.getDeleteImageButton().setOnAction(event -> resetImage());

        // --- Logika untuk Tombol Batal ---
        view.getBatalButton().setOnAction(event -> {
            resetAllFields();
            System.out.println("Semua field telah direset.");
        });
    }

    private void handleSimpan() {
        // 1. Ambil data dari form
        String namaKos = view.getNamaKosField().getText();
        String deskripsi = view.getDeskripsiArea().getText();
        String provinsi = view.getProvinsiComboBox().getValue();
        String kota = view.getKotaComboBox().getValue();
        String alamatLengkap = view.getAlamatArea().getText();
        String kodePos = view.getKodePosField().getText();
        int harga = Integer.parseInt(view.getHargaKosField().getText()); // Ubah ke int

        // Gabungkan alamat
        String alamatGabungan = String.join(", ", provinsi, kota, alamatLengkap, kodePos);

        // Ambil data user yang login
        new Session();
        UserModel currentUser = Session.get();
        if (currentUser == null) {
            System.err.println("Tidak ada user yang login, tidak bisa menyimpan.");
            return;
        }

        // 2. Ubah gambar menjadi byte array
        byte[] imageData = null;
        if (selectedImageFile != null) {
            try {
                imageData = Files.readAllBytes(selectedImageFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle error baca file
                return;
            }
        }

        // 3. Kirim data ke service untuk disimpan ke database
        try {
            boolean isSuccess;

            if (kosToEdit == null) {
                // Mode "Tambah Baru": Panggil metode create
                isSuccess = kostService.create(
                        currentUser.getId(),
                        namaKos,
                        alamatGabungan,
                        harga,
                        deskripsi,
                        imageData
                );
                if (isSuccess) {
                    showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data kos baru berhasil disimpan!");
                }
            } else {
                // Mode "Edit": Update objek yang ada, lalu panggil metode update
                kosToEdit.setName(namaKos);
                kosToEdit.setPrice(harga);
                kosToEdit.setLocation(alamatGabungan);
                kosToEdit.setDescription(deskripsi);
                if (imageData != null) {
                    kosToEdit.setImage(imageData);
                }
                isSuccess = kostService.updateById(kosToEdit);
                if (isSuccess) {
                    showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data kos berhasil diperbarui!");
                }
            }
            // -----------------------------

            if (isSuccess) {
                adminPageController.refreshDashboardData(); // Refresh tabel
            } else {
                showAlert(Alert.AlertType.ERROR, "Gagal", "Operasi data kos gagal.");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan. Pastikan semua data terisi dengan benar.");
        }
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Gambar Properti");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        Stage stage = (Stage) view.getView().getScene().getWindow();

        this.selectedImageFile = fileChooser.showOpenDialog(stage); // Simpan file-nya

        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            view.getPreviewImageView().setImage(image);
            view.getDeleteImageButton().setVisible(true);
        }
    }

    private void resetImage() {
        view.getPreviewImageView().setImage(null);
        view.getDeleteImageButton().setVisible(false);
        this.selectedImageFile = null; // Hapus referensi file
    }

    /**
     * Metode untuk mereset semua field input menjadi kosong.
     */
    private void resetAllFields() {
        view.getNamaKosField().clear();
        view.getHargaKosField().clear();
        view.getDeskripsiArea().clear();
        view.getProvinsiComboBox().setValue(null); // Mengosongkan ComboBox
        view.getKotaComboBox().getItems().clear();
        view.getKotaComboBox().setDisable(true);
        view.getAlamatArea().clear();
        view.getKodePosField().clear();
        resetImage();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }



}