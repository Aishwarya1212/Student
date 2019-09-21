package Connect;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect
{
    private static MyConnect dbIsntance;
    private static Connection con ;

    String DRIVER="com.mysql.jdbc.Driver";
    String URL="jdbc:mysql://localhost:3306/student";
    String UNAME="root";
    String PASS="root";


    private MyConnect() {
        // private constructor //
    }

    public static MyConnect getInstance(){
        if(dbIsntance==null){
            dbIsntance= new MyConnect();
        }
        return dbIsntance;
    }

    public  Connection getConnection() throws ClassNotFoundException, SQLException
    {

        if (con == null|| con.isClosed()) {
            Class.forName(DRIVER);
             con = (Connection) DriverManager.getConnection(URL, UNAME, PASS);
        }
        return  con;
    }
}
