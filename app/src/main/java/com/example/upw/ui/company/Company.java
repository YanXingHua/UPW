package com.example.upw.ui.company;


import androidx.room.Entity;
//@Entity
public class Company {



//    @PrimaryKey(autoGenerate = true)
  //private int id;
//    @ColumnInfo(name = "text")
 private String name;
//    @ColumnInfo(name = "image")
  private String img;
//    @ColumnInfo(name="textColor")
 private String textcolor;
//
    public String getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(String textcolor) {
        this.textcolor = textcolor;
    }

    public Company(String name, String img,String textcolor) {
        this.name = name;
        this.img = img;
        this.textcolor=textcolor;
    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
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

}
