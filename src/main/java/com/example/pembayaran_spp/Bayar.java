package com.example.pembayaran_spp;

public class Bayar {

    private String kelas;
    private String nominal;
    private String tanggal;
    private String status;


    public Bayar(String kelas, String nominal, String tanggal,  String status) {
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

    public String getTanggal() {
        return tanggal;
    }

    public String getStatus() {
        return status;
    }


}
