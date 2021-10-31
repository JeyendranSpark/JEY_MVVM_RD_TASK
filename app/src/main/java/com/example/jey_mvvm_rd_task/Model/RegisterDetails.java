package com.example.jey_mvvm_rd_task.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "register_table")
public class RegisterDetails {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String name;
    private String email;
    private String dob;
    private String password;
    private String type;
//@ColumnInfo(typeAffinity = ColumnInfo.BLOB)



    public RegisterDetails(String name, String email, String dob, String password, String type) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.type = type;
    }



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
