/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.pojo;

/**
 *
 * @author HP
 */
public class UserProfilePojo { //sir have UserPojo

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserProfilePojo.userName = userName;
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        UserProfilePojo.userType = userType;
    }
        private static String userName;
        private static String userType;
}
