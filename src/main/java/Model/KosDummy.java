package Model;

import Service.KosServiceDummy; // Ubah ke model Kos yang sesuai
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Sebuah metode harus berada di dalam sebuah kelas
public class KosDummy {

    // Metode Anda sekarang berada di dalam kelas ini
    private int no;
    private String nama;
    private String alamat;
    private String harga;

    public KosDummy(int no, String nama, String alamat, String harga) {
        this.no = no;
        this.nama = nama;
        this.alamat = alamat;
        this.harga = harga;
    }

    public int getNo() { return no; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }
    public String getHarga() { return harga; }

    // Anda juga bisa menambahkan metode lain untuk mengambil data lain di sini
    // misalnya getRecentTransactions(), getPendingBookings(), dll.
}