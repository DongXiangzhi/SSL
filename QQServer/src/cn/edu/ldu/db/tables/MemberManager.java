package cn.edu.ldu.db.tables;

import cn.edu.ldu.db.beans.Member;
import cn.edu.ldu.db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberManager {  
    //返回表中所有记录
    public static void displayAllRows() throws SQLException{
        String sql="SELECT * FROM MEMBER";
        ResultSet rs=null; //结果集
        try (
            Connection conn=DBUtils.getConnection(); 
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ){
            rs=st.executeQuery(sql); //返回结果集
            rs.last();//指针到最后一条记录
            int nRows=rs.getRow();//返回记录数
            if (nRows==0) {
                System.out.println("没有找到满足查询条件的记录！\n");
            }else {
                rs.beforeFirst(); //指针到第一条记录之前
                StringBuilder buffer=new StringBuilder(); //动态字符串
                while (rs.next()) { //遍历记录集
                    buffer.append(rs.getInt("id")).append(",");
                    buffer.append(rs.getString("name")).append(",");
                    buffer.append(rs.getString("password")).append(",");
                    buffer.append(rs.getString("email")).append(",");
                    buffer.append(rs.getString("headimage")).append(",");
                    buffer.append(rs.getTimestamp("time")).append("\n");
                }//end while
                System.out.println(buffer.toString());
            }//end if
        }catch (SQLException ex) {
        }finally {
            if (rs!=null) rs.close();
        }//end try
    }//end displayAllRows
    
    //根据id查找记录
    public static Member getRowById(int id) throws SQLException {
        String sql="SELECT * FROM member WHERE id=?";
        ResultSet rs=null; //结果集
        try (
            Connection conn=DBUtils.getConnection(); 
            PreparedStatement st=conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ){     
            st.setInt(1, id); //设置参数
            rs=st.executeQuery(); //返回结果集
            if (rs.next()) { //找到
                Member bean=new Member(); //定义用户数据实体
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
                bean.setEmail(rs.getString("email"));
                bean.setHeadImage(rs.getString("headimage"));
                bean.setTime(rs.getTimestamp("time"));
                return bean; //返回用户数据实体
            }else { //没找到
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }finally {
            if (rs!=null) rs.close();
        }//end try  
    }//end getRowById
    //注册用户
    public static boolean registerUser(Member bean) throws SQLException{
        if (getRowById(bean.getId())!=null) return false;//如果用户存在，则注册失败
        String sql="INSERT INTO member (id,name,password,email,headimage,time) VALUES (?,?,?,?,?,?)";
        try (
            Connection conn=DBUtils.getConnection(); 
            PreparedStatement st=conn.prepareStatement(sql);
            ){
            //设置参数
            st.setInt(1,bean.getId()); 
            st.setString(2, bean.getName());
            st.setString(3, bean.getPassword());
            st.setString(4, bean.getEmail());
            st.setString(5, bean.getHeadImage());
            st.setTimestamp(6,bean.getTime());
            int affected=st.executeUpdate();
            return affected==1; //注册成功或失败
        } catch (SQLException ex) {
            return false;
        }finally {
        }//end try  
    }//end registerUser
    //用户登录
    public static boolean userLogin(Member bean) throws SQLException {
        String sql="SELECT * FROM member WHERE id=? AND password=?";
        ResultSet rs=null;
        try (
            Connection conn=DBUtils.getConnection(); 
            PreparedStatement st=conn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ){
            //设置参数
            st.setInt(1,bean.getId()); 
            st.setString(2, bean.getPassword());
            rs=st.executeQuery(); //返回结果集
            return rs.next(); //登录成功或失败
        } catch (SQLException ex) {
            return false;
        }finally {
            if (rs!=null) rs.close();
        }//end try  
    }//end userLogin  
    //修改用户
    public static boolean updateUser(Member bean) {      
        String sql="UPDATE member SET name=? , password= ? ,"
            + " email=? , headimage= ? , time= ? WHERE id=?";
        try (
            Connection conn=DBUtils.getConnection(); 
            PreparedStatement st=conn.prepareStatement(sql);
            ){           
            //设置参数
            st.setString(1, bean.getName());
            st.setString(2, bean.getPassword());
            st.setString(3, bean.getEmail());
            st.setString(4,bean.getHeadImage());
            st.setTimestamp(5, bean.getTime());
            st.setInt(6,bean.getId());            
            int affected=st.executeUpdate();
            return affected==1; //修改成功或失败
        } catch (SQLException ex) {
            return false;
        }//end try  
    }//end updateUser    
    //删除用户
    public static boolean deleteUser(int id) {      
        String sql="DELETE FROM member WHERE id=?";
        try (
             Connection conn=DBUtils.getConnection(); 
             PreparedStatement st=conn.prepareStatement(sql);
            ){                        
            st.setInt(1,id);         
            int affected=st.executeUpdate();
            return affected==1; //删除成功或失败
        } catch (SQLException ex) {
            return false;
        }//end try  
    }//end updateUser     
}//end class
