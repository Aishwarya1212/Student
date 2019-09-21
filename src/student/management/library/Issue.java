package student.management.library;

import Connect.MyConnect;
import student.management.utility.Common;
import student.management.utility.Instance;
import student.management.utility.JFrameSize;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Issue extends JFrame
{

    JLabel l1,l2,l3,l4,l5,l6,l,l7;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2;
    JPanel accUp,background,head;

    String uname;

    Issue(String uname) throws SQLException, ClassNotFoundException {
        this.uname = uname;

        Image img = Common.getIcon();
        Image icon1 = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(icon1);

        head = JFrameSize.head();

        background = JFrameSize.panelCall();
        ImageIcon bg_img = Common.background();
        JLabel background=new JLabel("",bg_img,JLabel.CENTER);
        background.setBounds(0,0,900,600);

        accUp = JFrameSize.panelCall();
        accUp.setBounds(250,50,400,400);


        l = JFrameSize.headline("Book Issue");
        l.setBounds(140,20,450,30);
        accUp.add(l);

        l1 = new JLabel("Book no");
        l1.setBounds(50,80,300,30);
        accUp.add(l1);

        t1 = new JTextField(30);
        t1.setBounds(150,80,150,25);
        accUp.add(t1);
        t1.setEditable(false);

        l2 = new JLabel("Book Name");
        l2.setBounds(50,120,300,30);
        accUp.add(l2);

        t2 = new JTextField(30);
        t2.setBounds(150,120,150,25);
        accUp.add(t2);
        t2.setEditable(false);

        l3 = new JLabel("Author");
        l3.setBounds(50,160,300,30);
        accUp.add(l3);

        t3 = new JTextField(30);
        t3.setBounds(150,160,150,25);
        accUp.add(t3);
        t3.setEditable(false);

        l4 = new JLabel("Rack No");
        l4.setBounds(50,200,300,30);
        accUp.add(l4);

        t4 = new JTextField(30);
        t4.setBounds(150,200,150,25);
        accUp.add(t4);
        t3.setEditable(false);

        l5 = new JLabel("status");
        l5.setBounds(50,240,300,30);
        accUp.add(l5);

        t5 = new JTextField(30);
        t5.setBounds(150,240,150,25);
        accUp.add(t5);

        l6 = new JLabel("rollno");
        l6.setBounds(50,280,300,30);
        accUp.add(l6);

        t6 = new JTextField(30);
        t6.setBounds(150,280,150,25);
        accUp.add(t6);

        l7 = new JLabel("name");
        l7.setBounds(50,320,300,30);
        accUp.add(l7);

        t7 = new JTextField(30);
        t7.setBounds(150,320,150,25);
        accUp.add(t7);

        b1 = new JButton("ISSUE");
        b1.setBounds(70,350,100,30);
        accUp.add(b1);
        b1.addActionListener(e -> {if(e.getSource()==b1){
            String bid = t1.getText(); String bname= t2.getText();
            String status= t5.getText();
            String roll = t6.getText(); String name = t7.getText();
            try {
             Instance.checkStatus(bid,bname,status,roll,name);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } });

        b2 = new JButton("BACK");
        b2.setBounds(220,350,100,30);
        accUp.add(b2);
        b2.addActionListener(actionEvent -> {new Library();Common.close(this);});

        viewProcess(uname);
        background.add(accUp);
        add(head);
        add(background);
        Common.define(this);

    }

    private void viewProcess(String e) {

        try (Connection con = MyConnect.getInstance().getConnection())
        {
            String sql ="select * from book where bname='" + e + "'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                t1.setText(String.valueOf(rs.getInt(1)));
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Issue("wings of fire");
    }
}
