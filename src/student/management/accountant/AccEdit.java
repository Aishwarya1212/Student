package student.management.accountant;

import Connect.MyConnect;
import student.management.utility.Common;
import student.management.utility.Instance;
import student.management.utility.JFrameSize;
import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class AccEdit extends JFrame
{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JPasswordField p;
    JButton b1,b2;
    JPanel accUp,background,head;

    String uname;

    public AccEdit(String data) throws SQLException, ClassNotFoundException {

        uname = data;
        head = JFrameSize.head();
        Image img = Common.getIcon();
        Image icon1 = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(icon1);

        background = JFrameSize.panelCall();
        ImageIcon bg_img = Common.background();
        JLabel background=new JLabel("",bg_img,JLabel.CENTER);
        background.setBounds(0,0,900,600);

        accUp = JFrameSize.panelCall();
        accUp.setBounds(250,70,400,450);


        l = JFrameSize.headline("Student's Update");
        l.setBounds(60,20,450,30);
        accUp.add(l);

        l1 = new JLabel("Roll no");
        l1.setBounds(50,80,300,30);
        accUp.add(l1);

        t1 = new JTextField(30);
        t1.setBounds(150,80,150,25);
        accUp.add(t1);
        t1.setEditable(false);

        l2 = new JLabel("Name");
        l2.setBounds(50,120,300,30);
        accUp.add(l2);

        t2 = new JTextField(30);
        t2.setBounds(150,120,150,25);
        accUp.add(t2);
        t2.setEditable(false);

        l3 = new JLabel("email");
        l3.setBounds(50,160,300,30);
        accUp.add(l3);

        t3 = new JTextField(30);
        t3.setBounds(150,160,150,25);
        accUp.add(t3);
        t3.setEditable(false);

        l4 = new JLabel("Total fee");
        l4.setBounds(50,200,300,30);
        accUp.add(l4);

        t4 = new JTextField(30);
        t4.setBounds(150,200,150,25);
        accUp.add(t4);

        l5 = new JLabel("Paid fee");
        l5.setBounds(50,240,300,30);
        accUp.add(l5);

        t5 = new JTextField(30);
        t5.setBounds(150,240,150,25);
        accUp.add(t5);

        l6 = new JLabel("Due Fee");
        l6.setBounds(50,280,300,30);
        accUp.add(l6);

        t6 = new JTextField(30);
        t6.setBounds(150,280,150,25);
        accUp.add(t6);
        t6.setEditable(false);

        l7 = new JLabel("Username");
        l7.setBounds(50,320,300,30);
        accUp.add(l7);

        t7 = new JTextField(30);
        t7.setBounds(150,320,150,25);
        accUp.add(t7);
        t7.setEditable(false);

        l7 = new JLabel("Password");
        l7.setBounds(50,360,300,30);
        accUp.add(l7);

        p = new JPasswordField(10);
        p.setBounds(150,360,150,25);
        accUp.add(p);
        p.setEditable(false);

        b1 = new JButton("update");
        b1.setBounds(70,400,100,30);
        accUp.add(b1);

        b1.addActionListener(e -> {if(e.getSource()==b1){
            String id = t1.getText(); String name= t2.getText();
            String email = t3.getText(); String total= t4.getText();
            String paid= t5.getText();int due1 = Integer.parseInt(t4.getText())-Integer.parseInt(t5.getText());
            t6.setText(String.valueOf(due1));
            String due = t6.getText();
            String user = t7.getText(); String pass= p.getText();
            boolean b= Common.validEmp(id,name,email,total,paid,due,user,pass);
            if(b==true)
            {
                Instance.update(id,name,email,total,paid,due,user,pass);
            }

        } });

        b2 = new JButton("Back");
        b2.setBounds(220,400,100,30);
        accUp.add(b2);
        b2.addActionListener(actionEvent -> {new Account();Common.close(this);});

        viewProcess(uname);
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


   /* public static void main(String[] args) throws SQLException, ClassNotFoundException {
       new AccEdit("ram");
    }*/
}
