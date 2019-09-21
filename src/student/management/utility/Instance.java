package student.management.utility;

import Connect.MyConnect;
import student.management.library.StatusException;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Instance
{

    public static void result(String s, String p) throws SQLException, ClassNotFoundException {

    try (Connection con = MyConnect.getInstance().getConnection()) {
        Statement st = con.createStatement();
        ResultSet r = st.executeQuery("select * from staff where username='" + s + "' and password='" + p + "'");

        if (r.next()) {

            if (s.equals(r.getString(3)) && p.equals(r.getString(4)))
            {
                JOptionPane.showMessageDialog(null, "Login Successful", "Success", JOptionPane.PLAIN_MESSAGE);
                LoginInterface l= Factory.getInstance(r.getString(5));
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public static void save(String id, String name, String email, String total, String paid, String due, String user, String pass) {
        LinkedList<String> list = new LinkedList<>();
        list.add(id);
        list.add(name);
        list.add(email);
        list.add(total); list.add(paid);
        list.add(due);
        list.add(user);
        list.add(pass);
        AtomicInteger index= new AtomicInteger(1);

        try(Connection con =MyConnect.getInstance().getConnection())
        {
            PreparedStatement p = con.prepareStatement("insert into emp values(?,?,?,?,?,?,?,?)");
            PreparedStatement p1 = con.prepareStatement("Insert into marks values(?,?,?,?,?,?)");
            PreparedStatement p2 = con.prepareStatement("Insert into borrower values(?,?,?,?,?)");

            list.stream().forEach(x->{
                if(index.get()<=list.size()){
                    try {
                        p.setString(index.get(),x);
                        System.out.println(index+"/"+x);
                        index.getAndIncrement();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            p1.setString(1,id);
            p1.setString(2,name);
            p1.setString(3,"0");
            p1.setString(4,"0");
            p1.setString(5,"0");
            p1.setString(6,"0");

            p2.setString(1,"0");
            p2.setString(2,"0");
            p2.setString(3,"0");
            p2.setString(4,id);
            p2.setString(5,name);

            int n, n1,n2;
            n =p.executeUpdate();
            n1 = p1.executeUpdate();
            n2 = p2.executeUpdate();

            if(n==1 && n1==1 && n2==1)
            {
                JOptionPane.showMessageDialog(null,"Registered Successfully","Success",JOptionPane.PLAIN_MESSAGE);
            }

            else{
                JOptionPane.showMessageDialog(null,"Sorry! Couldn't register","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
           JOptionPane.showMessageDialog(null, " Enter Unique ID or Username", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void update(String id, String name, String email, String total, String paid, String due, String user, String pass) {

        try(Connection con = MyConnect.getInstance().getConnection())
        {
            String str = "UPDATE emp SET name = ?,email= ?,total=?, paid=?, due=?,username= ?,password=? WHERE roll = ? ";

            PreparedStatement p = con.prepareStatement(str);
            p.setString(1,name);
            p.setString(2,email);
            p.setString(3,total);
            p.setString(4,paid);
            p.setString(5, due);
            p.setString(6,user);
            p.setString(7, pass);
            p.setString(8, String.valueOf(id));

            int n = p.executeUpdate();
            if(n==1)
            {
                JOptionPane.showMessageDialog(null,"Updated Successfully","Success",JOptionPane.PLAIN_MESSAGE);
            }

            else{
                JOptionPane.showMessageDialog(null,"Sorry! Couldn't Update","Error",JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void updatestu(String roll, String name, String maths, String science, String english, String total) {

        try(Connection con = MyConnect.getInstance().getConnection())
        {
            String str = "UPDATE marks SET name = ?,maths= ?,science=?, english=?, total=? WHERE rollno = ?";

            PreparedStatement p = con.prepareStatement(str);
            p.setString(1,name);
            p.setString(2,maths);
            p.setString(3, science);
            p.setString(4,english);
            p.setString(5, total);
            p.setString(6,roll);

            int n = p.executeUpdate();
            if(n==1)
            {
                JOptionPane.showMessageDialog(null,"Updated Successfully","Success",JOptionPane.PLAIN_MESSAGE);
            }

            else{
                JOptionPane.showMessageDialog(null,"Sorry! Couldn't Update","Error",JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void upLib(String bid, String name, String author, String rack) {
        ArrayList<String>list = new ArrayList<>();
        list.add(bid);
        list.add(name);
        list.add(author);
        list.add(rack);

        AtomicInteger index= new AtomicInteger(1);

        try(Connection con = MyConnect.getInstance().getConnection())
        {
            PreparedStatement p2 = con.prepareStatement("Insert into book values(?,?,?,?)");
            list.stream().forEach(x->{
                if(index.get()<=list.size()){
                    try {
                        p2.setString(index.get(),x);
                        System.out.println(index+"/"+x);
                        index.getAndIncrement();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            int n =p2.executeUpdate();
            if(n==1 )
            {
                JOptionPane.showMessageDialog(null,"Book Registered Successfully","Success",JOptionPane.PLAIN_MESSAGE);
            }

            else{
                JOptionPane.showMessageDialog(null,"Sorry! Couldn't register","Error",JOptionPane.ERROR_MESSAGE);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public static void checkStatus(String bid, String name, String status, String roll, String name1) throws SQLException, ClassNotFoundException {


        try(Connection con = MyConnect.getInstance().getConnection())
        {
           String str = "Select * from Borrower where rollno='"+roll+"'";
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery(str);
            String s = "issued";

            if (r.next()) {
                if (s.equalsIgnoreCase(r.getString(3)))
                {
                    throw new StatusException();
                }
            else {
                    String str1 = "UPDATE borrower SET bid = ?,bookname= ?,status=?, name=? WHERE rollno = ?";

                    PreparedStatement p = con.prepareStatement(str1);
                    p.setString(1, bid);
                    p.setString(2, name);
                    p.setString(3, status);
                    p.setString(4, name1);
                    p.setString(5, roll);

                    int n = p.executeUpdate();
                    if (n == 1) {
                        JOptionPane.showMessageDialog(null, "Issued Successfully", "Success", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry! Couldn't Issue", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (StatusException e) {
            e.printStackTrace();
        }
    }

    public static void Status(String bid, String bname, String status, String roll, String name) throws SQLException, ClassNotFoundException {
        try(Connection con = MyConnect.getInstance().getConnection())
        {
            String str = "Select * from Borrower where rollno='"+roll+"'";
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery(str);
            String s = "issued";

            if (r.next()) {
                String str1 = "UPDATE borrower SET bid = ?,bookname= ?,status=?, name=? WHERE rollno = ?";

                PreparedStatement p = con.prepareStatement(str1);
                p.setString(1, bid);
                p.setString(2, bname);
                p.setString(3, status);
                p.setString(4, name);
                p.setString(5, roll);

                int n = p.executeUpdate();
                if (n == 1) {
                    JOptionPane.showMessageDialog(null, "Returned Successfully", "Success", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry! Couldn't Return", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {

         try(Connection con = MyConnect.getInstance().getConnection())
         {
             String str = "delete from emp where name=? ";
             String str2 = "delete from marks where name=? ";
             String str3 ="delete from borrower where name=? ";
             PreparedStatement p = con.prepareStatement(str);
             PreparedStatement p1 = con.prepareStatement(str2);
             PreparedStatement p2 = con.prepareStatement(str3);

             p.setString(1,id);
             p1.setString(1,id);
             p2.setString(1,id);
             int r = p.executeUpdate();
             int r1 = p1.executeUpdate();
             int r2 = p2.executeUpdate();

             if(r==1 && r1==1 && r2==1)
             {
                JOptionPane.showMessageDialog(null,"Deleted","Success",JOptionPane.PLAIN_MESSAGE);
            }
            else{
                 JOptionPane.showMessageDialog(null,"Sorry! Couldn't Delete","Error",JOptionPane.ERROR_MESSAGE);
             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
    }




}
