package Program;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminController implements Initializable {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private File filegambar;

    @FXML
    private Label username;
    @FXML
    private Button btnLogout;
    @FXML
    private Button menuTambah;
    @FXML
    private Button menuLihat;
    @FXML
    private Label username1;
    @FXML
    private TextField txtBrand;
    @FXML
    private TextField txtHarga;
    @FXML
    private TextField txtBerat;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClr;
    @FXML
    private ComboBox<String> cbStok;
    @FXML
    private Label username11;
    @FXML
    private TextField txtDesc;
    @FXML
    private TableView<Kulkas> tableKulkas;
    @FXML
    private Label labelproduk;
    @FXML
    private TextField txtCari;
    @FXML
    private Button btnExit;
    @FXML
    private Label username2;
    @FXML
    private Label username21;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnHapus;
    
    ObservableList<Kulkas> listKulkas;
    @FXML
    private TableColumn<Kulkas, String> brandK;
    @FXML
    private TableColumn<Kulkas, String> hargaK;
    @FXML
    private TableColumn<Kulkas, String> stokK;
    @FXML
    private TableColumn<Kulkas, String> tipeK;
    @FXML
    private TableColumn<Kulkas, String> beratK;
    @FXML
    private TableColumn<Kulkas, String> deskripsiK;
    private AnchorPane menuEdit;
    @FXML
    private Label username12;
    @FXML
    private TextField txtEditBrand;
    @FXML
    private TextField txtEditBerat;
    @FXML
    private ComboBox<String> cbEditStok;
    @FXML
    private Label username111;
    @FXML
    private TextField txtEditDesc;
    @FXML
    private TextField txtEditHarga;
    @FXML
    private Button btnKembali;
     @FXML
    private RadioButton tipe1;
     @FXML
    private RadioButton tipe2;
    @FXML
    private RadioButton tipe3;
    @FXML
    private ToggleGroup tipekulkas;
    
    private String tipe_kulkas;
    @FXML
    private ImageView imageView;
    @FXML
    private Button gambar;
    @FXML
    private ImageView imageView1;
    @FXML
    private Button gambar1;
    @FXML
    private ImageView gambarlogo;
    @FXML
    private AnchorPane menutambahdisplay;
    @FXML
    private AnchorPane menulihatdisplay;
    @FXML
    private AnchorPane menueditdisplay;
    @FXML
    private RadioButton tipeEdit1;
    @FXML
    private ToggleGroup tipeEditKulkas;
    @FXML
    private RadioButton tipeEdit2;
    @FXML
    private RadioButton tipeEdit3;

    private void clear(){
        txtBrand.clear();
        txtHarga.clear();
        cbStok.setValue(null);
        txtBerat.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(MouseEvent event) {
        try {
            btnLogout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = new Stage();
            Main.drag(stage, root);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root, 614, 415));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void setCbStok(ObservableList<String> stok) {
        cbStok.setItems(stok);
        cbEditStok.setItems(stok);
    }
    @FXML
    private void addData(MouseEvent event) throws Exception {
        Path source = null;
        String extension = "";
        String query = "SELECT * FROM tbkulkas WHERE brand = ?";
        connect = Database.connectDB();
        prepare = connect.prepareStatement(query);
        prepare.setString(1,txtBrand.getText());
        result = prepare.executeQuery();
        if (!result.next()) {
            int harga = Integer.parseInt(txtHarga.getText());
            int stok = Integer.parseInt(cbStok.getValue());
            String tipe = tipe_kulkas;
            int berat = Integer.parseInt(txtBerat.getText());
            String deskripsi = txtDesc.getText();
            String gambar = txtBrand.getText();

            if (filegambar != null){
                source = filegambar.toPath();
                int i = filegambar.getName().lastIndexOf('.');
                if (i > 0) {
                    extension = filegambar.getName().substring(i+1);
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setContentText("WAJIB PAKE GAMBAR");
                alert.showAndWait(); 
            }

        Path destination = Paths.get(System.getProperty("user.dir"),"/GambarKulkas/", txtBrand.getText() + "."+ extension);
        Files.copy(source,destination);
        Kulkas kulkass = new Kulkas(txtBrand.getText(), harga, stok, tipe, berat, deskripsi, gambar + "."+ extension);
        kulkass.addDB();

        clear();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Duplikat Nama Produk");
            alert.setContentText("Add Data Produk Gagal");
            alert.showAndWait();
        }
    }
    
    public void setTableKulkas(String s) {
        try {
            connect = Database.connectDB();
            String SQL = "SELECT * FROM tbkulkas WHERE brand LIKE '%" + s + "%'";
            prepare = connect.prepareStatement(SQL);
            result = prepare.executeQuery();

            this.listKulkas = FXCollections.observableArrayList();

            while (result.next()) {
                Kulkas kulkas = new Kulkas(result.getString("brand"),
                        result.getInt("harga"),
                        result.getInt("stok"),
                        result.getString("tipe"),
                        result.getInt("berat"),
                        result.getString("deskripsi"),
                        result.getString("gambar")
                );
                listKulkas.add(kulkas);
            }

            brandK.setCellValueFactory(new PropertyValueFactory<>("brand"));
            hargaK.setCellValueFactory(new PropertyValueFactory<>("harga"));
            stokK.setCellValueFactory(new PropertyValueFactory<>("stok"));
            tipeK.setCellValueFactory(new PropertyValueFactory<>("tipe"));
            beratK.setCellValueFactory(new PropertyValueFactory<>("berat"));
            deskripsiK.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

            tableKulkas.setItems(listKulkas);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gagal Memuat Data!");
            alert.setContentText("Gagal Memuat Data Produk");
            alert.showAndWait();
        }
    }
    
    public void setUsername(String user) {
        username.setText(user);
    }

    @FXML
    private void clrForm(MouseEvent event) {
        clear();
    }
    
    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void tambahKulkas(MouseEvent event) {
        menutambahdisplay.setVisible(true);
        menulihatdisplay.setVisible(false);
    }

    @FXML
    private void lihatKulkas(MouseEvent event) {
        menutambahdisplay.setVisible(false);
        menulihatdisplay.setVisible(true);
        setTableKulkas(txtCari.getText());
    }

    @FXML
    private void editData(MouseEvent event) throws Exception {
            Path source = null;
            String extension = "";
            Kulkas kulkass = tableKulkas.getSelectionModel().getSelectedItem();

            String ambil_id = "SELECT id_brand FROM tbkulkas WHERE brand = ?";
            prepare = connect.prepareStatement(ambil_id);
            prepare.setString(1, kulkass.getBrand());
            result = prepare.executeQuery();
            
            int id_brand = 0;
            String brand = txtEditBrand.getText();
            int harga = Integer.parseInt(txtEditHarga.getText());
            int stok = Integer.parseInt(cbEditStok.getValue());
            String tipe = tipe_kulkas;
            int berat = Integer.parseInt(txtEditBerat.getText());
            String deskripsi = txtEditDesc.getText();
            String gambar = kulkass.getGambar();
            
            if (filegambar != null){
                source = filegambar.toPath();
                int i = filegambar.getName().lastIndexOf('.');
                if (i > 0) {
                    extension = filegambar.getName().substring(i+1);
                }
            }
            else{
                source = Paths.get(System.getProperty("user.dir"),"/GambarKulkas/", kulkass.getGambar());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setContentText("WAJIB PAKE GAMBAR");
                alert.showAndWait(); 
            }
            gambar = brand + "." + extension;
            Path destination = Paths.get(System.getProperty("user.dir"),"/GambarKulkas/", txtEditBrand.getText() + "."+ extension);
            Files.copy(source,destination);
            
            if(result.next()){
                id_brand = result.getInt("id_brand");
            }

            filegambar = null;
            try {
                Files.delete(Paths.get(System.getProperty("user.dir"),"/GambarKulkas/", kulkass.getGambar()));
                System.out.println("wow");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            
            kulkass.editDB(brand, harga, stok, tipe, berat, deskripsi, gambar, id_brand);

            txtCari.setText("");
            setTableKulkas(txtCari.getText());
            clear();
            menutambahdisplay.setVisible(false);
            menulihatdisplay.setVisible(true);
            menueditdisplay.setVisible(false);
    }

    @FXML
    private void Editrb1(ActionEvent event) {
        tipe_kulkas = tipeEdit1.getText();
    }

    @FXML
    private void Editrb2(ActionEvent event) {
        tipe_kulkas = tipeEdit2.getText();
    }

    @FXML
    private void Editrb3(ActionEvent event) {
        tipe_kulkas = tipeEdit3.getText();
    }

    @FXML
    private void hapusData(MouseEvent event) throws Exception {
        ButtonType yes = new ButtonType("Ya", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Tidak", ButtonBar.ButtonData.CANCEL_CLOSE);
        Kulkas kulkass = tableKulkas.getSelectionModel().getSelectedItem();

        int id_brand = 0;
        
        String ambil_id = "SELECT id_brand FROM tbkulkas WHERE brand = ?";
        prepare = connect.prepareStatement(ambil_id);
        prepare.setString(1, kulkass.getBrand());
        result = prepare.executeQuery();
        if(result.next()){
            id_brand= result.getInt("id_brand");
        }
        
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Yakin ingin menghapus "+ kulkass.getBrand(),
                yes,
                no);

        alert.setTitle("Menghapus data");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            Files.delete(Paths.get(System.getProperty("user.dir"),"/GambarKulkas/", kulkass.getGambar()));
            kulkass.hapustodb(id_brand);
        }

        txtCari.setText("");
        setTableKulkas(txtCari.getText());
    }

    @FXML
    private void cariKulkas(KeyEvent event) {
        setTableKulkas(txtCari.getText());
    }

    @FXML
    private void menuEditData(MouseEvent event) throws Exception {
        Kulkas kulkass = tableKulkas.getSelectionModel().getSelectedItem();
        if (kulkass != null) {
            FileInputStream awu = new FileInputStream(System.getProperty("user.dir") + "/GambarKulkas/" + kulkass.getGambar());
            Image image = new Image(awu);
            awu.close();
            
            txtEditBrand.setText(kulkass.getBrand());
            txtEditHarga.setText(String.valueOf(kulkass.getHarga()));
            if(null != kulkass.getTipe())switch (kulkass.getTipe()) {
                case "1 Pintu":
                    tipeEdit1.setSelected(true);
                    tipe_kulkas = "1 Pintu";
                    break;
                case "2 Pintu":
                    tipeEdit2.setSelected(true);
                    tipe_kulkas = "2 Pintu";
                    break;
                case "3 Pintu":
                    tipeEdit3.setSelected(true);
                    tipe_kulkas = "3 Pintu";
                    break;
                default:
                    break;
            }
            txtEditBerat.setText(String.valueOf(kulkass.getBerat()));
            txtEditDesc.setText(kulkass.getDeskripsi());

            imageView1.setImage(image);
        }
        menutambahdisplay.setVisible(false);
        menulihatdisplay.setVisible(false);
        menueditdisplay.setVisible(true);
    }

    @FXML
    private void kembali(MouseEvent event) {
        menulihatdisplay.setVisible(true);
        menueditdisplay.setVisible(false);
    }

    @FXML
    private void actionrb1(ActionEvent event) {
        tipe_kulkas = tipe1.getText();
    }

    @FXML
    private void actionrb2(ActionEvent event) {
        tipe_kulkas = tipe2.getText();
    }

    @FXML
    private void actionrb3(ActionEvent event) {
        tipe_kulkas = tipe3.getText();
    }

    @FXML
    private void pilihGambar(MouseEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Pilih Gambar");
        FileChooser fc = new FileChooser();
        
        filegambar = fc.showOpenDialog(stage);
        
        if (filegambar != null){
            String path = filegambar.getAbsolutePath();
            Image image = new Image(path);
            if (menutambahdisplay.isVisible()){
                imageView.setImage(image);
                imageView.setFitHeight(50.0);
                imageView.setFitWidth(70.0);
            }
            else{
                imageView1.setImage(image);
                imageView1.setFitHeight(50.0);
                imageView1.setFitWidth(70.0);
            }
            image = null;
        }
        stage.close();
    }
}
