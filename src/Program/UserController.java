package Program;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserController implements Initializable {
    public int id_akun;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    @FXML
    private Label username;
    @FXML
    private Button btnLogout;
    @FXML
    private Button menuLihat;
    ObservableList<Kulkas> listKulkas;
    @FXML
    private TableView<Kulkas> tableKulkas;
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
    @FXML
    private Label labelproduk;
    @FXML
    private TextField txtCari;
    @FXML
    private Button btnExit;
    @FXML
    private ImageView detailgambar;
    @FXML
    private TextField detaildeskripsi;
    @FXML
    private TextField detailharga;
    @FXML
    private TextField detailtipe;
    @FXML
    private TextField detailberat;
    @FXML
    private TextField detailbrand;
    
    @FXML
    private AnchorPane menuLihat1;
    @FXML
    private Label frost;
    @FXML
    private ImageView gambarlogo;
    @FXML
    private ImageView pintukeluar;
    @FXML
    private Button btnLihatDetail;
    @FXML
    private AnchorPane detailProduk;
    @FXML
    private Button btnKembali;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Logout");
        alert.setHeaderText(null);
        alert.setContentText("Anda yakin ingin logout?");

        ButtonType buttonTypeYes = new ButtonType("Ya");
        ButtonType buttonTypeNo = new ButtonType("Tidak");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            try {
                btnLogout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = new Stage();
                Main.drag(stage, root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root, 614, 415));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void setUsername(String user) {
        username.setText(user);
    }
    
    public void setTableKulkas(String s) {
        try {
            connect = Database.connectDB();
            String SQL = "SELECT * FROM tbkulkas WHERE brand LIKE '%" + s + "%'";
            prepare = connect.prepareStatement(SQL);
            result = prepare.executeQuery();

            this.listKulkas = FXCollections.observableArrayList();

            while (result.next()) {
                Kulkas kulkass = new Kulkas(result.getString("brand"),
                        result.getInt("harga"),
                        result.getInt("stok"),
                        result.getString("tipe"),
                        result.getInt("berat"),
                        result.getString("deskripsi"),
                        result.getString("gambar")
                );
                listKulkas.add(kulkass);
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
            alert.setTitle("Proses Gagal! (Error)");
            alert.setContentText("Load Data Kulkas Gagal Dilakukan");
            alert.showAndWait();
        }
    }

    @FXML
    private void lihatKulkas(MouseEvent event) {
        menuLihat1.setVisible(true);
        setTableKulkas(txtCari.getText());
    }

    @FXML
    private void cariKulkas(KeyEvent event) {
        setTableKulkas(txtCari.getText());
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void lihatDetail(MouseEvent event) throws FileNotFoundException {
        Kulkas kulkass = tableKulkas.getSelectionModel().getSelectedItem();
        if (kulkass != null) {

            // Mengambil gambar dari imagePath
            Image image = new Image(new FileInputStream(System.getProperty("user.dir") + "/GambarKulkas/" + kulkass.getGambar()));

            detailbrand.setText(kulkass.getBrand());
            detailharga.setText(String.valueOf("Harga: Rp. " + kulkass.getHarga()));
            detailtipe.setText("Tipe: " + kulkass.getTipe());
            detailberat.setText(String.valueOf("Berat: " + kulkass.getBerat()) + "Kg");
            detaildeskripsi.setText(kulkass.getDeskripsi());

            detailgambar.setImage(image);
            
            detailProduk.setVisible(true);
            menuLihat1.setVisible(false);
            
            }
        }

    @FXML
    private void kembali(MouseEvent event) {
        menuLihat1.setVisible(true);
        detailProduk.setVisible(false);
    }
 }
    
