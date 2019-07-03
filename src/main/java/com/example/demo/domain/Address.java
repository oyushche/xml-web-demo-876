package com.example.demo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address
{
    private long id;
    private String country;
    private String city;
    private int home;

    public Address()
    {
    }

    public Address(String country, String city, int home)
    {
        this.country = country;
        this.city = city;
        this.home = home;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getHome()
    {
        return home;
    }

    public void setHome(int home)
    {
        this.home = home;
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", home=" + home +
                '}';
    }
}
