
package Bankmanagementsystem;
import java.sql.*;

public class Conn {
    
    Connection c ;
    Statement st;
    
    public Conn(){
        try{
          //  Class.forName(com.mysql.cj.jdbc.Driver);
            c= DriverManager.getConnection("jdbc:mysql://localhost:3306/bankManagementSystem","root","root");
            st=c.createStatement();
            
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
