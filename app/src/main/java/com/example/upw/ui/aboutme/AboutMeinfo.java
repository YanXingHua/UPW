package com.example.upw.ui.aboutme;

import java.util.ArrayList;
import java.util.List;

public class AboutMeinfo{

        private int id;
        private String type;
        private String company;
        private String receiver;
        private String street;
        private String tel;
        private String zip;
        private String city;
        private String province;
        private String country;
        private boolean default_address;
        private String addr;
        private String country_name;
        private Object cust_no;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isDefault_address() {
        return default_address;
    }

    public void setDefault_address(boolean default_address) {
        this.default_address = default_address;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public Object getCust_no() {
        return cust_no;
    }

    public void setCust_no(Object cust_no) {
        this.cust_no = cust_no;
    }

    public AboutMeinfo(int id, String type, String company, String receiver, String street, String tel, String zip, String city, String province,
                   String country, boolean default_address, String addr, String country_name, Object cust_no){
            this.id=id;
            this.type=type;
            this.company=company;
            this.receiver=receiver;
            this.street=street;
            this.tel=tel;
            this.zip=zip;
            this.city=city;
            this.province=province;
            this.country=country;
            this.default_address=default_address;
            this.addr=addr;
            this.country_name=country_name;
            this.cust_no=cust_no;
        }

}
