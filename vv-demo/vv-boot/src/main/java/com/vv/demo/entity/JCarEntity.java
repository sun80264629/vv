package com.vv.demo.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "j_car")
public class JCarEntity {
    private String area;
    private String carno;
    private String name;
    private Time regdate;
    private String carCode;
    private String tel;
    private String band;

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Id
    @Column(name = "carno")
    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "regdate")
    public Time getRegdate() {
        return regdate;
    }

    public void setRegdate(Time regdate) {
        this.regdate = regdate;
    }

    @Basic
    @Column(name = "carCode")
    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "band")
    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }
}
