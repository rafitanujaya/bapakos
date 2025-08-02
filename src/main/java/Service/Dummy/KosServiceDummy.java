package Service.Dummy;

import Model.Dummy.KosDummy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class KosServiceDummy {

    private final ObservableList<KosDummy> allKos;

    public KosServiceDummy() {
        // Simpan daftar lengkap kos saat service dibuat
        allKos = FXCollections.observableArrayList(
                new KosDummy(1, "Kos Jamet", "Jl. Tubagus 5 No 9", "Rp 1.500.000,00"),
                new KosDummy(2, "Kos Mawar", "Jl. Mawar No 10", "Rp 1.200.000,00"),
                new KosDummy(3, "Kos Anggrek", "Jl. Anggrek No 1", "Rp 1.800.000,00"),
                new KosDummy(4, "Kos Melati", "Jl. Melati No 22", "Rp 1.000.000,00")
        );
    }

    // Metode ini mengembalikan semua data
    public ObservableList<KosDummy> getAllKos() {
        return allKos;
    }

    /**
     * Metode BARU untuk mencari kos berdasarkan nama.
     * @param keyword Kata kunci pencarian.
     * @return Daftar kos yang sudah difilter.
     */
    public ObservableList<KosDummy> searchKosByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return allKos; // Jika kosong, kembalikan semua
        }

        String lowerCaseKeyword = keyword.toLowerCase();

        // Filter daftar 'allKos'
        List<KosDummy> filteredList = allKos.stream()
                .filter(kos -> kos.getNama().toLowerCase().contains(lowerCaseKeyword))
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(filteredList);
    }
}