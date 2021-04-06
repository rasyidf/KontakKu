package com.rasyidf.kontakku;

import androidx.annotation.NonNull;

public class KontakItem {
    private int id;
    private String name;
    private String phone;
    public KontakItem()
    {
    }
    public KontakItem(int id,String name,String address)
    {
        this.id=id;
        this.name=name;
        this.phone =address;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getId() {
        return id;
    }
    public String getPhone() {
        return phone;
    }
    public String getName() {
        return name;
    }
}
