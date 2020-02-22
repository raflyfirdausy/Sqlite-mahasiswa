package id.ac.amikompurwokerto.sqlitemahasiswa;

public class MahasiswaModel {
    String nim;
    String nama;

    String alamat;
    String jk;
    String agama;
    String tgl;

    String prodi;
    String matakuliah;
    String nilai_awal;
    String nilai_akhir;

    public MahasiswaModel() {
    }

    public MahasiswaModel(String nim, String nama, String alamat, String jk, String agama, String tgl) {
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jk = jk;
        this.agama = agama;
        this.tgl = tgl;
    }


    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(String matakuliah) {
        this.matakuliah = matakuliah;
    }

    public String getNilai_awal() {
        return nilai_awal;
    }

    public void setNilai_awal(String nilai_awal) {
        this.nilai_awal = nilai_awal;
    }

    public String getNilai_akhir() {
        return nilai_akhir;
    }

    public void setNilai_akhir(String nilai_akhir) {
        this.nilai_akhir = nilai_akhir;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
