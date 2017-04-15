package cn.edu.ldu.db.beans;

import java.sql.Timestamp;


public class Member { //定义会员实体类，属性名与字段名相同，类型也相同
    private int id; //对应数据表中的id
    private String name; //对应数据表中的name
    private String password; //对应数据表中的password
    private String email; //对应数据表中的email
    private Timestamp time; //对应数据表中的time
    private String headImage;//对应数据表中的headImage
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    } 

    public String getHeadImage() {return headImage;}
    public void setHeadImage(String headImage) {this.headImage = headImage;}
    
}
