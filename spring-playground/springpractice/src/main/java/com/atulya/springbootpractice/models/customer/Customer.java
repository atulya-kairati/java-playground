package com.atulya.springbootpractice.models.customer;

import jakarta.persistence.*;

import java.util.Objects;


/**
 * ALL the annotation
 * are only need by JPA
 * NOT by JDBC
 */
@Entity
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "constraint_unique_mail",
                        columnNames = "mail"
                )
        }
)
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_id_seq",
            sequenceName = "customer_id_seq",
            allocationSize = 1 // default is 50 which mismatches with the db and causes error
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String mail;
    @Column(nullable = false)
    int age;

    public Customer() {
    }

    public Customer(long id, String name, String mail, int age) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.age = age;
    }

    public Customer(String name, String mail, int age) {
        this.name = name;
        this.mail = mail;
        this.age = age;
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
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", mail='" + mail + '\'' + ", age=" + age + '}';
    }
}
