package com.example.demo.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class DogBox
{
    private List<Dog> dogs;

    public DogBox()
    {
    }

    @XmlElementWrapper(name = "lowly-dogs")
    @XmlElement(name = "dog")
    public List<Dog> getDogs()
    {
        return dogs;
    }

    public void setDogs(List<Dog> dogs)
    {
        this.dogs = dogs;
    }
}
