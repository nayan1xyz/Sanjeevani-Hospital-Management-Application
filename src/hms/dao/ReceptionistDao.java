/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.dao;

import hms.dbutil.DBConnection;
import hms.pojo.EmpPojo;
import hms.pojo.UserDetailsPojo;
import hms.pojo.UserPojo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ReceptionistDao {
    public static boolean addReceptionist(UserDetailsPojo user) throws SQLException //sir have UserPojo 
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?)");
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getEmpId());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getUsertype());
        int x=ps.executeUpdate();
        return x>0;
    }
    public static ArrayList<UserDetailsPojo> getAllReceptionist() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select * from users where UserType='RECEPTIONIST'");
        ArrayList<UserDetailsPojo> list=new ArrayList<>();
        
        while(rs.next())
        {
           UserDetailsPojo e=new UserDetailsPojo();
           e.setUserid(rs.getString(1));
           e.setUsername(rs.getString(2));
           e.setEmpId(rs.getString(3));
           e.setUsertype(rs.getString(5));
           list.add(e); 
        }
        return list;
    }
     public static UserDetailsPojo searchReceptionist(String id)throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from users where EmpId=?");
        ps.setString(1, id);
        ArrayList<UserDetailsPojo> list=new ArrayList<>();
        UserDetailsPojo e=new UserDetailsPojo();
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
           e.setUserid(rs.getString(1));
           e.setUsername(rs.getString(2));
           e.setUsertype(rs.getString(5));
        }
        return e;
    }
    public static boolean deleteReceptionist(String empid) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("Delete from users where EmpId=?");
        ps.setString(1, empid);
        int result=ps.executeUpdate();
        return result==1;
    }
}

   
