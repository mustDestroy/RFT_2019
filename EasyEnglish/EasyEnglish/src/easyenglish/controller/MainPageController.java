/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyenglish.controller;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 *
 * @author Dominik
 */
public class MainPageController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        //boolean r = Authentication(1, "1");
    }

    public boolean Authentication(int id, String pw) throws SQLException {
        int i_d = 103450;
        String p_w = "malac";

        Singleton conn = Singleton.getInstance();       //connection példány
        CallableStatement cs;
        cs = conn.getConnection().prepareCall("{call authentication(?,?)}"); // itt hívom meg a tárolt eljárást
        cs.setInt("id", 100);
        cs.setString("pass", p_w);

        boolean rs = cs.execute();

        if (rs == true){ // ekkor dob át másik oldalra
               // System.out.println("Működik");
        } else {
            System.out.println("nem működik");
        }

        return true;
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
