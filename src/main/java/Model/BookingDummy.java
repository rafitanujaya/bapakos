package Model;

public class BookingDummy {
    private String nama;
    private String kamar;
    // Tambahkan properti lain jika perlu

    public BookingDummy(String nama, String kamar) {
        this.nama = nama;
        this.kamar = kamar;
    }

    public String getNama() { return nama; }
    public String getKamar() { return kamar; }
}
