package com.example.demo;

import com.example.demo.domain.Dog;

import java.util.List;

public interface Storage
{
    Dog storeDog(Dog dog);

    Dog getDog(long id);

    List<Dog> getAllDogs();
}
