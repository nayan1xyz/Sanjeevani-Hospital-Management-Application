/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.dao;

import hms.dbutil.DBConnection;
import hms.pojo.UserDetailsPojo;
import hms.pojo.UserPojo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class UserDao {
    public static String validateUser(UserPojo user) throws SQLException
    {
        System.out.println(user);
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select userName from users where userId=? and password=? and userType=?");
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next())
        {
            username=rs.getString(1);
        }
        return username;
    }
    public static ArrayList<String> getAllEmpId() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select EmpId from users where UserType='RECEPTIONIST'");
        ArrayList <String> list=new ArrayList<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            list.add(id);
        }
        return list;  
    }
    public static boolean changePassword(String userid, String pwd) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("update users set password=? where userId=?");
        ps.setString(1, pwd);
        ps.setString(2, userid);
        return (ps.executeUpdate()!=0);
    }
    public static HashMap<String,String> getReceptionistList() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        HashMap <String, String> receptionist=new HashMap<>();
        ResultSet rs=st.executeQuery("select userid, username from users where userType='RECEPTIONIST'");
        while(rs.next())
        {
            receptionist.put(rs.getString(1), rs.getString(2));
        }
        return receptionist;
    }
    
}
   
