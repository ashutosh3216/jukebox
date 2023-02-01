package org.example.DaoInterface;


import org.example.ModelClass.User;

public interface UserDao {


    public void addUser(User user);

    public boolean checkUser(String userName, String password);

    public int getuserId(String userName);


}
