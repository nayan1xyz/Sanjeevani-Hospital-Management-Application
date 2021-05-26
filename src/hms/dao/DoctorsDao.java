/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.dao;

import hms.dbutil.DBConnection;
import hms.pojo.DoctorPojo;
import hms.pojo.UserDetailsPojo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class DoctorsDao {
    public static ArrayList<String> getAllDoctorId() throws SQLException 
    {
      ArrayList<String> docId=new ArrayList<>();
      Statement st=DBConnection.getConnection().createStatement();
      ResultSet rs=st.executeQuery("select doctorid from doctors where active='Y'");
      while(rs.next())
      {
          docId.add(rs.getString(1));
      }
      return docId;
    }
    public static boolean addDoctors(UserDetailsPojo user) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?,'Y')");
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getEmpId());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getUsertype().toUpperCase());
        int x=ps.executeUpdate();
        if(x>0)
            return true;
        else
            return false;
    }
    public static String getNewId() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select max(doctorid) from doctors");
        int id=1;
        if(rs.next())
        {
            String empid=rs.getString(1);
            System.out.println(empid.substring(3));
            int eno=Integer.parseInt(empid.substring(3));
            id=id+eno;
            String sr="DOC"+id;
            System.out.println(sr);
            return sr;
        }
        else
            return "DOC101";
    }
    public static boolean addDoctor(UserDetailsPojo user) throws SQLException //sir have UserPojo 
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
    public static boolean addDoctorInDoctors(DoctorPojo d) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into doctors values(?,?,?,?,?)");
        ps.setString(1, d.getUserid());
        ps.setString(2, d.getDoctorid());
        ps.setString(3, d.getQualification());
        ps.setString(4, d.getSpecialist());
        ps.setString(5,d.getActive().toString().toUpperCase());
        int result=ps.executeUpdate();
        return result>0;   
    }
    public static ArrayList<DoctorPojo> getAllDoctor() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select * from doctors where active='Y'");
        ArrayList<DoctorPojo> list=new ArrayList<>();
        
        while(rs.next())
        {
           DoctorPojo e=new DoctorPojo();
           e.setUserid(rs.getString(1));
           e.setDoctorid(rs.getString(2));
           e.setQualification(rs.getString(3));
           e.setSpecialist(rs.getString(4));
           list.add(e); 
        }
        return list;
    }
    
    public static boolean removeDoctor(String id)throws SQLException 
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("update doctors set active='N' where doctorid=? ");
        ps.setString(1, id);
        int result=ps.executeUpdate();
        return result==1;
    }
    
    
}

