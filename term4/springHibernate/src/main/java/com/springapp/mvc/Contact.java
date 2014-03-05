package com.springapp.mvc;

public class Contact {
   private String phone;
   private String name;

   public void setName(String name) {
      this.name = name;
   }
   public String getName() {
      return name;
   }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}