/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Johanna
 */
public class DBManager {
    private String name;//name of the database
    private String user;//user of the account that connects to the db
    private String password;//password of the account that connects to the db
    private Connection connect = null;//connection with the db
    public Statement statement = null;//statement of a connection
    private ResultSet resultSet = null;//result set of a query
    
    
    public DBManager(){
      this.name="yw385";
      this.user="root";
      this.password="mysql";
    }
    public void openConnection() throws Exception{
       Class.forName("com.mysql.jdbc.Driver");
       this.connect =DriverManager.getConnection("jdbc:mysql://localhost/"+this.name,"root","mysql");
       this.statement = connect.createStatement();
    }
  
    public void closeConnection() {
        try {
           if (this.resultSet != null) {
               this.resultSet.close();
           }if (this.statement != null) {
               this.statement.close();
           }if (this.connect != null) {
               this.connect.close();
           }
        }catch (Exception e) {}
    }
}
