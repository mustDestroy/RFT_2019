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
public class StudentRegistrationFXMLController implements Initializable {

    @FXML
    private Button btnRegistarion;
    @FXML
    private TextField txtRegStudentId;
    @FXML
    private TextField txtRegStudentName;
    @FXML
    private PasswordField txtRegStudentPw;
    @FXML
    private Label lblErrorMessage;

    public void btnRegistrationAction(ActionEvent event) throws SQLException {

        if (txtRegStudentId.getText().equals("")
                || txtRegStudentName.getText().equals("")
                || txtRegStudentPw.getText().equals("")) {
            lblErrorMessage.setText("Egyik mező sem lehet üres");
        } else {
            StudentRegistration(Integer.valueOf(txtRegStudentId.getText().trim()),
                    txtRegStudentPw.getText().trim(),
                    txtRegStudentName.getText().trim());
            lblErrorMessage.setText("Regisztráció sikeres");
        }
    }

    public void btnBackAction(ActionEvent event) throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StudentLoginFXML.fxml"));
        Scene scene = new Scene(root);
        loginStage.setScene(scene);
        loginStage.show();

        Node closeLogin = (Node) event.getSource();
        Stage stage = (Stage) closeLogin.getScene().getWindow();
        stage.close();
        stage.setFullScreen(true);
    }

    public void StudentRegistration(int id, String pw, String name) throws SQLException {
        Singleton conn = Singleton.getInstance();
        CallableStatement cs;
        cs = conn.getConnection().prepareCall("{call AddStudent(?,?,?)}");
        cs.setInt("id", id);
        cs.setString("pass", pw);
        cs.setString("name", name);
        ResultSet rs = cs.executeQuery();
        //return rs.next();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
