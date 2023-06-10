package Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class Kulkas extends DataKulkas{
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public Kulkas(String brand, int harga, int stok, String tipe, int berat, String deskripsi, String gambar) {
        super(brand, tipe, deskripsi, gambar, harga, stok, berat);
    }
    
    public void addDB() throws SQLException{
        connect = Database.connectDB();
        String query = "INSERT INTO tbkulkas(brand, harga, stok, tipe, berat, deskripsi, gambar) VALUES (?,?,?,?,?,?,?)";

        prepare = connect.prepareStatement(query);

        prepare.setString(1, this.getBrand());
        prepare.setInt(2, this.getHarga());
        prepare.setInt(3, this.getStok());
        prepare.setString(4, this.getTipe());
        prepare.setInt(5, this.getBerat());
        prepare.setString(6, this.getDeskripsi());
        prepare.setString(7, this.getGambar());

        prepare.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tambah Berhasil");
        alert.setContentText("Produk Berhasil di Tambah");
        alert.showAndWait();
    }
    
    public void editDB(String brand, int harga, int stok, String tipe, int berat, String deskripsi, String gambar, int id_brand) throws SQLException{
        connect = Database.connectDB();
        this.setBrand(brand);
        this.setTipe(tipe);
        this.setDeskripsi(deskripsi);
        this.setGambar(gambar);
        this.setHarga(harga);
        this.setStok(stok);
        this.setBerat(berat);

        String query = "UPDATE tbkulkas set brand=?,harga=?,stok=?,tipe=?,berat=?,deskripsi=?,gambar=? where id_brand=?";
        
        prepare = connect.prepareStatement(query);
        
        prepare.setString(1, this.getBrand());
        prepare.setInt(2, this.getHarga());
        prepare.setInt(3, this.getStok());
        prepare.setString(4, this.getTipe());
        prepare.setInt(5, this.getBerat());
        prepare.setString(6, this.getDeskripsi());
        prepare.setString(7, this.getGambar());
        prepare.setInt(8, id_brand);
        
        prepare.executeUpdate();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Edit Berhasil");
        alert.setContentText("Produk Berhasil di Edit");
        alert.showAndWait();
    }
    
    public void hapustodb(int id_brand) throws SQLException{
        connect = Database.connectDB();
        String query = "delete from tbkulkas where id_brand=?";
        
        prepare = connect.prepareStatement(query);
        prepare.setInt(1, id_brand);
        prepare.executeUpdate();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hapus Berhasil");
        alert.setContentText("Produk Berhasil di Hapus");
        alert.showAndWait();
    }
}
