package student.management.utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JFrameSize
{
        public static JPanel panelCall()
        {
            JPanel p = new JPanel();
            p.setBackground(new Color(0,0,0,80));
            p.setLayout(null);
            return p;
        }
        public static JPanel head()
        {
            JPanel p = new JPanel();
            p.setBackground(new Color(0,0,0,90));
            p.setBounds(0,0,900,50);
            JLabel l = new JLabel("STUDENT MANAGEMENT SYSTEM FOR SCHOOL");
            l.setFont(new Font("serif",Font.BOLD,25));
            l.setForeground(Color.white);
            l.setBounds(150,10,600,30);
            p.add(l);
            p.setLayout(null);
            return p;
        }

        public static JTextField textField()
        {
            JTextField t1 = new JTextField();
            t1.setBounds(250,80,150,30);
            return t1;
        }

        public static JLabel headline(String head)
        {
            JLabel l = new JLabel(head);
            l.setFont(new Font("serif",Font.BOLD,30));
            //l.setBounds(200,20,300,30);
            return l;
        }

        public static JLabel title(String title)
        {
            JLabel l =new JLabel(title);
            l.setFont(new Font("serif",Font.BOLD,20));
            return l;
        }

    static DefaultTableModel model = new DefaultTableModel();



}
