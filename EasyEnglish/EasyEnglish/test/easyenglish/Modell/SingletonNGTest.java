/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyenglish.Modell;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.testng.Assert.*;
import org.testng.annotations.Test;


/**
 *
 * @author Dominik
 */
public class SingletonNGTest {
      public static Singleton firstInstance=null;
      private java.sql.Connection dbConnection=null;
      
    public SingletonNGTest() {
          }

    
    @Test
    public void testGetInstance() throws SQLException, ClassNotFoundException {
        firstInstance=Singleton.getInstance();
        assertNotNull(firstInstance);

    }

    
    @Test
    public void testGetConnection() {
        dbConnection=Singleton.getInstance().getConnection();
         assertNotNull(dbConnection);
    }
    
}
