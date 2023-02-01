package org.example.DaoImpl;


import org.example.DaoInterface.UserDao;
import org.example.ModelClass.User;
import org.example.DBConnection.DBConnection;

import java.sql.*;

public class UserDaoImpl implements UserDao {



    public void addUser(User user) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("insert into users (username, name, phoneNo, Passwords) values(?,?,?,?)");
            prs.setString(1, user.getUsername());
            prs.setString(2, user.getName());
            prs.setString(3, user.getPhoneNo());
            prs.setString(4, user.getPasswords());
            int rows = prs.executeUpdate();
            if (rows > 0) {
                System.out.print("\nUser account created successfully");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkUser(String userName, String password) {
        try (Connection con = DBConnection.getConnection()) {
            Statement  prs = con.createStatement();
              String s="select * from users";
            ResultSet rs = prs.executeQuery(s);
            while (rs.next()) {
                String u_name = rs.getString("username");
                String u_password = rs.getString("Passwords");
                if (u_name.equals(userName)&&u_password.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public int getuserId(String userName) {
        try (Connection con = DBConnection.getConnection()) {
            Statement  prs = con.createStatement();
            String s="select * from users where username='"+userName+"'";
            ResultSet rs = prs.executeQuery(s);
            while (rs.next()) {
              return rs.getInt("userid");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }




}
