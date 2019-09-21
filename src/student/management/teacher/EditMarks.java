package student.management.teacher;

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

class EditMarks extends JFrame
{

    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JPasswordField p;
    JButton b1,b2;
    JPanel empUp,background,head;
    String uname;

    EditMarks(String uname) throws SQLException, ClassNotFoundException {
        this.uname=uname;

        Image img = Common.getIcon();
        Image icon1 = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(icon1);

        head =JFrameSize.head();

        background = JFrameSize.panelCall();
        ImageIcon bg_img = Common.background();
        JLabel background=new JLabel("",bg_img,JLabel.CENTER);
        background.setBounds(0,0,900,600);

        empUp = JFrameSize.panelCall();
        empUp.setBounds(250,50,400,450);


        l = JFrameSize.headline("Student's Marks");
        l.setBounds(60,20,450,30);
        empUp.add(l);

        l1 = new JLabel("Roll no");
        l1.setBounds(50,80,300,30);
        empUp.add(l1);

        t1 = new JTextField(30);
        t1.setBounds(150,80,150,25);
        empUp.add(t1);
        t1.setEditable(false);

        l2 = new JLabel("Name");
        l2.setBounds(50,120,300,30);
        empUp.add(l2);


        t2 = new JTextField(30);
        t2.setBounds(150,120,150,25);
        empUp.add(t2);
        t2.setEditable(false);

        l3 = new JLabel("Maths");
        l3.setBounds(50,160,300,30);
        empUp.add(l3);

        t3 = new JTextField(30);
        t3.setBounds(150,160,150,25);
        empUp.add(t3);

        l4 = new JLabel("Science");
        l4.setBounds(50,200,300,30);
        empUp.add(l4);

        t4 = new JTextField(30);
        t4.setBounds(150,200,150,25);
        empUp.add(t4);

        l5 = new JLabel("English");
        l5.setBounds(50,240,300,30);
        empUp.add(l5);

        t5 = new JTextField(30);
        t5.setBounds(150,240,150,25);
        empUp.add(t5);

        l6 = new JLabel("Total");
        l6.setBounds(50,280,300,30);
        empUp.add(l6);

        t6 = new JTextField(30);
        t6.setBounds(150,280,150,25);
        empUp.add(t6);
        t6.setEditable(false);

        b1 = new JButton("Update");
        b1.setBounds(70,360,100,30);
        empUp.add(b1);
        b1.addActionListener(e -> {if(e.getSource()==b1){
            String roll= t1.getText(); String name= t2.getText();
            String maths= t3.getText(); String science= t4.getText();
            String english= t5.getText();
            int due1 = Integer.parseInt(t3.getText())+Integer.parseInt(t4.getText())+Integer.parseInt(t5.getText());
            if(due1>300){
                try {
                    throw new resultException(due1);
                } catch (resultException ex) {
                    ex.printStackTrace();
                }
            }
            else{t6.setText(String.valueOf(due1));
                String total = t6.getText();
                Instance.updatestu(roll,name,maths,science,english,total);}

        } });

        b2 = new JButton("Back");
        b2.setBounds(200,360,100,30);
        empUp.add(b2);
        b2.addActionListener(e->{new Teacher(); Common.close(this);});



        viewProcess(uname);
        background.add(empUp);
        add(head);
        add(background);
        Common.define(this);
    }

    private void viewProcess(String e) throws SQLException, ClassNotFoundException {

        try (Connection con = MyConnect.getInstance().getConnection())
        {
            String sql ="select * from marks where name='" + e + "'";
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
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new EditMarks("shree");
    }
}
