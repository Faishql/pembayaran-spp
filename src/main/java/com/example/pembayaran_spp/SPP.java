package com.example.pembayaran_spp;

public class SPP {
    private int no;
    private String kelas;
    private int nominal;
    private String tanggal;


    public SPP(int no, String kelas, Integer nominal,String tanggal) {
        this.no = no;
        this.kelas = kelas;
        this.nominal = nominal;
        this.tanggal = tanggal;
    }


    public int getNo() {
        return no;
    }
    public String getKelas() {
        return kelas;
    }
    public Integer getNominal() {
        return nominal;
    }
    public String getTanggal() {
        return tanggal;
    }
}
