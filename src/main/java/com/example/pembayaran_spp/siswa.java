package com.example.pembayaran_spp;

public class siswa {
    
    private Integer no;
    private String nama;
    private String kelas;
    private String nis;
    private String status;

    public siswa(Integer no, String nama, String kelas, String nis, String status)
    {
        this.no = no;
        this.nama = nama;
        this.kelas = kelas;
        this.nis = nis;
        this.status = status;
    }

    public Integer getNo() {
        return no;
    }
    public String getNama() {
        return nama;
    }
    public String getKelas() {
        return kelas;
    }
    public String getNis() {
        return nis;
    }
    public String getStatus() {
        return status;
    }
}
