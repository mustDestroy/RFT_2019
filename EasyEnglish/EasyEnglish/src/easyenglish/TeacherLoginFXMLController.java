/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyenglish;

import easyenglish.Modell.Singleton;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class TeacherLoginFXMLController implements Initializable {

    @FXML
    private TextField txtFieldTeacherId;
    @FXML
    private PasswordField txtFieldTeacherPw;
    @FXML
    private Label lblErrorMessage;

    public void btnTeacherLoginAction(ActionEvent event) throws SQLException, IOException {

        if (txtFieldTeacherId.getText().equals("")
                || txtFieldTeacherPw.getText().equals("")) {
            lblErrorMessage.setText("Egyik mező sem lehet üres");
        } 
         else if(!txtFieldTeacherId.getText().trim().matches("[0-9]+")){
                lblErrorMessage.setText("Az azonosító csak számot tartalmazhat");
        }
        else {

            String id = txtFieldTeacherId.getText().trim(); //kiszedem a space-kat
            int numberId = Integer.valueOf(id);

            if (Authentication(numberId, txtFieldTeacherPw.getText().trim())) {
                Stage resultStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("GetResultsFXML.fxml"));
                Scene scene = new Scene(root);
                resultStage.setScene(scene);
                resultStage.show();

                Node closeLogin = (Node) event.getSource();
                Stage stage = (Stage) closeLogin.getScene().getWindow();
                stage.close();
                stage.setFullScreen(true);

            } else {
                lblErrorMessage.setText("Azonosító vagy jelszó hibás");
            }
        }
    }

    public void btnTeacherToRegAction(ActionEvent event) throws IOException, SQLException {

        Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("TeacherRegistrationFXML.fxml"));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

        //előző ablak becsukása
        Node closeLogin = (Node) event.getSource();
        Stage stage = (Stage) closeLogin.getScene().getWindow();
        stage.close();
        stage.setFullScreen(true);
    }

    //az autentikációhoz tanári azon szám és saját jelszó tartozik
    public boolean Authentication(int id, String pw) throws SQLException {

        Singleton conn = Singleton.getInstance();       //connection példány
        CallableStatement cs;
        cs = conn.getConnection().prepareCall("{call authForTeachers(?,?)}"); // itt hívom meg a tárolt eljárást
        cs.setInt("id", id);
        cs.setString("pass", pw);
        ResultSet rs = cs.executeQuery(); //a lekérdezés eredménye
        if (rs.next()) { // ekkor dob át másik oldalra, ha ez igaz-t ad vissza
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
