package student.management.utility;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    public static void define(JFrame f){
        f.setTitle("STUDENT MANAGEMENT SYSTEM");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setSize(900,600);
        f.setLayout(null);
        f.setIconImage(getIcon());
        f.setVisible(true);
        f.setResizable(false);
    }

    public static Image getIcon(){
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Student\\logo.jfif");
        return icon;
    }

    public static ImageIcon background() {
        ImageIcon bg_img = new ImageIcon("D:\\Student\\photo.jfif");
        Image i=bg_img.getImage();
        Image icon=i.getScaledInstance(900,600,Image.SCALE_SMOOTH);
        bg_img=new ImageIcon(icon);
        Image img = Common.getIcon();
        return bg_img;
    }

    public static void close(JFrame obj){
        WindowEvent we=new WindowEvent(obj,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
    }


    public static boolean validEmp(String id, String name, String email, String total, String paid, String due, String user, String pass) {
      boolean b = false;

        id = id.replace(" ", ""); // Remove spaces, sometimes people seperate different
        boolean idvalid =id.matches("[0-9]{1,10}");

        String onlychar = "^[a-zA-Z]*$";
        Pattern pattern1 = Pattern.compile(onlychar);
        Matcher matcher1 = pattern1.matcher(name);
        Boolean nvaild= matcher1.matches();

        String mail = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(mail);
        Matcher matcher = pattern.matcher(email);
        Boolean evaild= matcher.matches();


        boolean fvalid =total.matches("[0-9]{1,10}");

        boolean pvalid =paid.matches("[0-9]{1,10}");

        boolean dvalid =due.matches("[0-9]{1,10}");


        if(id.isEmpty()||name.isEmpty()||email.isEmpty()||total.isEmpty()||paid.isEmpty()||due.isEmpty()||user.isEmpty()||pass.isEmpty())
        {
            JOptionPane.showMessageDialog(null, " Please Enter in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }

        else if(idvalid==false)
        {
            JOptionPane.showMessageDialog(null, " Enter ID in digits/Numbers", "Error", JOptionPane.ERROR_MESSAGE);
        }

        else if (nvaild==false)
        {
            JOptionPane.showMessageDialog(null, " Enter Only Character String in Name", "Error", JOptionPane.ERROR_MESSAGE);
        }

        else if(evaild==false)
        {
            JOptionPane.showMessageDialog(null, " Enter Vaild Email ID", "Error", JOptionPane.ERROR_MESSAGE);
        }

        else if(fvalid==false||pvalid==false||dvalid==false)
        {
            JOptionPane.showMessageDialog(null, " Enter Fee in digits/Numbers", "Error", JOptionPane.ERROR_MESSAGE);

        }
        else{
            b = true;
        }

      return b;
    }

}
