package student.management.studentLogin;

import Connect.MyConnect;
import student.management.Login;
import student.management.utility.Common;
import student.management.utility.JFrameSize;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StuLogin extends JFrame
{

    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JPasswordField p;
    JButton b1,b2;
    JPanel accUp,background,head;

    String name;
    public StuLogin(String name) throws SQLException, ClassNotFoundException {
        this.name = name;

        Image img = Common.getIcon();
        Image icon1 = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(icon1);

        head = JFrameSize.head();

        background = JFrameSize.panelCall();
        ImageIcon bg_img = Common.background();
        JLabel background=new JLabel("",bg_img,JLabel.CENTER);
        background.setBounds(0,0,900,600);

        accUp = JFrameSize.panelCall();
        accUp.setBounds(250,70,400,480);


        l = JFrameSize.headline(name +"'s profile");
        l.setBounds(130,20,450,30);
        accUp.add(l);

        b1 = new JButton("Logout");
        b1.setBounds(300,50,80,25);
        accUp.add(b1);
        b1.addActionListener(e -> {
            new StudentLogin();
            Common.close(this);});

        l1 = new JLabel("Roll no");
        l1.setBounds(50,90,300,30);
        accUp.add(l1);

        t1 = new JTextField(30);
        t1.setBounds(150,90,150,25);
        accUp.add(t1);
        t1.setEditable(false);

        l2 = new JLabel("Name");
        l2.setBounds(50,130,300,30);
        accUp.add(l2);

        t2 = new JTextField(30);
        t2.setBounds(150,130,150,25);
        accUp.add(t2);
        t2.setEditable(false);

        l3 = new JLabel("email");
        l3.setBounds(50,170,300,30);
        accUp.add(l3);

        t3 = new JTextField(30);
        t3.setBounds(150,170,150,25);
        accUp.add(t3);
        t3.setEditable(false);

        l4 = new JLabel("Total fee");
        l4.setBounds(50,210,300,30);
        accUp.add(l4);

        t4 = new JTextField(30);
        t4.setBounds(150,210,150,25);
        accUp.add(t4);
        t4.setEditable(false);

        l5 = new JLabel("Paid fee");
        l5.setBounds(50,250,300,30);
        accUp.add(l5);

        t5 = new JTextField(30);
        t5.setBounds(150,250,150,25);
        accUp.add(t5);
        t5.setEditable(false);

        l6 = new JLabel("Due Fee");
        l6.setBounds(50,290,300,30);
        accUp.add(l6);

        t6 = new JTextField(30);
        t6.setBounds(150,290,150,25);
        accUp.add(t6);
        t6.setEditable(false);

        l7 = new JLabel("Username");
        l7.setBounds(50,330,300,30);
        accUp.add(l7);

        t7 = new JTextField(30);
        t7.setBounds(150,330,150,25);
        accUp.add(t7);
        t7.setEditable(false);

        l7 = new JLabel("Password");
        l7.setBounds(50,370,300,30);
        accUp.add(l7);

        p = new JPasswordField(10);
        p.setBounds(150,370,150,25);
        accUp.add(p);
        p.setEditable(false);

        b2 = new JButton("VIEW RESULT");
        b2.setBounds(120,420,150,30);
        accUp.add(b2);
        b2.addActionListener(e-> {
            try {
                new ViewResult(name);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            Common.close(this);});

         viewProcess(name);
        background.add(accUp);
        add(head);
        add(background);
        Common.define(this);

    }

    private void viewProcess(String e) throws SQLException, ClassNotFoundException {

        try (Connection con = MyConnect.getInstance().getConnection())
        {
            String sql ="select * from emp where name='" + e + "'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                t1.setText(String.valueOf(rs.getInt(1)));
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
                t5.setText(rs.getString(5));
                t6.setText(rs.getString(6));
                t7.setText(rs.getString(7));
                p.setText(rs.getString(8));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new StuLogin("ram");
    }
}
