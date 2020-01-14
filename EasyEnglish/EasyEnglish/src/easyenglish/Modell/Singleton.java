/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyenglish.Modell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dominik
 */
public class Singleton {
    
    
    private static Singleton firstInstance = null;
    private Connection dbConnection;
    //private String url;

    public static Singleton getInstance() {
        if (firstInstance == null) {
            firstInstance = new Singleton();
        }
        return firstInstance;
    }

    private Singleton() {
        String url = "jdbc:mysql://localhost:3306/registration";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Success");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Connection was failed");
        }
        try {
            dbConnection = DriverManager.getConnection(url, "root", "Insertinto48");
            System.out.println("Database connected");
        } catch (SQLException se) {
            System.out.println("No database " + se);
        }

    }

    public Connection getConnection() {
        return firstInstance.dbConnection;
    }
 
    
}
