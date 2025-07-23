# 🏠 Aplikasi Manajemen Kost - JavaFX

Aplikasi desktop berbasis JavaFX untuk mengelola kost, melakukan booking, serta mengatur pengguna dan penyewa. Proyek ini menerapkan standar profesional seperti arsitektur MVC, pola DAO, dan pengembangan modular untuk memastikan skalabilitas dan kemudahan pemeliharaan.

---

## 📌 Fitur

- Registrasi & Login Pengguna (dengan peran: Pengguna & Penyewa)
- CRUD Kost (oleh Penyewa)
- Pencarian Kost (oleh Pengguna)
- Alur Booking Kost → Persetujuan → Pembayaran
- Tampilan berdasarkan peran pengguna
- Pelacakan status booking & pembayaran

---

## 🧱 Arsitektur

### Pola: MVC + Service + DAO

```
/src/main/java/com/namadomain/kostapp
├── config/         → Konfigurasi aplikasi & koneksi DB
├── dao/            → Lapisan akses database (CRUD)
├── model/          → Kelas data seperti User, Kost, Booking
├── service/        → Logika bisnis & validasi
├── controller/     → Controller JavaFX untuk menangani UI
├── view/           → Helper tampilan (opsional)
└── Main.java       → Titik masuk aplikasi

/resources/
├── fxml/           → Desain UI (FXML)
├── css/            → Styling JavaFX
├── images/         → Ikon, aset visual
├── db/             → Skema SQL / mock DB
└── config.properties (opsional)
```

---

## 🧩 Konvensi Penamaan

| Komponen     | Format              | Contoh                        |
|--------------|---------------------|-------------------------------|
| Package      | lowercase           | com.kostinaja.kostapp.service |
| Class        | PascalCase          | BookingService, KostDao       |
| Controller   | PascalCase          | LoginController               |
| FXML File    | snake_case          | login.fxml                    |
| CSS File     | kebab-case          | main-style.css                |

---

## 🔁 Alur Git & Konvensi Commit

### Branch:
- `main` → kode siap produksi
- `dev` → pengembangan aktif
- `feat/<fitur>` → fitur baru
- `fix/<bug>` → perbaikan bug
- `refactor/<bagian>` → refactor kode

### Format Commit (Conventional Commit):
```
<type>(scope): deskripsi singkat
```

#### Contoh:
- feat(auth): tambahkan logika login dengan hashing password
- fix(booking): perbaiki konflik booking ganda
- docs(readme): update petunjuk penggunaan proyek

---

## 🛠️ Cara Menjalankan Aplikasi

### Syarat:
- Java 11 ke atas
- JavaFX SDK
- Maven atau Gradle (pilih salah satu)

### Langkah:
1. Clone repo:
```bash
git clone https://github.com/username/kost-management-app-javafx.git
```
2. Buka di IntelliJ atau VS Code (dengan ekstensi Java)
3. Jalankan `Main.java`

---

## 💡 Panduan Pengembangan

- DAO: Fokus pada query dan transaksi database
- Service: Menangani logika booking, validasi, dan proses bisnis
- Controller: Menangani interaksi UI (onClick, navigasi, dsb)
- FXML: Hanya untuk desain UI, hindari logika berat di sini
- Gunakan `DBConfig.java` untuk koneksi database terpusat

---

## 🧪 Strategi Testing

- Unit test pada Service & DAO menggunakan JUnit 5
- Testing manual via UI JavaFX
- Opsional: Buat test data di `/resources/db/`

---

## 🧠 Best Practice

- Selalu gunakan `PreparedStatement` (hindari SQL injection)
- Simpan password dalam bentuk hash (mis. PBKDF2, bcrypt)
- Hindari logika kompleks di controller
- Validasi semua input pengguna sebelum simpan ke DB

---

## 📄 Lisensi

Proyek ini menggunakan lisensi MIT.

---

## 👨‍💻 Kontributor

- @yourusername - Principal Engineer
- @teammate1 - Frontend JavaFX
- @teammate2 - Backend & Database