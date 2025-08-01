package Model;

public class TransaksiModelDummy {
    private int no;
    private String namaKos;
    private String namaPenyewa;
    private String hargaSewa;
    private String status;

    public TransaksiModelDummy(int no, String namaKos, String namaPenyewa, String hargaSewa, String status) {
        this.no = no;
        this.namaKos = namaKos;
        this.namaPenyewa = namaPenyewa;
        this.hargaSewa = hargaSewa;
        this.status = status;
    }

    // Getter untuk setiap properti
    public int getNo() { return no; }
    public String getNamaKos() { return namaKos; }
    public String getNamaPenyewa() { return namaPenyewa; }
    public String getHargaSewa() { return hargaSewa; }
    public String getStatus() { return status; }
}