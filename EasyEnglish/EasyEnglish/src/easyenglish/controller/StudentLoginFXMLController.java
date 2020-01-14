/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyenglish.controller;

import easyenglish.Modell.Singleton;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Dominik
 */
public class StudentLoginFXMLController implements Initializable {

    // diák bejelentkezéshez kell a diákAzon, és jelszó
    public int id;
    public String pw;


    public boolean Authentication(int id, String pw) throws SQLException {

        Singleton conn = Singleton.getInstance();       //connection példány
        CallableStatement cs;
        cs = conn.getConnection().prepareCall("{call authentication(?,?)}"); // itt hívom meg a tárolt eljárást
        cs.setInt("id", id);
        cs.setString("pass", pw);
        boolean rs = cs.execute(); //a lekérdezés eredménye

        if (rs) { // ekkor dob át másik oldalra
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

}
