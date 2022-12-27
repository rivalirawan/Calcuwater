package com.mobileprogramming.calcuwater.Model;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("lebar_ambang")
    private String lebar_ambang;
    @SerializedName("tinggi_bukaan")
    private String tinggi_bukaan;
    @SerializedName("selisih_tinggi")
    private String selisih_tinggi;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    private String debit;

    public Data(){}

    public Data(String id, String nama, String lebar_ambang, String tinggi_bukaan, String selisih_tinggi, String tanggal) {
        this.id = id;
        this.nama = nama;
        this.lebar_ambang = lebar_ambang;
        this.tinggi_bukaan = tinggi_bukaan;
        this.selisih_tinggi = selisih_tinggi;
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLebar_ambang() {
        return lebar_ambang;
    }

    public void setLebar_ambang(String lebar_ambang) {
        this.lebar_ambang = lebar_ambang;
    }

    public String getTinggi_bukaan() {
        return tinggi_bukaan;
    }

    public void setTinggi_bukaan(String tinggi_bukaan) {
        this.tinggi_bukaan = tinggi_bukaan;
    }

    public String getSelisih_tinggi() {
        return selisih_tinggi;
    }

    public void setSelisih_tinggi(String selisih_tinggi) {
        this.selisih_tinggi = selisih_tinggi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
