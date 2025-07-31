package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaksi {
    private final StringProperty nama;
    private final StringProperty kamar;
    private final StringProperty tanggal;
    private final StringProperty jumlah;
    private final StringProperty status;
    private final StringProperty imagePath;

    public Transaksi(String imagePath, String nama, String kamar, String tanggal, String jumlah, String status) {
        this.imagePath = new SimpleStringProperty(imagePath);
        this.nama = new SimpleStringProperty(nama);
        this.kamar = new SimpleStringProperty(kamar);
        this.tanggal = new SimpleStringProperty(tanggal);
        this.jumlah = new SimpleStringProperty(jumlah);
        this.status = new SimpleStringProperty(status);
    }

    // Getter untuk setiap properti
    public String getNama() { return nama.get(); }
    public StringProperty namaProperty() { return nama; }
    public String getKamar() { return kamar.get(); }
    public StringProperty kamarProperty() { return kamar; }
    public String getTanggal() { return tanggal.get(); }
    public StringProperty tanggalProperty() { return tanggal; }
    public String getJumlah() { return jumlah.get(); }
    public StringProperty jumlahProperty() { return jumlah; }
    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
    public String getImagePath() { return imagePath.get(); }
    public StringProperty imagePathProperty() { return imagePath; }
}