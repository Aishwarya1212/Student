package student.management.library;

import student.management.utility.Common;
import student.management.utility.Instance;
import student.management.utility.JFrameSize;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddBook extends JFrame
{

    JLabel l1,l2,l3,l4,l5,l6,l,l7;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2;
    JPanel accUp,background,head;

    AddBook()
    {
        Image img = Common.getIcon();
        Image icon1 = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(icon1);

        head = JFrameSize.head();
        background = JFrameSize.panelCall();
        ImageIcon bg_img = Common.background();
        JLabel background=new JLabel("",bg_img,JLabel.CENTER);
        background.setBounds(0,0,900,600);

        accUp = JFrameSize.panelCall();
        accUp.setBounds(250,100,400,350);


        l = JFrameSize.headline("Add New Book");
        l.setBounds(120,20,450,30);
        accUp.add(l);

        l1 = new JLabel("Book no");
        l1.setBounds(50,80,300,30);
        accUp.add(l1);

        t1 = new JTextField(30);
        t1.setBounds(150,80,150,25);
        accUp.add(t1);

        l2 = new JLabel("Book Name");
        l2.setBounds(50,120,300,30);
        accUp.add(l2);

        t2 = new JTextField(30);
        t2.setBounds(150,120,150,25);
        accUp.add(t2);


        l3 = new JLabel("Author");
        l3.setBounds(50,160,300,30);
        accUp.add(l3);

        t3 = new JTextField(30);
        t3.setBounds(150,160,150,25);
        accUp.add(t3);

        l4 = new JLabel("Rack No");
        l4.setBounds(50,200,300,30);
        accUp.add(l4);

        t4 = new JTextField(30);
        t4.setBounds(150,200,150,25);
        accUp.add(t4);

        b1 = new JButton("ADD");
        b1.setBounds(70,280,100,30);
        accUp.add(b1);
        b1.addActionListener(e -> {if(e.getSource()==b1){
            String bid = t1.getText(); String bname= t2.getText();
            String author= t3.getText();
            String rack = t4.getText();
                Instance.upLib(bid,bname,author,rack); } });

        b2 = new JButton("BACK");
        b2.setBounds(220,280,100,30);
        accUp.add(b2);
        b2.addActionListener(actionEvent -> {new Library();Common.close(this);});

        background.add(accUp);
        add(head);
        add(background);
        Common.define(this);

    }

    public static void main(String[] args) {
        new AddBook();
    }
}
