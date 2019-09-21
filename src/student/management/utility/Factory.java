package student.management.utility;

import student.management.Admin.Admin;
import student.management.accountant.Account;
import student.management.library.Library;
import student.management.studentLogin.StuLogin;
import student.management.teacher.Teacher;
import student.management.utility.LoginInterface;

import java.sql.SQLException;

public class Factory {
  public static   LoginInterface getInstance(String name) throws SQLException, ClassNotFoundException {

        if(name.equalsIgnoreCase("admin"))
    {
        return  new Admin();
    }else if(name.equalsIgnoreCase("accountant"))
    {
       return new Account();
    }else if(name.equalsIgnoreCase("teacher"))
        {
            return new Teacher();
        }
        else if(name.equalsIgnoreCase("library"))
        {
            return new Library();
        }
        else{
            return null;
        }
    }
}
