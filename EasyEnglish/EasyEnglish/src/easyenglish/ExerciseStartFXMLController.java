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
import java.util.ArrayList;
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
 * FXML Controller class
 *
 * @author Dominik
 */
public class ExerciseStartFXMLController implements Initializable {

    //listák deklarálása a mondatoknak
    //és a hozzá tartozó válaszoknak
    public ArrayList<String> arrayOfSentences = new ArrayList<String>();
    public ArrayList<String> arrayOfAnswr1 = new ArrayList<String>();
    public ArrayList<String> arrayOfAnswr2 = new ArrayList<String>();
    public ArrayList<String> arrayOfAnswr3 = new ArrayList<String>();
    public ArrayList<String> arrayOfAnswr4 = new ArrayList<String>();
    public ArrayList<Integer> arrayNumOfRightAnswers = new ArrayList<Integer>();
    public ArrayList<Integer> arrayOfChoosenNumbers = new ArrayList<Integer>();
    public int numOfChoosenAnswr = 0;       // kiválasztott válasz sorszáma a 4-ből
    //0 az alapért. ezt hibásnak válasznak számolja
    public int numberOfSentences = 0;
    int i = 0;

    @FXML
    private Button btnStartTest;
    @FXML
    private Button btnAnswr1;
    @FXML
    private Button btnAnswr2;
    @FXML
    private Button btnAnswr3;
    @FXML
    private Button btnAnswr4;
    @FXML
    private Label lblMessage;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnFinish;
    @FXML
    private Label lblResult;

    //Eseménykezelő metódusok
    public void btnStartTestAction(ActionEvent event) throws IOException, SQLException {
        GetSentencesFromDb();           // feladatok betöltése a DB-ből
        btnStartTest.setVisible(false); //grafikai elemek láthatóságának beállítása
        btnAnswr1.setVisible(true);
        btnAnswr1.setVisible(true);
        btnAnswr2.setVisible(true);
        btnAnswr3.setVisible(true);
        btnAnswr4.setVisible(true);
        btnNext.setVisible(true);

        lblMessage.setText(arrayOfSentences.get(0));
        btnAnswr1.setText(arrayOfAnswr1.get(0));
        btnAnswr2.setText(arrayOfAnswr2.get(0));
        btnAnswr3.setText(arrayOfAnswr3.get(0));
        btnAnswr4.setText(arrayOfAnswr4.get(0));

    }

    public void btnAnswr1Action(ActionEvent event) {
        numOfChoosenAnswr = 1;
    }

    public void btnAnswr2Action(ActionEvent event) {
        numOfChoosenAnswr = 2;
    }

    public void btnAnswr3Action(ActionEvent event) {
        numOfChoosenAnswr = 3;
    }

    public void btnAnswr4Action(ActionEvent event) {
        numOfChoosenAnswr = 4;
    }

    public void btnNextAction(ActionEvent event) {

        i++;
        if (i >= arrayOfSentences.size()) {
            btnAnswr1.setVisible(false);
            btnAnswr2.setVisible(false);
            btnAnswr3.setVisible(false);
            btnAnswr4.setVisible(false);
            btnNext.setVisible(false);
            btnFinish.setVisible(true);
            lblMessage.setText("A teszt végetért!\n Kattints a KÉSZ gombra.");

        } else {
            lblMessage.setText(arrayOfSentences.get(i));
            btnAnswr1.setText(arrayOfAnswr1.get(i));
            btnAnswr2.setText(arrayOfAnswr2.get(i));
            btnAnswr3.setText(arrayOfAnswr3.get(i));
            btnAnswr4.setText(arrayOfAnswr4.get(i));
        }
        arrayOfChoosenNumbers.add(numOfChoosenAnswr);
    }

    public void btnFinishAction(ActionEvent event) {
        
        int count = 0;
        for (int j = 0; j < arrayNumOfRightAnswers.size(); j++) {
            System.out.println(arrayOfChoosenNumbers.get(j) + " igazi: " + arrayNumOfRightAnswers.get(j));
            if (arrayNumOfRightAnswers.get(j).compareTo(arrayOfChoosenNumbers.get(j)) == 0) {
                count++;
            }
        }
        double resultOfTest = (double) (((double) count / (double) arrayNumOfRightAnswers.size()) * (double) 100);
        int result = (int) resultOfTest;
        System.out.println(result);
        lblResult.setText("Az elért eredmény: " +result+ " %");
        lblResult.setVisible(true);
//        
//        Node closeLogin = (Node) event.getSource();
//        Stage stage = (Stage) closeLogin.getScene().getWindow();
//        stage.close();
//        stage.setFullScreen(true);
    }

    //Metódusok
    public void GetSentencesFromDb() throws SQLException {
        Singleton conn = Singleton.getInstance();
        CallableStatement cs;
        cs = conn.getConnection().prepareCall("{call SelectSentences()}"); // itt hívom meg a tárolt eljárást

        ResultSet rs = cs.executeQuery(); //a lekérdezés eredménye
        while (rs.next()) {
            numberOfSentences++;

            arrayOfSentences.add(rs.getString("sentence"));
            arrayOfAnswr1.add(rs.getString("answr1"));
            arrayOfAnswr2.add(rs.getString("answr2"));
            arrayOfAnswr3.add(rs.getString("answr3"));
            arrayOfAnswr4.add(rs.getString("answr4"));
            arrayNumOfRightAnswers.add(rs.getInt("num_of_right_answr"));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAnswr1.setVisible(false);
        btnAnswr2.setVisible(false);
        btnAnswr3.setVisible(false);
        btnAnswr4.setVisible(false);
        btnNext.setVisible(false);
        btnFinish.setVisible(false);
        lblResult.setVisible(false);
    }

}
