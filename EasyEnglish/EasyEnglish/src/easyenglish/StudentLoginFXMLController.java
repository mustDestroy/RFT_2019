/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyenglish;

import easyenglish.Modell.Singleton;
import java.awt.Desktop.Action;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class StudentLoginFXMLController implements Initializable {

    // diák bejelentkezéshez kell a diákAzon, és jelszó
    @FXML
    private Button btnStudentLogin;
    @FXML
    private Button btnGoToRegistration;
    @FXML
    private TextField txtFieldStudentId;
    @FXML
    private PasswordField txtFieldStudentPw;
    @FXML
    private Label lblErrorMessage;

    public void btnStudentLoginAction(ActionEvent event) throws IOException, SQLException {

        if (txtFieldStudentId.getText().equals("")
                || txtFieldStudentPw.getText().equals("")) {
            lblErrorMessage.setText("Egyik mező sem lehet üres");
        } else {

            String id = txtFieldStudentId.getText().trim(); //kiszedem a space-kat
            int numberId = Integer.valueOf(id);

            if (Authentication(numberId, txtFieldStudentPw.getText().trim())) {
                Stage examStartStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("ExerciseStartFXML.fxml"));
                Scene scene = new Scene(root);
                examStartStage.setScene(scene);
                examStartStage.show();

                Node closeLogin = (Node) event.getSource();
                Stage stage = (Stage) closeLogin.getScene().getWindow();
                stage.close();
                stage.setFullScreen(true);

            } else {
                lblErrorMessage.setText("Azonosító vagy jelszó hibás");
            }
        }

    }

    public void btnGoToRegistrationAction(ActionEvent event) throws IOException {
        Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StudentRegistrationFXML.fxml"));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

        Node closeLogin = (Node) event.getSource();
        Stage stage = (Stage) closeLogin.getScene().getWindow();
        stage.close();
        stage.setFullScreen(true);
    }

    public boolean Authentication(int id, String pw) throws SQLException {

        Singleton conn = Singleton.getInstance();       //connection példány
        CallableStatement cs;
        cs = conn.getConnection().prepareCall("{call authForStudents(?,?)}"); // itt hívom meg a tárolt eljárást
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

    }

}
