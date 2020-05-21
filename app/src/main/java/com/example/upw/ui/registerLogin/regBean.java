package com.example.upw.ui.registerLogin;

import android.service.autofill.FillEventHistory;

import java.util.List;

public class regBean {


    /**
     * data : {"company":"东莞联达毛纺有限公司","firstname":"张","surname":"三","title":"Mr","email":"3466195386@qq.com","telephone":"12345678901","market":"10"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public regBean(DataBean data) {
        this.data = data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {


        private String company;
        private String firstname;
        private String surname;
        private String title;
        private String email;
        private String telephone;
        private String market;
        private List<String>business_activity;

        public DataBean(String company, String firstname, String surname, String title, String email, String telephone, String market,List<String>business_activity) {
            this.company = company;
            this.firstname = firstname;
            this.surname = surname;
            this.title = title;
            this.email = email;
            this.telephone = telephone;
            this.market = market;
            this.business_activity=business_activity;
        }

        public List<String> getBusiness_activity() {
            return business_activity;
        }

        public void setBusiness_activity(List<String> business_activity) {
            this.business_activity = business_activity;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }
    }
}


