package com.example.home.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EquipmentBean {
    @Id
    private long id;
    String name;
    String img;
    String status;//开启1，关闭0
    String catgeory;// 列别：客厅，厨房
    String userTime;//使用时长


    @Generated(hash = 1114912291)
    public EquipmentBean(long id, String name, String img, String status,
            String catgeory, String userTime) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.status = status;
        this.catgeory = catgeory;
        this.userTime = userTime;
    }

    @Generated(hash = 1113208600)
    public EquipmentBean() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCatgeory() {
        return catgeory;
    }

    public void setCatgeory(String catgeory) {
        this.catgeory = catgeory;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }
}







