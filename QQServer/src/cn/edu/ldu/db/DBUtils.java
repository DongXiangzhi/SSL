package cn.edu.ldu.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    //Derby数据库Url
    private static final String DBURL="jdbc:derby://localhost:1527/QQDB";
    private static final String USERNAME="nbuser";//Derby数据库用户名
    private static final String PASSWORD="password";//Derby登录密码
    public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
    }//end getConnection
}//end class
