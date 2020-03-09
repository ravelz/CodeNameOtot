package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.cj.jdbc.Driver;
import java.sql.SQLException;
public class koneksi{
    
    public static Connection konek;
    public static Connection getConnection () throws SQLException{
        if(konek == null){
            try{
                new Driver();
                konek = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/1718157_raymasterio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT", "root", "");
            }catch(SQLException e){
                System.out.println(e);
            }        
        }
        return konek;
    }
} 