package student.management.accountant;

import Connect.MyConnect;
import student.management.Login;
import student.management.utility.ButtonRender;
import student.management.utility.Common;
import student.management.utility.JFrameSize;
import student.management.utility.LoginInterface;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account extends JFrame implements LoginInterface
{
    static JTable table;
    JLabel l1,l2;
    JButton b1,b2,b3;
    JPanel admin,background,head;
    String Data;
    JTextField t1;

    static DefaultTableModel model = new DefaultTableModel();

    public Account()
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

        l1 = JFrameSize.headline("College Accounts");
        l1.setBounds(280,20,400,30);
        admin.add(l1);

        b1 = new JButton("Logout");
        b1.setBounds(550,60,80,25);
        admin.add(b1);
        b1.addActionListener(e -> {
            new Login();
            Common.close(this);});

        l2 = JFrameSize.title("Students of School");
        l2.setBounds(30,80,300,30);
        admin.add(l2);

        model.setDataVector(new Object[][]{{"1","2","3","4","5","6","EDIT","DELETE"},{"1","2","3","4","5","6","EDIT","DELETE"}},
                new Object[]{"Roll No","Name","Email","Total","Paid","Due","EDIT"});

        table = new JTable(model);
        table.getColumn("EDIT").setCellRenderer(new ButtonRender());

        table.getColumn("EDIT").setCellEditor(new ButtonEditor(new JCheckBox(),this));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(30,120,650,130);
        getContentPane().add(scroll);

        admin.add(scroll);
        getModel();

        b2 = new JButton("Paid list");
        b2.setBounds(200,300,100,30);
        admin.add(b2);
       b2.addActionListener(e ->{new PaidList(); Common.close(this);});

        b3 = new JButton("Dues List");
        b3.setBounds(400,300,100,30);
        admin.add(b3);
         b3.addActionListener(e ->{
             try {
                 new DueList();
             } catch (SQLException ex) {
                 ex.printStackTrace();
             } catch (ClassNotFoundException ex) {
                 ex.printStackTrace();
             }
             Common.close(this);});

        //get each row value
        table.setCellSelectionEnabled(true);
        ListSelectionModel select = table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int[] row = table.getSelectedRows();
                int[] col = table.getSelectedColumns();
                for (int i = 0; i < row.length; i++) {
                    Data = (String) table.getValueAt(row[i], 1);

                }
                System.out.println(" Table name selected is: "+Data);
            }
        });
        background.add(admin);
        add(head);
        add(background);

        Common.define(this);

    }

   public void getModel()
    {
        String column[] = {"rollno", "name", "email","total","paid","due"};

        ArrayList list;

        try (Connection con = MyConnect.getInstance().getConnection()) {
            String sql ="select * from emp";
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
                list.add("EDIT");
                model.addRow(list.stream().toArray());
                list.removeAll(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class ButtonEditor extends DefaultCellEditor
    {
        protected JButton button;
        private String label;
        private boolean isPushed;

        ButtonEditor(JCheckBox checkBox,JFrame frame)
        {
            super(checkBox);

            button = new JButton("EDIT");
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        new AccEdit(Data);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Common.close(frame);
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }


        public Object getCellEditorValue() {
            if (isPushed)  {
                // JOptionPane.showMessageDialog(button ,label + ":edit");

            }
            isPushed = false;
            return new String( label ) ;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

    }

    /*public static void main(String[] args) {
        new Account();
    }*/
}
