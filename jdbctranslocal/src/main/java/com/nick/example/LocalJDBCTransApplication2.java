package com.nick.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class LocalJDBCTransApplication2 {
    private static final Logger log = LoggerFactory.getLogger(LocalJDBCTransApplication2.class);
    public static void main(String[] args) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);


            String sql1 = "select *  from t_user";
//            String sql1 = "select *  from t_user FOR UPDATE"; //读操作需要获得锁 默认锁全表，最好加上where条件
            PreparedStatement ps1 = connection.prepareStatement(sql1);

            ResultSet rs = ps1.executeQuery();
            while (rs.next()){
                String username =   rs.getString(2);
                Integer amount =   rs.getInt(3);
                log.info("{} has amount: {}",username,amount);
            }

//            String sql2 = "UPDATE t_user SET amount = amount+100 where username =?";
//            PreparedStatement ps2 = connection.prepareStatement(sql2);
//            ps2.setString(1,"elaine");
//            ps2.executeUpdate();

            connection.commit();

            ps1.close();
            connection.close();

        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }
    }
    private static void throwException() throws Exception {
        throw new Exception("test exception");
    }

    private static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test111";
        String userName="root";
        String password= "123456";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            return null;
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
