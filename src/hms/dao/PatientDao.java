/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.dao;

import hms.dbutil.DBConnection;
import hms.pojo.DoctorPojo;
import hms.pojo.PatientPojo;
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
public class PatientDao {
    public static boolean addPatient(PatientPojo p) throws SQLException
    {
       PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?)");
       ps.setString(1, p.getP_id());
       ps.setString(2, p.getF_name());
       ps.setString(3, p.getS_name());
       ps.setInt(4, p.getAge());
       ps.setString(5, p.getOpd());
       ps.setString(6, p.getGender());
       ps.setString(7, p.getM_status());
       ps.setDate(8, p.getDate());
       ps.setString(9, p.getAddress());
       ps.setString(10, p.getCity());
       ps.setString(11, p.getMno());
       ps.setString(12, p.getDoctor_id());
       return (ps.executeUpdate()!=0);
    }
    public static String getNewId() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select max(p_id) from patient");
        int id=1;
        if(rs.next())
        {
            String empid=rs.getString(1);
            if(empid==null)
                return "P101";
            System.out.println(empid.substring(1));
            int eno=Integer.parseInt(empid.substring(1));
            id=id+eno;
            String sr="P"+id;
            return sr;
        }
        else
            return "P101";
    }
    public static ArrayList<PatientPojo> getAllPatient() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select * from patient");
        ArrayList<PatientPojo> list=new ArrayList<>();
        
        while(rs.next())
        {
           PatientPojo e=new PatientPojo();
           e.setP_id(rs.getString(1));
           e.setF_name(rs.getString(2));
           e.setS_name(rs.getString(3));
           e.setAge(rs.getInt(4));
           e.setOpd(rs.getString(5));
           e.setGender(rs.getString(6));
           e.setM_status(rs.getString(7));
           e.setDate(rs.getDate(8));
           e.setAddress(rs.getString(9));
           e.setCity(rs.getString(10));
           e.setMno(rs.getString(11));
           e.setDoctor_id(rs.getString(12));
           
           list.add(e); 
        }
        return list;
    }
    public static ArrayList<String> getAllPatientId() throws SQLException 
    {
      ArrayList<String> pId=new ArrayList<>();
      Statement st=DBConnection.getConnection().createStatement();
      ResultSet rs=st.executeQuery("select p_id from patient");
      while(rs.next())
      {
          pId.add(rs.getString(1));
      }
      return pId;
    }
    public static boolean removePatient(String id)throws SQLException 
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("delete from patient where p_id=?");
        ps.setString(1, id);
        int result=ps.executeUpdate();
        return result==1;
    }
    public static boolean updatePatient(PatientPojo p)throws SQLException
    {
        
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("update patient set f_name=?,s_name=?,age=?,opd=?,gender=?,m_status=?,address=?,city=?,phone_no=?,doctor_id=? where p_id=?");
        ps.setString(1, p.getF_name());
        ps.setString(2, p.getS_name());
        ps.setInt(3, p.getAge());
        ps.setString(4, p.getOpd());
        ps.setString(5, p.getGender());
        ps.setString(6, p.getM_status());
        ps.setString(7, p.getAddress());
        ps.setString(8, p.getCity());
        ps.setString(9, p.getMno());
        ps.setString(10, p.getDoctor_id());
        ps.setString(11, p.getP_id());
        int response=ps.executeUpdate();
        return response==1;  
    }
  public static HashMap<String,PatientPojo> getPatientById() throws SQLException
  {
      HashMap<String,PatientPojo> list=new HashMap<>();
      Statement st=DBConnection.getConnection().createStatement();
      ResultSet rs=st.executeQuery("select * from patient");
      while(rs.next())
      {
          PatientPojo p=new PatientPojo();
          p.setF_name(rs.getString(2));
          p.setS_name(rs.getString(3));
          p.setAge(rs.getInt(4));
          p.setOpd(rs.getString(5));
          p.setGender(rs.getString(6));
          p.setM_status(rs.getString(7));
          p.setDate(rs.getDate(8));
          p.setAddress(rs.getString(9));
          p.setCity(rs.getString(10));
          p.setMno(rs.getString(11));
          p.setDoctor_id(rs.getString(12));
          list.put(rs.getString(1), p);
      }
      return list;
      
  }
  public static ArrayList<PatientPojo> getPatientForAppointment() throws SQLException
  {
      Statement st=DBConnection.getConnection().createStatement();
      ResultSet rs=st.executeQuery("select * from patient");
      ArrayList<PatientPojo> list=new ArrayList<>();
      while(rs.next())
      {
          PatientPojo p=new PatientPojo();
          p.setP_id(rs.getString(1));
          p.setF_name(rs.getString(2));
          p.setS_name(rs.getString(3));
          p.setAge(rs.getInt(4));
          p.setOpd(rs.getString(5));
          list.add(p);
      }
      return list;
      
  }
    
    
    
}
