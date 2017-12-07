package model;

import org.dbutils.handler.Column;

//实体类
public class User {

    @Column("U_ID")
    private int uid;
    @Column("U_NAME")
    private String name;
    @Column("U_AGE")
    private int age;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
