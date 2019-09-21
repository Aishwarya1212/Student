package student.management.studentLogin;

import Connect.MyConnect;
import student.management.utility.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentLogin extends JFrame
{

    JLabel l1,l2,l;
    JTextField t1;
    JPasswordField p1;
    JButton b1;
    JPanel login, background, head;

    StudentLogin()
    {
        background =  JFrameSize.panelCall();
        head = JFrameSize.head();

        //image icon
        Image img = Common.getIcon();
        Image icon1 = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(icon1);

        //Background

        ImageIcon bg_img = Common.background();
        JLabel background=new JLabel("",bg_img,JLabel.CENTER);
        background.setBounds(0,0,900,600);

        login = JFrameSize.panelCall();
        login.setBounds(200,100,500,300);


        l = JFrameSize.headline("STUDENT LOGIN");
        l.setBounds(150,20,300,30);
        login.add(l);

        l1=JFrameSize.title("Username");
        l1.setBounds(100,80,100,30);
        login.add(l1);

        t1 = JFrameSize.textField();
        login.add(t1);

        l2=JFrameSize.title("Password");
        l2.setBounds(100,150,100,30);
        login.add(l2);

        p1 = new JPasswordField();
        p1.setBounds(250,150,150,30);
        login.add(p1);

        b1 = new JButton("Login");
        b1.setBounds(200,210,100,40);
        login.add(b1);

        b1.addActionListener(e ->{String uname = t1.getText();
            String pass = p1.getText();
            if (uname.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, " Please fill username and password", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                student(uname, pass);
            }});

        background.add(login);
        add(head);
        add(background);
        Common.define(this);
    }

    private void student(String s, String p) {
        try (Connection con = MyConnect.getInstance().getConnection()) {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("select * from emp where username='" + s + "' and password='" + p + "'");

            if (r.next()) {

                if (s.equals(r.getString(7)) && p.equals(r.getString(8)))
                {
                    JOptionPane.showMessageDialog(null, "Login Successful", "Success", JOptionPane.PLAIN_MESSAGE);
                    new StuLogin(r.getString(7));
                    Common.close(this);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentLogin();
    }
}
