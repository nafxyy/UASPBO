package Program;

import java.sql.SQLException;

public abstract class DataKulkas {
    private String brand;
    private String tipe;
    private String deskripsi;
    private String gambar;
    private int harga;
    private int stok;
    private int berat;
    private int id_brand;

    public DataKulkas(String brand, String tipe, String deskripsi, String gambar, int harga, int stok, int berat) {
        this.brand = brand;
        this.tipe = tipe;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
        this.harga = harga;
        this.stok = stok;
        this.berat = berat;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
    
    public int getHarga(){
        return harga;
    }
    
    public void setHarga(int harga){
        this.harga = harga;
    }
    
    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }
}
