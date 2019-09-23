package student.management.accountant;

import Connect.MyConnect;
import student.management.utility.Common;
import student.management.utility.JFrameSize;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PaidList extends JFrame
{
    static JTable table;
    JLabel l1,l2;
    JButton b1,b2,b3;
    JPanel admin,background,head;

    static DefaultTableModel model = new DefaultTableModel();

    PaidList()
    {
        admin = JFrameSize.panelCall();
        admin.setBounds(100,100,700,400);
        head = JFrameSize.head();
        //logo
        Image img = Common.getIcon();
        Image icon1 = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(icon1);

        background = JFrameSize.panelCall();
        ImageIcon bg_img = Common.background();
        JLabel background=new JLabel("",bg_img,JLabel.CENTER);
        background.setBounds(0,0,900,600);

        l1 = JFrameSize.headline("Fee Paid List");
        l1.setBounds(280,20,400,30);
        admin.add(l1);

        String row[][] = { };
        String column[] ={"Roll No","Name","Total","Paid","Due"};
        table = new JTable(row,column);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30,120,650,130);
        getContentPane().add(scroll);
        admin.add(scroll);
        getModel();

        b2 = new JButton("BACK");
        b2.setBounds(300,300,100,30);
        admin.add(b2);
        b2.addActionListener(e ->{new Account(); Common.close(this);} );

        background.add(admin);
        add(head);
        add(background);

        Common.define(this);

    }
    public static void getModel()
    {
        String column[] = {"rollno", "name", "total","paid","due"};

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);

        int id;
        String name;
        String total;
        String paid;
        String due;

        try (Connection con = MyConnect.getInstance().getConnection()) {
            String sql ="select * from emp";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                total = rs.getString(4);
                paid = rs.getString(5);
                due = rs.getString(6);

               if(Integer.parseInt(due)<=0)
                {
                    model.addRow(new Object[]{id, name, total,paid,due});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* public static void main(String[] args) {
        new PaidList();
    }*/
}
