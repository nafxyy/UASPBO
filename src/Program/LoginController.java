package Program;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
    @FXML
    private TextField txtuser;
    @FXML
    private PasswordField txtpass;
    @FXML
    private Button btnLogin;
    @FXML
    private Label registrasi;
    @FXML
    private Button btnExit;
    @FXML
    private AnchorPane registerkanan;
    @FXML
    private Button regBtn;
    @FXML
    private AnchorPane loginform;
    @FXML
    private AnchorPane loginkanan;
    @FXML
    private AnchorPane registerform;
    @FXML
    private TextField txtNama;
    @FXML
    private PasswordField txtPass;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUsername; 
    @FXML
    private AnchorPane welcomepage;
    @FXML
    private Label login;
    @FXML
    private Label welcome;
     
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    ObservableList<String> stok = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7");
   
    @FXML
    private void login(MouseEvent event) throws SQLException {
        String query = "SELECT * FROM tbakunpbo WHERE username = ? AND password = ?";
        connect = Database.connectDB();
        try{
            Alert alert;
            prepare = connect.prepareStatement(query);
            prepare.setString(1, txtuser.getText());
            prepare.setString(2, txtpass.getText());
            result = prepare.executeQuery();
            
            if(result.next()){
                Parent root;
                if("Admin".equals(result.getString(4))){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAdmin.fxml"));
                    root = loader.load();
                    AdminController controller = loader.getController();
                    controller.setCbStok(stok);
                    controller.setUsername(txtuser.getText());
                    
                    btnLogin.getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Main.drag(stage, root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(new Scene(root,700, 512));
                    stage.show();
                }else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PageUser.fxml"));
                    root = loader.load();
                    UserController controller = loader.getController();
                    controller.setTableKulkas("");
                    controller.id_akun = result.getInt("id_akun");
                    controller.setUsername(txtuser.getText());
                    
                    btnLogin.getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Main.drag(stage, root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(new Scene(root,705, 512));
                    stage.show();
                } 
            } 
            else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Login Gagal!");
                alert.setHeaderText(null);
                alert.setContentText("Kesalahan pada Username/Password");
                alert.showAndWait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void formRegis(MouseEvent event) {
        loginform.setVisible(false);
        loginkanan.setVisible(false);
        registerform.setVisible(true);
        registerkanan.setVisible(true);
    }
    
    @FXML
    private void formLogin(MouseEvent event) {
        loginform.setVisible(true);
        loginkanan.setVisible(true);
        registerform.setVisible(false);
        registerkanan.setVisible(false);
    }
    
    private boolean cekDataRegis(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(txtNama.getText().isEmpty()){
            alert.setTitle("NOTIFIKASI");
            alert.setContentText("Data Nama Belum Diisi");
            alert.showAndWait();
            txtNama.requestFocus();
            return false;
        }else if (txtEmail.getText().isEmpty()){
            alert.setTitle("NOTIFIKASI");
            alert.setContentText("Data Email Belum Diisi");
            alert.showAndWait();
            txtEmail.requestFocus();
            return false;
        }else if (txtUsername.getText().isEmpty()){
            alert.setTitle("NOTIFIKASI");
            alert.setContentText("Data Username Belum Diisi");
            alert.showAndWait();
            txtUsername.requestFocus();
            return false;
        }else if (txtPass.getText().isEmpty()){
           alert.setTitle("NOTIFIKASI");
            alert.setContentText("Data Password Belum Diisi");
            alert.showAndWait();
            txtPass.requestFocus();
            return false;
        }else{
            return true;
        }
    }
        
    @FXML
    private void registrasiData(MouseEvent event) throws IOException, SQLException {
        if(cekDataRegis() == false){
            return;
        }
        String selectQuery = "select * from tbakunpbo where username=? OR email=?";
        connect = Database.connectDB();

        prepare = connect.prepareStatement(selectQuery);
        prepare.setString(1,txtUsername.getText());
        prepare.setString(2, txtEmail.getText());
        result = prepare.executeQuery();
        
        if(result.next()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("NOTIFIKASI");
            alert.setContentText("Username atau Email Telah Digunakan");
            alert.showAndWait();
            txtEmail.requestFocus();
        }
        else{
            String insertQuery = "Insert into tbakunpbo(nama, password, username, email) values (?,?,?,?)";
            connect = Database.connectDB();
            prepare = connect.prepareStatement(insertQuery);
            prepare.setString(1, txtNama.getText());
            prepare.setString(2, txtPass.getText());
            prepare.setString(3, txtUsername.getText());
            prepare.setString(4, txtEmail.getText());
            prepare.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("NOTIFIKASI");
            alert.setContentText("Registrasi Akun Telah Berhasil");
            alert.showAndWait();
        }
    }

    @FXML
    private void backform(MouseEvent event) {
        loginform.setVisible(true);
        loginkanan.setVisible(true);
        welcomepage.setVisible(false);
    }


}
