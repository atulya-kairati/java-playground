package com.atulya.springbootpractice.models.customer;

import java.util.Objects;

public class Customer {
    int id;
    String name;
    String mail;
    int age;

    public Customer() {
    }

    public Customer(int id, String name, String mail, int age) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.age = age;
    }


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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && age == customer.age && name.equals(customer.name) && mail.equals(customer.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mail, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", age=" + age +
                '}';
    }
}
