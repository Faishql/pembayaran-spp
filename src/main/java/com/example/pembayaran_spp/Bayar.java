package com.example.pembayaran_spp;

public class Bayar {

    private String kelas;
    private String nominal;
    private String status;
    private String tanggal;

    public Bayar(String kelas, String nominal, String status, String tanggal) {
        this.kelas = kelas;
        this.nominal = nominal;
        this.status = status;
        this.tanggal = tanggal;
    }

    public String getKelas() {
        return kelas;
    }

    public String getNominal() {
        return nominal;
    }

    public String getStatus() {
        return status;
    }

    public String getTanggal() {
        return tanggal;
    }
}
