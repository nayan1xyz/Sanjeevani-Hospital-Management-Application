/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.dao;

import hms.dbutil.DBConnection;
import hms.pojo.EmpPojo;
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
public class EmpDao {
    public static String getNewId() throws SQLException 
    {
        
        Statement st=DBConnection.getConnection().createStatement();
       // ResultSet rs=st.executeQuery("select count(*) from employees");
        ResultSet rs=st.executeQuery("select max(EmpId) from employees");  //SQL password student, username=myhms
        //int id=101;
        int id=1;
        if(rs.next())
        {
            String empid=rs.getString(1);  //column no
            int eno=Integer.parseInt(empid.substring(1));
            //id=id+rs.getInt(1);
            id=id+eno;
            
        }
        String sr="E"+id;
        System.out.println(sr);
        return sr;
   
    }
    public static boolean addEmployee (EmpPojo e) throws SQLException 
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1, e.getEmpid());
        ps.setString(2, e.getEmpname());
        ps.setString(3, e.getJob().toUpperCase());
        ps.setDouble(4, e.getSal());
        int x=ps.executeUpdate();
        return x==1;
        
        
    }
    public static ArrayList<EmpPojo> getAllEmp() throws SQLException 
    {
        Statement st=DBConnection.getConnection().createStatement();
        ArrayList<EmpPojo> empList=new ArrayList<>();
        ResultSet rs=st.executeQuery("Select * from employees");
        while(rs.next())
        {
            EmpPojo e=new EmpPojo();
            e.setEmpid(rs.getString(1));
            e.setEmpname(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
            empList.add(e);
        }
        return empList;    
    }
    public static EmpPojo searchEmp(String empid) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select * from employees where EmpId=?");
        ps.setString(1, empid);
        ResultSet rs=ps.executeQuery();
        EmpPojo e=new EmpPojo();
        while(rs.next())
        {
            e.setEmpname(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
        }
         return e;
    }
    public static boolean deleteEmployee(String empid) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("Delete from employees where EmpId=?");
        ps.setString(1, empid);
        PreparedStatement ps1=DBConnection.getConnection().prepareStatement("Delete from users where EmpId=?");
        ps.setString(1, empid);
        int result1=ps1.executeUpdate();
        System.out.println(result1);
        int result=ps.executeUpdate();
        return result==1;
    }
    public static boolean updateEmployee(EmpPojo e) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("update employees set EmpName=?,Role=?,Sal=? where EmpId=?");
        ps.setString(1,e.getEmpname());
        ps.setString(2, e.getJob());
        ps.setDouble(3, e.getSal());
        ps.setString(4, e.getEmpid());
        int result=ps.executeUpdate();
        return result==1;
    }
    public static HashMap<String, String> getNonRegisteredReceptionistList() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select EmpId,EmpName from employees where Role='RECEPTIONIST' and EmpId not in (select EmpId from users where UserType='RECEPTIONIST')");
        HashMap<String, String> receptionist=new HashMap<>(); //in my case role='receptionist in lower case
        while(rs.next())
        {
           String id=rs.getString(1);
           String name=rs.getString(2);
           receptionist.put(id, name);
        }
        return receptionist;
                
    }
    public static ArrayList<String> getAllEmpId() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select EmpId from employees");
        ArrayList <String> list=new ArrayList<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            list.add(id);
        }
        return list;
    }
     public static HashMap<String,String> getNonRegisteredDoctorList() throws SQLException
    {
        Statement st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select EmpId,EmpName from employees where Role='DOCTOR' and EmpId not in (select EmpId from users where UserType='DOCTOR')");
        HashMap<String, String> doctor=new HashMap<>(); //in my case role='receptionist in lower case
        while(rs.next())
        {
           String id=rs.getString(1);
           String name=rs.getString(2);
           doctor.put(name,id);
        }
        return doctor;          
    }
     
    
}
