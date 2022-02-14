package com.example.pembayaran_spp;

public class Siswa {
    
    private int no;
    private String nis;
    private String nama;
    private String kelas;
    private String status;

    public Siswa(int no, String nis, String nama, String kelas, String status) {
        this.no = no;
        this.nis = nis;
        this.nama = nama;
        this.kelas = kelas;
        this.status = status;
    }

    public int getNo() {
        return no;
    }

    public String getNis() {
        return nis;
    }

    public String getNama() {
        return nama;
    }

    public String getKelas() {
        return kelas;
    }

    public String getStatus() {
        return status;
    }
}
