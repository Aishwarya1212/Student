package student.management.teacher;

import Connect.MyConnect;
import student.management.Login;
import student.management.utility.Common;
import student.management.utility.JFrameSize;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class View extends JFrame
{
    static JTable table;
    JLabel l1,l2;
    JButton b1,b2,b3;
    JPanel teach,background,head;
    String Data;
    JTextField t1;

    View()
    {
        teach = JFrameSize.panelCall();
        teach.setBounds(100,100,700,400);

        head = JFrameSize.head();

        //logo
        Image img = Common.getIcon();
        Image icon1 = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(icon1);

        background = JFrameSize.panelCall();
        ImageIcon bg_img = Common.background();
        JLabel background=new JLabel("",bg_img,JLabel.CENTER);
        background.setBounds(0,0,900,600);

        l1 = JFrameSize.headline("View Mark List");
        l1.setBounds(230,30,400,30);
        teach.add(l1);

        b1 = new JButton("Logout");
        b1.setBounds(550,70,80,25);
        teach.add(b1);
        b1.addActionListener(e -> {
            new Login();
            Common.close(this);});

        l2 = JFrameSize.title("Mark list of students");
        l2.setBounds(30,80,300,30);
        teach.add(l2);

        String row[][] = { };
        String column[] ={"Roll No","Name","Maths","Science","English","Total"};
        table = new JTable(row,column);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30,120,650,130);
        getContentPane().add(scroll);
        teach.add(scroll);
        getModel();

        b2 = new JButton("Back");
        b2.setBounds(300,300,120,30);
        teach.add(b2);
        b2.addActionListener(e ->{new Teacher(); Common.close(this);});

        background.add(teach);
        add(head);
        add(background);

        Common.define(this);
    }

    public void getModel() {

        String column[] = {"Roll No","Name","Maths","Science","English","Total"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        ArrayList list;

        try (Connection con = MyConnect.getInstance().getConnection()) {
            String sql ="select * from marks";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();

            model.setRowCount(0);
            int count = 6;
            int index=1;

            while(rs.next())
            {
                list= new ArrayList();
                index=1;

                while(index<=count){
                    list.add(rs.getString(index));
                    index++;
                }
                model.addRow(list.stream().toArray());
                list.removeAll(list);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new View();
    }
}
