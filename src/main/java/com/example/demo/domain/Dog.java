package com.example.demo.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Dog
{
    private long id;

    private String name;
    private int age;

    private Address address;

    public Dog()
    {
    }

    public Dog(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @XmlAttribute
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }
}
