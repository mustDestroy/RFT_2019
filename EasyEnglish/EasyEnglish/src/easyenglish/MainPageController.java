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
import javafx.stage.Stage;

/**
 *
 * @author Dominik
 */
public class MainPageController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button btnTeacher;
    @FXML
    private Button btnStudent;

    @FXML
    private void btnTeacherAction(ActionEvent event) throws IOException, SQLException {

        //megnyitom a tanár login felületet
        Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("TeacherLoginFXML.fxml"));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

        //bezárom a fő felületet
        Node closeLogin = (Node) event.getSource();
        Stage stage = (Stage) closeLogin.getScene().getWindow();
        stage.close();
        stage.setFullScreen(true);
        
        //System.out.println("You clicked me!");

    }

    @FXML
    private void btnStudentAction(ActionEvent event) throws IOException {
        //megnyitom a diák belépési oldalt
         Stage mainStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StudentLoginFXML.fxml"));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        
        //bezárom a fő felületet
         Node closeLogin = (Node) event.getSource();
        Stage stage = (Stage) closeLogin.getScene().getWindow();
        stage.close();
        stage.setFullScreen(true);
    }

    public boolean Authentication(int id, String pw) throws SQLException {

        Singleton conn = Singleton.getInstance();       //connection példány
        CallableStatement cs;
        cs = conn.getConnection().prepareCall("{call authForStudents(?,?)}"); // itt hívom meg a tárolt eljárást
        cs.setInt("id", 100);
        cs.setString("pass", "malac");

        boolean rs = cs.execute();

        if (rs == true) { // ekkor dob át másik oldalra
            System.out.println("Működik");
        } else {
            System.out.println("nem működik");
        }

        return true;
    }

    public void TeacherRegistration(int id, String pw, String name) throws SQLException {
        Singleton conn = Singleton.getInstance();
        CallableStatement cs;
        cs = conn.getConnection().prepareCall("{call AddStudent(?,?,?)}");
        cs.setInt("id", id);
        cs.setString("pass", pw);
        cs.setString("name", name);
        cs.execute();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
