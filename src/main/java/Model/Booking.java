package Model;

public class Booking {
    private String nama;
    private String kamar;
    // Tambahkan properti lain jika perlu

    public Booking(String nama, String kamar) {
        this.nama = nama;
        this.kamar = kamar;
    }

    public String getNama() { return nama; }
    public String getKamar() { return kamar; }
}
