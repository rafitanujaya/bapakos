package Model;

public class BookingModelDummy {
    private int no;
    private String namaKos;
    private String namaPenyewa;
    private String hargaSewa;

    public BookingModelDummy(int no, String namaKos, String namaPenyewa, String hargaSewa) {
        this.no = no;
        this.namaKos = namaKos;
        this.namaPenyewa = namaPenyewa;
        this.hargaSewa = hargaSewa;
    }

    // Getter untuk setiap properti
    public int getNo() { return no; }
    public String getNamaKos() { return namaKos; }
    public String getNamaPenyewa() { return namaPenyewa; }
    public String getHargaSewa() { return hargaSewa; }
}
